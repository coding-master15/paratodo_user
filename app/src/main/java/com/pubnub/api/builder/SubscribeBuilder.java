/*    */ package com.pubnub.api.builder;
/*    */ 
/*    */

import com.pubnub.api.builder.dto.SubscribeOperation;
import com.pubnub.api.managers.SubscriptionManager;

import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SubscribeBuilder
/*    */   extends PubSubBuilder
/*    */ {
/*    */   private boolean presenceEnabled;
/*    */   private Long timetoken;
/*    */   
/*    */   public SubscribeBuilder(SubscriptionManager subscriptionManager) {
/* 24 */     super(subscriptionManager);
/*    */   }
/*    */   
/*    */   public SubscribeBuilder withPresence() {
/* 28 */     this.presenceEnabled = true;
/* 29 */     return this;
/*    */   }
/*    */   
/*    */   public SubscribeBuilder withTimetoken(Long timetokenInstance) {
/* 33 */     this.timetoken = timetokenInstance;
/* 34 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 43 */     SubscribeOperation subscribeOperation = SubscribeOperation.builder().channels(getChannelSubscriptions()).channelGroups(getChannelGroupSubscriptions()).timetoken(this.timetoken).presenceEnabled(this.presenceEnabled).build();
/*    */     
/* 45 */     getSubscriptionManager().adaptSubscribeBuilder(subscribeOperation);
/*    */   }
/*    */   
/*    */   public SubscribeBuilder channels(List<String> channels) {
/* 49 */     return (SubscribeBuilder)super.channels(channels);
/*    */   }
/*    */   
/*    */   public SubscribeBuilder channelGroups(List<String> channelGroups) {
/* 53 */     return (SubscribeBuilder)super.channelGroups(channelGroups);
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/builder/SubscribeBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */