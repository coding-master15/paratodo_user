package com.pubnub.api.callbacks;

import com.pubnub.api.models.consumer.PNStatus;

public abstract class PNCallback<X> {
  public abstract void onResponse(X paramX, PNStatus paramPNStatus);
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/callbacks/PNCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */