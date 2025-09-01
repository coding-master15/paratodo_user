/*    */ package com.pubnub.api.models.server;
/*    */ 
/*    */

import com.google.gson.annotations.SerializedName;

import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SubscribeEnvelope
/*    */ {
/*    */   @SerializedName("m")
/*    */   private List<SubscribeMessage> messages;
/*    */   @SerializedName("t")
/*    */   private SubscribeMetadata metadata;
/*    */   
/*    */   public List<SubscribeMessage> getMessages() {
/* 17 */     return this.messages;
/*    */   }
/*    */ 
/*    */   
/*    */   public SubscribeMetadata getMetadata() {
/* 22 */     return this.metadata;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/SubscribeEnvelope.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */