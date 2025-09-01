/*    */ package com.pubnub.api.endpoints.push;
/*    */ 
/*    */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.enums.PNPushType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.push.PNPushAddChannelResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AddChannelsToPush
/*    */   extends Endpoint<List<Object>, PNPushAddChannelResult>
/*    */ {
/*    */   private PNPushType pushType;
/*    */   private List<String> channels;
/*    */   private String deviceId;
/*    */   
/*    */   public AddChannelsToPush(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/* 32 */     super(pubnub, telemetryManager, retrofit);
/* 33 */     this.channels = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannels() {
/* 38 */     return this.channels;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannelGroups() {
/* 43 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void validateParams() throws PubNubException {
/* 48 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/* 49 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*    */     }
/* 51 */     if (this.pushType == null) {
/* 52 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PUSH_TYPE_MISSING).build();
/*    */     }
/* 54 */     if (this.deviceId == null || this.deviceId.isEmpty()) {
/* 55 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_DEVICE_ID_MISSING).build();
/*    */     }
/* 57 */     if (this.channels.size() == 0) {
/* 58 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_MISSING).build();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected Call<List<Object>> doWork(Map<String, String> baseParams) throws PubNubException {
/* 64 */     baseParams.put("type", this.pushType.name().toLowerCase());
/*    */     
/* 66 */     if (this.channels.size() != 0) {
/* 67 */       baseParams.put("add", PubNubUtil.joinString(this.channels, ","));
/*    */     }
/*    */     
/* 70 */     return getRetrofit().getPushService().modifyChannelsForDevice(getPubnub().getConfiguration().getSubscribeKey(), this.deviceId, baseParams);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected PNPushAddChannelResult createResponse(Response<List<Object>> input) throws PubNubException {
/* 76 */     if (input.body() == null) {
/* 77 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*    */     }
/*    */     
/* 80 */     return PNPushAddChannelResult.builder().build();
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 85 */     return PNOperationType.PNPushNotificationEnabledChannelsOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 90 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/push/AddChannelsToPush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */