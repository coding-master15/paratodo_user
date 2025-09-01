/*    */ package com.pubnub.api.managers;
/*    */ 
/*    */ 
/*    */ public class PublishSequenceManager
/*    */ {
/*    */   private int maxSequence;
/*    */   private int nextSequence;
/*    */   
/*    */   public PublishSequenceManager(int providedMaxSequence) {
/* 10 */     this.maxSequence = providedMaxSequence;
/*    */   }
/*    */   
/*    */   public synchronized int getNextSequence() {
/* 14 */     if (this.maxSequence == this.nextSequence) {
/* 15 */       this.nextSequence = 1;
/*    */     } else {
/* 17 */       this.nextSequence++;
/*    */     } 
/*    */     
/* 20 */     return this.nextSequence;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/managers/PublishSequenceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */