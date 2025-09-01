/*    */ package com.general.files;
/*    */ 
/*    */ import android.os.Handler;

/*    */
/*    */ 
/*    */ public class UpdateFrequentTask
/*    */   implements Runnable
/*    */ {
/*    */   int a;
/*    */   OnTaskRunCalled b;
/*    */   boolean c = false;
/*    */   boolean d = false;
/*    */   private Handler e;
/*    */   
/*    */   public UpdateFrequentTask(int mInterval) {
/* 16 */     this.a = mInterval;
/* 17 */     this.e = new Handler();
/*    */   }
/*    */   
/*    */   public void avoidFirstRun() {
/* 21 */     this.c = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 26 */     if (this.d) {
/*    */       return;
/*    */     }
/* 29 */     if (this.b != null && !this.c) {
/* 30 */       this.b.onTaskRun();
/*    */     }
/*    */     
/* 33 */     if (this.c) {
/* 34 */       this.c = false;
/*    */     }
/*    */     
/* 37 */     this.e.postDelayed(this, this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startRepeatingTask() {
/* 42 */     this.d = false;
/* 43 */     run();
/*    */   }
/*    */   
/*    */   public void stopRepeatingTask() {
/* 47 */     this.d = true;
/* 48 */     this.e.removeCallbacks(this);
/*    */   }
/*    */   
/*    */   public void setTaskRunListener(OnTaskRunCalled onTaskRunCalled) {
/* 52 */     this.b = onTaskRunCalled;
/*    */   }
/*    */   
/*    */   public static interface OnTaskRunCalled {
/*    */     void onTaskRun();
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/general/files/UpdateFrequentTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */