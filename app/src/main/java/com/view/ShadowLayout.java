/*     */ package com.view;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.RectF;
/*     */ import android.graphics.drawable.BitmapDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Build;
/*     */ import android.util.AttributeSet;
/*     */ import android.widget.FrameLayout;
/*     */ import com.InfoProvider;
/*     */ import com.paratodo.user.R;
import com.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShadowLayout
/*     */   extends FrameLayout
/*     */ {
/*     */   private int a;
/*     */   private float b;
/*     */   private float c;
/*     */   private float d;
/*     */   private float e;
/*     */   private boolean f = true;
/*     */   private boolean g = false;
/*     */   
/*     */   public ShadowLayout(Context context) {
/*  35 */     super(context);
/*  36 */     a(context, (AttributeSet)null);
/*  37 */     a(context);
/*     */   }
/*     */   
/*     */   public ShadowLayout(Context context, AttributeSet attrs) {
/*  41 */     super(context, attrs);
/*  42 */     a(context, attrs);
/*  43 */     a(context);
/*     */   }
/*     */   
/*     */   public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
/*  47 */     super(context, attrs, defStyleAttr);
/*  48 */     a(context, attrs);
/*  49 */     a(context);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSuggestedMinimumWidth() {
/*  54 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSuggestedMinimumHeight() {
/*  59 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
/*  64 */     super.onSizeChanged(w, h, oldw, oldh);
/*  65 */     if (w > 0 && h > 0 && (getBackground() == null || this.f || this.g)) {
/*  66 */       this.g = false;
/*  67 */       a(w, h);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(Context paramContext) {
/*  72 */     if (!isInEditMode() && Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("No")) {
/*  73 */       new InfoProvider(paramContext);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
/*  79 */     super.onLayout(changed, left, top, right, bottom);
/*  80 */     if (this.g) {
/*  81 */       this.g = false;
/*  82 */       a(right - left, bottom - top);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setInvalidateShadowOnSizeChanged(boolean invalidateShadowOnSizeChanged) {
/*  87 */     this.f = invalidateShadowOnSizeChanged;
/*     */   }
/*     */   
/*     */   public void invalidateShadow() {
/*  91 */     this.g = true;
/*  92 */     requestLayout();
/*  93 */     invalidate();
/*     */   }
/*     */   
/*     */   private void a(Context paramContext, AttributeSet paramAttributeSet) {
/*  97 */     b(paramContext, paramAttributeSet);
/*     */     
/*  99 */     int i = (int)(this.b + Math.abs(this.d));
/* 100 */     int j = (int)(this.b + Math.abs(this.e));
/* 101 */     setPadding(i, j, i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(int paramInt1, int paramInt2) {
/* 106 */     Bitmap bitmap = a(paramInt1, paramInt2, this.c, this.b, this.d, this.e, this.a, 0);
/* 107 */     BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
/* 108 */     if (Build.VERSION.SDK_INT <= 16) {
/* 109 */       setBackgroundDrawable((Drawable)bitmapDrawable);
/*     */     } else {
/* 111 */       setBackground((Drawable)bitmapDrawable);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void b(Context paramContext, AttributeSet paramAttributeSet) {
/* 117 */     TypedArray typedArray = a(paramContext, paramAttributeSet, R.styleable.ShadowLayout);
/* 118 */     if (typedArray == null) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/* 123 */       this.c = typedArray.getDimension(R.styleable.ShadowLayout_sl_cornerRadius, getResources().getDimension(R.dimen.default_corner_radius_prj));
/* 124 */       this.b = typedArray.getDimension(R.styleable.ShadowLayout_sl_shadowRadius, getResources().getDimension(R.dimen.default_shadow_radius_prj));
/* 125 */       this.d = typedArray.getDimension(R.styleable.ShadowLayout_sl_dx, 0.0F);
/* 126 */       this.e = typedArray.getDimension(R.styleable.ShadowLayout_sl_dy, 0.0F);
/* 127 */       this.a = typedArray.getColor(R.styleable.ShadowLayout_sl_shadowColor, getResources().getColor(R.color.default_shadow_color));
/*     */     } finally {
/* 129 */       typedArray.recycle();
/*     */     } 
/*     */   }
/*     */   
/*     */   private TypedArray a(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfint) {
/* 134 */     return paramContext.obtainStyledAttributes(paramAttributeSet, paramArrayOfint, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Bitmap a(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3, int paramInt4) {
/* 140 */     Bitmap bitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ALPHA_8);
/* 141 */     Canvas canvas = new Canvas(bitmap);
/*     */     
/* 143 */     RectF rectF = new RectF(paramFloat2, paramFloat2, paramInt1 - paramFloat2, paramInt2 - paramFloat2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     if (paramFloat4 > 0.0F) {
/* 150 */       rectF.top += paramFloat4;
/* 151 */       rectF.bottom -= paramFloat4;
/* 152 */     } else if (paramFloat4 < 0.0F) {
/* 153 */       rectF.top += Math.abs(paramFloat4);
/* 154 */       rectF.bottom -= Math.abs(paramFloat4);
/*     */     } 
/*     */     
/* 157 */     if (paramFloat3 > 0.0F) {
/* 158 */       rectF.left += paramFloat3;
/* 159 */       rectF.right -= paramFloat3;
/* 160 */     } else if (paramFloat3 < 0.0F) {
/* 161 */       rectF.left += Math.abs(paramFloat3);
/* 162 */       rectF.right -= Math.abs(paramFloat3);
/*     */     } 
/*     */     
/* 165 */     Paint paint = new Paint();
/* 166 */     paint.setAntiAlias(true);
/* 167 */     paint.setColor(paramInt4);
/* 168 */     paint.setStyle(Paint.Style.FILL);
/*     */     
/* 170 */     if (!isInEditMode()) {
/* 171 */       paint.setShadowLayer(paramFloat2, paramFloat3, paramFloat4, paramInt3);
/*     */     }
/*     */     
/* 174 */     canvas.drawRoundRect(rectF, paramFloat1, paramFloat1, paint);
/*     */     
/* 176 */     return bitmap;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/ShadowLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */