/*    */ package com.pubnub.api.models.consumer.push;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class PNPushListProvisionsResult
/*    */ {
/*    */   private List<String> channels;
/*    */   
/*    */   PNPushListProvisionsResult(List<String> channels) {
/* 11 */     this.channels = channels;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNPushListProvisionsResultBuilder builder() {
/* 16 */     return new PNPushListProvisionsResultBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getChannels() {
/* 21 */     return this.channels;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 28 */     return "PNPushListProvisionsResult(channels=" + getChannels() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNPushListProvisionsResultBuilder
/*    */   {
/*    */     private List<String> channels;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public PNPushListProvisionsResultBuilder channels(List<String> channels) {
/* 43 */       this.channels = channels;
/* 44 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNPushListProvisionsResult build() {
/* 49 */       return new PNPushListProvisionsResult(this.channels);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 56 */       return "PNPushListProvisionsResult.PNPushListProvisionsResultBuilder(channels=" + this.channels + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/push/PNPushListProvisionsResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */