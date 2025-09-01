/*     */ package com.rest;
/*     */ 
/*     */

import android.os.Handler;
import android.os.Looper;

import com.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataRequestProgressBody
/*     */   extends RequestBody
/*     */ {
/*     */   private File ab;
/*     */   private UploadCallbacks bb;
/*  26 */   public static int DEFAULT_BUFFER_SIZE = 2048;
/*     */   
/*     */   private MediaType cb;
/*     */   
/*  30 */   private long db = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean eb = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataRequestProgressBody(@Nullable MediaType mContentType, File mFile, UploadCallbacks mListener) {
/*  45 */     this.cb = mContentType;
/*  46 */     this.ab = mFile;
/*  47 */     this.bb = mListener;
/*     */     
/*  49 */     this.db = this.ab.length();
/*     */     
/*  51 */     if (Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("Yes")) {
/*  52 */       this.eb = true;
/*     */     }
/*     */     
/*  55 */     if (this.bb != null) {
/*  56 */       this.bb.uploadStart();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public File getFile() {
/*  62 */     return this.ab;
/*     */   }
/*     */ 
/*     */   
/*     */   public MediaType contentType() {
/*  67 */     return this.cb;
/*     */   }
/*     */ 
/*     */   
/*     */   public long contentLength() {
/*  72 */     return this.db;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(BufferedSink sink) throws IOException {
/*  77 */     if (this.ab != null) {
/*  78 */       if (this.eb) {
/*  79 */         this.eb = false;
/*     */         
/*     */         return;
/*     */       } 
/*  83 */       long l1 = this.db;
/*  84 */       byte[] arrayOfByte = new byte[DEFAULT_BUFFER_SIZE];
/*  85 */       FileInputStream fileInputStream = new FileInputStream(this.ab);
/*  86 */       long l2 = 0L;
/*     */ 
/*     */       
/*     */       try {
/*  90 */         Handler handler = new Handler(Looper.getMainLooper());
/*  91 */         int j = 0; int i;
/*  92 */         while ((i = fileInputStream.read(arrayOfByte)) != -1) {
/*     */           
/*  94 */           int k = (int)(100L * l2 / l1);
/*  95 */           if (k > j + 1) {
/*     */             
/*  97 */             handler.post(new a(l2, l1));
/*  98 */             j = k;
/*     */           } 
/* 100 */           l2 += i;
/* 101 */           sink.write(arrayOfByte, 0, i);
/*     */         } 
/*     */       } finally {
/* 104 */         fileInputStream.close();
/*     */       } 
/*     */     } else {
/* 107 */       sink.write(new byte[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   private class a implements Runnable {
/*     */     private long bc;
/*     */     private long cc;
/*     */     
/*     */     a(long param1Long1, long param1Long2) {
/* 116 */       this.bc = param1Long1;
/* 117 */       this.cc = param1Long2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/*     */       try {
/* 124 */         int i = (int)(100L * this.bc / this.cc);
/*     */         
/* 126 */         if (i == 100) {
/* 127 */           bb.onFinish();
/*     */         } else {
/* 129 */           bb.onProgressUpdate(i);
/*     */         } 
/* 131 */       } catch (ArithmeticException arithmeticException) {
/* 132 */         bb.onError();
/* 133 */         arithmeticException.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface UploadCallbacks {
/*     */     void onProgressUpdate(int param1Int);
/*     */     
/*     */     void onError();
/*     */     
/*     */     void onFinish();
/*     */     
/*     */     void uploadStart();
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/rest/DataRequestProgressBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */