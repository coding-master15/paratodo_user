/*    */ package com.pubnub.api.models.consumer.presence;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ 
/*    */ public class PNSetStateResult
/*    */ {
/*    */   private JsonElement state;
/*    */   
/*    */   PNSetStateResult(JsonElement state) {
/* 10 */     this.state = state;
/*    */   }
/*    */   
/*    */   public static PNSetStateResultBuilder builder() {
/* 14 */     return new PNSetStateResultBuilder();
/*    */   }
/*    */   
/*    */   public JsonElement getState() {
/* 18 */     return this.state;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 23 */     return "PNSetStateResult(state=" + getState() + ")";
/*    */   }
/*    */ 
/*    */   
/*    */   public static class PNSetStateResultBuilder
/*    */   {
/*    */     private JsonElement state;
/*    */ 
/*    */     
/*    */     public PNSetStateResultBuilder state(JsonElement state) {
/* 33 */       this.state = state;
/* 34 */       return this;
/*    */     }
/*    */     
/*    */     public PNSetStateResult build() {
/* 38 */       return new PNSetStateResult(this.state);
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 43 */       return "PNSetStateResult.PNSetStateResultBuilder(state=" + this.state + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/presence/PNSetStateResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */