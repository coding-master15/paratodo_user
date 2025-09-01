/*     */ package com.pubnub.api.endpoints.access;
/*     */ 
/*     */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.access_manager.PNAccessManagerAuditResult;
import com.pubnub.api.models.server.Envelope;
import com.pubnub.api.models.server.access_manager.AccessManagerAuditPayload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Audit
/*     */   extends Endpoint<Envelope<AccessManagerAuditPayload>, PNAccessManagerAuditResult>
/*     */ {
/*     */   private List<String> authKeys;
/*     */   private String channel;
/*     */   private String channelGroup;
/*     */   
/*     */   public Audit(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/*  33 */     super(pubnub, telemetryManager, retrofit);
/*  34 */     this.authKeys = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  39 */     return Collections.singletonList(this.channel);
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  44 */     return Collections.singletonList(this.channelGroup);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  49 */     if (this.authKeys.size() == 0) {
/*  50 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_AUTH_KEYS_MISSING).build();
/*     */     }
/*  52 */     if (getPubnub().getConfiguration().getSecretKey() == null || getPubnub().getConfiguration().getSecretKey().isEmpty()) {
/*  53 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SECRET_KEY_MISSING).build();
/*     */     }
/*  55 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/*  56 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*     */     }
/*  58 */     if (getPubnub().getConfiguration().getPublishKey() == null || getPubnub().getConfiguration().getPublishKey().isEmpty()) {
/*  59 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PUBLISH_KEY_MISSING).build();
/*     */     }
/*  61 */     if (this.channel == null && this.channelGroup == null) {
/*  62 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_AND_GROUP_MISSING).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Call<Envelope<AccessManagerAuditPayload>> doWork(Map<String, String> queryParams) throws PubNubException {
/*  69 */     if (this.channel != null) {
/*  70 */       queryParams.put("channel", this.channel);
/*     */     }
/*     */     
/*  73 */     if (this.channelGroup != null) {
/*  74 */       queryParams.put("channel-group", this.channelGroup);
/*     */     }
/*     */     
/*  77 */     if (this.authKeys.size() > 0) {
/*  78 */       queryParams.put("auth", PubNubUtil.joinString(this.authKeys, ","));
/*     */     }
/*     */     
/*  81 */     return getRetrofit().getAccessManagerService().audit(getPubnub().getConfiguration().getSubscribeKey(), queryParams);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNAccessManagerAuditResult createResponse(Response<Envelope<AccessManagerAuditPayload>> input) throws PubNubException {
/*  86 */     PNAccessManagerAuditResult.PNAccessManagerAuditResultBuilder pNAccessManagerAuditResultBuilder = PNAccessManagerAuditResult.builder();
/*     */     
/*  88 */     if (input.body() == null || ((Envelope)input.body()).getPayload() == null) {
/*  89 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*     */     }
/*     */     
/*  92 */     AccessManagerAuditPayload accessManagerAuditPayload = (AccessManagerAuditPayload)((Envelope)input.body()).getPayload();
/*  93 */     pNAccessManagerAuditResultBuilder
/*  94 */       .authKeys(accessManagerAuditPayload.getAuthKeys())
/*  95 */       .channel(accessManagerAuditPayload.getChannel())
/*  96 */       .channelGroup(accessManagerAuditPayload.getChannelGroup())
/*  97 */       .level(accessManagerAuditPayload.getLevel())
/*  98 */       .subscribeKey(accessManagerAuditPayload.getSubscribeKey());
/*     */ 
/*     */     
/* 101 */     return pNAccessManagerAuditResultBuilder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/* 106 */     return PNOperationType.PNAccessManagerAudit;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Audit authKeys(List<String> authKeys) {
/* 116 */     this.authKeys = authKeys;
/* 117 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Audit channel(String channel) {
/* 122 */     this.channel = channel;
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Audit channelGroup(String channelGroup) {
/* 128 */     this.channelGroup = channelGroup;
/* 129 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/access/Audit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */