/*    */ package com.pubnub.api.models.server;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OriginationMetaData
/*    */ {
/*    */   @SerializedName("t")
/*    */   private Long timetoken;
/*    */   @SerializedName("r")
/*    */   private Integer region;
/*    */   
/*    */   public Long getTimetoken() {
/* 16 */     return this.timetoken;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getRegion() {
/* 21 */     return this.region;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/OriginationMetaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */