/*    */ package com.pubnub.api.models.consumer.presence;
/*    */ 
/*    */

import com.google.gson.JsonElement;

import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PNGetStateResult
/*    */ {
/*    */   private Map<String, JsonElement> stateByUUID;
/*    */   
/*    */   PNGetStateResult(Map<String, JsonElement> stateByUUID) {
/* 13 */     this.stateByUUID = stateByUUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNGetStateResultBuilder builder() {
/* 18 */     return new PNGetStateResultBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, JsonElement> getStateByUUID() {
/* 23 */     return this.stateByUUID;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 30 */     return "PNGetStateResult(stateByUUID=" + getStateByUUID() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNGetStateResultBuilder
/*    */   {
/*    */     private Map<String, JsonElement> stateByUUID;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public PNGetStateResultBuilder stateByUUID(Map<String, JsonElement> stateByUUID) {
/* 45 */       this.stateByUUID = stateByUUID;
/* 46 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNGetStateResult build() {
/* 51 */       return new PNGetStateResult(this.stateByUUID);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 58 */       return "PNGetStateResult.PNGetStateResultBuilder(stateByUUID=" + this.stateByUUID + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/presence/PNGetStateResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */