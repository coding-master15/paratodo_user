/*     */ package com.view;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Typeface;
/*     */ import android.os.Handler;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ import androidx.appcompat.widget.AppCompatTextView;

import com.InfoProvider;
import com.paratodo.user.R;
import com.utils.Utils;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MTextView
/*     */   extends AppCompatTextView
/*     */   implements View.OnClickListener
/*     */ {
/*     */   public Typeface mTypeface;
/*     */   private boolean a = false;
/*  24 */   private long b = 0L;
/*  25 */   private OnClickListener c = null;
/*     */   
/*  27 */   public int DEFAULT_DURATION_CLICK_RESTRICTION = 350;
/*     */   
/*     */   public MTextView(Context context) {
/*  30 */     this(context, (AttributeSet)null);
/*  31 */     a(context);
/*     */   }
/*     */   
/*     */   public MTextView(Context context, AttributeSet attrs) {
/*  35 */     this(context, attrs, 0);
/*  36 */     a(context);
/*     */   }
/*     */   
/*     */   public MTextView(Context context, AttributeSet attrs, int defStyle) {
/*  40 */     super(context, attrs, defStyle);
/*     */     
/*  42 */     a(context);
/*  43 */     TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MTextView);
/*     */     
/*  45 */     if (this.mTypeface == null)
/*     */     {
/*  47 */       if (typedArray != null) {
/*  48 */         String str = typedArray.getString(R.styleable.MTextView_customTypeFace);
/*     */         
/*     */         try {
/*  51 */           if (str != null) {
/*  52 */             if (str.equalsIgnoreCase("roboto_medium")) {
/*  53 */               str = getResources().getString(R.string.robotomediumFont);
/*  54 */             } else if (str.equalsIgnoreCase("roboto_light")) {
/*  55 */               str = getResources().getString(R.string.robotolightFont);
/*  56 */             } else if (str.equalsIgnoreCase("roboto_bold")) {
/*  57 */               str = getResources().getString(R.string.robotobold);
/*     */             } 
/*     */           }
/*     */           
/*  61 */           if (str != null) {
/*  62 */             this.mTypeface = Typeface.createFromAsset(context.getAssets(), str);
/*     */           } else {
/*  64 */             String str1 = getResources().getString(R.string.robotolightFont);
/*  65 */             this.mTypeface = Typeface.createFromAsset(context.getAssets(), str1);
/*     */           } 
/*  67 */         } catch (Exception exception) {}
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  72 */         String str = getResources().getString(R.string.robotolightFont);
/*  73 */         this.mTypeface = Typeface.createFromAsset(context.getAssets(), str);
/*     */       } 
/*     */     }
/*     */     
/*  77 */     setCustomTypeFace(this.mTypeface);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeTypeFace(String typeFace) {
/*     */     try {
/*  84 */       Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), typeFace);
/*  85 */       setCustomTypeFace(typeface);
/*  86 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setCustomTypeFace(Typeface mTypeface) {
/*  92 */     this.mTypeface = mTypeface;
/*  93 */     setTypeface(mTypeface);
/*  94 */     this.a = true;
/*     */   }
/*     */   
/*     */   private void a(Context paramContext) {
/*  98 */     if (!isInEditMode() && Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("No")) {
/*  99 */       new InfoProvider(paramContext);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTypeface(Typeface tf) {
/* 105 */     super.setTypeface(tf);
/* 106 */     if (this.a) {
/* 107 */       this.mTypeface = tf;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOnClickListener(OnClickListener clickListener) {
/* 113 */     this.c = clickListener;
/* 114 */     super.setOnClickListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(View v) {
/* 119 */     long l = System.currentTimeMillis();
/*     */ 
/*     */     
/* 122 */     int i = this.DEFAULT_DURATION_CLICK_RESTRICTION + 700;
/*     */     
/* 124 */     if (l - this.b > i) {
/* 125 */       this.b = l;
/* 126 */       setEnabled(false);
/* 127 */       (new Handler()).postDelayed(() -> setEnabled(true), i);
/* 128 */       if (this.c != null)
/* 129 */         this.c.onClick((View)this); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/MTextView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */