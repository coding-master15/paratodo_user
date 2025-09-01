/*    */ package com.pubnub.api.models.server;
/*    */ 
/*    */

import java.util.List;
import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class FetchMessagesEnvelope
/*    */ {
/*    */   private Map<String, List<HistoryForChannelsItem>> channels;
/*    */   
/*    */   public Map<String, List<HistoryForChannelsItem>> getChannels() {
/* 12 */     return this.channels;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setChannels(Map<String, List<HistoryForChannelsItem>> channels) {
/* 17 */     this.channels = channels;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/server/FetchMessagesEnvelope.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */