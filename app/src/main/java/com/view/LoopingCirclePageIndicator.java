/*     */ package com.view;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Parcel;
/*     */ import android.os.Parcelable;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.View;
/*     */ import android.view.ViewConfiguration;
/*     */ import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.viewpager.widget.ViewPager;

import com.InfoProvider;
import com.paratodo.user.R;
import com.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoopingCirclePageIndicator
/*     */   extends View
/*     */   implements PageIndicator
/*     */ {
/*     */   private static final int c = -1;
/*  34 */   private final Paint d = new Paint(1);
/*  35 */   private final Paint e = new Paint(1);
/*  36 */   private final Paint f = new Paint(1);
/*     */   Context a;
/*  38 */   int b = 0;
/*     */   private float g;
/*     */   private ViewPager h;
/*     */   private ViewPager.OnPageChangeListener i;
/*     */   private int j;
/*     */   private int k;
/*     */   private float l;
/*     */   private int m;
/*     */   private int n;
/*     */   private boolean o;
/*     */   private boolean p;
/*     */   private int q;
/*  50 */   private float r = -1.0F;
/*  51 */   private int s = -1;
/*     */   private boolean t;
/*     */   private float u;
/*     */   
/*     */   public LoopingCirclePageIndicator(Context context) {
/*  56 */     this(context, (AttributeSet)null);
/*  57 */     this.a = context;
/*  58 */     a(this.a);
/*     */   }
/*     */   
/*     */   public LoopingCirclePageIndicator(Context context, AttributeSet attrs) {
/*  62 */     this(context, attrs, R.attr.vpiCirclePageIndicatorStyle);
/*  63 */     this.a = context;
/*  64 */     a(this.a);
/*     */   }
/*     */   
/*     */   public LoopingCirclePageIndicator(Context context, AttributeSet attrs, int defStyle) {
/*  68 */     super(context, attrs, defStyle);
/*  69 */     this.a = context;
/*  70 */     a(this.a);
/*  71 */     if (isInEditMode()) {
/*     */       return;
/*     */     }
/*  74 */     Resources resources = getResources();
/*  75 */     int i = resources.getColor(R.color.default_circle_indicator_page_color_prj);
/*  76 */     int j = resources.getColor(R.color.default_circle_indicator_fill_color_prj);
/*  77 */     int k = resources.getInteger(R.integer.default_circle_indicator_orientation_prj);
/*  78 */     int m = resources.getColor(R.color.default_circle_indicator_stroke_color_prj);
/*  79 */     float f1 = resources.getDimension(R.dimen.default_circle_indicator_stroke_width_prj);
/*  80 */     float f2 = resources.getDimension(R.dimen.default_circle_indicator_radius_prj);
/*  81 */     boolean bool1 = resources.getBoolean(R.bool.default_circle_indicator_centered_prj);
/*  82 */     boolean bool2 = resources.getBoolean(R.bool.default_circle_indicator_snap);
/*     */ 
/*     */     
/*  85 */     TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoopingCirclePageIndicator, defStyle, 0);
/*     */     
/*  87 */     //this.o = typedArray.getBoolean(R.styleable.LoopingCirclePageIndicator_centered, bool1);
/*  88 */     this.n = typedArray.getInt(R.styleable.LoopingCirclePageIndicator_android_orientation, k);
/*  89 */     this.d.setStyle(Paint.Style.FILL);
/*  90 */     this.d.setColor(typedArray.getColor(R.styleable.LoopingCirclePageIndicator_pageColor, i));
/*  91 */     this.e.setStyle(Paint.Style.STROKE);
/*  92 */     this.e.setColor(typedArray.getColor(R.styleable.LoopingCirclePageIndicator_strokeColor, m));
/*  93 */     this.e.setStrokeWidth(typedArray.getDimension(R.styleable.LoopingCirclePageIndicator_strokeWidth, f1));
/*  94 */     this.f.setStyle(Paint.Style.FILL);
/*  95 */     this.f.setColor(typedArray.getColor(R.styleable.LoopingCirclePageIndicator_fillColor, j));
/*  96 */     this.g = typedArray.getDimension(R.styleable.LoopingCirclePageIndicator_radius, f2);
/*  97 */     this.p = typedArray.getBoolean(R.styleable.LoopingCirclePageIndicator_snap, bool2);
/*     */     
/*  99 */     Drawable drawable = typedArray.getDrawable(R.styleable.LoopingCirclePageIndicator_android_background);
/* 100 */     if (drawable != null) {
/* 101 */       setBackgroundDrawable(drawable);
/*     */     }
/*     */     
/* 104 */     typedArray.recycle();
/*     */     
/* 106 */     ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
/* 107 */     this.q = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
/*     */   }
/*     */   
/*     */   public void setDataSize(int dataSize) {
/* 111 */     this.b = dataSize;
/*     */   }
/*     */   
/*     */   public void setExtraSpacing(float extraSpacing) {
/* 115 */     this.u = extraSpacing;
/*     */   }
/*     */   
/*     */   public boolean isCentered() {
/* 119 */     return this.o;
/*     */   }
/*     */   
/*     */   public void setCentered(boolean centered) {
/* 123 */     this.o = centered;
/* 124 */     invalidate();
/*     */   }
/*     */   
/*     */   public int getPageColor() {
/* 128 */     return this.d.getColor();
/*     */   }
/*     */   
/*     */   public void setPageColor(int pageColor) {
/* 132 */     this.d.setColor(pageColor);
/* 133 */     invalidate();
/*     */   }
/*     */   
/*     */   public int getFillColor() {
/* 137 */     return this.f.getColor();
/*     */   }
/*     */   
/*     */   public void setFillColor(int fillColor) {
/* 141 */     this.f.setColor(fillColor);
/* 142 */     invalidate();
/*     */   }
/*     */   
/*     */   public int getOrientation() {
/* 146 */     return this.n;
/*     */   }
/*     */   
/*     */   public void setOrientation(int orientation) {
/* 150 */     switch (orientation) {
/*     */       case 0:
/*     */       case 1:
/* 153 */         this.n = orientation;
/* 154 */         requestLayout();
/*     */         return;
/*     */     } 
/*     */     
/* 158 */     throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStrokeColor() {
/* 163 */     return this.e.getColor();
/*     */   }
/*     */   
/*     */   public void setStrokeColor(int strokeColor) {
/* 167 */     this.e.setColor(strokeColor);
/* 168 */     invalidate();
/*     */   }
/*     */   
/*     */   public float getStrokeWidth() {
/* 172 */     return this.e.getStrokeWidth();
/*     */   }
/*     */   
/*     */   public void setStrokeWidth(float strokeWidth) {
/* 176 */     this.e.setStrokeWidth(strokeWidth);
/* 177 */     invalidate();
/*     */   }
/*     */   
/*     */   public float getRadius() {
/* 181 */     return this.g;
/*     */   }
/*     */   
/*     */   public void setRadius(float radius) {
/* 185 */     this.g = radius;
/* 186 */     invalidate();
/*     */   }
/*     */   
/*     */   public boolean isSnap() {
/* 190 */     return this.p;
/*     */   }
/*     */   
/*     */   public void setSnap(boolean snap) {
/* 194 */     this.p = snap;
/* 195 */     invalidate();
/*     */   }
/*     */   
/*     */   private void a(Context paramContext) {
/* 199 */     if (!isInEditMode() && Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("No"))
/* 200 */       new InfoProvider(paramContext); 
/*     */   }
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/*     */     int j, k, m, n;
/*     */     float f4, f5;
/* 206 */     super.onDraw(canvas);
/*     */     
/* 208 */     if (this.h == null) {
/*     */       return;
/*     */     }
/* 211 */     int i = getRealCount();
/* 212 */     if (i == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 216 */     if (this.j >= i) {
/* 217 */       setCurrentItem(i - 1);
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 225 */     if (this.n == 0) {
/* 226 */       j = getWidth();
/* 227 */       k = getPaddingLeft();
/* 228 */       m = getPaddingRight();
/* 229 */       n = getPaddingTop();
/*     */     } else {
/* 231 */       j = getHeight();
/* 232 */       k = getPaddingTop();
/* 233 */       m = getPaddingBottom();
/* 234 */       n = getPaddingLeft();
/*     */     } 
/*     */     
/* 237 */     float f1 = this.g * 3.0F + this.u;
/* 238 */     float f2 = n + this.g;
/* 239 */     float f3 = k + this.g;
/* 240 */     if (this.o) {
/* 241 */       f4 = this.g * 2.0F;
/* 242 */       f5 = i * f4 + (i - 1) * this.u;
/* 243 */       f3 += (j - k - m) / 2.0F - f5 / 2.0F;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 249 */     float f6 = this.g;
/* 250 */     if (this.e.getStrokeWidth() > 0.0F) {
/* 251 */       f6 -= this.e.getStrokeWidth() / 2.0F;
/*     */     }
/*     */ 
/*     */     
/* 255 */     for (byte b = 0; b < i; b++) {
/* 256 */       float f = f3 + b * f1;
/* 257 */       if (this.n == 0) {
/* 258 */         f4 = f;
/* 259 */         f5 = f2;
/*     */       } else {
/* 261 */         f4 = f2;
/* 262 */         f5 = f;
/*     */       } 
/*     */       
/* 265 */       if (this.d.getAlpha() > 0) {
/* 266 */         canvas.drawCircle(f4, f5, f6, this.d);
/*     */       }
/*     */ 
/*     */       
/* 270 */       if (f6 != this.g) {
/* 271 */         canvas.drawCircle(f4, f5, this.g, this.e);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 276 */     float f7 = (this.p ? this.k : this.j) * f1;
/* 277 */     if (!this.p) {
/* 278 */       f7 += this.l * f1;
/*     */     }
/* 280 */     if (this.n == 0) {
/* 281 */       f4 = f3 + f7;
/* 282 */       f5 = f2;
/*     */     } else {
/* 284 */       f4 = f2;
/* 285 */       f5 = f3 + f7;
/*     */     } 
/* 287 */     canvas.drawCircle(f4, f5, this.g, this.f); } public boolean onTouchEvent(MotionEvent ev) { int j;
/*     */     float f1;
/*     */     int k;
/*     */     float f2;
/* 291 */     if (super.onTouchEvent(ev)) {
/* 292 */       return true;
/*     */     }
/* 294 */     if (this.h == null || getRealCount() == 0) {
/* 295 */       return false;
/*     */     }
/*     */     
/* 298 */     int i = ev.getAction() & 0xFF;
/* 299 */     switch (i) {
/*     */       case 0:
/* 301 */         this.s = MotionEventCompat.getPointerId(ev, 0);
/* 302 */         this.r = ev.getX();
/*     */         break;
/*     */       
/*     */       case 2:
/* 306 */         j = MotionEventCompat.findPointerIndex(ev, this.s);
/* 307 */         f1 = MotionEventCompat.getX(ev, j);
/* 308 */         f2 = f1 - this.r;
/*     */         
/* 310 */         if (!this.t && 
/* 311 */           Math.abs(f2) > this.q) {
/* 312 */           this.t = true;
/*     */         }
/*     */ 
/*     */         
/* 316 */         if (this.t) {
/* 317 */           this.r = f1;
/* 318 */           if (this.h.isFakeDragging() || this.h.beginFakeDrag()) {
/* 319 */             this.h.fakeDragBy(f2);
/*     */           }
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/*     */       case 3:
/* 328 */         if (!this.t) {
/* 329 */           j = getRealCount();
/* 330 */           int m = getWidth();
/* 331 */           f2 = m / 2.0F;
/* 332 */           float f = m / 6.0F;
/*     */           
/* 334 */           if (this.j > 0 && ev.getX() < f2 - f) {
/* 335 */             if (i != 3) {
/* 336 */               this.h.setCurrentItem(this.j - 1);
/*     */             }
/* 338 */             return true;
/* 339 */           }  if (this.j < j - 1 && ev.getX() > f2 + f) {
/* 340 */             if (i != 3) {
/* 341 */               this.h.setCurrentItem(this.j + 1);
/*     */             }
/* 343 */             return true;
/*     */           } 
/*     */         } 
/*     */         
/* 347 */         this.t = false;
/* 348 */         this.s = -1;
/* 349 */         if (this.h.isFakeDragging()) this.h.endFakeDrag();
/*     */         
/*     */         break;
/*     */       case 5:
/* 353 */         j = MotionEventCompat.getActionIndex(ev);
/* 354 */         this.r = MotionEventCompat.getX(ev, j);
/* 355 */         this.s = MotionEventCompat.getPointerId(ev, j);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/* 360 */         j = MotionEventCompat.getActionIndex(ev);
/* 361 */         k = MotionEventCompat.getPointerId(ev, j);
/* 362 */         if (k == this.s) {
/* 363 */           boolean bool = (j == 0) ? true : false;
/* 364 */           this.s = MotionEventCompat.getPointerId(ev, j);
/*     */         } 
/* 366 */         this.r = MotionEventCompat.getX(ev, MotionEventCompat.findPointerIndex(ev, this.s));
/*     */         break;
/*     */     } 
/*     */     
/* 370 */     return true; }
/*     */ 
/*     */ 
/*     */   
/*     */
/*     */

    @Override
    public void setViewPager(ViewPager paramViewPager) {
        if (this.h == paramViewPager) {
            /*     */       return;
            /*     */     }
        /* 378 */     if (this.h != null) {
            /* 379 */       this.h.setOnPageChangeListener(null);
            /*     */     }
        /* 381 */     if (paramViewPager.getAdapter() == null) {
            /* 382 */       throw new IllegalStateException("ViewPager does not have adapter instance.");
            /*     */     }
        /* 384 */     this.h = paramViewPager;
        /* 385 */     this.h.setOnPageChangeListener(this);
        /* 386 */     invalidate();
    }

    @Override
    public void setViewPager(ViewPager paramViewPager, int initialPosition) {
        setViewPager(paramViewPager);
        /* 392 */     setCurrentItem(initialPosition);
    }

    /*     */
/*     */   
/*     */   public void setCurrentItem(int item) {
/* 397 */     if (this.h == null) {
/* 398 */       throw new IllegalStateException("ViewPager has not been bound.");
/*     */     }
/* 400 */     this.h.setCurrentItem(item);
/* 401 */     this.j = getRealPage(item);
/* 402 */     invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void notifyDataSetChanged() {
/* 407 */     invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPageScrollStateChanged(int state) {
/* 412 */     this.m = state;
/*     */     
/* 414 */     if (this.i != null) {
/* 415 */       this.i.onPageScrollStateChanged(state);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
/* 421 */     this.j = getRealPage(position);
/* 422 */     this.l = positionOffset;
/* 423 */     invalidate();
/*     */     
/* 425 */     if (this.i != null) {
/* 426 */       this.i.onPageScrolled(position, positionOffset, positionOffsetPixels);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPageSelected(int position) {
/* 432 */     if (this.p || this.m == 0) {
/* 433 */       this.j = getRealPage(position);
/* 434 */       this.k = getRealPage(position);
/* 435 */       invalidate();
/*     */     } 
/*     */     
/* 438 */     if (this.i != null) {
/* 439 */       this.i.onPageSelected(position);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
/* 445 */     this.i = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/* 455 */     if (this.n == 0) {
/* 456 */       setMeasuredDimension(a(widthMeasureSpec), b(heightMeasureSpec));
/*     */     } else {
/* 458 */       setMeasuredDimension(b(widthMeasureSpec), a(heightMeasureSpec));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int a(int paramInt) {
/* 470 */     int i, j = MeasureSpec.getMode(paramInt);
/* 471 */     int k = MeasureSpec.getSize(paramInt);
/*     */     
/* 473 */     if (j == 1073741824 || this.h == null) {
/*     */       
/* 475 */       i = k;
/*     */     } else {
/*     */       
/* 478 */       int m = getRealCount();
/* 479 */       i = (int)((getPaddingLeft() + getPaddingRight()) + (m * 2) * this.g + (m - 1) * (this.g + this.u) + 1.0F);
/*     */ 
/*     */       
/* 482 */       if (j == Integer.MIN_VALUE) {
/* 483 */         i = Math.min(i, k);
/*     */       }
/*     */     } 
/* 486 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int b(int paramInt) {
/* 497 */     int i, j = MeasureSpec.getMode(paramInt);
/* 498 */     int k = MeasureSpec.getSize(paramInt);
/*     */     
/* 500 */     if (j == 1073741824) {
/*     */       
/* 502 */       i = k;
/*     */     } else {
/*     */       
/* 505 */       i = (int)(2.0F * this.g + getPaddingTop() + getPaddingBottom() + 1.0F);
/*     */       
/* 507 */       if (j == Integer.MIN_VALUE) {
/* 508 */         i = Math.min(i, k);
/*     */       }
/*     */     } 
/* 511 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRestoreInstanceState(Parcelable state) {
/* 516 */     a a = (a)state;
/* 517 */     super.onRestoreInstanceState(a.getSuperState());
/* 518 */     this.j = getRealPage(a.a);
/* 519 */     this.k = getRealPage(a.a);
/* 520 */     requestLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   public Parcelable onSaveInstanceState() {
/* 525 */     Parcelable parcelable = super.onSaveInstanceState();
/* 526 */     a a = new a(parcelable);
/* 527 */     a.a = this.j;
/* 528 */     return (Parcelable)a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRealCount() {
/*     */     try {
/* 538 */       if (this.b > 0) {
/* 539 */         return this.b;
/*     */       }
/* 541 */       return this.h.getAdapter().getCount();
/* 542 */     } catch (Exception exception) {
/* 543 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRealPage(int page) {
/*     */     try {
/* 553 */       return page % getRealCount();
/* 554 */     } catch (Exception exception) {
/*     */ 
/*     */       
/* 557 */       return page;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class a
/*     */     extends BaseSavedState
/*     */   {
/* 566 */     public static final Creator<a> CREATOR = new Creator<a>()
/*     */       {
    @Override
    public LoopingCirclePageIndicator.a createFromParcel(Parcel parcel) {
        return new a(parcel);
    }

    @Override
    public LoopingCirclePageIndicator.a[] newArray(int i) {
        return new a[i];
    }

/*     */       };
/*     */     int a;
/*     */     
/*     */     public a(Parcelable param1Parcelable) {
/* 580 */       super(param1Parcelable);
/*     */     }
/*     */     
/*     */     private a(Parcel param1Parcel) {
/* 584 */       super(param1Parcel);
/* 585 */       this.a = param1Parcel.readInt();
/*     */     }
/*     */ 
/*     */     
/*     */     public void writeToParcel(Parcel dest, int flags) {
/* 590 */       super.writeToParcel(dest, flags);
/* 591 */       dest.writeInt(this.a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface LoopingPagerAdapter {
/*     */     int getRealCount();
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/LoopingCirclePageIndicator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */