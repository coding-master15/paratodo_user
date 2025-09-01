package com.paratodo.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.balysv.materialripple.MaterialRippleLayout;
import com.dialogs.OpenListView;
import com.fragments.MyWalletFragment;
import com.frogobox.loadingindicator.FrogoLoadingIndicatorView;
import com.general.files.AppFunctions;
import com.general.files.ExecuteWebServerUrl;
import com.general.files.GeneralFunctions;
import com.general.files.MyApp;
import com.google.android.material.textfield.TextInputEditText;
import com.utils.CommonUtilities;
import com.utils.Logger;
import com.utils.Utils;
import com.view.MButton;
import com.view.MTextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

public class CyberSourceAddCardActivity extends AppCompatActivity implements TextWatcher {

    MTextView titleTxt;
    GeneralFunctions generalFunc;
    ImageView backImgView;
    FrogoLoadingIndicatorView loaderView;
    WebView paymentWebview;
    String required_str = "";
    String userProfileJson = "";
    String rechargeBox = "";
    String onlyCVV = "false";
    String NewCard = "false";
    String token = "";
    String cardTokenAvailable = "";
    ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_source_add_card);
        titleTxt = findViewById(R.id.titleTxt);
        backImgView = (ImageView) findViewById(R.id.backImgView);

        paymentWebview = (WebView) findViewById(R.id.paymentWebview);
        loaderView = (FrogoLoadingIndicatorView) findViewById(R.id.loaderView);
        loadingBar = findViewById(R.id.loadingBar);
        rechargeBox = getIntent().getStringExtra("Amount");
        onlyCVV = getIntent().getStringExtra("OnlyCVV");
        NewCard = getIntent().getStringExtra("NewCard");
        token = getIntent().getStringExtra("token");
        generalFunc = MyApp.getInstance().getGeneralFun(this);

        userProfileJson = generalFunc.retrieveValue(Utils.USER_PROFILE_JSON);
        if (token.equals("") || NewCard.equals("true")) {
            cardTokenAvailable = "false";
        } else {
            cardTokenAvailable = "true";
        }
        required_str = generalFunc.retrieveLangLBl("", "LBL_FEILD_REQUIRD");

        backImgView.setOnClickListener(new setOnClickList());
        Utils.hideKeyboard(getActContext());

        String url;
        if(onlyCVV.equals("false")) {
            url = CommonUtilities.PAYMENTLINK + "amount=" + rechargeBox + "&iUserId=" + generalFunc.getMemberId() + "&UserType=" + Utils.app_type + "&vUserDeviceCountry=" +
                    generalFunc.retrieveValue(Utils.DefaultCountryCode) + "&ccode=" + generalFunc.getJsonValue("vCurrencyPassenger", userProfileJson) + "&UniqueCode=" + System.currentTimeMillis()
                    + "&cardTokenAvailable=" + cardTokenAvailable;
        } else {
            url = CommonUtilities.PAYMENTLINK+ "amount=" + rechargeBox + "&iUserId=" + generalFunc.getMemberId() + "&UserType=" + Utils.app_type + "&vUserDeviceCountry=" +
                    generalFunc.retrieveValue(Utils.DefaultCountryCode) + "&ccode=" + generalFunc.getJsonValue("vCurrencyPassenger", userProfileJson) + "&UniqueCode=" + System.currentTimeMillis()
                    + "&cardTokenAvailable=" + cardTokenAvailable;
        }

        Logger.e("aaaa", url);

        paymentWebview.setWebViewClient(new myWebClient());
        paymentWebview.getSettings().setJavaScriptEnabled(true);
        paymentWebview.loadUrl(url);
        paymentWebview.setFocusable(true);
        paymentWebview.setVisibility(View.VISIBLE);
        loaderView.setVisibility(View.VISIBLE);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    public Context getActContext() {
        return CyberSourceAddCardActivity.this;
    }


    public class setOnClickList implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int i = view.getId();
            Utils.hideKeyboard(getApplicationContext());

            if (i == R.id.backImgView) {
                onBackPressed();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public class myWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            Utils.hideKeyboard(getApplicationContext());
            String data = url;
            Logger.d("WebData", "::" + data);
            data = data.substring(data.indexOf("data") + 5, data.length());
            try {

                String datajson = URLDecoder.decode(data, "UTF-8");
                Logger.d("WebData", "::2222222::" + datajson);
                loaderView.setVisibility(View.VISIBLE);

                view.setOnTouchListener(null);


                if (url.contains("success=1") || url.contains("success.php")) {

                    paymentWebview.setVisibility(View.GONE);
                    Intent intent = new Intent();
                    intent.putExtra("message", "success");
                    MyWalletFragment frag = new MyWalletFragment();
                    setResult(frag.RESULT_CODE, intent);
                    onBackPressed();
                    //rechargeBox.setText("");
                    /*generalFunc.showGeneralMessage("", generalFunc.retrieveLangLBl("", generalFunc.retrieveLangLBl("", "LBL_WALLET_MONEY_CREDITED")), "", generalFunc.retrieveLangLBl("", "LBL_OK"), i -> {
//                        isFinish = true;

                        list.clear();
                        getRecentTransction(false);
                        // getWalletBalDetails();
                    });*/
                }

                if (url.contains("success=0")) {
                    paymentWebview.setVisibility(View.GONE);

                    String message = null;
                    if (Utils.checkText(url) && url.contains("&message=")) {
                        String msg = AppFunctions.substringAfterLast(url, "&message=");
                        message = Utils.checkText(msg) ? msg.replaceAll("%20", " ") : message;
                    } else {
                        message = generalFunc.retrieveLangLBl("", "LBL_REQUEST_FAILED_PROCESS");

                    }

                    Intent intent = new Intent();
                    intent.putExtra("message", message);
                    MyWalletFragment frag = new MyWalletFragment();
                    setResult(frag.RESULT_CODE, intent);
                    onBackPressed();


                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }


        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {

            generalFunc.showError();
            loaderView.setVisibility(View.GONE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            loaderView.setVisibility(View.GONE);

            view.setOnTouchListener((v, event) -> {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                        }
                        break;
                }
                return false;
            });

        }
    }

    public class setOnTouchList implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP && !view.hasFocus()) {
                view.performClick();
            }
            return true;
        }
    }

}