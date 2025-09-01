/*     */ package com.pubnub.api;
/*     */ 
/*     */

import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.vendor.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.HttpUrl;
import okhttp3.Request;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PubNubUtil
/*     */ {
/*  26 */   private static final Logger log = Logger.getLogger(PubNubUtil.class.getName());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String joinString(List<String> val, String delim) {
/*  33 */     StringBuilder stringBuilder = new StringBuilder();
/*  34 */     for (String str : val) {
/*  35 */       stringBuilder.append(str);
/*  36 */       stringBuilder.append(",");
/*     */     } 
/*     */     
/*  39 */     return stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
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
/*     */   public static String pamEncode(String stringToEncode) {
/*  52 */     String str = urlEncode(stringToEncode);
/*  53 */     if (str != null)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  62 */       str = str.replace("*", "%2A").replace("!", "%21").replace("'", "%27").replace("(", "%28").replace(")", "%29").replace("[", "%5B").replace("]", "%5D").replace("~", "%7E");
/*     */     }
/*  64 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String urlEncode(String stringToEncode) {
/*     */     try {
/*  76 */       return URLEncoder.encode(stringToEncode, "UTF-8").replace("+", "%20");
/*  77 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/*  78 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String urlDecode(String stringToEncode) {
/*     */     try {
/*  90 */       return URLDecoder.decode(stringToEncode, "UTF-8");
/*  91 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/*  92 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String preparePamArguments(Map<String, String> pamArgs) {
/*  97 */     TreeSet<String> treeSet = new TreeSet<>(pamArgs.keySet());
/*  98 */     String str = "";
/*  99 */     int b = 0;
/*     */     
/* 101 */     for (String str1 : treeSet) {
/* 102 */       if (b == 1) {
/* 103 */         str = str.concat("&");
/*     */       }
/*     */ 
/*     */       
/* 107 */       str = str.concat(str1).concat("=").concat(pamEncode(pamArgs.get(str1)));
/*     */       
/* 109 */       b++;
/*     */     } 
/*     */     
/* 112 */     return str;
/*     */   }
/*     */   
/*     */   public static String signSHA256(String key, String data) throws PubNubException {
/*     */     Mac mac;
/*     */     byte[] arrayOfByte;
/* 118 */     SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(Charset.forName("UTF-8")), "HmacSHA256");
/*     */     
/*     */     try {
/* 121 */       mac = Mac.getInstance("HmacSHA256");
/* 122 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/* 123 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CRYPTO_ERROR).errormsg(noSuchAlgorithmException.getMessage()).build();
/*     */     } 
/*     */     
/*     */     try {
/* 127 */       mac.init(secretKeySpec);
/* 128 */     } catch (InvalidKeyException invalidKeyException) {
/* 129 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CRYPTO_ERROR).errormsg(invalidKeyException.getMessage()).build();
/*     */     } 
/*     */     
/*     */     try {
/* 133 */       arrayOfByte = mac.doFinal(data.getBytes("UTF-8"));
/* 134 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 135 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_CRYPTO_ERROR).errormsg(unsupportedEncodingException.getMessage()).build();
/*     */     } 
/*     */     
/* 138 */     return (new String(Base64.encode(arrayOfByte, 0), Charset.forName("UTF-8"))).replace('+', '-').replace('/', '_').replace("\n", "");
/*     */   }
/*     */   
/*     */   public static String replaceLast(String string, String toReplace, String replacement) {
/* 142 */     int i = string.lastIndexOf(toReplace);
/* 143 */     if (i > -1) {
/* 144 */       return string.substring(0, i).concat(replacement).concat(string.substring(i + toReplace.length(), string.length()));
/*     */     }
/* 146 */     return string;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Request requestSigner(Request originalRequest, PNConfiguration pnConfiguration, int timestamp) {
/* 152 */     if (pnConfiguration.getSecretKey() == null) {
/* 153 */       return originalRequest;
/*     */     }
/*     */     
/* 156 */     HttpUrl httpUrl1 = originalRequest.url();
/* 157 */     String str1 = httpUrl1.encodedPath();
/* 158 */     HashMap<Object, Object> hashMap = new HashMap<>();
/* 159 */     String str2 = "";
/*     */     
/* 161 */     for (String str : httpUrl1.queryParameterNames()) {
/* 162 */       hashMap.put(str, httpUrl1.queryParameter(str));
/*     */     }
/*     */     
/* 165 */     hashMap.put("timestamp", String.valueOf(timestamp));
/*     */     
/* 167 */     String str3 = pnConfiguration.getSubscribeKey() + "\n" + pnConfiguration.getPublishKey() + "\n";
/*     */     
/* 169 */     if (str1.startsWith("/v1/auth/audit")) {
/* 170 */       str3 = str3 + "audit\n";
/* 171 */     } else if (str1.startsWith("/v1/auth/grant")) {
/* 172 */       str3 = str3 + "grant\n";
/*     */     } else {
/* 174 */       str3 = str3 + str1 + "\n";
/*     */     } 
/*     */     
/* 177 */     str3 = str3 + preparePamArguments((Map)hashMap);
/*     */     
/*     */     try {
/* 180 */       str2 = signSHA256(pnConfiguration.getSecretKey(), str3);
/* 181 */     } catch (PubNubException pubNubException) {
/* 182 */       log.warning("signature failed on SignatureInterceptor: " + pubNubException.toString());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     HttpUrl httpUrl2 = httpUrl1.newBuilder().addQueryParameter("timestamp", String.valueOf(timestamp)).addQueryParameter("signature", str2).build();
/*     */     
/* 190 */     return originalRequest.newBuilder().url(httpUrl2).build();
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/PubNubUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */