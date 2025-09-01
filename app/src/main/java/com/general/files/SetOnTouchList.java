/*    */ package com.general.files;
/*    */ 
/*    */

import android.view.MotionEvent;
import android.view.View;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetOnTouchList
/*    */   implements View.OnTouchListener
/*    */ {
/*    */   public boolean onTouch(View view, MotionEvent motionEvent) {
/* 13 */     if (motionEvent.getAction() == 1 && !view.hasFocus()) {
/* 14 */       view.performClick();
/*    */     }
/* 16 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/general/files/SetOnTouchList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */