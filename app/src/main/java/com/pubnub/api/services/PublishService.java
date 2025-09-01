package com.pubnub.api.services;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface PublishService {
  @GET("publish/{pubKey}/{subKey}/0/{channel}/0/{message}")
  Call<List<Object>> publish(@Path("pubKey") String paramString1, @Path("subKey") String paramString2, @Path("channel") String paramString3, @Path(value = "message", encoded = true) String paramString4, @QueryMap(encoded = true) Map<String, String> paramMap);
  
  @POST("publish/{pubKey}/{subKey}/0/{channel}/0")
  @Headers({"Content-Type: application/json; charset=UTF-8"})
  Call<List<Object>> publishWithPost(@Path("pubKey") String paramString1, @Path("subKey") String paramString2, @Path("channel") String paramString3, @Body Object paramObject, @QueryMap(encoded = true) Map<String, String> paramMap);
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/services/PublishService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */