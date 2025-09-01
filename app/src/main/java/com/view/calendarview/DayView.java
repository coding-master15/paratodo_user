/*     */ package com.view.calendarview;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.RectF;
/*     */ import android.graphics.Typeface;
/*     */ import android.os.Build;
/*     */ import android.util.AttributeSet;
/*     */ import androidx.appcompat.widget.AppCompatTextView;

import com.paratodo.user.R;

import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DayView
/*     */   extends AppCompatTextView
/*     */ {
/*     */   private Date b;
/*     */   private List<DayDecorator> c;
/*  25 */   private String d = "";
/*     */   
/*     */   private Typeface e;
/*     */   
/*     */   private boolean f = false;
/*     */   
/*     */   private Path g;
/*     */   boolean a = false;
/*     */   
/*     */   public DayView(Context context) {
/*  35 */     this(context, (AttributeSet)null, 0);
/*     */   }
/*     */   
/*     */   public DayView(Context context, AttributeSet attrs) {
/*  39 */     this(context, attrs, 0);
/*     */   }
/*     */   
/*     */   public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
/*  43 */     super(context, attrs, defStyleAttr);
/*  44 */     if (Build.VERSION.SDK_INT >= 3 && 
/*  45 */       isInEditMode()) {
/*     */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   public void bind(Date date, List<DayDecorator> decorators) {
/*  51 */     this.b = date;
/*  52 */     this.c = decorators;
/*     */ 
/*     */     
/*  55 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");
/*  56 */     int i = Integer.parseInt(simpleDateFormat.format(date));
/*     */     
/*  58 */     setText(simpleDateFormat.format(date));
/*     */   }
/*     */ 
/*     */   
/*     */   public void decorate() {
/*  63 */     if (null != this.c) {
/*  64 */       for (DayDecorator dayDecorator : this.c) {
/*  65 */         dayDecorator.decorate(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void changeTypeFace(String typeFace_str) {
/*  71 */     this.d = typeFace_str;
/*     */ 
/*     */     
/*     */     try {
/*  75 */       if (typeFace_str != null) {
/*  76 */         if (typeFace_str.equalsIgnoreCase("roboto_medium")) {
/*  77 */           typeFace_str = getResources().getString(R.string.robotomediumFont);
/*  78 */         } else if (typeFace_str.equalsIgnoreCase("roboto_light")) {
/*  79 */           typeFace_str = getResources().getString(R.string.robotolightFont);
/*  80 */         } else if (typeFace_str.equalsIgnoreCase("roboto_bold")) {
/*  81 */           typeFace_str = getResources().getString(R.string.robotobold);
/*     */         } 
/*     */       }
/*     */       
/*  85 */       if (typeFace_str != null) {
/*  86 */         this.e = Typeface.createFromAsset(getContext().getAssets(), typeFace_str);
/*     */       } else {
/*  88 */         String str = getResources().getString(R.string.robotolightFont);
/*  89 */         this.e = Typeface.createFromAsset(getContext().getAssets(), str);
/*     */       }
/*     */     
/*  92 */     } catch (Exception exception) {
/*  93 */       String str = getResources().getString(R.string.robotolightFont);
/*  94 */       this.e = Typeface.createFromAsset(getContext().getAssets(), str);
/*     */     } 
/*     */     
/*  97 */     setCustomTypeFace(this.e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setCustomTypeFace(Typeface mTypeface) {
/* 103 */     this.e = mTypeface;
/* 104 */     setTypeface(mTypeface);
/* 105 */     this.f = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeface(Typeface tf) {
/* 110 */     super.setTypeface(tf);
/* 111 */     if (this.f) {
/* 112 */       this.e = tf;
/*     */     }
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 117 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
/* 122 */     super.onSizeChanged(width, height, oldWidth, oldHeight);
/*     */     
/* 124 */     if (this.a) {
/* 125 */       int i = height / 2;
/* 126 */       this.g = new Path();
/* 127 */       this.g.addRoundRect(new RectF(0.0F, 0.0F, width, height), i, i, Path.Direction.CW);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dispatchDraw(Canvas canvas) {
/* 133 */     if (this.a && this.g != null) {
/* 134 */       canvas.clipPath(this.g);
/*     */     }
/* 136 */     super.dispatchDraw(canvas);
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/calendarview/DayView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */