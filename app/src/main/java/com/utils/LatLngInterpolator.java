/*    */ package com.utils;
/*    */ 
/*    */ import com.google.android.gms.maps.model.LatLng;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface LatLngInterpolator
/*    */ {
/*    */   LatLng interpolate(float paramFloat, LatLng paramLatLng1, LatLng paramLatLng2);
/*    */   
/*    */   public static class Linear
/*    */     implements LatLngInterpolator
/*    */   {
/*    */     public LatLng interpolate(float fraction, LatLng a, LatLng b) {
/* 24 */       double d1 = (b.latitude - a.latitude) * fraction + a.latitude;
/* 25 */       double d2 = (b.longitude - a.longitude) * fraction + a.longitude;
/* 26 */       return new LatLng(d1, d2);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class LinearFixed
/*    */     implements LatLngInterpolator {
/*    */     public LatLng interpolate(float fraction, LatLng a, LatLng b) {
/* 33 */       double d1 = (b.latitude - a.latitude) * fraction + a.latitude;
/* 34 */       double d2 = b.longitude - a.longitude;
/*    */ 
/*    */       
/* 37 */       if (Math.abs(d2) > 180.0D) {
/* 38 */         d2 -= Math.signum(d2) * 360.0D;
/*    */       }
/* 40 */       double d3 = d2 * fraction + a.longitude;
/* 41 */       return new LatLng(d1, d3);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Spherical
/*    */     implements LatLngInterpolator
/*    */   {
/*    */     public LatLng interpolate(float fraction, LatLng from, LatLng to) {
/* 51 */       double d1 = Math.toRadians(from.latitude);
/* 52 */       double d2 = Math.toRadians(from.longitude);
/* 53 */       double d3 = Math.toRadians(to.latitude);
/* 54 */       double d4 = Math.toRadians(to.longitude);
/* 55 */       double d5 = Math.cos(d1);
/* 56 */       double d6 = Math.cos(d3);
/*    */ 
/*    */       
/* 59 */       double d7 = a(d1, d2, d3, d4);
/* 60 */       double d8 = Math.sin(d7);
/* 61 */       if (d8 < 1.0E-6D) {
/* 62 */         return from;
/*    */       }
/* 64 */       double d9 = Math.sin((1.0F - fraction) * d7) / d8;
/* 65 */       double d10 = Math.sin(fraction * d7) / d8;
/*    */ 
/*    */       
/* 68 */       double d11 = d9 * d5 * Math.cos(d2) + d10 * d6 * Math.cos(d4);
/* 69 */       double d12 = d9 * d5 * Math.sin(d2) + d10 * d6 * Math.sin(d4);
/* 70 */       double d13 = d9 * Math.sin(d1) + d10 * Math.sin(d3);
/*    */ 
/*    */       
/* 73 */       double d14 = Math.atan2(d13, Math.sqrt(d11 * d11 + d12 * d12));
/* 74 */       double d15 = Math.atan2(d12, d11);
/* 75 */       return new LatLng(Math.toDegrees(d14), Math.toDegrees(d15));
/*    */     }
/*    */ 
/*    */     
/*    */     private double a(double param1Double1, double param1Double2, double param1Double3, double param1Double4) {
/* 80 */       double d1 = param1Double1 - param1Double3;
/* 81 */       double d2 = param1Double2 - param1Double4;
/* 82 */       return 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(d1 / 2.0D), 2.0D) + 
/* 83 */             Math.cos(param1Double1) * Math.cos(param1Double3) * Math.pow(Math.sin(d2 / 2.0D), 2.0D)));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/utils/LatLngInterpolator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */