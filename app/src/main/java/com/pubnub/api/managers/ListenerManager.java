/*    */ package com.pubnub.api.managers;
/*    */ 
/*    */

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.util.ArrayList;
import java.util.List;
/*    */ 
/*    */ 
/*    */ public class ListenerManager
/*    */ {
/*    */   private final List<SubscribeCallback> listeners;
/*    */   private final PubNub pubnub;
/*    */   
/*    */   public ListenerManager(PubNub pubnubInstance) {
/* 18 */     this.listeners = new ArrayList<>();
/* 19 */     this.pubnub = pubnubInstance;
/*    */   }
/*    */   
/*    */   public void addListener(SubscribeCallback listener) {
/* 23 */     synchronized (this.listeners) {
/* 24 */       this.listeners.add(listener);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void removeListener(SubscribeCallback listener) {
/* 29 */     synchronized (this.listeners) {
/* 30 */       this.listeners.remove(listener);
/*    */     } 
/*    */   }
/*    */   
/*    */   private List<SubscribeCallback> getListeners() {
/* 35 */     ArrayList<SubscribeCallback> arrayList = new ArrayList();
/* 36 */     synchronized (this.listeners) {
/* 37 */       arrayList.addAll(this.listeners);
/*    */     } 
/* 39 */     return arrayList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void announce(PNStatus status) {
/* 48 */     for (SubscribeCallback subscribeCallback : getListeners()) {
/* 49 */       subscribeCallback.status(this.pubnub, status);
/*    */     }
/*    */   }
/*    */   
/*    */   public void announce(PNMessageResult message) {
/* 54 */     for (SubscribeCallback subscribeCallback : getListeners()) {
/* 55 */       subscribeCallback.message(this.pubnub, message);
/*    */     }
/*    */   }
/*    */   
/*    */   public void announce(PNPresenceEventResult presence) {
/* 60 */     for (SubscribeCallback subscribeCallback : getListeners())
/* 61 */       subscribeCallback.presence(this.pubnub, presence); 
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/managers/ListenerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */