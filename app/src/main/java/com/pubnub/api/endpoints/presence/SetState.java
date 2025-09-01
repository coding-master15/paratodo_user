/*     */ package com.pubnub.api.endpoints.presence;
/*     */ 
/*     */

import com.google.gson.JsonElement;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.builder.dto.StateOperation;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.SubscriptionManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.presence.PNSetStateResult;
import com.pubnub.api.models.server.Envelope;

import java.util.ArrayList;
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
/*     */ public class SetState
/*     */   extends Endpoint<Envelope<JsonElement>, PNSetStateResult>
/*     */ {
/*     */   private SubscriptionManager subscriptionManager;
/*     */   private List<String> channels;
/*     */   private List<String> channelGroups;
/*     */   private Object state;
/*     */   private String uuid;
/*     */   
/*     */   public SetState(PubNub pubnub, SubscriptionManager subscriptionManagerInstance, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/*  39 */     super(pubnub, telemetryManager, retrofit);
/*  40 */     this.subscriptionManager = subscriptionManagerInstance;
/*  41 */     this.channels = new ArrayList<>();
/*  42 */     this.channelGroups = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  47 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  52 */     return this.channelGroups;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  57 */     if (this.state == null) {
/*  58 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_STATE_MISSING).build();
/*     */     }
/*  60 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/*  61 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*     */     }
/*  63 */     if (this.channels.size() == 0 && this.channelGroups.size() == 0) {
/*  64 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_AND_GROUP_MISSING).build();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Call<Envelope<JsonElement>> doWork(Map<String, String> params) throws PubNubException {
/*  70 */     String str1 = (this.uuid != null) ? this.uuid : getPubnub().getConfiguration().getUuid();
/*     */ 
/*     */ 
/*     */     
/*  74 */     if (str1.equals(getPubnub().getConfiguration().getUuid())) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  79 */       StateOperation stateOperation = StateOperation.builder().state(this.state).channels(this.channels).channelGroups(this.channelGroups).build();
/*  80 */       this.subscriptionManager.adaptStateBuilder(stateOperation);
/*     */     } 
/*     */     
/*  83 */     if (this.channelGroups.size() > 0) {
/*  84 */       params.put("channel-group", PubNubUtil.joinString(this.channelGroups, ","));
/*     */     }
/*     */     
/*  87 */     String str2 = getPubnub().getMapper().toJson(this.state);
/*     */     
/*  89 */     str2 = PubNubUtil.urlEncode(str2);
/*  90 */     params.put("state", str2);
/*     */     
/*  92 */     String str3 = (this.channels.size() > 0) ? PubNubUtil.joinString(this.channels, ",") : ",";
/*     */     
/*  94 */     return getRetrofit().getPresenceService().setState(getPubnub().getConfiguration().getSubscribeKey(), str3, str1, params);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected PNSetStateResult createResponse(Response<Envelope<JsonElement>> input) throws PubNubException {
/* 100 */     if (input.body() == null || ((Envelope)input.body()).getPayload() == null) {
/* 101 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*     */     }
/*     */ 
/*     */     
/* 105 */     PNSetStateResult.PNSetStateResultBuilder pNSetStateResultBuilder = PNSetStateResult.builder().state((JsonElement)((Envelope)input.body()).getPayload());
/*     */     
/* 107 */     return pNSetStateResultBuilder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/* 112 */     return PNOperationType.PNSetStateOperation;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/* 117 */     return true;
/*     */   }
/*     */   
/*     */   public SetState channels(List<String> channels) {
/* 121 */     this.channels = channels;
/* 122 */     return this;
/*     */   }
/*     */   
/*     */   public SetState channelGroups(List<String> channelGroups) {
/* 126 */     this.channelGroups = channelGroups;
/* 127 */     return this;
/*     */   }
/*     */   
/*     */   public SetState state(Object state) {
/* 131 */     this.state = state;
/* 132 */     return this;
/*     */   }
/*     */   
/*     */   public SetState uuid(String uuid) {
/* 136 */     this.uuid = uuid;
/* 137 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/presence/SetState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */