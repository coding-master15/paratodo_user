/*     */ package com.pubnub.api.models.consumer.pubsub;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PNMessageResult
/*     */ {
/*     */   private JsonElement message;
/*     */   @Deprecated
/*     */   private String subscribedChannel;
/*     */   @Deprecated
/*     */   private String actualChannel;
/*     */   private String channel;
/*     */   private String subscription;
/*     */   private Long timetoken;
/*     */   private JsonElement userMetadata;
/*     */   private String publisher;
/*     */   
/*     */   PNMessageResult(JsonElement message, String subscribedChannel, String actualChannel, String channel, String subscription, Long timetoken, JsonElement userMetadata, String publisher) {
/*  24 */     this.message = message;
/*  25 */     this.subscribedChannel = subscribedChannel;
/*  26 */     this.actualChannel = actualChannel;
/*  27 */     this.channel = channel;
/*  28 */     this.subscription = subscription;
/*  29 */     this.timetoken = timetoken;
/*  30 */     this.userMetadata = userMetadata;
/*  31 */     this.publisher = publisher;
/*     */   }
/*     */   
/*     */   public static PNMessageResultBuilder builder() {
/*  35 */     return new PNMessageResultBuilder();
/*     */   }
/*     */   
/*     */   public JsonElement getMessage() {
/*  39 */     return this.message;
/*     */   }
/*     */   
/*     */   public String getSubscribedChannel() {
/*  43 */     return this.subscribedChannel;
/*     */   }
/*     */   
/*     */   public String getActualChannel() {
/*  47 */     return this.actualChannel;
/*     */   }
/*     */   
/*     */   public String getChannel() {
/*  51 */     return this.channel;
/*     */   }
/*     */   
/*     */   public String getSubscription() {
/*  55 */     return this.subscription;
/*     */   }
/*     */   
/*     */   public Long getTimetoken() {
/*  59 */     return this.timetoken;
/*     */   }
/*     */   
/*     */   public JsonElement getUserMetadata() {
/*  63 */     return this.userMetadata;
/*     */   }
/*     */   
/*     */   public String getPublisher() {
/*  67 */     return this.publisher;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  71 */     return "PNMessageResult(message=" + getMessage() + ", subscribedChannel=" + getSubscribedChannel() + ", actualChannel=" + getActualChannel() + ", channel=" + getChannel() + ", subscription=" + getSubscription() + ", timetoken=" + getTimetoken() + ", userMetadata=" + getUserMetadata() + ", publisher=" + getPublisher() + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PNMessageResultBuilder
/*     */   {
/*     */     private JsonElement message;
/*     */     
/*     */     private String subscribedChannel;
/*     */     
/*     */     private String actualChannel;
/*     */     
/*     */     private String channel;
/*     */     
/*     */     private String subscription;
/*     */     
/*     */     private Long timetoken;
/*     */     
/*     */     private JsonElement userMetadata;
/*     */     
/*     */     private String publisher;
/*     */ 
/*     */     
/*     */     public PNMessageResultBuilder message(JsonElement message) {
/*  96 */       this.message = message;
/*  97 */       return this;
/*     */     }
/*     */     
/*     */     public PNMessageResultBuilder subscribedChannel(String subscribedChannel) {
/* 101 */       this.subscribedChannel = subscribedChannel;
/* 102 */       return this;
/*     */     }
/*     */     
/*     */     public PNMessageResultBuilder actualChannel(String actualChannel) {
/* 106 */       this.actualChannel = actualChannel;
/* 107 */       return this;
/*     */     }
/*     */     
/*     */     public PNMessageResultBuilder channel(String channel) {
/* 111 */       this.channel = channel;
/* 112 */       return this;
/*     */     }
/*     */     
/*     */     public PNMessageResultBuilder subscription(String subscription) {
/* 116 */       this.subscription = subscription;
/* 117 */       return this;
/*     */     }
/*     */     
/*     */     public PNMessageResultBuilder timetoken(Long timetoken) {
/* 121 */       this.timetoken = timetoken;
/* 122 */       return this;
/*     */     }
/*     */     
/*     */     public PNMessageResultBuilder userMetadata(JsonElement userMetadata) {
/* 126 */       this.userMetadata = userMetadata;
/* 127 */       return this;
/*     */     }
/*     */     
/*     */     public PNMessageResultBuilder publisher(String publisher) {
/* 131 */       this.publisher = publisher;
/* 132 */       return this;
/*     */     }
/*     */     
/*     */     public PNMessageResult build() {
/* 136 */       return new PNMessageResult(this.message, this.subscribedChannel, this.actualChannel, this.channel, this.subscription, this.timetoken, this.userMetadata, this.publisher);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 140 */       return "PNMessageResult.PNMessageResultBuilder(message=" + this.message + ", subscribedChannel=" + this.subscribedChannel + ", actualChannel=" + this.actualChannel + ", channel=" + this.channel + ", subscription=" + this.subscription + ", timetoken=" + this.timetoken + ", userMetadata=" + this.userMetadata + ", publisher=" + this.publisher + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/pubsub/PNMessageResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */