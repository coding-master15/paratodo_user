/*    */ package com.view;
/*    */ 
/*    */ import android.app.Dialog;
/*    */ import android.content.Context;
/*    */ import android.view.Window;
/*    */ import com.InfoProvider;
/*    */ import com.general.files.GeneralFunctions;
import com.paratodo.user.R;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MyProgressDialog
/*    */ {
/*    */   Context a;
/*    */   boolean b;
/*    */   Dialog c;
/*    */   
/*    */   public MyProgressDialog(Context mContext, boolean cancelable, String message_str) {
/* 23 */     this.a = mContext;
/* 24 */     this.b = cancelable;
/* 25 */     a(this.a);
/* 26 */     build();
/* 27 */     setMessage(message_str);
/*    */   }
/*    */   
/*    */   public void build() {
/* 31 */     this.c = new Dialog(this.a, R.style.theme_my_progress_dialog);
/*    */     
/* 33 */     this.c.setContentView(R.layout.my_progress_dilalog_design);
/*    */     
/* 35 */     Window window = this.c.getWindow();
/* 36 */     window.setGravity(17);
/*    */     
/* 38 */     window.setLayout(-2, -2);
/*    */ 
/*    */     
/* 41 */     this.c.getWindow().setLayout(-2, -2);
/*    */ 
/*    */     
/* 44 */     this.c.setCanceledOnTouchOutside(false);
/* 45 */     this.c.setCancelable(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMessage(String msg_str) {
/* 50 */     MTextView mTextView = (MTextView)this.c.findViewById(R.id.msgTxt);
/* 51 */     mTextView.setText(msg_str);
/*    */   }
/*    */   
/*    */   private void a(Context paramContext) {
/* 55 */     new InfoProvider(paramContext);
/*    */   }
/*    */   
/*    */   public void show() {
/*    */     try {
/* 60 */       GeneralFunctions generalFunctions = new GeneralFunctions(this.a);
/* 61 */       if (generalFunctions.isRTLmode()) {
/* 62 */         generalFunctions.forceRTLIfSupported(this.c);
/*    */       } else {
/* 64 */         generalFunctions.forceLTRIfSupported(this.c);
/*    */       } 
/*    */       
/* 67 */       this.c.show();
/* 68 */     } catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() {
/*    */     try {
/* 76 */       this.c.dismiss();
/* 77 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/MyProgressDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */