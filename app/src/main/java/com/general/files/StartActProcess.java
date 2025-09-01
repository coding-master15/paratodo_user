/*     */ package com.general.files;
/*     */ 
/*     */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import com.utils.Utils;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StartActProcess
/*     */ {
/*     */   Context a;
/*     */   
/*     */   public StartActProcess(Context mContext) {
/*  23 */     this.a = mContext;
/*  24 */     Utils.hideKeyboard(mContext);
/*     */   }
/*     */   
/*     */   public void startAct(Class<?> cls) {
/*  28 */     Intent intent = new Intent(this.a, cls);
/*  29 */     this.a.startActivity(intent);
/*     */   }
/*     */   
/*     */   public void startAct(Intent intent) {
/*  33 */     this.a.startActivity(intent);
/*     */   }
/*     */   
/*     */   public void startActWithData(Class<?> cls, Bundle bundle) {
/*  37 */     Intent intent = new Intent(this.a, cls);
/*     */     
/*  39 */     intent.putExtras(bundle);
/*  40 */     this.a.startActivity(intent);
/*     */   }
/*     */   
/*     */   @SuppressLint("WrongConstant")
public void startActWithDataNewTask(Class<?> cls, boolean isClearAllActivities) {
/*  44 */     Intent intent = new Intent(this.a, cls);
/*     */     
/*  46 */     if (isClearAllActivities) {
/*  47 */       intent.setFlags(268468224);
/*     */     } else {
/*  49 */       intent.setFlags(268435456);
/*     */     } 
/*     */     
/*  52 */     this.a.startActivity(intent);
/*     */   }
/*     */   
/*     */   public void startActWithDataNewTask(Class<?> cls, Bundle bundle, boolean isClearAllActivities) {
/*  56 */     Intent intent = new Intent(this.a, cls);
/*  57 */     intent.putExtras(bundle);
/*     */     
/*  59 */     if (isClearAllActivities) {
/*  60 */       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
/*     */     } else {
/*  62 */       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
/*     */     } 
/*     */     
/*  65 */     this.a.startActivity(intent);
/*     */   }
/*     */   
/*     */   public void startActWithDataNewTask(Class<?> cls, Bundle bundle) {
/*  69 */     Intent intent = new Intent(this.a, cls);
/*  70 */     intent.putExtras(bundle);
/*  71 */     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
/*  72 */     this.a.startActivity(intent);
/*     */   }
/*     */   
/*     */   public void startActWithDataNewTask(Class<?> cls) {
/*  76 */     Intent intent = new Intent(this.a, cls);
/*  77 */     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
/*  78 */     this.a.startActivity(intent);
/*     */   }
/*     */   
/*     */   public void startActWithDataClearTop(Class<?> cls, Bundle bundle) {
/*  82 */     Intent intent = new Intent(this.a, cls);
/*  83 */     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
/*  84 */     intent.putExtras(bundle);
/*  85 */     this.a.startActivity(intent);
/*     */   }
/*     */   
/*     */   public void startActClearTop(Class<?> cls, Bundle bundle) {
/*  89 */     Intent intent = new Intent(this.a, cls);
/*  90 */     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
/*  91 */     intent.putExtras(bundle);
/*  92 */     this.a.startActivity(intent);
/*     */   }
/*     */   
/*     */   public void startActClearTop(Class<?> cls) {
/*  96 */     Intent intent = new Intent(this.a, cls);
/*  97 */     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
/*  98 */     this.a.startActivity(intent);
/*     */   }
/*     */   
/*     */   public void startActForResult(Class<?> cls, Bundle bundle, int requestCode) {
/* 102 */     Intent intent = new Intent(this.a, cls);
/* 103 */     intent.putExtras(bundle);
/* 104 */     ((Activity)this.a).startActivityForResult(intent, requestCode);
/*     */   }
/*     */   
/*     */   public void startActForResult(Class<?> cls, int requestCode) {
/* 108 */     Intent intent = new Intent(this.a, cls);
/* 109 */     ((Activity)this.a).startActivityForResult(intent, requestCode);
/*     */   }
/*     */   
/*     */   public void requestOverlayPermission(int requestCode) {
/*     */     try {
/* 114 */       Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
/* 115 */       intent.setData(Uri.parse("package:" + (this.a.getApplicationInfo()).packageName));
/* 116 */       ((Activity)this.a).startActivityForResult(intent, requestCode);
/* 117 */     } catch (Exception exception) {
/* 118 */       Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
/* 119 */       Uri uri = Uri.fromParts("package", (this.a.getApplicationInfo()).packageName, null);
/* 120 */       intent.setData(uri);
/* 121 */       ((Activity)this.a).startActivityForResult(intent, requestCode);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startActForResult(String cls, int requestCode) {
/* 126 */     Intent intent = new Intent(cls);
/* 127 */     ((Activity)this.a).startActivityForResult(intent, requestCode);
/*     */   }
/*     */   
/*     */   public void startActForResult(Fragment fragment, Class<?> cls, int requestCode) {
/* 131 */     Intent intent = new Intent(this.a, cls);
/* 132 */     fragment.startActivityForResult(intent, requestCode);
/*     */   }
/*     */   
/*     */   public void startActForResult(Fragment fragment, Class<?> cls, int requestCode, Bundle bundle) {
/* 136 */     Intent intent = new Intent(this.a, cls);
/* 137 */     intent.putExtras(bundle);
/* 138 */     fragment.startActivityForResult(intent, requestCode);
/*     */   }
/*     */   
/*     */   public void setOkResult() {
/* 142 */     Intent intent = new Intent();
/* 143 */     ((Activity)this.a).setResult(-1, intent);
/*     */   }
/*     */   
/*     */   public void setOkResult(Bundle bn) {
/* 147 */     Intent intent = new Intent();
/* 148 */     intent.putExtras(bn);
/* 149 */     ((Activity)this.a).setResult(-1, intent);
/*     */   }
/*     */   
/*     */   public void setOkResult(int resultCode) {
/* 153 */     Intent intent = new Intent();
/* 154 */     ((Activity)this.a).setResult(resultCode, intent);
/*     */   }
/*     */   
/*     */   public void startService(Class<?> cls) {
/* 158 */     Intent intent = new Intent(this.a, cls);
/* 159 */     this.a.startService(intent);
/*     */   }
/*     */   
/*     */   public Intent startForegroundService(Class<?> cls) {
/* 163 */     Intent intent = new Intent(this.a, cls);
/* 164 */     if (Build.VERSION.SDK_INT >= 26) {
/* 165 */       this.a.startForegroundService(intent);
/*     */     } else {
/* 167 */       this.a.startService(intent);
/*     */     } 
/*     */     
/* 170 */     return intent;
/*     */   }
/*     */   
/*     */   public boolean openURL(String url) {
/*     */     try {
/* 175 */       CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
/* 176 */       CustomTabsIntent customTabsIntent = builder.build();
/* 177 */       customTabsIntent.intent.setPackage("com.android.chrome");
/* 178 */       customTabsIntent.launchUrl(this.a, Uri.parse(url));
/* 179 */     } catch (Exception exception) {
/*     */       try {
/* 181 */         Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
/* 182 */         this.a.startActivity(intent);
/* 183 */       } catch (Exception exception1) {}
/*     */     } 
/*     */ 
/*     */     
/* 187 */     return true;
/*     */   }
/*     */   
/*     */   public boolean openURL(String url, String packageName, String className) {
/* 191 */     if ((packageName == null && className == null) || (packageName.equalsIgnoreCase("") && className.equalsIgnoreCase(""))) {
/* 192 */       openURL(url);
/* 193 */       return false;
/*     */     } 
/*     */     
/*     */     try {
/* 197 */       Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
/* 198 */       if (className != null && packageName != null && !packageName.equalsIgnoreCase("") && !className.equalsIgnoreCase("")) {
/* 199 */         intent.setClassName(packageName, className);
/*     */       }
/* 201 */       this.a.startActivity(intent);
/* 202 */     } catch (Exception exception) {
/* 203 */       openURL(url);
/* 204 */       return false;
/*     */     } 
/* 206 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/general/files/StartActProcess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */