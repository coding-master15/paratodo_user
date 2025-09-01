/*     */ package com.view;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.ColorStateList;
/*     */ import android.content.res.Resources;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapShader;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.ColorFilter;
/*     */ import android.graphics.Matrix;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.PixelFormat;
import android.graphics.Rect;
/*     */ import android.graphics.RectF;
/*     */ import android.graphics.Shader;
/*     */ import android.graphics.drawable.BitmapDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.graphics.drawable.LayerDrawable;
/*     */ import android.net.Uri;
/*     */ import android.util.AttributeSet;
/*     */ import android.widget.ImageView;
/*     */ import androidx.appcompat.widget.AppCompatImageView;

import com.InfoProvider;
import com.paratodo.user.R;
import com.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SelectableRoundedImageView
/*     */   extends AppCompatImageView
/*     */ {
/*     */   public static final String TAG = "SelectableRoundedIV";
/*  36 */   private static final ScaleType[] abc = new ScaleType[] { ScaleType.MATRIX, ScaleType.FIT_XY, ScaleType.FIT_START, ScaleType.FIT_CENTER, ScaleType.FIT_END, ScaleType.CENTER, ScaleType.CENTER_CROP, ScaleType.CENTER_INSIDE };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int b = -16777216;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   private int c = 0;
/*     */ 
/*     */   
/*  50 */   private ScaleType d = ScaleType.FIT_CENTER;
/*  51 */   private float e = 0.0F;
/*  52 */   private float f = 0.0F;
/*  53 */   private float g = 0.0F;
/*  54 */   private float h = 0.0F;
/*  55 */   private float i = 0.0F;
/*  56 */   private ColorStateList j = ColorStateList.valueOf(-16777216);
/*     */   
/*     */   private boolean k = false;
/*     */   
/*     */   private Drawable l;
/*     */   
/*  62 */   private float[] m = new float[] { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F };
/*     */   
/*     */   public SelectableRoundedImageView(Context context) {
/*  65 */     super(context);
/*  66 */     a(context);
/*     */   }
/*     */   
/*     */   public SelectableRoundedImageView(Context context, AttributeSet attrs) {
/*  70 */     this(context, attrs, 0);
/*  71 */     a(context);
/*     */   }
/*     */   
/*     */   public SelectableRoundedImageView(Context context, AttributeSet attrs, int defStyle) {
/*  75 */     super(context, attrs, defStyle);
/*  76 */     a(context);
/*  77 */     TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelectableRoundedImageView, defStyle, 0);
/*     */ 
/*     */     
/*  80 */     int i = typedArray.getInt(R.styleable.SelectableRoundedImageView_android_scaleType, -1);
/*  81 */     if (i >= 0) {
/*  82 */       setScaleType(abc[i]);
/*     */     }
/*     */     
/*  85 */     this.e = typedArray.getDimensionPixelSize(R.styleable.SelectableRoundedImageView_sriv_left_top_corner_radius, 0);
/*     */     
/*  87 */     this.f = typedArray.getDimensionPixelSize(R.styleable.SelectableRoundedImageView_sriv_right_top_corner_radius, 0);
/*     */     
/*  89 */     this.g = typedArray.getDimensionPixelSize(R.styleable.SelectableRoundedImageView_sriv_left_bottom_corner_radius, 0);
/*     */     
/*  91 */     this.h = typedArray.getDimensionPixelSize(R.styleable.SelectableRoundedImageView_sriv_right_bottom_corner_radius, 0);
/*     */ 
/*     */     
/*  94 */     if (this.e < 0.0F || this.f < 0.0F || this.g < 0.0F || this.h < 0.0F)
/*     */     {
/*  96 */       throw new IllegalArgumentException("radius values cannot be negative.");
/*     */     }
/*     */     
/*  99 */     this.m = new float[] { this.e, this.e, this.f, this.f, this.h, this.h, this.g, this.g };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     this.i = typedArray.getDimensionPixelSize(R.styleable.SelectableRoundedImageView_sriv_border_width, 0);
/*     */     
/* 107 */     if (this.i < 0.0F) {
/* 108 */       throw new IllegalArgumentException("border width cannot be negative.");
/*     */     }
/*     */     
/* 111 */     this
/* 112 */       .j = typedArray.getColorStateList(R.styleable.SelectableRoundedImageView_sriv_border_color);
/* 113 */     if (this.j == null) {
/* 114 */       this.j = ColorStateList.valueOf(-16777216);
/*     */     }
/*     */     
/* 117 */     this.k = typedArray.getBoolean(R.styleable.SelectableRoundedImageView_sriv_oval, false);
/* 118 */     typedArray.recycle();
/*     */     
/* 120 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawableStateChanged() {
/* 125 */     super.drawableStateChanged();
/* 126 */     invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public ScaleType getScaleType() {
/* 131 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScaleType(ScaleType scaleType) {
/* 136 */     super.setScaleType(scaleType);
/* 137 */     this.d = scaleType;
/* 138 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImageDrawable(Drawable drawable) {
/* 143 */     this.c = 0;
/* 144 */     this.l = a.a(drawable, getResources());
/* 145 */     super.setImageDrawable(this.l);
/* 146 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImageBitmap(Bitmap bm) {
/* 151 */     this.c = 0;
/* 152 */     this.l = a.a(bm, getResources());
/* 153 */     super.setImageDrawable(this.l);
/* 154 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImageResource(int resId) {
/* 159 */     if (this.c != resId) {
/* 160 */       this.c = resId;
/* 161 */       this.l = a();
/* 162 */       super.setImageDrawable(this.l);
/* 163 */       b();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImageURI(Uri uri) {
/* 169 */     super.setImageURI(uri);
/* 170 */     setImageDrawable(getDrawable());
/*     */   }
/*     */   
/*     */   private Drawable a() {
/* 174 */     Resources resources = getResources();
/* 175 */     if (resources == null) {
/* 176 */       return null;
/*     */     }
/*     */     
/* 179 */     Drawable drawable = null;
/*     */     
/* 181 */     if (this.c != 0) {
/*     */       try {
/* 183 */         drawable = resources.getDrawable(this.c);
/* 184 */       } catch (Resources.NotFoundException notFoundException) {
/* 185 */         this.c = 0;
/*     */       } 
/*     */     }
/* 188 */     return a.a(drawable, getResources());
/*     */   }
/*     */   
/*     */   private void b() {
/* 192 */     if (this.l == null) {
/*     */       return;
/*     */     }
/*     */     
/* 196 */     ((a)this.l).a(this.d);
/* 197 */     ((a)this.l).a(this.m);
/* 198 */     ((a)this.l).a(this.i);
/* 199 */     ((a)this.l).a(this.j);
/* 200 */     ((a)this.l).a(this.k);
/*     */   }
/*     */   
/*     */   private void a(Context paramContext) {
/* 204 */     if (!isInEditMode() && Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("No")) {
/* 205 */       new InfoProvider(paramContext);
/*     */     }
/*     */   }
/*     */   
/*     */   public float getCornerRadius() {
/* 210 */     return this.e;
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
/*     */   public void setCornerRadiiDP(float leftTop, float rightTop, float leftBottom, float rightBottom) {
/* 222 */     float f1 = (getResources().getDisplayMetrics()).density;
/*     */     
/* 224 */     float f2 = leftTop * f1;
/* 225 */     float f3 = rightTop * f1;
/* 226 */     float f4 = leftBottom * f1;
/* 227 */     float f5 = rightBottom * f1;
/*     */     
/* 229 */     this.m = new float[] { f2, f2, f3, f3, f5, f5, f4, f4 };
/* 230 */     b();
/*     */   }
/*     */   
/*     */   public float getBorderWidth() {
/* 234 */     return this.i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBorderWidthDP(float width) {
/* 243 */     float f = (getResources().getDisplayMetrics()).density * width;
/* 244 */     if (this.i == f) {
/*     */       return;
/*     */     }
/*     */     
/* 248 */     this.i = f;
/* 249 */     b();
/* 250 */     invalidate();
/*     */   }
/*     */   
/*     */   public int getBorderColor() {
/* 254 */     return this.j.getDefaultColor();
/*     */   }
/*     */   
/*     */   public void setBorderColor(ColorStateList colors) {
/* 258 */     if (this.j.equals(colors)) {
/*     */       return;
/*     */     }
/*     */     
/* 262 */     this
/* 263 */       .j = (colors != null) ? colors : ColorStateList.valueOf(-16777216);
/* 264 */     b();
/* 265 */     if (this.i > 0.0F) {
/* 266 */       invalidate();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setBorderColor(int color) {
/* 271 */     setBorderColor(ColorStateList.valueOf(color));
/*     */   }
/*     */   
/*     */   public ColorStateList getBorderColors() {
/* 275 */     return this.j;
/*     */   }
/*     */   
/*     */   public boolean isOval() {
/* 279 */     return this.k;
/*     */   }
/*     */   
/*     */   public void setOval(boolean oval) {
/* 283 */     this.k = oval;
/* 284 */     b();
/* 285 */     invalidate();
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends Drawable {
/*     */     private static final String a = "SelectableRoundedCD";
/*     */     private static final int b = -16777216;
/* 292 */     private final RectF c = new RectF();
/*     */     private final int d;
/*     */     private final int e;
/*     */     private final Paint f;
/*     */     private final Paint g;
/* 297 */     private RectF h = new RectF();
/* 298 */     private RectF i = new RectF();
/*     */     
/*     */     private BitmapShader j;
/* 301 */     private float[] k = new float[] { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F };
/* 302 */     private float[] l = new float[] { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F };
/*     */     
/*     */     private boolean m = false;
/*     */     
/* 306 */     private float n = 0.0F;
/* 307 */     private ColorStateList o = ColorStateList.valueOf(-16777216);
/*     */ 
/*     */     
/* 310 */     private ScaleType p = ScaleType.FIT_CENTER;
/*     */     
/* 312 */     private Path q = new Path();
/*     */     private Bitmap r;
/*     */     private boolean s = false;
/*     */     
/*     */     public a(Bitmap param1Bitmap, Resources param1Resources) {
/* 317 */       this.r = param1Bitmap;
/* 318 */       this.j = new BitmapShader(param1Bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
/*     */       
/* 320 */       if (param1Bitmap != null) {
/* 321 */         this.d = param1Bitmap.getScaledWidth(param1Resources.getDisplayMetrics());
/* 322 */         this.e = param1Bitmap.getScaledHeight(param1Resources.getDisplayMetrics());
/*     */       } else {
/* 324 */         this.d = this.e = -1;
/*     */       } 
/*     */       
/* 327 */       this.c.set(0.0F, 0.0F, this.d, this.e);
/*     */       
/* 329 */       this.f = new Paint(1);
/* 330 */       this.f.setStyle(Paint.Style.FILL);
/* 331 */       this.f.setShader((Shader)this.j);
/*     */       
/* 333 */       this.g = new Paint(1);
/* 334 */       this.g.setStyle(Paint.Style.STROKE);
/* 335 */       this.g.setColor(this.o.getColorForState(getState(), -16777216));
/* 336 */       this.g.setStrokeWidth(this.n);
/*     */     }
/*     */     
/*     */     public static a a(Bitmap param1Bitmap, Resources param1Resources) {
/* 340 */       if (param1Bitmap != null) {
/* 341 */         return new a(param1Bitmap, param1Resources);
/*     */       }
/* 343 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public static Drawable a(Drawable param1Drawable, Resources param1Resources) {
/* 348 */       if (param1Drawable != null) {
/* 349 */         if (param1Drawable instanceof a)
/* 350 */           return param1Drawable; 
/* 351 */         if (param1Drawable instanceof LayerDrawable) {
/* 352 */           LayerDrawable layerDrawable = (LayerDrawable)param1Drawable;
/* 353 */           int i = layerDrawable.getNumberOfLayers();
/* 354 */           for (byte b = 0; b < i; b++) {
/* 355 */             Drawable drawable = layerDrawable.getDrawable(b);
/* 356 */             layerDrawable.setDrawableByLayerId(layerDrawable.getId(b), a(drawable, param1Resources));
/*     */           } 
/* 358 */           return (Drawable)layerDrawable;
/*     */         } 
/*     */         
/* 361 */         Bitmap bitmap = a(param1Drawable);
/* 362 */         if (bitmap != null) {
/* 363 */           return new a(bitmap, param1Resources);
/*     */         }
/* 365 */         System.out.println("SelectableRoundedCD:Failed to create bitmap!");
/*     */       } 
/*     */       
/* 368 */       return param1Drawable;
/*     */     }
/*     */     public static Bitmap a(Drawable param1Drawable) {
/*     */       Bitmap bitmap;
/* 372 */       if (param1Drawable == null) {
/* 373 */         return null;
/*     */       }
/*     */       
/* 376 */       if (param1Drawable instanceof BitmapDrawable) {
/* 377 */         return ((BitmapDrawable)param1Drawable).getBitmap();
/*     */       }
/*     */ 
/*     */       
/* 381 */       int i = Math.max(param1Drawable.getIntrinsicWidth(), 2);
/* 382 */       int j = Math.max(param1Drawable.getIntrinsicHeight(), 2);
/*     */       try {
/* 384 */         bitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
/* 385 */         Canvas canvas = new Canvas(bitmap);
/* 386 */         param1Drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
/* 387 */         param1Drawable.draw(canvas);
/* 388 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 389 */         illegalArgumentException.printStackTrace();
/* 390 */         bitmap = null;
/*     */       } 
/* 392 */       return bitmap;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isStateful() {
/* 397 */       return this.o.isStateful();
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean onStateChange(int[] state) {
/* 402 */       int i = this.o.getColorForState(state, 0);
/* 403 */       if (this.g.getColor() != i) {
/* 404 */         this.g.setColor(i);
/* 405 */         return true;
/*     */       } 
/* 407 */       return super.onStateChange(state);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void a(Canvas param1Canvas) {
/* 416 */       Rect rect = param1Canvas.getClipBounds();
/* 417 */       Matrix matrix = param1Canvas.getMatrix();
/*     */       
/* 419 */       if (ScaleType.CENTER == this.p) {
/* 420 */         this.h.set(rect);
/* 421 */       } else if (ScaleType.CENTER_CROP == this.p) {
/* 422 */         a(matrix);
/* 423 */         this.h.set(rect);
/* 424 */       } else if (ScaleType.FIT_XY == this.p) {
/* 425 */         Matrix matrix1 = new Matrix();
/* 426 */         matrix1.setRectToRect(this.c, new RectF(rect), Matrix.ScaleToFit.FILL);
/* 427 */         this.j.setLocalMatrix(matrix1);
/* 428 */         this.h.set(rect);
/* 429 */       } else if (ScaleType.FIT_START == this.p || ScaleType.FIT_END == this.p || ScaleType.FIT_CENTER == this.p || ScaleType.CENTER_INSIDE == this.p) {
/*     */         
/* 431 */         a(matrix);
/* 432 */         this.h.set(this.c);
/* 433 */       } else if (ScaleType.MATRIX == this.p) {
/* 434 */         a(matrix);
/* 435 */         this.h.set(this.c);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void a(Matrix param1Matrix) {
/* 440 */       float[] arrayOfFloat = new float[9];
/* 441 */       param1Matrix.getValues(arrayOfFloat);
/* 442 */       for (byte b = 0; b < this.k.length; b++) {
/* 443 */         this.k[b] = this.k[b] / arrayOfFloat[0];
/*     */       }
/*     */     }
/*     */     
/*     */     private void b(Canvas param1Canvas) {
/* 448 */       Matrix matrix = param1Canvas.getMatrix();
/* 449 */       float[] arrayOfFloat = new float[9];
/* 450 */       matrix.getValues(arrayOfFloat);
/*     */       
/* 452 */       float f1 = arrayOfFloat[0];
/* 453 */       float f2 = arrayOfFloat[4];
/* 454 */       float f3 = arrayOfFloat[2];
/* 455 */       float f4 = arrayOfFloat[5];
/*     */ 
/*     */       
/* 458 */       float f5 = this.h.width() / (this.h.width() + this.n + this.n);
/*     */       
/* 460 */       float f6 = this.h.height() / (this.h.height() + this.n + this.n);
/*     */       
/* 462 */       param1Canvas.scale(f5, f6);
/* 463 */       if (ScaleType.FIT_START == this.p || ScaleType.FIT_END == this.p || ScaleType.FIT_XY == this.p || ScaleType.FIT_CENTER == this.p || ScaleType.CENTER_INSIDE == this.p || ScaleType.MATRIX == this.p) {
/*     */ 
/*     */         
/* 466 */         param1Canvas.translate(this.n, this.n);
/* 467 */       } else if (ScaleType.CENTER == this.p || ScaleType.CENTER_CROP == this.p) {
/*     */         
/* 469 */         param1Canvas.translate(-f3 / f5 * f1, -f4 / f6 * f2);
/*     */ 
/*     */ 
/*     */         
/* 473 */         param1Canvas.translate(-(this.h.left - this.n), -(this.h.top - this.n));
/*     */       } 
/*     */     }
/*     */     
/*     */     private void c(Canvas param1Canvas) {
/* 478 */       Matrix matrix = param1Canvas.getMatrix();
/* 479 */       float[] arrayOfFloat = new float[9];
/* 480 */       matrix.getValues(arrayOfFloat);
/*     */       
/* 482 */       float f1 = arrayOfFloat[0];
/*     */       
/* 484 */       float f2 = this.h.width() * f1;
/* 485 */       this.n = this.n * this.h.width() / (f2 - 2.0F * this.n);
/* 486 */       this.g.setStrokeWidth(this.n);
/*     */       
/* 488 */       this.i.set(this.h);
/* 489 */       this.i.inset(-this.n / 2.0F, -this.n / 2.0F);
/*     */     }
/*     */     
/*     */     private void f() {
/* 493 */       for (byte b = 0; b < this.k.length; b++) {
/* 494 */         if (this.k[b] > 0.0F) {
/* 495 */           this.l[b] = this.k[b];
/* 496 */           this.k[b] = this.k[b] - this.n;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void draw(Canvas canvas) {
/* 503 */       canvas.save();
/* 504 */       if (!this.s) {
/* 505 */         a(canvas);
/* 506 */         if (this.n > 0.0F) {
/* 507 */           c(canvas);
/* 508 */           f();
/*     */         } 
/* 510 */         this.s = true;
/*     */       } 
/*     */       
/* 513 */       if (this.m) {
/* 514 */         if (this.n > 0.0F) {
/* 515 */           b(canvas);
/* 516 */           this.q.addOval(this.h, Path.Direction.CW);
/* 517 */           canvas.drawPath(this.q, this.f);
/* 518 */           this.q.reset();
/* 519 */           this.q.addOval(this.i, Path.Direction.CW);
/* 520 */           canvas.drawPath(this.q, this.g);
/*     */         } else {
/* 522 */           this.q.addOval(this.h, Path.Direction.CW);
/* 523 */           canvas.drawPath(this.q, this.f);
/*     */         }
/*     */       
/* 526 */       } else if (this.n > 0.0F) {
/* 527 */         b(canvas);
/* 528 */         this.q.addRoundRect(this.h, this.k, Path.Direction.CW);
/* 529 */         canvas.drawPath(this.q, this.f);
/* 530 */         this.q.reset();
/* 531 */         this.q.addRoundRect(this.i, this.l, Path.Direction.CW);
/* 532 */         canvas.drawPath(this.q, this.g);
/*     */       } else {
/* 534 */         this.q.addRoundRect(this.h, this.k, Path.Direction.CW);
/* 535 */         canvas.drawPath(this.q, this.f);
/*     */       } 
/*     */       
/* 538 */       canvas.restore();
/*     */     }
/*     */     
/*     */     public void a(float[] param1ArrayOffloat) {
/* 542 */       if (param1ArrayOffloat == null) {
/*     */         return;
/*     */       }
/* 545 */       if (param1ArrayOffloat.length != 8) {
/* 546 */         throw new ArrayIndexOutOfBoundsException("radii[] needs 8 values");
/*     */       }
/*     */       
/* 549 */       for (byte b = 0; b < param1ArrayOffloat.length; b++) {
/* 550 */         this.k[b] = param1ArrayOffloat[b];
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public int getOpacity() {
/* 556 */       return (this.r == null || this.r.hasAlpha() || this.f.getAlpha() < 255) ? PixelFormat.UNKNOWN : PixelFormat.UNKNOWN;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setAlpha(int alpha) {
/* 562 */       this.f.setAlpha(alpha);
/* 563 */       invalidateSelf();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setColorFilter(ColorFilter cf) {
/* 568 */       this.f.setColorFilter(cf);
/* 569 */       invalidateSelf();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setDither(boolean dither) {
/* 574 */       this.f.setDither(dither);
/* 575 */       invalidateSelf();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setFilterBitmap(boolean filter) {
/* 580 */       this.f.setFilterBitmap(filter);
/* 581 */       invalidateSelf();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIntrinsicWidth() {
/* 586 */       return this.d;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIntrinsicHeight() {
/* 591 */       return this.e;
/*     */     }
/*     */     
/*     */     public float a() {
/* 595 */       return this.n;
/*     */     }
/*     */     
/*     */     public void a(float param1Float) {
/* 599 */       this.n = param1Float;
/* 600 */       this.g.setStrokeWidth(param1Float);
/*     */     }
/*     */     
/*     */     public int b() {
/* 604 */       return this.o.getDefaultColor();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void a(ColorStateList param1ColorStateList) {
/* 614 */       if (param1ColorStateList == null) {
/* 615 */         this.n = 0.0F;
/* 616 */         this.o = ColorStateList.valueOf(0);
/* 617 */         this.g.setColor(0);
/*     */       } else {
/* 619 */         this.o = param1ColorStateList;
/* 620 */         this.g.setColor(this.o.getColorForState(getState(), -16777216));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(int param1Int) {
/* 626 */       a(ColorStateList.valueOf(param1Int));
/*     */     }
/*     */     
/*     */     public ColorStateList c() {
/* 630 */       return this.o;
/*     */     }
/*     */     
/*     */     public boolean d() {
/* 634 */       return this.m;
/*     */     }
/*     */     
/*     */     public void a(boolean param1Boolean) {
/* 638 */       this.m = param1Boolean;
/*     */     }
/*     */     
/*     */     public ScaleType e() {
/* 642 */       return this.p;
/*     */     }
/*     */     
/*     */     public void a(ScaleType param1ScaleType) {
/* 646 */       if (param1ScaleType == null) {
/*     */         return;
/*     */       }
/* 649 */       this.p = param1ScaleType;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/SelectableRoundedImageView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */