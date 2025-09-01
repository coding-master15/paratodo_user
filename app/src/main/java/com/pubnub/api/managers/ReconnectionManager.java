/*     */ package com.pubnub.api.managers;
/*     */ 
/*     */

import android.util.Log;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.ReconnectionCallback;
import com.pubnub.api.enums.PNReconnectionPolicy;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.PNTimeResult;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReconnectionManager
/*     */ {
/*     */   private static final int INTERVAL = 3;
/*     */   private static final int MINEXPONENTIALBACKOFF = 1;
/*     */   private static final int MAXEXPONENTIALBACKOFF = 32;
/*     */   private static final int MILLISECONDS = 1000;
/*     */   private ReconnectionCallback callback;
/*     */   private PubNub pubnub;
/*  25 */   private int exponentialMultiplier = 1;
/*  26 */   private int failedCalls = 0;
/*     */ 
/*     */   
/*     */   private Timer timer;
/*     */ 
/*     */   
/*     */   public ReconnectionManager(PubNub pubnubInstance) {
/*  33 */     this.pubnub = pubnubInstance;
/*     */   }
/*     */   
/*     */   public ReconnectionManager setReconnectionListener(ReconnectionCallback reconnectionCallback) {
/*  37 */     this.callback = reconnectionCallback;
/*  38 */     return this;
/*     */   }
/*     */   
/*     */   public void startPolling() {
/*  42 */     if (this.pubnub.getConfiguration().getReconnectionPolicy() == PNReconnectionPolicy.NONE) {
/*  43 */       Log.w("", "reconnection policy is disabled, please handle reconnection manually.");
/*     */       
/*     */       return;
/*     */     } 
/*  47 */     this.exponentialMultiplier = 1;
/*  48 */     this.failedCalls = 0;
/*     */     
/*  50 */     registerHeartbeatTimer();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void registerHeartbeatTimer() {
/*  56 */     stopHeartbeatTimer();
/*     */     
/*  58 */     if (this.pubnub.getConfiguration().getReconnectionPolicy() == PNReconnectionPolicy.NONE) {
/*  59 */       Log.w("", "reconnection policy is disabled, please handle reconnection manually.");
/*     */       
/*     */       return;
/*     */     } 
/*  63 */     int i = this.pubnub.getConfiguration().getMaximumReconnectionRetries();
/*  64 */     if (i != -1 && this.failedCalls >= i) {
/*  65 */       this.callback.onMaxReconnectionExhaustion();
/*     */       
/*     */       return;
/*     */     } 
/*  69 */     this.timer = new Timer();
/*  70 */     int j = 3;
/*     */     
/*  72 */     if (this.pubnub.getConfiguration().getReconnectionPolicy() == PNReconnectionPolicy.EXPONENTIAL) {
/*  73 */       j = (int)(Math.pow(2.0D, this.exponentialMultiplier) - 1.0D);
/*  74 */       if (j > 32) {
/*  75 */         j = 1;
/*  76 */         this.exponentialMultiplier = 1;
/*  77 */         Log.d("", "timerInterval > MAXEXPONENTIALBACKOFF at: " + Calendar.getInstance().getTime().toString());
/*  78 */       } else if (j < 1) {
/*  79 */         j = 1;
/*     */       } 
/*  81 */       Log.d("", "timerInterval = " + String.valueOf(j) + " at: " + Calendar.getInstance().getTime().toString());
/*     */     } 
/*     */     
/*  84 */     if (this.pubnub.getConfiguration().getReconnectionPolicy() == PNReconnectionPolicy.LINEAR) {
/*  85 */       j = 3;
/*     */     }
/*     */     
/*  88 */     this.timer.schedule(new TimerTask()
/*     */         {
/*     */           public void run() {
/*  91 */             ReconnectionManager.this.callTime();
/*     */           }
/*     */         },  (j * 1000), (j * 1000));
/*     */   }
/*     */   
/*     */   private void stopHeartbeatTimer() {
/*  97 */     if (this.timer != null) {
/*  98 */       this.timer.cancel();
/*  99 */       this.timer = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void callTime() {
/*     */     try {
/* 105 */       this.pubnub.time().async(new PNCallback<PNTimeResult>()
/*     */           {
/*     */             public void onResponse(PNTimeResult result, PNStatus status) {
/* 108 */               if (!status.isError()) {
/* 109 */                 ReconnectionManager.this.stopHeartbeatTimer();
/* 110 */                 ReconnectionManager.this.callback.onReconnection();
/*     */               } else {
/* 112 */                 Log.d("", "callTime() at: " + Calendar.getInstance().getTime().toString());
/* 113 */                 ReconnectionManager.this.exponentialMultiplier++;
/* 114 */                 ReconnectionManager.this.failedCalls++;
/* 115 */                 ReconnectionManager.this.registerHeartbeatTimer();
/*     */               } 
/*     */             }
/*     */           });
/* 119 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/managers/ReconnectionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */