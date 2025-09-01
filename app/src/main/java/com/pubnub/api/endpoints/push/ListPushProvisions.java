/*    */ package com.pubnub.api.endpoints.push;
/*    */ 
/*    */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.enums.PNPushType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.push.PNPushListProvisionsResult;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ListPushProvisions
/*    */   extends Endpoint<List<String>, PNPushListProvisionsResult>
/*    */ {
/*    */   private PNPushType pushType;
/*    */   private String deviceId;
/*    */   
/*    */   public ListPushProvisions(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/* 26 */     super(pubnub, telemetryManager, retrofit);
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannels() {
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannelGroups() {
/* 36 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void validateParams() throws PubNubException {
/* 41 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/* 42 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*    */     }
/* 44 */     if (this.pushType == null) {
/* 45 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PUSH_TYPE_MISSING).build();
/*    */     }
/* 47 */     if (this.deviceId == null || this.deviceId.isEmpty()) {
/* 48 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_DEVICE_ID_MISSING).build();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected Call<List<String>> doWork(Map<String, String> params) throws PubNubException {
/* 54 */     params.put("type", this.pushType.name().toLowerCase());
/* 55 */     return getRetrofit().getPushService().listChannelsForDevice(getPubnub().getConfiguration().getSubscribeKey(), this.deviceId, params);
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNPushListProvisionsResult createResponse(Response<List<String>> input) throws PubNubException {
/* 60 */     if (input.body() == null) {
/* 61 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*    */     }
/*    */     
/* 64 */     return PNPushListProvisionsResult.builder().channels((List)input.body()).build();
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 69 */     return PNOperationType.PNPushNotificationEnabledChannelsOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 74 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ListPushProvisions pushType(PNPushType pushType) {
/* 79 */     this.pushType = pushType;
/* 80 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public ListPushProvisions deviceId(String deviceId) {
/* 85 */     this.deviceId = deviceId;
/* 86 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/push/ListPushProvisions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */