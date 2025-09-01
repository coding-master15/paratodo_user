package com.pubnub.api.services;

import com.pubnub.api.models.server.SubscribeEnvelope;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface SubscribeService {
  @GET("v2/subscribe/{subKey}/{channel}/0")
  Call<SubscribeEnvelope> subscribe(@Path("subKey") String paramString1, @Path("channel") String paramString2, @QueryMap(encoded = true) Map<String, String> paramMap);
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/services/SubscribeService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */