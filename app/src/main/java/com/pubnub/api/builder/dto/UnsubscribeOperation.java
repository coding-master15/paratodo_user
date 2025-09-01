/*    */ package com.pubnub.api.builder.dto;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class UnsubscribeOperation
/*    */ {
/*    */   private List<String> channels;
/*    */   private List<String> channelGroups;
/*    */   
/*    */   UnsubscribeOperation(List<String> channels, List<String> channelGroups) {
/* 12 */     this.channels = channels;
/* 13 */     this.channelGroups = channelGroups;
/*    */   }
/*    */ 
/*    */   
/*    */   public static UnsubscribeOperationBuilder builder() {
/* 18 */     return new UnsubscribeOperationBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getChannels() {
/* 23 */     return this.channels;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getChannelGroups() {
/* 28 */     return this.channelGroups;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class UnsubscribeOperationBuilder
/*    */   {
/*    */     private List<String> channels;
/*    */ 
/*    */ 
/*    */     
/*    */     private List<String> channelGroups;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public UnsubscribeOperationBuilder channels(List<String> channels) {
/* 46 */       this.channels = channels;
/* 47 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public UnsubscribeOperationBuilder channelGroups(List<String> channelGroups) {
/* 52 */       this.channelGroups = channelGroups;
/* 53 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public UnsubscribeOperation build() {
/* 58 */       return new UnsubscribeOperation(this.channels, this.channelGroups);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 65 */       return "UnsubscribeOperation.UnsubscribeOperationBuilder(channels=" + this.channels + ", channelGroups=" + this.channelGroups + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/builder/dto/UnsubscribeOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */