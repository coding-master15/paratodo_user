/*    */ package com;
/*    */ 
/*    */ import android.content.Context;
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
/*    */ public class InfoProvider
/*    */ {
/*    */   Context a;
/*    */   
/*    */   public InfoProvider(Context mContext) {
/* 19 */     this.a = mContext;
/* 20 */     new abcd(mContext);
/*    */   }
/*    */   
/*    */   public String getAppConfigurationStr() {
/* 24 */     return (new abcd(this.a)).a();
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/InfoProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */