/*    */ package com.general.files;
/*    */ 
/*    */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.InfoProvider;

import java.util.Calendar;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NotificationScheduler
/*    */ {
/*    */   public static final int DAILY_REMINDER_REQUEST_CODE = 100;
/*    */   public static final String TAG = "NotificationScheduler";
/*    */   
/*    */   public static void setReminder(Context context, Class<?> cls, int hour, int min, int second) {
/* 25 */     a(context);
/* 26 */     Calendar calendar1 = Calendar.getInstance();
/*    */     
/* 28 */     Calendar calendar2 = Calendar.getInstance();
/* 29 */     calendar2.set(11, hour);
/* 30 */     calendar2.set(12, min);
/* 31 */     calendar2.set(13, second);
/*    */     
/* 33 */     cancelReminder(context, cls);
/*    */     
/* 35 */     if (calendar2.before(calendar1)) {
/* 36 */       calendar2.add(5, 1);
/*    */     }
/* 38 */     ComponentName componentName = new ComponentName(context, cls);
/* 39 */     PackageManager packageManager = context.getPackageManager();
/*    */     
/* 41 */     packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     Intent intent = new Intent(context, cls);
/* 47 */     PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
/* 48 */     AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
/* 49 */     alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), 86400000L, pendingIntent);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void cancelReminder(Context context, Class<?> cls) {
/* 54 */     ComponentName componentName = new ComponentName(context, cls);
/* 55 */     PackageManager packageManager = context.getPackageManager();
/*    */     
/* 57 */     packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
/*    */ 
/*    */ 
/*    */     
/* 61 */     Intent intent = new Intent(context, cls);
/* 62 */     PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
/* 63 */     AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
/* 64 */     alarmManager.cancel(pendingIntent);
/* 65 */     pendingIntent.cancel();
/*    */   }
/*    */   
/*    */   private static void a(Context paramContext) {
/* 69 */     new InfoProvider(paramContext);
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfile/classes.jar!/com/general/files/NotificationScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */