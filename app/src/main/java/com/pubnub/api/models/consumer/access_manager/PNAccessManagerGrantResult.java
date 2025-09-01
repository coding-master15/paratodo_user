/*     */ package com.pubnub.api.models.consumer.access_manager;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PNAccessManagerGrantResult
/*     */ {
/*     */   private String level;
/*     */   private int ttl;
/*     */   private String subscribeKey;
/*     */   private Map<String, Map<String, PNAccessManagerKeyData>> channels;
/*     */   private Map<String, Map<String, PNAccessManagerKeyData>> channelGroups;
/*     */   
/*     */   PNAccessManagerGrantResult(String level, int ttl, String subscribeKey, Map<String, Map<String, PNAccessManagerKeyData>> channels, Map<String, Map<String, PNAccessManagerKeyData>> channelGroups) {
/*  17 */     this.level = level;
/*  18 */     this.ttl = ttl;
/*  19 */     this.subscribeKey = subscribeKey;
/*  20 */     this.channels = channels;
/*  21 */     this.channelGroups = channelGroups;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PNAccessManagerGrantResultBuilder builder() {
/*  26 */     return new PNAccessManagerGrantResultBuilder();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLevel() {
/*  31 */     return this.level;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTtl() {
/*  36 */     return this.ttl;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubscribeKey() {
/*  41 */     return this.subscribeKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Map<String, PNAccessManagerKeyData>> getChannels() {
/*  46 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Map<String, PNAccessManagerKeyData>> getChannelGroups() {
/*  51 */     return this.channelGroups;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  58 */     return "PNAccessManagerGrantResult(level=" + getLevel() + ", ttl=" + getTtl() + ", subscribeKey=" + getSubscribeKey() + ", channels=" + getChannels() + ", channelGroups=" + getChannelGroups() + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PNAccessManagerGrantResultBuilder
/*     */   {
/*     */     private String level;
/*     */ 
/*     */ 
/*     */     
/*     */     private int ttl;
/*     */ 
/*     */ 
/*     */     
/*     */     private String subscribeKey;
/*     */ 
/*     */     
/*     */     private Map<String, Map<String, PNAccessManagerKeyData>> channels;
/*     */ 
/*     */     
/*     */     private Map<String, Map<String, PNAccessManagerKeyData>> channelGroups;
/*     */ 
/*     */ 
/*     */     
/*     */     public PNAccessManagerGrantResultBuilder level(String level) {
/*  85 */       this.level = level;
/*  86 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNAccessManagerGrantResultBuilder ttl(int ttl) {
/*  91 */       this.ttl = ttl;
/*  92 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNAccessManagerGrantResultBuilder subscribeKey(String subscribeKey) {
/*  97 */       this.subscribeKey = subscribeKey;
/*  98 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNAccessManagerGrantResultBuilder channels(Map<String, Map<String, PNAccessManagerKeyData>> channels) {
/* 103 */       this.channels = channels;
/* 104 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNAccessManagerGrantResultBuilder channelGroups(Map<String, Map<String, PNAccessManagerKeyData>> channelGroups) {
/* 109 */       this.channelGroups = channelGroups;
/* 110 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNAccessManagerGrantResult build() {
/* 115 */       return new PNAccessManagerGrantResult(this.level, this.ttl, this.subscribeKey, this.channels, this.channelGroups);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 122 */       return "PNAccessManagerGrantResult.PNAccessManagerGrantResultBuilder(level=" + this.level + ", ttl=" + this.ttl + ", subscribeKey=" + this.subscribeKey + ", channels=" + this.channels + ", channelGroups=" + this.channelGroups + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/access_manager/PNAccessManagerGrantResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */