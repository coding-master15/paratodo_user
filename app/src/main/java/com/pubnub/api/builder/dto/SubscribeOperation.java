/*     */ package com.pubnub.api.builder.dto;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SubscribeOperation
/*     */ {
/*     */   private List<String> channels;
/*     */   private List<String> channelGroups;
/*     */   private boolean presenceEnabled;
/*     */   private Long timetoken;
/*     */   
/*     */   SubscribeOperation(List<String> channels, List<String> channelGroups, boolean presenceEnabled, Long timetoken) {
/*  17 */     this.channels = channels;
/*  18 */     this.channelGroups = channelGroups;
/*  19 */     this.presenceEnabled = presenceEnabled;
/*  20 */     this.timetoken = timetoken;
/*     */   }
/*     */ 
/*     */   
/*     */   public static SubscribeOperationBuilder builder() {
/*  25 */     return new SubscribeOperationBuilder();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getChannels() {
/*  30 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getChannelGroups() {
/*  35 */     return this.channelGroups;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPresenceEnabled() {
/*  40 */     return this.presenceEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getTimetoken() {
/*  45 */     return this.timetoken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SubscribeOperationBuilder
/*     */   {
/*     */     private List<String> channels;
/*     */ 
/*     */ 
/*     */     
/*     */     private List<String> channelGroups;
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean presenceEnabled;
/*     */ 
/*     */     
/*     */     private Long timetoken;
/*     */ 
/*     */ 
/*     */     
/*     */     public SubscribeOperationBuilder channels(List<String> channels) {
/*  69 */       this.channels = channels;
/*  70 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public SubscribeOperationBuilder channelGroups(List<String> channelGroups) {
/*  75 */       this.channelGroups = channelGroups;
/*  76 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public SubscribeOperationBuilder presenceEnabled(boolean presenceEnabled) {
/*  81 */       this.presenceEnabled = presenceEnabled;
/*  82 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public SubscribeOperationBuilder timetoken(Long timetoken) {
/*  87 */       this.timetoken = timetoken;
/*  88 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public SubscribeOperation build() {
/*  93 */       return new SubscribeOperation(this.channels, this.channelGroups, this.presenceEnabled, this.timetoken);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 100 */       return "SubscribeOperation.SubscribeOperationBuilder(channels=" + this.channels + ", channelGroups=" + this.channelGroups + ", presenceEnabled=" + this.presenceEnabled + ", timetoken=" + this.timetoken + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/builder/dto/SubscribeOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */