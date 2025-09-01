/*    */ package com.pubnub.api.models.server;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SubscribeMetadata
/*    */ {
/*    */   @SerializedName("t")
/*    */   private Long timetoken;
/*    */   @SerializedName("r")
/*    */   private String region;
/*    */   
/*    */   public Long getTimetoken() {
/* 15 */     return this.timetoken;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRegion() {
/* 20 */     return this.region;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/SubscribeMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */