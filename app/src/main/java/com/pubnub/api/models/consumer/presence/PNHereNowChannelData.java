/*    */ package com.pubnub.api.models.consumer.presence;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class PNHereNowChannelData
/*    */ {
/*    */   private String channelName;
/*    */   private int occupancy;
/*    */   private List<PNHereNowOccupantData> occupants;
/*    */   
/*    */   PNHereNowChannelData(String channelName, int occupancy, List<PNHereNowOccupantData> occupants) {
/* 13 */     this.channelName = channelName;
/* 14 */     this.occupancy = occupancy;
/* 15 */     this.occupants = occupants;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNHereNowChannelDataBuilder builder() {
/* 20 */     return new PNHereNowChannelDataBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getChannelName() {
/* 25 */     return this.channelName;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getOccupancy() {
/* 30 */     return this.occupancy;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<PNHereNowOccupantData> getOccupants() {
/* 35 */     return this.occupants;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return "PNHereNowChannelData(channelName=" + getChannelName() + ", occupancy=" + getOccupancy() + ", occupants=" + getOccupants() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNHereNowChannelDataBuilder
/*    */   {
/*    */     private String channelName;
/*    */ 
/*    */ 
/*    */     
/*    */     private int occupancy;
/*    */ 
/*    */ 
/*    */     
/*    */     private List<PNHereNowOccupantData> occupants;
/*    */ 
/*    */ 
/*    */     
/*    */     public PNHereNowChannelDataBuilder channelName(String channelName) {
/* 63 */       this.channelName = channelName;
/* 64 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHereNowChannelDataBuilder occupancy(int occupancy) {
/* 69 */       this.occupancy = occupancy;
/* 70 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHereNowChannelDataBuilder occupants(List<PNHereNowOccupantData> occupants) {
/* 75 */       this.occupants = occupants;
/* 76 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNHereNowChannelData build() {
/* 81 */       return new PNHereNowChannelData(this.channelName, this.occupancy, this.occupants);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 88 */       return "PNHereNowChannelData.PNHereNowChannelDataBuilder(channelName=" + this.channelName + ", occupancy=" + this.occupancy + ", occupants=" + this.occupants + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/presence/PNHereNowChannelData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */