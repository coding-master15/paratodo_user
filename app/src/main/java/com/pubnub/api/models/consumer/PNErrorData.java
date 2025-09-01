/*    */ package com.pubnub.api.models.consumer;
/*    */ 
/*    */ public class PNErrorData
/*    */ {
/*    */   private String information;
/*    */   private Exception throwable;
/*    */   
/*    */   public PNErrorData(String information, Exception throwable) {
/*  9 */     this.information = information;
/* 10 */     this.throwable = throwable;
/*    */   }
/*    */   
/*    */   public String getInformation() {
/* 14 */     return this.information;
/*    */   }
/*    */   
/*    */   public Exception getThrowable() {
/* 18 */     return this.throwable;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 23 */     return "PNErrorData(information=" + getInformation() + ", throwable=" + getThrowable() + ")";
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/PNErrorData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */