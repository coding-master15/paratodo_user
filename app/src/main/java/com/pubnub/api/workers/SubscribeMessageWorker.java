/*     */ package com.pubnub.api.workers;
/*     */ 
/*     */

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.managers.DuplicationManager;
import com.pubnub.api.managers.ListenerManager;
import com.pubnub.api.managers.MapperManager;
import com.pubnub.api.models.consumer.PNErrorData;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.server.PresenceEnvelope;
import com.pubnub.api.models.server.PublishMetaData;
import com.pubnub.api.models.server.SubscribeMessage;
import com.pubnub.api.vendor.Crypto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SubscribeMessageWorker
/*     */   implements Runnable
/*     */ {
/*     */   private PubNub pubnub;
/*     */   private ListenerManager listenerManager;
/*     */   private LinkedBlockingQueue<SubscribeMessage> queue;
/*     */   private DuplicationManager duplicationManager;
/*     */   private boolean isRunning;
/*     */   
/*     */   public SubscribeMessageWorker(PubNub pubnubInstance, ListenerManager listenerManagerInstance, LinkedBlockingQueue<SubscribeMessage> queueInstance, DuplicationManager dupManager) {
/*  43 */     this.pubnub = pubnubInstance;
/*  44 */     this.listenerManager = listenerManagerInstance;
/*  45 */     this.queue = queueInstance;
/*  46 */     this.duplicationManager = dupManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  51 */     takeMessage();
/*     */   }
/*     */ 
/*     */   
/*     */   private void takeMessage() {
/*  56 */     this.isRunning = true;
/*     */     
/*  58 */     while (this.isRunning) {
/*     */       try {
/*  60 */         processIncomingPayload(this.queue.take());
/*  61 */       } catch (InterruptedException interruptedException) {
/*  62 */         this.isRunning = false;
/*  63 */         Log.i("", "take message interrupted", interruptedException);
/*     */       } 
/*     */     } 
/*     */   } private JsonElement processMessage(JsonElement input) {
/*     */     String str1, str2;
/*     */     JsonElement jsonElement;
/*     */     JsonObject jsonObject = null;
/*  70 */     if (this.pubnub.getConfiguration().getCipherKey() == null) {
/*  71 */       return input;
/*     */     }
/*     */     
/*  74 */     Crypto crypto = new Crypto(this.pubnub.getConfiguration().getCipherKey());
/*  75 */     MapperManager mapperManager = this.pubnub.getMapper();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     if (mapperManager.isJsonObject(input) && mapperManager.hasField(input, "pn_other")) {
/*  81 */       str1 = mapperManager.elementToString(input, "pn_other");
/*     */     } else {
/*  83 */       str1 = mapperManager.elementToString(input);
/*     */     } 
/*     */     
/*     */     try {
/*  87 */       str2 = crypto.decrypt(str1);
/*  88 */     } catch (PubNubException pubNubException) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  93 */       PNStatus pNStatus = PNStatus.builder().error(true).errorData(new PNErrorData(pubNubException.getMessage(), (Exception)pubNubException)).operation(PNOperationType.PNSubscribeOperation).category(PNStatusCategory.PNDecryptionErrorCategory).build();
/*     */       
/*  95 */       this.listenerManager.announce(pNStatus);
/*  96 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 100 */       jsonElement = (JsonElement)mapperManager.fromJson(str2, JsonElement.class);
/* 101 */     } catch (PubNubException pubNubException) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       PNStatus pNStatus = PNStatus.builder().error(true).errorData(new PNErrorData(pubNubException.getMessage(), (Exception)pubNubException)).operation(PNOperationType.PNSubscribeOperation).category(PNStatusCategory.PNMalformedResponseCategory).build();
/*     */       
/* 108 */       this.listenerManager.announce(pNStatus);
/* 109 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 113 */     if (mapperManager.isJsonObject(input) && mapperManager.hasField(input, "pn_other")) {
/* 114 */       JsonObject jsonObject1 = mapperManager.getAsObject(input);
/* 115 */       mapperManager.putOnObject(jsonObject1, "pn_other", jsonElement);
/* 116 */       jsonObject = jsonObject1;
/*     */     } 
/*     */     
/* 119 */     return (JsonElement)jsonObject;
/*     */   }
/*     */   
/*     */   private void processIncomingPayload(SubscribeMessage message) {
/* 123 */     MapperManager mapperManager = this.pubnub.getMapper();
/*     */     
/* 125 */     String str1 = message.getChannel();
/* 126 */     String str2 = message.getSubscriptionMatch();
/* 127 */     PublishMetaData publishMetaData = message.getPublishMetaData();
/*     */     
/* 129 */     if (str1 != null && str1.equals(str2)) {
/* 130 */       str2 = null;
/*     */     }
/*     */     
/* 133 */     if (this.pubnub.getConfiguration().isDedupOnSubscribe()) {
/* 134 */       if (this.duplicationManager.isDuplicate(message)) {
/*     */         return;
/*     */       }
/* 137 */       this.duplicationManager.addEntry(message);
/*     */     } 
/*     */ 
/*     */     
/* 141 */     if (message.getChannel().endsWith("-pnpres")) {
/* 142 */       PresenceEnvelope presenceEnvelope = (PresenceEnvelope)mapperManager.convertValue(message.getPayload(), PresenceEnvelope.class);
/*     */       
/* 144 */       String str3 = null;
/* 145 */       String str4 = null;
/*     */       
/* 147 */       if (str1 != null) {
/* 148 */         str3 = PubNubUtil.replaceLast(str1, "-pnpres", "");
/*     */       }
/* 150 */       if (str2 != null) {
/* 151 */         str4 = PubNubUtil.replaceLast(str2, "-pnpres", "");
/*     */       }
/*     */       
/* 154 */       JsonElement jsonElement = message.getPayload().getAsJsonObject().get("here_now_refresh");
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
/* 173 */       PNPresenceEventResult pNPresenceEventResult = PNPresenceEventResult.builder().event(presenceEnvelope.getAction()).actualChannel((str2 != null) ? str1 : null).subscribedChannel((str2 != null) ? str2 : str1).channel(str3).subscription(str4).state(presenceEnvelope.getData()).timetoken(publishMetaData.getPublishTimetoken()).occupancy(presenceEnvelope.getOccupancy()).uuid(presenceEnvelope.getUuid()).timestamp(presenceEnvelope.getTimestamp()).join(getDelta(message.getPayload().getAsJsonObject().get("join"))).leave(getDelta(message.getPayload().getAsJsonObject().get("leave"))).timeout(getDelta(message.getPayload().getAsJsonObject().get("timeout"))).hereNowRefresh(Boolean.valueOf((jsonElement != null && jsonElement.getAsBoolean()))).build();
/*     */       
/* 175 */       this.listenerManager.announce(pNPresenceEventResult);
/*     */     } else {
/* 177 */       JsonElement jsonElement = processMessage(message.getPayload());
/*     */       
/* 179 */       if (jsonElement == null) {
/* 180 */         Log.d("", "unable to parse payload on #processIncomingMessages");
/*     */       }
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
/* 194 */       PNMessageResult pNMessageResult = PNMessageResult.builder().message(jsonElement).actualChannel((str2 != null) ? str1 : null).subscribedChannel((str2 != null) ? str2 : str1).channel(str1).subscription(str2).timetoken(publishMetaData.getPublishTimetoken()).publisher(message.getIssuingClientId()).userMetadata(message.getUserMetadata()).build();
/*     */ 
/*     */       
/* 197 */       this.listenerManager.announce(pNMessageResult);
/*     */     } 
/*     */   }
/*     */   
/*     */   private List<String> getDelta(JsonElement delta) {
/* 202 */     ArrayList<String> arrayList = new ArrayList();
/* 203 */     if (delta != null) {
/* 204 */       JsonArray jsonArray = delta.getAsJsonArray();
/* 205 */       for (byte b = 0; b < jsonArray.size(); b++) {
/* 206 */         arrayList.add(jsonArray.get(b).getAsString());
/*     */       }
/*     */     } 
/*     */     
/* 210 */     return arrayList;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/workers/SubscribeMessageWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */