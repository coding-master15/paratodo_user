/*     */ package com.pubnub.api.endpoints.push;
/*     */ 
/*     */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.enums.PNPushType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.push.PNPushRemoveChannelResult;

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
/*     */ public class RemoveChannelsFromPush
/*     */   extends Endpoint<List<Object>, PNPushRemoveChannelResult>
/*     */ {
/*     */   private PNPushType pushType;
/*     */   private List<String> channels;
/*     */   private String deviceId;
/*     */   
/*     */   public RemoveChannelsFromPush(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/*  31 */     super(pubnub, telemetryManager, retrofit);
/*     */     
/*  33 */     this.channels = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  38 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  48 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/*  49 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*     */     }
/*  51 */     if (this.pushType == null) {
/*  52 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PUSH_TYPE_MISSING).build();
/*     */     }
/*  54 */     if (this.deviceId == null || this.deviceId.isEmpty()) {
/*  55 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_DEVICE_ID_MISSING).build();
/*     */     }
/*  57 */     if (this.channels.size() == 0) {
/*  58 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_MISSING).build();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Call<List<Object>> doWork(Map<String, String> baseParams) throws PubNubException {
/*  64 */     baseParams.put("type", this.pushType.name().toLowerCase());
/*     */     
/*  66 */     if (this.channels.size() != 0) {
/*  67 */       baseParams.put("remove", PubNubUtil.joinString(this.channels, ","));
/*     */     }
/*     */     
/*  70 */     return getRetrofit().getPushService().modifyChannelsForDevice(getPubnub().getConfiguration().getSubscribeKey(), this.deviceId, baseParams);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected PNPushRemoveChannelResult createResponse(Response<List<Object>> input) throws PubNubException {
/*  76 */     if (input.body() == null) {
/*  77 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*     */     }
/*     */     
/*  80 */     return PNPushRemoveChannelResult.builder().build();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/*  85 */     return PNOperationType.PNRemovePushNotificationsFromChannelsOperation;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public RemoveChannelsFromPush pushType(PNPushType pushType) {
/*  95 */     this.pushType = pushType;
/*  96 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RemoveChannelsFromPush channels(List<String> channels) {
/* 101 */     this.channels = channels;
/* 102 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RemoveChannelsFromPush deviceId(String deviceId) {
/* 107 */     this.deviceId = deviceId;
/* 108 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/push/RemoveChannelsFromPush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */