/*     */ package com.view;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Typeface;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Build;
/*     */ import android.os.Handler;
/*     */ import android.text.Layout;
/*     */ import android.text.StaticLayout;
/*     */ import android.text.TextPaint;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.Log;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import androidx.appcompat.widget.AppCompatButton;

import com.InfoProvider;
import com.paratodo.user.R;
import com.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MButton
/*     */   extends AppCompatButton
/*     */   implements View.OnClickListener
/*     */ {
/*     */   private static Typeface a;
/*  29 */   private CharSequence b = "";
/*     */   
/*  31 */   private long c = 0L;
/*  32 */   private OnClickListener d = null;
/*     */   
/*  34 */   public int DEFAULT_DURATION_CLICK_RESTRICTION = 350;
/*     */   
/*     */   private boolean e = false;
/*  37 */   private int f = -1;
/*     */   
/*  39 */   private float g = -1.0F;
/*  40 */   private float h = -1.0F;
/*     */   
/*  42 */   public View parentView = null;
/*     */   
/*     */   private boolean i = false;
/*     */   
/*     */   public MButton(Context context) {
/*  47 */     super(context);
/*  48 */     a((AttributeSet)null);
/*     */   }
/*     */   
/*     */   public MButton(Context context, AttributeSet attrs, int defStyle) {
/*  52 */     super(context, attrs, defStyle);
/*  53 */     a(context);
/*     */   }
/*     */ 
/*     */   
/*     */   public MButton(Context context, AttributeSet attrs) {
/*  58 */     super(context, attrs);
/*  59 */     a(context);
/*  60 */     a(attrs);
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(AttributeSet paramAttributeSet) {
/*  65 */     TypedArray typedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.MButton);
/*     */     
/*  67 */     if (typedArray != null) {
/*  68 */       String str = typedArray.getString(R.styleable.MButton_customButtonTypeFace);
/*  69 */       if (str != null) {
/*  70 */         a = Typeface.createFromAsset(getContext().getAssets(), str);
/*     */       } else {
/*  72 */         String str1 = getResources().getString(R.string.robotolightFont);
/*  73 */         a = Typeface.createFromAsset(getContext().getAssets(), str1);
/*     */       } 
/*     */ 
/*     */ 

/*  81 */         this.h = Utils.dipToPixels(getContext(), 7.0F);
/*     */
/*     */       
/*     */     } else {
/*     */       
/*  88 */       if (a == null) {
/*  89 */         String str = getResources().getString(R.string.robotolightFont);
/*  90 */         a = Typeface.createFromAsset(getContext().getAssets(), str);
/*     */       } 
/*  92 */       this.h = Utils.dipToPixels(getContext(), 7.0F);
/*     */     } 
/*     */     
/*  95 */     setTypeface(a);
/*  96 */     setAllCaps(true);
/*     */     
/*  98 */     a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onAttachedToWindow() {
/* 103 */     super.onAttachedToWindow();
/*     */     
/*     */     try {
/* 106 */       if (getParent() != null && getParent() instanceof View && this.f != -1) {
/* 107 */         View view = (View)getParent();
/* 108 */         ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
/* 109 */         layoutParams.height = this.f;
/* 110 */         view.setLayoutParams(layoutParams);
/*     */       } 
/* 112 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 119 */     setAlpha(enabled ? 1.0F : 0.5F);
/* 120 */     super.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(Context paramContext) {
/* 130 */     if (!isInEditMode() && Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("No")) {
/* 131 */       new InfoProvider(paramContext);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onTouchEvent(MotionEvent event) {
/* 137 */     this.i = true;
/* 138 */     Log.d("MButtonTouch", ":-1:" + this.i);
/* 139 */     return super.onTouchEvent(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOnClickListener(OnClickListener clickListener) {
/* 144 */     this.d = clickListener;
/* 145 */     super.setOnClickListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClick(View v) {
/* 150 */     long l = System.currentTimeMillis();
/*     */ 
/*     */     
/* 153 */     int i = this.DEFAULT_DURATION_CLICK_RESTRICTION + 700;
/*     */     
/* 155 */     Log.d("MButtonTouch", ":0:" + this.i);
/* 156 */     if (!this.i) {
/* 157 */       Log.d("MButtonTouch", ":1:" + this.i);
/* 158 */       if (this.d != null) {
/* 159 */         this.d.onClick((View)this);
/*     */       }
/*     */       return;
/*     */     } 
/* 163 */     Log.d("MButtonTouch", ":2:" + this.i);
/*     */     
/* 165 */     if (l - this.c > i) {
/* 166 */       Log.d("MButtonTouch", ":3:" + this.i);
/* 167 */       this.i = false;
/*     */       
/* 169 */       this.c = l;
/* 170 */       setEnabled(false);
/* 171 */       (new Handler()).postDelayed(() -> setEnabled(true), i);
/* 172 */       if (this.d != null) {
/* 173 */         this.d.onClick((View)this);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a() {
/* 179 */     if (!this.e) {
/*     */       return;
/*     */     }
/* 182 */     CharSequence charSequence = getText();
/* 183 */     if (charSequence != null) {
/* 184 */       this.b = getText();
/* 185 */       if (getTransformationMethod() != null) {
/* 186 */         this.b = getTransformationMethod().getTransformation(getText(), (View)this);
/*     */       }
/*     */     } 
/* 189 */     this.g = getTextSize();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
/* 194 */     super.onLayout(changed, left, top, right, bottom);
/*     */     
/* 196 */     Log.e("MButtonLayoutChange", "Called");
/* 197 */     if (!this.e) {
/*     */       return;
/*     */     }
/*     */     
/* 201 */     setBestTextSize(getInnerWidth());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
/* 206 */     super.onTextChanged(text, start, lengthBefore, lengthAfter);
/* 207 */     if (!this.e) {
/*     */       return;
/*     */     }
/* 210 */     this.b = text;
/* 211 */     if (lengthBefore != lengthAfter) {
/* 212 */       setBestTextSize(getInnerWidth());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
/* 218 */     super.onSizeChanged(w, h, oldw, oldh);
/* 219 */     if (!this.e) {
/*     */       return;
/*     */     }
/* 222 */     int i = getInnerWidth();
/* 223 */     setBestTextSize(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getInnerWidth() {
/* 230 */     Drawable[] arrayOfDrawable = getCompoundDrawables();
/* 231 */     Drawable drawable1 = arrayOfDrawable[0];
/* 232 */     Drawable drawable2 = arrayOfDrawable[2];
/*     */     
/* 234 */     int i = 0, j = 0;
/* 235 */     if (drawable1 != null) {
/* 236 */       i = drawable1.getMinimumWidth() + getCompoundDrawablePadding();
/*     */     }
/*     */     
/* 239 */     if (drawable2 != null) {
/* 240 */       j = drawable2.getMinimumWidth() + getCompoundDrawablePadding();
/*     */     }
/*     */     
/* 243 */     return getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - i - j;
/*     */   }
/*     */   
/*     */   private void setBestTextSize(int innerWidth) {
/* 247 */     if (!this.e) {
/*     */       return;
/*     */     }
/*     */     
/* 251 */     if (innerWidth <= 0)
/*     */       return; 
/* 253 */     this.g = getTextSize();
/*     */     
/* 255 */     TextPaint textPaint = getPaint();
/*     */ 
/*     */     
/* 258 */     boolean bool = false;
/*     */     
/* 260 */     textPaint.setTextSize(this.g);
/*     */     
/* 262 */     while (!bool && this.g > 0.0F && this.g >= this.h) {
/* 263 */       StaticLayout staticLayout; textPaint.setTextSize(this.g);
/*     */       
/* 265 */       if (Build.VERSION.SDK_INT >= 23) {
/*     */ 
/*     */         
/* 268 */         StaticLayout.Builder builder = StaticLayout.Builder.obtain(this.b, 0, this.b.length(), textPaint, innerWidth).setLineSpacing(getLineSpacingExtra(), getLineSpacingMultiplier()).setIncludePad(true);
/*     */         
/* 270 */         staticLayout = builder.build();
/*     */       } else {
/*     */         
/* 273 */         staticLayout = new StaticLayout(this.b, textPaint, innerWidth, Layout.Alignment.ALIGN_CENTER, getLineSpacingMultiplier(), getLineSpacingExtra(), true);
/*     */       } 
/*     */       
/* 276 */       if (staticLayout.getLineCount() <= 1) {
/* 277 */         bool = true;
/* 278 */         this.g--; continue;
/*     */       } 
/* 280 */       this.g--;
/*     */     } 
/*     */ 
/*     */     
/* 284 */     setTextSize((this.g - 1.0F) / (getResources().getDisplayMetrics()).density);
/*     */   }
/*     */   
/*     */   public float getMinTextSize() {
/* 288 */     return this.h;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/MButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */