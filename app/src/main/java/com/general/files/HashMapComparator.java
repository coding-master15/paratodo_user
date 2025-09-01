/*    */ package com.general.files;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HashMapComparator
/*    */   implements Comparator<HashMap<String, String>>
/*    */ {
/*    */   String a;
/*    */   
/*    */   public HashMapComparator(String key) {
/* 14 */     this.a = key;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int compare(HashMap<String, String> map1, HashMap<String, String> map2) {
/* 20 */     if (Double.parseDouble(map1.get(this.a)) > Double.parseDouble(map2.get(this.a)))
/* 21 */       return 1; 
/* 22 */     if (Double.parseDouble(map1.get(this.a)) < Double.parseDouble(map2.get(this.a))) {
/* 23 */       return -1;
/*    */     }
/* 25 */     return 0;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfile/classes.jar!/com/general/files/HashMapComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */