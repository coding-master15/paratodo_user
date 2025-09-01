/*    */ package com.pubnub.api.endpoints.presence;
/*    */ 
/*    */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.server.Envelope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*    */ 
/*    */ 
/*    */ public class Leave
/*    */   extends Endpoint<Envelope, Boolean>
/*    */ {
/*    */   private List<String> channels;
/*    */   private List<String> channelGroups;
/*    */   
/*    */   public Leave(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/* 26 */     super(pubnub, telemetryManager, retrofit);
/* 27 */     this.channels = new ArrayList<>();
/* 28 */     this.channelGroups = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void validateParams() throws PubNubException {
/* 33 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/* 34 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*    */     }
/* 36 */     if (this.channels.size() == 0 && this.channelGroups.size() == 0) {
/* 37 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_AND_GROUP_MISSING).build();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Call<Envelope> doWork(Map<String, String> params) {
/*    */     String str;
/* 45 */     if (this.channelGroups.size() > 0) {
/* 46 */       params.put("channel-group", PubNubUtil.joinString(this.channelGroups, ","));
/*    */     }
/*    */     
/* 49 */     if (this.channels.size() > 0) {
/* 50 */       str = PubNubUtil.joinString(this.channels, ",");
/*    */     } else {
/* 52 */       str = ",";
/*    */     } 
/*    */     
/* 55 */     return getRetrofit().getPresenceService().leave(getPubnub().getConfiguration().getSubscribeKey(), str, params);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Boolean createResponse(Response<Envelope> input) throws PubNubException {
/* 60 */     return Boolean.valueOf(true);
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 65 */     return PNOperationType.PNUnsubscribeOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 70 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannels() {
/* 75 */     return this.channels;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannelGroups() {
/* 80 */     return this.channelGroups;
/*    */   }
/*    */ 
/*    */   
/*    */   public Leave channels(List<String> channels) {
/* 85 */     this.channels = channels;
/* 86 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public Leave channelGroups(List<String> channelGroups) {
/* 91 */     this.channelGroups = channelGroups;
/* 92 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/presence/Leave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */