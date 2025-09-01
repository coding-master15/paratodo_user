/*    */ package com.pubnub.api.endpoints;
/*    */ 
/*    */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.PNTimeResult;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*    */ 
/*    */ 
/*    */ public class Time
/*    */   extends Endpoint<List<Long>, PNTimeResult>
/*    */ {
/*    */   public Time(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/* 20 */     super(pubnub, telemetryManager, retrofit);
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannels() {
/* 25 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannelGroups() {
/* 30 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void validateParams() throws PubNubException {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected Call<List<Long>> doWork(Map<String, String> params) {
/* 41 */     return getRetrofit().getTimeService().fetchTime(params);
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNTimeResult createResponse(Response<List<Long>> input) throws PubNubException {
/* 46 */     PNTimeResult.PNTimeResultBuilder pNTimeResultBuilder = PNTimeResult.builder();
/*    */     
/* 48 */     if (input.body() == null || ((List)input.body()).size() == 0) {
/* 49 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*    */     }
/*    */     
/* 52 */     pNTimeResultBuilder.timetoken(((List<Long>)input.body()).get(0));
/* 53 */     return pNTimeResultBuilder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 58 */     return PNOperationType.PNTimeOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 63 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/Time.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */