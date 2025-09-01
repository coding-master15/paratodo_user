/*     */ package com.pubnub.api.vendor;
/*     */ 
/*     */

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Base64
/*     */ {
/*     */   public static final int DEFAULT = 0;
/*     */   public static final int NO_PADDING = 1;
/*     */   public static final int NO_WRAP = 2;
/*     */   public static final int CRLF = 4;
/*     */   public static final int URL_SAFE = 8;
/*     */   public static final int NO_CLOSE = 16;
/*     */   
/*     */   public static byte[] decode(String str, int flags) {
/*  94 */     return decode(str.getBytes(Charset.forName("UTF-8")), flags);
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
/*     */   
/*     */   public static byte[] decode(byte[] input, int flags) {
/* 111 */     return decode(input, 0, input.length, flags);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] decode(byte[] input, int offset, int len, int flags) {
/* 132 */     Decoder decoder = new Decoder(flags, new byte[len * 3 / 4]);
/*     */     
/* 134 */     if (!decoder.process(input, offset, len, true)) {
/* 135 */       throw new IllegalArgumentException("bad base-64");
/*     */     }
/*     */ 
/*     */     
/* 139 */     if (decoder.op == decoder.output.length) {
/* 140 */       return decoder.output;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 145 */     byte[] arrayOfByte = new byte[decoder.op];
/* 146 */     System.arraycopy(decoder.output, 0, arrayOfByte, 0, decoder.op);
/* 147 */     return arrayOfByte;
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
/*     */   public static String encodeToString(byte[] input, int flags) {
/*     */     try {
/* 161 */       return new String(encode(input, flags), "US-ASCII");
/* 162 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/*     */       
/* 164 */       throw new AssertionError(unsupportedEncodingException);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String encodeToString(byte[] input, int offset, int len, int flags) {
/*     */     try {
/* 186 */       return new String(encode(input, offset, len, flags), "US-ASCII");
/* 187 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {
/*     */       
/* 189 */       throw new AssertionError(unsupportedEncodingException);
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
/*     */   
/*     */   public static byte[] encode(byte[] input, int flags) {
/* 203 */     return encode(input, 0, input.length, flags);
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
/*     */   public static byte[] encode(byte[] input, int offset, int len, int flags) {
/* 219 */     Encoder encoder = new Encoder(flags, null);
/*     */ 
/*     */     
/* 222 */     int i = len / 3 * 4;
/*     */ 
/*     */     
/* 225 */     if (encoder.do_padding) {
/* 226 */       if (len % 3 > 0) {
/* 227 */         i += 4;
/*     */       }
/*     */     } else {
/* 230 */       switch (len % 3) {
/*     */ 
/*     */         
/*     */         case 1:
/* 234 */           i += 2;
/*     */           break;
/*     */         case 2:
/* 237 */           i += 3;
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 245 */     if (encoder.do_newline && len > 0) {
/* 246 */       i += ((len - 1) / 57 + 1) * (encoder.do_cr ? 2 : 1);
/*     */     }
/*     */ 
/*     */     
/* 250 */     encoder.output = new byte[i];
/* 251 */     encoder.process(input, offset, len, true);
/*     */     
/* 253 */     assert encoder.op == i;
/*     */     
/* 255 */     return encoder.output;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static abstract class Coder
/*     */   {
/*     */     public byte[] output;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int op;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public abstract boolean process(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2, boolean param1Boolean);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public abstract int maxOutputSize(int param1Int);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Decoder
/*     */     extends Coder
/*     */   {
/* 289 */     private static final int[] DECODE = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 312 */     private static final int[] DECODE_WEBSAFE = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final int SKIP = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final int EQUALS = -2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final int[] alphabet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int state;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Decoder(int flags, byte[] output) {
/* 350 */       this.output = output;
/*     */       
/* 352 */       this.alphabet = ((flags & 0x8) == 0) ? DECODE : DECODE_WEBSAFE;
/* 353 */       this.state = 0;
/* 354 */       this.value = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int maxOutputSize(int len) {
/* 362 */       return len * 3 / 4 + 10;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean process(byte[] input, int offset, int len, boolean finish) {
/* 372 */       if (this.state == 6) return false;
/*     */       
/* 374 */       int i = offset;
/* 375 */       len += offset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 382 */       int j = this.state;
/* 383 */       int k = this.value;
/* 384 */       byte b = 0;
/* 385 */       byte[] arrayOfByte = this.output;
/* 386 */       int[] arrayOfInt = this.alphabet;
/*     */       
/* 388 */       while (i < len) {
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
/* 403 */         if (j == 0) {
/* 404 */           while (i + 4 <= len && (k = arrayOfInt[input[i] & 0xFF] << 18 | arrayOfInt[input[i + 1] & 0xFF] << 12 | arrayOfInt[input[i + 2] & 0xFF] << 6 | arrayOfInt[input[i + 3] & 0xFF]) >= 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 409 */             arrayOfByte[b + 2] = (byte)k;
/* 410 */             arrayOfByte[b + 1] = (byte)(k >> 8);
/* 411 */             arrayOfByte[b] = (byte)(k >> 16);
/* 412 */             b += 3;
/* 413 */             i += 4;
/*     */           } 
/* 415 */           if (i >= len) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 423 */         int m = arrayOfInt[input[i++] & 0xFF];
/*     */         
/* 425 */         switch (j) {
/*     */           case 0:
/* 427 */             if (m >= 0) {
/* 428 */               k = m;
/* 429 */               j++; continue;
/* 430 */             }  if (m != -1) {
/* 431 */               this.state = 6;
/* 432 */               return false;
/*     */             } 
/*     */ 
/*     */           
/*     */           case 1:
/* 437 */             if (m >= 0) {
/* 438 */               k = k << 6 | m;
/* 439 */               j++; continue;
/* 440 */             }  if (m != -1) {
/* 441 */               this.state = 6;
/* 442 */               return false;
/*     */             } 
/*     */ 
/*     */           
/*     */           case 2:
/* 447 */             if (m >= 0) {
/* 448 */               k = k << 6 | m;
/* 449 */               j++; continue;
/* 450 */             }  if (m == -2) {
/*     */ 
/*     */               
/* 453 */               arrayOfByte[b++] = (byte)(k >> 4);
/* 454 */               j = 4; continue;
/* 455 */             }  if (m != -1) {
/* 456 */               this.state = 6;
/* 457 */               return false;
/*     */             } 
/*     */ 
/*     */           
/*     */           case 3:
/* 462 */             if (m >= 0) {
/*     */               
/* 464 */               k = k << 6 | m;
/* 465 */               arrayOfByte[b + 2] = (byte)k;
/* 466 */               arrayOfByte[b + 1] = (byte)(k >> 8);
/* 467 */               arrayOfByte[b] = (byte)(k >> 16);
/* 468 */               b += 3;
/* 469 */               j = 0; continue;
/* 470 */             }  if (m == -2) {
/*     */ 
/*     */               
/* 473 */               arrayOfByte[b + 1] = (byte)(k >> 2);
/* 474 */               arrayOfByte[b] = (byte)(k >> 10);
/* 475 */               b += 2;
/* 476 */               j = 5; continue;
/* 477 */             }  if (m != -1) {
/* 478 */               this.state = 6;
/* 479 */               return false;
/*     */             } 
/*     */ 
/*     */           
/*     */           case 4:
/* 484 */             if (m == -2) {
/* 485 */               j++; continue;
/* 486 */             }  if (m != -1) {
/* 487 */               this.state = 6;
/* 488 */               return false;
/*     */             } 
/*     */ 
/*     */           
/*     */           case 5:
/* 493 */             if (m != -1) {
/* 494 */               this.state = 6;
/* 495 */               return false;
/*     */             } 
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/* 501 */       if (!finish) {
/*     */ 
/*     */         
/* 504 */         this.state = j;
/* 505 */         this.value = k;
/* 506 */         this.op = b;
/* 507 */         return true;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 513 */       switch (j) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/* 520 */           this.state = 6;
/* 521 */           return false;
/*     */ 
/*     */         
/*     */         case 2:
/* 525 */           arrayOfByte[b++] = (byte)(k >> 4);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 530 */           arrayOfByte[b++] = (byte)(k >> 10);
/* 531 */           arrayOfByte[b++] = (byte)(k >> 2);
/*     */           break;
/*     */         
/*     */         case 4:
/* 535 */           this.state = 6;
/* 536 */           return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 543 */       this.state = j;
/* 544 */       this.op = b;
/* 545 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Encoder
/*     */     extends Coder
/*     */   {
/*     */     public static final int LINE_GROUPS = 19;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 561 */     private static final byte[] ENCODE = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
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
/* 572 */     private static final byte[] ENCODE_WEBSAFE = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
/*     */     
/*     */     public final boolean do_padding;
/*     */     
/*     */     public final boolean do_newline;
/*     */     
/*     */     public final boolean do_cr;
/*     */     
/*     */     private final byte[] tail;
/*     */     
/*     */     private final byte[] alphabet;
/*     */     int tailLen;
/*     */     private int count;
/*     */     
/*     */     public Encoder(int flags, byte[] output) {
/* 587 */       this.output = output;
/*     */       
/* 589 */       this.do_padding = ((flags & 0x1) == 0);
/* 590 */       this.do_newline = ((flags & 0x2) == 0);
/* 591 */       this.do_cr = ((flags & 0x4) != 0);
/* 592 */       this.alphabet = ((flags & 0x8) == 0) ? ENCODE : ENCODE_WEBSAFE;
/*     */       
/* 594 */       this.tail = new byte[2];
/* 595 */       this.tailLen = 0;
/*     */       
/* 597 */       this.count = this.do_newline ? 19 : -1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int maxOutputSize(int len) {
/* 605 */       return len * 8 / 5 + 10;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean process(byte[] input, int offset, int len, boolean finish) {
/* 610 */       byte[] arrayOfByte1 = this.alphabet;
/* 611 */       byte[] arrayOfByte2 = this.output;
/* 612 */       byte b = 0;
/* 613 */       int i = this.count;
/*     */       
/* 615 */       int j = offset;
/* 616 */       len += offset;
/* 617 */       int k = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 623 */       switch (this.tailLen) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/* 629 */           if (j + 2 <= len) {
/*     */ 
/*     */             
/* 632 */             k = (this.tail[0] & 0xFF) << 16 | (input[j++] & 0xFF) << 8 | input[j++] & 0xFF;
/*     */ 
/*     */             
/* 635 */             this.tailLen = 0;
/*     */           } 
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 641 */           if (j + 1 <= len) {
/*     */             
/* 643 */             k = (this.tail[0] & 0xFF) << 16 | (this.tail[1] & 0xFF) << 8 | input[j++] & 0xFF;
/*     */ 
/*     */             
/* 646 */             this.tailLen = 0;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */       
/* 651 */       if (k != -1) {
/* 652 */         arrayOfByte2[b++] = arrayOfByte1[k >> 18 & 0x3F];
/* 653 */         arrayOfByte2[b++] = arrayOfByte1[k >> 12 & 0x3F];
/* 654 */         arrayOfByte2[b++] = arrayOfByte1[k >> 6 & 0x3F];
/* 655 */         arrayOfByte2[b++] = arrayOfByte1[k & 0x3F];
/* 656 */         if (--i == 0) {
/* 657 */           if (this.do_cr) arrayOfByte2[b++] = 13; 
/* 658 */           arrayOfByte2[b++] = 10;
/* 659 */           i = 19;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 668 */       while (j + 3 <= len) {
/* 669 */         k = (input[j] & 0xFF) << 16 | (input[j + 1] & 0xFF) << 8 | input[j + 2] & 0xFF;
/*     */ 
/*     */         
/* 672 */         arrayOfByte2[b] = arrayOfByte1[k >> 18 & 0x3F];
/* 673 */         arrayOfByte2[b + 1] = arrayOfByte1[k >> 12 & 0x3F];
/* 674 */         arrayOfByte2[b + 2] = arrayOfByte1[k >> 6 & 0x3F];
/* 675 */         arrayOfByte2[b + 3] = arrayOfByte1[k & 0x3F];
/* 676 */         j += 3;
/* 677 */         b += 4;
/* 678 */         if (--i == 0) {
/* 679 */           if (this.do_cr) arrayOfByte2[b++] = 13; 
/* 680 */           arrayOfByte2[b++] = 10;
/* 681 */           i = 19;
/*     */         } 
/*     */       } 
/*     */       
/* 685 */       if (finish) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 691 */         if (j - this.tailLen == len - 1) {
/* 692 */           byte b1 = 0;
/* 693 */           k = (((this.tailLen > 0) ? this.tail[b1++] : input[j++]) & 0xFF) << 4;
/* 694 */           this.tailLen -= b1;
/* 695 */           arrayOfByte2[b++] = arrayOfByte1[k >> 6 & 0x3F];
/* 696 */           arrayOfByte2[b++] = arrayOfByte1[k & 0x3F];
/* 697 */           if (this.do_padding) {
/* 698 */             arrayOfByte2[b++] = 61;
/* 699 */             arrayOfByte2[b++] = 61;
/*     */           } 
/* 701 */           if (this.do_newline) {
/* 702 */             if (this.do_cr) arrayOfByte2[b++] = 13; 
/* 703 */             arrayOfByte2[b++] = 10;
/*     */           } 
/* 705 */         } else if (j - this.tailLen == len - 2) {
/* 706 */           byte b1 = 0;
/* 707 */           k = (((this.tailLen > 1) ? this.tail[b1++] : input[j++]) & 0xFF) << 10 | (((this.tailLen > 0) ? this.tail[b1++] : input[j++]) & 0xFF) << 2;
/*     */           
/* 709 */           this.tailLen -= b1;
/* 710 */           arrayOfByte2[b++] = arrayOfByte1[k >> 12 & 0x3F];
/* 711 */           arrayOfByte2[b++] = arrayOfByte1[k >> 6 & 0x3F];
/* 712 */           arrayOfByte2[b++] = arrayOfByte1[k & 0x3F];
/* 713 */           if (this.do_padding) {
/* 714 */             arrayOfByte2[b++] = 61;
/*     */           }
/* 716 */           if (this.do_newline) {
/* 717 */             if (this.do_cr) arrayOfByte2[b++] = 13; 
/* 718 */             arrayOfByte2[b++] = 10;
/*     */           } 
/* 720 */         } else if (this.do_newline && b > 0 && i != 19) {
/* 721 */           if (this.do_cr) arrayOfByte2[b++] = 13; 
/* 722 */           arrayOfByte2[b++] = 10;
/*     */         } 
/*     */         
/* 725 */         assert this.tailLen == 0;
/* 726 */         assert j == len;
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 731 */       else if (j == len - 1) {
/* 732 */         this.tail[this.tailLen++] = input[j];
/* 733 */       } else if (j == len - 2) {
/* 734 */         this.tail[this.tailLen++] = input[j];
/* 735 */         this.tail[this.tailLen++] = input[j + 1];
/*     */       } 
/*     */ 
/*     */       
/* 739 */       this.op = b;
/* 740 */       this.count = i;
/*     */       
/* 742 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/vendor/Base64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */