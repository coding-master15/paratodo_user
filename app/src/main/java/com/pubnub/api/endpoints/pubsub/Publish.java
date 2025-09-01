/*     */ package com.pubnub.api.endpoints.pubsub;
/*     */ 
/*     */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.MapperManager;
import com.pubnub.api.managers.PublishSequenceManager;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.vendor.Crypto;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Publish
/*     */   extends Endpoint<List<Object>, PNPublishResult>
/*     */ {
/*     */   private Object message;
/*     */   private String channel;
/*     */   private Boolean shouldStore;
/*     */   private Boolean usePOST;
/*     */   private Object meta;
/*     */   private Boolean replicate;
/*     */   private Integer ttl;
/*     */   private PublishSequenceManager publishSequenceManager;
/*     */   
/*     */   public Publish(PubNub pubnub, PublishSequenceManager providedPublishSequenceManager, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/*  43 */     super(pubnub, telemetryManager, retrofit);
/*     */     
/*  45 */     this.publishSequenceManager = providedPublishSequenceManager;
/*  46 */     this.replicate = Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  51 */     return Collections.singletonList(this.channel);
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  56 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  61 */     if (this.message == null) {
/*  62 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_MESSAGE_MISSING).build();
/*     */     }
/*  64 */     if (this.channel == null || this.channel.isEmpty()) {
/*  65 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_MISSING).build();
/*     */     }
/*  67 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/*  68 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*     */     }
/*  70 */     if (getPubnub().getConfiguration().getPublishKey() == null || getPubnub().getConfiguration().getPublishKey().isEmpty()) {
/*  71 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PUBLISH_KEY_MISSING).build();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Call<List<Object>> doWork(Map<String, String> params) throws PubNubException {
/*  77 */     MapperManager mapperManager = getPubnub().getMapper();
/*     */     
/*  79 */     String str = mapperManager.toJson(this.message);
/*     */     
/*  81 */     if (this.meta != null) {
/*  82 */       String str1 = mapperManager.toJson(this.meta);
/*  83 */       str1 = PubNubUtil.urlEncode(str1);
/*  84 */       params.put("meta", str1);
/*     */     } 
/*     */     
/*  87 */     if (this.shouldStore != null) {
/*  88 */       if (this.shouldStore.booleanValue()) {
/*  89 */         params.put("store", "1");
/*     */       } else {
/*  91 */         params.put("store", "0");
/*     */       } 
/*     */     }
/*     */     
/*  95 */     if (this.ttl != null) {
/*  96 */       params.put("ttl", String.valueOf(this.ttl));
/*     */     }
/*     */     
/*  99 */     params.put("seqn", String.valueOf(this.publishSequenceManager.getNextSequence()));
/*     */     
/* 101 */     if (!this.replicate.booleanValue()) {
/* 102 */       params.put("norep", "true");
/*     */     }
/*     */     
/* 105 */     if (getPubnub().getConfiguration().getCipherKey() != null) {
/* 106 */       Crypto crypto = new Crypto(getPubnub().getConfiguration().getCipherKey());
/* 107 */       str = crypto.encrypt(str).replace("\n", "");
/*     */     } 
/*     */     
/* 110 */     if (this.usePOST != null && this.usePOST.booleanValue()) {
/*     */       Object object;
/*     */       
/* 113 */       if (getPubnub().getConfiguration().getCipherKey() != null) {
/* 114 */         object = str;
/*     */       } else {
/* 116 */         object = this.message;
/*     */       } 
/*     */       
/* 119 */       return getRetrofit().getPublishService().publishWithPost(getPubnub().getConfiguration().getPublishKey(), 
/* 120 */           getPubnub().getConfiguration().getSubscribeKey(), this.channel, object, params);
/*     */     } 
/*     */ 
/*     */     
/* 124 */     if (getPubnub().getConfiguration().getCipherKey() != null) {
/* 125 */       str = "\"".concat(str).concat("\"");
/*     */     }
/*     */     
/* 128 */     str = PubNubUtil.urlEncode(str);
/*     */     
/* 130 */     return getRetrofit().getPublishService().publish(getPubnub().getConfiguration().getPublishKey(), 
/* 131 */         getPubnub().getConfiguration().getSubscribeKey(), this.channel, str, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PNPublishResult createResponse(Response<List<Object>> input) throws PubNubException {
/* 138 */     PNPublishResult.PNPublishResultBuilder pNPublishResultBuilder = PNPublishResult.builder();
/* 139 */     pNPublishResultBuilder.timetoken(Long.valueOf(((List)input.body()).get(2).toString()));
/*     */     
/* 141 */     return pNPublishResultBuilder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/* 146 */     return PNOperationType.PNPublishOperation;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/* 151 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Publish message(Object message) {
/* 156 */     this.message = message;
/* 157 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Publish channel(String channel) {
/* 162 */     this.channel = channel;
/* 163 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Publish shouldStore(Boolean shouldStore) {
/* 168 */     this.shouldStore = shouldStore;
/* 169 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Publish usePOST(Boolean usePOST) {
/* 174 */     this.usePOST = usePOST;
/* 175 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Publish meta(Object meta) {
/* 180 */     this.meta = meta;
/* 181 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Publish replicate(Boolean replicate) {
/* 186 */     this.replicate = replicate;
/* 187 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Publish ttl(Integer ttl) {
/* 192 */     this.ttl = ttl;
/* 193 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/pubsub/Publish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */