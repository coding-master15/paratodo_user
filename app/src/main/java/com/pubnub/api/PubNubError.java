/*     */ package com.pubnub.api;
/*     */ 
/*     */ import com.google.gson.JsonElement;
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
/*     */ public class PubNubError
/*     */ {
/*     */   private int errorCode;
/*     */   private int errorCodeExtended;
/*     */   private JsonElement errorObject;
/*     */   private String message;
/*     */   private String errorString;
/*     */   
/*     */   PubNubError(int errorCode, int errorCodeExtended, JsonElement errorObject, String message, String errorString) {
/*  28 */     this.errorCode = errorCode;
/*  29 */     this.errorCodeExtended = errorCodeExtended;
/*  30 */     this.errorObject = errorObject;
/*  31 */     this.message = message;
/*  32 */     this.errorString = errorString;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PubNubErrorBuilder builder() {
/*  37 */     return new PubNubErrorBuilder();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getErrorCode() {
/*  42 */     return this.errorCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getErrorCodeExtended() {
/*  47 */     return this.errorCodeExtended;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonElement getErrorObject() {
/*  52 */     return this.errorObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  61 */     return this.message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorString() {
/*  70 */     return this.errorString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PubNubErrorBuilder
/*     */   {
/*     */     private int errorCode;
/*     */ 
/*     */ 
/*     */     
/*     */     private int errorCodeExtended;
/*     */ 
/*     */ 
/*     */     
/*     */     private JsonElement errorObject;
/*     */ 
/*     */     
/*     */     private String message;
/*     */ 
/*     */     
/*     */     private String errorString;
/*     */ 
/*     */ 
/*     */     
/*     */     public PubNubErrorBuilder errorCode(int errorCode) {
/*  97 */       this.errorCode = errorCode;
/*  98 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubErrorBuilder errorCodeExtended(int errorCodeExtended) {
/* 103 */       this.errorCodeExtended = errorCodeExtended;
/* 104 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubErrorBuilder errorObject(JsonElement errorObject) {
/* 109 */       this.errorObject = errorObject;
/* 110 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubErrorBuilder message(String message) {
/* 115 */       this.message = message;
/* 116 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubErrorBuilder errorString(String errorString) {
/* 121 */       this.errorString = errorString;
/* 122 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PubNubError build() {
/* 127 */       return new PubNubError(this.errorCode, this.errorCodeExtended, this.errorObject, this.message, this.errorString);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 134 */       return "PubNubError.PubNubErrorBuilder(errorCode=" + this.errorCode + ", errorCodeExtended=" + this.errorCodeExtended + ", errorObject=" + this.errorObject + ", message=" + this.message + ", errorString=" + this.errorString + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/PubNubError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */