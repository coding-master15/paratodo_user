/*     */ package com.pubnub.api.managers;
/*     */ 
/*     */

import com.pubnub.api.PubNub;
import com.pubnub.api.builder.dto.PresenceOperation;
import com.pubnub.api.builder.dto.StateOperation;
import com.pubnub.api.builder.dto.SubscribeOperation;
import com.pubnub.api.builder.dto.UnsubscribeOperation;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.ReconnectionCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.endpoints.presence.Heartbeat;
import com.pubnub.api.endpoints.presence.Leave;
import com.pubnub.api.endpoints.pubsub.Subscribe;
import com.pubnub.api.enums.PNHeartbeatNotificationOptions;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.server.SubscribeEnvelope;
import com.pubnub.api.models.server.SubscribeMessage;
import com.pubnub.api.workers.SubscribeMessageWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SubscriptionManager
/*     */ {
/*     */   private static final int HEARTBEAT_INTERVAL_MULTIPLIER = 1000;
/*     */   private PubNub pubnub;
/*     */   private TelemetryManager telemetryManager;
/*     */   private Subscribe subscribeCall;
/*     */   private Heartbeat heartbeatCall;
/*     */   private LinkedBlockingQueue<SubscribeMessage> messageQueue;
/*     */   private DuplicationManager duplicationManager;
/*     */   private Long timetoken;
/*     */   private Long storedTimetoken;
/*     */   private String region;
/*     */   private Timer timer;
/*     */   private StateManager subscriptionState;
/*     */   private ListenerManager listenerManager;
/*     */   private ReconnectionManager reconnectionManager;
/*     */   private RetrofitManager retrofitManager;
/*     */   private Thread consumerThread;
/*     */   private boolean subscriptionStatusAnnounced;
/*     */   
/*     */   public SubscriptionManager(PubNub pubnubInstance, RetrofitManager retrofitManagerInstance, TelemetryManager telemetry) {
/*  72 */     this.pubnub = pubnubInstance;
/*  73 */     this.telemetryManager = telemetry;
/*     */     
/*  75 */     this.subscriptionStatusAnnounced = false;
/*  76 */     this.messageQueue = new LinkedBlockingQueue<>();
/*  77 */     this.subscriptionState = new StateManager();
/*     */     
/*  79 */     this.listenerManager = new ListenerManager(this.pubnub);
/*  80 */     this.reconnectionManager = new ReconnectionManager(this.pubnub);
/*  81 */     this.retrofitManager = retrofitManagerInstance;
/*  82 */     this.duplicationManager = new DuplicationManager(this.pubnub.getConfiguration());
/*     */     
/*  84 */     this.timetoken = Long.valueOf(0L);
/*  85 */     this.storedTimetoken = null;
/*     */     
/*  87 */     this.reconnectionManager.setReconnectionListener(new ReconnectionCallback()
/*     */         {
/*     */           public void onReconnection() {
/*  90 */             SubscriptionManager.this.reconnect();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  96 */             PNStatus pNStatus = PNStatus.builder().error(false).affectedChannels(SubscriptionManager.this.subscriptionState.prepareChannelList(true)).affectedChannelGroups(SubscriptionManager.this.subscriptionState.prepareChannelGroupList(true)).category(PNStatusCategory.PNReconnectedCategory).build();
/*     */             
/*  98 */             SubscriptionManager.this.subscriptionStatusAnnounced = true;
/*  99 */             SubscriptionManager.this.listenerManager.announce(pNStatus);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onMaxReconnectionExhaustion() {
/* 109 */             PNStatus pNStatus = PNStatus.builder().error(false).category(PNStatusCategory.PNReconnectionAttemptsExhausted).affectedChannels(SubscriptionManager.this.subscriptionState.prepareChannelList(true)).affectedChannelGroups(SubscriptionManager.this.subscriptionState.prepareChannelGroupList(true)).build();
/* 110 */             SubscriptionManager.this.listenerManager.announce(pNStatus);
/*     */             
/* 112 */             SubscriptionManager.this.disconnect();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 117 */     if (this.pubnub.getConfiguration().isStartSubscriberThread()) {
/* 118 */       this.consumerThread = new Thread((Runnable)new SubscribeMessageWorker(this.pubnub, this.listenerManager, this.messageQueue, this.duplicationManager));
/* 119 */       this.consumerThread.setName("Subscription Manager Consumer Thread");
/* 120 */       this.consumerThread.start();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addListener(SubscribeCallback listener) {
/* 125 */     this.listenerManager.addListener(listener);
/*     */   }
/*     */   
/*     */   public void removeListener(SubscribeCallback listener) {
/* 129 */     this.listenerManager.removeListener(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void reconnect() {
/* 134 */     startSubscribeLoop();
/* 135 */     registerHeartbeatTimer();
/*     */   }
/*     */   
/*     */   public synchronized void disconnect() {
/* 139 */     stopHeartbeatTimer();
/* 140 */     stopSubscribeLoop();
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public synchronized void stop() {
/* 146 */     disconnect();
/* 147 */     this.consumerThread.interrupt();
/*     */   }
/*     */   
/*     */   public synchronized void destroy(boolean forceDestroy) {
/* 151 */     disconnect();
/* 152 */     if (forceDestroy && this.consumerThread != null) {
/* 153 */       this.consumerThread.interrupt();
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void adaptStateBuilder(StateOperation stateOperation) {
/* 158 */     this.subscriptionState.adaptStateBuilder(stateOperation);
/* 159 */     reconnect();
/*     */   }
/*     */   
/*     */   public synchronized void adaptSubscribeBuilder(SubscribeOperation subscribeOperation) {
/* 163 */     this.subscriptionState.adaptSubscribeBuilder(subscribeOperation);
/*     */     
/* 165 */     this.subscriptionStatusAnnounced = false;
/*     */     
/* 167 */     this.duplicationManager.clearHistory();
/*     */     
/* 169 */     if (subscribeOperation.getTimetoken() != null) {
/* 170 */       this.timetoken = subscribeOperation.getTimetoken();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 175 */     if (this.timetoken.longValue() != 0L) {
/* 176 */       this.storedTimetoken = this.timetoken;
/*     */     }
/* 178 */     this.timetoken = Long.valueOf(0L);
/*     */     
/* 180 */     reconnect();
/*     */   }
/*     */   
/*     */   public void adaptPresenceBuilder(PresenceOperation presenceOperation) {
/* 184 */     this.subscriptionState.adaptPresenceBuilder(presenceOperation);
/*     */     
/* 186 */     if (!this.pubnub.getConfiguration().isSupressLeaveEvents() && !presenceOperation.isConnected()) {
/* 187 */       (new Leave(this.pubnub, this.telemetryManager, this.retrofitManager))
/* 188 */         .channels(presenceOperation.getChannels()).channelGroups(presenceOperation.getChannelGroups())
/* 189 */         .async(new PNCallback<Boolean>()
/*     */           {
/*     */             public void onResponse(Boolean result, PNStatus status) {
/* 192 */               SubscriptionManager.this.listenerManager.announce(status);
/*     */             }
/*     */           });
/*     */     }
/*     */     
/* 197 */     registerHeartbeatTimer();
/*     */   }
/*     */   
/*     */   public synchronized void adaptUnsubscribeBuilder(UnsubscribeOperation unsubscribeOperation) {
/* 201 */     this.subscriptionState.adaptUnsubscribeBuilder(unsubscribeOperation);
/*     */     
/* 203 */     this.subscriptionStatusAnnounced = false;
/*     */     
/* 205 */     if (!this.pubnub.getConfiguration().isSupressLeaveEvents()) {
/* 206 */       (new Leave(this.pubnub, this.telemetryManager, this.retrofitManager))
/* 207 */         .channels(unsubscribeOperation.getChannels()).channelGroups(unsubscribeOperation.getChannelGroups())
/* 208 */         .async(new PNCallback<Boolean>()
/*     */           {
/*     */             public void onResponse(Boolean result, PNStatus status) {
/* 211 */               SubscriptionManager.this.listenerManager.announce(status);
/*     */             }
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 218 */     if (this.subscriptionState.isEmpty()) {
/* 219 */       this.region = null;
/* 220 */       this.storedTimetoken = null;
/* 221 */       this.timetoken = Long.valueOf(0L);
/*     */     } else {
/* 223 */       this.storedTimetoken = this.timetoken;
/* 224 */       this.timetoken = Long.valueOf(0L);
/*     */     } 
/*     */     
/* 227 */     reconnect();
/*     */   }
/*     */ 
/*     */   
/*     */   private void registerHeartbeatTimer() {
/* 232 */     stopHeartbeatTimer();
/*     */ 
/*     */     
/* 235 */     if (this.pubnub.getConfiguration().getHeartbeatInterval() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 239 */     this.timer = new Timer();
/* 240 */     this.timer.schedule(new TimerTask()
/*     */         {
/*     */           public void run() {
/* 243 */             SubscriptionManager.this.performHeartbeatLoop();
/*     */           }
/* 245 */         },  0L, (this.pubnub.getConfiguration().getHeartbeatInterval() * 1000));
/*     */   }
/*     */ 
/*     */   
/*     */   private void stopHeartbeatTimer() {
/* 250 */     if (this.timer != null) {
/* 251 */       this.timer.cancel();
/* 252 */       this.timer = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void startSubscribeLoop() {
/* 258 */     stopSubscribeLoop();
/*     */     
/* 260 */     List<String> list1 = this.subscriptionState.prepareChannelList(true);
/* 261 */     List<String> list2 = this.subscriptionState.prepareChannelGroupList(true);
/*     */ 
/*     */     
/* 264 */     if (list1.isEmpty() && list2.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 268 */     this
/*     */ 
/*     */       
/* 271 */       .subscribeCall = (new Subscribe(this.pubnub, this.retrofitManager)).channels(list1).channelGroups(list2).timetoken(this.timetoken).region(this.region).filterExpression(this.pubnub.getConfiguration().getFilterExpression());
/*     */     
/* 273 */     this.subscribeCall.async(new PNCallback<SubscribeEnvelope>()
/*     */         {
/*     */           public void onResponse(SubscribeEnvelope result, PNStatus status) {
/* 276 */             if (status.isError()) {
/*     */               
/* 278 */               if (status.getCategory() == PNStatusCategory.PNTimeoutCategory) {
/* 279 */                 SubscriptionManager.this.startSubscribeLoop();
/*     */               } else {
/* 281 */                 SubscriptionManager.this.disconnect();
/* 282 */                 SubscriptionManager.this.listenerManager.announce(status);
/*     */ 
/*     */                 
/* 285 */                 SubscriptionManager.this.reconnectionManager.startPolling();
/*     */               } 
/*     */               
/*     */               return;
/*     */             } 
/*     */             
/* 291 */             if (!SubscriptionManager.this.subscriptionStatusAnnounced) {
/*     */ 
/*     */ 
/*     */               
/* 295 */               PNStatus pNStatus = SubscriptionManager.this.createPublicStatus(status).category(PNStatusCategory.PNConnectedCategory).error(false).build();
/* 296 */               SubscriptionManager.this.subscriptionStatusAnnounced = true;
/* 297 */               SubscriptionManager.this.listenerManager.announce(pNStatus);
/*     */             } 
/*     */             
/* 300 */             Integer integer = SubscriptionManager.this.pubnub.getConfiguration().getRequestMessageCountThreshold();
/* 301 */             if (integer != null && integer.intValue() <= result.getMessages().size()) {
/*     */ 
/*     */ 
/*     */               
/* 305 */               PNStatus pNStatus = SubscriptionManager.this.createPublicStatus(status).category(PNStatusCategory.PNRequestMessageCountExceededCategory).error(false).build();
/*     */               
/* 307 */               SubscriptionManager.this.listenerManager.announce(pNStatus);
/*     */             } 
/*     */             
/* 310 */             if (result.getMessages().size() != 0) {
/* 311 */               SubscriptionManager.this.messageQueue.addAll(result.getMessages());
/*     */             }
/*     */             
/* 314 */             if (SubscriptionManager.this.storedTimetoken != null) {
/* 315 */               SubscriptionManager.this.timetoken = SubscriptionManager.this.storedTimetoken;
/* 316 */               SubscriptionManager.this.storedTimetoken = null;
/*     */             } else {
/* 318 */               SubscriptionManager.this.timetoken = result.getMetadata().getTimetoken();
/*     */             } 
/*     */             
/* 321 */             SubscriptionManager.this.region = result.getMetadata().getRegion();
/* 322 */             SubscriptionManager.this.startSubscribeLoop();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void stopSubscribeLoop() {
/* 329 */     if (this.subscribeCall != null) {
/* 330 */       this.subscribeCall.silentCancel();
/* 331 */       this.subscribeCall = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void performHeartbeatLoop() {
/* 336 */     if (this.heartbeatCall != null) {
/* 337 */       this.heartbeatCall.silentCancel();
/* 338 */       this.heartbeatCall = null;
/*     */     } 
/*     */     
/* 341 */     List<String> list1 = this.subscriptionState.prepareChannelList(false);
/* 342 */     List<String> list2 = this.subscriptionState.prepareChannelGroupList(false);
/* 343 */     Map<String, Object> map = this.subscriptionState.createStatePayload();
/*     */     
/* 345 */     List<String> list3 = this.subscriptionState.prepareHeartbeatChannelList(false);
/* 346 */     List<String> list4 = this.subscriptionState.prepareHeartbeatChannelGroupList(false);
/*     */ 
/*     */ 
/*     */     
/* 350 */     if (list1.isEmpty() && list2
/* 351 */       .isEmpty() && list3
/* 352 */       .isEmpty() && list4
/* 353 */       .isEmpty()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 358 */     ArrayList<String> arrayList1 = new ArrayList();
/* 359 */     arrayList1.addAll(list1);
/* 360 */     arrayList1.addAll(list3);
/*     */     
/* 362 */     ArrayList<String> arrayList2 = new ArrayList();
/* 363 */     arrayList2.addAll(list2);
/* 364 */     arrayList2.addAll(list4);
/*     */     
/* 366 */     this
/*     */       
/* 368 */       .heartbeatCall = (new Heartbeat(this.pubnub, this.telemetryManager, this.retrofitManager)).channels(arrayList1).channelGroups(arrayList2).state(map);
/*     */     
/* 370 */     this.heartbeatCall.async(new PNCallback<Boolean>()
/*     */         {
/*     */           public void onResponse(Boolean result, PNStatus status)
/*     */           {
/* 374 */             PNHeartbeatNotificationOptions pNHeartbeatNotificationOptions = SubscriptionManager.this.pubnub.getConfiguration().getHeartbeatNotificationOptions();
/*     */             
/* 376 */             if (status.isError()) {
/* 377 */               if (pNHeartbeatNotificationOptions == PNHeartbeatNotificationOptions.ALL || pNHeartbeatNotificationOptions == PNHeartbeatNotificationOptions.FAILURES)
/*     */               {
/* 379 */                 SubscriptionManager.this.listenerManager.announce(status);
/*     */               }
/*     */ 
/*     */               
/* 383 */               SubscriptionManager.this.stopHeartbeatTimer();
/*     */             
/*     */             }
/* 386 */             else if (pNHeartbeatNotificationOptions == PNHeartbeatNotificationOptions.ALL) {
/* 387 */               SubscriptionManager.this.listenerManager.announce(status);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized List<String> getSubscribedChannels() {
/* 396 */     return this.subscriptionState.prepareChannelList(false);
/*     */   }
/*     */   
/*     */   public synchronized List<String> getSubscribedChannelGroups() {
/* 400 */     return this.subscriptionState.prepareChannelGroupList(false);
/*     */   }
/*     */   
/*     */   public synchronized void unsubscribeAll() {
/* 404 */     adaptUnsubscribeBuilder(UnsubscribeOperation.builder()
/* 405 */         .channelGroups(this.subscriptionState.prepareChannelGroupList(false))
/* 406 */         .channels(this.subscriptionState.prepareChannelList(false))
/* 407 */         .build());
/*     */   }
/*     */   
/*     */   private PNStatus.PNStatusBuilder createPublicStatus(PNStatus privateStatus) {
/* 411 */     return PNStatus.builder()
/* 412 */       .statusCode(privateStatus.getStatusCode())
/* 413 */       .authKey(privateStatus.getAuthKey())
/* 414 */       .operation(privateStatus.getOperation())
/* 415 */       .affectedChannels(privateStatus.getAffectedChannels())
/* 416 */       .affectedChannelGroups(privateStatus.getAffectedChannelGroups())
/* 417 */       .clientRequest(privateStatus.getClientRequest())
/* 418 */       .origin(privateStatus.getOrigin())
/* 419 */       .tlsEnabled(privateStatus.isTlsEnabled());
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/managers/SubscriptionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */