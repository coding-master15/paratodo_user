/*     */ package com.view;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Paint;
/*     */ import android.text.Layout;
/*     */ import android.text.StaticLayout;
/*     */ import android.text.TextPaint;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutoResizeTextView
/*     */   extends AppCompatTextView
/*     */ {
/*     */   public static final float MIN_TEXT_SIZE = 20.0F;
/*     */   private static final String a = "...";
/*     */   private OnTextResizeListener b;
/*     */   private boolean c = false;
/*     */   private float d;
/*  39 */   private float e = 0.0F;
/*     */ 
/*     */   
/*  42 */   private float f = 20.0F;
/*     */ 
/*     */   
/*  45 */   private float g = 1.0F;
/*     */ 
/*     */   
/*  48 */   private float h = 0.0F;
/*     */ 
/*     */   
/*     */   private boolean i = true;
/*     */ 
/*     */   
/*     */   public AutoResizeTextView(Context context) {
/*  55 */     this(context, (AttributeSet)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public AutoResizeTextView(Context context, AttributeSet attrs) {
/*  60 */     this(context, attrs, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public AutoResizeTextView(Context context, AttributeSet attrs, int defStyle) {
/*  65 */     super(context, attrs, defStyle);
/*  66 */     this.d = getTextSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onTextChanged(CharSequence text, int start, int before, int after) {
/*  74 */     this.c = true;
/*     */     
/*  76 */     resetTextSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
/*  84 */     if (w != oldw || h != oldh) {
/*  85 */       this.c = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOnResizeListener(OnTextResizeListener listener) {
/*  95 */     this.b = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextSize(float size) {
/* 103 */     super.setTextSize(size);
/* 104 */     this.d = getTextSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextSize(int unit, float size) {
/* 112 */     super.setTextSize(unit, size);
/* 113 */     this.d = getTextSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineSpacing(float add, float mult) {
/* 121 */     super.setLineSpacing(add, mult);
/* 122 */     this.g = mult;
/* 123 */     this.h = add;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxTextSize(float maxTextSize) {
/* 132 */     this.e = maxTextSize;
/* 133 */     requestLayout();
/* 134 */     invalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxTextSize() {
/* 143 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinTextSize(float minTextSize) {
/* 152 */     this.f = minTextSize;
/* 153 */     requestLayout();
/* 154 */     invalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMinTextSize() {
/* 163 */     return this.f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddEllipsis(boolean addEllipsis) {
/* 172 */     this.i = addEllipsis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAddEllipsis() {
/* 181 */     return this.i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTextSize() {
/* 188 */     if (this.d > 0.0F) {
/* 189 */       super.setTextSize(0, this.d);
/* 190 */       this.e = this.d;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
/* 199 */     if (changed || this.c) {
/* 200 */       int i = right - left - getCompoundPaddingLeft() - getCompoundPaddingRight();
/* 201 */       int j = bottom - top - getCompoundPaddingBottom() - getCompoundPaddingTop();
/* 202 */       resizeText(i, j);
/*     */     } 
/* 204 */     super.onLayout(changed, left, top, right, bottom);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resizeText() {
/* 212 */     int i = getHeight() - getPaddingBottom() - getPaddingTop();
/* 213 */     int j = getWidth() - getPaddingLeft() - getPaddingRight();
/* 214 */     resizeText(j, i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resizeText(int width, int height) {
/* 224 */     CharSequence charSequence = getText();
/*     */     
/* 226 */     if (charSequence == null || charSequence.length() == 0 || height <= 0 || width <= 0 || this.d == 0.0F) {
/*     */       return;
/*     */     }
/*     */     
/* 230 */     if (getTransformationMethod() != null) {
/* 231 */       charSequence = getTransformationMethod().getTransformation(charSequence, (View)this);
/*     */     }
/*     */ 
/*     */     
/* 235 */     TextPaint textPaint = getPaint();
/*     */ 
/*     */     
/* 238 */     float f1 = textPaint.getTextSize();
/*     */     
/* 240 */     float f2 = (this.e > 0.0F) ? Math.min(this.d, this.e) : this.d;
/*     */ 
/*     */     
/* 243 */     int i = a(charSequence, textPaint, width, f2);
/*     */ 
/*     */     
/* 246 */     while (i > height && f2 > this.f) {
/* 247 */       f2 = Math.max(f2 - 2.0F, this.f);
/* 248 */       i = a(charSequence, textPaint, width, f2);
/*     */     } 
/*     */ 
/*     */     
/* 252 */     if (this.i && f2 == this.f && i > height) {
/*     */ 
/*     */       
/* 255 */       TextPaint textPaint1 = new TextPaint((Paint)textPaint);
/*     */       
/* 257 */       StaticLayout staticLayout = new StaticLayout(charSequence, textPaint1, width, Layout.Alignment.ALIGN_NORMAL, this.g, this.h, false);
/*     */       
/* 259 */       if (staticLayout.getLineCount() > 0) {
/*     */ 
/*     */         
/* 262 */         int j = staticLayout.getLineForVertical(height) - 1;
/*     */         
/* 264 */         if (j < 0) {
/* 265 */           setText("");
/*     */         }
/*     */         else {
/*     */           
/* 269 */           int k = staticLayout.getLineStart(j);
/* 270 */           int m = staticLayout.getLineEnd(j);
/* 271 */           float f3 = staticLayout.getLineWidth(j);
/* 272 */           float f4 = textPaint.measureText("...");
/*     */ 
/*     */           
/* 275 */           while (width < f3 + f4) {
/* 276 */             f3 = textPaint.measureText(charSequence.subSequence(k, --m + 1).toString());
/*     */           }
/* 278 */           setText(charSequence.subSequence(0, m) + "...");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 285 */     setTextSize(0, f2);
/* 286 */     setLineSpacing(this.h, this.g);
/*     */ 
/*     */     
/* 289 */     if (this.b != null) {
/* 290 */       this.b.onTextResize((TextView)this, f1, f2);
/*     */     }
/*     */ 
/*     */     
/* 294 */     this.c = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int a(CharSequence paramCharSequence, TextPaint paramTextPaint, int paramInt, float paramFloat) {
/* 302 */     TextPaint textPaint = new TextPaint((Paint)paramTextPaint);
/*     */     
/* 304 */     textPaint.setTextSize(paramFloat);
/*     */     
/* 306 */     StaticLayout staticLayout = new StaticLayout(paramCharSequence, textPaint, paramInt, Layout.Alignment.ALIGN_NORMAL, this.g, this.h, true);
/* 307 */     return staticLayout.getHeight();
/*     */   }
/*     */   
/*     */   public static interface OnTextResizeListener {
/*     */     void onTextResize(TextView param1TextView, float param1Float1, float param1Float2);
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/AutoResizeTextView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */