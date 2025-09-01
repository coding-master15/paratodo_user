package com.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.paratodo.user.BookingSummaryActivity;
import com.paratodo.user.CardPaymentActivity;
import com.paratodo.user.R;
import com.paratodo.user.VerifyCardTokenActivity;
import com.general.files.ExecuteWebServerUrl;
import com.general.files.GeneralFunctions;
import com.general.files.StartActProcess;
import com.stripe.android.CardUtils;
import com.stripe.android.Stripe;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardMultilineWidget;
import com.stripe.android.view.CardNumberEditText;
import com.utils.Logger;
import com.utils.ModelUtils;
import com.utils.Utils;
import com.view.GenerateAlertBox;
import com.view.MButton;
import com.view.MyProgressDialog;
import com.xendit.Models.XenditError;
import com.xendit.Xendit;

import org.json.JSONObject;

import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.HashMap;

import co.omise.android.Client;
import co.omise.android.TokenRequest;
import co.omise.android.TokenRequestListener;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCardFragment extends Fragment implements TextWatcher {

    private static final char SPACE_CHAR = ' ';

    GeneralFunctions generalFunc;
    View view;

    CardPaymentActivity cardPayAct;

    BookingSummaryActivity bookingSummaryActivity;

    String userProfileJson;
    MButton btn_type2;
    TextInputEditText nameOfCardBox;
    TextInputEditText creditCardBox;
    TextInputEditText cvvBox;
    TextInputEditText mmBox;
    TextInputEditText yyBox;

    View nameArea;
    String required_str = "";
    public boolean isInProcessMode = false;
    LinearLayout defaultArea, stripearea;
    CardMultilineWidget card_input_widget;
    ImageView stCardImgView;
    ImageView cardImgView;

    String LBL_ADD_CARD = "";
    String LBL_CHANGE_CARD = "";
    String APP_PAYMENT_METHOD = "";
    JSONObject userProfileJsonObj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_card, container, false);

        if (getActivity() instanceof CardPaymentActivity) {
            cardPayAct = (CardPaymentActivity) getActivity();
            generalFunc = cardPayAct.generalFunc;
            userProfileJsonObj = cardPayAct.userProfileJsonObj;
        }
        if (getActivity() instanceof BookingSummaryActivity) {

            bookingSummaryActivity = (BookingSummaryActivity) getActivity();
            generalFunc = bookingSummaryActivity.generalFunc;
            userProfileJsonObj = bookingSummaryActivity.userProfileJsonObj;

        }

        APP_PAYMENT_METHOD = generalFunc.getJsonValueStr("APP_PAYMENT_METHOD", userProfileJsonObj);
        LBL_ADD_CARD = generalFunc.retrieveLangLBl("", "LBL_ADD_CARD");
        LBL_CHANGE_CARD = generalFunc.retrieveLangLBl("Change Card", "LBL_CHANGE_CARD");

        btn_type2 = ((MaterialRippleLayout) view.findViewById(R.id.btn_type2)).getChildView();
        nameOfCardBox = (TextInputEditText) view.findViewById(R.id.nameOfCardBox).findViewWithTag("edittext");
        creditCardBox = (TextInputEditText) view.findViewById(R.id.creditCardBox).findViewWithTag("edittext");
        cvvBox = (TextInputEditText) view.findViewById(R.id.cvvBox).findViewWithTag("edittext");
        mmBox = (TextInputEditText) view.findViewById(R.id.mmBox).findViewWithTag("edittext");
        yyBox = (TextInputEditText) view.findViewById(R.id.yyBox).findViewWithTag("edittext");
        defaultArea = (LinearLayout) view.findViewById(R.id.defaultArea);
        stripearea = (LinearLayout) view.findViewById(R.id.stripearea);
        card_input_widget = (CardMultilineWidget) view.findViewById(R.id.card_input_widget);
        stCardImgView = (ImageView) view.findViewById(R.id.stCardImgView);
        cardImgView = (ImageView) view.findViewById(R.id.cardImgView);
        nameArea = view.findViewById(R.id.nameArea);

        cardImgView.setImageDrawable(getResources().getDrawable(R.drawable.ic_card_default));
        if (cardPayAct != null) {
            if (getArguments().getString("PAGE_MODE").equals("ADD_CARD")) {
                cardPayAct.changePageTitle(LBL_ADD_CARD);
                btn_type2.setText(LBL_ADD_CARD);
            } else {
                cardPayAct.changePageTitle(LBL_CHANGE_CARD);
                btn_type2.setText(LBL_CHANGE_CARD);
            }
        } else {

            if (getArguments().getString("PAGE_MODE").equals("ADD_CARD")) {
                btn_type2.setText(LBL_ADD_CARD);
            } else {
                btn_type2.setText(LBL_CHANGE_CARD);
            }

        }

        btn_type2.setId(Utils.generateViewId());
        btn_type2.setOnClickListener(new setOnClickList());

        setLabels();

        mmBox.setInputType(InputType.TYPE_CLASS_NUMBER);
        yyBox.setInputType(InputType.TYPE_CLASS_NUMBER);
        cvvBox.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        cvvBox.setImeOptions(EditorInfo.IME_ACTION_DONE);
        creditCardBox.setInputType(InputType.TYPE_CLASS_PHONE);

        creditCardBox.setFilters(new InputFilter[]{new InputFilter.LengthFilter(24)});
        mmBox.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        cvvBox.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        yyBox.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});

        creditCardBox.addTextChangedListener(this);


            stripearea.setVisibility(View.GONE);
            defaultArea.setVisibility(View.VISIBLE);


        setCardIOView(true);

        return view;
    }


    public void setLabels() {

        nameOfCardBox.setHint(generalFunc.retrieveLangLBl("", "LBL_CARD_HOLDER_NAME_TXT"));
        creditCardBox.setHint(generalFunc.retrieveLangLBl("", "LBL_CARD_NUMBER_HEADER_TXT"));
        cvvBox.setHint(generalFunc.retrieveLangLBl("", "LBL_CVV_HEADER_TXT"));
        mmBox.setHint(generalFunc.retrieveLangLBl("", "LBL_EXP_MONTH_HINT_TXT"));
        yyBox.setHint(generalFunc.retrieveLangLBl("", "LBL_EXP_YEAR_HINT_TXT"));

        required_str = generalFunc.retrieveLangLBl("", "LBL_FEILD_REQUIRD");
        creditCardBox.addTextChangedListener(new CardTypeTextWatcher());
    }

    public boolean validateExpYear(Calendar now) {
        return yyBox.getText().toString() != null && !ModelUtils.hasYearPassed(GeneralFunctions.parseIntegerValue(0, yyBox.getText().toString()), now);
    }

    private void setCardIOView(boolean isShow) {
        if (Utils.isClassExist("io.card.payment.CardIOActivity")) {
            try {
                View actView = generalFunc.getCurrentView(getActivity());
                if (actView.findViewById(R.id.cardioview) != null) {
                    View cardioview = actView.findViewById(R.id.cardioview);
                    cardioview.setVisibility(isShow ? View.GONE : View.GONE);
                    cardioview.setOnClickListener(new setOnClickList());
                }
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onDetach() {
        setCardIOView(false);
        super.onDetach();
    }

    public class CardTypeTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    public void configureStripeView() {
        /*stCardImgView.setImageDrawable(getResources().getDrawable(R.drawable.ic_card_default));

        View cardNumView = getStripeCardBox(com.stripe.android.R.id.et_add_source_card_number_ml);

        if (cardNumView != null && cardNumView instanceof CardNumberEditText) {

            card_input_widget.setCardNumberTextWatcher(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    int charCount = editable.length();

                    if (charCount > 0 && ((CardNumberEditText) cardNumView).getCardBrand() != null) {
                        stCardImgView.setImageDrawable(getBrands(CardUtils.getPossibleCardType(editable.toString())));
                    } else {
                        stCardImgView.setImageDrawable(getResources().getDrawable(R.drawable.ic_card_default));
                    }
                }
            });
        }*/
    }


    public void checkDetails() {


        /*Card card = new Card(Utils.getText(creditCardBox), generalFunc.parseIntegerValue(0, Utils.getText(mmBox)),
                generalFunc.parseIntegerValue(0, Utils.getText(yyBox)), Utils.getText(cvvBox));

        Logger.d("Card No", ":" + card.validateNumber() + "::num::" + card.getNumber());
        boolean isNameEntered = true;
        if (nameArea.getVisibility() == View.VISIBLE) {
            isNameEntered = Utils.checkText(nameOfCardBox) ? true : Utils.setErrorFields(nameOfCardBox, generalFunc.retrieveLangLBl("", "LBL_FEILD_REQUIRD"));
        }
        boolean cardNoEntered = Utils.checkText(creditCardBox) ? (card.validateNumber() ? true :
                Utils.setErrorFields(creditCardBox, generalFunc.retrieveLangLBl("", "LBL_INVALID")))
                : Utils.setErrorFields(creditCardBox, required_str);
        boolean cvvEntered = Utils.checkText(cvvBox) ? (card.validateCVC() ? true :
                Utils.setErrorFields(cvvBox, generalFunc.retrieveLangLBl("", "LBL_INVALID"))) : Utils.setErrorFields(cvvBox, required_str);
        boolean monthEntered = Utils.checkText(mmBox) ? (card.validateExpMonth() ? true :
                Utils.setErrorFields(mmBox, generalFunc.retrieveLangLBl("", "LBL_INVALID"))) : Utils.setErrorFields(mmBox, required_str);
        boolean yearEntered = Utils.checkText(yyBox) ? (validateExpYear(Calendar.getInstance()) ? true :
                Utils.setErrorFields(yyBox, generalFunc.retrieveLangLBl("", "LBL_INVALID"))) : Utils.setErrorFields(yyBox, required_str);
        boolean yearEntedcount = true;
        if (yearEntered == true) {
            yearEntedcount = yyBox.getText().toString().length() == 4 ? true : Utils.setErrorFields(yyBox, generalFunc.retrieveLangLBl("", "LBL_INVALID"));
        }


        if (isNameEntered == false || cardNoEntered == false || cvvEntered == false || monthEntered == false || yearEntered == false || yearEntedcount == false) {
            return;
        }*/



    }
    public void setProcessingMode() {
        isInProcessMode = true;
        btn_type2.setText(generalFunc.retrieveLangLBl("Processing Payment", "LBL_PROCESS_PAYMENT_TXT"));
        creditCardBox.setEnabled(false);
        mmBox.setEnabled(false);
        yyBox.setEnabled(false);
        cvvBox.setEnabled(false);
        btn_type2.setEnabled(false);
    }


    public void closeProcessingMode() {
        try {
            isInProcessMode = false;
            if (getArguments().getString("PAGE_MODE").equals("ADD_CARD")) {
                btn_type2.setText(LBL_ADD_CARD);
            } else {
                btn_type2.setText(LBL_CHANGE_CARD);
            }
            creditCardBox.setEnabled(true);
            mmBox.setEnabled(true);
            yyBox.setEnabled(true);
            cvvBox.setEnabled(true);
            btn_type2.setEnabled(true);
        } catch (Exception e) {

        }
    }

    public MyProgressDialog showLoader() {
        MyProgressDialog myPDialog = new MyProgressDialog(getActContext(), false, generalFunc.retrieveLangLBl("Loading", "LBL_LOADING_TXT"));
        myPDialog.show();

        return myPDialog;
    }

    public void generateOmiseToken(String cardHolderName, String cardNumber, String expMonth, String expYear, String cvv) throws GeneralSecurityException {
        final MyProgressDialog myPDialog = showLoader();
        String OMISE_PUBLIC_KEY = generalFunc.getJsonValueStr("OMISE_PUBLIC_KEY", userProfileJsonObj);
        Client client = new Client(OMISE_PUBLIC_KEY);

        TokenRequest request = new TokenRequest();
        request.number = cardNumber.replaceAll("\\s+", "");
        request.name = cardHolderName;
        request.expirationMonth = GeneralFunctions.parseIntegerValue(1, expMonth);
        request.expirationYear = GeneralFunctions.parseIntegerValue(Calendar.getInstance().get(Calendar.YEAR), expYear);
        request.securityCode = cvv;

        client.send(request, new TokenRequestListener() {

            @Override
            public void onTokenRequestSucceed(TokenRequest tokenRequest, co.omise.android.models.Token token) {
                myPDialog.close();
                CreateCustomer(Utils.maskCardNumber(cardNumber), token.id);
            }

            @Override
            public void onTokenRequestFailed(TokenRequest tokenRequest, Throwable throwable) {
                myPDialog.close();
            }
        });

    }

    public void generateStripeToken(final Card card) {

        /*final MyProgressDialog myPDialog = showLoader();

        String STRIPE_PUBLISH_KEY = generalFunc.getJsonValueStr("STRIPE_PUBLISH_KEY", userProfileJsonObj);
        Stripe stripe = new Stripe(getActContext());

        stripe.createToken(card, STRIPE_PUBLISH_KEY, new TokenCallback() {
            public void onSuccess(Token token) {
                // TODO: Send Token information to your backend to initiate a charge
                myPDialog.close();
//                CreateCustomer(card, null, null, token.getId());
                CreateCustomer(Utils.maskCardNumber(card.getNumber()), token.getId());
            }

            public void onError(Exception error) {
                myPDialog.close();
                generalFunc.showError();
            }
        });*/
    }


    public void setScanData(CreditCard scanResult) {
        if (scanResult == null) {
            return;
        }

        String cardnumber = scanResult.getFormattedCardNumber();
        String expMonth = "" + scanResult.expiryMonth;
        String expYear = "" + scanResult.expiryYear;
        String cvc = "" + scanResult.cvv;
        String cardHolderName = scanResult.cardholderName == null ? "" : scanResult.cardholderName;

//        String cardnumber, String expMonth, String expYear, String cvc


            if (nameArea.getVisibility() == View.VISIBLE) {
                nameOfCardBox.setText(cardHolderName);
            }
            creditCardBox.setText(cardnumber);
            mmBox.setText(expMonth);
            yyBox.setText(expYear);
            cvvBox.setText(cvc);

    }

    public View getStripeCardBox(int id) {
        if (stripearea.getVisibility() == View.VISIBLE && card_input_widget.findViewById(id) != null) {

            return card_input_widget.findViewById(id);
        }
        return null;
    }

    public void CreateCustomer(String cardNum, String token) {
        if (cardPayAct.getIntent().hasExtra("isCheckout")) {
            addMoneyToWallet(token);
        } else {
            submitToken(cardNum, token);
        }

    }

    private void addMoneyToWallet(String token) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", "addMoneyUserWalletByChargeCard");
        parameters.put("iMemberId", generalFunc.getMemberId());
        parameters.put("fAmount", cardPayAct.getIntent().getStringExtra("fAmount"));
        parameters.put("UserType", Utils.app_type);
        parameters.put("vStripeToken", token);
        parameters.put("eSystem", Utils.eSystem_Type);

        ExecuteWebServerUrl exeWebServer = new ExecuteWebServerUrl(getActContext(), parameters);
        exeWebServer.setLoaderConfig(getActContext(), true, generalFunc);
        exeWebServer.setDataResponseListener(new ExecuteWebServerUrl.SetDataResponse() {
            @Override
            public void setResponse(String responseString) {

                if (responseString != null && !responseString.equals("")) {

                    boolean isDataAvail = GeneralFunctions.checkDataAvail(Utils.action_str, responseString);

                    if (isDataAvail == true) {

                        generalFunc.storeData(Utils.USER_PROFILE_JSON, generalFunc.getJsonValue(Utils.message_str, responseString));
                        GenerateAlertBox generateAlertBox = new GenerateAlertBox(getActContext());
                        generateAlertBox.setCancelable(false);
                        generateAlertBox.setContentMessage("", generalFunc.retrieveLangLBl("", generalFunc.getJsonValue(Utils.message_str_one, responseString)));

                        generateAlertBox.setPositiveBtn(generalFunc.retrieveLangLBl("", "LBL_OK"));
                        generateAlertBox.setBtnClickList(new GenerateAlertBox.HandleAlertBtnClick() {
                            @Override
                            public void handleBtnClick(int btn_id) {
                                Intent returnIntent = new Intent();
                                cardPayAct.setResult(Activity.RESULT_OK, returnIntent);
                                cardPayAct.onBackPressed();
                                generateAlertBox.closeAlertBox();

                            }
                        });
                        generateAlertBox.showAlertBox();
                    } else {
                        generalFunc.showGeneralMessage("",
                                generalFunc.retrieveLangLBl("", generalFunc.getJsonValue(Utils.message_str, responseString)));
                    }
                } else {
                    generalFunc.showError();
                }
            }
        });
        exeWebServer.execute();

    }

    private void submitToken(String cardNum, String token) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", "GenerateCustomer");
        parameters.put("iUserId", generalFunc.getMemberId());

        parameters.put("CardNo", cardNum);


        parameters.put("UserType", Utils.app_type);


        ExecuteWebServerUrl exeWebServer = new ExecuteWebServerUrl(getActContext(), parameters);
        exeWebServer.setLoaderConfig(getActContext(), true, generalFunc);
        exeWebServer.setDataResponseListener(responseString -> {


            if (responseString != null && !responseString.equals("")) {

                boolean isDataAvail = GeneralFunctions.checkDataAvail(Utils.action_str, responseString);


                if (isDataAvail == true) {


                } else {

                    generalFunc.showGeneralMessage("",
                            generalFunc.retrieveLangLBl("", generalFunc.getJsonValue(Utils.message_str, responseString)));
                }
            } else {
                generalFunc.showError();
            }
        });
        exeWebServer.execute();
    }


    public void setdata(int requestCode, int resultCode, Intent data) {

        if (requestCode == Utils.REQ_VERIFY_CARD_PIN_CODE && resultCode == cardPayAct.RESULT_OK && data != null) {

            UpdateCustomerToken((HashMap<String, Object>) data.getSerializableExtra("data"));
        }
    }

    private void UpdateCustomerToken(HashMap<String, Object> data) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", "UpdateCustomerToken");
        parameters.put("iUserId", generalFunc.getMemberId());
        parameters.put("vPaymayaToken", data.get("vPaymayaToken").toString());
        parameters.put("UserType", Utils.app_type);

        ExecuteWebServerUrl exeWebServer = new ExecuteWebServerUrl(getActContext(), parameters);
        exeWebServer.setLoaderConfig(getActContext(), true, generalFunc);
        exeWebServer.setDataResponseListener(responseString -> {

            if (responseString != null && !responseString.equals("")) {

                boolean isDataAvail = GeneralFunctions.checkDataAvail(Utils.action_str, responseString);

                if (isDataAvail == true) {
                    generalFunc.storeData(Utils.USER_PROFILE_JSON, generalFunc.getJsonValue(Utils.message_str, responseString));
                    cardPayAct.changeUserProfileJson(generalFunc.getJsonValue(Utils.message_str, responseString));
                } else {
                    closeProcessingMode();
                    generalFunc.showGeneralMessage("",
                            generalFunc.retrieveLangLBl("", generalFunc.getJsonValue(Utils.message_str, responseString)));
                }
            } else {
                closeProcessingMode();
                generalFunc.showError();
            }
        });
        exeWebServer.execute();
    }


    public Context getActContext() {
        if (cardPayAct != null) {
            return cardPayAct.getActContext();
        }
        if (bookingSummaryActivity != null) {
            return bookingSummaryActivity.getActContext();
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Utils.hideKeyboard(getActContext());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0 && (s.length() % 5) == 0) {
            final char c = s.charAt(s.length() - 1);
            if (SPACE_CHAR == c) {
                s.delete(s.length() - 1, s.length());
            }
        }
        // Insert char where needed.
        if (s.length() > 0 && (s.length() % 5) == 0) {
            char c = s.charAt(s.length() - 1);
            // Only if its a digit where there should be a space we insert a space
            if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(SPACE_CHAR)).length <= 3) {
                s.insert(s.length() - 1, String.valueOf(SPACE_CHAR));
            }
        }
    }

    public class setOnClickList implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Utils.hideKeyboard(getActContext());
            int i = view.getId();
            if (i == btn_type2.getId()) {
                checkDetails();
            } else if (i == R.id.cardioview) {

                if (generalFunc.isCameraPermissionGranted() && Utils.isClassExist("io.card.payment.CardIOActivity")) {
                    Bundle bn = new Bundle();
                    bn.putBoolean(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
                    bn.putBoolean(CardIOActivity.EXTRA_SCAN_EXPIRY, true);
                    bn.putBoolean(CardIOActivity.EXTRA_REQUIRE_CVV, true);
                    bn.putBoolean(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
                    bn.putBoolean(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true);
                    bn.putBoolean(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, false);
                    bn.putBoolean(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false);
                    new StartActProcess(getActContext()).startActForResult(AddCardFragment.this, CardIOActivity.class, Utils.MY_SCAN_REQUEST_CODE, bn);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Utils.MY_SCAN_REQUEST_CODE && data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {

            CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

            setScanData(scanResult);

            if (scanResult != null) {
                Logger.d("scanResult", "::" + scanResult.toString());
            }
        }
    }
}
