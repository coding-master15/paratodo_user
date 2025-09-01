/*    */ package com.view;
/*    */ 
/*    */ import android.annotation.TargetApi;
/*    */ import android.content.Context;
/*    */ import android.graphics.Bitmap;
/*    */ import android.graphics.Canvas;
/*    */ import android.graphics.Color;
/*    */ import android.graphics.Paint;
/*    */ import android.graphics.PorterDuff;
/*    */ import android.graphics.PorterDuffXfermode;
/*    */ import android.graphics.RectF;
/*    */ import android.graphics.Xfermode;
/*    */ import android.util.AttributeSet;
/*    */ import android.widget.LinearLayout;
/*    */ import com.utils.Utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RadiusOverlayView
/*    */   extends LinearLayout
/*    */ {
/*    */   private Bitmap a;
/*    */   private Context b;
/*    */   
/*    */   public RadiusOverlayView(Context context) {
/* 28 */     super(context);
/* 29 */     this.b = context;
/*    */   }
/*    */   
/*    */   public RadiusOverlayView(Context context, AttributeSet attrs) {
/* 33 */     super(context, attrs);
/* 34 */     this.b = context;
/*    */   }
/*    */   
/*    */   public RadiusOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
/* 38 */     super(context, attrs, defStyleAttr);
/* 39 */     this.b = context;
/*    */   }
/*    */   
/*    */   @TargetApi(21)
/*    */   public RadiusOverlayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/* 44 */     super(context, attrs, defStyleAttr, defStyleRes);
/* 45 */     this.b = context;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void dispatchDraw(Canvas canvas) {
/* 50 */     super.dispatchDraw(canvas);
/*    */     
/* 52 */     if (this.a == null) {
/* 53 */       createWindowFrame();
/*    */     }
/*    */     
/* 56 */     canvas.drawBitmap(this.a, 0.0F, 0.0F, null);
/*    */   }
/*    */   
/*    */   protected void createWindowFrame() {
/* 60 */     this.a = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
/* 61 */     Canvas canvas = new Canvas(this.a);
/*    */     
/* 63 */     RectF rectF = new RectF(0.0F, 0.0F, getWidth(), getHeight());
/*    */     
/* 65 */     Paint paint = new Paint(1);
/* 66 */     paint.setColor(Color.parseColor("#333333"));
/*    */     
/* 68 */     canvas.drawRect(rectF, paint);
/*    */     
/* 70 */     paint.setColor(0);
/* 71 */     paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
/* 72 */     float f1 = (getWidth() / 2);
/* 73 */     float f2 = (getHeight() / 2);
/* 74 */     float f3 = (Math.min(getWidth(), getHeight()) / 2 - Utils.dipToPixels(this.b, 15.0F));
/* 75 */     canvas.drawCircle(f1, f2, f3, paint);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInEditMode() {
/* 80 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onLayout(boolean changed, int l, int t, int r, int b) {
/* 85 */     super.onLayout(changed, l, t, r, b);
/*    */     
/* 87 */     this.a = null;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/RadiusOverlayView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */