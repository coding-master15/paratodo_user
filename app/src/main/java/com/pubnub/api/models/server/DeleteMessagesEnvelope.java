/*    */ package com.pubnub.api.models.server;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ public class DeleteMessagesEnvelope
/*    */ {
/*    */   private Integer status;
/*    */   private boolean error;
/*    */   @SerializedName("error_message")
/*    */   private String errorMessage;
/*    */   
/*    */   public Integer getStatus() {
/* 14 */     return this.status;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStatus(Integer status) {
/* 19 */     this.status = status;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isError() {
/* 24 */     return this.error;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setError(boolean error) {
/* 29 */     this.error = error;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getErrorMessage() {
/* 34 */     return this.errorMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setErrorMessage(String errorMessage) {
/* 39 */     this.errorMessage = errorMessage;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/DeleteMessagesEnvelope.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */