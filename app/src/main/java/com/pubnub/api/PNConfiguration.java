/*     */ package com.pubnub.api;
/*     */ 
/*     */

import com.pubnub.api.enums.PNHeartbeatNotificationOptions;
import com.pubnub.api.enums.PNLogVerbosity;
import com.pubnub.api.enums.PNReconnectionPolicy;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509ExtendedTrustManager;

import okhttp3.Authenticator;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionSpec;
import okhttp3.logging.HttpLoggingInterceptor;

/*     */
/*     */ 
/*     */ public class PNConfiguration
/*     */ {
/*     */   private static final int DEFAULT_DEDUPE_SIZE = 100;
/*     */   private static final int PRESENCE_TIMEOUT = 300;
/*     */   private static final int NON_SUBSCRIBE_REQUEST_TIMEOUT = 10;
/*     */   private static final int SUBSCRIBE_TIMEOUT = 310;
/*     */   private static final int CONNECT_TIMEOUT = 5;
/*     */   private SSLSocketFactory sslSocketFactory;
/*     */   private X509ExtendedTrustManager x509ExtendedTrustManager;
/*     */   private ConnectionSpec connectionSpec;
/*     */   private HostnameVerifier hostnameVerifier;
/*     */   private boolean includeInstanceIdentifier;
/*     */   private boolean includeRequestIdentifier;
/*     */   private String origin;
/*     */   private int subscribeTimeout;
/*     */   private int presenceTimeout;
/*     */   private int heartbeatInterval;
/*     */   private boolean secure;
/*     */   private String subscribeKey;
/*     */   private String publishKey;
/*     */   private String secretKey;
/*     */   private String cipherKey;
/*     */   private String authKey;
/*     */   private String uuid;
/*     */   @Deprecated
/*     */   private boolean cacheBusting;
/*     */   private PNLogVerbosity logVerbosity;
/*     */   private int connectTimeout;
/*     */   private int nonSubscribeRequestTimeout;
/*     */   private boolean supressLeaveEvents;
/*     */   private PNHeartbeatNotificationOptions heartbeatNotificationOptions;
/*     */   private String filterExpression;
/*     */   private PNReconnectionPolicy reconnectionPolicy;
/*     */   private int maximumReconnectionRetries;
/*     */   private Proxy proxy;
/*     */   private ProxySelector proxySelector;
/*     */   private Authenticator proxyAuthenticator;
/*     */   private CertificatePinner certificatePinner;
/*     */   private Integer maximumConnections;
/*     */   private HttpLoggingInterceptor httpLoggingInterceptorPrj;
/*     */   private Integer requestMessageCountThreshold;
/*     */   private boolean googleAppEngineNetworking;
/*     */   private boolean startSubscriberThread;
/*     */   private boolean dedupOnSubscribe;
/*     */   private Integer maximumMessagesCacheSize;
/*     */   
/*     */   public PNConfiguration() {
/* 161 */     setPresenceTimeout(300);
/*     */     
/* 163 */     this.uuid = "pn-" + UUID.randomUUID().toString();
/*     */     
/* 165 */     this.nonSubscribeRequestTimeout = 10;
/* 166 */     this.subscribeTimeout = 310;
/* 167 */     this.connectTimeout = 5;
/*     */     
/* 169 */     this.logVerbosity = PNLogVerbosity.NONE;
/*     */     
/* 171 */     this.heartbeatNotificationOptions = PNHeartbeatNotificationOptions.FAILURES;
/* 172 */     this.reconnectionPolicy = PNReconnectionPolicy.NONE;
/*     */     
/* 174 */     this.secure = true;
/*     */     
/* 176 */     this.includeInstanceIdentifier = false;
/*     */     
/* 178 */     this.includeRequestIdentifier = true;
/*     */     
/* 180 */     this.startSubscriberThread = true;
/*     */     
/* 182 */     this.maximumReconnectionRetries = -1;
/*     */     
/* 184 */     this.dedupOnSubscribe = false;
/* 185 */     this.supressLeaveEvents = false;
/* 186 */     this.maximumMessagesCacheSize = Integer.valueOf(100);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setPresenceTimeoutWithCustomInterval(int timeout, int interval) {
/* 197 */     this.presenceTimeout = timeout;
/* 198 */     this.heartbeatInterval = interval;
/*     */     
/* 200 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOrigin() {
/* 210 */     return this.origin;
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
/*     */   public PNConfiguration setOrigin(String origin) {
/* 222 */     this.origin = origin;
/* 223 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSubscribeTimeout() {
/* 228 */     return this.subscribeTimeout;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setSubscribeTimeout(int subscribeTimeout) {
/* 233 */     this.subscribeTimeout = subscribeTimeout;
/* 234 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPresenceTimeout() {
/* 243 */     return this.presenceTimeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setPresenceTimeout(int timeout) {
/* 253 */     return setPresenceTimeoutWithCustomInterval(timeout, timeout / 2 - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeartbeatInterval() {
/* 262 */     return this.heartbeatInterval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSecure() {
/* 271 */     return this.secure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setSecure(boolean secure) {
/* 282 */     this.secure = secure;
/* 283 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubscribeKey() {
/* 292 */     return this.subscribeKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setSubscribeKey(String subscribeKey) {
/* 303 */     this.subscribeKey = subscribeKey;
/* 304 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPublishKey() {
/* 314 */     return this.publishKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setPublishKey(String publishKey) {
/* 325 */     this.publishKey = publishKey;
/* 326 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSecretKey() {
/* 331 */     return this.secretKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setSecretKey(String secretKey) {
/* 336 */     this.secretKey = secretKey;
/* 337 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCipherKey() {
/* 342 */     return this.cipherKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setCipherKey(String cipherKey) {
/* 347 */     this.cipherKey = cipherKey;
/* 348 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAuthKey() {
/* 353 */     return this.authKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setAuthKey(String authKey) {
/* 358 */     this.authKey = authKey;
/* 359 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUuid() {
/* 364 */     return this.uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setUuid(String uuid) {
/* 369 */     this.uuid = uuid;
/* 370 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean isCacheBusting() {
/* 381 */     return this.cacheBusting;
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
/*     */   @Deprecated
/*     */   public PNConfiguration setCacheBusting(boolean cacheBusting) {
/* 394 */     this.cacheBusting = cacheBusting;
/* 395 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNLogVerbosity getLogVerbosity() {
/* 404 */     return this.logVerbosity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setLogVerbosity(PNLogVerbosity logVerbosity) {
/* 415 */     this.logVerbosity = logVerbosity;
/* 416 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getConnectTimeout() {
/* 425 */     return this.connectTimeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setConnectTimeout(int connectTimeout) {
/* 436 */     this.connectTimeout = connectTimeout;
/* 437 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNonSubscribeRequestTimeout() {
/* 447 */     return this.nonSubscribeRequestTimeout;
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
/*     */   public PNConfiguration setNonSubscribeRequestTimeout(int nonSubscribeRequestTimeout) {
/* 459 */     this.nonSubscribeRequestTimeout = nonSubscribeRequestTimeout;
/* 460 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSupressLeaveEvents() {
/* 469 */     return this.supressLeaveEvents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setSupressLeaveEvents(boolean supressLeaveEvents) {
/* 480 */     this.supressLeaveEvents = supressLeaveEvents;
/* 481 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNHeartbeatNotificationOptions getHeartbeatNotificationOptions() {
/* 490 */     return this.heartbeatNotificationOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setHeartbeatNotificationOptions(PNHeartbeatNotificationOptions heartbeatNotificationOptions) {
/* 501 */     this.heartbeatNotificationOptions = heartbeatNotificationOptions;
/* 502 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFilterExpression() {
/* 511 */     return this.filterExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setFilterExpression(String filterExpression) {
/* 522 */     this.filterExpression = filterExpression;
/* 523 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNReconnectionPolicy getReconnectionPolicy() {
/* 532 */     return this.reconnectionPolicy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setReconnectionPolicy(PNReconnectionPolicy reconnectionPolicy) {
/* 543 */     this.reconnectionPolicy = reconnectionPolicy;
/* 544 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaximumReconnectionRetries() {
/* 553 */     return this.maximumReconnectionRetries;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setMaximumReconnectionRetries(int maximumReconnectionRetries) {
/* 564 */     this.maximumReconnectionRetries = maximumReconnectionRetries;
/* 565 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Proxy getProxy() {
/* 574 */     return this.proxy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setProxy(Proxy proxy) {
/* 585 */     this.proxy = proxy;
/* 586 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ProxySelector getProxySelector() {
/* 591 */     return this.proxySelector;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setProxySelector(ProxySelector proxySelector) {
/* 596 */     this.proxySelector = proxySelector;
/* 597 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Authenticator getProxyAuthenticator() {
/* 602 */     return this.proxyAuthenticator;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setProxyAuthenticator(Authenticator proxyAuthenticator) {
/* 607 */     this.proxyAuthenticator = proxyAuthenticator;
/* 608 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CertificatePinner getCertificatePinner() {
/* 613 */     return this.certificatePinner;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setCertificatePinner(CertificatePinner certificatePinner) {
/* 618 */     this.certificatePinner = certificatePinner;
/* 619 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getMaximumConnections() {
/* 624 */     return this.maximumConnections;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setMaximumConnections(Integer maximumConnections) {
/* 629 */     this.maximumConnections = maximumConnections;
/* 630 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpLoggingInterceptor getHttpLoggingInterceptorPrj() {
/* 635 */     return this.httpLoggingInterceptorPrj;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setHttpLoggingInterceptorPrj(HttpLoggingInterceptor httpLoggingInterceptorPrj) {
/* 640 */     this.httpLoggingInterceptorPrj = httpLoggingInterceptorPrj;
/* 641 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getRequestMessageCountThreshold() {
/* 650 */     return this.requestMessageCountThreshold;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setRequestMessageCountThreshold(Integer requestMessageCountThreshold) {
/* 661 */     this.requestMessageCountThreshold = requestMessageCountThreshold;
/* 662 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGoogleAppEngineNetworking() {
/* 671 */     return this.googleAppEngineNetworking;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setGoogleAppEngineNetworking(boolean googleAppEngineNetworking) {
/* 682 */     this.googleAppEngineNetworking = googleAppEngineNetworking;
/* 683 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStartSubscriberThread() {
/* 688 */     return this.startSubscriberThread;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setStartSubscriberThread(boolean startSubscriberThread) {
/* 693 */     this.startSubscriberThread = startSubscriberThread;
/* 694 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDedupOnSubscribe() {
/* 699 */     return this.dedupOnSubscribe;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setDedupOnSubscribe(boolean dedupOnSubscribe) {
/* 704 */     this.dedupOnSubscribe = dedupOnSubscribe;
/* 705 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getMaximumMessagesCacheSize() {
/* 710 */     return this.maximumMessagesCacheSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setMaximumMessagesCacheSize(Integer maximumMessagesCacheSize) {
/* 715 */     this.maximumMessagesCacheSize = maximumMessagesCacheSize;
/* 716 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SSLSocketFactory getSslSocketFactory() {
/* 721 */     return this.sslSocketFactory;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
/* 726 */     this.sslSocketFactory = sslSocketFactory;
/* 727 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public X509ExtendedTrustManager getX509ExtendedTrustManager() {
/* 732 */     return this.x509ExtendedTrustManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setX509ExtendedTrustManager(X509ExtendedTrustManager x509ExtendedTrustManager) {
/* 737 */     this.x509ExtendedTrustManager = x509ExtendedTrustManager;
/* 738 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConnectionSpec getConnectionSpec() {
/* 743 */     return this.connectionSpec;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setConnectionSpec(ConnectionSpec connectionSpec) {
/* 748 */     this.connectionSpec = connectionSpec;
/* 749 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public HostnameVerifier getHostnameVerifier() {
/* 754 */     return this.hostnameVerifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNConfiguration setHostnameVerifier(HostnameVerifier hostnameVerifier) {
/* 759 */     this.hostnameVerifier = hostnameVerifier;
/* 760 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIncludeInstanceIdentifier() {
/* 769 */     return this.includeInstanceIdentifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setIncludeInstanceIdentifier(boolean includeInstanceIdentifier) {
/* 780 */     this.includeInstanceIdentifier = includeInstanceIdentifier;
/* 781 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIncludeRequestIdentifier() {
/* 790 */     return this.includeRequestIdentifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNConfiguration setIncludeRequestIdentifier(boolean includeRequestIdentifier) {
/* 801 */     this.includeRequestIdentifier = includeRequestIdentifier;
/* 802 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/PNConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */