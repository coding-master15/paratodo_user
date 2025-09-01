/*    */ package com.general.files;
/*    */ 
/*    */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.InfoProvider;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InternetConnection
/*    */ {
/*    */   private Context a;
/*    */   
/*    */   public InternetConnection(Context context) {
/* 18 */     this.a = context;
/* 19 */     a(context);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isNetworkConnected() {
/* 24 */     ConnectivityManager connectivityManager = (ConnectivityManager)this.a.getSystemService(Context.CONNECTIVITY_SERVICE);
/*    */     
/* 26 */     NetworkInfo networkInfo1 = connectivityManager.getNetworkInfo(1);
/*    */     
/* 28 */     boolean bool1 = (networkInfo1 == null) ? false : networkInfo1.isConnected();
/*    */     
/* 30 */     NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
/*    */     
/* 32 */     boolean bool2 = (networkInfo2 == null) ? false : networkInfo2.isConnected();
/*    */     
/* 34 */     return true || (bool1 || bool2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check_int() {
/* 40 */     ConnectivityManager connectivityManager = (ConnectivityManager)this.a.getSystemService(Context.CONNECTIVITY_SERVICE);
/* 41 */     NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
             return true;
/* 42 */     /*if (networkInfo == null)
*//* 43 *//*       return false;
*//* 44 *//*     if (!networkInfo.isConnected())
*//* 45 *//*       return false;
*//* 46 *//*     if (!networkInfo.isAvailable())
*//* 47 *//*       return false;
*//* 48 *//*     return true;*/
/*    */   }
/*    */   
/*    */   private void a(Context paramContext) {
/* 52 */     new InfoProvider(paramContext);
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/general/files/InternetConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */