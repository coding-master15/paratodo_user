/*    */ package com.pubnub.api.models.consumer.history;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PNHistoryItemResult
/*    */ {
/*    */   private Long timetoken;
/*    */   private JsonElement entry;
/*    */   
/*    */   PNHistoryItemResult(Long timetoken, JsonElement entry) {
/* 13 */     this.timetoken = timetoken;
/* 14 */     this.entry = entry;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNHistoryItemResultBuilder builder() {
/* 19 */     return new PNHistoryItemResultBuilder();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return "PNHistoryItemResult(timetoken=" + getTimetoken() + ", entry=" + getEntry() + ")";
/*    */   }
/*    */ 
/*    */   
/*    */   public Long getTimetoken() {
/* 31 */     return this.timetoken;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement getEntry() {
/* 36 */     return this.entry;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNHistoryItemResultBuilder
/*    */   {
/*    */     private Long timetoken;
/*    */ 
/*    */ 
/*    */     
/*    */     private JsonElement entry;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public PNHistoryItemResultBuilder timetoken(Long timetoken) {
/* 54 */       this.timetoken = timetoken;
/* 55 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHistoryItemResultBuilder entry(JsonElement entry) {
/* 60 */       this.entry = entry;
/* 61 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHistoryItemResult build() {
/* 66 */       return new PNHistoryItemResult(this.timetoken, this.entry);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 73 */       return "PNHistoryItemResult.PNHistoryItemResultBuilder(timetoken=" + this.timetoken + ", entry=" + this.entry + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/history/PNHistoryItemResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */