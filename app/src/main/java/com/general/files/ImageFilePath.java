/*     */ package com.general.files;
/*     */ 
/*     */

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.InfoProvider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageFilePath
/*     */ {
/*     */   public static final String DOCUMENTS_DIR = "documents";
/*     */   
/*     */   @TargetApi(19)
/*     */   public static String getPath(Context context, Uri uri) {
/*  45 */     boolean bool = (Build.VERSION.SDK_INT >= 19) ? true : false;
/*     */     
/*  47 */     a(context);
/*     */ 
/*     */     
/*  50 */     if (bool && DocumentsContract.isDocumentUri(context, uri)) {
/*     */ 
/*     */       
/*  53 */       if (isExternalStorageDocument(uri)) {
/*  54 */         String str1 = DocumentsContract.getDocumentId(uri);
/*  55 */         String[] arrayOfString = str1.split(":");
/*  56 */         String str2 = arrayOfString[0];
/*     */         
/*  58 */         if ("primary".equalsIgnoreCase(str2)) {
/*  59 */           return Environment.getExternalStorageDirectory() + "/" + arrayOfString[1];
/*     */         
/*     */         }
/*     */       }
/*  63 */       else if (isDownloadsDocument(uri)) {
/*     */         
/*  65 */         String str = DocumentsContract.getDocumentId(uri);
/*  66 */         if (!TextUtils.isEmpty(str)) {
/*  67 */           if (str.startsWith("raw:")) {
/*  68 */             return str.replaceFirst("raw:", "");
/*     */           }
/*     */           try {
/*  71 */             String[] arrayOfString = { "content://downloads/public_downloads", "content://downloads/my_downloads", "content://downloads/all_downloads" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  77 */             for (String str1 : arrayOfString) {
/*  78 */               Uri uri1 = ContentUris.withAppendedId(Uri.parse(str1), Long.valueOf(str).longValue());
/*     */               try {
/*  80 */                 String str2 = getDataColumn(context, uri1, null, null);
/*  81 */                 if (str2 != null) {
/*  82 */                   return str2;
/*     */                 }
/*  84 */               } catch (Exception exception) {}
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/*  90 */               String str1 = getFileName(context, uri);
/*  91 */               File file1 = getDocumentCacheDir(context);
/*  92 */               File file2 = generateFileName(str1, file1);
/*  93 */               String str2 = null;
/*  94 */               if (file2 != null) {
/*  95 */                 str2 = file2.getAbsolutePath();
/*  96 */                 a(context, uri, str2);
/*     */               } 
/*     */               
/*  99 */               return str2;
/* 100 */             } catch (Exception exception) {
/* 101 */               return null;
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 106 */           catch (NumberFormatException numberFormatException) {
/* 107 */             return null;
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 116 */       else if (isMediaDocument(uri)) {
/* 117 */         String str1 = DocumentsContract.getDocumentId(uri);
/* 118 */         String[] arrayOfString1 = str1.split(":");
/* 119 */         String str2 = arrayOfString1[0];
/*     */         
/* 121 */         Uri uri1 = null;
/* 122 */         if ("image".equals(str2)) {
/* 123 */           uri1 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
/* 124 */         } else if ("video".equals(str2)) {
/* 125 */           uri1 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
/* 126 */         } else if ("audio".equals(str2)) {
/* 127 */           uri1 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
/*     */         } 
/*     */         
/* 130 */         String str3 = "_id=?";
/* 131 */         String[] arrayOfString2 = { arrayOfString1[1] };
/*     */ 
/*     */ 
/*     */         
/* 135 */         return getDataColumn(context, uri1, "_id=?", arrayOfString2);
/*     */       } 
/*     */     } else {
/*     */       
/* 139 */       if ("content".equalsIgnoreCase(uri.getScheme())) {
/*     */ 
/*     */         
/* 142 */         if (isGooglePhotosUri(uri)) {
/* 143 */           return uri.getLastPathSegment();
/*     */         }
/* 145 */         return getDataColumn(context, uri, null, null);
/*     */       } 
/*     */       
/* 148 */       if ("file".equalsIgnoreCase(uri.getScheme())) {
/* 149 */         return uri.getPath();
/*     */       }
/*     */     } 
/* 152 */     return null;
/*     */   }
/*     */   
/*     */   private static void a(Context paramContext) {
/* 156 */     new InfoProvider(paramContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
/* 172 */     Cursor cursor = null;
/* 173 */     String str = "_data";
/* 174 */     String[] arrayOfString = { "_data" };
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 179 */       cursor = context.getContentResolver().query(uri, arrayOfString, selection, selectionArgs, null);
/*     */       
/* 181 */       cursor.moveToFirst();
/* 182 */       int i = cursor.getColumnIndex(arrayOfString[0]);
/* 183 */       if (cursor != null && cursor.moveToFirst())
/*     */       {
/* 185 */         return cursor.getString(i);
/*     */       }
/* 187 */     } catch (Exception exception) {
/* 188 */       if (cursor != null)
/* 189 */         cursor.close(); 
/*     */     } finally {
/* 191 */       if (cursor != null)
/* 192 */         cursor.close(); 
/*     */     } 
/* 194 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isExternalStorageDocument(Uri uri) {
/* 202 */     return "com.android.externalstorage.documents".equals(uri.getAuthority());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isDownloadsDocument(Uri uri) {
/* 210 */     return "com.android.providers.downloads.documents".equals(uri.getAuthority());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isMediaDocument(Uri uri) {
/* 218 */     return "com.android.providers.media.documents".equals(uri.getAuthority());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isGooglePhotosUri(Uri uri) {
/* 226 */     return "com.google.android.apps.photos.content".equals(uri.getAuthority());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isGoogleDriveUri(Uri uri) {
/* 234 */     return "com.google.android.apps.docs.storage".equals(uri.getAuthority());
/*     */   }
/*     */   
/*     */   public static String getFileName(@NonNull Context context, Uri uri) {
/* 238 */     String str1 = context.getContentResolver().getType(uri);
/* 239 */     String str2 = null;
/*     */     
/* 241 */     if (str1 == null && context != null) {
/* 242 */       String str = getPath(context, uri);
/* 243 */       if (str == null) {
/* 244 */         str2 = getName(uri.toString());
/*     */       } else {
/* 246 */         File file = new File(str);
/* 247 */         str2 = file.getName();
/*     */       } 
/*     */     } else {
/* 250 */       Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
/* 251 */       if (cursor != null) {
/* 252 */         int i = cursor.getColumnIndex("_display_name");
/* 253 */         cursor.moveToFirst();
/* 254 */         str2 = cursor.getString(i);
/* 255 */         cursor.close();
/*     */       } 
/*     */     } 
/*     */     
/* 259 */     return str2;
/*     */   }
/*     */   
/*     */   public static String getName(String filename) {
/* 263 */     if (filename == null) {
/* 264 */       return null;
/*     */     }
/* 266 */     int i = filename.lastIndexOf('/');
/* 267 */     return filename.substring(i + 1);
/*     */   }
/*     */   
/*     */   public static File getDocumentCacheDir(@NonNull Context context) {
/* 271 */     File file = new File(context.getCacheDir(), "documents");
/* 272 */     if (!file.exists()) {
/* 273 */       file.mkdirs();
/*     */     }
/* 275 */     return file;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static File generateFileName(@Nullable String name, File directory) {
/* 280 */     if (name == null) {
/* 281 */       return null;
/*     */     }
/*     */     
/* 284 */     File file = new File(directory, name);
/*     */     
/* 286 */     if (file.exists()) {
/* 287 */       String str1 = name;
/* 288 */       String str2 = "";
/* 289 */       int i = name.lastIndexOf('.');
/* 290 */       if (i > 0) {
/* 291 */         str1 = name.substring(0, i);
/* 292 */         str2 = name.substring(i);
/*     */       } 
/*     */       
/* 295 */       byte b = 0;
/*     */       
/* 297 */       while (file.exists()) {
/* 298 */         b++;
/* 299 */         name = str1 + '(' + b + ')' + str2;
/* 300 */         file = new File(directory, name);
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/* 305 */       if (!file.createNewFile()) {
/* 306 */         return null;
/*     */       }
/* 308 */     } catch (IOException iOException) {
/* 309 */       return null;
/*     */     } 
/* 311 */     return file;
/*     */   }
/*     */   
/*     */   private static void a(Context paramContext, Uri paramUri, String paramString) {
/* 315 */     InputStream inputStream = null;
/* 316 */     BufferedOutputStream bufferedOutputStream = null;
/*     */     try {
/* 318 */       inputStream = paramContext.getContentResolver().openInputStream(paramUri);
/* 319 */       bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(paramString, false));
/* 320 */       byte[] arrayOfByte = new byte[1024];
/* 321 */       inputStream.read(arrayOfByte);
/*     */       do {
/* 323 */         bufferedOutputStream.write(arrayOfByte);
/* 324 */       } while (inputStream.read(arrayOfByte) != -1);
/* 325 */     } catch (IOException iOException) {
/* 326 */       iOException.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 329 */         if (inputStream != null) inputStream.close(); 
/* 330 */         if (bufferedOutputStream != null) bufferedOutputStream.close(); 
/* 331 */       } catch (IOException iOException) {
/* 332 */         iOException.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/general/files/ImageFilePath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */