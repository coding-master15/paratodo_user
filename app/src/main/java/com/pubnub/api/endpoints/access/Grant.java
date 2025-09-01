/*     */ package com.pubnub.api.endpoints.access;
/*     */ 
/*     */

import com.google.gson.JsonElement;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.MapperManager;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.access_manager.PNAccessManagerGrantResult;
import com.pubnub.api.models.consumer.access_manager.PNAccessManagerKeyData;
import com.pubnub.api.models.consumer.access_manager.PNAccessManagerKeysData;
import com.pubnub.api.models.server.Envelope;
import com.pubnub.api.models.server.access_manager.AccessManagerGrantPayload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
/*     */ public class Grant
/*     */   extends Endpoint<Envelope<AccessManagerGrantPayload>, PNAccessManagerGrantResult>
/*     */ {
/*     */   private boolean read;
/*     */   private boolean write;
/*     */   private boolean manage;
/*     */   private Integer ttl;
/*     */   private List<String> authKeys;
/*     */   private List<String> channels;
/*     */   private List<String> channelGroups;
/*     */   
/*     */   public Grant(PubNub pubnub, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/*  45 */     super(pubnub, telemetryManager, retrofit);
/*  46 */     this.authKeys = new ArrayList<>();
/*  47 */     this.channels = new ArrayList<>();
/*  48 */     this.channelGroups = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  53 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  58 */     return this.channelGroups;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  63 */     if (getPubnub().getConfiguration().getSecretKey() == null || getPubnub().getConfiguration().getSecretKey().isEmpty()) {
/*  64 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SECRET_KEY_MISSING).build();
/*     */     }
/*  66 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/*  67 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*     */     }
/*  69 */     if (getPubnub().getConfiguration().getPublishKey() == null || getPubnub().getConfiguration().getPublishKey().isEmpty()) {
/*  70 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PUBLISH_KEY_MISSING).build();
/*     */     }
/*  72 */     if (this.channels.size() == 0 && this.channelGroups.size() == 0) {
/*  73 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_AND_GROUP_MISSING).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Call<Envelope<AccessManagerGrantPayload>> doWork(Map<String, String> queryParams) throws PubNubException {
/*  80 */     if (this.channels.size() > 0) {
/*  81 */       queryParams.put("channel", PubNubUtil.joinString(this.channels, ","));
/*     */     }
/*     */     
/*  84 */     if (this.channelGroups.size() > 0) {
/*  85 */       queryParams.put("channel-group", PubNubUtil.joinString(this.channelGroups, ","));
/*     */     }
/*     */     
/*  88 */     if (this.authKeys.size() > 0) {
/*  89 */       queryParams.put("auth", PubNubUtil.joinString(this.authKeys, ","));
/*     */     }
/*     */     
/*  92 */     if (this.ttl != null && this.ttl.intValue() >= -1) {
/*  93 */       queryParams.put("ttl", String.valueOf(this.ttl));
/*     */     }
/*     */     
/*  96 */     queryParams.put("r", this.read ? "1" : "0");
/*  97 */     queryParams.put("w", this.write ? "1" : "0");
/*  98 */     queryParams.put("m", this.manage ? "1" : "0");
/*     */     
/* 100 */     return getRetrofit().getAccessManagerService().grant(getPubnub().getConfiguration().getSubscribeKey(), queryParams);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNAccessManagerGrantResult createResponse(Response<Envelope<AccessManagerGrantPayload>> input) throws PubNubException {
/* 105 */     MapperManager mapperManager = getPubnub().getMapper();
/* 106 */     PNAccessManagerGrantResult.PNAccessManagerGrantResultBuilder pNAccessManagerGrantResultBuilder = PNAccessManagerGrantResult.builder();
/*     */     
/* 108 */     if (input.body() == null || ((Envelope)input.body()).getPayload() == null) {
/* 109 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
/*     */     }
/*     */     
/* 112 */     AccessManagerGrantPayload accessManagerGrantPayload = (AccessManagerGrantPayload)((Envelope)input.body()).getPayload();
/* 113 */     HashMap<String, Map<String, PNAccessManagerKeyData>> hashMap1 = new HashMap<>();
/* 114 */     HashMap<String, Map<String, PNAccessManagerKeyData>> hashMap2 = new HashMap<>();
/*     */ 
/*     */     
/* 117 */     if (accessManagerGrantPayload.getChannel() != null) {
/* 118 */       hashMap1.put(accessManagerGrantPayload.getChannel(), accessManagerGrantPayload.getAuthKeys());
/*     */     }
/*     */     
/* 121 */     if (this.channelGroups != null) {
/* 122 */       if (this.channelGroups.size() == 1) {
/* 123 */         hashMap2.put(mapperManager.elementToString(accessManagerGrantPayload.getChannelGroups()), accessManagerGrantPayload.getAuthKeys());
/* 124 */       } else if (this.channelGroups.size() > 1) {
/* 125 */         for (Iterator<Map.Entry<String, JsonElement>> iterator = mapperManager.getObjectIterator(accessManagerGrantPayload.getChannelGroups()); iterator.hasNext(); ) {
/* 126 */           Map.Entry<String, JsonElement> entry = iterator.next();
/* 127 */           hashMap2.put(entry.getKey(), createKeyMap((JsonElement)entry.getValue()));
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 132 */     if (accessManagerGrantPayload.getChannels() != null) {
/* 133 */       for (String str : accessManagerGrantPayload.getChannels().keySet()) {
/* 134 */         hashMap1.put(str, ((PNAccessManagerKeysData)accessManagerGrantPayload.getChannels().get(str)).getAuthKeys());
/*     */       }
/*     */     }
/*     */     
/* 138 */     return pNAccessManagerGrantResultBuilder
/* 139 */       .subscribeKey(accessManagerGrantPayload.getSubscribeKey())
/* 140 */       .level(accessManagerGrantPayload.getLevel())
/* 141 */       .ttl(accessManagerGrantPayload.getTtl())
/* 142 */       .channels(hashMap1)
/* 143 */       .channelGroups(hashMap2)
/* 144 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/* 149 */     return PNOperationType.PNAccessManagerGrant;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/* 154 */     return false;
/*     */   }
/*     */   
/*     */   private Map<String, PNAccessManagerKeyData> createKeyMap(JsonElement input) {
/* 158 */     HashMap<Object, Object> hashMap = new HashMap<>();
/* 159 */     MapperManager mapperManager = getPubnub().getMapper();
/*     */     
/* 161 */     for (Iterator<Map.Entry<String, JsonElement>> iterator = mapperManager.getObjectIterator(input, "auths"); iterator.hasNext(); ) {
/* 162 */       Map.Entry entry = iterator.next();
/*     */ 
/*     */ 
/*     */       
/* 166 */       PNAccessManagerKeyData pNAccessManagerKeyData = (new PNAccessManagerKeyData()).setManageEnabled(mapperManager.getAsBoolean((JsonElement)entry.getValue(), "m")).setWriteEnabled(mapperManager.getAsBoolean((JsonElement)entry.getValue(), "w")).setReadEnabled(mapperManager.getAsBoolean((JsonElement)entry.getValue(), "r"));
/*     */       
/* 168 */       hashMap.put(entry.getKey(), pNAccessManagerKeyData);
/*     */     } 
/*     */     
/* 171 */     return (Map)hashMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public Grant read(boolean read) {
/* 176 */     this.read = read;
/* 177 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Grant write(boolean write) {
/* 182 */     this.write = write;
/* 183 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Grant manage(boolean manage) {
/* 188 */     this.manage = manage;
/* 189 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Grant ttl(Integer ttl) {
/* 194 */     this.ttl = ttl;
/* 195 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Grant authKeys(List<String> authKeys) {
/* 200 */     this.authKeys = authKeys;
/* 201 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Grant channels(List<String> channels) {
/* 206 */     this.channels = channels;
/* 207 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Grant channelGroups(List<String> channelGroups) {
/* 212 */     this.channelGroups = channelGroups;
/* 213 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/access/Grant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */