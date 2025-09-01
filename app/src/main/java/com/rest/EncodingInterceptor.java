/*     */ package com.rest;
/*     */ 
/*     */

import android.content.Context;
import android.util.Base64;

import com.general.files.DataHelper;
import com.utils.Utils;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EncodingInterceptor
/*     */   implements Interceptor
/*     */ {
/*     */   Context a;
/*  31 */   private volatile Level b = Level.NONE;
/*  32 */   private final Logger c = Logger.DEFAULT;
/*  33 */   private static final Charset d = Charset.forName("UTF-8");
/*  34 */   private String e = "";
/*  35 */   private String f = "!!@@!!@@@@@!!@@!!";
/*     */   
/*     */   public EncodingInterceptor(Context mContext, String baseURL) {
/*  38 */     this.a = mContext;
/*  39 */     this.e = baseURL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Response intercept(Chain chain) throws IOException {
/*  45 */     Request request = chain.request();
/*  46 */     RequestBody requestBody = request.body();
/*  47 */     Buffer buffer = new Buffer();
/*  48 */     requestBody.writeTo((BufferedSink)buffer);
/*  49 */     String str = buffer.readUtf8();
/*     */     
/*  51 */     if (!requestBody.contentType().toString().contains("multipart/form-data")) {
/*  52 */       MediaType mediaType1 = MediaType.parse("text/plain; charset=utf-8");
/*     */       
/*  54 */       String str1 = "";
/*     */       
/*     */       try {
/*  57 */         str1 = DataHelper.getInstance(this.a).encrypt(str);
/*  58 */         str1 = str1 + this.f + Base64.encodeToString((this.a.getApplicationInfo()).packageName.getBytes("UTF-8"), 0) + this.f + Base64.encodeToString(Utils.SERVER_CONNECTION_URL.getBytes("UTF-8"), 0);
/*  59 */       } catch (Exception exception) {
/*  60 */         exception.printStackTrace();
/*  61 */         str1 = str;
/*     */       } 
/*     */       
/*  64 */       RequestBody requestBody1 = RequestBody.create(mediaType1, str1);
/*  65 */       request = request.newBuilder().header("Content-Type", requestBody1.contentType().toString()).header("Content-Length", String.valueOf(requestBody1.contentLength())).method(request.method(), requestBody1).build();
/*     */ 
/*     */       
/*  68 */       if (this.b == Level.BODY) {
/*  69 */         this.c.log("Post Data Start >> Just for debug purpose >> ");
/*  70 */         this.c.log(request.url() + "" + str);
/*  71 */         this.c.log("<< Post Data End");
/*     */       } 
/*     */       
/*  74 */       boolean bool1 = (this.b == Level.BODY) ? true : false;
/*  75 */       boolean bool2 = (bool1 || this.b == Level.HEADERS) ? true : false;
/*     */       
/*  77 */       boolean bool3 = true;
/*     */       
/*  79 */       Connection connection = chain.connection();
/*     */ 
/*     */ 
/*     */       
/*  83 */       String str2 = "--> " + request.method() + ' ' + request.url() + ((connection != null) ? (" " + connection.protocol()) : "");
/*  84 */       if (!bool2 && bool3) {
/*  85 */         str2 = str2 + " (" + requestBody1.contentLength() + "-byte body)";
/*     */       }
/*     */       
/*  88 */       if (this.b == Level.HEADERS || this.b == Level.BODY) {
/*  89 */         this.c.log(str2);
/*     */       }
/*     */       
/*  92 */       if (bool2) {
/*  93 */         if (bool3) {
/*     */ 
/*     */           
/*  96 */           if (requestBody1.contentType() != null) {
/*  97 */             this.c.log("Content-Type: " + requestBody1.contentType());
/*     */           }
/*  99 */           if (requestBody1.contentLength() != -1L) {
/* 100 */             this.c.log("Content-Length: " + requestBody1.contentLength());
/*     */           }
/*     */         } 
/*     */         
/* 104 */         Headers headers = request.headers(); byte b; int i;
/* 105 */         for (b = 0, i = headers.size(); b < i; b++) {
/* 106 */           String str6 = headers.name(b);
/*     */           
/* 108 */           if (!"Content-Type".equalsIgnoreCase(str6) && !"Content-Length".equalsIgnoreCase(str6)) {
/* 109 */             this.c.log(str6 + ": " + headers.value(b));
/*     */           }
/*     */         } 
/*     */         
/* 113 */         if (!bool1 || !bool3) {
/* 114 */           this.c.log("--> END " + request.method());
/* 115 */         } else if (a(request.headers())) {
/* 116 */           this.c.log("--> END " + request.method() + " (encoded body omitted)");
/*     */         } else {
/* 118 */           Buffer buffer1 = new Buffer();
/* 119 */           requestBody1.writeTo((BufferedSink)buffer1);
/*     */           
/* 121 */           Charset charset = d;
/* 122 */           MediaType mediaType = requestBody1.contentType();
/* 123 */           if (mediaType != null) {
/* 124 */             charset = mediaType.charset(d);
/*     */           }
/*     */           
/* 127 */           this.c.log("");
/* 128 */           if (a(buffer1)) {
/* 129 */             this.c.log(buffer1.readString(charset));
/* 130 */             this.c.log("--> END " + request.method() + " (" + requestBody1
/* 131 */                 .contentLength() + "-byte body)");
/*     */           } else {
/* 133 */             this.c.log("--> END " + request.method() + " (binary " + requestBody1
/* 134 */                 .contentLength() + "-byte body omitted)");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 139 */       long l1 = System.nanoTime();

                Response response;
/*     */       
/*     */       try {
/* 142 */         response = chain.proceed(request);
/* 143 */       } catch (Exception exception) {
/* 144 */         this.c.log("<-- HTTP FAILED: " + exception);
/* 145 */         throw exception;
/*     */       } 
/* 147 */       long l2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - l1);
/*     */       
/* 149 */       ResponseBody responseBody = response.body();
/*     */       
/* 151 */       String str3 = responseBody.string();
/*     */       
/* 153 */       String str4 = "";
/*     */       
/*     */       try {
/* 156 */         str4 = DataHelper.getInstance(this.a).decrypt(str3);
/* 157 */       } catch (Exception exception) {
/* 158 */         if (!RestClient.isEncAllowed(this.a, this.e)) {
/* 159 */           str4 = str3;
/*     */         } else {
/* 161 */           exception.printStackTrace();
/*     */         } 
/*     */       } 
/*     */       
/* 165 */       MediaType mediaType2 = response.body().contentType();
/* 166 */       responseBody = ResponseBody.create(mediaType2, str4);
/* 167 */       Response responsea = response.newBuilder().body(responseBody).build();
/*     */       
/* 169 */       long l3 = responseBody.contentLength();
/* 170 */       String str5 = (l3 != -1L) ? (l3 + "-byte") : "unknown-length";
/*     */       
/* 172 */       if (this.b == Level.HEADERS || this.b == Level.BODY) {
/* 173 */         this.c.log("<-- " + responsea
/* 174 */             .code() + (
/* 175 */             responsea.message().isEmpty() ? "" : (' ' + responsea.message())) + ' ' + responsea
/* 176 */             .request().url() + " (" + l2 + "ms" + (!bool2 ? (", " + str5 + " body") : "") + ')');
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 181 */       if (bool2) {
/* 182 */         Headers headers = response.headers(); byte b; int i;
/* 183 */         for (b = 0, i = headers.size(); b < i; b++) {
/* 184 */           this.c.log(headers.name(b) + ": " + headers.value(b));
/*     */         }
/*     */         
/* 187 */         if (!bool1 || !HttpHeaders.hasBody(response)) {
/* 188 */           this.c.log("<-- END HTTP");
/* 189 */         } else if (a(response.headers())) {
/* 190 */           this.c.log("<-- END HTTP (encoded body omitted)");
/*     */         } else {
/* 192 */           BufferedSource bufferedSource = responseBody.source();
/* 193 */           bufferedSource.request(Long.MAX_VALUE);
/* 194 */           Buffer buffer1 = bufferedSource.buffer();
/*     */           
/* 196 */           Charset charset = d;
/* 197 */           if (mediaType2 != null) {
/* 198 */             charset = mediaType2.charset(d);
/*     */           }
/*     */           
/* 201 */           if (!a(buffer1)) {
/* 202 */             this.c.log("");
/* 203 */             this.c.log("<-- END HTTP (binary " + buffer1.size() + "-byte body omitted)");
/* 204 */             return response;
/*     */           } 
/*     */           
/* 207 */           if (l3 != 0L) {
/* 208 */             this.c.log("");
/* 209 */             this.c.log(buffer1.clone().readString(charset));
/*     */           } 
/*     */           
/* 212 */           this.c.log("<-- END HTTP (" + buffer1.size() + "-byte body)");
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 217 */       return response;
/*     */     } 
/*     */     
/* 220 */     return chain.proceed(request);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Level
/*     */   {
/* 229 */     NONE,
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
/* 240 */     BASIC,
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
/* 258 */     HEADERS,
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
/* 280 */     BODY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EncodingInterceptor setLevel(Level level) {
/* 287 */     if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead."); 
/* 288 */     this.b = level;
/* 289 */     return this;
/*     */   }
/*     */   public static interface Logger {

    public static final Logger DEFAULT = (message -> Platform.get().log(message, 4, null));

    /*     */
    /*     */     void log(String param1String);

}
/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(Headers paramHeaders) {
/* 303 */     String str = paramHeaders.get("Content-Encoding");
/* 304 */     return (str != null && !str.equalsIgnoreCase("identity"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean a(Buffer paramBuffer) {
/*     */     try {
/* 313 */       Buffer buffer = new Buffer();
/* 314 */       long l = (paramBuffer.size() < 64L) ? paramBuffer.size() : 64L;
/* 315 */       paramBuffer.copyTo(buffer, 0L, l);
/* 316 */       for (byte b = 0; b < 16 && 
/* 317 */         !buffer.exhausted(); b++) {
/*     */ 
/*     */         
/* 320 */         int i = buffer.readUtf8CodePoint();
/* 321 */         if (Character.isISOControl(i) && !Character.isWhitespace(i)) {
/* 322 */           return false;
/*     */         }
/*     */       } 
/* 325 */       return true;
/* 326 */     } catch (EOFException eOFException) {
/* 327 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/rest/EncodingInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */