/*    */ package com.view;
/*    */ 
/*    */ import android.graphics.drawable.Drawable;
/*    */ import android.graphics.drawable.GradientDrawable;
/*    */ import android.view.View;
/*    */ 
/*    */ 
/*    */ public class CreateRoundedView
/*    */ {
/*    */   int a;
/*    */   int b;
/*    */   int c;
/*    */   int d;
/*    */   View e;
/*    */   boolean f = false;
/*    */   
/*    */   public CreateRoundedView(int bgColor, int cornerRadius, int strokeWidth, int strokeColor, View view) {
/* 18 */     this.a = bgColor;
/* 19 */     this.b = cornerRadius;
/* 20 */     this.c = strokeWidth;
/* 21 */     this.d = strokeColor;
/* 22 */     this.e = view;
/*    */     
/* 24 */     buildRoundedView();
/*    */   }
/*    */   
/*    */   public CreateRoundedView(int bgColor, int cornerRadius, int strokeWidth, int strokeColor, View view, boolean isImageView) {
/* 28 */     this.a = bgColor;
/* 29 */     this.b = cornerRadius;
/* 30 */     this.c = strokeWidth;
/* 31 */     this.d = strokeColor;
/* 32 */     this.e = view;
/* 33 */     this.f = isImageView;
/*    */     
/* 35 */     buildRoundedImgView();
/*    */   }
/*    */   
/*    */   public void buildRoundedView() {
/* 39 */     GradientDrawable gradientDrawable = new GradientDrawable();
/* 40 */     gradientDrawable.setColor(this.a);
/* 41 */     gradientDrawable.setCornerRadius(this.b);
/* 42 */     gradientDrawable.setStroke(this.c, this.d);
/* 43 */     this.e.setBackground((Drawable)gradientDrawable);
/*    */   }
/*    */   
/*    */   public void buildRoundedImgView() {
/* 47 */     GradientDrawable gradientDrawable = new GradientDrawable();
/* 48 */     gradientDrawable.setColor(this.a);
/* 49 */     gradientDrawable.setShape(1);
/* 50 */     gradientDrawable.setCornerRadius(this.b);
/* 51 */     gradientDrawable.setStroke(this.c, this.d);
/* 52 */     this.e.setBackground((Drawable)gradientDrawable);
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/CreateRoundedView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */