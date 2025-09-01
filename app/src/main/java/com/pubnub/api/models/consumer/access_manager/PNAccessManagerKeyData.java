/*    */ package com.pubnub.api.models.consumer.access_manager;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PNAccessManagerKeyData
/*    */ {
/*    */   @SerializedName("r")
/*    */   private boolean readEnabled;
/*    */   @SerializedName("w")
/*    */   private boolean writeEnabled;
/*    */   @SerializedName("m")
/*    */   private boolean manageEnabled;
/*    */   
/*    */   public boolean isReadEnabled() {
/* 19 */     return this.readEnabled;
/*    */   }
/*    */ 
/*    */   
/*    */   public PNAccessManagerKeyData setReadEnabled(boolean readEnabled) {
/* 24 */     this.readEnabled = readEnabled;
/* 25 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isWriteEnabled() {
/* 30 */     return this.writeEnabled;
/*    */   }
/*    */ 
/*    */   
/*    */   public PNAccessManagerKeyData setWriteEnabled(boolean writeEnabled) {
/* 35 */     this.writeEnabled = writeEnabled;
/* 36 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isManageEnabled() {
/* 41 */     return this.manageEnabled;
/*    */   }
/*    */ 
/*    */   
/*    */   public PNAccessManagerKeyData setManageEnabled(boolean manageEnabled) {
/* 46 */     this.manageEnabled = manageEnabled;
/* 47 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return "PNAccessManagerKeyData(readEnabled=" + isReadEnabled() + ", writeEnabled=" + isWriteEnabled() + ", manageEnabled=" + isManageEnabled() + ")";
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/access_manager/PNAccessManagerKeyData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */