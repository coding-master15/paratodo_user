package com.general.files;

import android.content.Context;
import android.os.AsyncTask;

import com.InfoProvider;

public class GetDeviceToken extends AsyncTask<String, String, String> {
    SetTokenResponse setTokenResponse;
    GeneralFunctions generalFunctions;
    String c = "";

    public GetDeviceToken(GeneralFunctions generalFunc) {
        this.generalFunctions = generalFunc;
        a(generalFunc.a);
    }

    protected String doInBackground(String... strings) {
        this.c = this.generalFunctions.generateDeviceToken();
        return null;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (this.setTokenResponse != null) {
            this.setTokenResponse.onTokenFound(this.c);
        }

    }

    public void setDataResponseListener(SetTokenResponse setTokenRes) {
        this.setTokenResponse = setTokenRes;
    }

    private static void a(Context var0) {
        new InfoProvider(var0);
    }

    public interface SetTokenResponse {
        void onTokenFound(String var1);
    }
}
