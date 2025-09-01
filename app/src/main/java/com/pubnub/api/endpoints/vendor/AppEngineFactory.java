/*     */ package com.pubnub.api.endpoints.vendor;
/*     */ 
/*     */

import androidx.annotation.NonNull;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Timeout;

/*     */
/*     */ 
/*     */ public class AppEngineFactory
/*     */   implements Call
/*     */ {
/*  25 */   private static final Logger log = Logger.getLogger(AppEngineFactory.class.getName());
/*     */   
/*     */   private Request request;
/*     */   private PubNub pubNub;
/*     */   
/*     */   AppEngineFactory(Request request, PubNub pubNub) {
/*  31 */     this.request = request;
/*  32 */     this.pubNub = pubNub;
/*     */   }
/*     */ 
/*     */   
/*     */   public Request request() {
/*  37 */     return this.request;
/*     */   }
/*     */ 
/*     */   
/*     */   public Response execute() throws IOException {
/*  42 */     this.request = PubNubUtil.requestSigner(this.request, this.pubNub.getConfiguration(), this.pubNub.getTimestamp());
/*     */     
/*  44 */     URL uRL = this.request.url().url();
/*  45 */     final HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
/*  46 */     httpURLConnection.setUseCaches(false);
/*  47 */     httpURLConnection.setDoOutput(true);
/*  48 */     httpURLConnection.setRequestMethod(this.request.method());
/*     */     
/*  50 */     Headers headers = this.request.headers();
/*  51 */     if (headers != null) {
/*  52 */       for (byte b = 0; b < headers.size(); b++) {
/*  53 */         String str = headers.name(b);
/*  54 */         httpURLConnection.setRequestProperty(str, headers.get(str));
/*     */       } 
/*     */     }
/*     */     
/*  58 */     if (this.request.body() != null) {
/*     */       
/*  60 */       BufferedSink bufferedSink = Okio.buffer(Okio.sink(httpURLConnection.getOutputStream()));
/*  61 */       this.request.body().writeTo(bufferedSink);
/*  62 */       bufferedSink.close();
/*     */     } 
/*     */     
/*  65 */     httpURLConnection.connect();
/*     */     
/*  67 */     final BufferedSource bufferedSource = Okio.buffer(Okio.source(httpURLConnection.getInputStream()));
/*  68 */     if (httpURLConnection.getResponseCode() != 200) {
/*  69 */       throw new IOException("Fail to call  :: " + bufferedSource.readUtf8());
/*     */     }
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
/*  92 */     return (new Response.Builder()).code(httpURLConnection.getResponseCode()).message(httpURLConnection.getResponseMessage()).request(this.request).protocol(Protocol.HTTP_1_1).body(new ResponseBody() { public MediaType contentType() { return MediaType.parse(httpURLConnection.getContentType()); } public long contentLength() { return httpURLConnection.getContentLengthLong(); } public BufferedSource source() { return bufferedSource; } }).build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enqueue(Callback responseCallback) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cancel() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExecuted() {
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCanceled() {
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Call clone() {
/*     */     try {
/* 119 */       return (Call)super.clone();
/* 120 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/* 121 */       return null;
/*     */     } 
/*     */   }

    @NonNull
    @Override
    public Timeout timeout() {
        return Timeout.NONE;
    }

    /*     */
/*     */   public static class Factory implements Call.Factory {
/*     */     private PubNub pubNub;
/*     */     
/*     */     public Factory(PubNub pubNub) {
/* 129 */       this.pubNub = pubNub;
/*     */     }
/*     */ 
/*     */     
/*     */     public Call newCall(Request request) {
/* 134 */       return new AppEngineFactory(request, this.pubNub);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/vendor/AppEngineFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */