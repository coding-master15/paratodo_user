/*    */ package com.pubnub.api.models.consumer.history;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class PNHistoryResult
/*    */ {
/*    */   private List<PNHistoryItemResult> messages;
/*    */   private Long startTimetoken;
/*    */   private Long endTimetoken;
/*    */   
/*    */   PNHistoryResult(List<PNHistoryItemResult> messages, Long startTimetoken, Long endTimetoken) {
/* 13 */     this.messages = messages;
/* 14 */     this.startTimetoken = startTimetoken;
/* 15 */     this.endTimetoken = endTimetoken;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNHistoryResultBuilder builder() {
/* 20 */     return new PNHistoryResultBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<PNHistoryItemResult> getMessages() {
/* 25 */     return this.messages;
/*    */   }
/*    */ 
/*    */   
/*    */   public Long getStartTimetoken() {
/* 30 */     return this.startTimetoken;
/*    */   }
/*    */ 
/*    */   
/*    */   public Long getEndTimetoken() {
/* 35 */     return this.endTimetoken;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return "PNHistoryResult(messages=" + getMessages() + ", startTimetoken=" + getStartTimetoken() + ", endTimetoken=" + getEndTimetoken() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNHistoryResultBuilder
/*    */   {
/*    */     private List<PNHistoryItemResult> messages;
/*    */ 
/*    */ 
/*    */     
/*    */     private Long startTimetoken;
/*    */ 
/*    */ 
/*    */     
/*    */     private Long endTimetoken;
/*    */ 
/*    */ 
/*    */     
/*    */     public PNHistoryResultBuilder messages(List<PNHistoryItemResult> messages) {
/* 63 */       this.messages = messages;
/* 64 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHistoryResultBuilder startTimetoken(Long startTimetoken) {
/* 69 */       this.startTimetoken = startTimetoken;
/* 70 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHistoryResultBuilder endTimetoken(Long endTimetoken) {
/* 75 */       this.endTimetoken = endTimetoken;
/* 76 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHistoryResult build() {
/* 81 */       return new PNHistoryResult(this.messages, this.startTimetoken, this.endTimetoken);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 88 */       return "PNHistoryResult.PNHistoryResultBuilder(messages=" + this.messages + ", startTimetoken=" + this.startTimetoken + ", endTimetoken=" + this.endTimetoken + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/history/PNHistoryResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */