/*    */ package com.view.line;
/*    */ 
/*    */ import android.annotation.TargetApi;
/*    */ import android.content.Context;
/*    */ import android.content.res.Resources;
/*    */ import android.graphics.Canvas;
/*    */ import android.graphics.Paint;
/*    */ import android.graphics.Path;
/*    */ import android.graphics.PathDashPathEffect;
/*    */ import android.graphics.PathEffect;
/*    */ import android.os.Build;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.View;

import com.paratodo.user.R;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DottedLine
/*    */   extends View
/*    */ {
/*    */   private Paint a;
/*    */   
/*    */   public DottedLine(Context context) {
/* 25 */     super(context);
/* 26 */     a();
/*    */   }
/*    */   
/*    */   public DottedLine(Context context, AttributeSet attrs) {
/* 30 */     super(context, attrs);
/* 31 */     a();
/*    */   }
/*    */   
/*    */   public DottedLine(Context context, AttributeSet attrs, int defStyleAttr) {
/* 35 */     super(context, attrs, defStyleAttr);
/* 36 */     a();
/*    */   }
/*    */   
/*    */   @TargetApi(21)
/*    */   public DottedLine(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
/* 41 */     super(context, attrs, defStyleAttr, defStyleRes);
/* 42 */     a();
/*    */   }
/*    */   
/*    */   private void a() {
/* 46 */     Resources resources = getResources();
/* 47 */     this.a = new Paint();
/*    */     
/* 49 */     this.a.setColor(resources.getColor(R.color.gray));
/* 50 */     int i = resources.getDimensionPixelSize(R.dimen.divider_size_vert_prj);
/* 51 */     int j = resources.getDimensionPixelSize(R.dimen.divider_gap_vert_prj);
/* 52 */     this.a.setStyle(Paint.Style.FILL);
/*    */ 
/*    */     
/* 55 */     Path path = new Path();
/* 56 */     path.addCircle(0.0F, 0.0F, i, Path.Direction.CW);
/*    */     
/* 58 */     this.a.setPathEffect((PathEffect)new PathDashPathEffect(path, j, 0.0F, PathDashPathEffect.Style.ROTATE));
/*    */ 
/*    */     
/* 61 */     if (Build.VERSION.SDK_INT >= 11) {
/* 62 */       setLayerType(1, null);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onDraw(Canvas canvas) {
/* 68 */     super.onDraw(canvas);
/* 69 */     canvas.drawLine((getWidth() / 2), 0.0F, (getWidth() / 2), getHeight(), this.a);
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/line/DottedLine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */