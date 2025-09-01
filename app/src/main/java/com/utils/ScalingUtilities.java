/*     */ package com.utils;
/*     */ 
/*     */

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
/*     */ public class ScalingUtilities
/*     */ {
/*     */   public static Bitmap decodeResource(Resources res, int resId, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
/*  33 */     BitmapFactory.Options options = new BitmapFactory.Options();
/*  34 */     options.inJustDecodeBounds = true;
/*  35 */     BitmapFactory.decodeResource(res, resId, options);
/*  36 */     options.inJustDecodeBounds = false;
/*  37 */     options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, dstWidth, dstHeight, scalingLogic);
/*     */     
/*  39 */     return BitmapFactory.decodeResource(res, resId, options);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Bitmap decodeFile(String path, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
/*  46 */     BitmapFactory.Options options = new BitmapFactory.Options();
/*  47 */     options.inJustDecodeBounds = true;
/*  48 */     BitmapFactory.decodeFile(path, options);
/*  49 */     options.inJustDecodeBounds = false;
/*  50 */     options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, dstWidth, dstHeight, scalingLogic);
/*     */     
/*  52 */     return BitmapFactory.decodeFile(path, options);
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
/*     */   public static Bitmap createScaledBitmap(Bitmap unscaledBitmap, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
/*  68 */     Rect rect1 = calculateSrcRect(unscaledBitmap.getWidth(), unscaledBitmap.getHeight(), dstWidth, dstHeight, scalingLogic);
/*     */     
/*  70 */     Rect rect2 = calculateDstRect(unscaledBitmap.getWidth(), unscaledBitmap.getHeight(), dstWidth, dstHeight, scalingLogic);
/*     */     
/*  72 */     Bitmap bitmap = Bitmap.createBitmap(rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888);
/*     */     
/*  74 */     Canvas canvas = new Canvas(bitmap);
/*  75 */     canvas.drawBitmap(unscaledBitmap, rect1, rect2, new Paint(2));
/*     */     
/*  77 */     return bitmap;
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
/*     */   public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
/*  93 */     if (scalingLogic == ScalingLogic.FIT) {
/*  94 */       float f3 = srcWidth / srcHeight;
/*  95 */       float f4 = dstWidth / dstHeight;
/*     */       
/*  97 */       if (f3 > f4) {
/*  98 */         return srcWidth / dstWidth;
/*     */       }
/* 100 */       return srcHeight / dstHeight;
/*     */     } 
/*     */     
/* 103 */     float f1 = srcWidth / srcHeight;
/* 104 */     float f2 = dstWidth / dstHeight;
/*     */     
/* 106 */     if (f1 > f2) {
/* 107 */       return srcHeight / dstHeight;
/*     */     }
/* 109 */     return srcWidth / dstWidth;
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
/*     */   public static Rect calculateSrcRect(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
/* 126 */     if (scalingLogic == ScalingLogic.CROP) {
/* 127 */       float f1 = srcWidth / srcHeight;
/* 128 */       float f2 = dstWidth / dstHeight;
/*     */       
/* 130 */       if (f1 > f2) {
/* 131 */         int k = (int)(srcHeight * f2);
/* 132 */         int m = (srcWidth - k) / 2;
/* 133 */         return new Rect(m, 0, m + k, srcHeight);
/*     */       } 
/* 135 */       int i = (int)(srcWidth / f2);
/* 136 */       int j = (srcHeight - i) / 2;
/* 137 */       return new Rect(0, j, srcWidth, j + i);
/*     */     } 
/*     */     
/* 140 */     return new Rect(0, 0, srcWidth, srcHeight);
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
/*     */   public static Rect calculateDstRect(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
/* 156 */     if (scalingLogic == ScalingLogic.FIT) {
/* 157 */       float f1 = srcWidth / srcHeight;
/* 158 */       float f2 = dstWidth / dstHeight;
/*     */       
/* 160 */       if (f1 > f2) {
/* 161 */         return new Rect(0, 0, dstWidth, (int)(dstWidth / f1));
/*     */       }
/* 163 */       return new Rect(0, 0, (int)(dstHeight * f1), dstHeight);
/*     */     } 
/*     */     
/* 166 */     return new Rect(0, 0, dstWidth, dstHeight);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Bitmap decodeImageFromPath(String path, int reqWidth, int reqHeight) {
/* 173 */     BitmapFactory.Options options = new BitmapFactory.Options();
/* 174 */     options.inJustDecodeBounds = true;
/* 175 */     BitmapFactory.decodeFile(path, options);
/*     */ 
/*     */ 
/*     */     
/* 179 */     int i = options.outHeight;
/* 180 */     int j = options.outWidth;
/* 181 */     options.inPreferredConfig = Bitmap.Config.RGB_565;
/* 182 */     int k = 1;
/*     */     
/* 184 */     if (i > reqHeight) {
/* 185 */       k = Math.round(i / reqHeight);
/*     */     }
/*     */     
/* 188 */     int m = j / k;
/*     */     
/* 190 */     if (m > reqWidth)
/*     */     {
/* 192 */       k = Math.round(j / reqWidth);
/*     */     }
/*     */ 
/*     */     
/* 196 */     options.inSampleSize = k;
/*     */ 
/*     */     
/* 199 */     options.inJustDecodeBounds = false;
/*     */     
/* 201 */     return BitmapFactory.decodeFile(path, options);
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
/*     */   public enum ScalingLogic
/*     */   {
/* 218 */     CROP, FIT;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/utils/ScalingUtilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */