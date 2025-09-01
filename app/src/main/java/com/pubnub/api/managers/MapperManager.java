/*     */ package com.pubnub.api.managers;
/*     */ 
/*     */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.pubnub.api.PubNubException;
import com.pubnub.api.builder.PubNubErrorBuilder;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapperManager
/*     */ {
/*     */   private Gson objectMapper;
/*     */   private Converter.Factory converterFactory;
/*     */   
/*     */   public MapperManager() {
/*  30 */     TypeAdapter<Boolean> typeAdapter = new TypeAdapter<Boolean>()
/*     */       {
/*     */         public void write(JsonWriter out, Boolean value) throws IOException {
/*  33 */           if (value == null) {
/*  34 */             out.nullValue();
/*     */           } else {
/*  36 */             out.value(value);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public Boolean read(JsonReader in) throws IOException {
/*  42 */           JsonToken jsonToken = in.peek();
/*  43 */           switch (jsonToken) {
/*     */             case BOOLEAN:
/*  45 */               return Boolean.valueOf(in.nextBoolean());
/*     */ 
/*     */ 
/*     */             
/*     */             case NUMBER:
/*  50 */               return Boolean.valueOf((in.nextInt() != 0));
/*     */             case STRING:
/*  52 */               return Boolean.valueOf(Boolean.parseBoolean(in.nextString()));
/*     */           } 
/*  54 */           throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + jsonToken);
/*     */         }
/*     */       };
/*     */ 
/*     */     
/*  59 */     this
/*     */ 
/*     */       
/*  62 */       .objectMapper = (new GsonBuilder()).registerTypeAdapter(Boolean.class, typeAdapter).registerTypeAdapter(boolean.class, typeAdapter).create();
/*  63 */     this.converterFactory = (Converter.Factory)GsonConverterFactory.create(getObjectMapper());
/*     */   }
/*     */   
/*     */   public boolean hasField(JsonElement element, String field) {
/*  67 */     return element.getAsJsonObject().has(field);
/*     */   }
/*     */   
/*     */   public JsonElement getField(JsonElement element, String field) {
/*  71 */     return element.getAsJsonObject().get(field);
/*     */   }
/*     */   
/*     */   public Iterator<JsonElement> getArrayIterator(JsonElement element) {
/*  75 */     return element.getAsJsonArray().iterator();
/*     */   }
/*     */   
/*     */   public Iterator<JsonElement> getArrayIterator(JsonElement element, String field) {
/*  79 */     return element.getAsJsonObject().get(field).getAsJsonArray().iterator();
/*     */   }
/*     */   
/*     */   public Iterator<Map.Entry<String, JsonElement>> getObjectIterator(JsonElement element) {
/*  83 */     return element.getAsJsonObject().entrySet().iterator();
/*     */   }
/*     */   
/*     */   public Iterator<Map.Entry<String, JsonElement>> getObjectIterator(JsonElement element, String field) {
/*  87 */     return element.getAsJsonObject().get(field).getAsJsonObject().entrySet().iterator();
/*     */   }
/*     */   
/*     */   public String elementToString(JsonElement element) {
/*  91 */     return element.getAsString();
/*     */   }
/*     */   
/*     */   public String elementToString(JsonElement element, String field) {
/*  95 */     return element.getAsJsonObject().get(field).getAsString();
/*     */   }
/*     */   
/*     */   public int elementToInt(JsonElement element, String field) {
/*  99 */     return element.getAsJsonObject().get(field).getAsInt();
/*     */   }
/*     */   
/*     */   public boolean isJsonObject(JsonElement element) {
/* 103 */     return element.isJsonObject();
/*     */   }
/*     */   
/*     */   public JsonObject getAsObject(JsonElement element) {
/* 107 */     return element.getAsJsonObject();
/*     */   }
/*     */   
/*     */   public boolean getAsBoolean(JsonElement element, String field) {
/* 111 */     return element.getAsJsonObject().get(field).getAsBoolean();
/*     */   }
/*     */   
/*     */   public void putOnObject(JsonObject element, String key, JsonElement value) {
/* 115 */     element.add(key, value);
/*     */   }
/*     */   
/*     */   public JsonElement getArrayElement(JsonElement element, int index) {
/* 119 */     return element.getAsJsonArray().get(index);
/*     */   }
/*     */   
/*     */   public Long elementToLong(JsonElement element) {
/* 123 */     return Long.valueOf(element.getAsLong());
/*     */   }
/*     */   
/*     */   public Long elementToLong(JsonElement element, String field) {
/* 127 */     return Long.valueOf(element.getAsJsonObject().get(field).getAsLong());
/*     */   }
/*     */   
/*     */   public JsonArray getAsArray(JsonElement element) {
/* 131 */     return element.getAsJsonArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T fromJson(String input, Class<T> clazz) throws PubNubException {
/*     */     try {
/* 137 */       return (T)this.objectMapper.fromJson(input, clazz);
/* 138 */     } catch (JsonParseException jsonParseException) {
/* 139 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).errormsg(jsonParseException.getMessage()).build();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T convertValue(JsonElement input, Class clazz) {
/* 145 */     return (T)this.objectMapper.fromJson(input, clazz);
/*     */   }
/*     */   
/*     */   public String toJson(Object input) throws PubNubException {
/*     */     try {
/* 150 */       return this.objectMapper.toJson(input);
/* 151 */     } catch (JsonParseException jsonParseException) {
/* 152 */       throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_JSON_ERROR).errormsg(jsonParseException.getMessage()).build();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Gson getObjectMapper() {
/* 158 */     return this.objectMapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public Converter.Factory getConverterFactory() {
/* 163 */     return this.converterFactory;
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/managers/MapperManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */