/*     */ package com.pubnub.api;
/*     */ 
/*     */

import com.google.gson.JsonElement;

import retrofit2.Call;
/*     */ 
/*     */ public class PubNubException
/*     */   extends Exception
/*     */ {
/*   9 */   private String errormsg = "";
/*     */   
/*     */   private PubNubError pubnubError;
/*     */   
/*     */   private JsonElement jso;
/*     */   
/*     */   private String response;
/*     */   
/*     */   private int statusCode;
/*     */   
/*     */   private Call affectedCall;
/*     */   
/*     */   PubNubException(String errormsg, PubNubError pubnubError, JsonElement jso, String response, int statusCode, Call affectedCall) {
/*  22 */     this.errormsg = errormsg;
/*  23 */     this.pubnubError = pubnubError;
/*  24 */     this.jso = jso;
/*  25 */     this.response = response;
/*  26 */     this.statusCode = statusCode;
/*  27 */     this.affectedCall = affectedCall;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PubNubExceptionBuilder builder() {
/*  32 */     return new PubNubExceptionBuilder();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getErrormsg() {
/*  37 */     return this.errormsg;
/*     */   }
/*     */ 
/*     */   
/*     */   public PubNubError getPubnubError() {
/*  42 */     return this.pubnubError;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonElement getJso() {
/*  47 */     return this.jso;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResponse() {
/*  52 */     return this.response;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStatusCode() {
/*  57 */     return this.statusCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PubNubExceptionBuilder
/*     */   {
/*     */     private String errormsg;
/*     */ 
/*     */ 
/*     */     
/*     */     private PubNubError pubnubError;
/*     */ 
/*     */ 
/*     */     
/*     */     private JsonElement jso;
/*     */ 
/*     */     
/*     */     private String response;
/*     */ 
/*     */     
/*     */     private int statusCode;
/*     */ 
/*     */     
/*     */     private Call affectedCall;
/*     */ 
/*     */ 
/*     */     
/*     */     public PubNubExceptionBuilder errormsg(String errormsg) {
/*  87 */       this.errormsg = errormsg;
/*  88 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubExceptionBuilder pubnubError(PubNubError pubnubError) {
/*  93 */       this.pubnubError = pubnubError;
/*  94 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubExceptionBuilder jso(JsonElement jso) {
/*  99 */       this.jso = jso;
/* 100 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubExceptionBuilder response(String response) {
/* 105 */       this.response = response;
/* 106 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubExceptionBuilder statusCode(int statusCode) {
/* 111 */       this.statusCode = statusCode;
/* 112 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubExceptionBuilder affectedCall(Call affectedCall) {
/* 117 */       this.affectedCall = affectedCall;
/* 118 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubException build() {
/* 123 */       return new PubNubException(this.errormsg, this.pubnubError, this.jso, this.response, this.statusCode, this.affectedCall);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 130 */       return "PubNubException.PubNubExceptionBuilder(errormsg=" + this.errormsg + ", pubnubError=" + this.pubnubError + ", jso=" + this.jso + ", response=" + this.response + ", statusCode=" + this.statusCode + ", affectedCall=" + this.affectedCall + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/PubNubException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */