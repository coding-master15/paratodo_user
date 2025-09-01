/*     */ package com.view;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.Dialog;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ import android.graphics.Color;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.AdapterView;
/*     */ import android.widget.BaseAdapter;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.ListAdapter;
/*     */ import android.widget.ListView;
/*     */ import androidx.appcompat.app.AlertDialog;

import com.InfoProvider;
/*     */ import com.general.files.GeneralFunctions;
/*     */ import com.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenerateAlertBox
/*     */ {
/*     */   public AlertDialog alertDialog;
/*     */   Context a;
/*     */   HandleAlertBtnClick b;
/*     */   AlertDialog.Builder builder;
/*     */   boolean cancelable = false;
/*     */   GeneralFunctions e;
/*  36 */   View f = null;
/*     */   
/*     */   public GenerateAlertBox(Context mContext) {
/*  39 */     this.a = mContext;
/*     */     
/*  41 */     this.builder = new AlertDialog.Builder(mContext);
/*     */     
/*  43 */     this.e = new GeneralFunctions(this.a);
/*  44 */     a(mContext);
/*     */   }
/*     */ 
/*     */   
/*     */   public GenerateAlertBox(Context mContext, boolean isCancelable) {
/*  49 */     this.a = mContext;
/*  50 */     this.cancelable = isCancelable;
/*  51 */     a(this.a);
/*  52 */     this.builder = new AlertDialog.Builder(mContext);
/*     */ 
/*     */     
/*  55 */     this.e = new GeneralFunctions(this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public AlertDialog.Builder getBuilder() {
/*  60 */     return this.builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContentMessage(String title_str, String message_str) {
/*  65 */     this.builder.setTitle(title_str);
/*  66 */     this.builder
/*  67 */       .setMessage(message_str);
/*     */   }
/*     */   
/*     */   public void setCancelable(boolean value) {
/*  71 */     this.cancelable = value;
/*  72 */     this.builder.setCancelable(value);
/*  73 */     if (this.alertDialog != null) {
/*  74 */       this.alertDialog.setCanceledOnTouchOutside(value);
/*  75 */       this.alertDialog.setCancelable(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setNegativeBtn(String negative_btn_str) {
/*  80 */     this.builder.setNegativeButton(negative_btn_str, (paramDialogInterface, paramInt) -> {
/*     */           if (this.b != null) {
/*     */             this.b.handleBtnClick(0);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void setPositiveBtn(String positive_btn_str) {
/*  88 */     this.builder.setPositiveButton(positive_btn_str, (paramDialogInterface, paramInt) -> {
/*     */           if (this.b != null) {
/*     */             this.b.handleBtnClick(1);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNeutralBtn(String neutral_btn_str) {
/*  97 */     this.builder.setNeutralButton(neutral_btn_str, (paramDialogInterface, paramInt) -> {
/*     */           if (this.b != null) {
/*     */             this.b.handleBtnClick(2);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetBtn() {
/* 106 */     this.builder.setNegativeButton(null, null);
/* 107 */     this.builder.setPositiveButton(null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void showAlertBox() {
/* 112 */     if (this.a instanceof Activity) {
                ((Activity)this.a).runOnUiThread(() -> {
                   a();
                });
             } else {
               a();
             }
           }

/*     */   public void setCustomView(int resourceId) {
/* 124 */     LayoutInflater layoutInflater = (LayoutInflater)this.a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
/*     */     
/* 126 */     View view = layoutInflater.inflate(resourceId, null);
/* 127 */     if (this.builder != null) {
/* 128 */       this.f = view;
/* 129 */       this.builder.setView(view);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a(Context paramContext) {
/* 134 */     new InfoProvider(paramContext);
/*     */   }
/*     */ 
/*     */   
/*     */   public View getView(int resourceId) {
/* 139 */     if (this.f != null) {
/* 140 */       return this.f.findViewById(resourceId);
/*     */     }
/* 142 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a() {
/*     */     try {
/* 149 */       if (this.alertDialog == null) {
/* 150 */         this.alertDialog = this.builder.create();
/* 151 */         this.alertDialog.setCancelable(this.cancelable);
/* 152 */         if (this.e.isRTLmode()) {
/* 153 */           this.e.forceRTLIfSupported(this.alertDialog);
/*     */         } else {
/* 155 */           this.e.forceLTRIfSupported((Dialog)this.alertDialog);
/*     */         } 
/*     */       } 
/* 158 */       this.alertDialog.show();
/* 159 */     } catch (Exception exception) {
/* 160 */       System.out.println(exception.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createList(ArrayList<HashMap<String, String>> dataList, String keyToShow, OnItemClickListener onItemClickListener) {
/* 167 */     if (this.builder != null && this.a != null) {
/* 168 */       ListView listView = new ListView(this.a);
/* 169 */       listView.setDivider(null);
/* 170 */       listView.setPadding(0, Utils.dipToPixels(this.a, 10.0F), 0, 0);
/* 171 */       a a = new a(this, dataList, this.a, keyToShow);
/* 172 */       listView.setAdapter((ListAdapter)a);
/*     */       
/* 174 */       listView.setOnItemClickListener((paramAdapterView, paramView, paramInt, paramLong) -> {
/*     */             if (onItemClickListener != null) {
/*     */               onItemClickListener.onItemClick(paramInt);
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 181 */       this.builder.setView((View)listView);
/*     */     } 
/*     */   }
/*     */   
/*     */   private class a
/*     */     extends BaseAdapter {
/*     */     ArrayList<HashMap<String, String>> a;
/*     */     Context b;
/*     */     String c;
/*     */     
/*     */     public a(GenerateAlertBox this$0, ArrayList<HashMap<String, String>> param1ArrayList, Context param1Context, String param1String) {
/* 192 */       this.a = param1ArrayList;
/* 193 */       this.b = param1Context;
/* 194 */       this.c = param1String;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getCount() {
/* 199 */       return this.a.size();
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getItem(int i) {
/* 204 */       return this.a.get(i);
/*     */     }
/*     */ 
/*     */     
/*     */     public long getItemId(int i) {
/* 209 */       return i;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public View getView(int position, View view, ViewGroup viewGroup) {
/*     */       LinearLayout linearLayout1 = null;
/* 216 */       if (view == null) {
/* 217 */         LinearLayout linearLayout = new LinearLayout(this.b);
/*     */         
/* 219 */         MTextView mTextView = new MTextView(this.b);
/*     */         
/* 221 */         mTextView.setTextSize(2, 16.0F);
/* 222 */         mTextView.setTextColor(Color.parseColor("#1c1c1c"));
/*     */ 
/*     */ 
/*     */         
/* 226 */         LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
/* 227 */         mTextView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
/* 228 */         mTextView.setMinHeight(Utils.dipToPixels(this.b, 40.0F));
/* 229 */         mTextView.setPadding(Utils.dipToPixels(this.b, 25.0F), Utils.dipToPixels(this.b, 5.0F), Utils.dipToPixels(this.b, 25.0F), Utils.dipToPixels(this.b, 5.0F));
/* 230 */         mTextView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
/* 231 */         mTextView.setGravity(8388627);
/* 232 */         linearLayout.addView((View)mTextView);
/*     */         
/* 234 */         linearLayout1 = linearLayout;
/*     */       } 
/*     */       
/* 237 */       LinearLayout linearLayout2 = linearLayout1;
/*     */       
/* 239 */       if (linearLayout2.getChildCount() > 0 && linearLayout2.getChildAt(0) instanceof MTextView) {
/* 240 */         String str = (String)((HashMap)this.a.get(position)).get(this.c);
/* 241 */         ((MTextView)linearLayout2.getChildAt(0)).setText((str != null) ? str : "");
/*     */       } 
/*     */       
/* 244 */       return (View)linearLayout1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeAlertBox() {
/*     */     try {
/* 254 */       if (this.alertDialog != null) {
/* 255 */         this.alertDialog.dismiss();
/*     */       }
/* 257 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBtnClickList(HandleAlertBtnClick listener) {
/* 263 */     this.b = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showSessionOutAlertBox() {
/*     */     try {
/* 273 */       if (this.alertDialog != null && this.alertDialog.isShowing()) {
/*     */         return;
/*     */       }
/* 276 */       this.alertDialog = this.builder.create();
/* 277 */       this.alertDialog.setCancelable(false);
/* 278 */       if (this.e.isRTLmode()) {
/* 279 */         this.e.forceRTLIfSupported(this.alertDialog);
/*     */       } else {
/* 281 */         this.e.forceLTRIfSupported((Dialog)this.alertDialog);
/*     */       } 
/* 283 */       this.alertDialog.show();
/* 284 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   public static interface OnItemClickListener {
/*     */     void onItemClick(int param1Int);
/*     */   }
/*     */   
/*     */   public static interface HandleAlertBtnClick {
/*     */     void handleBtnClick(int param1Int);
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/GenerateAlertBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */