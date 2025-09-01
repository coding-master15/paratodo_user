package com.pubnub.api.services;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface PushService {
  @GET("v1/push/sub-key/{subKey}/devices/{pushToken}")
  Call<List<Object>> modifyChannelsForDevice(@Path("subKey") String paramString1, @Path("pushToken") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @GET("v1/push/sub-key/{subKey}/devices/{pushToken}/remove")
  Call<List<Object>> removeAllChannelsForDevice(@Path("subKey") String paramString1, @Path("pushToken") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @GET("v1/push/sub-key/{subKey}/devices/{pushToken}")
  Call<List<String>> listChannelsForDevice(@Path("subKey") String paramString1, @Path("pushToken") String paramString2, @QueryMap Map<String, String> paramMap);
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/services/PushService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */