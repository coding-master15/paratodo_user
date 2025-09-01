/*     */ package com.pubnub.api.endpoints.presence;
/*     */ 
/*     */

import com.google.gson.JsonElement;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.MapperManager;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.presence.PNGetStateResult;
import com.pubnub.api.models.server.Envelope;

import java.util.ArrayList;
import java.util.HashMap;
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
/*     */ public class GetState
/*     */   extends Endpoint<Envelope<JsonElement>, PNGetStateResult>
/*     */ {
/*     */   private List<String> channels;
/*     */   private List<String> channelGroups;
/*     */   private String uuid;
/*     */   
/*     */   public GetState(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/*  35 */     super(pubnub, telemetryManager, retrofit);
/*  36 */     this.channels = new ArrayList<>();
/*  37 */     this.channelGroups = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  42 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  47 */     return this.channelGroups;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  52 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/*  53 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*     */     }
/*  55 */     if (this.channels.size() == 0 && this.channelGroups.size() == 0) {
/*  56 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_AND_GROUP_MISSING).build();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Call<Envelope<JsonElement>> doWork(Map<String, String> params) {
/*  62 */     if (this.channelGroups.size() > 0) {
/*  63 */       params.put("channel-group", PubNubUtil.joinString(this.channelGroups, ","));
/*     */     }
/*     */     
/*  66 */     String str1 = (this.channels.size() > 0) ? PubNubUtil.joinString(this.channels, ",") : ",";
/*     */     
/*  68 */     String str2 = (this.uuid != null) ? this.uuid : getPubnub().getConfiguration().getUuid();
/*     */     
/*  70 */     return getRetrofit().getPresenceService().getState(getPubnub().getConfiguration().getSubscribeKey(), str1, str2, params);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNGetStateResult createResponse(Response<Envelope<JsonElement>> input) throws PubNubException {
/*  75 */     HashMap<String, JsonElement> hashMap = new HashMap<>();
/*  76 */     MapperManager mapperManager = getPubnub().getMapper();
/*     */     
/*  78 */     if (this.channels.size() == 1 && this.channelGroups.size() == 0) {
/*  79 */       hashMap.put(this.channels.get(0), input.body().getPayload());
/*     */     } else {
/*  81 */       for (Iterator<Map.Entry<String, JsonElement>> iterator = mapperManager.getObjectIterator((JsonElement)((Envelope)input.body()).getPayload()); iterator.hasNext(); ) {
/*  82 */         Map.Entry<String, JsonElement> entry = iterator.next();
/*  83 */         hashMap.put(entry.getKey(), entry.getValue());
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     return PNGetStateResult.builder().stateByUUID(hashMap).build();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/*  92 */     return PNOperationType.PNGetState;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public GetState channels(List<String> channels) {
/* 102 */     this.channels = channels;
/* 103 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GetState channelGroups(List<String> channelGroups) {
/* 108 */     this.channelGroups = channelGroups;
/* 109 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GetState uuid(String uuid) {
/* 114 */     this.uuid = uuid;
/* 115 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/presence/GetState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */