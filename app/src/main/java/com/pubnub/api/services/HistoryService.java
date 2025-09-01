package com.pubnub.api.services;

import com.google.gson.JsonElement;
import com.pubnub.api.models.server.DeleteMessagesEnvelope;
import com.pubnub.api.models.server.FetchMessagesEnvelope;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface HistoryService {
  @GET("v2/history/sub-key/{subKey}/channel/{channel}")
  Call<JsonElement> fetchHistory(@Path("subKey") String paramString1, @Path("channel") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @DELETE("v3/history/sub-key/{subKey}/channel/{channels}")
  Call<DeleteMessagesEnvelope> deleteMessages(@Path("subKey") String paramString1, @Path("channels") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @GET("v3/history/sub-key/{subKey}/channel/{channels}")
  Call<FetchMessagesEnvelope> fetchMessages(@Path("subKey") String paramString1, @Path("channels") String paramString2, @QueryMap Map<String, String> paramMap);
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/services/HistoryService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */