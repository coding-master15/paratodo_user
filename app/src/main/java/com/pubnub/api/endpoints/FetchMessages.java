/*     */ package com.pubnub.api.endpoints;
/*     */ 
/*     */

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.MapperManager;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.history.PNFetchMessagesResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.server.FetchMessagesEnvelope;
import com.pubnub.api.models.server.HistoryForChannelsItem;
import com.pubnub.api.vendor.Crypto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FetchMessages
/*     */   extends Endpoint<FetchMessagesEnvelope, PNFetchMessagesResult>
/*     */ {
/*     */   private static final int MAX_MESSAGES = 25;
/*     */   private List<String> channels;
/*     */   private Integer maximumPerChannel;
/*     */   private Long start;
/*     */   private Long end;
/*     */   
/*     */   public FetchMessages(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/*  40 */     super(pubnub, telemetryManager, retrofit);
/*  41 */     this.channels = new ArrayList<>();
/*  42 */     this.maximumPerChannel = Integer.valueOf(1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  47 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  58 */     if (this.channels == null || this.channels.size() == 0) {
/*  59 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_MISSING).build();
/*     */     }
/*  61 */     if (this.maximumPerChannel != null && this.maximumPerChannel.intValue() > 25) {
/*  62 */       this.maximumPerChannel = Integer.valueOf(25);
/*  63 */     } else if (this.maximumPerChannel == null) {
/*  64 */       this.maximumPerChannel = Integer.valueOf(1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Call<FetchMessagesEnvelope> doWork(Map<String, String> params) {
/*  70 */     params.put("max", String.valueOf(this.maximumPerChannel));
/*     */     
/*  72 */     if (this.start != null) {
/*  73 */       params.put("start", Long.toString(this.start.longValue()).toLowerCase());
/*     */     }
/*  75 */     if (this.end != null) {
/*  76 */       params.put("end", Long.toString(this.end.longValue()).toLowerCase());
/*     */     }
/*     */     
/*  79 */     return getRetrofit().getHistoryService().fetchMessages(getPubnub().getConfiguration().getSubscribeKey(), PubNubUtil.joinString(this.channels, ","), params);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNFetchMessagesResult createResponse(Response<FetchMessagesEnvelope> input) throws PubNubException {
/*  84 */     if (input.body() == null) {
/*  85 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*     */     }
/*     */     
/*  88 */     PNFetchMessagesResult.PNFetchMessagesResultBuilder pNFetchMessagesResultBuilder = PNFetchMessagesResult.builder();
/*  89 */     HashMap<String, List<PNMessageResult>> hashMap = new HashMap<>();
/*     */     
/*  91 */     FetchMessagesEnvelope fetchMessagesEnvelope = (FetchMessagesEnvelope)input.body();
/*     */     
/*  93 */     for (Map.Entry<String, List<HistoryForChannelsItem>> entry : fetchMessagesEnvelope.getChannels().entrySet()) {
/*     */       
/*  95 */       ArrayList<PNMessageResult> arrayList = new ArrayList();
/*     */       
/*  97 */       for (HistoryForChannelsItem historyForChannelsItem : entry.getValue()) {
/*  98 */         PNMessageResult.PNMessageResultBuilder pNMessageResultBuilder = PNMessageResult.builder();
/*  99 */         pNMessageResultBuilder.channel((String)entry.getKey());
/* 100 */         JsonElement jsonElement = processMessage(historyForChannelsItem.getMessage());
/* 101 */         pNMessageResultBuilder.message(jsonElement);
/* 102 */         pNMessageResultBuilder.timetoken(historyForChannelsItem.getTimetoken());
/* 103 */         arrayList.add(pNMessageResultBuilder.build());
/*     */       } 
/*     */       
/* 106 */       hashMap.put(entry.getKey(), arrayList);
/*     */     } 
/*     */     
/* 109 */     pNFetchMessagesResultBuilder.channels(hashMap);
/*     */     
/* 111 */     return pNFetchMessagesResultBuilder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/* 116 */     return PNOperationType.PNFetchMessagesOperation;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/* 121 */     return true;
/*     */   }
/*     */   private JsonElement processMessage(JsonElement message) throws PubNubException {
/*     */     String str1;
/*     */     JsonObject jsonObject = null;
/* 126 */     if (getPubnub().getConfiguration().getCipherKey() == null) {
/* 127 */       return message;
/*     */     }
/*     */     
/* 130 */     Crypto crypto = new Crypto(getPubnub().getConfiguration().getCipherKey());
/* 131 */     MapperManager mapperManager = getPubnub().getMapper();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     if (mapperManager.isJsonObject(message) && mapperManager.hasField(message, "pn_other")) {
/* 137 */       str1 = mapperManager.elementToString(message, "pn_other");
/*     */     } else {
/* 139 */       str1 = mapperManager.elementToString(message);
/*     */     } 
/*     */     
/* 142 */     String str2 = crypto.decrypt(str1);
/* 143 */     JsonElement jsonElement = (JsonElement)mapperManager.fromJson(str2, JsonElement.class);
/*     */ 
/*     */     
/* 146 */     if (mapperManager.isJsonObject(message) && mapperManager.hasField(message, "pn_other")) {
/* 147 */       JsonObject jsonObject1 = mapperManager.getAsObject(message);
/* 148 */       mapperManager.putOnObject(jsonObject1, "pn_other", jsonElement);
/* 149 */       jsonObject = jsonObject1;
/*     */     } 
/*     */     
/* 152 */     return (JsonElement)jsonObject;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/FetchMessages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */