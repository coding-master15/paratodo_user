/*     */ package com.rest;
/*     */ 
/*     */

import android.content.Context;

import com.general.files.GeneralFunctions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utils.Utils;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;
/*     */ 
/*     */ 
/*     */ public class RestClient
/*     */ {
/*     */   public static final String SERVER_URL_KEY = "SERVERURL";
/*  44 */   public static String ALLOW_ENC_URL = "http://192.168.4.151/cubejekdev";
/*     */   
/*     */   public static boolean IS_ENCODE_ALL_DATA = false;
/*     */   
/*  48 */   public static int COMM_CONNECT_TIMEOUT = 280;
/*  49 */   public static int COMM_READ_TIMEOUT = 280;
/*  50 */   public static int COMM_WRITE_TIMEOUT = 280;
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
/*     */   public static boolean isEncAllowed(Context mContext, String serverConnectionURL) {
/*  65 */     return IS_ENCODE_ALL_DATA;
/*     */   }
/*     */   
/*     */   public static ApiInterface getClient(String connectionType, String baseURL, Context mContext, boolean isAdvanceMapAPIUrl) {
/*  69 */     return getClient(connectionType, baseURL, mContext, (Converter.Factory)GsonConverterFactory.create(), isAdvanceMapAPIUrl);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ApiInterface getClient(String connectionType, String baseURL, Context mContext) {
/*  74 */     return getClient(connectionType, baseURL, mContext, (Converter.Factory)GsonConverterFactory.create(), false);
/*     */   }
/*     */   
/*     */   public static ApiInterface getClient(String connectionType, String baseURL, Context mContext, Converter.Factory factory) {
/*  78 */     return getClient(connectionType, baseURL, mContext, factory, false);
/*     */   }
/*     */   
/*     */   public static ApiInterface getClient(String connectionType, String baseURL, Context mContext, Converter.Factory factory, boolean isAdvanceMapAPIUrl) {
/*     */     HttpLoggingInterceptor httpLoggingInterceptorPrj = null;
/*  83 */     EncodingInterceptor encodingInterceptor = null;
/*     */     
/*  85 */     String str = GeneralFunctions.retrieveValue("SERVERURL", mContext);
/*     */     
/*  87 */     if ((str.startsWith(baseURL) && isEncAllowed(mContext, str)) || isAdvanceMapAPIUrl) {
/*  88 */       EncodingInterceptor encodingInterceptor1 = new EncodingInterceptor(mContext, str);
/*  89 */       encodingInterceptor1.setLevel(Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("Yes") ? EncodingInterceptor.Level.BODY : EncodingInterceptor.Level.NONE);
/*  90 */       encodingInterceptor = encodingInterceptor1;
/*     */     } else {
/*  92 */       HttpLoggingInterceptor httpLoggingInterceptorPrj1 = new HttpLoggingInterceptor();
/*  93 */       httpLoggingInterceptorPrj1.setLevel(Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("Yes") ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
/*  94 */       httpLoggingInterceptorPrj = httpLoggingInterceptorPrj1;
/*     */     } 
/*     */     
/*  97 */     OkHttpClient.Builder builder = null;
/*     */     
/*  99 */     if (baseURL.startsWith("https://") && connectionType.equalsIgnoreCase("POST")) {
/* 100 */       builder = a();
/*     */     } else {
/* 102 */       builder = new OkHttpClient.Builder();
/*     */     } 
/*     */     
/* 105 */     if (builder == null) {
/* 106 */       builder = new OkHttpClient.Builder();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     OkHttpClient okHttpClient = builder.addInterceptor((Interceptor)httpLoggingInterceptorPrj).connectTimeout(COMM_CONNECT_TIMEOUT, TimeUnit.SECONDS).readTimeout(COMM_READ_TIMEOUT, TimeUnit.SECONDS).writeTimeout(COMM_WRITE_TIMEOUT, TimeUnit.SECONDS).build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     Retrofit retrofit = (new Retrofit.Builder()).baseUrl(baseURL).client(okHttpClient).addConverterFactory(factory).build();
/*     */     
/* 120 */     return (ApiInterface)retrofit.create(ApiInterface.class);
/*     */   }
/*     */   
/*     */   public static ApiInterface getClient(String connectionType, String baseURL) {
/* 124 */     HttpLoggingInterceptor httpLoggingInterceptorPrj = new HttpLoggingInterceptor();
/*     */     
/* 126 */     if (Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("Yes")) {
/* 127 */       httpLoggingInterceptorPrj.setLevel(HttpLoggingInterceptor.Level.BODY);
/*     */     } else {
/* 129 */       httpLoggingInterceptorPrj.setLevel(HttpLoggingInterceptor.Level.NONE);
/*     */     } 
/*     */     
/* 132 */     OkHttpClient.Builder builder = null;
/*     */     
/* 134 */     if (baseURL.startsWith("https://") && connectionType.equalsIgnoreCase("POST")) {
/* 135 */       builder = a();
/*     */     } else {
/* 137 */       builder = new OkHttpClient.Builder();
/*     */     } 
/*     */     
/* 140 */     if (builder == null) {
/* 141 */       builder = new OkHttpClient.Builder();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     Retrofit retrofit = (new Retrofit.Builder()).baseUrl(baseURL).client(builder.addInterceptor((Interceptor)httpLoggingInterceptorPrj).connectTimeout(COMM_CONNECT_TIMEOUT, TimeUnit.SECONDS).readTimeout(COMM_READ_TIMEOUT, TimeUnit.SECONDS).writeTimeout(COMM_WRITE_TIMEOUT, TimeUnit.SECONDS).build()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).build();
/*     */     
/* 153 */     return (ApiInterface)retrofit.create(ApiInterface.class);
/*     */   }
/*     */   
/*     */   private static OkHttpClient.Builder a()
/*     */   {
/*     */     try {
/* 159 */       TrustManager[] arrayOfTrustManager = { new X509TrustManager()
/*     */           {
/*     */             public void checkClientTrusted(X509Certificate[] chain, String authType) {}
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void checkServerTrusted(X509Certificate[] chain, String authType) {}
/*     */ 
/*     */ 
/*     */             
/*     */             public X509Certificate[] getAcceptedIssuers() {
/* 171 */               return new X509Certificate[0];
/*     */             }
/*     */           } };
/*     */ 
/*     */ 
/*     */       
/* 177 */       SSLContext sSLContext = SSLContext.getInstance("SSL");
/* 178 */       sSLContext.init(null, arrayOfTrustManager, new SecureRandom());
/*     */ 
/*     */       
/* 181 */       SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
/*     */       
/* 183 */       OkHttpClient.Builder builder = new OkHttpClient.Builder();
/* 184 */       builder.sslSocketFactory(sSLSocketFactory, (X509TrustManager)arrayOfTrustManager[0]);
/*     */       
/* 186 */       return builder;
/* 187 */     } catch (Exception exception) {
/* 188 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Gson getGSONBuilder() {
/* 198 */     return (new GsonBuilder()).create();
/*     */   }
/*     */   
/*     */   public static interface ApiInterface {
/*     */     @FormUrlEncoded
/*     */     @POST
/*     */     Call<Object> getResponse(@Url String param1String, @FieldMap Map<String, String> param1Map);
/*     */     
/*     */     @FormUrlEncoded
/*     */     @POST
/*     */     Call<ResponseBody> getStringResponse(@Url String param1String, @FieldMap Map<String, String> param1Map);
/*     */     
/*     */     @FormUrlEncoded
/*     */     @POST
/*     */     Call<Object> getResponseObj(@Url String param1String, @FieldMap Map<String, Object> param1Map);
/*     */     
/*     */     @GET
/*     */     Call<Object> getResponse(@Url String param1String);
/*     */     
/*     */     @Multipart
/*     */     @POST
/*     */     Call<Object> uploadData(@Url String param1String, @Part MultipartBody.Part param1Part, @PartMap Map<String, RequestBody> param1Map);
/*     */     
/*     */     @Multipart
/*     */     @POST
/*     */     Call<Object> uploadData(@Url String param1String, @Part List<MultipartBody.Part> param1List, @PartMap Map<String, RequestBody> param1Map);
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/rest/RestClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */