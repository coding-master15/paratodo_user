/*     */ package com.view;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapShader;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Shader;
/*     */ import android.graphics.drawable.BitmapDrawable;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ import androidx.appcompat.widget.AppCompatImageView;

import com.InfoProvider;
/*     */ import com.paratodo.user.R;
import com.utils.Utils;
/*     */ 
/*     */ 
/*     */ public class RoundedImageView
/*     */   extends AppCompatImageView
/*     */ {
/*     */   TypedArray a;
/*     */   boolean b = false;
/*     */   boolean c = false;
/*  27 */   private int d = 4;
/*     */   private int e;
/*     */   private int f;
/*     */   private Bitmap g;
/*     */   private Paint h;
/*     */   private Paint i;
/*     */   private BitmapShader j;
/*     */   private Context k;
/*     */   
/*     */   public RoundedImageView(Context context) {
/*  37 */     super(context);
/*  38 */     this.k = context;
/*  39 */     a();
/*     */   }
/*     */   
/*     */   public RoundedImageView(Context context, AttributeSet attrs) {
/*  43 */     super(context, attrs);
/*  44 */     this.k = context;
/*  45 */     this.a = this.k.obtainStyledAttributes(attrs, R.styleable.RoundedImageView);
/*  46 */     a(this.k);
/*  47 */     a();
/*     */   }
/*     */   
/*     */   public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
/*  51 */     super(context, attrs, defStyle);
/*  52 */     this.k = context;
/*  53 */     this.a = this.k.obtainStyledAttributes(attrs, R.styleable.RoundedImageView);
/*  54 */     a(this.k);
/*  55 */     a();
/*     */   }
/*     */ 
/*     */   
/*     */   @SuppressLint({"NewApi"})
/*     */   private void a() {
/*  61 */     this.h = new Paint();
/*  62 */     this.h.setAntiAlias(true);
/*     */     
/*  64 */     this.i = new Paint();
/*  65 */     setBorderColor(-1);
/*  66 */     this.i.setAntiAlias(true);
/*  67 */     setLayerType(View.LAYER_TYPE_SOFTWARE, this.i);
/*  68 */     if (this.a != null) {
/*  69 */       this.c = this.a.getBoolean(R.styleable.RoundedImageView_isShadowAvoid, false);
/*     */       
/*  71 */       float f = this.a.getDimension(R.styleable.RoundedImageView_imgViewBorderWidth, 0.0F);
/*  72 */       setBorderWidth((int)f);
/*     */       
/*  74 */       int i = this.a.getInt(R.styleable.RoundedImageView_imgViewBorderColor, Color.parseColor("#FFFFFF"));
/*  75 */       setBorderColor(i);
/*     */     } 
/*     */     
/*  78 */     if (!this.c) {
/*  79 */       this.i.setShadowLayer(4.0F, 0.0F, 2.0F, -16777216);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setBorderWidth(int borderWidth) {
/*  84 */     this.d = borderWidth;
/*  85 */     invalidate();
/*     */   }
/*     */   
/*     */   public void setBorderColor(int borderColor) {
/*  89 */     if (this.i != null) {
/*  90 */       this.i.setColor(borderColor);
/*     */     }
/*  92 */     invalidate();
/*     */   }
/*     */   
/*     */   private void b() {
/*  96 */     BitmapDrawable bitmapDrawable = (BitmapDrawable)getDrawable();
/*     */     
/*  98 */     if (bitmapDrawable != null)
/*  99 */       this.g = bitmapDrawable.getBitmap(); 
/*     */   }
/*     */   
/*     */   private void a(Context paramContext) {
/* 103 */     if (!isInEditMode() && Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("No")) {
/* 104 */       new InfoProvider(paramContext);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"DrawAllocation"})
/*     */   public void onDraw(Canvas canvas) {
/* 112 */     if (this.a != null) {
/* 113 */       this.b = this.a.getBoolean(R.styleable.RoundedImageView_isRoundedImgAvoid, false);
/*     */     }
/*     */     
/* 116 */     if (this.b == true) {
/* 117 */       super.onDraw(canvas);
/*     */       
/*     */       return;
/*     */     } 
/* 121 */     b();
/*     */ 
/*     */     
/* 124 */     if (this.g != null) {
/* 125 */       this.j = new BitmapShader(Bitmap.createScaledBitmap(this.g, canvas.getWidth(), canvas.getHeight(), false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
/*     */       
/* 127 */       this.h.setShader((Shader)this.j);
/* 128 */       int i = this.e / 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 134 */       if (!this.c) {
/* 135 */         canvas.drawCircle((i + this.d), (i + this.d), (i + this.d) - 4.0F, this.i);
/*     */         
/* 137 */         canvas.drawCircle((i + this.d), (i + this.d), i - 4.0F, this.h);
/*     */       } else {
/* 139 */         canvas.drawCircle((i + this.d), (i + this.d), (i + this.d), this.i);
/*     */         
/* 141 */         canvas.drawCircle((i + this.d), (i + this.d), i, this.h);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/* 149 */     int i = a(widthMeasureSpec);
/* 150 */     int j = a(heightMeasureSpec, widthMeasureSpec);
/*     */     
/* 152 */     this.e = i - this.d * 2;
/* 153 */     this.f = j - this.d * 2;
/*     */     
/* 155 */     setMeasuredDimension(i, j);
/*     */   }
/*     */   
/*     */   private int a(int paramInt) {
/* 159 */     int i = 0;
/* 160 */     int j = MeasureSpec.getMode(paramInt);
/* 161 */     int k = MeasureSpec.getSize(paramInt);
/*     */     
/* 163 */     if (j == 1073741824) {
/*     */       
/* 165 */       i = k;
/*     */     } else {
/*     */       
/* 168 */       i = this.e;
/*     */     } 
/*     */     
/* 171 */     return i;
/*     */   }
/*     */   
/*     */   private int a(int paramInt1, int paramInt2) {
/* 175 */     int i = 0;
/* 176 */     int j = MeasureSpec.getMode(paramInt1);
/* 177 */     int k = MeasureSpec.getSize(paramInt1);
/*     */     
/* 179 */     if (j == 1073741824) {
/*     */       
/* 181 */       i = k;
/*     */     } else {
/*     */       
/* 184 */       i = this.f;
/*     */     } 
/*     */     
/* 187 */     return i + 2;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/RoundedImageView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */