/*     */ package com.utils;
/*     */ 
/*     */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.location.Location;
import android.view.animation.LinearInterpolator;

import com.general.files.GeneralFunctions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnimateMarker
/*     */ {
/*     */   public boolean driverMarkerAnimFinished = true;
/*  25 */   public ArrayList<HashMap<String, String>> driverMarkersPositionList = new ArrayList<>();
/*  26 */   public HashMap<String, String> toPositionLat = new HashMap<>();
/*  27 */   public HashMap<String, String> toPositionLong = new HashMap<>();
/*  28 */   public LatLng currentLng = null;
/*     */   ValueAnimator a;
/*     */   
/*     */   public static void animateMarker(Marker marker, GoogleMap gMap, Location toPosition, float rotationAngle, float duration) {
/*  32 */     if (marker == null || toPosition == null || gMap == null) {
/*     */       return;
/*     */     }
/*     */     
/*  36 */     LatLng latLng1 = marker.getPosition();
/*  37 */     LatLng latLng2 = new LatLng(toPosition.getLatitude(), toPosition.getLongitude());
/*  38 */     float f = marker.getRotation();
/*     */     
/*  40 */     LatLngInterpolator.LinearFixed linearFixed = new LatLngInterpolator.LinearFixed();
/*  41 */     ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
/*  42 */     valueAnimator.setDuration((long)duration);
/*  43 */     valueAnimator.setInterpolator((TimeInterpolator)new LinearInterpolator());

/*  44 */     valueAnimator.addUpdateListener(paramValueAnimator -> {
/*     */           try {
/*     */             float f1 = paramValueAnimator.getAnimatedFraction();
/*     */             LatLng latLng = linearFixed.interpolate(f1, latLng1, latLng2);
/*     */             marker.setPosition(latLng);
/*     */             marker.setRotation(a(f, rotationAngle, duration));
/*  50 */           } catch (Exception exception) {}
/*     */         });
/*     */ 
/*     */     
/*  54 */     valueAnimator.start();
/*     */   }
/*     */   
/*     */   private static float a(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  58 */     float f4, f1 = paramFloat3 - paramFloat2;
/*  59 */     float f2 = (f1 + 360.0F) % 360.0F;
/*     */     
/*  61 */     float f3 = (f2 > 180.0F) ? -1.0F : 1.0F;
/*     */     
/*  63 */     if (f3 > 0.0F) {
/*  64 */       f4 = f2;
/*     */     } else {
/*  66 */       f4 = f2 - 360.0F;
/*     */     } 
/*     */     
/*  69 */     float f5 = paramFloat1 * f4 + paramFloat2;
/*  70 */     return (f5 + 360.0F) % 360.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<String, String> getNextBufferedLocationData(String iDriverId) {
/*  75 */     for (byte b = 0; b < this.driverMarkersPositionList.size(); b++) {
/*  76 */       HashMap<String, String> hashMap = this.driverMarkersPositionList.get(b);
/*  77 */       if (((String)hashMap.get("iDriverId")).equals(iDriverId)) {
/*  78 */         hashMap.put("Position", "" + b);
/*  79 */         return hashMap;
/*     */       } 
/*     */     } 
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeBufferedLocation(String iDriverId, String LocTime) {
/*  87 */     for (byte b = 0; b < this.driverMarkersPositionList.size(); b++) {
/*  88 */       HashMap hashMap = this.driverMarkersPositionList.get(b);
/*  89 */       if (((String)hashMap.get("LocTime")).equals(LocTime) && ((String)hashMap.get("iDriverId")).equals(iDriverId)) {
/*  90 */         this.driverMarkersPositionList.remove(b);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addToListAndStartNext(Marker marker, GoogleMap gMap, Location toPosition, float rotationAngle, float duration, String iDriverId, String locTime) {
/*  98 */     if (this.a != null) {
/*  99 */       this.a.cancel();
/*     */       
/* 101 */       this.a = null;
/*     */     } 
/*     */     
/* 104 */     animateMarker(marker, gMap, toPosition, rotationAngle, duration, iDriverId, locTime);
/*     */   }
/*     */   
/*     */   public void animateMarker(Marker marker, GoogleMap gMap, Location toPosition, float rotationAngle, float duration, String iDriverId, String locTime) {
/* 108 */     if (marker == null || toPosition == null || gMap == null) {
/*     */       return;
/*     */     }
/* 111 */     LatLng latLng1 = marker.getPosition();
/* 112 */     LatLng latLng2 = new LatLng(toPosition.getLatitude(), toPosition.getLongitude());
/* 113 */     float f = marker.getRotation();
/*     */ 
/*     */     
/* 116 */     this.driverMarkerAnimFinished = false;
/*     */     
/* 118 */     LatLngInterpolator.LinearFixed linearFixed = new LatLngInterpolator.LinearFixed();
/* 119 */     ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
/* 120 */     valueAnimator.setDuration((long)duration);
/* 121 */     valueAnimator.setInterpolator((TimeInterpolator)new LinearInterpolator());
/*     */ 
/*     */ 
/*     */     
/* 125 */     this.a = valueAnimator;
/*     */     
/* 127 */     valueAnimator.addUpdateListener(paramValueAnimator -> {
/*     */           try {
/*     */             float f1 = paramValueAnimator.getAnimatedFraction();
/*     */ 
/*     */             
/*     */             float f2 = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
/*     */             
/*     */             LatLng latLng = linearFixed.interpolate(f1, latLng1, latLng2);
/*     */             
/*     */             this.currentLng = latLng;
/*     */             
/*     */             marker.setPosition(latLng);
/*     */             
/*     */             marker.setRotation(a(f1, f2, rotationAngle));
/* 141 */           } catch (Exception exception) {}
/*     */         });
/*     */ 
/*     */     
/* 145 */     valueAnimator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter()
/*     */         {
/*     */           public void onAnimationCancel(Animator animation) {
/* 148 */             marker.setRotation(rotationAngle);
/* 149 */             super.onAnimationCancel(animation);
/*     */           }
/*     */ 
/*     */           
/*     */           public void onAnimationEnd(Animator animation) {
/* 154 */             driverMarkerAnimFinished = true;
/* 155 */             marker.setRotation(rotationAngle);
/*     */           }
/*     */         });
/*     */     
/* 159 */     valueAnimator.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void animEnd(Marker marker, GoogleMap gMap, Location toPosition, float rotationAngle, float duration, String iDriverId, String locTime) {
/* 166 */     removeBufferedLocation(iDriverId, locTime);
/*     */     
/* 168 */     HashMap<String, String> hashMap = getNextBufferedLocationData(iDriverId);
/* 169 */     if (hashMap != null) {
/* 170 */       Location location = new Location("gps");
/* 171 */       location.setLatitude(GeneralFunctions.parseDoubleValue(0.0D, hashMap.get("vLatitude")).doubleValue());
/* 172 */       location.setLongitude(GeneralFunctions.parseDoubleValue(0.0D, hashMap.get("vLongitude")).doubleValue());
/* 173 */       float f = (this.driverMarkersPositionList.size() > 0) ? (duration / 2.0F) : duration;
/* 174 */       animateMarker(marker, gMap, location, GeneralFunctions.parseFloatValue(marker.getRotation(), hashMap.get("RotationAngle")).floatValue(), f, iDriverId, hashMap.get("LocTime"));
/*     */     } 
/* 176 */     marker.setVisible(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<String, String> getLastLocationDataOfMarker(Marker marker) {
/* 181 */     if (marker == null || marker.getTitle() == null || marker.getTitle() == "") {
/* 182 */       return new HashMap<>();
/*     */     }
/*     */     
/* 185 */     int i = this.driverMarkersPositionList.size() - 1;
/*     */     
/* 187 */     for (byte b = 0; b < this.driverMarkersPositionList.size(); b++) {
/* 188 */       HashMap<String, String> hashMap = this.driverMarkersPositionList.get(i - b);
/*     */       
/* 190 */       if (((String)hashMap.get("iDriverId")).equals(marker.getTitle().replace("DriverId", ""))) {
/* 191 */         return hashMap;
/*     */       }
/*     */     } 
/*     */     
/* 195 */     return new HashMap<>();
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
/*     */   public double bearingBetweenLocations(LatLng latLng1, LatLng latLng2) {
/* 209 */     double d1 = 3.14159D;
/* 210 */     double d2 = latLng1.latitude * d1 / 180.0D;
/* 211 */     double d3 = latLng1.longitude * d1 / 180.0D;
/* 212 */     double d4 = latLng2.latitude * d1 / 180.0D;
/* 213 */     double d5 = latLng2.longitude * d1 / 180.0D;
/*     */     
/* 215 */     double d6 = d5 - d3;
/*     */     
/* 217 */     double d7 = Math.sin(d6) * Math.cos(d4);
/*     */     
/* 219 */     double d8 = Math.cos(d2) * Math.sin(d4) - Math.sin(d2) * Math.cos(d4) * Math.cos(d6);
/*     */     
/* 221 */     double d9 = Math.atan2(d7, d8);
/*     */     
/* 223 */     d9 = Math.toDegrees(d9);
/* 224 */     d9 = (d9 + 360.0D) % 360.0D;
/*     */     
/* 226 */     return d9;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/utils/AnimateMarker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */