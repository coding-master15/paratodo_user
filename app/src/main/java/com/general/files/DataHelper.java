/*    */ package com.general.files;
/*    */ 
/*    */

import android.content.Context;
import android.util.Base64;

import com.InfoProvider;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataHelper
/*    */ {
/*    */   private IvParameterSpec a;
/*    */   private SecretKeySpec b;
/*    */   private Cipher c;
/*    */   private static DataHelper d;
/*    */   
/*    */   public static DataHelper getInstance(Context mContext) {
/* 23 */     if (d == null) {
/* 24 */       d = new DataHelper(a(mContext));
/*    */     }
/* 26 */     return d;
/*    */   }
/*    */   
/*    */   private static String a(Context paramContext) {
/* 30 */     return (new InfoProvider(paramContext)).getAppConfigurationStr();
/*    */   }
/*    */   
/*    */   public DataHelper(String secretKey) {
/* 34 */     this.a = new IvParameterSpec(secretKey.getBytes());
/* 35 */     this.b = new SecretKeySpec(secretKey.getBytes(), "AES");
/*    */     
/*    */     try {
/* 38 */       this.c = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 39 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/*    */       
/* 41 */       noSuchAlgorithmException.printStackTrace();
/* 42 */     } catch (NoSuchPaddingException noSuchPaddingException) {
/*    */       
/* 44 */       noSuchPaddingException.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public String encrypt(String valueToEncrypt) throws Exception {
/* 49 */     return Base64.encodeToString(a(valueToEncrypt), 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public String decrypt(String valueToDecrypt) throws Exception {
/* 54 */     return new String(b(valueToDecrypt));
/*    */   }
/*    */   
/*    */   private byte[] a(String paramString) throws Exception {
/* 58 */     if (paramString == null || paramString.length() == 0) {
/* 59 */       throw new Exception("Empty string");
/*    */     }
/*    */     
/* 62 */     byte[] arrayOfByte = null;
/*    */     try {
/* 64 */       this.c.init(1, this.b, this.a);
/* 65 */       arrayOfByte = this.c.doFinal(paramString.getBytes());
/* 66 */     } catch (Exception exception) {
/* 67 */       throw new Exception("[encrypt] " + exception.getMessage());
/*    */     } 
/* 69 */     return arrayOfByte;
/*    */   }
/*    */   
/*    */   private byte[] b(String paramString) throws Exception {
/* 73 */     if (paramString == null || paramString.length() == 0) {
/* 74 */       throw new Exception("Empty string");
/*    */     }
/*    */     
/* 77 */     byte[] arrayOfByte = null;
/*    */     try {
/* 79 */       this.c.init(2, this.b, this.a);
/*    */       
/* 81 */       arrayOfByte = this.c.doFinal(
/* 82 */           Base64.decode(paramString, 0));
/* 83 */     } catch (Exception exception) {
/* 84 */       throw new Exception("[decrypt] " + exception.getMessage());
/*    */     } 
/* 86 */     return arrayOfByte;
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/general/files/DataHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */