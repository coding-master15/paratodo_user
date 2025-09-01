/*    */ package com.pubnub.api.models.consumer;
/*    */ 
/*    */ public class PNPublishResult {
/*    */   private Long timetoken;
/*    */   
/*    */   PNPublishResult(Long timetoken) {
/*  7 */     this.timetoken = timetoken;
/*    */   }
/*    */   
/*    */   public static PNPublishResultBuilder builder() {
/* 11 */     return new PNPublishResultBuilder();
/*    */   }
/*    */   
/*    */   public Long getTimetoken() {
/* 15 */     return this.timetoken;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "PNPublishResult(timetoken=" + getTimetoken() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNPublishResultBuilder
/*    */   {
/*    */     private Long timetoken;
/*    */ 
/*    */     
/*    */     public PNPublishResultBuilder timetoken(Long timetoken) {
/* 31 */       this.timetoken = timetoken;
/* 32 */       return this;
/*    */     }
/*    */     
/*    */     public PNPublishResult build() {
/* 36 */       return new PNPublishResult(this.timetoken);
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 41 */       return "PNPublishResult.PNPublishResultBuilder(timetoken=" + this.timetoken + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/PNPublishResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */