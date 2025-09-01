/*     */ package com.pubnub.api.vendor;
/*     */ 
/*     */

import com.pubnub.api.PubNubError;
import com.pubnub.api.PubNubException;
import com.pubnub.api.builder.PubNubErrorBuilder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Crypto
/*     */ {
/*  25 */   byte[] keyBytes = null;
/*  26 */   byte[] ivBytes = null;
/*  27 */   String initializationVector = "0123456789012345";
/*     */   String cipherKey;
/*     */   boolean INIT = false;
/*     */   
/*     */   public Crypto(String cipherKey) {
/*  32 */     this.cipherKey = cipherKey;
/*     */   }
/*     */   
/*     */   public Crypto(String cipherKey, String customInitializationVector) {
/*  36 */     if (customInitializationVector != null) {
/*  37 */       this.initializationVector = customInitializationVector;
/*     */     }
/*     */     
/*  40 */     this.cipherKey = cipherKey;
/*     */   }
/*     */   
/*     */   public static byte[] hexEncode(byte[] input) throws PubNubException {
/*  44 */     StringBuffer stringBuffer = new StringBuffer();
/*  45 */     for (byte b : input)
/*  46 */       stringBuffer.append(Integer.toString((b & 0xFF) + 256, 16).substring(1)); 
/*     */     try {
/*  48 */       return stringBuffer.toString().getBytes("UTF-8");
/*  49 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/*  50 */       throw PubNubException.builder().pubnubError(newCryptoError(12, unsupportedEncodingException.toString())).errormsg(unsupportedEncodingException.getMessage()).build();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static PubNubError newCryptoError(int code, String message) {
/*  56 */     return PubNubErrorBuilder.createCryptoError(code, message);
/*     */   }
/*     */   
/*     */   public static byte[] hexStringToByteArray(String s) {
/*  60 */     int i = s.length();
/*  61 */     byte[] arrayOfByte = new byte[i / 2];
/*  62 */     for (byte b = 0; b < i; b += 2) {
/*  63 */       arrayOfByte[b / 2] = (byte)((Character.digit(s.charAt(b), 16) << 4) + Character.digit(s.charAt(b + 1), 16));
/*     */     }
/*  65 */     return arrayOfByte;
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
/*     */   public static byte[] md5(String input) throws PubNubException {
/*     */     try {
/*  78 */       MessageDigest messageDigest = MessageDigest.getInstance("MD5");
/*  79 */       return messageDigest.digest(input.getBytes("UTF-8"));
/*     */     }
/*  81 */     catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/*  82 */       throw PubNubException.builder().pubnubError(newCryptoError(118, noSuchAlgorithmException.toString())).errormsg(noSuchAlgorithmException.getMessage()).build();
/*  83 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/*  84 */       throw PubNubException.builder().pubnubError(newCryptoError(119, unsupportedEncodingException.toString())).errormsg(unsupportedEncodingException.getMessage()).build();
/*     */     } 
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
/*     */   public static byte[] sha256(byte[] input) throws PubNubException {
/*     */     try {
/*  98 */       MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
/*  99 */       return messageDigest.digest(input);
/*     */     }
/* 101 */     catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/* 102 */       throw PubNubException.builder().pubnubError(newCryptoError(111, noSuchAlgorithmException.toString())).errormsg(noSuchAlgorithmException.getMessage()).build();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initCiphers() throws PubNubException {
/* 107 */     if (this.INIT) {
/*     */       return;
/*     */     }
/*     */     try {
/* 111 */       this
/*     */         
/* 113 */         .keyBytes = (new String(hexEncode(sha256(this.cipherKey.getBytes("UTF-8"))), "UTF-8")).substring(0, 32).toLowerCase().getBytes("UTF-8");
/* 114 */       this.ivBytes = this.initializationVector.getBytes("UTF-8");
/* 115 */       this.INIT = true;
/* 116 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 117 */       throw PubNubException.builder().pubnubError(newCryptoError(11, unsupportedEncodingException.toString())).errormsg(unsupportedEncodingException.getMessage()).build();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String encrypt(String input) throws PubNubException {
/*     */     try {
/* 123 */       initCiphers();
/* 124 */       IvParameterSpec ivParameterSpec = new IvParameterSpec(this.ivBytes);
/* 125 */       SecretKeySpec secretKeySpec = new SecretKeySpec(this.keyBytes, "AES");
/* 126 */       Cipher cipher = null;
/* 127 */       cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 128 */       cipher.init(1, secretKeySpec, ivParameterSpec);
/* 129 */       return new String(Base64.encode(cipher.doFinal(input.getBytes("UTF-8")), 0), Charset.forName("UTF-8"));
/* 130 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/* 131 */       throw PubNubException.builder().errormsg(noSuchAlgorithmException.toString()).build();
/* 132 */     } catch (NoSuchPaddingException noSuchPaddingException) {
/* 133 */       throw PubNubException.builder().errormsg(noSuchPaddingException.toString()).build();
/* 134 */     } catch (InvalidKeyException invalidKeyException) {
/* 135 */       throw PubNubException.builder().errormsg(invalidKeyException.toString()).build();
/* 136 */     } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
/* 137 */       throw PubNubException.builder().errormsg(invalidAlgorithmParameterException.toString()).build();
/* 138 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 139 */       throw PubNubException.builder().errormsg(unsupportedEncodingException.toString()).build();
/* 140 */     } catch (IllegalBlockSizeException illegalBlockSizeException) {
/* 141 */       throw PubNubException.builder().errormsg(illegalBlockSizeException.toString()).build();
/* 142 */     } catch (BadPaddingException badPaddingException) {
/* 143 */       throw PubNubException.builder().errormsg(badPaddingException.toString()).build();
/*     */     } 
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
/*     */   public String decrypt(String cipher_text) throws PubNubException {
/*     */     try {
/* 157 */       initCiphers();
/* 158 */       IvParameterSpec ivParameterSpec = new IvParameterSpec(this.ivBytes);
/* 159 */       SecretKeySpec secretKeySpec = new SecretKeySpec(this.keyBytes, "AES");
/* 160 */       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 161 */       cipher.init(2, secretKeySpec, ivParameterSpec);
/* 162 */       return new String(cipher.doFinal(Base64.decode(cipher_text, 0)), "UTF-8");
/* 163 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 164 */       throw PubNubException.builder().errormsg(illegalArgumentException.toString()).build();
/* 165 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 166 */       throw PubNubException.builder().errormsg(unsupportedEncodingException.toString()).build();
/* 167 */     } catch (IllegalBlockSizeException illegalBlockSizeException) {
/* 168 */       throw PubNubException.builder().errormsg(illegalBlockSizeException.toString()).build();
/* 169 */     } catch (BadPaddingException badPaddingException) {
/* 170 */       throw PubNubException.builder().errormsg(badPaddingException.toString()).build();
/* 171 */     } catch (InvalidKeyException invalidKeyException) {
/* 172 */       throw PubNubException.builder().errormsg(invalidKeyException.toString()).build();
/* 173 */     } catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
/* 174 */       throw PubNubException.builder().errormsg(invalidAlgorithmParameterException.toString()).build();
/* 175 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/* 176 */       throw PubNubException.builder().errormsg(noSuchAlgorithmException.toString()).build();
/* 177 */     } catch (NoSuchPaddingException noSuchPaddingException) {
/* 178 */       throw PubNubException.builder().errormsg(noSuchPaddingException.toString()).build();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/vendor/Crypto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */