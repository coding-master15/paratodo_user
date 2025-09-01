/*    */ package com.pubnub.api.models.server;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HistoryForChannelsItem
/*    */ {
/*    */   private JsonElement message;
/*    */   private Long timetoken;
/*    */   
/*    */   public JsonElement getMessage() {
/* 13 */     return this.message;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMessage(JsonElement message) {
/* 18 */     this.message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   public Long getTimetoken() {
/* 23 */     return this.timetoken;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTimetoken(Long timetoken) {
/* 28 */     this.timetoken = timetoken;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/HistoryForChannelsItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */