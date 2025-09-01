/*     */ package com.pubnub.api.models.consumer;
/*     */ 
/*     */

import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.enums.PNStatusCategory;

import java.util.List;
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
/*     */ public class PNStatus
/*     */ {
/*     */   private PNStatusCategory category;
/*     */   private PNErrorData errorData;
/*     */   private boolean error;
/*     */   private int statusCode;
/*     */   private PNOperationType operation;
/*     */   private boolean tlsEnabled;
/*     */   private String uuid;
/*     */   private String authKey;
/*     */   private String origin;
/*     */   private Object clientRequest;
/*     */   private List<String> affectedChannels;
/*     */   private List<String> affectedChannelGroups;
/*     */   private Endpoint executedEndpoint;
/*     */   
/*     */   PNStatus(PNStatusCategory category, PNErrorData errorData, boolean error, int statusCode, PNOperationType operation, boolean tlsEnabled, String uuid, String authKey, String origin, Object clientRequest, List<String> affectedChannels, List<String> affectedChannelGroups, Endpoint executedEndpoint) {
/*  40 */     this.category = category;
/*  41 */     this.errorData = errorData;
/*  42 */     this.error = error;
/*  43 */     this.statusCode = statusCode;
/*  44 */     this.operation = operation;
/*  45 */     this.tlsEnabled = tlsEnabled;
/*  46 */     this.uuid = uuid;
/*  47 */     this.authKey = authKey;
/*  48 */     this.origin = origin;
/*  49 */     this.clientRequest = clientRequest;
/*  50 */     this.affectedChannels = affectedChannels;
/*  51 */     this.affectedChannelGroups = affectedChannelGroups;
/*  52 */     this.executedEndpoint = executedEndpoint;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PNStatusBuilder builder() {
/*  57 */     return new PNStatusBuilder();
/*     */   }
/*     */   
/*     */   public void retry() {
/*  61 */     this.executedEndpoint.retry();
/*     */   }
/*     */ 
/*     */   
/*     */   public PNStatusCategory getCategory() {
/*  66 */     return this.category;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNErrorData getErrorData() {
/*  71 */     return this.errorData;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isError() {
/*  76 */     return this.error;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStatusCode() {
/*  81 */     return this.statusCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public PNOperationType getOperation() {
/*  86 */     return this.operation;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTlsEnabled() {
/*  91 */     return this.tlsEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUuid() {
/*  96 */     return this.uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAuthKey() {
/* 101 */     return this.authKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOrigin() {
/* 106 */     return this.origin;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClientRequest() {
/* 111 */     return this.clientRequest;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getAffectedChannels() {
/* 116 */     return this.affectedChannels;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getAffectedChannelGroups() {
/* 121 */     return this.affectedChannelGroups;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 128 */     return "PNStatus(category=" + getCategory() + ", errorData=" + getErrorData() + ", error=" + isError() + ", statusCode=" + getStatusCode() + ", operation=" + getOperation() + ", tlsEnabled=" + isTlsEnabled() + ", uuid=" + getUuid() + ", authKey=" + getAuthKey() + ", origin=" + getOrigin() + ", clientRequest=" + getClientRequest() + ", affectedChannels=" + getAffectedChannels() + ", affectedChannelGroups=" + getAffectedChannelGroups() + ", executedEndpoint=" + this.executedEndpoint + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PNStatusBuilder
/*     */   {
/*     */     private PNStatusCategory category;
/*     */ 
/*     */ 
/*     */     
/*     */     private PNErrorData errorData;
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean error;
/*     */ 
/*     */     
/*     */     private int statusCode;
/*     */ 
/*     */     
/*     */     private PNOperationType operation;
/*     */ 
/*     */     
/*     */     private boolean tlsEnabled;
/*     */ 
/*     */     
/*     */     private String uuid;
/*     */ 
/*     */     
/*     */     private String authKey;
/*     */ 
/*     */     
/*     */     private String origin;
/*     */ 
/*     */     
/*     */     private Object clientRequest;
/*     */ 
/*     */     
/*     */     private List<String> affectedChannels;
/*     */ 
/*     */     
/*     */     private List<String> affectedChannelGroups;
/*     */ 
/*     */     
/*     */     private Endpoint executedEndpoint;
/*     */ 
/*     */ 
/*     */     
/*     */     public PNStatusBuilder category(PNStatusCategory category) {
/* 179 */       this.category = category;
/* 180 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder errorData(PNErrorData errorData) {
/* 185 */       this.errorData = errorData;
/* 186 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder error(boolean error) {
/* 191 */       this.error = error;
/* 192 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder statusCode(int statusCode) {
/* 197 */       this.statusCode = statusCode;
/* 198 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder operation(PNOperationType operation) {
/* 203 */       this.operation = operation;
/* 204 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder tlsEnabled(boolean tlsEnabled) {
/* 209 */       this.tlsEnabled = tlsEnabled;
/* 210 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder uuid(String uuid) {
/* 215 */       this.uuid = uuid;
/* 216 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder authKey(String authKey) {
/* 221 */       this.authKey = authKey;
/* 222 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder origin(String origin) {
/* 227 */       this.origin = origin;
/* 228 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder clientRequest(Object clientRequest) {
/* 233 */       this.clientRequest = clientRequest;
/* 234 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder affectedChannels(List<String> affectedChannels) {
/* 239 */       this.affectedChannels = affectedChannels;
/* 240 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder affectedChannelGroups(List<String> affectedChannelGroups) {
/* 245 */       this.affectedChannelGroups = affectedChannelGroups;
/* 246 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatusBuilder executedEndpoint(Endpoint executedEndpoint) {
/* 251 */       this.executedEndpoint = executedEndpoint;
/* 252 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public PNStatus build() {
/* 257 */       return new PNStatus(this.category, this.errorData, this.error, this.statusCode, this.operation, this.tlsEnabled, this.uuid, this.authKey, this.origin, this.clientRequest, this.affectedChannels, this.affectedChannelGroups, this.executedEndpoint);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 264 */       return "PNStatus.PNStatusBuilder(category=" + this.category + ", errorData=" + this.errorData + ", error=" + this.error + ", statusCode=" + this.statusCode + ", operation=" + this.operation + ", tlsEnabled=" + this.tlsEnabled + ", uuid=" + this.uuid + ", authKey=" + this.authKey + ", origin=" + this.origin + ", clientRequest=" + this.clientRequest + ", affectedChannels=" + this.affectedChannels + ", affectedChannelGroups=" + this.affectedChannelGroups + ", executedEndpoint=" + this.executedEndpoint + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/models/consumer/PNStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */