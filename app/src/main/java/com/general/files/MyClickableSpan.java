/*    */ package com.general.files;
/*    */ 
/*    */

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;

/*    */
/*    */ 
/*    */ public abstract class MyClickableSpan
/*    */   extends ClickableSpan
/*    */ {
/*    */   Context e;
/*    */   int f;
/*    */   int g;
/*    */   
/*    */   public MyClickableSpan(Context mContext, int color, int dimension) {
/* 16 */     this.e = mContext;
/* 17 */     this.f = color;
/* 18 */     this.g = dimension;
/*    */   }
/*    */   
/*    */   public void updateDrawState(TextPaint ds) {
/* 22 */     ds.setColor(this.e.getResources().getColor(this.f));
/* 23 */     ds.setTextSize(this.e.getResources().getDimension(this.g));
/* 24 */     ds.setUnderlineText(false);
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/general/files/MyClickableSpan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */