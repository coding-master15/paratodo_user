/*    */ package com.pubnub.api.models.server;
/*    */ 
/*    */ import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PublishMetaData
/*    */ {
/*    */   @SerializedName("t")
/*    */   private Long publishTimetoken;
/*    */   @SerializedName("r")
/*    */   private Integer region;
/*    */   
/*    */   public Long getPublishTimetoken() {
/* 15 */     return this.publishTimetoken;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getRegion() {
/* 20 */     return this.region;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/PublishMetaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */