/*    */ package com.pubnub.api.models.consumer.channel_group;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class PNChannelGroupsAllChannelsResult
/*    */ {
/*    */   private List<String> channels;
/*    */   
/*    */   PNChannelGroupsAllChannelsResult(List<String> channels) {
/* 10 */     this.channels = channels;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNChannelGroupsAllChannelsResultBuilder builder() {
/* 15 */     return new PNChannelGroupsAllChannelsResultBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getChannels() {
/* 20 */     return this.channels;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     return "PNChannelGroupsAllChannelsResult(channels=" + getChannels() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNChannelGroupsAllChannelsResultBuilder
/*    */   {
/*    */     private List<String> channels;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public PNChannelGroupsAllChannelsResultBuilder channels(List<String> channels) {
/* 42 */       this.channels = channels;
/* 43 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNChannelGroupsAllChannelsResult build() {
/* 48 */       return new PNChannelGroupsAllChannelsResult(this.channels);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 55 */       return "PNChannelGroupsAllChannelsResult.PNChannelGroupsAllChannelsResultBuilder(channels=" + this.channels + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/channel_group/PNChannelGroupsAllChannelsResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */