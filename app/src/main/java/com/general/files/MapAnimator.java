/*     */ package com.general.files;
/*     */ 
/*     */

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.utils.Utils;

import java.util.List;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapAnimator
/*     */ {
/*  29 */   public static int NON_HIGHLIGHT_COLOR = Color.parseColor("#FFA7A6A6");
/*  30 */   public static int HIGHLIGHT_COLOR = -16777216;
/*     */   
/*     */   public static MapAnimator mapAnimator;
/*     */   private Polyline d;
/*     */   private Polyline e;
/*     */   private PolylineOptions f;
/*     */   private AnimatorSet g;
/*     */   private AnimatorSet h;
/*     */   Context a;
/*  39 */   List<LatLng> b = null;
/*  40 */   GoogleMap c = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MapAnimator getInstance() {
/*  47 */     if (mapAnimator == null) mapAnimator = new MapAnimator(); 
/*  48 */     return mapAnimator;
/*     */   }
/*     */   
/*     */   public void stopRouteAnim() {
/*     */     try {
/*  53 */       if (this.g != null) {
/*  54 */         this.g.removeAllListeners();
/*  55 */         this.g.end();
/*  56 */         this.g.cancel();
/*     */       } 
/*  58 */       if (this.h != null) {
/*  59 */         this.h.removeAllListeners();
/*  60 */         this.h.end();
/*  61 */         this.h.cancel();
/*     */       } 
/*  63 */       if (this.d != null) {
/*  64 */         this.d.remove();
/*  65 */         this.d = null;
/*     */       } 
/*  67 */       if (this.e != null) {
/*  68 */         this.e.remove();
/*  69 */         this.e = null;
/*     */       } 
/*     */       
/*  72 */       if (this.b != null) {
/*  73 */         this.b.clear();
/*     */       }
/*  75 */       if (this.c != null) {
/*  76 */         this.c = null;
/*     */       }
/*     */       
/*  79 */       if (this.f != null) {
/*  80 */         this.f = null;
/*     */       }
/*  82 */       mapAnimator = null;
/*     */ 
/*     */     
/*     */     }
/*  86 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void animateRoute(GoogleMap googleMap, List<LatLng> routePointList, Context mContext) {
/*  92 */     this.a = mContext;
/*  93 */     this.b = routePointList;
/*  94 */     this.c = googleMap;
/*  95 */     if (this.g == null) {
/*  96 */       this.g = new AnimatorSet();
/*     */     } else {
/*  98 */       this.g.removeAllListeners();
/*  99 */       this.g.end();
/* 100 */       this.g.cancel();
/*     */       
/* 102 */       this.g = new AnimatorSet();
/*     */     } 
/* 104 */     if (this.h == null) {
/* 105 */       this.h = new AnimatorSet();
/*     */     } else {
/* 107 */       this.h.removeAllListeners();
/* 108 */       this.h.end();
/* 109 */       this.h.cancel();
/*     */       
/* 111 */       this.h = new AnimatorSet();
/*     */     } 
/*     */     
/* 114 */     if (this.e != null) this.e.remove(); 
/* 115 */     if (this.d != null) this.d.remove();
/*     */ 
/*     */     
/* 118 */     PolylineOptions polylineOptions = (new PolylineOptions()).add(routePointList.get(0)).color(NON_HIGHLIGHT_COLOR).width(Utils.dipToPixels(mContext, 5.0F));
/* 119 */     this.d = googleMap.addPolyline(polylineOptions);
/*     */     
/* 121 */     this.f = (new PolylineOptions()).add(routePointList.get(0)).color(HIGHLIGHT_COLOR).width(Utils.dipToPixels(mContext, 5.0F));
/* 122 */     this.e = googleMap.addPolyline(this.f);
/*     */     
/* 124 */     ValueAnimator valueAnimator1 = ValueAnimator.ofInt(new int[] { 0, 100 });
/* 125 */     valueAnimator1.setDuration(1800L);
/* 126 */     valueAnimator1.setInterpolator((TimeInterpolator)new DecelerateInterpolator());
/* 127 */     valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
/*     */         {
/*     */           public void onAnimationUpdate(ValueAnimator animation) {
/* 130 */             if (d == null || a == null) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/* 135 */             List list1 = d.getPoints();
/*     */             
/* 137 */             int i = ((Integer)animation.getAnimatedValue()).intValue();
/* 138 */             int j = list1.size();
/* 139 */             int k = (int)(j * i / 100.0F);
/* 140 */             List list2 = list1.subList(0, k);
/* 141 */             list2.clear();
/*     */             
/* 143 */             d.setPoints(list1);
/*     */           }
/*     */         });
/* 146 */     valueAnimator1.addListener(new Animator.AnimatorListener()
/*     */         {
/*     */           public void onAnimationStart(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationEnd(Animator animation) {
/* 154 */             if (d == null) {
/*     */               return;
/*     */             }
/*     */             
/* 158 */             d.setColor(MapAnimator.NON_HIGHLIGHT_COLOR);
/* 159 */             d.setPoints(e.getPoints());
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationCancel(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationRepeat(Animator animation) {}
/*     */         });
/* 174 */     ValueAnimator valueAnimator2 = ValueAnimator.ofObject((TypeEvaluator)new ArgbEvaluator(), new Object[] { Integer.valueOf(NON_HIGHLIGHT_COLOR), Integer.valueOf(HIGHLIGHT_COLOR) });
/* 175 */     valueAnimator2.setInterpolator((TimeInterpolator)new AccelerateInterpolator());
/* 176 */     valueAnimator2.setDuration(15L);
/*     */     
/* 178 */     valueAnimator2.addUpdateListener(paramValueAnimator -> {
/*     */           if (this.e == null) {
/*     */             return;
/*     */           }
/*     */           
/*     */           this.e.setColor(((Integer)paramValueAnimator.getAnimatedValue()).intValue());
/*     */         });
/* 185 */     if (routePointList == null || routePointList.size() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 189 */     ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this, "routeIncreaseForward", new RouteEvaluator(this), routePointList.toArray());
/* 190 */     objectAnimator.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
/* 191 */     objectAnimator.addListener(new Animator.AnimatorListener()
/*     */         {
/*     */           public void onAnimationStart(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationEnd(Animator animation) {
/* 199 */             if (e == null) {
/*     */               return;
/*     */             }
/* 202 */             e.setPoints(d.getPoints());
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationCancel(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationRepeat(Animator animation) {}
/*     */         });
/* 215 */     objectAnimator.setDuration(2500L);
/*     */ 
/*     */     
/* 218 */     this.g.playSequentially(new Animator[] { (Animator)objectAnimator, (Animator)valueAnimator1 });
/*     */     
/* 220 */     this.g.addListener(new Animator.AnimatorListener()
/*     */         {
/*     */           public void onAnimationStart(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationEnd(Animator animation) {
/* 228 */             animation.start();
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationCancel(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationRepeat(Animator animation) {}
/*     */         });
/* 242 */     this.h.playSequentially(new Animator[] { (Animator)valueAnimator2, (Animator)valueAnimator1 });
/*     */     
/* 244 */     this.h.setStartDelay(10L);
/*     */     
/* 246 */     this.h.addListener(new Animator.AnimatorListener()
/*     */         {
/*     */           public void onAnimationStart(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationEnd(Animator animation) {
/* 254 */             animation.start();
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationCancel(Animator animation) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onAnimationRepeat(Animator animation) {}
/*     */         });
/* 268 */     this.g.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRouteIncreaseForward(LatLng endLatLng) {
/* 276 */     List<LatLng> list = this.e.getPoints();
/* 277 */     list.add(endLatLng);
/* 278 */     this.e.setPoints(list);
/*     */   }
/*     */   
/*     */   public class RouteEvaluator implements TypeEvaluator<LatLng> { public RouteEvaluator(MapAnimator this$0) {}
/*     */     
/*     */     public LatLng evaluate(float t, LatLng startPoint, LatLng endPoint) {
/* 284 */       double d1 = startPoint.latitude + t * (endPoint.latitude - startPoint.latitude);
/* 285 */       double d2 = startPoint.longitude + t * (endPoint.longitude - startPoint.longitude);
/* 286 */       return new LatLng(d1, d2);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfile/classes.jar!/com/general/files/MapAnimator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */