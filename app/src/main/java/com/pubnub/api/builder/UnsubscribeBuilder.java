/*    */ package com.pubnub.api.builder;
/*    */ 
/*    */

import com.pubnub.api.builder.dto.UnsubscribeOperation;
import com.pubnub.api.managers.SubscriptionManager;
/*    */ 
/*    */ public class UnsubscribeBuilder
/*    */   extends PubSubBuilder
/*    */ {
/*    */   public UnsubscribeBuilder(SubscriptionManager subscriptionManager) {
/* 10 */     super(subscriptionManager);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute() {
/* 18 */     UnsubscribeOperation unsubscribeOperation = UnsubscribeOperation.builder().channels(getChannelSubscriptions()).channelGroups(getChannelGroupSubscriptions()).build();
/*    */     
/* 20 */     getSubscriptionManager().adaptUnsubscribeBuilder(unsubscribeOperation);
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/builder/UnsubscribeBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */