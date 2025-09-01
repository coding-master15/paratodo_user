package com.paratodo.user;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.general.files.FireTripStatusMsg;
import com.general.files.GeneralFunctions;
import com.general.files.MyApp;
import com.general.files.SinchService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sinch.android.rtc.SinchPush;
import com.sinch.android.rtc.calling.CallNotificationResult;
import com.utils.Logger;
import com.utils.Utils;

import java.io.IOException;
import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final String TAG1 = "MyFirebaseIIDService";


    String authorizedEntity; // Project id from Google Developer Console
    String scope = "GCM"; // e.g. communicating using GCM, but you can use any
    // URL-safe characters up to a maximum of 1000, or
    // you can also leave it blank.

    @Override
    public void onNewToken(String s) {
        // depricated
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        if (!Utils.checkText(authorizedEntity)) {
            authorizedEntity = MyApp.getInstance().getGeneralFun(this).retrieveValue(Utils.APP_GCM_SENDER_ID_KEY);
        }
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fb", s).apply();
        sendRegistrationToServer(s);
        super.onNewToken(s);
    }


    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> data = remoteMessage.getData();
        Logger.d("RemoteMessage", "::" + data.toString());
        if (SinchPush.isSinchPushPayload(remoteMessage.getData())) {

            GeneralFunctions generalFunctions = MyApp.getInstance().getGeneralFun(getApplicationContext());
            if (generalFunctions.getMemberId().equals("")) {
                return;
            }


            new ServiceConnection() {
                private CallNotificationResult payload;

                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    if (payload != null) {
                        SinchService.SinchServiceInterface sinchService = (SinchService.SinchServiceInterface) service;
                        if (sinchService != null) {
                            try {
                                sinchService.relayRemotePushNotificationPayload(payload);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    payload = null;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                }

                public void relayMessageData(Map<String, String> data, Context context) {
                    payload = SinchPush.queryPushNotificationPayload(context, data);
                    getApplicationContext().bindService(new Intent(getApplicationContext(), SinchService.class), this, BIND_AUTO_CREATE);
                }
            }.relayMessageData(data, getApplicationContext());
            return;

        }


        if (!Utils.checkText(authorizedEntity)) {
            authorizedEntity = MyApp.getInstance().getGeneralFun(this).retrieveValue(Utils.APP_GCM_SENDER_ID_KEY);
        }

        if (remoteMessage.getData().isEmpty()/* || remoteMessage.getNotification().getBody() == null*/) {
            return;
        }


        String message = remoteMessage.getData().get("message");

        try {
            PowerManager powerManager = (PowerManager) MyApp.getInstance().getCurrentAct().getSystemService(Context.POWER_SERVICE);
            boolean isScreenOn = powerManager.isScreenOn();
            if (isScreenOn) {
                new FireTripStatusMsg(MyApp.getInstance() != null ? MyApp.getInstance().getCurrentAct() : getApplicationContext()).fireTripMsg(message);
            } else {
                new FireTripStatusMsg(MyApp.getInstance() != null ? MyApp.getInstance().getCurrentAct() : getApplicationContext()).fireTripMsg(message);
            }
        } catch (Exception e) {
            new FireTripStatusMsg(MyApp.getInstance() != null ? MyApp.getInstance().getCurrentAct() : getApplicationContext()).fireTripMsg(message);
        }

    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }

}
