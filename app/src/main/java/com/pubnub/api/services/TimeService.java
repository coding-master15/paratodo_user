package com.pubnub.api.services;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface TimeService {
  @GET("/time/0")
  Call<List<Long>> fetchTime(@QueryMap Map<String, String> paramMap);
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/services/TimeService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */