/*    */ package com.view;
/*    */ 
/*    */ import android.annotation.TargetApi;
/*    */ import android.content.Context;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.MotionEvent;
/*    */ import android.widget.ScrollView;
/*    */ import com.general.files.OnScrollTouchDelegate;
/*    */ 
/*    */ public class MyScrollView
/*    */   extends ScrollView
/*    */ {
/*    */   private boolean b = true;
/*    */   OnScrollTouchDelegate a;
/*    */   
/*    */   public MyScrollView(Context context) {
/* 17 */     super(context);
/*    */   }
/*    */ 
/*    */   
/*    */   public MyScrollView(Context context, AttributeSet attrs) {
/* 22 */     super(context, attrs);
/*    */   }
/*    */   
/*    */   public void setTouchDelegate(OnScrollTouchDelegate scrollOnTouchPerform) {
/* 26 */     this.a = scrollOnTouchPerform;
/*    */   }
/*    */   
/*    */   public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
/* 30 */     super(context, attrs, defStyleAttr);
/*    */   }
/*    */   
/*    */   @TargetApi(21)
/*    */   public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/* 35 */     super(context, attrs, defStyleAttr, defStyleRes);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onInterceptTouchEvent(MotionEvent ev) {
/* 41 */     if (a()) {
/* 42 */       return super.onInterceptTouchEvent(ev);
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onTouchEvent(MotionEvent ev) {
/* 50 */     if (this.a != null) {
/* 51 */       this.a.onTouchDelegate();
/*    */     }
/* 53 */     if (a()) {
/* 54 */       return super.onTouchEvent(ev);
/*    */     }
/* 56 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean a() {
/* 61 */     return this.b;
/*    */   }
/*    */   
/*    */   public void setScrolling(boolean enableScrolling) {
/* 65 */     this.b = enableScrolling;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/MyScrollView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */