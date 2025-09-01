/*    */ package com.pubnub.api.builder.dto;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class StateOperation
/*    */ {
/*    */   private List<String> channels;
/*    */   private List<String> channelGroups;
/*    */   private Object state;
/*    */   
/*    */   StateOperation(List<String> channels, List<String> channelGroups, Object state) {
/* 12 */     this.channels = channels;
/* 13 */     this.channelGroups = channelGroups;
/* 14 */     this.state = state;
/*    */   }
/*    */   
/*    */   public static StateOperationBuilder builder() {
/* 18 */     return new StateOperationBuilder();
/*    */   }
/*    */   
/*    */   public List<String> getChannels() {
/* 22 */     return this.channels;
/*    */   }
/*    */   
/*    */   public List<String> getChannelGroups() {
/* 26 */     return this.channelGroups;
/*    */   }
/*    */   
/*    */   public Object getState() {
/* 30 */     return this.state;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class StateOperationBuilder
/*    */   {
/*    */     private List<String> channels;
/*    */     
/*    */     private List<String> channelGroups;
/*    */     private Object state;
/*    */     
/*    */     public StateOperationBuilder channels(List<String> channels) {
/* 42 */       this.channels = channels;
/* 43 */       return this;
/*    */     }
/*    */     
/*    */     public StateOperationBuilder channelGroups(List<String> channelGroups) {
/* 47 */       this.channelGroups = channelGroups;
/* 48 */       return this;
/*    */     }
/*    */     
/*    */     public StateOperationBuilder state(Object state) {
/* 52 */       this.state = state;
/* 53 */       return this;
/*    */     }
/*    */     
/*    */     public StateOperation build() {
/* 57 */       return new StateOperation(this.channels, this.channelGroups, this.state);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 61 */       return "StateOperation.StateOperationBuilder(channels=" + this.channels + ", channelGroups=" + this.channelGroups + ", state=" + this.state + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/builder/dto/StateOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */