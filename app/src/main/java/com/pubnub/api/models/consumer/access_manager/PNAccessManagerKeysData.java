/*    */ package com.pubnub.api.models.consumer.access_manager;
/*    */ 
/*    */

import com.google.gson.annotations.SerializedName;

import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PNAccessManagerKeysData
/*    */ {
/*    */   @SerializedName("auths")
/*    */   private Map<String, PNAccessManagerKeyData> authKeys;
/*    */   
/*    */   public Map<String, PNAccessManagerKeyData> getAuthKeys() {
/* 14 */     return this.authKeys;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "PNAccessManagerKeysData(authKeys=" + getAuthKeys() + ")";
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/access_manager/PNAccessManagerKeysData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */