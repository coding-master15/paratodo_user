/*     */ package com.pubnub.api.managers;
/*     */ 
/*     */

import com.pubnub.api.builder.dto.PresenceOperation;
import com.pubnub.api.builder.dto.StateOperation;
import com.pubnub.api.builder.dto.SubscribeOperation;
import com.pubnub.api.builder.dto.UnsubscribeOperation;
import com.pubnub.api.models.SubscriptionItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StateManager
/*     */ {
/*  39 */   private Map<String, SubscriptionItem> channels = new HashMap<>();
/*  40 */   private Map<String, SubscriptionItem> presenceChannels = new HashMap<>();
/*     */   
/*  42 */   private Map<String, SubscriptionItem> groups = new HashMap<>();
/*  43 */   private Map<String, SubscriptionItem> presenceGroups = new HashMap<>();
/*     */   
/*  45 */   private Map<String, SubscriptionItem> heartbeatChannels = new HashMap<>();
/*  46 */   private Map<String, SubscriptionItem> heartbeatGroups = new HashMap<>();
/*     */ 
/*     */   
/*     */   public synchronized void adaptSubscribeBuilder(SubscribeOperation subscribeOperation) {
/*  50 */     for (String str : subscribeOperation.getChannels()) {
/*  51 */       if (str == null || str.length() == 0) {
/*     */         continue;
/*     */       }
/*     */       
/*  55 */       SubscriptionItem subscriptionItem = (new SubscriptionItem()).setName(str);
/*  56 */       this.channels.put(str, subscriptionItem);
/*     */       
/*  58 */       if (subscribeOperation.isPresenceEnabled()) {
/*  59 */         SubscriptionItem subscriptionItem1 = (new SubscriptionItem()).setName(str);
/*  60 */         this.presenceChannels.put(str, subscriptionItem1);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  65 */     for (String str : subscribeOperation.getChannelGroups()) {
/*  66 */       if (str == null || str.length() == 0) {
/*     */         continue;
/*     */       }
/*     */       
/*  70 */       SubscriptionItem subscriptionItem = (new SubscriptionItem()).setName(str);
/*  71 */       this.groups.put(str, subscriptionItem);
/*     */       
/*  73 */       if (subscribeOperation.isPresenceEnabled()) {
/*  74 */         SubscriptionItem subscriptionItem1 = (new SubscriptionItem()).setName(str);
/*  75 */         this.presenceGroups.put(str, subscriptionItem1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void adaptStateBuilder(StateOperation stateOperation) {
/*  82 */     for (String str : stateOperation.getChannels()) {
/*  83 */       SubscriptionItem subscriptionItem = this.channels.get(str);
/*     */       
/*  85 */       if (subscriptionItem != null) {
/*  86 */         subscriptionItem.setState(stateOperation.getState());
/*     */       }
/*     */     } 
/*     */     
/*  90 */     for (String str : stateOperation.getChannelGroups()) {
/*  91 */       SubscriptionItem subscriptionItem = this.groups.get(str);
/*     */       
/*  93 */       if (subscriptionItem != null) {
/*  94 */         subscriptionItem.setState(stateOperation.getState());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void adaptUnsubscribeBuilder(UnsubscribeOperation unsubscribeOperation) {
/* 101 */     for (String str : unsubscribeOperation.getChannels()) {
/* 102 */       this.channels.remove(str);
/* 103 */       this.presenceChannels.remove(str);
/*     */     } 
/*     */     
/* 106 */     for (String str : unsubscribeOperation.getChannelGroups()) {
/* 107 */       this.groups.remove(str);
/* 108 */       this.presenceGroups.remove(str);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void adaptPresenceBuilder(PresenceOperation presenceOperation) {
/* 113 */     for (String str : presenceOperation.getChannels()) {
/* 114 */       if (str == null || str.length() == 0) {
/*     */         continue;
/*     */       }
/*     */       
/* 118 */       if (presenceOperation.isConnected()) {
/* 119 */         SubscriptionItem subscriptionItem = (new SubscriptionItem()).setName(str);
/* 120 */         this.heartbeatChannels.put(str, subscriptionItem); continue;
/*     */       } 
/* 122 */       this.heartbeatChannels.remove(str);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 127 */     for (String str : presenceOperation.getChannelGroups()) {
/* 128 */       if (str == null || str.length() == 0) {
/*     */         continue;
/*     */       }
/*     */       
/* 132 */       if (presenceOperation.isConnected()) {
/* 133 */         SubscriptionItem subscriptionItem = (new SubscriptionItem()).setName(str);
/* 134 */         this.heartbeatGroups.put(str, subscriptionItem); continue;
/*     */       } 
/* 136 */       this.heartbeatGroups.remove(str);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Map<String, Object> createStatePayload() {
/* 143 */     HashMap<Object, Object> hashMap = new HashMap<>();
/*     */     
/* 145 */     for (SubscriptionItem subscriptionItem : this.channels.values()) {
/* 146 */       if (subscriptionItem.getState() != null) {
/* 147 */         hashMap.put(subscriptionItem.getName(), subscriptionItem.getState());
/*     */       }
/*     */     } 
/*     */     
/* 151 */     for (SubscriptionItem subscriptionItem : this.groups.values()) {
/* 152 */       if (subscriptionItem.getState() != null) {
/* 153 */         hashMap.put(subscriptionItem.getName(), subscriptionItem.getState());
/*     */       }
/*     */     } 
/*     */     
/* 157 */     return (Map)hashMap;
/*     */   }
/*     */   
/*     */   public synchronized List<String> prepareChannelList(boolean includePresence) {
/* 161 */     return prepareMembershipList(this.channels, this.presenceChannels, includePresence);
/*     */   }
/*     */   
/*     */   public synchronized List<String> prepareChannelGroupList(boolean includePresence) {
/* 165 */     return prepareMembershipList(this.groups, this.presenceGroups, includePresence);
/*     */   }
/*     */   
/*     */   public synchronized List<String> prepareHeartbeatChannelList(boolean includePresence) {
/* 169 */     return prepareMembershipList(this.heartbeatChannels, this.presenceChannels, includePresence);
/*     */   }
/*     */   
/*     */   public synchronized List<String> prepareHeartbeatChannelGroupList(boolean includePresence) {
/* 173 */     return prepareMembershipList(this.heartbeatGroups, this.presenceGroups, includePresence);
/*     */   }
/*     */   
/*     */   public synchronized boolean isEmpty() {
/* 177 */     return (this.channels.isEmpty() && this.presenceChannels.isEmpty() && this.groups.isEmpty() && this.presenceGroups.isEmpty());
/*     */   }
/*     */   
/*     */   private synchronized List<String> prepareMembershipList(Map<String, SubscriptionItem> dataStorage, Map<String, SubscriptionItem> presenceStorage, boolean includePresence) {
/* 181 */     ArrayList<String> arrayList = new ArrayList();
/*     */     
/* 183 */     for (SubscriptionItem subscriptionItem : dataStorage.values()) {
/* 184 */       arrayList.add(subscriptionItem.getName());
/*     */     }
/*     */     
/* 187 */     if (includePresence) {
/* 188 */       for (SubscriptionItem subscriptionItem : presenceStorage.values()) {
/* 189 */         arrayList.add(subscriptionItem.getName().concat("-pnpres"));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 194 */     return arrayList;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/managers/StateManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */