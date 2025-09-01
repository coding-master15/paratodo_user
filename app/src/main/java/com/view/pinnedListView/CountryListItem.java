/*    */ package com.view.pinnedListView;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CountryListItem
/*    */ {
/*    */   public static final int ITEM = 0;
/*    */   public static final int SECTION = 1;
/*    */   public final int type;
/*    */   public final String text;
/*    */   public int sectionPosition;
/*    */   public int listPosition;
/*    */   public int CountSubItems;
/* 14 */   public String vCountryCode = "";
/* 15 */   public String vPhoneCode = "";
/* 16 */   public String iCountryId = "";
/*    */   
/* 18 */   public String iStateId = "";
/* 19 */   public String vStateCode = "";
/*    */   
/*    */   public Object obj_other_data;
/*    */   
/*    */   public String getvRImage() {
/* 24 */     return this.vRImage;
/*    */   }
/*    */   
/*    */   public void setvRImage(String vRImage) {
/* 28 */     this.vRImage = vRImage;
/*    */   }
/*    */   
/*    */   public String getvSImage() {
/* 32 */     return this.vSImage;
/*    */   }
/*    */   
/*    */   public void setvSImage(String vSImage) {
/* 36 */     this.vSImage = vSImage;
/*    */   }
/*    */   
/* 39 */   public String vRImage = "";
/* 40 */   public String vSImage = "";
/*    */   
/*    */   public CountryListItem(int type, String text) {
/* 43 */     this.type = type;
/* 44 */     this.text = text;
/*    */   }
/*    */   
/*    */   public String getvCountryCode() {
/* 48 */     return this.vCountryCode;
/*    */   }
/*    */   
/*    */   public void setvCountryCode(String vCountryCode) {
/* 52 */     this.vCountryCode = vCountryCode;
/*    */   }
/*    */   
/*    */   public String getvPhoneCode() {
/* 56 */     return this.vPhoneCode;
/*    */   }
/*    */   
/*    */   public void setvPhoneCode(String vPhoneCode) {
/* 60 */     this.vPhoneCode = vPhoneCode;
/*    */   }
/*    */   
/*    */   public String getiCountryId() {
/* 64 */     return this.iCountryId;
/*    */   }
/*    */   
/*    */   public void setiCountryId(String iCountryId) {
/* 68 */     this.iCountryId = iCountryId;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 73 */     return this.text;
/*    */   }
/*    */   
/*    */   public String getvStateCode() {
/* 77 */     return this.vStateCode;
/*    */   }
/*    */   
/*    */   public void setvStateCode(String vStateCode) {
/* 81 */     this.vStateCode = vStateCode;
/*    */   }
/*    */   
/*    */   public String getiStateId() {
/* 85 */     return this.iStateId;
/*    */   }
/*    */   
/*    */   public void setiStateId(String iStateId) {
/* 89 */     this.iStateId = iStateId;
/*    */   }
/*    */   
/*    */   public Object getOtherData() {
/* 93 */     return this.obj_other_data;
/*    */   }
/*    */   
/*    */   public void setOtherData(Object obj_other_data) {
/* 97 */     this.obj_other_data = obj_other_data;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/pinnedListView/CountryListItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */