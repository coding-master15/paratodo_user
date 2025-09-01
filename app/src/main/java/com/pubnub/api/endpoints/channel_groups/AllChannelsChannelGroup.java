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
import com.pubnub.api.models.consumer.channel_group.PNChannelGroupsAllChannelsResult;
import com.pubnub.api.models.server.Envelope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AllChannelsChannelGroup
/*    */   extends Endpoint<Envelope<Object>, PNChannelGroupsAllChannelsResult>
/*    */ {
/*    */   private String channelGroup;
/*    */   
/*    */   public AllChannelsChannelGroup(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
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
/* 41 */     if (this.channelGroup == null || this.channelGroup.isEmpty()) {
/* 42 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_GROUP_MISSING).build();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected Call<Envelope<Object>> doWork(Map<String, String> params) {
/* 48 */     return getRetrofit().getChannelGroupService()
/* 49 */       .allChannelsChannelGroup(getPubnub().getConfiguration().getSubscribeKey(), this.channelGroup, params);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected PNChannelGroupsAllChannelsResult createResponse(Response<Envelope<Object>> input) throws PubNubException {
/* 56 */     if (input.body() == null || ((Envelope)input.body()).getPayload() == null) {
/* 57 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*    */     }
/*    */     
/* 60 */     Map map = (Map)((Envelope)input.body()).getPayload();
/* 61 */     ArrayList arrayList = (ArrayList)map.get("channels");
/*    */     
/* 63 */     return PNChannelGroupsAllChannelsResult.builder()
/* 64 */       .channels(arrayList)
/* 65 */       .build();
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 70 */     return PNOperationType.PNChannelsForGroupOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 75 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public AllChannelsChannelGroup channelGroup(String channelGroup) {
/* 80 */     this.channelGroup = channelGroup;
/* 81 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/channel_groups/AllChannelsChannelGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */