/*    */ package com.pubnub.api.models.consumer.presence;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class PNWhereNowResult
/*    */ {
/*    */   private List<String> channels;
/*    */   
/*    */   PNWhereNowResult(List<String> channels) {
/* 10 */     this.channels = channels;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNWhereNowResultBuilder builder() {
/* 15 */     return new PNWhereNowResultBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getChannels() {
/* 20 */     return this.channels;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     return "PNWhereNowResult(channels=" + getChannels() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNWhereNowResultBuilder
/*    */   {
/*    */     private List<String> channels;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public PNWhereNowResultBuilder channels(List<String> channels) {
/* 42 */       this.channels = channels;
/* 43 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNWhereNowResult build() {
/* 48 */       return new PNWhereNowResult(this.channels);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 55 */       return "PNWhereNowResult.PNWhereNowResultBuilder(channels=" + this.channels + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/presence/PNWhereNowResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */