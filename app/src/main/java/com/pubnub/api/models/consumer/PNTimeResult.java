/*    */ package com.pubnub.api.models.consumer;
/*    */ 
/*    */ public class PNTimeResult
/*    */ {
/*    */   private Long timetoken;
/*    */   
/*    */   PNTimeResult(Long timetoken) {
/*  8 */     this.timetoken = timetoken;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNTimeResultBuilder builder() {
/* 13 */     return new PNTimeResultBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public Long getTimetoken() {
/* 18 */     return this.timetoken;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 25 */     return "PNTimeResult(timetoken=" + getTimetoken() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNTimeResultBuilder
/*    */   {
/*    */     private Long timetoken;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public PNTimeResultBuilder timetoken(Long timetoken) {
/* 40 */       this.timetoken = timetoken;
/* 41 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNTimeResult build() {
/* 46 */       return new PNTimeResult(this.timetoken);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 53 */       return "PNTimeResult.PNTimeResultBuilder(timetoken=" + this.timetoken + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/PNTimeResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */