/*     */ package com.view;
/*     */ 
/*     */ import android.annotation.SuppressLint;
import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.PorterDuff;
/*     */ import android.graphics.Rect;
/*     */ import android.graphics.drawable.ColorDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ import com.InfoProvider;
/*     */ import com.paratodo.user.R;
import com.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimelineView
/*     */   extends View
/*     */ {
/*     */   private Drawable a;
/*     */   private Drawable b;
/*     */   private Drawable c;
/*     */   private int d;
/*     */   private int e;
/*     */   private int f;
/*     */   private int g;
/*     */   private boolean h;
/*     */   private Rect i;
/*     */   private Context j;
/*     */   
/*     */   public TimelineView(Context context, AttributeSet attrs) {
/*  36 */     super(context, attrs);
/*  37 */     this.j = context;
/*  38 */     a(this.j);
/*  39 */     a(attrs);
/*     */   }
/*     */   
/*     */   @SuppressLint("ResourceType")
private void a(AttributeSet paramAttributeSet) {
/*  43 */     TypedArray typedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.timeline_style);
/*  44 */     this.a = typedArray.getDrawable(R.styleable.timeline_style_marker);
/*  45 */     this.b = typedArray.getDrawable(R.styleable.timeline_style_line);
/*  46 */     this.c = typedArray.getDrawable(R.styleable.timeline_style_line);
/*  47 */     this.d = typedArray.getDimensionPixelSize(R.styleable.timeline_style_markerSize, Utils.dpToPx(20.0F, this.j));
/*  48 */     this.e = typedArray.getDimensionPixelSize(R.styleable.timeline_style_lineSize, Utils.dpToPx(2.0F, this.j));
/*  49 */     this.f = typedArray.getInt(R.styleable.timeline_style_lineOrientation, 1);
/*  50 */     this.g = typedArray.getDimensionPixelSize(R.styleable.timeline_style_linePadding, 0);
/*  51 */     this.h = typedArray.getBoolean(R.styleable.timeline_style_markerInCenter, true);
/*  52 */     typedArray.recycle();
/*     */     
/*  54 */     if (this.a == null) {
/*  55 */       this.a = this.j.getResources().getDrawable(R.drawable.marker_prj);
/*     */     }
/*     */     
/*  58 */     if (this.b == null && this.c == null) {
/*  59 */       this.b = (Drawable)new ColorDrawable(this.j.getResources().getColor(17170432));
/*  60 */       this.c = (Drawable)new ColorDrawable(this.j.getResources().getColor(17170432));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/*  66 */     super.onMeasure(widthMeasureSpec, heightMeasureSpec);
/*     */     
/*  68 */     int i = this.d + getPaddingLeft() + getPaddingRight();
/*  69 */     int j = this.d + getPaddingTop() + getPaddingBottom();
/*     */ 
/*     */     
/*  72 */     int k = resolveSizeAndState(i, widthMeasureSpec, 0);
/*  73 */     int m = resolveSizeAndState(j, heightMeasureSpec, 0);
/*     */     
/*  75 */     setMeasuredDimension(k, m);
/*  76 */     a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
/*  81 */     super.onSizeChanged(w, h, oldw, oldh);
/*     */ 
/*     */     
/*  84 */     a();
/*     */   }
/*     */   
/*     */   private void a() {
/*  88 */     int i = getPaddingLeft();
/*  89 */     int j = getPaddingRight();
/*  90 */     int k = getPaddingTop();
/*  91 */     int m = getPaddingBottom();
/*     */     
/*  93 */     int n = getWidth();
/*  94 */     int i1 = getHeight();
/*     */     
/*  96 */     int i2 = n - i - j;
/*  97 */     int i3 = i1 - k - m;
/*     */     
/*  99 */     int i4 = Math.min(this.d, Math.min(i2, i3));
/*     */     
/* 101 */     if (this.h) {
/*     */       
/* 103 */       if (this.a != null) {
/* 104 */         this.a.setBounds(n / 2 - i4 / 2, i1 / 2 - i4 / 2, n / 2 + i4 / 2, i1 / 2 + i4 / 2);
/* 105 */         this.i = this.a.getBounds();
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 110 */     else if (this.a != null) {
/* 111 */       this.a.setBounds(i, k, i + i4, k + i4);
/* 112 */       this.i = this.a.getBounds();
/*     */     } 
/*     */ 
/*     */     
/* 116 */     int i5 = this.i.centerX();
/* 117 */     int i6 = i5 - (this.e >> 1);
/*     */     
/* 119 */     if (this.f == 0) {
/*     */ 
/*     */       
/* 122 */       if (this.b != null) {
/* 123 */         this.b.setBounds(0, k + this.i.height() / 2, this.i.left - this.g, this.i.height() / 2 + k + this.e);
/*     */       }
/*     */       
/* 126 */       if (this.c != null) {
/* 127 */         this.c.setBounds(this.i.right + this.g, k + this.i.height() / 2, n, this.i.height() / 2 + k + this.e);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 132 */       if (this.b != null) {
/* 133 */         this.b.setBounds(i6, 0, this.e + i6, this.i.top - this.g);
/*     */       }
/*     */       
/* 136 */       if (this.c != null) {
/* 137 */         this.c.setBounds(i6, this.i.bottom + this.g, this.e + i6, i1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(Context paramContext) {
/* 143 */     if (!isInEditMode() && Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("No")) {
/* 144 */       new InfoProvider(paramContext);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 150 */     super.onDraw(canvas);
/* 151 */     if (this.a != null) {
/* 152 */       this.a.draw(canvas);
/*     */     }
/*     */     
/* 155 */     if (this.b != null) {
/* 156 */       this.b.draw(canvas);
/*     */     }
/*     */     
/* 159 */     if (this.c != null) {
/* 160 */       this.c.draw(canvas);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMarker(Drawable marker) {
/* 170 */     this.a = marker;
/* 171 */     a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMarker(Drawable marker, int color) {
/* 181 */     this.a = marker;
/* 182 */     this.a.setColorFilter(color, PorterDuff.Mode.SRC);
/* 183 */     a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMarkerColor(int color) {
/* 192 */     this.a.setColorFilter(color, PorterDuff.Mode.SRC);
/* 193 */     a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartLine(int color, int viewType) {
/* 203 */     this.b = (Drawable)new ColorDrawable(color);
/* 204 */     initLine(viewType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndLine(int color, int viewType) {
/* 214 */     this.c = (Drawable)new ColorDrawable(color);
/* 215 */     initLine(viewType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMarkerSize(int markerSize) {
/* 224 */     this.d = markerSize;
/* 225 */     a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineSize(int lineSize) {
/* 234 */     this.e = lineSize;
/* 235 */     a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLinePadding(int padding) {
/* 244 */     this.g = padding;
/* 245 */     a();
/*     */   }
/*     */   
/*     */   private void setStartLine(Drawable startLine) {
/* 249 */     this.b = startLine;
/* 250 */     a();
/*     */   }
/*     */   
/*     */   private void setEndLine(Drawable endLine) {
/* 254 */     this.c = endLine;
/* 255 */     a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initLine(int viewType) {
/* 264 */     if (viewType == 1) {
/* 265 */       setStartLine((Drawable)null);
/* 266 */     } else if (viewType == 2) {
/* 267 */       setEndLine((Drawable)null);
/* 268 */     } else if (viewType == 3) {
/* 269 */       setStartLine((Drawable)null);
/* 270 */       setEndLine((Drawable)null);
/*     */     } 
/* 272 */     a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getTimeLineViewType(int position, int total_size) {
/* 283 */     if (total_size == 1)
/* 284 */       return 3; 
/* 285 */     if (position == 0)
/* 286 */       return 1; 
/* 287 */     if (position == total_size - 1) {
/* 288 */       return 2;
/*     */     }
/* 290 */     return 0;
/*     */   }
/*     */   
/*     */   public class LineType {
/*     */     public static final int NORMAL = 0;
/*     */     public static final int BEGIN = 1;
/*     */     public static final int END = 2;
/*     */     public static final int ONLYONE = 3;
/*     */     
/*     */     public LineType(TimelineView this$0) {}
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/TimelineView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */