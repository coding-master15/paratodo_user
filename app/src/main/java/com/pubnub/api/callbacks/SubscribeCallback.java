package com.pubnub.api.callbacks;

import com.pubnub.api.PubNub;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

public abstract class SubscribeCallback {
  public abstract void status(PubNub paramPubNub, PNStatus paramPNStatus);
  
  public abstract void message(PubNub paramPubNub, PNMessageResult paramPNMessageResult);
  
  public abstract void presence(PubNub paramPubNub, PNPresenceEventResult paramPNPresenceEventResult);
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/callbacks/SubscribeCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */