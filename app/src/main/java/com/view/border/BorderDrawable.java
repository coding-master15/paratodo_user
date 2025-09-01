/*     */ package com.view.border;
/*     */ 
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.ColorFilter;
/*     */ import android.graphics.DashPathEffect;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.PathEffect;
/*     */ import android.graphics.Rect;
/*     */ import android.graphics.drawable.Drawable;
/*     */ 
/*     */ public class BorderDrawable
/*     */   extends Drawable
/*     */ {
/*     */   public boolean setDashedAllBorder = false;
/*  16 */   private int a = 0;
/*  17 */   private int b = 0;
/*  18 */   private int c = -16777216;
/*  19 */   private int d = -16777216;
/*  20 */   private int e = 0;
/*  21 */   private int f = 0;
/*  22 */   private int g = -16777216;
/*  23 */   private int h = -16777216;
/*     */   private Paint i;
/*     */   private Rect j;
/*     */   private Path k;
/*     */   private Rect l;
/*     */   private Drawable m;
/*     */   
/*     */   public BorderDrawable(Drawable background) {
/*  31 */     this.i = new Paint();
/*  32 */     this.j = new Rect();
/*  33 */     this.k = new Path();
/*  34 */     this.m = background;
/*     */   }
/*     */   
/*     */   public BorderDrawable() {
/*  38 */     this((Drawable)null);
/*     */   }
/*     */   
/*     */   public Drawable getBackground() {
/*  42 */     return this.m;
/*     */   }
/*     */   
/*     */   public void setBackground(Drawable background) {
/*  46 */     this.m = background;
/*     */   }
/*     */   
/*     */   public void setDashedBorderAvail(boolean value) {
/*  50 */     this.setDashedAllBorder = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(Canvas canvas) {
/*  56 */     this.j = copyBounds();
/*     */     
/*  58 */     this.l = copyBounds();
/*     */     
/*  60 */     this.l.left += this.a;
/*  61 */     this.l.right -= this.b;
/*  62 */     this.l.top += this.e;
/*  63 */     this.l.bottom -= this.f;
/*     */     
/*  65 */     setBounds(this.l);
/*     */     
/*  67 */     if (this.m != null) {
/*  68 */       this.m.setBounds(this.l);
/*  69 */       this.m.draw(canvas);
/*     */     } 
/*     */     
/*  72 */     setBounds(this.j);
/*     */     
/*  74 */     if (this.setDashedAllBorder == true) {
/*     */       
/*  76 */       this.i.setStrokeWidth(1.0F);
/*  77 */       this.i.setAntiAlias(true);
/*  78 */       this.i.setStyle(Paint.Style.STROKE);
/*  79 */       this.i.setPathEffect((PathEffect)new DashPathEffect(new float[] { 40.0F, 40.0F }, 0.0F));
/*     */     } else {
/*  81 */       this.i.setPathEffect(null);
/*  82 */       this.i.setAntiAlias(true);
/*  83 */       this.i.setStrokeWidth(0.0F);
/*  84 */       this.i.setStyle(Paint.Style.FILL_AND_STROKE);
/*     */     } 
/*     */     
/*  87 */     canvas.save();
/*     */     
/*  89 */     if (this.a > 0) {
/*  90 */       this.k.reset();
/*  91 */       this.i.setColor(this.c);
/*  92 */       this.k.moveTo(this.j.left, this.j.top);
/*  93 */       this.k.lineTo((this.j.left + this.a), (this.j.top + this.e));
/*  94 */       this.k.lineTo((this.j.left + this.a), (this.j.bottom - this.f));
/*  95 */       this.k.lineTo(this.j.left, this.j.bottom);
/*  96 */       this.k.close();
/*  97 */       canvas.drawPath(this.k, this.i);
/*     */     } 
/*     */     
/* 100 */     if (this.b > 0) {
/* 101 */       this.k.reset();
/* 102 */       this.i.setColor(this.d);
/* 103 */       this.k.moveTo(this.j.right, this.j.top);
/* 104 */       this.k.lineTo((this.j.right - this.b), (this.j.top + this.e));
/* 105 */       this.k.lineTo((this.j.right - this.b), (this.j.bottom - this.f));
/* 106 */       this.k.lineTo(this.j.right, this.j.bottom);
/* 107 */       this.k.close();
/* 108 */       canvas.drawPath(this.k, this.i);
/*     */     } 
/*     */     
/* 111 */     if (this.e > 0) {
/* 112 */       this.k.reset();
/* 113 */       this.i.setColor(this.g);
/* 114 */       this.k.moveTo(this.j.left, this.j.top);
/* 115 */       this.k.lineTo((this.j.left + this.a), (this.j.top + this.e));
/* 116 */       this.k.lineTo((this.j.right - this.b), (this.j.top + this.e));
/* 117 */       this.k.lineTo(this.j.right, this.j.top);
/* 118 */       this.k.close();
/* 119 */       canvas.drawPath(this.k, this.i);
/*     */     } 
/*     */     
/* 122 */     if (this.f > 0) {
/* 123 */       this.k.reset();
/* 124 */       this.i.setColor(this.h);
/* 125 */       this.k.moveTo(this.j.left, this.j.bottom);
/* 126 */       this.k.lineTo((this.j.left + this.a), (this.j.bottom - this.f));
/* 127 */       this.k.lineTo((this.j.right - this.b), (this.j.bottom - this.f));
/* 128 */       this.k.lineTo(this.j.right, this.j.bottom);
/* 129 */       this.k.close();
/* 130 */       canvas.drawPath(this.k, this.i);
/*     */     } 
/*     */     
/* 133 */     canvas.restore();
/*     */   }
/*     */   
/*     */   public int getLeftBorderWidth() {
/* 137 */     return this.a;
/*     */   }
/*     */   
/*     */   public void setLeftBorderWidth(int mLeftBorderWidth) {
/* 141 */     this.a = mLeftBorderWidth;
/*     */   }
/*     */   
/*     */   public int getRightBorderWidth() {
/* 145 */     return this.b;
/*     */   }
/*     */   
/*     */   public void setRightBorderWidth(int mRightBorderWidth) {
/* 149 */     this.b = mRightBorderWidth;
/*     */   }
/*     */   
/*     */   public int getLeftBorderColor() {
/* 153 */     return this.c;
/*     */   }
/*     */   
/*     */   public void setLeftBorderColor(int mLeftBorderColor) {
/* 157 */     this.c = mLeftBorderColor;
/*     */   }
/*     */   
/*     */   public int getRightBorderColor() {
/* 161 */     return this.d;
/*     */   }
/*     */   
/*     */   public void setRightBorderColor(int mRightBorderColor) {
/* 165 */     this.d = mRightBorderColor;
/*     */   }
/*     */   
/*     */   public int getTopBorderWidth() {
/* 169 */     return this.e;
/*     */   }
/*     */   
/*     */   public void setTopBorderWidth(int mTopBorderWidth) {
/* 173 */     this.e = mTopBorderWidth;
/*     */   }
/*     */   
/*     */   public int getBottomBorderWidth() {
/* 177 */     return this.f;
/*     */   }
/*     */   
/*     */   public void setBottomBorderWidth(int mBottomBorderWidth) {
/* 181 */     this.f = mBottomBorderWidth;
/*     */   }
/*     */   
/*     */   public int getTopBorderColor() {
/* 185 */     return this.g;
/*     */   }
/*     */   
/*     */   public void setTopBorderColor(int mTopBorderColor) {
/* 189 */     this.g = mTopBorderColor;
/*     */   }
/*     */   
/*     */   public int getBottomBorderColor() {
/* 193 */     return this.h;
/*     */   }
/*     */   
/*     */   public void setBottomBorderColor(int mBottomBorderColor) {
/* 197 */     this.h = mBottomBorderColor;
/*     */   }
/*     */   
/*     */   public void setLeftBorder(int width, int color) {
/* 201 */     setLeftBorderWidth(width);
/* 202 */     setLeftBorderColor(color);
/*     */   }
/*     */   
/*     */   public void setTopBorder(int width, int color) {
/* 206 */     setTopBorderWidth(width);
/* 207 */     setTopBorderColor(color);
/*     */   }
/*     */   
/*     */   public void setRightBorder(int width, int color) {
/* 211 */     setRightBorderWidth(width);
/* 212 */     setRightBorderColor(color);
/*     */   }
/*     */   
/*     */   public void setBottomBorder(int width, int color) {
/* 216 */     setBottomBorderWidth(width);
/* 217 */     setBottomBorderColor(color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlpha(int alpha) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorFilter(ColorFilter cf) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOpacity() {
/* 235 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/border/BorderDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */