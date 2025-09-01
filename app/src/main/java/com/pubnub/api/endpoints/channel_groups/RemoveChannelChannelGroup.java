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
import com.pubnub.api.models.consumer.channel_group.PNChannelGroupsRemoveChannelResult;
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
/*    */ public class RemoveChannelChannelGroup
/*    */   extends Endpoint<Envelope, PNChannelGroupsRemoveChannelResult>
/*    */ {
/*    */   private String channelGroup;
/*    */   private List<String> channels;
/*    */   
/*    */   public RemoveChannelChannelGroup(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
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
/* 58 */       params.put("remove", PubNubUtil.joinString(this.channels, ","));
/*    */     }
/*    */     
/* 61 */     return getRetrofit().getChannelGroupService()
/* 62 */       .removeChannel(getPubnub().getConfiguration().getSubscribeKey(), this.channelGroup, params);
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNChannelGroupsRemoveChannelResult createResponse(Response<Envelope> input) throws PubNubException {
/* 67 */     if (input.body() == null) {
/* 68 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*    */     }
/* 70 */     return PNChannelGroupsRemoveChannelResult.builder().build();
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 75 */     return PNOperationType.PNRemoveChannelsFromGroupOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 80 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoveChannelChannelGroup channelGroup(String channelGroup) {
/* 85 */     this.channelGroup = channelGroup;
/* 86 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoveChannelChannelGroup channels(List<String> channels) {
/* 91 */     this.channels = channels;
/* 92 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/channel_groups/RemoveChannelChannelGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */