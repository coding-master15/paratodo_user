/*    */ package com.pubnub.api.endpoints.channel_groups;
/*    */ 
/*    */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.channel_group.PNChannelGroupsDeleteGroupResult;
import com.pubnub.api.models.server.Envelope;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DeleteChannelGroup
/*    */   extends Endpoint<Envelope, PNChannelGroupsDeleteGroupResult>
/*    */ {
/*    */   private String channelGroup;
/*    */   
/*    */   public DeleteChannelGroup(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
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
/* 36 */     return Collections.singletonList(this.channelGroup);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void validateParams() throws PubNubException {
/* 41 */     if (this.channelGroup == null || this.channelGroup.isEmpty()) {
/* 42 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_GROUP_MISSING).build();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected Call<Envelope> doWork(Map<String, String> params) {
/* 48 */     return getRetrofit().getChannelGroupService()
/* 49 */       .deleteChannelGroup(getPubnub().getConfiguration().getSubscribeKey(), this.channelGroup, params);
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNChannelGroupsDeleteGroupResult createResponse(Response<Envelope> input) throws PubNubException {
/* 54 */     if (input.body() == null) {
/* 55 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*    */     }
/* 57 */     return PNChannelGroupsDeleteGroupResult.builder().build();
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 62 */     return PNOperationType.PNRemoveGroupOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public DeleteChannelGroup channelGroup(String channelGroup) {
/* 72 */     this.channelGroup = channelGroup;
/* 73 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/channel_groups/DeleteChannelGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */