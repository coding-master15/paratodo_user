/*    */ package com.general.files;
/*    */ 
/*    */

import android.text.InputFilter;
import android.text.Spanned;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DecimalDigitsInputFilter
/*    */   implements InputFilter
/*    */ {
/*    */   private final int a;
/*    */   
/*    */   public DecimalDigitsInputFilter(int decimalDigits) {
/* 20 */     this.a = decimalDigits;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
/* 26 */     byte b = -1;
/* 27 */     int i = dest.length();
/* 28 */     for (byte b1 = 0; b1 < i; b1++) {
/* 29 */       char c = dest.charAt(b1);
/* 30 */       if (c == '.' || c == ',') {
/* 31 */         b = b1;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 36 */     if (b >= 0) {
/*    */       
/* 38 */       if (source.equals(".") || source.equals(",")) {
/* 39 */         return "";
/*    */       }
/*    */       
/* 42 */       if (dend <= b) {
/* 43 */         return null;
/*    */       }
/* 45 */       if (i - b > this.a) {
/* 46 */         return "";
/*    */       }
/*    */     } 
/*    */     
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfile/classes.jar!/com/general/files/DecimalDigitsInputFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */