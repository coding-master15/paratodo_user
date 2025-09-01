/*    */ package com.pubnub.api.models.consumer.channel_group;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class PNChannelGroupsListAllResult
/*    */ {
/*    */   private List<String> groups;
/*    */   
/*    */   PNChannelGroupsListAllResult(List<String> groups) {
/* 10 */     this.groups = groups;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PNChannelGroupsListAllResultBuilder builder() {
/* 15 */     return new PNChannelGroupsListAllResultBuilder();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> getGroups() {
/* 20 */     return this.groups;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     return "PNChannelGroupsListAllResult(groups=" + getGroups() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class PNChannelGroupsListAllResultBuilder
/*    */   {
/*    */     private List<String> groups;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public PNChannelGroupsListAllResultBuilder groups(List<String> groups) {
/* 42 */       this.groups = groups;
/* 43 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public PNChannelGroupsListAllResult build() {
/* 48 */       return new PNChannelGroupsListAllResult(this.groups);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 55 */       return "PNChannelGroupsListAllResult.PNChannelGroupsListAllResultBuilder(groups=" + this.groups + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/channel_group/PNChannelGroupsListAllResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */