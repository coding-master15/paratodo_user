/*    */ package com.pubnub.api.models.consumer.history;
/*    */ 
/*    */

import com.pubnub.api.models.consumer.pubsub.PNMessageResult;

import java.util.List;
import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class PNFetchMessagesResult
/*    */ {
/*    */   private Map<String, List<PNMessageResult>> channels;
/*    */   
/*    */   PNFetchMessagesResult(Map<String, List<PNMessageResult>> channels) {
/* 13 */     this.channels = channels;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNFetchMessagesResultBuilder builder() {
/* 18 */     return new PNFetchMessagesResultBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, List<PNMessageResult>> getChannels() {
/* 23 */     return this.channels;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 30 */     return "PNFetchMessagesResult(channels=" + getChannels() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNFetchMessagesResultBuilder
/*    */   {
/*    */     private Map<String, List<PNMessageResult>> channels;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public PNFetchMessagesResultBuilder channels(Map<String, List<PNMessageResult>> channels) {
/* 45 */       this.channels = channels;
/* 46 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNFetchMessagesResult build() {
/* 51 */       return new PNFetchMessagesResult(this.channels);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 58 */       return "PNFetchMessagesResult.PNFetchMessagesResultBuilder(channels=" + this.channels + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/history/PNFetchMessagesResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */