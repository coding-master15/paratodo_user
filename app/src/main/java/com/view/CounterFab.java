/*     */ package com.view;
/*     */ 
/*     */ import android.animation.ObjectAnimator;
/*     */ import android.animation.TimeInterpolator;
/*     */ import android.annotation.SuppressLint;
import android.content.Context;
/*     */ import android.content.res.ColorStateList;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Rect;
/*     */ import android.graphics.Typeface;
/*     */ import android.graphics.drawable.ColorDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Parcel;
/*     */ import android.os.Parcelable;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.Property;
/*     */ import android.view.View;
/*     */ import android.view.animation.Interpolator;
/*     */ import android.view.animation.OvershootInterpolator;
/*     */ import androidx.annotation.IntRange;
import androidx.core.view.ViewCompat;

import com.InfoProvider;
/*     */ import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.utils.Utils;

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
/*     */ public class CounterFab
/*     */   extends FloatingActionButton
/*     */ {
/*  48 */   private final Property<CounterFab, Float> a = new Property<CounterFab, Float>(Float.class, "animation")
/*     */     {
    @Override
    public Float get(CounterFab counterFab) {
        return null;
    }
    /*     */

/*     */ 
/*     */       
/*     */       public Float a(CounterFab param1CounterFab) {
/*  59 */         return Float.valueOf(0.0F);
/*     */       }
/*     */     };
/*     */   
/*     */   public static final int MAX_COUNT = 99;
/*     */   public static final String MAX_COUNT_TEXT = "99+";
/*  65 */   public static int TEXT_SIZE_DP = 11;
/*     */   public static final int TEXT_PADDING_DP = 2;
/*  67 */   public static final int MASK_COLOR = Color.parseColor("#FFFFFF");
/*  68 */   public static final Interpolator ANIMATION_INTERPOLATOR = (Interpolator)new OvershootInterpolator();
/*     */   
/*     */   private final Rect b;
/*     */   
/*     */   private final Paint c;
/*     */   private final float d;
/*     */   private final Paint e;
/*     */   private final Rect f;
/*     */   private final Paint g;
/*     */   private final int h;
/*     */   private float i;
/*     */   private int j;
/*     */   private String k;
/*     */   private float l;
/*     */   private ObjectAnimator m;
/*     */   
/*     */   public CounterFab(Context context) {
/*  85 */     this(context, (AttributeSet)null, 0);
/*  86 */     a(context);
/*     */   }
/*     */   
/*     */   public CounterFab(Context context, AttributeSet attrs) {
/*  90 */     this(context, attrs, 0);
/*  91 */     a(context);
/*     */   }
/*     */   
/*     */   @SuppressLint("ResourceType")
public CounterFab(Context context, AttributeSet attrs, int defStyleAttr) {
/*  95 */     super(context, attrs, defStyleAttr);
/*  97 */     a(context);
/*  98 */     float f1 = (getResources().getDisplayMetrics()).density;
/*     */     
/* 100 */     this.d = TEXT_SIZE_DP * f1;
/* 101 */     float f2 = 2.0F * f1;
/*     */     
/* 103 */     this.h = getResources().getInteger(17694720);
/* 104 */     this.i = 1.0F;
/*     */     
/* 106 */     this.c = new Paint(1);
/* 107 */     this.c.setStyle(Paint.Style.STROKE);
/* 108 */     this.c.setColor(-16777216);
/* 109 */     this.c.setTextSize(this.d);
/* 110 */     this.c.setTextAlign(Paint.Align.CENTER);
/* 111 */     this.c.setTypeface(Typeface.SANS_SERIF);
/*     */     
/* 113 */     this.e = new Paint(1);
/* 114 */     this.e.setStyle(Paint.Style.FILL);
/* 115 */     ColorStateList colorStateList = getBackgroundTintList();
/* 116 */     if (colorStateList != null) {
/* 117 */       this.e.setColor(colorStateList.getDefaultColor());
/*     */     } else {
/* 119 */       Drawable drawable = getBackground();
/* 120 */       if (drawable instanceof ColorDrawable) {
/* 121 */         ColorDrawable colorDrawable = (ColorDrawable)drawable;
/* 122 */         this.e.setColor(colorDrawable.getColor());
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     this.g = new Paint(1);
/* 127 */     this.g.setStyle(Paint.Style.FILL);
/* 128 */     this.g.setColor(MASK_COLOR);
/*     */     
/* 130 */     Rect rect = new Rect();
/* 131 */     this.c.getTextBounds("99+", 0, "99+".length(), rect);
/* 132 */     this.l = rect.height();
/*     */     
/* 134 */     float f3 = this.c.measureText("99+");
/* 135 */     float f4 = Math.max(f3, this.l) / 2.0F + f2;
/* 136 */     this.f = new Rect(0, 0, (int)(f4 * 2.0F), (int)(f4 * 2.0F));
/* 137 */     this.b = new Rect();
/*     */     
/* 139 */     a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 146 */     return this.j;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCount(@IntRange(from = 0L) int count) {
/* 155 */     if (count == this.j)
/* 156 */       return;  this.j = (count > 0) ? count : 0;
/* 157 */     a();
/* 158 */     if (ViewCompat.isLaidOut((View)this)) {
/* 159 */       b();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void increase() {
/* 167 */     setCount(this.j + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void decrease() {
/* 174 */     setCount((this.j > 0) ? (this.j - 1) : 0);
/*     */   }
/*     */   
/*     */   private void a() {
/* 178 */     if (this.j > 99) {
/* 179 */       this.k = String.valueOf("99+");
/*     */     } else {
/* 181 */       this.k = String.valueOf(this.j);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(Context paramContext) {
/* 186 */     if (!isInEditMode() && Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("No")) {
/* 187 */       new InfoProvider(paramContext);
/*     */     }
/*     */   }
/*     */   
/*     */   private void b() {
/* 192 */     float f1 = 0.0F;
/* 193 */     float f2 = 1.0F;
/* 194 */     if (this.j == 0) {
/* 195 */       f1 = 1.0F;
/* 196 */       f2 = 0.0F;
/*     */     } 
/* 198 */     if (c()) {
/* 199 */       this.m.cancel();
/*     */     }
/* 201 */     this.m = ObjectAnimator.ofObject(this, this.a.getName(), null, (Object[])new Float[] { Float.valueOf(f1), Float.valueOf(f2) });
/* 202 */     this.m.setInterpolator((TimeInterpolator)ANIMATION_INTERPOLATOR);
/* 203 */     this.m.setDuration(this.h);
/* 204 */     this.m.start();
/*     */   }
/*     */   
/*     */   private boolean c() {
/* 208 */     return (this.m != null && this.m.isRunning());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 213 */     super.onDraw(canvas);
/* 214 */     if (this.j > 0 || c()) {
/* 215 */       if (getGlobalVisibleRect(this.b)) {
/* 216 */         this.f.offsetTo(this.b.left + this.b.width() - this.f.width(), this.b.top);
/*     */       }
/* 218 */       float f1 = this.f.centerX();
/* 219 */       float f2 = this.f.centerY();
/* 220 */       float f3 = this.f.width() / 2.0F * this.i;
/*     */       
/* 222 */       canvas.drawCircle(f1, f2, f3, this.e);
/*     */       
/* 224 */       canvas.drawCircle(f1, f2, f3, this.g);
/*     */       
/* 226 */       this.c.setTextSize(this.d * this.i);
/* 227 */       canvas.drawText(this.k, f1, f2 + this.l / 2.0F, this.c);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static class a
/*     */     extends BaseSavedState
/*     */   {
/*     */     private int a;
/*     */ 
/*     */     
/*     */     private a(Parcelable param1Parcelable) {
/* 239 */       super(param1Parcelable);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private a(Parcel param1Parcel) {
/* 246 */       super(param1Parcel);
/* 247 */       this.a = param1Parcel.readInt();
/*     */     }
/*     */ 
/*     */     
/*     */     public void writeToParcel(Parcel out, int flags) {
/* 252 */       super.writeToParcel(out, flags);
/* 253 */       out.writeInt(this.a);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 258 */       return CounterFab.class.getSimpleName() + "." + a.class.getSimpleName() + "{" + 
/* 259 */         Integer.toHexString(System.identityHashCode(this)) + " count=" + this.a + "}";
/*     */     }
/*     */ 
/*     */     
/* 263 */     public static final Creator<a> CREATOR = new Creator<a>()
/*     */       {
    @Override
    public CounterFab.a createFromParcel(Parcel parcel) {
        return new a(parcel);
    }

    @Override
    public CounterFab.a[] newArray(int i) {
        return new a[i];
    }

    /*     */         public CounterFab.a a(Parcel param2Parcel) {
/* 266 */           return new a(param2Parcel);
/*     */         }
/*     */         
/*     */         public CounterFab.a[] a(int param2Int) {
/* 270 */           return new a[param2Int];
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/CounterFab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */