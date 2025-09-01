/*    */ package com.pubnub.api.models.server;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ 
/*    */ 
/*    */ public class PresenceEnvelope
/*    */ {
/*    */   private String action;
/*    */   private String uuid;
/*    */   private Integer occupancy;
/*    */   private Long timestamp;
/*    */   private JsonElement data;
/*    */   
/*    */   public String getAction() {
/* 15 */     return this.action;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUuid() {
/* 20 */     return this.uuid;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getOccupancy() {
/* 25 */     return this.occupancy;
/*    */   }
/*    */ 
/*    */   
/*    */   public Long getTimestamp() {
/* 30 */     return this.timestamp;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement getData() {
/* 35 */     return this.data;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/PresenceEnvelope.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */