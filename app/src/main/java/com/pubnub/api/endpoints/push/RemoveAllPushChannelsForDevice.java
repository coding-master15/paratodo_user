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
import com.pubnub.api.models.consumer.push.PNPushRemoveAllChannelsResult;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoveAllPushChannelsForDevice
/*    */   extends Endpoint<List<Object>, PNPushRemoveAllChannelsResult>
/*    */ {
/*    */   private PNPushType pushType;
/*    */   private String deviceId;
/*    */   
/*    */   public RemoveAllPushChannelsForDevice(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
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
/* 45 */     if (this.pushType == null) {
/* 46 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PUSH_TYPE_MISSING).build();
/*    */     }
/* 48 */     if (this.deviceId == null || this.deviceId.isEmpty()) {
/* 49 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_DEVICE_ID_MISSING).build();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Call<List<Object>> doWork(Map<String, String> params) throws PubNubException {
/* 56 */     params.put("type", this.pushType.name().toLowerCase());
/*    */     
/* 58 */     return getRetrofit().getPushService().removeAllChannelsForDevice(getPubnub().getConfiguration().getSubscribeKey(), this.deviceId, params);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected PNPushRemoveAllChannelsResult createResponse(Response<List<Object>> input) throws PubNubException {
/* 64 */     if (input.body() == null) {
/* 65 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*    */     }
/*    */     
/* 68 */     return PNPushRemoveAllChannelsResult.builder().build();
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 73 */     return PNOperationType.PNRemoveAllPushNotificationsOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 78 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoveAllPushChannelsForDevice pushType(PNPushType pushType) {
/* 83 */     this.pushType = pushType;
/* 84 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoveAllPushChannelsForDevice deviceId(String deviceId) {
/* 89 */     this.deviceId = deviceId;
/* 90 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/push/RemoveAllPushChannelsForDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */