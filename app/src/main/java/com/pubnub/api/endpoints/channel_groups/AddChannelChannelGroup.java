/*    */ package com.pubnub.api.endpoints.channel_groups;
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
import com.pubnub.api.models.consumer.channel_group.PNChannelGroupsAddChannelResult;
import com.pubnub.api.models.server.Envelope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AddChannelChannelGroup
/*    */   extends Endpoint<Envelope, PNChannelGroupsAddChannelResult>
/*    */ {
/*    */   private String channelGroup;
/*    */   private List<String> channels;
/*    */   
/*    */   public AddChannelChannelGroup(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/* 31 */     super(pubnub, telemetryManager, retrofit);
/* 32 */     this.channels = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannels() {
/* 37 */     return this.channels;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannelGroups() {
/* 42 */     return Collections.singletonList(this.channelGroup);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void validateParams() throws PubNubException {
/* 47 */     if (this.channelGroup == null || this.channelGroup.isEmpty()) {
/* 48 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_GROUP_MISSING).build();
/*    */     }
/* 50 */     if (this.channels.size() == 0) {
/* 51 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_MISSING).build();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected Call<Envelope> doWork(Map<String, String> params) {
/* 57 */     if (this.channels.size() > 0) {
/* 58 */       params.put("add", PubNubUtil.joinString(this.channels, ","));
/*    */     }
/*    */     
/* 61 */     return getRetrofit().getChannelGroupService().addChannelChannelGroup(getPubnub().getConfiguration().getSubscribeKey(), this.channelGroup, params);
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNChannelGroupsAddChannelResult createResponse(Response<Envelope> input) throws PubNubException {
/* 66 */     if (input.body() == null) {
/* 67 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*    */     }
/*    */     
/* 70 */     return PNChannelGroupsAddChannelResult.builder().build();
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 75 */     return PNOperationType.PNAddChannelsToGroupOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 80 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public AddChannelChannelGroup channelGroup(String channelGroup) {
/* 85 */     this.channelGroup = channelGroup;
/* 86 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public AddChannelChannelGroup channels(List<String> channels) {
/* 91 */     this.channels = channels;
/* 92 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/channel_groups/AddChannelChannelGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */