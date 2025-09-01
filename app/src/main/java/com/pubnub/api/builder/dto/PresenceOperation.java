/*    */ package com.pubnub.api.builder.dto;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PresenceOperation
/*    */ {
/*    */   private List<String> channels;
/*    */   private List<String> channelGroups;
/*    */   private boolean connected;
/*    */   
/*    */   PresenceOperation(List<String> channels, List<String> channelGroups, boolean connected) {
/* 14 */     this.channels = channels;
/* 15 */     this.channelGroups = channelGroups;
/* 16 */     this.connected = connected;
/*    */   }
/*    */   
/*    */   public static PresenceOperationBuilder builder() {
/* 20 */     return new PresenceOperationBuilder();
/*    */   }
/*    */   
/*    */   public List<String> getChannels() {
/* 24 */     return this.channels;
/*    */   }
/*    */   
/*    */   public List<String> getChannelGroups() {
/* 28 */     return this.channelGroups;
/*    */   }
/*    */   
/*    */   public boolean isConnected() {
/* 32 */     return this.connected;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PresenceOperationBuilder
/*    */   {
/*    */     private List<String> channels;
/*    */ 
/*    */     
/*    */     private List<String> channelGroups;
/*    */ 
/*    */     
/*    */     private boolean connected;
/*    */ 
/*    */     
/*    */     public PresenceOperationBuilder channels(List<String> channels) {
/* 49 */       this.channels = channels;
/* 50 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PresenceOperationBuilder channelGroups(List<String> channelGroups) {
/* 55 */       this.channelGroups = channelGroups;
/* 56 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PresenceOperationBuilder connected(boolean connected) {
/* 61 */       this.connected = connected;
/* 62 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PresenceOperation build() {
/* 67 */       return new PresenceOperation(this.channels, this.channelGroups, this.connected);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 71 */       return "PresenceOperation.PresenceOperationBuilder(channels=" + this.channels + ", channelGroups=" + this.channelGroups + ", connected=" + this.connected + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/builder/dto/PresenceOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */