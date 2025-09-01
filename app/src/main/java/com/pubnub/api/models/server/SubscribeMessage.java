/*    */ package com.pubnub.api.models.server;
/*    */ 
/*    */

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SubscribeMessage
/*    */ {
/*    */   @SerializedName("a")
/*    */   private String shard;
/*    */   @SerializedName("b")
/*    */   private String subscriptionMatch;
/*    */   @SerializedName("c")
/*    */   private String channel;
/*    */   @SerializedName("d")
/*    */   private JsonElement payload;
/*    */   @SerializedName("f")
/*    */   private String flags;
/*    */   @SerializedName("i")
/*    */   private String issuingClientId;
/*    */   @SerializedName("k")
/*    */   private String subscribeKey;
/*    */   @SerializedName("o")
/*    */   private OriginationMetaData originationMetadata;
/*    */   @SerializedName("p")
/*    */   private PublishMetaData publishMetaData;
/*    */   @SerializedName("u")
/*    */   private JsonElement userMetadata;
/*    */   
/*    */   public String getShard() {
/* 50 */     return this.shard;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSubscriptionMatch() {
/* 55 */     return this.subscriptionMatch;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getChannel() {
/* 60 */     return this.channel;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement getPayload() {
/* 65 */     return this.payload;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFlags() {
/* 70 */     return this.flags;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getIssuingClientId() {
/* 75 */     return this.issuingClientId;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSubscribeKey() {
/* 80 */     return this.subscribeKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public OriginationMetaData getOriginationMetadata() {
/* 85 */     return this.originationMetadata;
/*    */   }
/*    */ 
/*    */   
/*    */   public PublishMetaData getPublishMetaData() {
/* 90 */     return this.publishMetaData;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement getUserMetadata() {
/* 95 */     return this.userMetadata;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/SubscribeMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */