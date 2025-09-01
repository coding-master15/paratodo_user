/*     */ package com.pubnub.api.endpoints.presence;
/*     */ 
/*     */

import com.google.gson.JsonElement;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.MapperManager;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;
import com.pubnub.api.models.consumer.presence.PNHereNowOccupantData;
import com.pubnub.api.models.consumer.presence.PNHereNowResult;
import com.pubnub.api.models.server.Envelope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HereNow
/*     */   extends Endpoint<Envelope<JsonElement>, PNHereNowResult>
/*     */ {
/*     */   private List<String> channels;
/*     */   private List<String> channelGroups;
/*     */   private Boolean includeState;
/*     */   private Boolean includeUUIDs;
/*     */   
/*     */   public HereNow(PubNub pubnubInstance, TelemetryManager telemetryManager, RetrofitManager retrofit) {
/*  40 */     super(pubnubInstance, telemetryManager, retrofit);
/*  41 */     this.channels = new ArrayList<>();
/*  42 */     this.channelGroups = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannels() {
/*  47 */     return this.channels;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<String> getAffectedChannelGroups() {
/*  52 */     return this.channelGroups;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void validateParams() throws PubNubException {
/*  58 */     if (getPubnub().getConfiguration().getSubscribeKey() == null || getPubnub().getConfiguration().getSubscribeKey().isEmpty()) {
/*  59 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_SUBSCRIBE_KEY_MISSING).build();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected Call<Envelope<JsonElement>> doWork(Map<String, String> params) {
/*     */     String str;
/*  66 */     if (this.includeState == null) {
/*  67 */       this.includeState = Boolean.valueOf(false);
/*     */     }
/*     */     
/*  70 */     if (this.includeUUIDs == null) {
/*  71 */       this.includeUUIDs = Boolean.valueOf(true);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  76 */     if (this.includeState.booleanValue()) {
/*  77 */       params.put("state", "1");
/*     */     }
/*  79 */     if (!this.includeUUIDs.booleanValue()) {
/*  80 */       params.put("disable_uuids", "1");
/*     */     }
/*  82 */     if (this.channelGroups.size() > 0) {
/*  83 */       params.put("channel-group", PubNubUtil.joinString(this.channelGroups, ","));
/*     */     }
/*     */     
/*  86 */     if (this.channels.size() > 0) {
/*  87 */       str = PubNubUtil.joinString(this.channels, ",");
/*     */     } else {
/*  89 */       str = ",";
/*     */     } 
/*     */     
/*  92 */     if (this.channels.size() > 0 || this.channelGroups.size() > 0) {
/*  93 */       return getRetrofit().getPresenceService().hereNow(getPubnub().getConfiguration().getSubscribeKey(), str, params);
/*     */     }
/*  95 */     return getRetrofit().getPresenceService().globalHereNow(getPubnub().getConfiguration().getSubscribeKey(), params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PNHereNowResult createResponse(Response<Envelope<JsonElement>> input) {
/*     */     PNHereNowResult pNHereNowResult;
/* 103 */     if (this.channels.size() > 1 || this.channelGroups.size() > 0) {
/* 104 */       pNHereNowResult = parseMultipleChannelResponse((JsonElement)((Envelope)input.body()).getPayload());
/*     */     } else {
/* 106 */       pNHereNowResult = parseSingleChannelResponse((Envelope<JsonElement>)input.body());
/*     */     } 
/*     */     
/* 109 */     return pNHereNowResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PNHereNowResult parseSingleChannelResponse(Envelope<JsonElement> input) {
/* 117 */     PNHereNowResult pNHereNowResult = PNHereNowResult.builder().totalChannels(1).channels(new HashMap<>()).totalOccupancy(input.getOccupancy()).build();
/*     */ 
/*     */ 
/*     */     
/* 121 */     PNHereNowChannelData.PNHereNowChannelDataBuilder pNHereNowChannelDataBuilder = PNHereNowChannelData.builder().channelName(this.channels.get(0)).occupancy(input.getOccupancy());
/*     */     
/* 123 */     if (this.includeUUIDs.booleanValue()) {
/* 124 */       pNHereNowChannelDataBuilder.occupants(prepareOccupantData(input.getUuids()));
/* 125 */       pNHereNowResult.getChannels().put(this.channels.get(0), pNHereNowChannelDataBuilder.build());
/*     */     } 
/*     */     
/* 128 */     return pNHereNowResult;
/*     */   }
/*     */   
/*     */   private PNHereNowResult parseMultipleChannelResponse(JsonElement input) {
/* 132 */     MapperManager mapperManager = getPubnub().getMapper();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     PNHereNowResult pNHereNowResult = PNHereNowResult.builder().channels(new HashMap<>()).totalChannels(mapperManager.elementToInt(input, "total_channels")).totalOccupancy(mapperManager.elementToInt(input, "total_occupancy")).build();
/*     */     
/* 139 */     for (Iterator<Map.Entry<String, JsonElement>> iterator = mapperManager.getObjectIterator(input, "channels"); iterator.hasNext(); ) {
/* 140 */       Map.Entry<String, JsonElement> entry = iterator.next();
/*     */ 
/*     */ 
/*     */       
/* 144 */       PNHereNowChannelData.PNHereNowChannelDataBuilder pNHereNowChannelDataBuilder = PNHereNowChannelData.builder().channelName((String)entry.getKey()).occupancy(mapperManager.elementToInt((JsonElement)entry.getValue(), "occupancy"));
/*     */       
/* 146 */       if (this.includeUUIDs.booleanValue()) {
/* 147 */         pNHereNowChannelDataBuilder.occupants(prepareOccupantData(mapperManager.getField((JsonElement)entry.getValue(), "uuids")));
/*     */       } else {
/* 149 */         pNHereNowChannelDataBuilder.occupants(null);
/*     */       } 
/*     */       
/* 152 */       pNHereNowResult.getChannels().put(entry.getKey(), pNHereNowChannelDataBuilder.build());
/*     */     } 
/*     */     
/* 155 */     return pNHereNowResult;
/*     */   }
/*     */   
/*     */   private List<PNHereNowOccupantData> prepareOccupantData(JsonElement input) {
/* 159 */     ArrayList<PNHereNowOccupantData> arrayList = new ArrayList();
/* 160 */     MapperManager mapperManager = getPubnub().getMapper();
/*     */     
/* 162 */     if (this.includeState != null && this.includeState.booleanValue()) {
/* 163 */       for (Iterator<JsonElement> iterator = mapperManager.getArrayIterator(input); iterator.hasNext(); ) {
/* 164 */         JsonElement jsonElement = iterator.next();
/* 165 */         PNHereNowOccupantData.PNHereNowOccupantDataBuilder pNHereNowOccupantDataBuilder = PNHereNowOccupantData.builder();
/* 166 */         pNHereNowOccupantDataBuilder.uuid(mapperManager.elementToString(jsonElement, "uuid"));
/* 167 */         pNHereNowOccupantDataBuilder.state(mapperManager.getField(jsonElement, "state"));
/*     */         
/* 169 */         arrayList.add(pNHereNowOccupantDataBuilder.build());
/*     */       } 
/*     */     } else {
/* 172 */       for (Iterator<JsonElement> iterator = mapperManager.getArrayIterator(input); iterator.hasNext(); ) {
/* 173 */         JsonElement jsonElement = iterator.next();
/* 174 */         PNHereNowOccupantData.PNHereNowOccupantDataBuilder pNHereNowOccupantDataBuilder = PNHereNowOccupantData.builder();
/* 175 */         pNHereNowOccupantDataBuilder.uuid(mapperManager.elementToString(jsonElement));
/* 176 */         pNHereNowOccupantDataBuilder.state(null);
/*     */         
/* 178 */         arrayList.add(pNHereNowOccupantDataBuilder.build());
/*     */       } 
/*     */     } 
/*     */     
/* 182 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PNOperationType getOperationType() {
/* 187 */     return PNOperationType.PNHereNowOperation;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAuthRequired() {
/* 192 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/endpoints/presence/HereNow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */