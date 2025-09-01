/*    */ package com.pubnub.api.builder;
/*    */ 
/*    */

import com.pubnub.api.builder.dto.PresenceOperation;
import com.pubnub.api.managers.SubscriptionManager;

import java.util.List;
/*    */ 
/*    */ 
/*    */ public class PresenceBuilder
/*    */   extends PubSubBuilder
/*    */ {
/*    */   private boolean connected;
/*    */   
/*    */   public PresenceBuilder(SubscriptionManager subscriptionManager) {
/* 14 */     super(subscriptionManager);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 22 */     PresenceOperation presenceOperation = PresenceOperation.builder().channels(getChannelSubscriptions()).channelGroups(getChannelGroupSubscriptions()).connected(this.connected).build();
/*    */     
/* 24 */     getSubscriptionManager().adaptPresenceBuilder(presenceOperation);
/*    */   }
/*    */   
/*    */   public PresenceBuilder channels(List<String> channels) {
/* 28 */     return (PresenceBuilder)super.channels(channels);
/*    */   }
/*    */   
/*    */   public PresenceBuilder channelGroups(List<String> channelGroups) {
/* 32 */     return (PresenceBuilder)super.channelGroups(channelGroups);
/*    */   }
/*    */   
/*    */   public PresenceBuilder connected(boolean connected) {
/* 36 */     this.connected = connected;
/* 37 */     return this;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/builder/PresenceBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */