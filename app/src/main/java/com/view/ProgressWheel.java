/*     */ package com.view;
/*     */ 
/*     */ import android.annotation.TargetApi;
/*     */ import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.RectF;
/*     */ import android.os.Build;
/*     */ import android.os.Parcel;
/*     */ import android.os.Parcelable;
/*     */ import android.os.SystemClock;
/*     */ import android.provider.Settings;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.util.TypedValue;
/*     */ import android.view.View;

import com.paratodo.user.R;

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
/*     */ public class ProgressWheel
/*     */   extends View
/*     */ {
/*  32 */   private static final String a = ProgressWheel.class.getSimpleName();
/*  33 */   private final int b = 16;
/*  34 */   private final int c = 270;
/*  35 */   private final long d = 200L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private int e = 28;
/*  43 */   private int f = 4;
/*  44 */   private int g = 4;
/*     */   private boolean h = false;
/*  46 */   private double i = 0.0D;
/*  47 */   private double j = 460.0D;
/*  48 */   private float k = 0.0F;
/*     */   private boolean l = true;
/*  50 */   private long m = 0L;
/*     */   
/*  52 */   private int n = -1442840576;
/*  53 */   private int o = 16777215;
/*     */ 
/*     */   
/*  56 */   private Paint p = new Paint();
/*  57 */   private Paint q = new Paint();
/*     */ 
/*     */   
/*  60 */   private RectF r = new RectF();
/*     */ 
/*     */ 
/*     */   
/*  64 */   private float s = 230.0F;
/*     */ 
/*     */   
/*  67 */   private long t = 0L;
/*     */   
/*     */   private boolean u;
/*     */   
/*  71 */   private float v = 0.0F;
/*  72 */   private float w = 0.0F;
/*     */ 
/*     */   
/*     */   private boolean x = false;
/*     */   
/*     */   private ProgressCallback y;
/*     */   
/*     */   private boolean z;
/*     */ 
/*     */   
/*     */   public ProgressWheel(Context context, AttributeSet attrs) {
/*  83 */     super(context, attrs);
/*     */     
/*  85 */     a(context.obtainStyledAttributes(attrs, R.styleable.ProgressWheel));
/*     */     
/*  87 */     a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProgressWheel(Context context) {
/*  94 */     super(context);
/*  95 */     a();
/*     */   }
/*     */   @TargetApi(17)
/*     */   private void a() {
/*     */     float f;
/* 100 */     int i = Build.VERSION.SDK_INT;
/*     */ 
/*     */     
/* 103 */     if (i >= 17) {
/* 104 */       f = Settings.Global.getFloat(getContext().getContentResolver(), "animator_duration_scale", 1.0F);
/*     */     } else {
/*     */       
/* 107 */       f = Settings.System.getFloat(getContext().getContentResolver(), "animator_duration_scale", 1.0F);
/*     */     } 
/*     */ 
/*     */     
/* 111 */     this.z = (f != 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/*     */     int i2, i3;
/* 120 */     super.onMeasure(widthMeasureSpec, heightMeasureSpec);
/*     */     
/* 122 */     int i = this.e + getPaddingLeft() + getPaddingRight();
/* 123 */     int j = this.e + getPaddingTop() + getPaddingBottom();
/*     */     
/* 125 */     int k = MeasureSpec.getMode(widthMeasureSpec);
/* 126 */     int m = MeasureSpec.getSize(widthMeasureSpec);
/* 127 */     int n = MeasureSpec.getMode(heightMeasureSpec);
/* 128 */     int i1 = MeasureSpec.getSize(heightMeasureSpec);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     if (k == 1073741824) {
/*     */       
/* 136 */       i2 = m;
/* 137 */     } else if (k == Integer.MIN_VALUE) {
/*     */       
/* 139 */       i2 = Math.min(i, m);
/*     */     } else {
/*     */       
/* 142 */       i2 = i;
/*     */     } 
/*     */ 
/*     */     
/* 146 */     if (n == 1073741824 || k == 1073741824) {
/*     */       
/* 148 */       i3 = i1;
/* 149 */     } else if (n == Integer.MIN_VALUE) {
/*     */       
/* 151 */       i3 = Math.min(j, i1);
/*     */     } else {
/*     */       
/* 154 */       i3 = j;
/*     */     } 
/*     */     
/* 157 */     setMeasuredDimension(i2, i3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
/* 167 */     super.onSizeChanged(w, h, oldw, oldh);
/*     */     
/* 169 */     a(w, h);
/* 170 */     b();
/* 171 */     invalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void b() {
/* 179 */     this.p.setColor(this.n);
/* 180 */     this.p.setAntiAlias(true);
/* 181 */     this.p.setStyle(Paint.Style.STROKE);
/* 182 */     this.p.setStrokeWidth(this.f);
/*     */     
/* 184 */     this.q.setColor(this.o);
/* 185 */     this.q.setAntiAlias(true);
/* 186 */     this.q.setStyle(Paint.Style.STROKE);
/* 187 */     this.q.setStrokeWidth(this.g);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(int paramInt1, int paramInt2) {
/* 194 */     int i = getPaddingTop();
/* 195 */     int j = getPaddingBottom();
/* 196 */     int k = getPaddingLeft();
/* 197 */     int m = getPaddingRight();
/*     */     
/* 199 */     if (!this.h) {
/*     */       
/* 201 */       int n = Math.min(paramInt1 - k - m, paramInt2 - j - i);
/*     */ 
/*     */       
/* 204 */       int i1 = Math.min(n, this.e * 2 - this.f * 2);
/*     */ 
/*     */       
/* 207 */       int i2 = (paramInt1 - k - m - i1) / 2 + k;
/* 208 */       int i3 = (paramInt2 - i - j - i1) / 2 + i;
/*     */       
/* 210 */       this.r = new RectF((i2 + this.f), (i3 + this.f), (i2 + i1 - this.f), (i3 + i1 - this.f));
/*     */     }
/*     */     else {
/*     */       
/* 214 */       this.r = new RectF((k + this.f), (i + this.f), (paramInt1 - m - this.f), (paramInt2 - j - this.f));
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
/*     */   private void a(TypedArray paramTypedArray) {
/* 226 */     DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
/* 227 */     this.f = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.f, displayMetrics);
/* 228 */     this.g = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.g, displayMetrics);
/* 229 */     this
/* 230 */       .e = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.e, displayMetrics);
/*     */     
/* 232 */     this
/* 233 */       .e = (int)paramTypedArray.getDimension(R.styleable.ProgressWheel_matProg_circleRadius, this.e);
/*     */     
/* 235 */     this.h = paramTypedArray.getBoolean(R.styleable.ProgressWheel_matProg_fillRadius, false);
/*     */     
/* 237 */     this.f = (int)paramTypedArray.getDimension(R.styleable.ProgressWheel_matProg_barWidth, this.f);
/*     */     
/* 239 */     this.g = (int)paramTypedArray.getDimension(R.styleable.ProgressWheel_matProg_rimWidth, this.g);
/*     */ 
/*     */     
/* 242 */     float f = paramTypedArray.getFloat(R.styleable.ProgressWheel_matProg_spinSpeed, this.s / 360.0F);
/* 243 */     this.s = f * 360.0F;
/*     */     
/* 245 */     this
/* 246 */       .j = paramTypedArray.getInt(R.styleable.ProgressWheel_matProg_barSpinCycleTime, (int)this.j);
/*     */     
/* 248 */     this.n = paramTypedArray.getColor(R.styleable.ProgressWheel_matProg_barColor, this.n);
/*     */     
/* 250 */     this.o = paramTypedArray.getColor(R.styleable.ProgressWheel_matProg_rimColor, this.o);
/*     */     
/* 252 */     this.u = paramTypedArray.getBoolean(R.styleable.ProgressWheel_matProg_linearProgress, false);
/*     */     
/* 254 */     if (paramTypedArray.getBoolean(R.styleable.ProgressWheel_matProg_progressIndeterminate, false)) {
/* 255 */       spin();
/*     */     }
/*     */ 
/*     */     
/* 259 */     paramTypedArray.recycle();
/*     */   }
/*     */   
/*     */   public void setCallback(ProgressCallback progressCallback) {
/* 263 */     this.y = progressCallback;
/*     */     
/* 265 */     if (!this.x) {
/* 266 */       c();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 275 */     super.onDraw(canvas);
/*     */     
/* 277 */     canvas.drawArc(this.r, 360.0F, 360.0F, false, this.q);
/*     */     
/* 279 */     boolean bool = false;
/*     */     
/* 281 */     if (!this.z) {
/*     */       return;
/*     */     }
/*     */     
/* 285 */     if (this.x) {
/*     */       
/* 287 */       bool = true;
/*     */       
/* 289 */       long l = SystemClock.uptimeMillis() - this.t;
/* 290 */       float f1 = (float)l * this.s / 1000.0F;
/*     */       
/* 292 */       a(l);
/*     */       
/* 294 */       this.v += f1;
/* 295 */       if (this.v > 360.0F) {
/* 296 */         this.v -= 360.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 301 */         a(-1.0F);
/*     */       } 
/* 303 */       this.t = SystemClock.uptimeMillis();
/*     */       
/* 305 */       float f2 = this.v - 90.0F;
/* 306 */       float f3 = 16.0F + this.k;
/*     */       
/* 308 */       if (isInEditMode()) {
/* 309 */         f2 = 0.0F;
/* 310 */         f3 = 135.0F;
/*     */       } 
/*     */       
/* 313 */       canvas.drawArc(this.r, f2, f3, false, this.p);
/*     */     } else {
/* 315 */       float f1 = this.v;
/*     */       
/* 317 */       if (this.v != this.w) {
/*     */         
/* 319 */         bool = true;
/*     */         
/* 321 */         float f4 = (float)(SystemClock.uptimeMillis() - this.t) / 1000.0F;
/* 322 */         float f5 = f4 * this.s;
/*     */         
/* 324 */         this.v = Math.min(this.v + f5, this.w);
/* 325 */         this.t = SystemClock.uptimeMillis();
/*     */       } 
/*     */       
/* 328 */       if (f1 != this.v) {
/* 329 */         c();
/*     */       }
/*     */       
/* 332 */       float f2 = 0.0F;
/* 333 */       float f3 = this.v;
/* 334 */       if (!this.u) {
/* 335 */         float f = 2.0F;
/* 336 */         f2 = (float)(1.0D - Math.pow((1.0F - this.v / 360.0F), (2.0F * f))) * 360.0F;
/* 337 */         f3 = (float)(1.0D - Math.pow((1.0F - this.v / 360.0F), f)) * 360.0F;
/*     */       } 
/*     */       
/* 340 */       if (isInEditMode()) {
/* 341 */         f3 = 360.0F;
/*     */       }
/*     */       
/* 344 */       canvas.drawArc(this.r, f2 - 90.0F, f3, false, this.p);
/*     */     } 
/*     */     
/* 347 */     if (bool) {
/* 348 */       invalidate();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onVisibilityChanged(View changedView, int visibility) {
/* 354 */     super.onVisibilityChanged(changedView, visibility);
/*     */     
/* 356 */     if (visibility == 0) {
/* 357 */       this.t = SystemClock.uptimeMillis();
/*     */     }
/*     */   }
/*     */   
/*     */   private void a(long paramLong) {
/* 362 */     if (this.m >= 200L) {
/* 363 */       this.i += paramLong;
/*     */       
/* 365 */       if (this.i > this.j) {
/*     */ 
/*     */         
/* 368 */         this.i -= this.j;
/*     */         
/* 370 */         this.m = 0L;
/*     */         
/* 372 */         this.l = !this.l;
/*     */       } 
/*     */ 
/*     */       
/* 376 */       float f1 = (float)Math.cos((this.i / this.j + 1.0D) * Math.PI) / 2.0F + 0.5F;
/* 377 */       float f2 = 254.0F;
/*     */       
/* 379 */       if (this.l) {
/* 380 */         this.k = f1 * f2;
/*     */       } else {
/* 382 */         float f = f2 * (1.0F - f1);
/* 383 */         this.v += this.k - f;
/* 384 */         this.k = f;
/*     */       } 
/*     */     } else {
/* 387 */       this.m += paramLong;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSpinning() {
/* 396 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetCount() {
/* 403 */     this.v = 0.0F;
/* 404 */     this.w = 0.0F;
/* 405 */     invalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopSpinning() {
/* 412 */     this.x = false;
/* 413 */     this.v = 0.0F;
/* 414 */     this.w = 0.0F;
/* 415 */     invalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void spin() {
/* 422 */     this.t = SystemClock.uptimeMillis();
/* 423 */     this.x = true;
/* 424 */     invalidate();
/*     */   }
/*     */   
/*     */   private void a(float paramFloat) {
/* 428 */     if (this.y != null) {
/* 429 */       this.y.onProgressUpdate(paramFloat);
/*     */     }
/*     */   }
/*     */   
/*     */   private void c() {
/* 434 */     if (this.y != null) {
/* 435 */       float f = Math.round(this.v * 100.0F / 360.0F) / 100.0F;
/* 436 */       this.y.onProgressUpdate(f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInstantProgress(float progress) {
/* 447 */     if (this.x) {
/* 448 */       this.v = 0.0F;
/* 449 */       this.x = false;
/*     */     } 
/*     */     
/* 452 */     if (progress > 1.0F) {
/* 453 */       progress--;
/* 454 */     } else if (progress < 0.0F) {
/* 455 */       progress = 0.0F;
/*     */     } 
/*     */     
/* 458 */     if (progress == this.w) {
/*     */       return;
/*     */     }
/*     */     
/* 462 */     this.w = Math.min(progress * 360.0F, 360.0F);
/* 463 */     this.v = this.w;
/* 464 */     this.t = SystemClock.uptimeMillis();
/* 465 */     invalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Parcelable onSaveInstanceState() {
/* 471 */     Parcelable parcelable = super.onSaveInstanceState();
/*     */     
/* 473 */     a a = new a(parcelable);
/*     */ 
/*     */     
/* 476 */     a.a = this.v;
/* 477 */     a.b = this.w;
/* 478 */     a.c = this.x;
/* 479 */     a.d = this.s;
/* 480 */     a.e = this.f;
/* 481 */     a.f = this.n;
/* 482 */     a.g = this.g;
/* 483 */     a.h = this.o;
/* 484 */     a.i = this.e;
/* 485 */     a.j = this.u;
/* 486 */     a.k = this.h;
/*     */     
/* 488 */     return (Parcelable)a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRestoreInstanceState(Parcelable state) {
/* 493 */     if (!(state instanceof a)) {
/* 494 */       super.onRestoreInstanceState(state);
/*     */       
/*     */       return;
/*     */     } 
/* 498 */     a a = (a)state;
/* 499 */     super.onRestoreInstanceState(a.getSuperState());
/*     */     
/* 501 */     this.v = a.a;
/* 502 */     this.w = a.b;
/* 503 */     this.x = a.c;
/* 504 */     this.s = a.d;
/* 505 */     this.f = a.e;
/* 506 */     this.n = a.f;
/* 507 */     this.g = a.g;
/* 508 */     this.o = a.h;
/* 509 */     this.e = a.i;
/* 510 */     this.u = a.j;
/* 511 */     this.h = a.k;
/*     */     
/* 513 */     this.t = SystemClock.uptimeMillis();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getProgress() {
/* 521 */     return this.x ? -1.0F : (this.v / 360.0F);
/*     */   }
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
/*     */   public void setProgress(float progress) {
/* 535 */     if (this.x) {
/* 536 */       this.v = 0.0F;
/* 537 */       this.x = false;
/*     */       
/* 539 */       c();
/*     */     } 
/*     */     
/* 542 */     if (progress > 1.0F) {
/* 543 */       progress--;
/* 544 */     } else if (progress < 0.0F) {
/* 545 */       progress = 0.0F;
/*     */     } 
/*     */     
/* 548 */     if (progress == this.w) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 555 */     if (this.v == this.w) {
/* 556 */       this.t = SystemClock.uptimeMillis();
/*     */     }
/*     */     
/* 559 */     this.w = Math.min(progress * 360.0F, 360.0F);
/*     */     
/* 561 */     invalidate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLinearProgress(boolean isLinear) {
/* 570 */     this.u = isLinear;
/* 571 */     if (!this.x) {
/* 572 */       invalidate();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCircleRadius() {
/* 580 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCircleRadius(int circleRadius) {
/* 589 */     this.e = circleRadius;
/* 590 */     if (!this.x) {
/* 591 */       invalidate();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBarWidth() {
/* 599 */     return this.f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBarWidth(int barWidth) {
/* 608 */     this.f = barWidth;
/* 609 */     if (!this.x) {
/* 610 */       invalidate();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBarColor() {
/* 618 */     return this.n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBarColor(int barColor) {
/* 627 */     this.n = barColor;
/* 628 */     b();
/* 629 */     if (!this.x) {
/* 630 */       invalidate();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRimColor() {
/* 638 */     return this.o;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRimColor(int rimColor) {
/* 647 */     this.o = rimColor;
/* 648 */     b();
/* 649 */     if (!this.x) {
/* 650 */       invalidate();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSpinSpeed() {
/* 660 */     return this.s / 360.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpinSpeed(float spinSpeed) {
/* 671 */     this.s = spinSpeed * 360.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRimWidth() {
/* 678 */     return this.g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRimWidth(int rimWidth) {
/* 687 */     this.g = rimWidth;
/* 688 */     if (!this.x) {
/* 689 */       invalidate();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class a
/*     */     extends BaseSavedState
/*     */   {
/* 709 */     public static final Creator<a> CREATOR = new Creator<a>()
/*     */       {

    @Override
    public ProgressWheel.a createFromParcel(Parcel parcel) {
        return new a(parcel);
    }

    @Override
    public ProgressWheel.a[] newArray(int i) {
        return new a[i];
    }
/*     */       };
/*     */     float a;
/*     */     float b;
/*     */     boolean c;
/*     */     float d;
/*     */     int e;
/*     */     int f;
/*     */     int g;
/*     */     int h;
/*     */     int i;
/*     */     boolean j;
/*     */     boolean k;
/*     */     
/*     */     a(Parcelable param1Parcelable) {
/* 732 */       super(param1Parcelable);
/*     */     }
/*     */     
/*     */     private a(Parcel param1Parcel) {
/* 736 */       super(param1Parcel);
/* 737 */       this.a = param1Parcel.readFloat();
/* 738 */       this.b = param1Parcel.readFloat();
/* 739 */       this.c = (param1Parcel.readByte() != 0);
/* 740 */       this.d = param1Parcel.readFloat();
/* 741 */       this.e = param1Parcel.readInt();
/* 742 */       this.f = param1Parcel.readInt();
/* 743 */       this.g = param1Parcel.readInt();
/* 744 */       this.h = param1Parcel.readInt();
/* 745 */       this.i = param1Parcel.readInt();
/* 746 */       this.j = (param1Parcel.readByte() != 0);
/* 747 */       this.k = (param1Parcel.readByte() != 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void writeToParcel(Parcel out, int flags) {
/* 752 */       super.writeToParcel(out, flags);
/* 753 */       out.writeFloat(this.a);
/* 754 */       out.writeFloat(this.b);
/* 755 */       out.writeByte((byte)(this.c ? 1 : 0));
/* 756 */       out.writeFloat(this.d);
/* 757 */       out.writeInt(this.e);
/* 758 */       out.writeInt(this.f);
/* 759 */       out.writeInt(this.g);
/* 760 */       out.writeInt(this.h);
/* 761 */       out.writeInt(this.i);
/* 762 */       out.writeByte((byte)(this.j ? 1 : 0));
/* 763 */       out.writeByte((byte)(this.k ? 1 : 0));
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface ProgressCallback {
/*     */     void onProgressUpdate(float param1Float);
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/ProgressWheel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */