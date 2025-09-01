/*    */ package com.view;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.res.TypedArray;
/*    */ import android.graphics.Canvas;
/*    */ import android.graphics.DashPathEffect;
/*    */ import android.graphics.Paint;
/*    */ import android.graphics.PathEffect;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.View;

import com.paratodo.user.R;

/*    */
/*    */ 
/*    */ public class DividerView
/*    */   extends View
/*    */ {
/* 17 */   public static int ORIENTATION_HORIZONTAL = 0;
/* 18 */   public static int ORIENTATION_VERTICAL = 1;
/*    */   private Paint a;
/*    */   private int b;
/*    */   
/*    */   public DividerView(Context context, AttributeSet attrs) {
/* 23 */     super(context, attrs);
/*    */     
/*    */     int i, j, k, m;
/*    */     
/* 27 */     TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DividerView, 0, 0);
/*    */     
/*    */     try {
/* 30 */       i = typedArray.getDimensionPixelSize(R.styleable.DividerView_dividerDashGap, 5);
/* 31 */       j = typedArray.getDimensionPixelSize(R.styleable.DividerView_dividerDashLength, 5);
/* 32 */       k = typedArray.getDimensionPixelSize(R.styleable.DividerView_dividerDashThickness, 3);
/* 33 */       m = typedArray.getColor(R.styleable.DividerView_dividerColor, -16777216);
/* 34 */       this.b = typedArray.getInt(R.styleable.DividerView_dividerOrientation, ORIENTATION_HORIZONTAL);
/*    */     } finally {
/* 36 */       typedArray.recycle();
/*    */     } 
/*    */     
/* 39 */     this.a = new Paint();
/* 40 */     this.a.setAntiAlias(true);
/* 41 */     this.a.setColor(m);
/* 42 */     this.a.setStyle(Paint.Style.STROKE);
/* 43 */     this.a.setStrokeWidth(k);
/* 44 */     this.a.setPathEffect((PathEffect)new DashPathEffect(new float[] { j, i }, 0.0F));
/*    */   }
/*    */   
/*    */   public DividerView(Context context) {
/* 48 */     this(context, (AttributeSet)null);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onDraw(Canvas canvas) {
/* 53 */     if (this.b == ORIENTATION_HORIZONTAL) {
/* 54 */       float f = getHeight() * 0.5F;
/* 55 */       canvas.drawLine(0.0F, f, getWidth(), f, this.a);
/*    */     } else {
/* 57 */       float f = getWidth() * 0.5F;
/* 58 */       canvas.drawLine(f, 0.0F, f, getHeight(), this.a);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/DividerView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */