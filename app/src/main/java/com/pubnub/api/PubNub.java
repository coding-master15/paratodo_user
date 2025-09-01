/*     */ package com.pubnub.api;
/*     */ 
/*     */

import com.pubnub.api.builder.PresenceBuilder;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.builder.SubscribeBuilder;
import com.pubnub.api.builder.UnsubscribeBuilder;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.endpoints.DeleteMessages;
import com.pubnub.api.endpoints.FetchMessages;
import com.pubnub.api.endpoints.History;
import com.pubnub.api.endpoints.Time;
import com.pubnub.api.endpoints.access.Audit;
import com.pubnub.api.endpoints.access.Grant;
import com.pubnub.api.endpoints.channel_groups.AddChannelChannelGroup;
import com.pubnub.api.endpoints.channel_groups.AllChannelsChannelGroup;
import com.pubnub.api.endpoints.channel_groups.DeleteChannelGroup;
import com.pubnub.api.endpoints.channel_groups.ListAllChannelGroup;
import com.pubnub.api.endpoints.channel_groups.RemoveChannelChannelGroup;
import com.pubnub.api.endpoints.presence.GetState;
import com.pubnub.api.endpoints.presence.HereNow;
import com.pubnub.api.endpoints.presence.SetState;
import com.pubnub.api.endpoints.presence.WhereNow;
import com.pubnub.api.endpoints.pubsub.Publish;
import com.pubnub.api.endpoints.push.AddChannelsToPush;
import com.pubnub.api.endpoints.push.ListPushProvisions;
import com.pubnub.api.endpoints.push.RemoveAllPushChannelsForDevice;
import com.pubnub.api.endpoints.push.RemoveChannelsFromPush;
import com.pubnub.api.managers.BasePathManager;
import com.pubnub.api.managers.MapperManager;
import com.pubnub.api.managers.PublishSequenceManager;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.SubscriptionManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.vendor.Crypto;

import java.util.Date;
import java.util.List;
import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PubNub
/*     */ {
/*     */   private static final int TIMESTAMP_DIVIDER = 1000;
/*     */   private static final int MAX_SEQUENCE = 65535;
/*     */   private static final String SDK_VERSION = "4.17.0";
/*     */   private PNConfiguration configuration;
/*     */   private MapperManager mapper;
/*     */   private String instanceId;
/*     */   private SubscriptionManager subscriptionManager;
/*     */   private BasePathManager basePathManager;
/*     */   private PublishSequenceManager publishSequenceManager;
/*     */   private TelemetryManager telemetryManager;
/*     */   private RetrofitManager retrofitManager;
/*     */   
/*     */   public PubNub(PNConfiguration initialConfig) {
/*  56 */     this.configuration = initialConfig;
/*  57 */     this.mapper = new MapperManager();
/*  58 */     this.telemetryManager = new TelemetryManager();
/*  59 */     this.basePathManager = new BasePathManager(initialConfig);
/*  60 */     this.retrofitManager = new RetrofitManager(this);
/*  61 */     this.subscriptionManager = new SubscriptionManager(this, this.retrofitManager, this.telemetryManager);
/*  62 */     this.publishSequenceManager = new PublishSequenceManager(65535);
/*  63 */     this.instanceId = UUID.randomUUID().toString();
/*     */   }
/*     */   
/*     */   public String getBaseUrl() {
/*  67 */     return this.basePathManager.getBasePath();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addListener(SubscribeCallback listener) {
/*  73 */     this.subscriptionManager.addListener(listener);
/*     */   }
/*     */   
/*     */   public void removeListener(SubscribeCallback listener) {
/*  77 */     this.subscriptionManager.removeListener(listener);
/*     */   }
/*     */   
/*     */   public SubscribeBuilder subscribe() {
/*  81 */     return new SubscribeBuilder(this.subscriptionManager);
/*     */   }
/*     */   
/*     */   public UnsubscribeBuilder unsubscribe() {
/*  85 */     return new UnsubscribeBuilder(this.subscriptionManager);
/*     */   }
/*     */   
/*     */   public PresenceBuilder presence() {
/*  89 */     return new PresenceBuilder(this.subscriptionManager);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AddChannelsToPush addPushNotificationsOnChannels() {
/*  95 */     return new AddChannelsToPush(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public RemoveChannelsFromPush removePushNotificationsFromChannels() {
/*  99 */     return new RemoveChannelsFromPush(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public RemoveAllPushChannelsForDevice removeAllPushNotificationsFromDeviceWithPushToken() {
/* 103 */     return new RemoveAllPushChannelsForDevice(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public ListPushProvisions auditPushChannelProvisions() {
/* 107 */     return new ListPushProvisions(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WhereNow whereNow() {
/* 113 */     return new WhereNow(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public HereNow hereNow() {
/* 117 */     return new HereNow(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public Time time() {
/* 121 */     return new Time(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public History history() {
/* 125 */     return new History(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public FetchMessages fetchMessages() {
/* 129 */     return new FetchMessages(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public DeleteMessages deleteMessages() {
/* 133 */     return new DeleteMessages(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public Audit audit() {
/* 137 */     return new Audit(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public Grant grant() {
/* 141 */     return new Grant(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public GetState getPresenceState() {
/* 145 */     return new GetState(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public SetState setPresenceState() {
/* 149 */     return new SetState(this, this.subscriptionManager, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public Publish publish() {
/* 153 */     return new Publish(this, this.publishSequenceManager, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */ 
/*     */   
/*     */   public ListAllChannelGroup listAllChannelGroups() {
/* 158 */     return new ListAllChannelGroup(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public AllChannelsChannelGroup listChannelsForChannelGroup() {
/* 162 */     return new AllChannelsChannelGroup(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public AddChannelChannelGroup addChannelsToChannelGroup() {
/* 166 */     return new AddChannelChannelGroup(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public RemoveChannelChannelGroup removeChannelsFromChannelGroup() {
/* 170 */     return new RemoveChannelChannelGroup(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */   
/*     */   public DeleteChannelGroup deleteChannelGroup() {
/* 174 */     return new DeleteChannelGroup(this, this.telemetryManager, this.retrofitManager);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String decrypt(String inputString) throws PubNubException {
/* 186 */     if (inputString == null) {
/* 187 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_INVALID_ARGUMENTS).build();
/*     */     }
/*     */     
/* 190 */     return decrypt(inputString, getConfiguration().getCipherKey());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String decrypt(String inputString, String cipherKey) throws PubNubException {
/* 202 */     if (inputString == null) {
/* 203 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_INVALID_ARGUMENTS).build();
/*     */     }
/*     */     
/* 206 */     return (new Crypto(cipherKey)).decrypt(inputString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String encrypt(String inputString) throws PubNubException {
/* 216 */     if (inputString == null) {
/* 217 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_INVALID_ARGUMENTS).build();
/*     */     }
/*     */     
/* 220 */     return encrypt(inputString, getConfiguration().getCipherKey());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String encrypt(String inputString, String cipherKey) throws PubNubException {
/* 232 */     if (inputString == null) {
/* 233 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_INVALID_ARGUMENTS).build();
/*     */     }
/*     */     
/* 236 */     return (new Crypto(cipherKey)).encrypt(inputString);
/*     */   }
/*     */   
/*     */   public int getTimestamp() {
/* 240 */     return (int)((new Date()).getTime() / 1000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInstanceId() {
/* 247 */     return this.instanceId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRequestId() {
/* 254 */     return UUID.randomUUID().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVersion() {
/* 261 */     return "4.17.0";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void stop() {
/* 269 */     this.subscriptionManager.stop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroy() {
/*     */     try {
/* 277 */       this.subscriptionManager.destroy(false);
/* 278 */       this.retrofitManager.destroy(false);
/* 279 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void forceDestroy() {
/*     */     try {
/* 289 */       this.subscriptionManager.destroy(true);
/* 290 */       this.retrofitManager.destroy(true);
/* 291 */       this.telemetryManager.stopCleanUpTimer();
/* 292 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reconnect() {
/* 301 */     this.subscriptionManager.reconnect();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disconnect() {
/* 308 */     this.subscriptionManager.disconnect();
/*     */   }
/*     */   
/*     */   public Publish fire() {
/* 312 */     return publish().shouldStore(Boolean.valueOf(false)).replicate(Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public List<String> getSubscribedChannels() {
/* 316 */     return this.subscriptionManager.getSubscribedChannels();
/*     */   }
/*     */   
/*     */   public List<String> getSubscribedChannelGroups() {
/* 320 */     return this.subscriptionManager.getSubscribedChannelGroups();
/*     */   }
/*     */   
/*     */   public void unsubscribeAll() {
/* 324 */     this.subscriptionManager.unsubscribeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration getConfiguration() {
/* 329 */     return this.configuration;
/*     */   }
/*     */ 
/*     */   
/*     */   public MapperManager getMapper() {
/* 334 */     return this.mapper;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/PubNub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */