/*     */ package com.pubnub.api.models.consumer.pubsub;
/*     */ 
/*     */

import com.google.gson.JsonElement;

import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PNPresenceEventResult
/*     */ {
/*     */   private String event;
/*     */   private String uuid;
/*     */   private Long timestamp;
/*     */   private Integer occupancy;
/*     */   private JsonElement state;
/*     */   @Deprecated
/*     */   private String subscribedChannel;
/*     */   @Deprecated
/*     */   private String actualChannel;
/*     */   private String channel;
/*     */   private String subscription;
/*     */   private Long timetoken;
/*     */   private Object userMetadata;
/*     */   private List<String> join;
/*     */   private List<String> leave;
/*     */   private List<String> timeout;
/*     */   private Boolean hereNowRefresh;
/*     */   
/*     */   PNPresenceEventResult(String event, String uuid, Long timestamp, Integer occupancy, JsonElement state, String subscribedChannel, String actualChannel, String channel, String subscription, Long timetoken, Object userMetadata, List<String> join, List<String> leave, List<String> timeout, Boolean hereNowRefresh) {
/*  33 */     this.event = event;
/*  34 */     this.uuid = uuid;
/*  35 */     this.timestamp = timestamp;
/*  36 */     this.occupancy = occupancy;
/*  37 */     this.state = state;
/*  38 */     this.subscribedChannel = subscribedChannel;
/*  39 */     this.actualChannel = actualChannel;
/*  40 */     this.channel = channel;
/*  41 */     this.subscription = subscription;
/*  42 */     this.timetoken = timetoken;
/*  43 */     this.userMetadata = userMetadata;
/*  44 */     this.join = join;
/*  45 */     this.leave = leave;
/*  46 */     this.timeout = timeout;
/*  47 */     this.hereNowRefresh = hereNowRefresh;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PNPresenceEventResultBuilder builder() {
/*  52 */     return new PNPresenceEventResultBuilder();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEvent() {
/*  57 */     return this.event;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUuid() {
/*  62 */     return this.uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getTimestamp() {
/*  67 */     return this.timestamp;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getOccupancy() {
/*  72 */     return this.occupancy;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonElement getState() {
/*  77 */     return this.state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String getSubscribedChannel() {
/*  84 */     return this.subscribedChannel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String getActualChannel() {
/*  91 */     return this.actualChannel;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getChannel() {
/*  96 */     return this.channel;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubscription() {
/* 101 */     return this.subscription;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getTimetoken() {
/* 106 */     return this.timetoken;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getUserMetadata() {
/* 111 */     return this.userMetadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getJoin() {
/* 116 */     return this.join;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getLeave() {
/* 121 */     return this.leave;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getTimeout() {
/* 126 */     return this.timeout;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean getHereNowRefresh() {
/* 131 */     return this.hereNowRefresh;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 138 */     return "PNPresenceEventResult(event=" + getEvent() + ", uuid=" + getUuid() + ", timestamp=" + getTimestamp() + ", occupancy=" + getOccupancy() + ", state=" + getState() + ", subscribedChannel=" + getSubscribedChannel() + ", actualChannel=" + getActualChannel() + ", channel=" + getChannel() + ", subscription=" + getSubscription() + ", timetoken=" + getTimetoken() + ", userMetadata=" + getUserMetadata() + ", join=" + getJoin() + ", leave=" + getLeave() + ", timeout=" + getTimeout() + ", hereNowRefresh=" + getHereNowRefresh() + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PNPresenceEventResultBuilder
/*     */   {
/*     */     private String event;
/*     */ 
/*     */ 
/*     */     
/*     */     private String uuid;
/*     */ 
/*     */ 
/*     */     
/*     */     private Long timestamp;
/*     */ 
/*     */     
/*     */     private Integer occupancy;
/*     */ 
/*     */     
/*     */     private JsonElement state;
/*     */ 
/*     */     
/*     */     private String subscribedChannel;
/*     */ 
/*     */     
/*     */     private String actualChannel;
/*     */ 
/*     */     
/*     */     private String channel;
/*     */ 
/*     */     
/*     */     private String subscription;
/*     */ 
/*     */     
/*     */     private Long timetoken;
/*     */ 
/*     */     
/*     */     private Object userMetadata;
/*     */ 
/*     */     
/*     */     private List<String> join;
/*     */ 
/*     */     
/*     */     private List<String> leave;
/*     */ 
/*     */     
/*     */     private List<String> timeout;
/*     */ 
/*     */     
/*     */     private Boolean hereNowRefresh;
/*     */ 
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder event(String event) {
/* 195 */       this.event = event;
/* 196 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder uuid(String uuid) {
/* 201 */       this.uuid = uuid;
/* 202 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder timestamp(Long timestamp) {
/* 207 */       this.timestamp = timestamp;
/* 208 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder occupancy(Integer occupancy) {
/* 213 */       this.occupancy = occupancy;
/* 214 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder state(JsonElement state) {
/* 219 */       this.state = state;
/* 220 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public PNPresenceEventResultBuilder subscribedChannel(String subscribedChannel) {
/* 227 */       this.subscribedChannel = subscribedChannel;
/* 228 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public PNPresenceEventResultBuilder actualChannel(String actualChannel) {
/* 235 */       this.actualChannel = actualChannel;
/* 236 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder channel(String channel) {
/* 241 */       this.channel = channel;
/* 242 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder subscription(String subscription) {
/* 247 */       this.subscription = subscription;
/* 248 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder timetoken(Long timetoken) {
/* 253 */       this.timetoken = timetoken;
/* 254 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder userMetadata(Object userMetadata) {
/* 259 */       this.userMetadata = userMetadata;
/* 260 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder join(List<String> join) {
/* 265 */       this.join = join;
/* 266 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder leave(List<String> leave) {
/* 271 */       this.leave = leave;
/* 272 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder timeout(List<String> timeout) {
/* 277 */       this.timeout = timeout;
/* 278 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResultBuilder hereNowRefresh(Boolean hereNowRefresh) {
/* 283 */       this.hereNowRefresh = hereNowRefresh;
/* 284 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNPresenceEventResult build() {
/* 289 */       return new PNPresenceEventResult(this.event, this.uuid, this.timestamp, this.occupancy, this.state, this.subscribedChannel, this.actualChannel, this.channel, this.subscription, this.timetoken, this.userMetadata, this.join, this.leave, this.timeout, this.hereNowRefresh);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 296 */       return "PNPresenceEventResult.PNPresenceEventResultBuilder(event=" + this.event + ", uuid=" + this.uuid + ", timestamp=" + this.timestamp + ", occupancy=" + this.occupancy + ", state=" + this.state + ", subscribedChannel=" + this.subscribedChannel + ", actualChannel=" + this.actualChannel + ", channel=" + this.channel + ", subscription=" + this.subscription + ", timetoken=" + this.timetoken + ", userMetadata=" + this.userMetadata + ", join=" + this.join + ", leave=" + this.leave + ", timeout=" + this.timeout + ", hereNowRefresh=" + this.hereNowRefresh + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/pubsub/PNPresenceEventResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */