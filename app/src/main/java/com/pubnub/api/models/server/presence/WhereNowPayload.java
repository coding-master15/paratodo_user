/*    */ package com.pubnub.api.models.server.presence;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WhereNowPayload
/*    */ {
/*    */   private List<String> channels;
/*    */   
/*    */   public List<String> getChannels() {
/* 14 */     return this.channels;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setChannels(List<String> channels) {
/* 19 */     this.channels = channels;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 26 */     if (o == this) return true; 
/* 27 */     if (!(o instanceof WhereNowPayload)) return false; 
/* 28 */     WhereNowPayload whereNowPayload = (WhereNowPayload)o;
/* 29 */     if (!whereNowPayload.canEqual(this)) return false; 
/* 30 */     List<String> list1 = getChannels();
/* 31 */     List<String> list2 = whereNowPayload.getChannels();
/* 32 */     if ((list1 == null) ? (list2 != null) : !list1.equals(list2))
/* 33 */       return false; 
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean canEqual(Object other) {
/* 39 */     return other instanceof WhereNowPayload;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 46 */     byte b = 59;
/* 47 */     int i = 1;
/* 48 */     List<String> list = getChannels();
/* 49 */     i = i * 59 + ((list == null) ? 43 : list.hashCode());
/* 50 */     return i;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     return "WhereNowPayload(channels=" + getChannels() + ")";
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/presence/WhereNowPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */