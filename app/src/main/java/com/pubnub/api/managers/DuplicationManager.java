/*    */ package com.pubnub.api.managers;
/*    */ 
/*    */

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.models.server.SubscribeMessage;

import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class DuplicationManager
/*    */ {
/*    */   private ArrayList<String> hashHistory;
/*    */   private PNConfiguration pnConfiguration;
/*    */   
/*    */   public DuplicationManager(PNConfiguration pnc) {
/* 14 */     this.hashHistory = new ArrayList<>();
/* 15 */     this.pnConfiguration = pnc;
/*    */   }
/*    */   
/*    */   private String getKey(SubscribeMessage message) {
/* 19 */     return message.getPublishMetaData().getPublishTimetoken().toString().concat("-").concat(Integer.toString(message.getPayload().hashCode()));
/*    */   }
/*    */   
/*    */   public boolean isDuplicate(SubscribeMessage message) {
/* 23 */     return this.hashHistory.contains(getKey(message));
/*    */   }
/*    */   
/*    */   public void addEntry(SubscribeMessage message) {
/* 27 */     if (this.hashHistory.size() >= this.pnConfiguration.getMaximumMessagesCacheSize().intValue()) {
/* 28 */       this.hashHistory.remove(0);
/*    */     }
/*    */     
/* 31 */     this.hashHistory.add(getKey(message));
/*    */   }
/*    */   
/*    */   public void clearHistory() {
/* 35 */     this.hashHistory.clear();
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/managers/DuplicationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */