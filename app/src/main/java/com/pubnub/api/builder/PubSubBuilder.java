/*    */ package com.pubnub.api.builder;
/*    */ 
/*    */

import com.pubnub.api.managers.SubscriptionManager;

import java.util.ArrayList;
import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PubSubBuilder
/*    */ {
/*    */   private List<String> channelSubscriptions;
/*    */   private List<String> channelGroupSubscriptions;
/*    */   private SubscriptionManager subscriptionManager;
/*    */   
/*    */   public PubSubBuilder(SubscriptionManager subscriptionManagerInstance) {
/* 19 */     this.subscriptionManager = subscriptionManagerInstance;
/* 20 */     this.channelSubscriptions = new ArrayList<>();
/* 21 */     this.channelGroupSubscriptions = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public PubSubBuilder channels(List<String> channel) {
/* 26 */     this.channelSubscriptions.addAll(channel);
/* 27 */     return this;
/*    */   }
/*    */   
/*    */   public PubSubBuilder channelGroups(List<String> channelGroup) {
/* 31 */     this.channelGroupSubscriptions.addAll(channelGroup);
/* 32 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract void execute();
/*    */   
/*    */   protected List<String> getChannelSubscriptions() {
/* 39 */     return this.channelSubscriptions;
/*    */   }
/*    */   
/*    */   protected void setChannelSubscriptions(List<String> channelSubscriptions) {
/* 43 */     this.channelSubscriptions = channelSubscriptions;
/*    */   }
/*    */   
/*    */   protected List<String> getChannelGroupSubscriptions() {
/* 47 */     return this.channelGroupSubscriptions;
/*    */   }
/*    */   
/*    */   protected void setChannelGroupSubscriptions(List<String> channelGroupSubscriptions) {
/* 51 */     this.channelGroupSubscriptions = channelGroupSubscriptions;
/*    */   }
/*    */   
/*    */   protected SubscriptionManager getSubscriptionManager() {
/* 55 */     return this.subscriptionManager;
/*    */   }
/*    */   
/*    */   protected void setSubscriptionManager(SubscriptionManager subscriptionManager) {
/* 59 */     this.subscriptionManager = subscriptionManager;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/builder/PubSubBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */