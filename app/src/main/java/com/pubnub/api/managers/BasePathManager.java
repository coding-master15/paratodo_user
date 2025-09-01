/*    */ package com.pubnub.api.managers;
/*    */ 
/*    */ import com.pubnub.api.PNConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BasePathManager
/*    */ {
/*    */   private static final int MAX_SUBDOMAIN = 20;
/*    */   private static final String DEFAULT_SUBDOMAIN = "ps";
/*    */   private static final String DEFAULT_BASE_PATH = "pndsn.com";
/*    */   private PNConfiguration config;
/*    */   private int currentSubdomain;
/*    */   
/*    */   public BasePathManager(PNConfiguration initialConfig) {
/* 37 */     this.config = initialConfig;
/* 38 */     this.currentSubdomain = 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getBasePath() {
/* 48 */     StringBuilder stringBuilder = new StringBuilder("http");
/*    */     
/* 50 */     if (this.config.isSecure()) {
/* 51 */       stringBuilder.append("s");
/*    */     }
/*    */     
/* 54 */     stringBuilder.append("://");
/*    */     
/* 56 */     if (this.config.getOrigin() != null) {
/* 57 */       stringBuilder.append(this.config.getOrigin());
/* 58 */     } else if (this.config.isCacheBusting()) {
/* 59 */       stringBuilder.append("ps").append(this.currentSubdomain).append(".").append("pndsn.com");
/*    */       
/* 61 */       if (this.currentSubdomain == 20) {
/* 62 */         this.currentSubdomain = 1;
/*    */       } else {
/* 64 */         this.currentSubdomain++;
/*    */       } 
/*    */     } else {
/*    */       
/* 68 */       stringBuilder.append("ps").append(".").append("pndsn.com");
/*    */     } 
/*    */     
/* 71 */     return stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/managers/BasePathManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */