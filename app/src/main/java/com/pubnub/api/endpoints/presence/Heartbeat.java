/*     */ package com.pubnub.api.endpoints.presence;
/*     */ 
/*     */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.MapperManager;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.server.Envelope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Heartbeat
/*     */   extends Endpoint<Envelope, Boolean>
/*     */ {
/*     */   private Object state;
/*     */   private List<String> channels;
/*     */   private List<String> channelGroups;
/*     */   
/*     */   public Heartbeat(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/*  29 */     super(pubnub, telemetryManager, retrofit);
/*  30 */     this.channels = new ArrayList<>();
/*  31 */     this.channelGroups = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  36 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  41 */     return this.channelGroups;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  46 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/*  47 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*     */     }
/*  49 */     if (this.channels.size() == 0 && this.channelGroups.size() == 0) {
/*  50 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_AND_GROUP_MISSING).build();
/*     */     }
/*     */   }
/*     */   
/*     */   protected Call<Envelope> doWork(Map<String, String> params) throws PubNubException {
/*     */     String str;
/*  56 */     MapperManager mapperManager = getPubnub().getMapper();
/*     */     
/*  58 */     params.put("heartbeat", String.valueOf(getPubnub().getConfiguration().getPresenceTimeout()));
/*     */     
/*  60 */     if (this.channelGroups.size() > 0) {
/*  61 */       params.put("channel-group", PubNubUtil.joinString(this.channelGroups, ","));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  66 */     if (this.channels.size() > 0) {
/*  67 */       str = PubNubUtil.joinString(this.channels, ",");
/*     */     } else {
/*  69 */       str = ",";
/*     */     } 
/*     */     
/*  72 */     if (this.state != null) {
/*  73 */       String str1 = mapperManager.toJson(this.state);
/*  74 */       str1 = PubNubUtil.urlEncode(str1);
/*  75 */       params.put("state", str1);
/*     */     } 
/*     */ 
/*     */     
/*  79 */     return getRetrofit().getPresenceService().heartbeat(getPubnub().getConfiguration().getSubscribeKey(), str, params);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Boolean createResponse(Response<Envelope> input) throws PubNubException {
/*  84 */     return Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/*  89 */     return PNOperationType.PNHeartbeatOperation;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Heartbeat state(Object state) {
/*  99 */     this.state = state;
/* 100 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Heartbeat channels(List<String> channels) {
/* 105 */     this.channels = channels;
/* 106 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Heartbeat channelGroups(List<String> channelGroups) {
/* 111 */     this.channelGroups = channelGroups;
/* 112 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/presence/Heartbeat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */