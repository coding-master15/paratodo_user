/*    */ package com.general.files;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.res.TypedArray;
/*    */ import android.graphics.Canvas;
/*    */ import android.graphics.Rect;
/*    */ import android.graphics.drawable.Drawable;
/*    */ import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DividerItemDecoration
/*    */   extends RecyclerView.ItemDecoration
/*    */ {
/*    */   public static final int HORIZONTAL_LIST = 0;
/*    */   public static final int VERTICAL_LIST = 1;
/* 19 */   private static final int[] a = new int[] { 16843284 };
/*    */   
/*    */   public Drawable mDivider;
/*    */   
/*    */   private int b;
/*    */ 
/*    */   
/*    */   public DividerItemDecoration(Context context, int orientation) {
/* 27 */     TypedArray typedArray = context.obtainStyledAttributes(a);
/* 28 */     this.mDivider = typedArray.getDrawable(0);
/* 29 */     typedArray.recycle();
/* 30 */     setOrientation(orientation);
/*    */   }
/*    */   
/*    */   public void setOrientation(int orientation) {
/* 34 */     if (orientation != 0 && orientation != 1) {
/* 35 */       throw new IllegalArgumentException("invalid orientation");
/*    */     }
/* 37 */     this.b = orientation;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
/* 42 */     if (this.b == 1) {
/* 43 */       drawVertical(c, parent);
/*    */     } else {
/* 45 */       drawHorizontal(c, parent);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void drawVertical(Canvas c, RecyclerView parent) {
/* 50 */     int i = parent.getPaddingLeft();
/* 51 */     int j = parent.getWidth() - parent.getPaddingRight();
/*    */     
/* 53 */     int k = parent.getChildCount();
/* 54 */     for (byte b = 0; b < k; b++) {
/* 55 */       View view = parent.getChildAt(b);
/*    */       
/* 57 */       RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
/* 58 */       int m = view.getBottom() + layoutParams.bottomMargin;
/* 59 */       int n = m + this.mDivider.getIntrinsicHeight();
/* 60 */       this.mDivider.setBounds(0, m, j, n);
/*    */       
/* 62 */       this.mDivider.draw(c);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void drawHorizontal(Canvas c, RecyclerView parent) {
/* 67 */     int i = parent.getPaddingTop();
/* 68 */     int j = parent.getHeight() - parent.getPaddingBottom();
/*    */     
/* 70 */     int k = parent.getChildCount();
/* 71 */     for (byte b = 0; b < k; b++) {
/* 72 */       View view = parent.getChildAt(b);
/*    */       
/* 74 */       RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
/* 75 */       int m = view.getRight() + layoutParams.rightMargin;
/* 76 */       int n = m + this.mDivider.getIntrinsicHeight();
/* 77 */       this.mDivider.setBounds(m, i, n, j);
/* 78 */       this.mDivider.draw(c);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
/* 84 */     if (this.b == 1) {
/* 85 */       outRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
/*    */     } else {
/* 87 */       outRect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfile/classes.jar!/com/general/files/DividerItemDecoration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */