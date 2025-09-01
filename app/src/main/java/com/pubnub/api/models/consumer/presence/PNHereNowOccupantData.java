/*    */ package com.pubnub.api.models.consumer.presence;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ 
/*    */ public class PNHereNowOccupantData
/*    */ {
/*    */   private String uuid;
/*    */   private JsonElement state;
/*    */   
/*    */   PNHereNowOccupantData(String uuid, JsonElement state) {
/* 11 */     this.uuid = uuid;
/* 12 */     this.state = state;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNHereNowOccupantDataBuilder builder() {
/* 17 */     return new PNHereNowOccupantDataBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUuid() {
/* 22 */     return this.uuid;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement getState() {
/* 27 */     return this.state;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 34 */     return "PNHereNowOccupantData(uuid=" + getUuid() + ", state=" + getState() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNHereNowOccupantDataBuilder
/*    */   {
/*    */     private String uuid;
/*    */ 
/*    */ 
/*    */     
/*    */     private JsonElement state;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public PNHereNowOccupantDataBuilder uuid(String uuid) {
/* 52 */       this.uuid = uuid;
/* 53 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHereNowOccupantDataBuilder state(JsonElement state) {
/* 58 */       this.state = state;
/* 59 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHereNowOccupantData build() {
/* 64 */       return new PNHereNowOccupantData(this.uuid, this.state);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 71 */       return "PNHereNowOccupantData.PNHereNowOccupantDataBuilder(uuid=" + this.uuid + ", state=" + this.state + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/presence/PNHereNowOccupantData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */