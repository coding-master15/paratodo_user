/*    */ package com.pubnub.api.models.server.access_manager;
/*    */ 
/*    */

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.pubnub.api.models.consumer.access_manager.PNAccessManagerKeyData;
import com.pubnub.api.models.consumer.access_manager.PNAccessManagerKeysData;

import java.util.Map;
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
/*    */ public class AccessManagerGrantPayload
/*    */ {
/*    */   @SerializedName("level")
/*    */   private String level;
/*    */   private int ttl;
/*    */   @SerializedName("subscribe_key")
/*    */   private String subscribeKey;
/*    */   @SerializedName("channels")
/*    */   private Map<String, PNAccessManagerKeysData> channels;
/*    */   @SerializedName("channel-groups")
/*    */   private JsonElement channelGroups;
/*    */   @SerializedName("auths")
/*    */   private Map<String, PNAccessManagerKeyData> authKeys;
/*    */   @SerializedName("channel")
/*    */   private String channel;
/*    */   
/*    */   public String getLevel() {
/* 35 */     return this.level;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTtl() {
/* 40 */     return this.ttl;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSubscribeKey() {
/* 45 */     return this.subscribeKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, PNAccessManagerKeysData> getChannels() {
/* 50 */     return this.channels;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement getChannelGroups() {
/* 55 */     return this.channelGroups;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, PNAccessManagerKeyData> getAuthKeys() {
/* 60 */     return this.authKeys;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getChannel() {
/* 65 */     return this.channel;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/access_manager/AccessManagerGrantPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */