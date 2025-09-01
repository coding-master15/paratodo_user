/*    */ package com.view.pinnedListView;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OrganizationListItem
/*    */ {
/*    */   public static final int ITEM = 0;
/*    */   public static final int SECTION = 1;
/*    */   public final int type;
/*    */   public final String text;
/*    */   public int sectionPosition;
/*    */   String a;
/*    */   String b;
/*    */   
/*    */   public String getiOrganizationId() {
/* 16 */     return this.a;
/*    */   }
/*    */   
/*    */   public void setiOrganizationId(String iOrganizationId) {
/* 20 */     this.a = iOrganizationId;
/*    */   }
/*    */   
/*    */   public String getiUserProfileMasterId() {
/* 24 */     return this.b;
/*    */   }
/*    */   
/*    */   public void setiUserProfileMasterId(String iUserProfileMasterId) {
/* 28 */     this.b = iUserProfileMasterId;
/*    */   }
/*    */   
/*    */   public OrganizationListItem(int type, String text) {
/* 32 */     this.type = type;
/* 33 */     this.text = text;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     return this.text;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/pinnedListView/OrganizationListItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */