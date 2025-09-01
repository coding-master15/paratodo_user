/*    */ package com.view.border;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.res.TypedArray;
/*    */ import android.graphics.drawable.Drawable;
/*    */ import android.util.AttributeSet;
/*    */ import android.widget.FrameLayout;

import com.paratodo.user.R;

/*    */
/*    */ 
/*    */ 
/*    */ public class BorderFrameLayout
/*    */   extends FrameLayout
/*    */ {
/*    */   boolean a = false;
/*    */   boolean b = false;
/*    */   private BorderDrawable c;
/*    */   
/*    */   public BorderFrameLayout(Context context, AttributeSet attrs) {
/* 20 */     super(context, attrs);
/*    */     
/* 22 */     if (this.c == null) {
/* 23 */       this.c = new BorderDrawable();
/*    */     }
/* 25 */     if (attrs != null) {
/* 26 */       TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.BorderFrameLayout);
/*    */ 
/*    */ 
/*    */       
/* 30 */       this.a = typedArray.getBoolean(R.styleable.BorderFrameLayout_allBorderSet, false);
/* 31 */       this.b = typedArray.getBoolean(R.styleable.BorderFrameLayout_allBorderDashed, false);
/* 32 */       if (this.a == true) {
/* 33 */         int i = (int)typedArray.getDimension(R.styleable.BorderFrameLayout_allBorderWidth, 0.0F);
/* 34 */         int j = typedArray.getColor(R.styleable.BorderFrameLayout_allBorderColor, -16777216);
/*    */         
/* 36 */         if (this.b == true) {
/* 37 */           this.c.setDashedBorderAvail(true);
/*    */         }
/*    */         
/* 40 */         this.c.setLeftBorder(i, j);
/* 41 */         this.c.setTopBorder(i, j);
/* 42 */         this.c.setRightBorder(i, j);
/* 43 */         this.c.setBottomBorder(i, j);
/*    */       } else {
/* 45 */         int i = (int)typedArray.getDimension(R.styleable.BorderFrameLayout_leftBorderWidth, 0.0F);
/* 46 */         int j = typedArray.getColor(R.styleable.BorderFrameLayout_leftBorderColor, -16777216);
/* 47 */         this.c.setLeftBorder(i, j);
/*    */ 
/*    */         
/* 50 */         i = (int)typedArray.getDimension(R.styleable.BorderFrameLayout_rightBorderWidth, 0.0F);
/* 51 */         j = typedArray.getColor(R.styleable.BorderFrameLayout_rightBorderColor, -16777216);
/* 52 */         this.c.setRightBorder(i, j);
/*    */ 
/*    */         
/* 55 */         i = (int)typedArray.getDimension(R.styleable.BorderFrameLayout_topBorderWidth, 0.0F);
/* 56 */         j = typedArray.getColor(R.styleable.BorderFrameLayout_topBorderColor, -16777216);
/* 57 */         this.c.setTopBorder(i, j);
/*    */         
/* 59 */         i = (int)typedArray.getDimension(R.styleable.BorderFrameLayout_bottomBorderWidth, 0.0F);
/* 60 */         j = typedArray.getColor(R.styleable.BorderFrameLayout_bottomBorderColor, -16777216);
/* 61 */         this.c.setBottomBorder(i, j);
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 68 */     if (getBackground() != null) {
/* 69 */       this.c.setBackground(this.c);
/*    */     }
/*    */     
/* 72 */     setBackgroundDrawable(this.c);
/*    */   }
/*    */   
/*    */   public BorderFrameLayout(Context context) {
/* 76 */     this(context, (AttributeSet)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBackgroundDrawable(Drawable d) {
/* 81 */     if (d == this.c) {
/* 82 */       super.setBackgroundDrawable(d);
/*    */     } else {
/* 84 */       if (this.c == null)
/* 85 */         this.c = new BorderDrawable(); 
/* 86 */       this.c.setBackground(d);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/border/BorderFrameLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */