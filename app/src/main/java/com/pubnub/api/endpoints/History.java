/*     */ package com.pubnub.api.endpoints;
/*     */ 
/*     */

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.MapperManager;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.history.PNHistoryItemResult;
import com.pubnub.api.models.consumer.history.PNHistoryResult;
import com.pubnub.api.vendor.Crypto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
/*     */ 
/*     */ 
/*     */ public class History
/*     */   extends Endpoint<JsonElement, PNHistoryResult>
/*     */ {
/*     */   private static final int MAX_COUNT = 100;
/*     */   private String channel;
/*     */   private Long start;
/*     */   private Long end;
/*     */   private Boolean reverse;
/*     */   private Integer count;
/*     */   private Boolean includeTimetoken;
/*     */   
/*     */   public History(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/*  42 */     super(pubnub, telemetryManager, retrofit);
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  47 */     return Collections.singletonList(this.channel);
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  57 */     if (this.channel == null || this.channel.isEmpty()) {
/*  58 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_MISSING).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Call<JsonElement> doWork(Map<String, String> params) {
/*  65 */     if (this.reverse != null) {
/*  66 */       params.put("reverse", String.valueOf(this.reverse));
/*     */     }
/*     */     
/*  69 */     if (this.includeTimetoken != null) {
/*  70 */       params.put("include_token", String.valueOf(this.includeTimetoken));
/*     */     }
/*     */     
/*  73 */     if (this.count != null && this.count.intValue() > 0 && this.count.intValue() <= 100) {
/*  74 */       params.put("count", String.valueOf(this.count));
/*     */     } else {
/*  76 */       params.put("count", "100");
/*     */     } 
/*     */     
/*  79 */     if (this.start != null) {
/*  80 */       params.put("start", Long.toString(this.start.longValue()).toLowerCase());
/*     */     }
/*  82 */     if (this.end != null) {
/*  83 */       params.put("end", Long.toString(this.end.longValue()).toLowerCase());
/*     */     }
/*     */     
/*  86 */     return getRetrofit().getHistoryService().fetchHistory(getPubnub().getConfiguration().getSubscribeKey(), this.channel, params);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNHistoryResult createResponse(Response<JsonElement> input) throws PubNubException {
/*  91 */     PNHistoryResult.PNHistoryResultBuilder pNHistoryResultBuilder = PNHistoryResult.builder();
/*  92 */     ArrayList<PNHistoryItemResult> arrayList = new ArrayList();
/*  93 */     MapperManager mapperManager = getPubnub().getMapper();
/*     */     
/*  95 */     if (input.body() != null) {
/*  96 */       Long long_1 = mapperManager.elementToLong(mapperManager.getArrayElement((JsonElement)input.body(), 1));
/*  97 */       Long long_2 = mapperManager.elementToLong(mapperManager.getArrayElement((JsonElement)input.body(), 2));
/*     */       
/*  99 */       if (long_1.longValue() == 0L && long_2.longValue() == 0L) {
/* 100 */         throw PubNubException.builder()
/* 101 */           .pubnubError(PubNubErrorBuilder.PNERROBJ_HTTP_ERROR)
/* 102 */           .errormsg("History is disabled")
/* 103 */           .jso((JsonElement)input.body())
/* 104 */           .build();
/*     */       }
/*     */       
/* 107 */       pNHistoryResultBuilder.startTimetoken(long_1);
/* 108 */       pNHistoryResultBuilder.endTimetoken(long_2);
/*     */ 
/*     */       
/* 111 */       for (Iterator<JsonElement> iterator = mapperManager.getArrayIterator(mapperManager.getArrayElement((JsonElement)input.body(), 0)); iterator.hasNext(); ) {
/* 112 */         JsonElement jsonElement2, jsonElement1 = iterator.next();
/* 113 */         PNHistoryItemResult.PNHistoryItemResultBuilder pNHistoryItemResultBuilder = PNHistoryItemResult.builder();
/*     */ 
/*     */         
/* 116 */         if (this.includeTimetoken != null && this.includeTimetoken.booleanValue()) {
/* 117 */           pNHistoryItemResultBuilder.timetoken(mapperManager.elementToLong(jsonElement1, "timetoken"));
/* 118 */           jsonElement2 = processMessage(mapperManager.getField(jsonElement1, "message"));
/*     */         } else {
/* 120 */           jsonElement2 = processMessage(jsonElement1);
/*     */         } 
/*     */         
/* 123 */         pNHistoryItemResultBuilder.entry(jsonElement2);
/* 124 */         arrayList.add(pNHistoryItemResultBuilder.build());
/*     */       } 
/*     */       
/* 127 */       pNHistoryResultBuilder.messages(arrayList);
/*     */     } 
/*     */     
/* 130 */     return pNHistoryResultBuilder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/* 135 */     return PNOperationType.PNHistoryOperation;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/* 140 */     return true;
/*     */   }
/*     */   private JsonElement processMessage(JsonElement message) throws PubNubException {
/*     */     String str1;
/*     */     JsonObject jsonObject = null;
/* 145 */     if (getPubnub().getConfiguration().getCipherKey() == null) {
/* 146 */       return message;
/*     */     }
/*     */     
/* 149 */     Crypto crypto = new Crypto(getPubnub().getConfiguration().getCipherKey());
/* 150 */     MapperManager mapperManager = getPubnub().getMapper();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     if (mapperManager.isJsonObject(message) && mapperManager.hasField(message, "pn_other")) {
/* 156 */       str1 = mapperManager.elementToString(message, "pn_other");
/*     */     } else {
/* 158 */       str1 = mapperManager.elementToString(message);
/*     */     } 
/*     */     
/* 161 */     String str2 = crypto.decrypt(str1);
/* 162 */     JsonElement jsonElement = (JsonElement)getPubnub().getMapper().fromJson(str2, JsonElement.class);
/*     */ 
/*     */     
/* 165 */     if (mapperManager.isJsonObject(message) && mapperManager.hasField(message, "pn_other")) {
/* 166 */       JsonObject jsonObject1 = mapperManager.getAsObject(message);
/* 167 */       mapperManager.putOnObject(jsonObject1, "pn_other", jsonElement);
/* 168 */       jsonObject = jsonObject1;
/*     */     } 
/*     */     
/* 171 */     return (JsonElement)jsonObject;
/*     */   }
/*     */ 
/*     */   
/*     */   public History channel(String channel) {
/* 176 */     this.channel = channel;
/* 177 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public History start(Long start) {
/* 182 */     this.start = start;
/* 183 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public History end(Long end) {
/* 188 */     this.end = end;
/* 189 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public History reverse(Boolean reverse) {
/* 194 */     this.reverse = reverse;
/* 195 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public History count(Integer count) {
/* 200 */     this.count = count;
/* 201 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public History includeTimetoken(Boolean includeTimetoken) {
/* 206 */     this.includeTimetoken = includeTimetoken;
/* 207 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/History.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */