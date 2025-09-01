/*     */ package com.pubnub.api.endpoints;
/*     */ 
/*     */

import com.google.gson.JsonElement;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.managers.MapperManager;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.PNErrorData;
import com.pubnub.api.models.consumer.PNStatus;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Endpoint<Input, Output>
/*     */ {
/*     */   private static final int SERVER_RESPONSE_SUCCESS = 200;
/*     */   private static final int SERVER_RESPONSE_FORBIDDEN = 403;
/*     */   private static final int SERVER_RESPONSE_BAD_REQUEST = 400;
/*     */   private PubNub pubnub;
/*     */   private RetrofitManager retrofit;
/*     */   private TelemetryManager telemetryManager;
/*     */   private PNCallback<Output> cachedCallback;
/*     */   private Call<Input> call;
/*     */   private boolean silenceFailures;
/*     */   private MapperManager mapper;
/*     */   
/*     */   public Endpoint(PubNub pubnubInstance, TelemetryManager telemetry, RetrofitManager retrofitInstance) {
/*  49 */     this.pubnub = pubnubInstance;
/*  50 */     this.retrofit = retrofitInstance;
/*  51 */     this.mapper = this.pubnub.getMapper();
/*  52 */     this.telemetryManager = telemetry;
/*     */   }
/*     */   
/*     */   public Output sync() throws PubNubException {
/*     */     Response<Input> response;
/*  57 */     validateParams();
/*     */     
/*  59 */     this.call = doWork(createBaseParams());
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  64 */       response = this.call.execute();
/*  65 */     } catch (IOException iOException) {
/*  66 */       throw PubNubException.builder()
/*  67 */         .pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR)
/*  68 */         .errormsg(iOException.toString())
/*  69 */         .affectedCall(this.call)
/*  70 */         .build();
/*     */     } 
/*     */     
/*  73 */     if (!response.isSuccessful() || response.code() != 200) {
/*     */       String str;
/*     */       
/*     */       JsonElement object;
/*     */       try {
/*  78 */         str = response.errorBody().string();
/*  79 */       } catch (IOException iOException) {
/*  80 */         str = "N/A";
/*     */       } 
/*     */       
/*     */       try {
/*  84 */         object = this.mapper.fromJson(str, JsonElement.class);
/*  85 */       } catch (PubNubException pubNubException) {
/*  86 */         object = null;
/*     */       } 
/*     */       
/*  89 */       throw PubNubException.builder()
/*  90 */         .pubnubError(PubNubErrorBuilder.PNERROBJ_HTTP_ERROR)
/*  91 */         .errormsg(str)
/*  92 */         .jso(object)
/*  93 */         .statusCode(response.code())
/*  94 */         .affectedCall(this.call)
/*  95 */         .build();
/*     */     } 
/*     */     
/*  98 */     storeRequestLatency(response, getOperationType());
/*  99 */     return createResponse(response);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void async(final PNCallback<Output> callback) {
/* 105 */     this.cachedCallback = callback;
/*     */     
/*     */     try {
/* 108 */       validateParams();
/* 109 */       this.call = doWork(createBaseParams());
/* 110 */     } catch (PubNubException pubNubException) {
/* 111 */       callback.onResponse(null, createStatusResponse(PNStatusCategory.PNBadRequestCategory, null, (Exception)pubNubException, null, null));
/*     */       
/*     */       return;
/*     */     } 
/* 115 */     this.call.enqueue(new Callback<Input>()
/*     */         {
/*     */           public void onResponse(Call<Input> performedCall, Response<Input> response)
/*     */           {
/*     */             Output object;
/*     */             
/* 121 */             if (!response.isSuccessful() || response.code() != 200) {
/*     */               String str;
/*     */ 
/*     */               
/* 125 */               JsonElement jsonElement1, jsonElement2 = null;
/* 126 */               ArrayList<String> arrayList1 = new ArrayList();
/* 127 */               ArrayList<String> arrayList2 = new ArrayList();
/*     */               
/*     */               try {
/* 130 */                 str = response.errorBody().string();
/* 131 */               } catch (IOException iOException) {
/* 132 */                 str = "N/A";
/*     */               } 
/*     */               
/*     */               try {
/* 136 */                 jsonElement1 = (JsonElement)Endpoint.this.mapper.fromJson(str, JsonElement.class);
/* 137 */               } catch (PubNubException pubNubException1) {
/* 138 */                 jsonElement1 = null;
/*     */               } 
/*     */               
/* 141 */               if (jsonElement1 != null && Endpoint.this.mapper.isJsonObject(jsonElement1) && Endpoint.this.mapper.hasField(jsonElement1, "payload")) {
/* 142 */                 jsonElement2 = Endpoint.this.mapper.getField(jsonElement1, "payload");
/*     */               }
/*     */               
/* 145 */               PNStatusCategory pNStatusCategory = PNStatusCategory.PNUnknownCategory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 151 */               PubNubException pubNubException = PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_HTTP_ERROR).errormsg(str).jso(jsonElement1).statusCode(response.code()).build();
/*     */               
/* 153 */               if (response.code() == 403) {
/* 154 */                 pNStatusCategory = PNStatusCategory.PNAccessDeniedCategory;
/*     */                 
/* 156 */                 if (jsonElement2 != null && Endpoint.this.mapper.hasField(jsonElement2, "channels")) {
/* 157 */                   for (Iterator<JsonElement> iterator = Endpoint.this.mapper.getArrayIterator(jsonElement2, "channels"); iterator.hasNext(); ) {
/* 158 */                     JsonElement jsonElement = iterator.next();
/* 159 */                     arrayList1.add(Endpoint.this.mapper.elementToString(jsonElement));
/*     */                   } 
/*     */                 }
/*     */                 
/* 163 */                 if (jsonElement2 != null && Endpoint.this.mapper.hasField(jsonElement2, "channel-groups")) {
/* 164 */                   for (Iterator<JsonElement> iterator = Endpoint.this.mapper.getArrayIterator(jsonElement2, "channel-groups"); iterator.hasNext(); ) {
/* 165 */                     JsonElement jsonElement = iterator.next();
/* 166 */                     String str1 = Endpoint.this.mapper.elementToString(jsonElement).substring(0, 1).equals(":") ? Endpoint.this.mapper.elementToString(jsonElement).substring(1) : Endpoint.this.mapper.elementToString(jsonElement);
/* 167 */                     arrayList2.add(str1);
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */ 
/*     */               
/* 173 */               if (response.code() == 400) {
/* 174 */                 pNStatusCategory = PNStatusCategory.PNBadRequestCategory;
/*     */               }
/*     */               
/* 177 */               callback.onResponse(null, Endpoint.this.createStatusResponse(pNStatusCategory, response, (Exception)pubNubException, arrayList1, arrayList2));
/*     */               return;
/*     */             } 
/* 180 */             Endpoint.this.storeRequestLatency(response, Endpoint.this.getOperationType());
/*     */             
/*     */             try {
/* 183 */               object = Endpoint.this.createResponse(response);
/* 184 */             } catch (PubNubException pubNubException) {
/* 185 */               callback.onResponse(null, Endpoint.this.createStatusResponse(PNStatusCategory.PNMalformedResponseCategory, response, (Exception)pubNubException, null, null));
/*     */               
/*     */               return;
/*     */             } 
/* 189 */             callback.onResponse(object, Endpoint.this.createStatusResponse(PNStatusCategory.PNAcknowledgmentCategory, response, null, null, null));
/*     */           }
/*     */ 
/*     */           
/*     */           public void onFailure(Call<Input> performedCall, Throwable throwable) {
/* 194 */             if (Endpoint.this.silenceFailures) {
/*     */               return;
/*     */             }
/*     */             
/* 198 */             PNStatusCategory pNStatusCategory = PNStatusCategory.PNBadRequestCategory;
/*     */             
/* 200 */             PubNubException.PubNubExceptionBuilder pubNubExceptionBuilder = PubNubException.builder().errormsg(throwable.getMessage());
/*     */             
/*     */             try {
/* 203 */               throw throwable;
/* 204 */             } catch (UnknownHostException unknownHostException) {
/* 205 */               pubNubExceptionBuilder.pubnubError(PubNubErrorBuilder.PNERROBJ_CONNECTION_NOT_SET);
/* 206 */               pNStatusCategory = PNStatusCategory.PNUnexpectedDisconnectCategory;
/* 207 */             } catch (ConnectException connectException) {
/* 208 */               pubNubExceptionBuilder.pubnubError(PubNubErrorBuilder.PNERROBJ_CONNECT_EXCEPTION);
/* 209 */               pNStatusCategory = PNStatusCategory.PNUnexpectedDisconnectCategory;
/* 210 */             } catch (SocketTimeoutException socketTimeoutException) {
/* 211 */               pubNubExceptionBuilder.pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_TIMEOUT);
/* 212 */               pNStatusCategory = PNStatusCategory.PNTimeoutCategory;
/* 213 */             } catch (Throwable throwable1) {
/* 214 */               pubNubExceptionBuilder.pubnubError(PubNubErrorBuilder.PNERROBJ_HTTP_ERROR);
/*     */             } 
/*     */             
/* 217 */             callback.onResponse(null, Endpoint.this.createStatusResponse(pNStatusCategory, null, (Exception)pubNubExceptionBuilder.build(), null, null));
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void retry() {
/* 224 */     this.silenceFailures = false;
/* 225 */     async(this.cachedCallback);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void silentCancel() {
/* 232 */     if (this.call != null && !this.call.isCanceled()) {
/* 233 */       this.silenceFailures = true;
/* 234 */       this.call.cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   private PNStatus createStatusResponse(PNStatusCategory category, Response<Input> response, Exception throwable, ArrayList<String> errorChannels, ArrayList<String> errorChannelGroups) {
/* 239 */     PNStatus.PNStatusBuilder pNStatusBuilder = PNStatus.builder();
/*     */     
/* 241 */     pNStatusBuilder.executedEndpoint(this);
/*     */     
/* 243 */     if (response == null || throwable != null) {
/* 244 */       pNStatusBuilder.error(true);
/*     */     }
/* 246 */     if (throwable != null) {
/* 247 */       PNErrorData pNErrorData = new PNErrorData(throwable.getMessage(), throwable);
/* 248 */       pNStatusBuilder.errorData(pNErrorData);
/*     */     } 
/*     */     
/* 251 */     if (response != null) {
/* 252 */       pNStatusBuilder.statusCode(response.code());
/* 253 */       pNStatusBuilder.tlsEnabled(response.raw().request().url().isHttps());
/* 254 */       pNStatusBuilder.origin(response.raw().request().url().host());
/* 255 */       pNStatusBuilder.uuid(response.raw().request().url().queryParameter("uuid"));
/* 256 */       pNStatusBuilder.authKey(response.raw().request().url().queryParameter("auth"));
/* 257 */       pNStatusBuilder.clientRequest(response.raw().request());
/*     */     } 
/*     */     
/* 260 */     pNStatusBuilder.operation(getOperationType());
/* 261 */     pNStatusBuilder.category(category);
/*     */     
/* 263 */     if (errorChannels != null && !errorChannels.isEmpty()) {
/* 264 */       pNStatusBuilder.affectedChannels(errorChannels);
/*     */     } else {
/* 266 */       pNStatusBuilder.affectedChannels(getAffectedChannels());
/*     */     } 
/*     */     
/* 269 */     if (errorChannelGroups != null && !errorChannelGroups.isEmpty()) {
/* 270 */       pNStatusBuilder.affectedChannelGroups(errorChannelGroups);
/*     */     } else {
/* 272 */       pNStatusBuilder.affectedChannelGroups(getAffectedChannelGroups());
/*     */     } 
/*     */     
/* 275 */     return pNStatusBuilder.build();
/*     */   }
/*     */   
/*     */   private void storeRequestLatency(Response response, PNOperationType type) {
/* 279 */     if (this.telemetryManager != null) {
/* 280 */       long l = response.raw().receivedResponseAtMillis() - response.raw().sentRequestAtMillis();
/* 281 */       this.telemetryManager.storeLatency(l, type);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Map<String, String> createBaseParams() {
/* 286 */     HashMap<Object, Object> hashMap = new HashMap<>();
/*     */     
/* 288 */     hashMap.put("pnsdk", "PubNub-Java-Unified/".concat(this.pubnub.getVersion()));
/* 289 */     hashMap.put("uuid", this.pubnub.getConfiguration().getUuid());
/*     */     
/* 291 */     if (this.pubnub.getConfiguration().isIncludeInstanceIdentifier()) {
/* 292 */       hashMap.put("instanceid", this.pubnub.getInstanceId());
/*     */     }
/*     */     
/* 295 */     if (this.pubnub.getConfiguration().isIncludeRequestIdentifier()) {
/* 296 */       hashMap.put("requestid", this.pubnub.getRequestId());
/*     */     }
/*     */ 
/*     */     
/* 300 */     if (this.pubnub.getConfiguration().getAuthKey() != null && isAuthRequired()) {
/* 301 */       hashMap.put("auth", this.pubnub.getConfiguration().getAuthKey());
/*     */     }
/*     */     
/* 304 */     if (this.telemetryManager != null) {
/* 305 */       hashMap.putAll(this.telemetryManager.operationsLatency());
/*     */     }
/*     */     
/* 308 */     return (Map)hashMap;
/*     */   }
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
/*     */   protected PubNub getPubnub() {
/* 327 */     return this.pubnub;
/*     */   }
/*     */ 
/*     */   
/*     */   protected RetrofitManager getRetrofit() {
/* 332 */     return this.retrofit;
/*     */   }
/*     */   
/*     */   protected abstract List<String> getAffectedChannels();
/*     */   
/*     */   protected abstract List<String> getAffectedChannelGroups();
/*     */   
/*     */   protected abstract void validateParams() throws PubNubException;
/*     */   
/*     */   protected abstract Call<Input> doWork(Map<String, String> paramMap) throws PubNubException;
/*     */   
/*     */   protected abstract Output createResponse(Response<Input> paramResponse) throws PubNubException;
/*     */   
/*     */   protected abstract PNOperationType getOperationType();
/*     */   
/*     */   protected abstract boolean isAuthRequired();
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/Endpoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */