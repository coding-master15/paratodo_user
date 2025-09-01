/*    */ package com.general.files;
/*    */ 
/*    */

import android.content.Context;

import com.InfoProvider;
import com.utils.Utils;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetUserData
/*    */ {
/*    */   String a;
/*    */   GeneralFunctions b;
/*    */   boolean c = true;
/*    */   Context d;
/*    */   
/*    */   public SetUserData(String userProfileJson, GeneralFunctions generalFunc, Context mContext, boolean isStoreUserId) {
/* 19 */     this.a = userProfileJson;
/* 20 */     this.b = generalFunc;
/* 21 */     this.d = mContext;
/* 22 */     this.c = isStoreUserId;
/* 23 */     setData();
/* 24 */     a(this.d);
/*    */   }
/*    */   
/*    */   public SetUserData(String userProfileJson, GeneralFunctions generalFunc, boolean isStoreUserId) {
/* 28 */     this.a = userProfileJson;
/* 29 */     this.b = generalFunc;
/* 30 */     this.c = isStoreUserId;
/* 31 */     a(generalFunc.a);
/* 32 */     setData();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setData() {
/* 37 */     String str = this.b.getJsonValue("changeLangCode", this.a);
/* 38 */     if (str.equals("Yes")) {
/*    */       
/* 40 */       this.b.storeData(Utils.languageLabelsKey, this.b.getJsonValue("UpdatedLanguageLabels", this.a));
/* 41 */       this.b.storeData(Utils.LANGUAGE_CODE_KEY, this.b.getJsonValue("vLanguageCode", this.a));
/* 42 */       this.b.storeData(Utils.LANGUAGE_IS_RTL_KEY, this.b.getJsonValue("langType", this.a));
/*    */       
/* 44 */       this.b.storeData(Utils.GOOGLE_MAP_LANGUAGE_CODE_KEY, this.b.getJsonValue("vGMapLangCode", this.a));
/*    */       
/* 46 */       Utils.setAppLocal(this.d);
/*    */       
/* 48 */       if (!this.b.getJsonValue("LIST_LANGUAGES", this.a).equals("")) {
/* 49 */         this.b.storeData(Utils.LANGUAGE_LIST_KEY, this.b.getJsonValue("LIST_LANGUAGES", this.a));
/*    */       }
/*    */       
/* 52 */       if (!this.b.getJsonValue("LIST_CURRENCY", this.a).equals("")) {
/* 53 */         this.b.storeData(Utils.CURRENCY_LIST_KEY, this.b.getJsonValue("LIST_CURRENCY", this.a));
/*    */       }
/*    */       
/* 56 */       if (Utils.IS_OPTIMIZE_MODE_ENABLE) {
/* 57 */         GeneralFunctions.clearLanguageLabelsData();
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 62 */     if (!this.b.getJsonValue("Visit_Locations", this.b.getJsonValue(Utils.message_str, this.a)).equals("")) {
/* 63 */       this.b.storeData(Utils.VisitLocationsKey, this.b.getJsonValue("Visit_Locations", this.b.getJsonValue(Utils.message_str, this.a)));
/*    */     }
/*    */     
/* 66 */     if (Utils.IS_KIOSK_APP) {
/* 67 */       String str1 = this.b.getJsonValue("vCountry", this.b.getJsonValue(Utils.message_str, this.a));
/* 68 */       this.b.storeData(Utils.countrycode, str1);
/*    */     } 
/*    */     
/* 71 */     if (this.c) {
/* 72 */       String str1 = this.b.getJsonValue(Utils.USER_ID_KEY, this.b.getJsonValue(Utils.message_str, this.a));
/* 73 */       this.b.storeUserData(str1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void a(Context paramContext) {
/* 79 */     new InfoProvider(paramContext);
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/general/files/SetUserData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */