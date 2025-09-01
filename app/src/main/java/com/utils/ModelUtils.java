/*    */ package com.utils;
/*    */ 
/*    */

import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Locale;
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
/*    */ public class ModelUtils
/*    */ {
/*    */   static boolean a(@Nullable String paramString) {
/* 21 */     return (paramString != null && TextUtils.isDigitsOnly(paramString));
/*    */   }
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
/*    */   static boolean a(int paramInt1, int paramInt2, Calendar paramCalendar) {
/* 35 */     if (hasYearPassed(paramInt1, paramCalendar)) {
/* 36 */       return true;
/*    */     }
/*    */ 
/*    */     
/* 40 */     return (normalizeYear(paramInt1, paramCalendar) == paramCalendar.get(1) && paramInt2 < paramCalendar
/* 41 */       .get(2) + 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean hasYearPassed(int year, Calendar now) {
/* 53 */     int i = normalizeYear(year, now);
/* 54 */     return (i < now.get(1));
/*    */   }
/*    */   
/*    */   public static int normalizeYear(int year, Calendar now) {
/* 58 */     if (year < 100 && year >= 0) {
/* 59 */       String str1 = String.valueOf(now.get(1));
/* 60 */       String str2 = str1.substring(0, str1.length() - 2);
/* 61 */       year = Integer.parseInt(String.format(Locale.US, "%s%02d", new Object[] { str2, Integer.valueOf(year) }));
/*    */     } 
/* 63 */     return year;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/utils/ModelUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */