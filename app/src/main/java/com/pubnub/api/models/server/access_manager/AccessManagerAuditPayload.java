/*    */ package com.pubnub.api.models.server.access_manager;
/*    */ 
/*    */

import com.google.gson.annotations.SerializedName;
import com.pubnub.api.models.consumer.access_manager.PNAccessManagerKeyData;

import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AccessManagerAuditPayload
/*    */ {
/*    */   @SerializedName("level")
/*    */   private String level;
/*    */   @SerializedName("subscribe_key")
/*    */   private String subscribeKey;
/*    */   @SerializedName("channel")
/*    */   private String channel;
/*    */   @SerializedName("channel-group")
/*    */   private String channelGroup;
/*    */   @SerializedName("auths")
/*    */   private Map<String, PNAccessManagerKeyData> authKeys;
/*    */   
/*    */   public String getLevel() {
/* 27 */     return this.level;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSubscribeKey() {
/* 32 */     return this.subscribeKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getChannel() {
/* 37 */     return this.channel;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getChannelGroup() {
/* 42 */     return this.channelGroup;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, PNAccessManagerKeyData> getAuthKeys() {
/* 47 */     return this.authKeys;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/access_manager/AccessManagerAuditPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */