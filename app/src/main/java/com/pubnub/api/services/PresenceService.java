package com.pubnub.api.services;

import com.google.gson.JsonElement;
import com.pubnub.api.models.server.Envelope;
import com.pubnub.api.models.server.presence.WhereNowPayload;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface PresenceService {
  @GET("v2/presence/sub-key/{subKey}/channel/{channel}/leave")
  Call<Envelope> leave(@Path("subKey") String paramString1, @Path("channel") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @GET("v2/presence/sub-key/{subKey}/channel/{channel}/heartbeat")
  Call<Envelope> heartbeat(@Path("subKey") String paramString1, @Path("channel") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @GET("v2/presence/sub-key/{subKey}/uuid/{uuid}")
  Call<Envelope<WhereNowPayload>> whereNow(@Path("subKey") String paramString1, @Path("uuid") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @GET("v2/presence/sub_key/{subKey}")
  Call<Envelope<JsonElement>> globalHereNow(@Path("subKey") String paramString, @QueryMap Map<String, String> paramMap);
  
  @GET("v2/presence/sub_key/{subKey}/channel/{channel}")
  Call<Envelope<JsonElement>> hereNow(@Path("subKey") String paramString1, @Path("channel") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @GET("v2/presence/sub-key/{subKey}/channel/{channel}/uuid/{uuid}")
  Call<Envelope<JsonElement>> getState(@Path("subKey") String paramString1, @Path("channel") String paramString2, @Path("uuid") String paramString3, @QueryMap Map<String, String> paramMap);
  
  @GET("v2/presence/sub-key/{subKey}/channel/{channel}/uuid/{uuid}/data")
  Call<Envelope<JsonElement>> setState(@Path("subKey") String paramString1, @Path("channel") String paramString2, @Path("uuid") String paramString3, @QueryMap(encoded = true) Map<String, String> paramMap);
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/services/PresenceService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */