/*    */ package com.utils;
/*    */ 
/*    */

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OnSwipeTouchListener
/*    */   implements View.OnTouchListener
/*    */ {
/*    */   private final GestureDetector a;
/*    */   
/*    */   public OnSwipeTouchListener(Context ctx) {
/* 19 */     this.a = new GestureDetector(ctx, (GestureDetector.OnGestureListener)new a());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onTouch(View v, MotionEvent event) {
/* 24 */     return this.a.onTouchEvent(event);
/*    */   }
/*    */   
/*    */   private final class a extends GestureDetector.SimpleOnGestureListener {
/*    */     private static final int b = 100;
/*    */     private static final int c = 100;
/*    */     
/*    */     private a() {}
/*    */     
/*    */     public boolean onDown(MotionEvent e) {
/* 34 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
/* 39 */       boolean bool = false;
/*    */       try {
/* 41 */         float f1 = e2.getY() - e1.getY();
/* 42 */         float f2 = e2.getX() - e1.getX();
/* 43 */         if (Math.abs(f2) > Math.abs(f1)) {
/* 44 */           if (Math.abs(f2) > 100.0F && Math.abs(velocityX) > 100.0F) {
/* 45 */             if (f2 > 0.0F) {
/* 46 */               onSwipeRight();
/*    */             } else {
/* 48 */               onSwipeLeft();
/*    */             } 
/*    */           }
/* 51 */           bool = true;
/* 52 */         } else if (Math.abs(f1) > 100.0F && Math.abs(velocityY) > 100.0F) {
/* 53 */           if (f1 > 0.0F) {
/* 54 */             onSwipeBottom();
/*    */           } else {
/* 56 */             onSwipeTop();
/*    */           } 
/*    */         } 
/* 59 */         bool = true;
/*    */       }
/* 61 */       catch (Exception exception) {
/* 62 */         exception.printStackTrace();
/*    */       } 
/* 64 */       return bool;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean onSingleTapConfirmed(MotionEvent e) {
/* 69 */       onClick();
/* 70 */       return super.onSingleTapConfirmed(e);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onSwipeRight() {}
/*    */   
/*    */   public void onSwipeLeft() {}
/*    */   
/*    */   public void onSwipeTop() {}
/*    */   
/*    */   public void onSwipeBottom() {}
/*    */   
/*    */   public void onClick() {}
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/utils/OnSwipeTouchListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */