package com.general.files;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.paratodo.user.IncomingCallScreenActivity;
import com.paratodo.user.MainActivity;
import com.paratodo.user.MyFirebaseMessagingService;
import com.sinch.android.rtc.AudioController;
import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.PushConfiguration;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallController;
import com.sinch.android.rtc.calling.CallControllerListener;
import com.sinch.android.rtc.calling.CallNotificationResult;
import com.sinch.android.rtc.calling.MediaConstraints;
import com.utils.Logger;
import com.utils.Utils;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

public class SinchService extends Service {


    public static final String CALL_ID = "CALL_ID";
    public static final String CLI = "+12032983591";
    public static final String APP_FCM_SENDER_ID = "828087557039";

    //global
    String SINCH_APP_KEY="cd0f8618-a099-40e1-9f71-3506d80d0003";
    String SINCH_APP_SECRET="nbRxjOEiAQkOXAvkLqEbOtiQob";
    //global

    String SINCH_APP_KEY_2 ="6705a619-c193-4673-9d24-091a39272a3e";
    String SINCH_APP_SECRET_2 ="xajskX+nG0iz5P9m8FfBJQ==";
    static final String TAG = SinchService.class.getSimpleName();

    private final SinchServiceInterface mSinchServiceInterface = new SinchServiceInterface();
    private SinchClient mSinchClient;
    private String mUserId;

    private StartFailedListener mListener;

    private PersistedSettings mSettings;

    @Override
    public void onCreate() {
        super.onCreate();
        mSettings = new PersistedSettings(getApplicationContext());
    }

    @Override
    public void onDestroy() {
        if (mSinchClient != null && mSinchClient.isStarted()) {
            mSinchClient.terminateGracefully();

        }
        super.onDestroy();
    }

    private void start(String userName) {
        GeneralFunctions generalFunctions = MyApp.getInstance().getGeneralFun(getApplicationContext());
        if (mSinchClient == null) {
            mUserId = userName;
            mSettings.setUsername(userName);
            //String SINCH_APP_KEY=generalFunctions.retrieveValue(Utils.SINCH_APP_KEY);

            if (SINCH_APP_KEY == null || SINCH_APP_KEY.equalsIgnoreCase("")) {
                return;
            }
            Logger.d("SinchKey","::"+generalFunctions.retrieveValue(Utils.SINCH_APP_KEY));
            try {
                mSinchClient = SinchClient.builder().context(getApplicationContext()).userId(userName)
                        .applicationKey(SINCH_APP_KEY_2)
                        .userId(userName)
                        .environmentHost(/*generalFunctions.retrieveValue(Utils.SINCH_APP_ENVIRONMENT_HOST)*/"ocra.api.sinch.com")
                        .pushNotificationDisplayName(new GeneralFunctions(getApplicationContext()).retrieveLangLBl("", "LBL_INCOMING_CALL"))
                        .pushConfiguration(
                                PushConfiguration.fcmPushConfigurationBuilder()
                                        .senderID(APP_FCM_SENDER_ID)
                                        .registrationToken(MyFirebaseMessagingService.getToken(this)).build()
                        )
                        .build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            mSinchClient.getCallController().setRespectNativeCalls(false);

            mSinchClient.addSinchClientListener(new MySinchClientListener());
            mSinchClient.getCallController().addCallControllerListener(new SinchCallClientListener());
            mSinchClient.start();

        }
    }


    private void stop() {
        if (mSinchClient != null) {
            mSinchClient.terminateGracefully();
            mSinchClient = null;
        }
    }


    private void createClient(String username) {
        GeneralFunctions generalFunctions = MyApp.getInstance().getGeneralFun(getApplicationContext());
        try {
            mSinchClient = SinchClient.builder().context(getApplicationContext()).userId(username)
                    .applicationKey(SINCH_APP_KEY_2)
                    .environmentHost("ocra.api.sinch.com").build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mSinchClient.checkManifest();

        mSinchClient.addSinchClientListener(new MySinchClientListener());
        mSinchClient.getCallController().addCallControllerListener(new SinchCallClientListener());
    }

    private boolean isStarted() {
        return (mSinchClient != null && mSinchClient.isStarted());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mSinchServiceInterface;
    }

    public class SinchServiceInterface extends Binder {

        public Call callPhoneNumber(String phoneNumber) {
            return mSinchClient.getCallController().callPhoneNumber(phoneNumber, CLI);
        }

        public Call callUser(String userId) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("vName", "Test");
            //mSinchClient.start();
            return mSinchClient.getCallController().callUser(userId, new MediaConstraints(false), headers);
        }

        public Call callUser(String userId, Map<String, String> headers) {
            //mSinchClient.start();
            return mSinchClient.getCallController().callUser(userId, new MediaConstraints(false), headers);
        }

        public String getUserName() {
            return mUserId;
        }

        public boolean isStarted() {
            return SinchService.this.isStarted();
        }

        public void startClient(String userName) {
            start(userName);
        }

        public void stopClient() {
            stop();
        }

        public void setStartListener(StartFailedListener listener) {
            mListener = listener;
        }

        public Call getCall(String callId) {
            return mSinchClient.getCallController().getCall(callId);
        }



        public SinchClient getSinchClient() {
            return mSinchClient;
        }

        public void relayRemotePushNotificationPayload(final CallNotificationResult payload) throws IOException {
            if (mSinchClient == null && !mSettings.getUsername().isEmpty()) {
                createClient(mSettings.getUsername());
            } else if (mSinchClient == null && mSettings.getUsername().isEmpty()) {
                Log.e(TAG, "Can't start a SinchClient as no username is available, unable to relay push.");
            }
            mSinchClient.relayRemotePushNotification(payload);
        }

    }

    public interface StartFailedListener {

        void onStartFailed(SinchError error);

        void onStarted();
    }

    private String generateSafeToken() {

        return UUID.randomUUID().toString();
    }

    private class MySinchClientListener implements SinchClientListener {

        @Override
        public void onClientFailed(SinchClient client, SinchError error) {
            Logger.e("SSS", error.getMessage());
            if (mListener != null) {
                mListener.onStartFailed(error);
            }
            mSinchClient.terminateGracefully();
            mSinchClient = null;
        }

        @Override
        public void onClientStarted(SinchClient client) {
            Log.d(TAG, "SinchClient started");
            if (mListener != null) {
                mListener.onStarted();
            }
        }
        @Override
        public void onLogMessage(int level, String area, String message) {
            switch (level) {
                case Log.DEBUG:
                    Log.d(area, message);
                    break;
                case Log.ERROR:
                    Log.e(area, message);
                    break;
                case Log.INFO:
                    Log.i(area, message);
                    break;
                case Log.VERBOSE:
                    Log.v(area, message);
                    break;
                case Log.WARN:
                    Log.w(area, message);
                    break;
            }
        }

        @Override
        public void onPushTokenRegistered() {

        }

        @Override
        public void onPushTokenRegistrationFailed(@NonNull SinchError sinchError) {
            Log.e("FAILEDA", sinchError.getMessage());
        }

        @Override
        public void onPushTokenUnregistered() {

        }

        @Override
        public void onPushTokenUnregistrationFailed(@NonNull SinchError sinchError) {

        }

        @Override
        public void onCredentialsRequired(@NonNull ClientRegistration clientRegistration) {

            HashMap<String, String> parameters = new HashMap<String, String>();
            parameters.put("type", "getSinchToken");
            parameters.put("userId", mUserId);

            ExecuteWebServerUrl exeWebServer = new ExecuteWebServerUrl(getApplicationContext(), parameters);
            exeWebServer.setLoaderConfig(getApplicationContext(), true, MyApp.getInstance().getGeneralFun(getApplicationContext()));

            exeWebServer.setDataResponseListener(responseString -> {
                Logger.e("ttt", responseString);
                if (responseString != null && !responseString.equals("")) {

                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                    Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                    cal2.add(Calendar.SECOND, 600);
                    UserRegistrationToken signingKey = new UserRegistrationToken(
                            SINCH_APP_KEY_2,
                            SINCH_APP_SECRET_2,
                            mUserId,
                            generateSafeToken(),
                            cal.getTime(),
                            cal2.getTime()
                    );

                    String token = MyApp.getInstance().getGeneralFun(getApplicationContext()).getJsonValue("token", responseString);
                    Logger.e("ttt", signingKey.toJwt());
                    clientRegistration.register(signingKey.toJwt());
                } else {
                    clientRegistration.registerFailed();
                }
            });
            exeWebServer.execute();
        }

        @Override
        public void onUserRegistered() {

        }

        @Override
        public void onUserRegistrationFailed(@NonNull SinchError sinchError) {
            Log.e("FAILEDA", sinchError.getMessage());
            Log.e("FAILEDA", String.valueOf(sinchError.getCode()));
            Log.e("FAILEDA", sinchError.getExtras().toString());
        }
    }

    private class SinchCallClientListener implements CallControllerListener {

        @Override
        public void onIncomingCall(@NonNull CallController callController, @NonNull Call call) {
            Intent intent = new Intent(SinchService.this, IncomingCallScreenActivity.class);
            intent.putExtra(CALL_ID, call.getCallId());
            intent.putExtra("Name", call.getHeaders().get("Name"));
            intent.putExtra("PImage", call.getHeaders().get("PImage"));
            intent.putExtra("Id", call.getHeaders().get("Id"));
            intent.putExtra("type", call.getHeaders().get("type"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            SinchService.this.startActivity(intent);
        }
    }

    private class PersistedSettings {

        private SharedPreferences mStore;

        private static final String PREF_KEY = "Sinch";

        public PersistedSettings(Context context) {
            mStore = context.getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        }

        public String getUsername() {
            return mStore.getString("Username", "");
        }

        public void setUsername(String username) {
            SharedPreferences.Editor editor = mStore.edit();
            editor.putString("Username", username);
            editor.commit();
        }
    }


}