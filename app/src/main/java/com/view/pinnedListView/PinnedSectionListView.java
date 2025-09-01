/*     */ package com.view.pinnedListView;
/*     */ 
/*     */ import android.annotation.SuppressLint;
import android.content.Context;
/*     */ import android.database.DataSetObserver;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.PointF;
/*     */ import android.graphics.Rect;
/*     */ import android.graphics.drawable.GradientDrawable;
/*     */ import android.os.Parcelable;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.SoundEffectConstants;
import android.view.View;
/*     */ import android.view.ViewConfiguration;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.AbsListView;
/*     */ import android.widget.Adapter;
/*     */ import android.widget.AdapterView;
/*     */ import android.widget.HeaderViewListAdapter;
/*     */ import android.widget.ListAdapter;
/*     */ import android.widget.ListView;
/*     */ import android.widget.SectionIndexer;
/*     */ import com.InfoProvider;
/*     */ import com.general.files.OnScrollTouchDelegate;
/*     */ import com.utils.Utils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PinnedSectionListView
/*     */   extends ListView
/*     */ {
/*  53 */   private final Rect f = new Rect();
/*  54 */   private final PointF g = new PointF();
/*     */ 
/*     */   
/*     */   OnScrollListener a;
/*     */ 
/*     */   
/*     */   a b;
/*     */ 
/*     */   
/*     */   a c;
/*     */ 
/*     */   
/*     */   int d;
/*     */ 
/*     */   
/*     */   private int h;
/*     */ 
/*     */   
/*     */   private View i;
/*     */ 
/*     */   
/*     */   private MotionEvent j;
/*     */   
/*     */   private GradientDrawable k;
/*     */   
/*     */   private int l;
/*     */   
/*     */   OnScrollTouchDelegate e;
/*     */ 
/*     */   
/*  84 */   private final OnScrollListener m = new OnScrollListener()
/*     */     {
/*     */       public void onScrollStateChanged(AbsListView view, int scrollState)
/*     */       {
/*  88 */         if (a != null) {
/*  89 */           a.onScrollStateChanged(view, scrollState);
/*     */         }
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
/*  96 */         if (a != null) {
/*  97 */           a.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
/*     */         }
/*     */ 
/*     */         
/* 101 */         ListAdapter listAdapter = getAdapter();
/* 102 */         if (listAdapter == null || visibleItemCount == 0) {
/*     */           return;
/*     */         }
/* 105 */         boolean bool = PinnedSectionListView.isItemViewTypePinned(listAdapter, listAdapter
/* 106 */             .getItemViewType(firstVisibleItem));
/*     */         
/* 108 */         if (bool) {
/* 109 */           View view1 = getChildAt(0);
/* 110 */           if (view1 != null) {
/* 111 */             if (view1.getTop() == getPaddingTop()) {
/*     */ 
/*     */ 
/*     */               
/* 115 */               a();
/*     */             } else {
/*     */               
/* 118 */               a(firstVisibleItem, firstVisibleItem, visibleItemCount);
/*     */             } 
/*     */           }
/*     */         } else {
/* 122 */           int i = b(firstVisibleItem);
/* 123 */           if (i > -1) {
/* 124 */             a(i, firstVisibleItem, visibleItemCount);
/*     */           } else {
/*     */             
/* 127 */             a();
/*     */           } 
/*     */         } 
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   private final DataSetObserver n = new DataSetObserver()
/*     */     {
/*     */       public void onChanged() {
/* 141 */         b();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void onInvalidated() {
/* 147 */         b();
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public PinnedSectionListView(Context context, AttributeSet attrs) {
/* 153 */     super(context, attrs);
/* 154 */     a(context);
/* 155 */     c();
/*     */   }
/*     */   private int o;
/*     */   public PinnedSectionListView(Context context, AttributeSet attrs, int defStyle) {
/* 159 */     super(context, attrs, defStyle);
/* 160 */     a(context);
/* 161 */     c();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isItemViewTypePinned(ListAdapter adapter, int viewType) {
/* 167 */     if (adapter instanceof HeaderViewListAdapter) {
/* 168 */       adapter = ((HeaderViewListAdapter)adapter).getWrappedAdapter();
/*     */     }
/* 170 */     return ((PinnedSectionListAdapter)adapter).isItemViewTypePinned(viewType);
/*     */   }
/*     */   
/*     */   private void c() {
/* 174 */     setOnScrollListener(this.m);
/* 175 */     this.h = ViewConfiguration.get(getContext()).getScaledTouchSlop();
/* 176 */     initShadow(true);
/*     */   }
/*     */   
/*     */   public void setShadowVisible(boolean visible) {
/* 180 */     initShadow(visible);
/* 181 */     if (this.c != null) {
/* 182 */       View view = this.c.a;
/* 183 */       invalidate(view.getLeft(), view.getTop(), view.getRight(), view.getBottom() + this.o);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initShadow(boolean visible) {
/* 190 */     if (visible) {
/* 191 */       if (this.k == null) {
/* 192 */         this
/* 193 */           .k = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] { Color.parseColor("#ffa0a0a0"), Color.parseColor("#50a0a0a0"), Color.parseColor("#00a0a0a0") });
/* 194 */         this.o = (int)(8.0F * (getResources().getDisplayMetrics()).density);
/*     */       }
/*     */     
/* 197 */     } else if (this.k != null) {
/* 198 */       this.k = null;
/* 199 */       this.o = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTouchDegateListener(OnScrollTouchDelegate onTouchDeledate) {
/* 205 */     this.e = onTouchDeledate;
/*     */   }
/*     */   
/*     */   private void a(Context paramContext) {
/* 209 */     if (!isInEditMode() && Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("No")) {
/* 210 */       new InfoProvider(paramContext);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onTouchEvent(MotionEvent ev) {
/* 217 */     if (this.e != null) {
/* 218 */       this.e.onTouchDelegate();
/*     */     }
/*     */     
/* 221 */     return super.onTouchEvent(ev);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void a(int paramInt) {
/* 232 */     a a1 = this.b;
/* 233 */     this.b = null;
/*     */ 
/*     */     
/* 236 */     if (a1 == null) {
/* 237 */       a1 = new a();
/*     */     }
/* 239 */     View view = getAdapter().getView(paramInt, a1.a, (ViewGroup)this);
/*     */ 
/*     */     
/* 242 */     LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
/* 243 */     if (layoutParams == null) {
/* 244 */       layoutParams = (LayoutParams)generateDefaultLayoutParams();
/* 245 */       view.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
/*     */     } 
/*     */     
/* 248 */     int i = MeasureSpec.getMode(layoutParams.height);
/* 249 */     int j = MeasureSpec.getSize(layoutParams.height);
/*     */     
/* 251 */     if (i == 0) {
/* 252 */       i = 1073741824;
/*     */     }
/* 254 */     int k = getHeight() - getListPaddingTop() - getListPaddingBottom();
/* 255 */     if (j > k) {
/* 256 */       j = k;
/*     */     }
/*     */     
/* 259 */     int m = MeasureSpec.makeMeasureSpec(getWidth() - getListPaddingLeft() - getListPaddingRight(), MeasureSpec.EXACTLY);
/*     */     
/* 261 */     @SuppressLint("WrongConstant") int n = MeasureSpec.makeMeasureSpec(j, i);
/* 262 */     view.measure(m, n);
/* 263 */     view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
/* 264 */     this.d = 0;
/*     */ 
/*     */     
/* 267 */     a1.a = view;
/* 268 */     a1.b = paramInt;
/* 269 */     a1.c = getAdapter().getItemId(paramInt);
/*     */ 
/*     */     
/* 272 */     this.c = a1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void a() {
/* 279 */     if (this.c != null) {
/*     */       
/* 281 */       this.b = this.c;
/* 282 */       this.c = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void a(int paramInt1, int paramInt2, int paramInt3) {
/* 290 */     if (paramInt3 < 2) {
/*     */       
/* 292 */       a();
/*     */       
/*     */       return;
/*     */     } 
/* 296 */     if (this.c != null && this.c.b != paramInt1)
/*     */     {
/*     */ 
/*     */       
/* 300 */       a();
/*     */     }
/*     */     
/* 303 */     if (this.c == null) {
/* 304 */       a(paramInt1);
/*     */     }
/*     */ 
/*     */     
/* 308 */     int i = paramInt1 + 1;
/* 309 */     if (i < getCount()) {
/* 310 */       int j = a(i, paramInt3 - i - paramInt2);
/*     */       
/* 312 */       if (j > -1) {
/* 313 */         View view = getChildAt(j - paramInt2);
/* 314 */         if (view != null) {
/* 315 */           int k = this.c.a.getBottom() + getPaddingTop();
/* 316 */           this.l = view.getTop() - k;
/* 317 */           if (this.l < 0) {
/*     */             
/* 319 */             this.d = this.l;
/*     */           } else {
/*     */             
/* 322 */             this.d = 0;
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 327 */         this.d = 0;
/* 328 */         this.l = Integer.MAX_VALUE;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   int a(int paramInt1, int paramInt2) {
/* 335 */     ListAdapter listAdapter = getAdapter();
/*     */     
/* 337 */     int i = listAdapter.getCount();
/* 338 */     if (getLastVisiblePosition() >= i) {
/* 339 */       return -1;
/*     */     }
/* 341 */     if (paramInt1 + paramInt2 >= i)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 348 */       paramInt2 = i - paramInt1;
/*     */     }
/*     */     
/* 351 */     for (byte b = 0; b < paramInt2; b++) {
/* 352 */       int j = paramInt1 + b;
/* 353 */       int k = listAdapter.getItemViewType(j);
/* 354 */       if (isItemViewTypePinned(listAdapter, k))
/* 355 */         return j; 
/*     */     } 
/* 357 */     return -1;
/*     */   }
/*     */   
/*     */   int b(int paramInt) {
/* 361 */     ListAdapter listAdapter = getAdapter();
/*     */     
/* 363 */     if (paramInt >= listAdapter.getCount()) {
/* 364 */       return -1;
/*     */     }
/* 366 */     if (listAdapter instanceof SectionIndexer) {
/*     */       
/* 368 */       SectionIndexer sectionIndexer = (SectionIndexer)listAdapter;
/* 369 */       int j = sectionIndexer.getSectionForPosition(paramInt);
/* 370 */       int k = sectionIndexer.getPositionForSection(j);
/* 371 */       int m = listAdapter.getItemViewType(k);
/* 372 */       if (isItemViewTypePinned(listAdapter, m)) {
/* 373 */         return k;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 378 */     for (int i = paramInt; i >= 0; i--) {
/* 379 */       int j = listAdapter.getItemViewType(i);
/* 380 */       if (isItemViewTypePinned(listAdapter, j))
/* 381 */         return i; 
/*     */     } 
/* 383 */     return -1;
/*     */   }
/*     */   
/*     */   void b() {
/* 387 */     a();
/* 388 */     ListAdapter listAdapter = getAdapter();
/* 389 */     if (listAdapter != null && listAdapter.getCount() > 0) {
/* 390 */       int i = getFirstVisiblePosition();
/* 391 */       int j = b(i);
/* 392 */       if (j == -1)
/*     */         return; 
/* 394 */       a(j, i, 
/* 395 */           getLastVisiblePosition() - i);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOnScrollListener(OnScrollListener listener) {
/* 401 */     if (listener == this.m) {
/* 402 */       super.setOnScrollListener(listener);
/*     */     } else {
/* 404 */       this.a = listener;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRestoreInstanceState(Parcelable state) {
/* 410 */     super.onRestoreInstanceState(state);
/* 411 */     post(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 415 */             b();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAdapter(ListAdapter adapter) {
/* 424 */     if (adapter != null) {
/* 425 */       if (!(adapter instanceof PinnedSectionListAdapter))
/* 426 */         throw new IllegalArgumentException("Does your adapter implement Adapter?"); 
/* 427 */       if (adapter.getViewTypeCount() < 2) {
/* 428 */         throw new IllegalArgumentException("Does your adapter handle at least two types of views in getViewTypeCount() method: Items and sections?");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 433 */     ListAdapter listAdapter = getAdapter();
/* 434 */     if (listAdapter != null)
/* 435 */       listAdapter.unregisterDataSetObserver(this.n); 
/* 436 */     if (adapter != null) {
/* 437 */       adapter.registerDataSetObserver(this.n);
/*     */     }
/*     */     
/* 440 */     if (listAdapter != adapter) {
/* 441 */       a();
/*     */     }
/* 443 */     super.setAdapter(adapter);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onLayout(boolean changed, int l, int t, int r, int b) {
/* 448 */     super.onLayout(changed, l, t, r, b);
/* 449 */     if (this.c != null) {
/* 450 */       int i = r - l - getPaddingLeft() - getPaddingRight();
/* 451 */       int j = this.c.a.getWidth();
/* 452 */       if (i != j) {
/* 453 */         b();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dispatchDraw(Canvas canvas) {
/* 460 */     super.dispatchDraw(canvas);
/*     */     
/* 462 */     if (this.c != null) {
/*     */ 
/*     */       
/* 465 */       int i = getListPaddingLeft();
/* 466 */       int j = getListPaddingTop();
/* 467 */       View view = this.c.a;
/*     */ 
/*     */       
/* 470 */       canvas.save();
/*     */ 
/*     */       
/* 473 */       int k = view.getHeight() + ((this.k == null) ? 0 : Math.min(this.o, this.l));
/* 474 */       canvas.clipRect(i, j, i + view.getWidth(), j + k);
/*     */       
/* 476 */       canvas.translate(i, (j + this.d));
/* 477 */       drawChild(canvas, this.c.a, getDrawingTime());
/*     */       
/* 479 */       if (this.k != null && this.l > 0) {
/* 480 */         this.k.setBounds(this.c.a.getLeft(), this.c.a.getBottom(), this.c.a
/* 481 */             .getRight(), this.c.a.getBottom() + this.o);
/* 482 */         this.k.draw(canvas);
/*     */       } 
/*     */       
/* 485 */       canvas.restore();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean dispatchTouchEvent(MotionEvent ev) {
/* 492 */     float f1 = ev.getX();
/* 493 */     float f2 = ev.getY();
/* 494 */     int i = ev.getAction();
/*     */     
/* 496 */     if (i == 0 && this.i == null && this.c != null && 
/* 497 */       a(this.c.a, f1, f2)) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 502 */       this.i = this.c.a;
/* 503 */       this.g.x = f1;
/* 504 */       this.g.y = f2;
/*     */ 
/*     */       
/* 507 */       this.j = MotionEvent.obtain(ev);
/*     */     } 
/*     */     
/* 510 */     if (this.i != null) {
/* 511 */       if (a(this.i, f1, f2));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 516 */       if (i == 1) {
/*     */         
/* 518 */         super.dispatchTouchEvent(ev);
/* 519 */         e();
/* 520 */         d();
/*     */       }
/* 522 */       else if (i == 3) {
/* 523 */         d();
/*     */       }
/* 525 */       else if (i == 2 && 
/* 526 */         Math.abs(f2 - this.g.y) > this.h) {
/*     */ 
/*     */         
/* 529 */         MotionEvent motionEvent = MotionEvent.obtain(ev);
/* 530 */         motionEvent.setAction(3);
/* 531 */         this.i.dispatchTouchEvent(motionEvent);
/* 532 */         motionEvent.recycle();
/*     */ 
/*     */ 
/*     */         
/* 536 */         super.dispatchTouchEvent(this.j);
/* 537 */         super.dispatchTouchEvent(ev);
/* 538 */         d();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 543 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 547 */     return super.dispatchTouchEvent(ev);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(View paramView, float paramFloat1, float paramFloat2) {
/* 553 */     paramView.getHitRect(this.f);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 558 */     this.f.top += this.d;
/*     */     
/* 560 */     this.f.bottom += this.d + getPaddingTop();
/* 561 */     this.f.left += getPaddingLeft();
/* 562 */     this.f.right -= getPaddingRight();
/* 563 */     return this.f.contains((int)paramFloat1, (int)paramFloat2);
/*     */   }
/*     */   
/*     */   private void d() {
/* 567 */     this.i = null;
/* 568 */     if (this.j != null) {
/* 569 */       this.j.recycle();
/* 570 */       this.j = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean e() {
/* 575 */     if (this.c == null) {
/* 576 */       return false;
/*     */     }
/* 578 */     OnItemClickListener onItemClickListener = getOnItemClickListener();
/* 579 */     if (onItemClickListener != null && getAdapter().isEnabled(this.c.b)) {
/* 580 */       View view = this.c.a;
/* 581 */       playSoundEffect(SoundEffectConstants.CLICK);
/* 582 */       if (view != null) {
/* 583 */         view.sendAccessibilityEvent(1);
/*     */       }
/* 585 */       onItemClickListener.onItemClick((AdapterView)this, view, this.c.b, this.c.c);
/* 586 */       return true;
/*     */     } 
/* 588 */     return false;
/*     */   }
/*     */   
/*     */   public static interface PinnedSectionListAdapter extends ListAdapter {
/*     */     boolean isItemViewTypePinned(int param1Int);
/*     */   }
/*     */   
/*     */   static class a {
/*     */     public View a;
/*     */     public int b;
/*     */     public long c;
/*     */   }
/*     */   
/*     */   public final class BuildConfig {
/*     */     public static final boolean DEBUG = true;
/*     */     
/*     */     public BuildConfig(PinnedSectionListView this$0) {}
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/pinnedListView/PinnedSectionListView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */