/*     */ package com.pubnub.api.endpoints.pubsub;
/*     */ 
/*     */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.models.server.SubscribeEnvelope;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Subscribe
/*     */   extends Endpoint<SubscribeEnvelope, SubscribeEnvelope>
/*     */ {
/*     */   private List<String> channels;
/*     */   private List<String> channelGroups;
/*     */   private Long timetoken;
/*     */   private String filterExpression;
/*     */   private String region;
/*     */   
/*     */   public Subscribe(PubNub pubnub, RetrofitManager retrofit) {
/*  56 */     super(pubnub, null, retrofit);
/*  57 */     this.channels = new ArrayList<>();
/*  58 */     this.channelGroups = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  63 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  68 */     return this.channelGroups;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  73 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/*  74 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*     */     }
/*  76 */     if (this.channels.size() == 0 && this.channelGroups.size() == 0) {
/*  77 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_AND_GROUP_MISSING).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Call<SubscribeEnvelope> doWork(Map<String, String> params) throws PubNubException {
/*     */     String str;
/*  85 */     if (this.channelGroups.size() > 0) {
/*  86 */       params.put("channel-group", PubNubUtil.joinString(this.channelGroups, ","));
/*     */     }
/*     */     
/*  89 */     if (this.filterExpression != null && this.filterExpression.length() > 0) {
/*  90 */       params.put("filter-expr", PubNubUtil.urlEncode(this.filterExpression));
/*     */     }
/*     */     
/*  93 */     if (this.timetoken != null) {
/*  94 */       params.put("tt", this.timetoken.toString());
/*     */     }
/*     */     
/*  97 */     if (this.region != null) {
/*  98 */       params.put("tr", this.region);
/*     */     }
/*     */     
/* 101 */     if (this.channels.size() > 0) {
/* 102 */       str = PubNubUtil.joinString(this.channels, ",");
/*     */     } else {
/* 104 */       str = ",";
/*     */     } 
/*     */     
/* 107 */     params.put("heartbeat", String.valueOf(getPubnub().getConfiguration().getPresenceTimeout()));
/*     */     
/* 109 */     return getRetrofit().getSubscribeService()
/* 110 */       .subscribe(getPubnub().getConfiguration().getSubscribeKey(), str, params);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SubscribeEnvelope createResponse(Response<SubscribeEnvelope> input) throws PubNubException {
/* 116 */     if (input.body() == null) {
/* 117 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*     */     }
/*     */     
/* 120 */     return (SubscribeEnvelope)input.body();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/* 125 */     return PNOperationType.PNSubscribeOperation;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Subscribe channels(List<String> channels) {
/* 141 */     this.channels = channels;
/* 142 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Subscribe channelGroups(List<String> channelGroups) {
/* 153 */     this.channelGroups = channelGroups;
/* 154 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Subscribe timetoken(Long timetoken) {
/* 165 */     this.timetoken = timetoken;
/* 166 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Subscribe filterExpression(String filterExpression) {
/* 177 */     this.filterExpression = filterExpression;
/* 178 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Subscribe region(String region) {
/* 189 */     this.region = region;
/* 190 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/pubsub/Subscribe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */