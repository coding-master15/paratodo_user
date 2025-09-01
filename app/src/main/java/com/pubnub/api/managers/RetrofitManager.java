/*     */ package com.pubnub.api.managers;
/*     */ 
/*     */

import android.os.Build;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.endpoints.vendor.AppEngineFactory;
import com.pubnub.api.enums.PNLogVerbosity;
import com.pubnub.api.interceptors.SignatureInterceptor;
import com.pubnub.api.services.AccessManagerService;
import com.pubnub.api.services.ChannelGroupService;
import com.pubnub.api.services.HistoryService;
import com.pubnub.api.services.PresenceService;
import com.pubnub.api.services.PublishService;
import com.pubnub.api.services.PushService;
import com.pubnub.api.services.SubscribeService;
import com.pubnub.api.services.TimeService;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RetrofitManager
/*     */ {
/*     */   private PubNub pubnub;
/*     */   private SignatureInterceptor signatureInterceptor;
/*     */   private OkHttpClient transactionClientInstance;
/*     */   private OkHttpClient subscriptionClientInstance;
/*     */   private PresenceService presenceService;
/*     */   private HistoryService historyService;
/*     */   private PushService pushService;
/*     */   private AccessManagerService accessManagerService;
/*     */   private ChannelGroupService channelGroupService;
/*     */   private TimeService timeService;
/*     */   private PublishService publishService;
/*     */   private SubscribeService subscribeService;
/*     */   private Retrofit transactionInstance;
/*     */   private Retrofit subscriptionInstance;
/*     */   
/*     */   public RetrofitManager(PubNub pubNubInstance) {
/*  49 */     this.pubnub = pubNubInstance;
/*     */     
/*  51 */     this.signatureInterceptor = new SignatureInterceptor(pubNubInstance);
/*     */     
/*  53 */     if (!pubNubInstance.getConfiguration().isGoogleAppEngineNetworking()) {
/*  54 */       this.transactionClientInstance = createOkHttpClient(this.pubnub
/*  55 */           .getConfiguration().getNonSubscribeRequestTimeout(), this.pubnub
/*  56 */           .getConfiguration().getConnectTimeout());
/*     */ 
/*     */       
/*  59 */       this.subscriptionClientInstance = createOkHttpClient(this.pubnub
/*  60 */           .getConfiguration().getSubscribeTimeout(), this.pubnub
/*  61 */           .getConfiguration().getConnectTimeout());
/*     */     } 
/*     */ 
/*     */     
/*  65 */     this.transactionInstance = createRetrofit(this.transactionClientInstance);
/*  66 */     this.subscriptionInstance = createRetrofit(this.subscriptionClientInstance);
/*     */     
/*  68 */     this.presenceService = (PresenceService)this.transactionInstance.create(PresenceService.class);
/*  69 */     this.historyService = (HistoryService)this.transactionInstance.create(HistoryService.class);
/*  70 */     this.pushService = (PushService)this.transactionInstance.create(PushService.class);
/*  71 */     this.accessManagerService = (AccessManagerService)this.transactionInstance.create(AccessManagerService.class);
/*  72 */     this.channelGroupService = (ChannelGroupService)this.transactionInstance.create(ChannelGroupService.class);
/*  73 */     this.publishService = (PublishService)this.transactionInstance.create(PublishService.class);
/*  74 */     this.subscribeService = (SubscribeService)this.subscriptionInstance.create(SubscribeService.class);
/*  75 */     this.timeService = (TimeService)this.transactionInstance.create(TimeService.class);
/*     */   }
/*     */ 
/*     */   
/*     */   private OkHttpClient createOkHttpClient(int requestTimeout, int connectTimeOut) {
/*  80 */     PNConfiguration pNConfiguration = this.pubnub.getConfiguration();
/*  81 */     OkHttpClient.Builder builder = new OkHttpClient.Builder();
/*  82 */     builder.readTimeout(requestTimeout, TimeUnit.SECONDS);
/*  83 */     builder.connectTimeout(connectTimeOut, TimeUnit.SECONDS);
/*     */     
/*  85 */     if (this.pubnub.getConfiguration().getLogVerbosity() == PNLogVerbosity.BODY) {
/*  86 */       HttpLoggingInterceptor httpLoggingInterceptorPrj = new HttpLoggingInterceptor();
/*  87 */       httpLoggingInterceptorPrj.setLevel(HttpLoggingInterceptor.Level.BODY);
/*  88 */       builder.addInterceptor((Interceptor)httpLoggingInterceptorPrj);
/*     */     } 
/*     */     
/*  91 */     if (this.pubnub.getConfiguration().getHttpLoggingInterceptorPrj() != null) {
/*  92 */       builder.addInterceptor((Interceptor)this.pubnub.getConfiguration().getHttpLoggingInterceptorPrj());
/*     */     }
/*     */     
/*  95 */     if (pNConfiguration.getSslSocketFactory() != null && pNConfiguration.getX509ExtendedTrustManager() != null) {
/*  96 */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.sslSocketFactory(pNConfiguration.getSslSocketFactory(), pNConfiguration.getX509ExtendedTrustManager());
        }
        /*     */     }
/*     */     
/*  99 */     if (pNConfiguration.getConnectionSpec() != null) {
/* 100 */       builder.connectionSpecs(Collections.singletonList(pNConfiguration.getConnectionSpec()));
/*     */     }
/*     */     
/* 103 */     if (pNConfiguration.getHostnameVerifier() != null) {
/* 104 */       builder.hostnameVerifier(pNConfiguration.getHostnameVerifier());
/*     */     }
/*     */     
/* 107 */     if (this.pubnub.getConfiguration().getProxy() != null) {
/* 108 */       builder.proxy(this.pubnub.getConfiguration().getProxy());
/*     */     }
/*     */     
/* 111 */     if (this.pubnub.getConfiguration().getProxySelector() != null) {
/* 112 */       builder.proxySelector(this.pubnub.getConfiguration().getProxySelector());
/*     */     }
/*     */     
/* 115 */     if (this.pubnub.getConfiguration().getProxyAuthenticator() != null) {
/* 116 */       builder.proxyAuthenticator(this.pubnub.getConfiguration().getProxyAuthenticator());
/*     */     }
/*     */     
/* 119 */     if (this.pubnub.getConfiguration().getCertificatePinner() != null) {
/* 120 */       builder.certificatePinner(this.pubnub.getConfiguration().getCertificatePinner());
/*     */     }
/*     */     
/* 123 */     builder.addInterceptor((Interceptor)this.signatureInterceptor);
/*     */     
/* 125 */     OkHttpClient okHttpClient = builder.build();
/*     */     
/* 127 */     if (this.pubnub.getConfiguration().getMaximumConnections() != null) {
/* 128 */       okHttpClient.dispatcher().setMaxRequestsPerHost(this.pubnub.getConfiguration().getMaximumConnections().intValue());
/*     */     }
/*     */     
/* 131 */     return okHttpClient;
/*     */   }
/*     */   
/*     */   private Retrofit createRetrofit(OkHttpClient client) {
/* 135 */     Retrofit.Builder builder = new Retrofit.Builder();
/*     */     
/* 137 */     if (this.pubnub.getConfiguration().isGoogleAppEngineNetworking()) {
/* 138 */       builder.callFactory(new AppEngineFactory.Factory(this.pubnub));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 143 */     builder = builder.baseUrl(this.pubnub.getBaseUrl()).addConverterFactory(this.pubnub.getMapper().getConverterFactory());
/*     */     
/* 145 */     if (!this.pubnub.getConfiguration().isGoogleAppEngineNetworking()) {
/* 146 */       builder = builder.client(client);
/*     */     }
/*     */     
/* 149 */     return builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public void destroy(boolean force) {
/* 154 */     if (this.transactionClientInstance != null) {
/* 155 */       closeExecutor(this.transactionClientInstance, force);
/*     */     }
/* 157 */     if (this.subscriptionClientInstance != null) {
/* 158 */       closeExecutor(this.subscriptionClientInstance, force);
/*     */     }
/*     */   }
/*     */   
/*     */   private void closeExecutor(OkHttpClient client, boolean force) {
/* 163 */     client.dispatcher().cancelAll();
/* 164 */     if (force) {
/* 165 */       client.connectionPool().evictAll();
/* 166 */       ExecutorService executorService = client.dispatcher().executorService();
/* 167 */       executorService.shutdown();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PresenceService getPresenceService() {
/* 173 */     return this.presenceService;
/*     */   }
/*     */ 
/*     */   
/*     */   public HistoryService getHistoryService() {
/* 178 */     return this.historyService;
/*     */   }
/*     */ 
/*     */   
/*     */   public PushService getPushService() {
/* 183 */     return this.pushService;
/*     */   }
/*     */ 
/*     */   
/*     */   public AccessManagerService getAccessManagerService() {
/* 188 */     return this.accessManagerService;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelGroupService getChannelGroupService() {
/* 193 */     return this.channelGroupService;
/*     */   }
/*     */ 
/*     */   
/*     */   public TimeService getTimeService() {
/* 198 */     return this.timeService;
/*     */   }
/*     */ 
/*     */   
/*     */   public PublishService getPublishService() {
/* 203 */     return this.publishService;
/*     */   }
/*     */ 
/*     */   
/*     */   public SubscribeService getSubscribeService() {
/* 208 */     return this.subscribeService;
/*     */   }
/*     */ 
/*     */   
/*     */   public Retrofit getTransactionInstance() {
/* 213 */     return this.transactionInstance;
/*     */   }
/*     */ 
/*     */   
/*     */   public Retrofit getSubscriptionInstance() {
/* 218 */     return this.subscriptionInstance;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/managers/RetrofitManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */