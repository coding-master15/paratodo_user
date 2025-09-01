package com.pubnub.api.services;

import com.pubnub.api.models.server.Envelope;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ChannelGroupService {
  @GET("v1/channel-registration/sub-key/{subKey}/channel-group")
  Call<Envelope<Object>> listAllChannelGroup(@Path("subKey") String paramString, @QueryMap Map<String, String> paramMap);
  
  @GET("v1/channel-registration/sub-key/{subKey}/channel-group/{group}")
  Call<Envelope<Object>> allChannelsChannelGroup(@Path("subKey") String paramString1, @Path("group") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @GET("v1/channel-registration/sub-key/{subKey}/channel-group/{group}")
  Call<Envelope> addChannelChannelGroup(@Path("subKey") String paramString1, @Path("group") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @GET("v1/channel-registration/sub-key/{subKey}/channel-group/{group}")
  Call<Envelope> removeChannel(@Path("subKey") String paramString1, @Path("group") String paramString2, @QueryMap Map<String, String> paramMap);
  
  @GET("v1/channel-registration/sub-key/{subKey}/channel-group/{group}/remove")
  Call<Envelope> deleteChannelGroup(@Path("subKey") String paramString1, @Path("group") String paramString2, @QueryMap Map<String, String> paramMap);
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/services/ChannelGroupService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */