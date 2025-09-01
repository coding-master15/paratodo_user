/*    */ package com.pubnub.api.models.consumer.presence;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PNHereNowResult
/*    */ {
/*    */   private int totalChannels;
/*    */   private int totalOccupancy;
/*    */   private Map<String, PNHereNowChannelData> channels;
/*    */   
/*    */   PNHereNowResult(int totalChannels, int totalOccupancy, Map<String, PNHereNowChannelData> channels) {
/* 12 */     this.totalChannels = totalChannels;
/* 13 */     this.totalOccupancy = totalOccupancy;
/* 14 */     this.channels = channels;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNHereNowResultBuilder builder() {
/* 19 */     return new PNHereNowResultBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTotalChannels() {
/* 24 */     return this.totalChannels;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTotalOccupancy() {
/* 29 */     return this.totalOccupancy;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, PNHereNowChannelData> getChannels() {
/* 34 */     return this.channels;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return "PNHereNowResult(totalChannels=" + getTotalChannels() + ", totalOccupancy=" + getTotalOccupancy() + ", channels=" + getChannels() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNHereNowResultBuilder
/*    */   {
/*    */     private int totalChannels;
/*    */ 
/*    */ 
/*    */     
/*    */     private int totalOccupancy;
/*    */ 
/*    */ 
/*    */     
/*    */     private Map<String, PNHereNowChannelData> channels;
/*    */ 
/*    */ 
/*    */     
/*    */     public PNHereNowResultBuilder totalChannels(int totalChannels) {
/* 62 */       this.totalChannels = totalChannels;
/* 63 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHereNowResultBuilder totalOccupancy(int totalOccupancy) {
/* 68 */       this.totalOccupancy = totalOccupancy;
/* 69 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHereNowResultBuilder channels(Map<String, PNHereNowChannelData> channels) {
/* 74 */       this.channels = channels;
/* 75 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHereNowResult build() {
/* 80 */       return new PNHereNowResult(this.totalChannels, this.totalOccupancy, this.channels);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 87 */       return "PNHereNowResult.PNHereNowResultBuilder(totalChannels=" + this.totalChannels + ", totalOccupancy=" + this.totalOccupancy + ", channels=" + this.channels + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/presence/PNHereNowResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */