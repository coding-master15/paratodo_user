/*    */ package com.pubnub.api.models.server;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ 
/*    */ public class Envelope<T>
/*    */ {
/*    */   private int status;
/*    */   private String message;
/*    */   private String service;
/*    */   private T payload;
/*    */   private int occupancy;
/*    */   private JsonElement uuids;
/*    */   private String action;
/*    */   private boolean error;
/*    */   
/*    */   public int getStatus() {
/* 17 */     return this.status;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStatus(int status) {
/* 22 */     this.status = status;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 27 */     return this.message;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMessage(String message) {
/* 32 */     this.message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getService() {
/* 37 */     return this.service;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setService(String service) {
/* 42 */     this.service = service;
/*    */   }
/*    */ 
/*    */   
/*    */   public T getPayload() {
/* 47 */     return this.payload;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPayload(T payload) {
/* 52 */     this.payload = payload;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getOccupancy() {
/* 57 */     return this.occupancy;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOccupancy(int occupancy) {
/* 62 */     this.occupancy = occupancy;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement getUuids() {
/* 67 */     return this.uuids;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUuids(JsonElement uuids) {
/* 72 */     this.uuids = uuids;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAction() {
/* 77 */     return this.action;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAction(String action) {
/* 82 */     this.action = action;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isError() {
/* 87 */     return this.error;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setError(boolean error) {
/* 92 */     this.error = error;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/Envelope.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */