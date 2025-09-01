/*    */ package com.pubnub.api.endpoints.presence;
/*    */ 
/*    */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.presence.PNWhereNowResult;
import com.pubnub.api.models.server.Envelope;
import com.pubnub.api.models.server.presence.WhereNowPayload;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WhereNow
/*    */   extends Endpoint<Envelope<WhereNowPayload>, PNWhereNowResult>
/*    */ {
/*    */   private String uuid;
/*    */   
/*    */   public WhereNow(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/* 27 */     super(pubnub, telemetryManager, retrofit);
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannels() {
/* 32 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannelGroups() {
/* 37 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void validateParams() throws PubNubException {
/* 42 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/* 43 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected Call<Envelope<WhereNowPayload>> doWork(Map<String, String> params) {
/* 49 */     return getRetrofit().getPresenceService().whereNow(getPubnub().getConfiguration().getSubscribeKey(), (this.uuid != null) ? this.uuid : 
/* 50 */         getPubnub().getConfiguration().getUuid(), params);
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNWhereNowResult createResponse(Response<Envelope<WhereNowPayload>> input) throws PubNubException {
/* 55 */     if (input.body() == null || ((Envelope)input.body()).getPayload() == null) {
/* 56 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 61 */     return PNWhereNowResult.builder().channels(((WhereNowPayload)((Envelope)input.body()).getPayload()).getChannels()).build();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 68 */     return PNOperationType.PNWhereNowOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 73 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/presence/WhereNow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */