/*     */ package com.pubnub.api.models.consumer.access_manager;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PNAccessManagerAuditResult
/*     */ {
/*     */   private String level;
/*     */   private String subscribeKey;
/*     */   private String channel;
/*     */   private String channelGroup;
/*     */   private Map<String, PNAccessManagerKeyData> authKeys;
/*     */   
/*     */   PNAccessManagerAuditResult(String level, String subscribeKey, String channel, String channelGroup, Map<String, PNAccessManagerKeyData> authKeys) {
/*  18 */     this.level = level;
/*  19 */     this.subscribeKey = subscribeKey;
/*  20 */     this.channel = channel;
/*  21 */     this.channelGroup = channelGroup;
/*  22 */     this.authKeys = authKeys;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PNAccessManagerAuditResultBuilder builder() {
/*  27 */     return new PNAccessManagerAuditResultBuilder();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLevel() {
/*  32 */     return this.level;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubscribeKey() {
/*  37 */     return this.subscribeKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getChannel() {
/*  42 */     return this.channel;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getChannelGroup() {
/*  47 */     return this.channelGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, PNAccessManagerKeyData> getAuthKeys() {
/*  52 */     return this.authKeys;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  59 */     return "PNAccessManagerAuditResult(level=" + getLevel() + ", subscribeKey=" + getSubscribeKey() + ", channel=" + getChannel() + ", channelGroup=" + getChannelGroup() + ", authKeys=" + getAuthKeys() + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PNAccessManagerAuditResultBuilder
/*     */   {
/*     */     private String level;
/*     */ 
/*     */ 
/*     */     
/*     */     private String subscribeKey;
/*     */ 
/*     */ 
/*     */     
/*     */     private String channel;
/*     */ 
/*     */     
/*     */     private String channelGroup;
/*     */ 
/*     */     
/*     */     private Map<String, PNAccessManagerKeyData> authKeys;
/*     */ 
/*     */ 
/*     */     
/*     */     public PNAccessManagerAuditResultBuilder level(String level) {
/*  86 */       this.level = level;
/*  87 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNAccessManagerAuditResultBuilder subscribeKey(String subscribeKey) {
/*  92 */       this.subscribeKey = subscribeKey;
/*  93 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNAccessManagerAuditResultBuilder channel(String channel) {
/*  98 */       this.channel = channel;
/*  99 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNAccessManagerAuditResultBuilder channelGroup(String channelGroup) {
/* 104 */       this.channelGroup = channelGroup;
/* 105 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNAccessManagerAuditResultBuilder authKeys(Map<String, PNAccessManagerKeyData> authKeys) {
/* 110 */       this.authKeys = authKeys;
/* 111 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNAccessManagerAuditResult build() {
/* 116 */       return new PNAccessManagerAuditResult(this.level, this.subscribeKey, this.channel, this.channelGroup, this.authKeys);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 123 */       return "PNAccessManagerAuditResult.PNAccessManagerAuditResultBuilder(level=" + this.level + ", subscribeKey=" + this.subscribeKey + ", channel=" + this.channel + ", channelGroup=" + this.channelGroup + ", authKeys=" + this.authKeys + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/access_manager/PNAccessManagerAuditResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */