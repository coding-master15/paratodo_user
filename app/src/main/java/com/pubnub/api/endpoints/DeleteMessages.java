/*     */ package com.pubnub.api.endpoints;
/*     */ 
/*     */

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.history.PNDeleteMessagesResult;
import com.pubnub.api.models.server.DeleteMessagesEnvelope;

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
/*     */ public class DeleteMessages
/*     */   extends Endpoint<DeleteMessagesEnvelope, PNDeleteMessagesResult>
/*     */ {
/*     */   private static final int SERVER_RESPONSE_SUCCESS = 200;
/*     */   private List<String> channels;
/*     */   private Long start;
/*     */   private Long end;
/*     */   
/*     */   public DeleteMessages(PubNub pubnubInstance, TelemetryManager telemetryManager, RetrofitManager retrofitInstance) {
/*  32 */     super(pubnubInstance, telemetryManager, retrofitInstance);
/*  33 */     this.channels = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  38 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  48 */     if (this.channels == null || this.channels.size() == 0) {
/*  49 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CHANNEL_MISSING).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Call<DeleteMessagesEnvelope> doWork(Map<String, String> params) throws PubNubException {
/*  56 */     if (this.start != null) {
/*  57 */       params.put("start", Long.toString(this.start.longValue()).toLowerCase());
/*     */     }
/*  59 */     if (this.end != null) {
/*  60 */       params.put("end", Long.toString(this.end.longValue()).toLowerCase());
/*     */     }
/*     */     
/*  63 */     return getRetrofit().getHistoryService().deleteMessages(getPubnub().getConfiguration().getSubscribeKey(), PubNubUtil.joinString(this.channels, ","), params);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNDeleteMessagesResult createResponse(Response<DeleteMessagesEnvelope> input) throws PubNubException {
/*  68 */     if (input.body() == null || ((DeleteMessagesEnvelope)input.body()).getStatus() == null || ((DeleteMessagesEnvelope)input.body()).getStatus().intValue() != 200) {
/*  69 */       String str = null;
/*     */       
/*  71 */       if (input.body() != null && ((DeleteMessagesEnvelope)input.body()).getErrorMessage() != null) {
/*  72 */         str = ((DeleteMessagesEnvelope)input.body()).getErrorMessage();
/*     */       } else {
/*  74 */         str = "n/a";
/*     */       } 
/*     */       
/*  77 */       throw PubNubException.builder()
/*  78 */         .pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR)
/*  79 */         .errormsg(str)
/*  80 */         .build();
/*     */     } 
/*     */     
/*  83 */     return PNDeleteMessagesResult.builder().build();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/*  88 */     return PNOperationType.PNDeleteMessagesOperation;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeleteMessages channels(List<String> channels) {
/*  98 */     this.channels = channels;
/*  99 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeleteMessages start(Long start) {
/* 104 */     this.start = start;
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeleteMessages end(Long end) {
/* 110 */     this.end = end;
/* 111 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/DeleteMessages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */