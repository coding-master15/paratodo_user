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
import com.pubnub.api.models.consumer.channel_group.PNChannelGroupsListAllResult;
import com.pubnub.api.models.server.Envelope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ListAllChannelGroup
/*    */   extends Endpoint<Envelope<Object>, PNChannelGroupsListAllResult>
/*    */ {
/*    */   public ListAllChannelGroup(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/* 24 */     super(pubnub, telemetryManager, retrofit);
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannels() {
/* 29 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<String> getAffectedChannelGroups() {
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void validateParams() throws PubNubException {}
/*    */ 
/*    */   
/*    */   protected Call<Envelope<Object>> doWork(Map<String, String> params) {
/* 43 */     return getRetrofit().getChannelGroupService()
/* 44 */       .listAllChannelGroup(getPubnub().getConfiguration().getSubscribeKey(), params);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected PNChannelGroupsListAllResult createResponse(Response<Envelope<Object>> input) throws PubNubException {
/* 51 */     if (input.body() == null || ((Envelope)input.body()).getPayload() == null) {
/* 52 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*    */     }
/*    */     
/* 55 */     Map map = (Map)((Envelope)input.body()).getPayload();
/* 56 */     ArrayList arrayList = (ArrayList)map.get("groups");
/*    */     
/* 58 */     return PNChannelGroupsListAllResult.builder()
/* 59 */       .groups(arrayList)
/* 60 */       .build();
/*    */   }
/*    */ 
/*    */   
/*    */   protected PNOperationType getOperationType() {
/* 65 */     return PNOperationType.PNChannelGroupsOperation;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isAuthRequired() {
/* 70 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/channel_groups/ListAllChannelGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */