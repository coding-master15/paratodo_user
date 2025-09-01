package com.paratodo.user;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapter.files.ServiceSearchAdapter;
import com.frogobox.loadingindicator.FrogoLoadingIndicatorView;
import com.general.files.ExecuteWebServerUrl;
import com.general.files.GeneralFunctions;
import com.general.files.MyApp;
import com.general.files.StartActProcess;
import com.utils.Logger;
import com.utils.Utils;
import com.view.MTextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchServiceActivity extends AppCompatActivity implements ServiceSearchAdapter.OnItemClickList {

    EditText searchTxtView;
    RecyclerView foodSearchRecycView;
    ServiceSearchAdapter serviceSearchAdapter;
    MTextView cancelTxt;
    ArrayList<HashMap<String, String>> serviceSearchArrList = new ArrayList<>();
    ImageView imageCancel;
    GeneralFunctions generalFunc;

    ImageView noSearchImage;
    MTextView norecordTxt;
    FrogoLoadingIndicatorView loaderView;
    String parentId = "";
    MainActivity mainAct;
    Context mContext;
    private String iVehicleCategoryId;
    int MIN_CHAR_REQ_UFX_SEARCH_SERVICE  = 3;
    String userProfileJson = "";
    String currentSearchQuery = "";
    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_services);
        parentId = getIntent().getStringExtra("parentId");
        generalFunc = MyApp.getInstance().getGeneralFun(getActContext());
        userProfileJson = generalFunc.retrieveValue(Utils.USER_PROFILE_JSON);
        MIN_CHAR_REQ_UFX_SEARCH_SERVICE = GeneralFunctions.parseIntegerValue(3,
                generalFunc.getJsonValue("MIN_CHAR_REQ_UFX_SEARCH_SERVICE", userProfileJson));
        initView();
        if (mContext instanceof MainActivity) {
            mainAct = (MainActivity) mContext;
        }
    }


    public void initView() {
        generalFunc = MyApp.getInstance().getGeneralFun(getActContext());
        searchTxtView = (EditText) findViewById(R.id.searchTxtView);
        imageCancel = (ImageView) findViewById(R.id.imageCancel);
        cancelTxt = (MTextView) findViewById(R.id.cancelTxt);
        noSearchImage = (ImageView) findViewById(R.id.noSearchImage);
        norecordTxt = (MTextView) findViewById(R.id.norecordTxt);
        loaderView = (FrogoLoadingIndicatorView) findViewById(R.id.loaderView);
        cancelTxt.setOnClickListener(new setOnClickList());
        imageCancel.setOnClickListener(new setOnClickList());
        cancelTxt.setText(generalFunc.retrieveLangLBl("", "LBL_CANCEL_GENERAL"));

        searchTxtView.setHint(generalFunc.retrieveLangLBl("", "LBL_SEARCH_SERVICES"));
        Utils.showSoftKeyboard(this,searchTxtView);

       // new CreateRoundedView(Color.parseColor("#ECECEC"), 15, 0, getActContext().getResources().getColor(android.R.color.transparent), searchTxtView);

        foodSearchRecycView = findViewById(R.id.foodSearchRecycView);
        serviceSearchAdapter = new ServiceSearchAdapter(getActContext(), serviceSearchArrList);
        foodSearchRecycView.setLayoutManager(new LinearLayoutManager(getActContext()));
        foodSearchRecycView.setAdapter(serviceSearchAdapter);
        serviceSearchAdapter.setOnItemClickList(this);

        norecordTxt.setText(generalFunc.retrieveLangLBl("", "LBL_NO_RECORD_FOUND"));

        searchTxtView.setOnFocusChangeListener((v, hasFocus) -> {

            if (!hasFocus) {
                Utils.hideSoftKeyboard((Activity) getActContext(), searchTxtView);
            } else {
                Utils.showSoftKeyboard((Activity) getActContext(), searchTxtView);
            }
        });

        searchTxtView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                /*if (s.length() > MIN_CHAR_REQ_UFX_SEARCH_SERVICE) {

                    getRestaurantList(searchTxtView.getText().toString().trim());
                    Logger.d("LIST_SIZE_COUNT", "afterTextChanged"   + s.length());

                } else {
                    if (searchTxtView.getText().length() == 0) {
                        norecordTxt.setVisibility(View.GONE);
                    }
                    noSearchImage.setVisibility(View.VISIBLE);
                    serviceSearchArrList.clear();
                    serviceSearchAdapter.notifyDataSetChanged();
                }*/

                if (currentSearchQuery.equals(s.toString().trim())) {
                    return;
                }


                if (handler == null) {
                    handler = new Handler();
                } else {

                    handler.removeCallbacksAndMessages(null);
                    handler = new Handler();
                }


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        currentSearchQuery = searchTxtView.getText().toString();

                        if (s.length() >= MIN_CHAR_REQ_UFX_SEARCH_SERVICE) {

                            foodSearchRecycView.setVisibility(View.VISIBLE);

                            getRestaurantList(currentSearchQuery);
                        } else {
                            if (searchTxtView.getText().length() == 0) {
                                norecordTxt.setVisibility(View.GONE);
                            }
                            noSearchImage.setVisibility(View.VISIBLE);
                            serviceSearchArrList.clear();
                            serviceSearchAdapter.notifyDataSetChanged();

                        }
                    }
                }, 750);

            }
        });

    }

    @Override
    public void onItemClick(int position) {

        HashMap<String, String> mapData = serviceSearchArrList.get(position);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isufx", true);
        bundle.putString("latitude", getIntent().getStringExtra("latitude"));
        bundle.putString("longitude", getIntent().getStringExtra("longitude"));
        bundle.putString("address", getIntent().getStringExtra("address"));
        bundle.putString("SelectedVehicleTypeId", mapData.get("iVehicleCategoryId"));
        bundle.putString("parentId", parentId);
        bundle.putBoolean("isCarwash", true);

        new StartActProcess(getActContext()).startActWithData(MainActivity.class, bundle);
    }


    public class setOnClickList implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int i = view.getId();
            Utils.hideKeyboard(getActContext());
            if (i == R.id.cancelTxt) {
                onBackPressed();
            } else if (i == R.id.imageCancel) {
                searchTxtView.setText("");
                norecordTxt.setVisibility(View.GONE);
                loaderView.setVisibility(View.GONE);
            }


        }
    }

    public void getRestaurantList(String searchKeyword) {
        loaderView.setVisibility(View.VISIBLE);
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", "getSearchServiceCategories");
        parameters.put("userId", generalFunc.getMemberId());
        parameters.put("search", searchKeyword);
        parameters.put("parentId ", parentId);
        parameters.put("vLang", generalFunc.retrieveValue(Utils.LANGUAGE_CODE_KEY));

        ExecuteWebServerUrl exeWebServer = new ExecuteWebServerUrl(getActContext(), parameters);
        // exeWebServer.setLoaderConfig(getActContext(), true, generalFunc);
        exeWebServer.setDataResponseListener(new ExecuteWebServerUrl.SetDataResponse() {
            @Override
            public void setResponse(String responseString) {
                if (responseString != null && !responseString.equals("")) {
                    boolean isDataAvail = GeneralFunctions.checkDataAvail(Utils.action_str, responseString);

                    loaderView.setVisibility(View.GONE);

                    if (isDataAvail == true) {
                        serviceSearchArrList.clear();
                        JSONArray mainCataArray = generalFunc.getJsonArray("message", responseString);
                        int mainCatArraySize = mainCataArray.length();

                        for (int i = 0; i < mainCatArraySize; i++) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            JSONObject categoryObj = generalFunc.getJsonObject(mainCataArray, i);
                            map.put("eCatType", generalFunc.getJsonValueStr("eCatType", categoryObj));
                            map.put("eServiceCategory", generalFunc.getJsonValueStr("eServiceCategory", categoryObj));
                            map.put("iServiceId", generalFunc.getJsonValueStr("iServiceId", categoryObj));
                            map.put("vCategory", generalFunc.getJsonValueStr("vCategory", categoryObj));
                            map.put("vLogo_image", generalFunc.getJsonValueStr("vLogo_image", categoryObj));
                            map.put("iVehicleCategoryId", generalFunc.getJsonValueStr("iVehicleCategoryId", categoryObj));
                            iVehicleCategoryId = generalFunc.getJsonValueStr(
                                    "iVehicleCategoryId", categoryObj);
                            map.put("vCategoryBanner", generalFunc.getJsonValueStr("vCategoryBanner", categoryObj));
                            map.put("vBannerImage", generalFunc.getJsonValueStr("vBannerImage", categoryObj));
                            map.put("tBannerButtonText", generalFunc.getJsonValueStr("tBannerButtonText", categoryObj));
                            String eShowTerms = generalFunc.getJsonValueStr("eShowTerms", categoryObj);
                            map.put("eShowTerms", Utils.checkText(eShowTerms) ? eShowTerms : "");
//                            map.put("LBL_BOOK_NOW", LBL_BOOK_NOW);
                            serviceSearchArrList.add(map);
//                            Logger.d("LIST_SIZE_COUNT", ":::"   + serviceSearchArrList.toString());
                        }


                        serviceSearchAdapter.notifyDataSetChanged();


                        if (serviceSearchArrList.size() > 0) {
                            noSearchImage.setVisibility(View.GONE);
                        } else {
                            norecordTxt.setVisibility(View.VISIBLE);
                            noSearchImage.setVisibility(View.VISIBLE);
                        }

                    } else {
                        if (serviceSearchArrList != null && serviceSearchArrList.size() == 0) {
                            norecordTxt.setVisibility(View.VISIBLE);
                            noSearchImage.setVisibility(View.VISIBLE);
                        }
                        serviceSearchArrList.clear();
                        serviceSearchAdapter.notifyDataSetChanged();

                    }
                } else {
                    generalFunc.showError();
                }
            }
        });
        exeWebServer.execute();
    }


    public Context getActContext() {
        return SearchServiceActivity.this;
    }
}
