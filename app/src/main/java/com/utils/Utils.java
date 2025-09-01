/*      */ package com.utils;
/*      */ 
/*      */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.graphics.drawable.DrawableCompat;

import com.general.files.GeneralFunctions;
import com.google.android.material.textfield.TextInputEditText;
import com.view.MTextView;
import com.view.MyProgressDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Utils
/*      */ {
/*      */   public static final int NOTIFICATION_ID = 11;
/*      */   public static final String pubNub_Update_Loc_Channel_Prefix = "ONLINE_DRIVER_LOC_";
/*      */   public static final String deviceType = "Android";
/*   63 */   public static String userType = "";
/*   64 */   public static String USER_ID_KEY = "";
/*      */   
/*   66 */   public static String IS_APP_IN_DEBUG_MODE = "";
/*      */   public static boolean IS_OPTIMIZE_MODE_ENABLE = false;
/*   68 */   public static String SERVER_CONNECTION_URL = "";
/*      */   
/*   70 */   public static String CALLTO = "";
/*      */   
/*      */   public static final int MENU_PROFILE = 1;
/*      */   
/*      */   public static final int MENU_VEHICLE = 2;
/*      */   
/*      */   public static final int MENU_RIDE_HISTORY = 3;
/*      */   
/*      */   public static final int MENU_BOOKINGS = 4;
/*      */   
/*      */   public static final int MENU_ABOUT_US = 5;
/*      */   
/*      */   public static final int MENU_PAYMENT = 6;
/*      */   
/*      */   public static final int MENU_CONTACT_US = 7;
/*      */   
/*      */   public static final int MENU_HELP = 8;
/*      */   
/*      */   public static final int MENU_EMERGENCY_CONTACT = 9;
/*      */   
/*      */   public static final int MENU_SIGN_OUT = 10;
/*      */   
/*      */   public static final int MENU_WALLET = 11;
/*      */   
/*      */   public static final int MENU_INVITE_FRIEND = 12;
/*      */   
/*      */   public static final int MENU_POLICY = 13;
/*      */   
/*      */   public static final int MENU_ONGOING_TRIPS = 14;
/*      */   
/*      */   public static final int MENU_SUPPORT = 15;
/*      */   public static final int MENU_YOUR_TRIPS = 16;
/*      */   public static final int MENU_ACCOUNT_VERIFY = 17;
/*      */   public static final int MENU_BUSINESS_PROFILE = 18;
/*      */   public static final int MENU_FEEDBACK = 19;
/*      */   public static final int MENU_MY_HEATVIEW = 20;
/*      */   public static final int MENU_YOUR_DOCUMENTS = 21;
/*      */   public static final int MENU_MANAGE_VEHICLES = 22;
/*      */   public static final int MENU_TRIP_STATISTICS = 23;
/*      */   public static final int MENU_WAY_BILL = 24;
/*      */   public static final int MENU_BANK_DETAIL = 25;
/*      */   public static final int MENU_SET_AVAILABILITY = 26;
/*      */   public static final int MENU_ORDER = 27;
/*      */   public static final int MENU_CART = 28;
/*      */   public static final int MENU_ORDER_STATISTICS = 29;
/*      */   public static final int MENU_FOOD = 30;
/*      */   public static final int MENU_EARNING = 31;
/*      */   public static final int MENU_RESTAURANT_SETTINGS = 32;
/*      */   public static final int MENU_NOTIFICATION = 33;
/*      */   public static final int MENU_MY_GALLERY = 34;
/*      */   public static final int MENU_SUBSCRIPTION = 35;
/*      */   public static final int MENU_DONATION = 36;
/*      */   public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 41;
/*      */   public static final int OVERLAY_PERMISSION_REQ_CODE = 42;
/*      */   public static final int MY_SCAN_REQUEST_CODE = 43;
/*      */   public static final int REQ_VERIFY_CARD_PIN_CODE = 44;
/*      */   public static final int REQ_VERIFY_INSTANT_PAYMENT_CODE = 45;
/*      */   public static final int SELECT_COUNTRY_REQ_CODE = 46;
/*      */   public static final int SEARCH_PICKUP_LOC_REQ_CODE = 47;
/*      */   public static final int UBER_X_SEARCH_PICKUP_LOC_REQ_CODE = 48;
/*      */   public static final int SEARCH_DEST_LOC_REQ_CODE = 49;
/*      */   public static final int MY_PROFILE_REQ_CODE = 50;
/*      */   public static final int OTHER_AREA_CLICKED_CODE = 51;
/*      */   public static final int VERIFY_MOBILE_REQ_CODE = 52;
/*      */   public static final int ADD_HOME_LOC_REQ_CODE = 53;
/*      */   public static final int ADD_WORK_LOC_REQ_CODE = 54;
/*      */   public static final int CARD_PAYMENT_REQ_CODE = 55;
/*      */   public static final int VERIFY_INFO_REQ_CODE = 56;
/*      */   public static final int ADD_VEHICLE_REQ_CODE = 57;
/*      */   public static final int ADD_RECIPIENT_REQ_CODE = 58;
/*      */   public static final int SELECT_RECIPIENT_REQ_CODE = 59;
/*      */   public static final int SELECT_COUPON_REQ_CODE = 60;
/*      */   public static final int ASSIGN_DRIVER_CODE = 61;
/*      */   public static final int PLACE_CUSTOME_LOC_REQUEST_CODE = 62;
/*      */   public static final int RIDE_DELIVERY_DETAILS_REQ_CODE = 63;
/*      */   public static final int DELIVERY_DETAILS_REQ_CODE = 64;
/*      */   public static final int REQUEST_CODE_GPS_ON = 65;
/*      */   public static final int REQUEST_CODE_NETWOEK_ON = 67;
/*      */   public static final int FILTER_REQ_CODE = 68;
/*      */   public static final int COUPON_REQ_CODE = 69;
/*      */   public static final int REQ_OMISE_CODE = 70;
/*      */   public static final int UPLOAD_DOC_REQ_CODE = 71;
/*      */   public static final int UFX_REQUEST_CODE = 72;
/*      */   public static final int SCHEDULE_REQUEST_CODE = 73;
/*      */   public static final int SELECT_ORGANIZATION_CODE = 74;
/*      */   public static final int SELECT_ORGANIZATION_PAYMENT_CODE = 75;
/*      */   public static final int ADD_MAP_LOC_REQ_CODE = 76;
/*      */   public static final int LIVE_TRACK_REQUEST_CODE = 77;
/*      */   public static final int ALL_DELIVERY_DETAILS_ADDED_CODE = 78;
/*      */   public static final int ADD_LOC_REQ_CODE = 79;
/*      */   public static final int MULTI_DELIVERY_DETAILS_ADD_CODE = 80;
/*      */   public static final int MULTI_DELIVERY_DETAILS_REQ_CODE = 81;
/*      */   public static final int MULTIDELIVERY_HISTORY_RATE_CODE = 82;
/*      */   public static final int ORDER_DETAIL_REQUEST_CODE = 83;
/*      */   public static final int ADD_TO_BASKET = 84;
/*      */   public static final int SOCIAL_LOGIN_REQ_CODE = 85;
/*      */   public static final int ORDER_DETAILS_REQ_CODE = 86;
/*      */   public static final int TRACK_ORDER_REQ_CODE = 87;
/*      */   public static final int REQUEST_READ_CONTACTS = 88;
/*      */   public static final int REQUEST_PICK_CONTACTS = 89;
/*      */   public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
/*      */   public static final int LOCATION_UPDATE_MIN_DISTANCE_IN_MITERS = 2;
/*      */   public static final int LOCATION_POST_MIN_DISTANCE_IN_MITERS = 5;
/*      */   public static final int NOTIFICATION_BACKGROUND_ID = 12;
/*      */   public static final float defaultZomLevel = 16.5F;
/*      */   public static final int minPasswordLength = 6;
/*      */   public static final int ImageUpload_DESIREDWIDTH = 1024;
/*      */   public static final int ImageUpload_DESIREDHEIGHT = 1024;
/*      */   public static final int ImageUpload_MINIMUM_WIDTH = 256;
/*      */   public static final int ImageUpload_MINIMUM_HEIGHT = 256;
/*  180 */   public static String LIVE_CHAT_LICENCE_NUMBER = "LIVE_CHAT_LICENCE_NUMBER";
/*      */   
/*      */   public static final String All = "All";
/*      */   
/*      */   public static final String Notificatons = "Notification";
/*      */   
/*      */   public static final String News = "News";
/*      */   
/*      */   public static final String CALLTODRIVER = "Driver";
/*      */   public static final String CALLTOPASSENGER = "Passenger";
/*      */   public static final String CALLTOSTORE = "Company";
/*      */   public static final String TempImageFolderPath = "TempImages";
/*      */   public static final String TempProfileImageName = "temp_pic_img.png";
/*      */   public static final String CabReqType_Now = "Now";
/*      */   public static final String CabReqType_Later = "Later";
/*      */   public static final String CabGeneralType_Ride = "Ride";
/*      */   public static final String CabGeneralType_Deliver = "Delivery";
/*      */   public static final String CabGeneralType_UberX = "UberX";
/*      */   public static final String CabGeneralTypeRide_Delivery_UberX = "Ride-Delivery-UberX";
/*      */   public static final String CabGeneralTypeRide_Delivery = "Ride-Delivery";
/*      */   public static final String Cab_UberX_Type_List = "List";
/*      */   public static final String Cab_UberX_Type_Map = "Map";
/*      */   public static final String Cab_UberX_Type_Filter = "Filter";
/*      */   public static final String Wallet_all = "All";
/*      */   public static final String Wallet_credit = "CREDIT";
/*      */   public static final String Wallet_debit = "DEBIT";
/*      */   public static final String Past = "getRideHistory";
/*      */   public static final String Upcoming = "checkBookings";
/*      */   public static final String CabFaretypeRegular = "Regular";
/*      */   public static final String CabFaretypeFixed = "Fixed";
/*      */   public static final String CabFaretypeHourly = "Hourly";
/*      */   public static final String DELIVERY_DETAILS_KEY = "DeliverList";
/*      */   public static final String TRIP_REQ_CODE_PREFIX_KEY = "TRIP_STATUS_MSG_";
/*      */   public static final String PUBNUB_PUB_KEY = "PUBNUB_PUBLISH_KEY";
/*      */   public static final String PUBNUB_SUB_KEY = "PUBNUB_SUBSCRIBE_KEY";
/*      */   public static final String PUBNUB_SEC_KEY = "PUBNUB_SECRET_KEY";
/*      */   public static final String ENABLE_PUBNUB_KEY = "ENABLE_PUBNUB";
/*      */   public static final String USER_PROFILE_JSON = "User_Profile";
/*      */   public static final String SINCH_APP_KEY = "SINCH_APP_KEY";
/*      */   public static final String SINCH_APP_SECRET_KEY = "SINCH_APP_SECRET_KEY";
/*      */   public static final String SINCH_APP_ENVIRONMENT_HOST = "SINCH_APP_ENVIRONMENT_HOST";
/*  221 */   public static String HANDICAP_ACCESSIBILITY_OPTION = "HANDICAP_ACCESSIBILITY_OPTION";
/*  222 */   public static String CHILD_SEAT_ACCESSIBILITY_OPTION = "CHILD_SEAT_ACCESSIBILITY_OPTION";
/*  223 */   public static String WHEEL_CHAIR_ACCESSIBILITY_OPTION = "WHEEL_CHAIR_ACCESSIBILITY_OPTION";
/*      */   
/*  225 */   public static MyProgressDialog myPDialog = null;
/*      */   
/*  227 */   private static final AtomicInteger a = new AtomicInteger(1);
/*  228 */   public static String SMS_BODY_KEY = "SMS_BODY";
/*  229 */   public static String SESSION_ID_KEY = "APP_SESSION_ID";
/*  230 */   public static String DEVICE_SESSION_ID_KEY = "DEVICE_SESSION_ID";
/*  231 */   public static String FETCH_TRIP_STATUS_TIME_INTERVAL_KEY = "FETCH_TRIP_STATUS_TIME_INTERVAL";
/*  232 */   public static String RIDER_REQUEST_ACCEPT_TIME_KEY = "RIDER_REQUEST_ACCEPT_TIME";
/*  233 */   public static String SC_CONNECT_URL_KEY = "SC_CONNECT_URL";
/*  234 */   public static String GOOGLE_SERVER_ANDROID_PASSENGER_APP_KEY = "GOOGLE_SERVER_ANDROID_PASSENGER_APP_KEY";
/*      */   
/*  236 */   public static String dateFormateInHeaderBar = "EEE, MMM d, yyyy";
/*  237 */   public static String dateFormateInList = "dd-MMM-yyyy";
/*  238 */   public static String DefaultDatefromate = "yyyy-MM-dd";
/*  239 */   public static String OriginalDateFormate = "yyyy-MM-dd HH:mm:ss";
/*  240 */   public static String dateFormateForBooking = "dd MMM yyyy";
/*  241 */   public static String VERIFICATION_CODE_RESEND_TIME_IN_SECONDS_KEY = "VERIFICATION_CODE_RESEND_TIME_IN_SECONDS";
/*  242 */   public static String VERIFICATION_CODE_RESEND_COUNT_KEY = "VERIFICATION_CODE_RESEND_COUNT";
/*  243 */   public static String VERIFICATION_CODE_RESEND_COUNT_RESTRICTION_KEY = "VERIFICATION_CODE_RESEND_COUNT_RESTRICTION";
/*  244 */   public static String DefaultCountry = "vDefaultCountry";
/*  245 */   public static String DefaultCountryCode = "vDefaultCountryCode";
/*  246 */   public static String DefaultPhoneCode = "vDefaultPhoneCode";
/*  247 */   public static String DefaultCountryImage = "vDefaultCountryImage";
/*  248 */   public static String app_type = "";
/*  249 */   public static String languageLabelsKey = "LanguageLabel";
/*      */ 
/*      */   
/*  252 */   public static String APP_GCM_SENDER_ID_KEY = "APP_GCM_SENDER_ID";
/*  253 */   public static String MOBILE_VERIFICATION_ENABLE_KEY = "MOBILE_VERIFICATION_ENABLE";
/*  254 */   public static String FACEBOOK_APPID_KEY = "FACEBOOK_APPID";
/*  255 */   public static String LINK_FORGET_PASS_KEY = "LINK_FORGET_PASS_PAGE_PASSENGER";
/*  256 */   public static String LANGUAGE_LIST_KEY = "LANGUAGELIST";
/*  257 */   public static String CURRENCY_LIST_KEY = "CURRENCYLIST";
/*  258 */   public static String LANGUAGE_IS_RTL_KEY = "LANG_IS_RTL";
/*  259 */   public static String GOOGLE_MAP_LANGUAGE_CODE_KEY = "GOOGLE_MAP_LANG_CODE";
/*  260 */   public static String REFERRAL_SCHEME_ENABLE = "REFERRAL_SCHEME_ENABLE";
/*  261 */   public static String SITE_TYPE_KEY = "SITE_TYPE";
/*  262 */   public static String WALLET_ENABLE = "WALLET_ENABLE";
/*  263 */   public static String DATABASE_RTL_STR = "rtl";
/*  264 */   public static String LANGUAGE_CODE_KEY = "LANG_CODE";
/*  265 */   public static String isUserLogIn = "IsUserLoggedIn";
/*  266 */   public static String iMemberId_KEY = USER_ID_KEY;
/*  267 */   public static String APP_TYPE = "APP_TYPE";
/*  268 */   public static String action_str = "Action";
/*  269 */   public static String message_str = "message";
/*  270 */   public static String message_str_one = "message1";
/*  271 */   public static String UBERX_PARENT_CAT_ID = "UBERX_PARENT_CAT_ID";
/*  272 */   public static String APP_DESTINATION_MODE = "APP_DESTINATION_MODE";
/*  273 */   public static String ENABLE_TOLL_COST = "ENABLE_TOLL_COST";
/*  274 */   public static String TOLL_COST_APP_ID = "TOLL_COST_APP_ID";
/*  275 */   public static String TOLL_COST_APP_CODE = "TOLL_COST_APP_CODE";
/*  276 */   public static String FEMALE_RIDE_REQ_ENABLE = "FEMALE_RIDE_REQ_ENABLE";
/*  277 */   public static String PREF_FEMALE = "Female_request";
/*  278 */   public static String PREF_HANDICAP = "Handicap_request";
/*  279 */   public static String DEFAULT_LANGUAGE_VALUE = "DEFAULT_LANGUAGE_VALUE";
/*  280 */   public static String DEFAULT_CURRENCY_VALUE = "DEFAULT_CURRENCY_VALUE";
/*  281 */   public static String PUBSUB_TECHNIQUE = "PUBSUB_TECHNIQUE";
/*  282 */   public static String YALGAAR_CLIENT_KEY = "YALGAAR_CLIENT_KEY";
/*  283 */   public static String NONE_DESTINATION = "None";
/*  284 */   public static String STRICT_DESTINATION = "Strict";
/*  285 */   public static String NON_STRICT_DESTINATION = "NonStrict";
/*  286 */   public static String GCM_FAILED_KEY = "GCM_FAILED";
/*  287 */   public static String APNS_FAILED_KEY = "APNS_FAILED";
/*  288 */   public static String SELECTEDRIVERID = "SELECTEDRIVERID";
/*  289 */   public static String FACEBOOK_LOGIN = "FACEBOOK_LOGIN";
/*  290 */   public static String GOOGLE_LOGIN = "GOOGLE_LOGIN";
/*  291 */   public static String TWITTER_LOGIN = "TWITTER_LOGIN";
/*  292 */   public static String LINKDIN_LOGIN = "LINKDIN_LOGIN";
/*  293 */   public static String isCardAdded = "isCardAdded";
/*  294 */   public static String ISWALLETBALNCECHANGE = "ISWALLETBALNCECHANGE";
/*  295 */   public static String PUBSUB_PUBLISH_DRIVER_LOC_DISTANCE_LIMIT = "PUBSUB_PUBLISH_DRIVER_LOC_DISTANCE_LIMIT";
/*  296 */   public static String dateFormateTimeOnly = "h:mm a";
/*      */   
/*  298 */   public static String LINK_SIGN_UP_PAGE_KEY = "LINK_SIGN_UP_PAGE_DRIVER";
/*  299 */   public static String DRIVER_CURRENT_REQ_OPEN_KEY = "DRIVER_REQ_OPEN";
/*      */   
/*  301 */   public static String IsTripStarted = "TripStart";
/*  302 */   public static String DriverWaitingTime = "DriverWaitingTime";
/*  303 */   public static String DriverWaitingSecTime = "DriverWaitingSecTime";
/*      */   
/*  305 */   public static String GO_ONLINE_KEY = "GO_ONLINE";
/*  306 */   public static String LAST_FINISH_TRIP_TIME_KEY = "LAST_FINISH_TRIP_TIME";
/*  307 */   public static String PHOTO_UPLOAD_SERVICE_ENABLE_KEY = "PHOTO_UPLOAD_SERVICE_ENABLE";
/*      */   
/*  309 */   public static String DRIVER_ONLINE_KEY = "IS_DRIVER_ONLINE";
/*  310 */   public static String DRIVER_REQ_CODE_PREFIX_KEY = "REQUEST_CODE_";
/*  311 */   public static String DRIVER_ACTIVE_REQ_MSG_KEY = "ACTIVE_REQUEST_MSG_";
/*  312 */   public static String DRIVER_REQ_COMPLETED_MSG_CODE_KEY = "REQUEST_CODE_CONFIRMED_";
/*  313 */   public static String BACKGROUND_APP_RECEIVER_INTENT_ACTION = "BACKGROUND_CALLBACK_ACTION";
/*  314 */   public static String passenger_message_arrived_intent_key = "message";
/*      */   
/*  316 */   public static String WORKLOCATION = "vWorkLocation";
/*      */   
/*  318 */   public static String GOOGLE_SERVER_ANDROID_DRIVER_APP_KEY = "GOOGLE_SERVER_ANDROID_DRIVER_APP_KEY";
/*      */   
/*      */   public static final String ENABLE_DRIVER_DESTINATIONS_KEY = "ENABLE_DRIVER_DESTINATIONS";
/*      */   
/*      */   public static final String DRIVER_DESTINATION_AVAILABLE_KEY = "DRIVER_DESTINATION_AVAILABLE";
/*      */   
/*      */   public static final String ENABLE_FAVORITE_DRIVER_MODULE_KEY = "ENABLE_FAVORITE_DRIVER_MODULE";
/*  325 */   public static String ENABLE_MULTI_DELIVERY_KEY = "ENABLE_MULTI_DELIVERY";
/*  326 */   public static String MAX_ALLOW_NUM_DESTINATION_MULTI_KEY = "MAX_ALLOW_NUM_DESTINATION_MULTI";
/*  327 */   public static String ENABLE_ROUTE_CALCULATION_MULTI_KEY = "ENABLE_ROUTE_CALCULATION_MULTI";
/*  328 */   public static String ENABLE_ROUTE_OPTIMIZE_MULTI_KEY = "ENABLE_ROUTE_OPTIMIZE_MULTI";
/*      */   
/*  330 */   public static String ALLOW_MULTIPLE_DEST_ADD_KEY = "ALLOW_MULTIPLE_DEST_ADD";
/*      */   
/*      */   public static final String DELIVERY_ALL_DETAILS_KEY = "DeliveryListAll";
/*      */   
/*      */   public static final String MUTLI_DELIVERY_JSON_DETAILS_KEY = "DeliveryListMultiJson";
/*      */   
/*      */   public static final String MUTLI_DELIVERY_LIST_JSON_DETAILS_KEY = "DeliveryListJSonMulti";
/*      */   
/*      */   public static final String PUBNUB_DISABLED_KEY = "PUBNUB_DISABLED";
/*      */   
/*      */   public static final String ENABLE_SOCKET_CLUSTER_KEY = "ENABLE_SOCKET_CLUSTER";
/*      */   public static boolean SKIP_MOCK_LOCATION_CHECK = false;
/*  342 */   public static String serviceCategoriesKey = "ServiceCategories";
/*  343 */   public static String GOOGLE_SERVER_ANDROID_COMPANY_APP_KEY = "GOOGLE_SERVER_ANDROID_COMPANY_APP_KEY";
/*  344 */   public static String eSystem_Type = "DeliverAll";
/*  345 */   public static String BASKET_ITEMS = "BASKET_ITEMS";
/*  346 */   public static String COMPANY_ID = "COMPANY_ID";
/*  347 */   public static String COMPANY_MINIMUM_ORDER = "MINIMUM_ORDER";
/*  348 */   public static String COMPANY_MAX_QTY = "MAX_QTY";
/*  349 */   public static String SELECT_ADDRESSS = "SELECT_ADDRESSS";
/*  350 */   public static String SELECT_LATITUDE = "SELECT_LATITUDE";
/*  351 */   public static String SELECT_LONGITUDE = "SELECT_LONGITUDE";
/*  352 */   public static String SELECT_ADDRESS_ID = "SELECT_ADDRESS_ID";
/*  353 */   public static String CURRENT_ADDRESSS = "CURRENT_ADDRESSS";
/*  354 */   public static String CURRENT_LATITUDE = "CURRENT_LATITUDE";
/*  355 */   public static String CURRENT_LONGITUDE = "CURRENT_LONGITUDE";
/*  356 */   public static String iServiceId_KEY = "iServiceId";
/*  357 */   public static String DEFAULT_STATE_VALUE = "DEFAULT_STATE_VALUE";
/*  358 */   public static String DEFAULT_CITY_VALUE = "DEFAULT_CITY_VALUE";
/*      */ 
/*      */   
/*      */   public static final String eType_Multi_Delivery = "Multi-Delivery";
/*      */   
/*  363 */   public static String isMultiTrackRunning = "No";
/*      */ 
/*      */ 
/*      */   
/*  367 */   public static String TrackDateFormatewithTime = "dd MMM, hh:mm aa";
/*  368 */   public static String DELIVERALL_KEY = "DELIVERALL";
/*  369 */   public static String ONLYDELIVERALL_KEY = "ONLYDELIVERALL";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  375 */   public static String iHotelId_KEY = "iHotelId";
/*      */   public static final String HOTEL_PROFILE_JSON = "Hotel_Profile";
/*  377 */   public static String eSystem_Type_KIOSK = "kiosk";
/*      */   
/*      */   public static boolean IS_KIOSK_APP = false;
/*      */   
/*      */   public static final String KIOSK_DESTINATION_LIST_JSON_DETAILS_KEY = "destinationSelect";
/*      */   public static final String KIOSK_HOTEL_ADDRESS_KEY = "hotelAddress";
/*      */   public static final String KIOSK_HOTEL_Lattitude_KEY = "hotelLattitude";
/*      */   public static final String KIOSK_HOTEL_Longitude_KEY = "hotelLongitude";
/*  385 */   public static String VisitLocationsKey = "Visit_Locations";
/*  386 */   public static String countrycode = "COUNTRY_CODE";
/*      */ 
/*      */   
/*      */   public static final String BOOK_FOR_ELSE_SHOW_NO_CONTACT_KEY = "BOOK_FOR_ELSE_SHOW_NO_CONTACT";
/*      */   
/*      */   public static final String LIST_CONTACTS_KEY = "LIST_CONTACTS";
/*      */   
/*      */   public static final String BFSE_SELECTED_CONTACT_KEY = "BFSE_SELECTED_CONTACT";
/*      */   
/*  395 */   public static String ENABLE_STOPOVER_POINT_KEY = "ENABLE_STOPOVER_POINT";
/*  396 */   public static String MAX_NUMBER_STOP_OVER_POINTS_KEY = "MAX_NUMBER_STOP_OVER_POINTS";
/*  397 */   public static String BOOK_FOR_ELSE_ENABLE_KEY = "BOOK_FOR_ELSE_ENABLE";
/*      */   
/*  399 */   public static String DRIVER_SUBSCRIPTION_ENABLE_KEY = "DRIVER_SUBSCRIPTION_ENABLE";
/*      */   public static final String ENABLE_GOPAY_KEY = "ENABLE_GOPAY";
/*  401 */   public static String DONATION_ENABLE = "DONATION_ENABLE";
/*      */   
/*      */   public static final String THERMAL_PRINT_ENABLE_KEY = "THERMAL_PRINT_ENABLE";
/*      */   
/*      */   public static final String AUTO_PRINT_KEY = "AUTO_PRINT";
/*      */   public static final String THERMAL_PRINT_ALLOWED_KEY = "THERMAL_PRINT_ALLOWED";
/*  407 */   public static int SETTINGS_UPDATED_CODE = 565;
/*      */   
/*      */   public static final int REQUEST_COARSE_LOCATION = 200;
/*      */   
/*      */   public static final int REQUEST_CONNECT_BT = 0;
/*      */   public static final int REQUEST_ENABLE_BT = 0;
/*      */   public static final String KOT_BILL_FORMAT_KEY = "KOT_BILL_FORMAT";
/*      */   public static final String SITE_NAME_KEY = "SITE_NAME";
/*  415 */   public static String passenger_app_type = "Passenger";
/*      */   
/*  417 */   public static String data_str = "Data";
/*      */   
/*  419 */   public static String WalletApiFormate = "dd-MMM-yyyy";
/*  420 */   public static String DateFormateInDetailScreen = "EEE, MMM dd, yyyy hh:mm aaa";
/*      */   
/*      */   public static String getDetailDateFormat(Context mContext) {
/*  423 */     GeneralFunctions generalFunctions = new GeneralFunctions(mContext);
/*      */     
/*  425 */     String str = generalFunctions.retrieveLangLBl("at", "LBL_AT_TXT");
/*      */     
/*  427 */     return "EEE, MMM dd, yyyy '" + str + "' hh:mm aaa";
/*      */   }
/*      */   
/*      */   public static int dipToPixels(Context context, float dipValue) {
/*  431 */     DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
/*  432 */     return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, displayMetrics);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getExifRotation(String path) {
/*  437 */     ExifInterface exifInterface = null;
/*      */     try {
/*  439 */       exifInterface = new ExifInterface(path);
/*  440 */     } catch (IOException iOException) {
/*  441 */       iOException.printStackTrace();
/*      */     } 
/*  443 */     return exifInterface.getAttributeInt("Orientation", 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setMenuTextColor(MenuItem item, int color) {
/*  451 */     SpannableString spannableString = new SpannableString(item.getTitle());
/*  452 */     spannableString.setSpan(new ForegroundColorSpan(color), 0, spannableString.length(), 0);
/*  453 */     item.setTitle((CharSequence)spannableString);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String removeWithSpace(String data) {
/*  459 */     return data.replaceAll("\\s+", " ");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int generateViewId() {
/*  470 */
    /*      */
/*  482 */     return View.generateViewId();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void runGC() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void removeInput(EditText editBox) {
/*  496 */     editBox.setInputType(0);
/*  497 */     editBox.setFocusableInTouchMode(false);
/*  498 */     editBox.setFocusable(false);
/*      */     
/*  500 */     editBox.setOnTouchListener((paramView, paramMotionEvent) -> true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean checkText(TextInputEditText editBox) {
/*  507 */     if (getText(editBox).trim().equals("") || TextUtils.isEmpty((CharSequence)editBox.getText())) {
/*  508 */       return false;
/*      */     }
/*  510 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean checkText(String text) {
/*  514 */     if (text == null || text.trim().equals("") || TextUtils.isEmpty(text)) {
/*  515 */       return false;
/*      */     }
/*  517 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean checkText(EditText editBox) {
/*  521 */     if (getText(editBox).trim().equals("")) {
/*  522 */       return false;
/*      */     }
/*  524 */     return true;
/*      */   }
/*      */   
/*      */   public static String getText(TextInputEditText editBox) {
/*  528 */     return editBox.getText().toString().trim();
/*      */   }
/*      */   
/*      */   public static String getText(EditText editBox) {
/*  532 */     return editBox.getText().toString().trim();
/*      */   }
/*      */   
/*      */   public static String getText(MTextView textView) {
/*  536 */     return textView.getText().toString().trim();
/*      */   }
/*      */   
/*      */   public static boolean setErrorFields(TextInputEditText editBox, String error) {
/*  540 */     editBox.setError(error);
/*  541 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean setErrorFields(EditText editBox, String error) {
/*  545 */     editBox.setError(error);
/*  546 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String maskCardNumber(String cardNumber) {
/*  551 */     byte b = 0;
/*  552 */     StringBuffer stringBuffer = new StringBuffer();
/*  553 */     while (b < cardNumber.length()) {
/*  554 */       if (b > cardNumber.length() - 5) {
/*  555 */         stringBuffer.append(cardNumber.charAt(b));
/*      */       } else {
/*  557 */         stringBuffer.append("X");
/*      */       } 
/*  559 */       b++;
/*      */     } 
/*      */     
/*  562 */     return stringBuffer.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public static Date convertStringToDate(String format, String date) {
/*  567 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
/*      */     try {
/*  569 */       return simpleDateFormat.parse(date);
/*      */     }
/*  571 */     catch (Exception exception) {
/*  572 */       exception.printStackTrace();
/*      */       
/*  574 */       return null;
/*      */     } 
/*      */   }
/*      */   public static String convertDateToFormat(String format, Date date) {
/*  578 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
/*  579 */     return simpleDateFormat.format(date);
/*      */   }
/*      */   
/*      */   public static boolean isValidTimeSelect(Date selectedDate, long timeOffset) {
/*  583 */     if (selectedDate.getTime() >= System.currentTimeMillis() + timeOffset) {
/*  584 */       return true;
/*      */     }
/*  586 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isValidTimeSelectForLater(Date selectedDate, long timeOffset) {
/*  590 */     if (selectedDate.getTime() <= System.currentTimeMillis() + timeOffset) {
/*  591 */       return true;
/*      */     }
/*  593 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void hideKeyboard(Context context) {
/*  598 */     if (context != null && context instanceof Activity) {
/*  599 */       hideKeyboard((Activity)context);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void hideKeyboard(Activity act) {
/*  604 */     if (act != null && act instanceof Activity) {
/*  605 */       act.getWindow().setSoftInputMode(3);
/*  606 */       act.getWindow().setSoftInputMode(3);
/*  607 */       View view = act.getCurrentFocus();
/*  608 */       if (view != null) {
/*  609 */         InputMethodManager inputMethodManager = (InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE);
/*  610 */         inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void hideKeyPad(Activity act) {
/*  617 */     InputMethodManager inputMethodManager = (InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE);
/*  618 */     inputMethodManager.toggleSoftInput(1, 0);
/*      */   }
/*      */   
/*      */   public static void setAppLocal(Context mContext) {
/*  622 */     GeneralFunctions generalFunctions = new GeneralFunctions(mContext);
/*      */     
/*  624 */     String str1 = generalFunctions.retrieveValue(GOOGLE_MAP_LANGUAGE_CODE_KEY);
/*  625 */     String str2 = str1.trim().equals("") ? "en" : str1;
/*  626 */     Locale locale = new Locale(str2, (mContext.getResources().getConfiguration()).locale.getCountry());
/*  627 */     Locale.setDefault(locale);
/*      */     
/*  629 */     if (Build.VERSION.SDK_INT >= 24) {
/*  630 */       a(mContext, locale);
/*      */       
/*      */       return;
/*      */     } 
/*  634 */     b(mContext, locale);
/*      */   }
/*      */   
/*      */   @TargetApi(24)
/*      */   private static Context a(Context paramContext, Locale paramLocale) {
/*  639 */     Configuration configuration = paramContext.getResources().getConfiguration();
/*  640 */     configuration.setLocale(paramLocale);
/*  641 */     return paramContext.createConfigurationContext(configuration);
/*      */   }
/*      */ 
/*      */   
/*      */   private static Context b(Context paramContext, Locale paramLocale) {
/*  646 */     Resources resources = paramContext.getResources();
/*  647 */     Configuration configuration = resources.getConfiguration();
/*  648 */     configuration.locale = paramLocale;
/*  649 */     resources.updateConfiguration(configuration, resources.getDisplayMetrics());
/*  650 */     return paramContext;
/*      */   }
/*      */   
/*      */   public static void clearApplicationData(Context mContext) {
/*  654 */     File file1 = mContext.getCacheDir();
/*  655 */     File file2 = new File(file1.getParent());
/*  656 */     if (file2.exists()) {
/*  657 */       String[] arrayOfString = file2.list();
/*  658 */       for (String str : arrayOfString) {
/*  659 */         if (!str.equals("lib")) {
/*  660 */           deleteDir(new File(file2, str));
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean deleteDir(File dir) {
/*  667 */     if (dir != null && dir.isDirectory()) {
/*  668 */       String[] arrayOfString = dir.list();
/*  669 */       for (byte b = 0; b < arrayOfString.length; b++) {
/*  670 */         boolean bool = deleteDir(new File(dir, arrayOfString[b]));
/*  671 */         if (!bool) {
/*  672 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*  676 */     return dir.delete();
/*      */   }
/*      */   
/*      */   public static double CalculationByLocation(double lat1, double lon1, double lat2, double lon2, String returnType) {
/*  680 */     char c = 'ᣣ';
/*  681 */     double d1 = lat1;
/*  682 */     double d2 = lat2;
/*  683 */     double d3 = lon1;
/*  684 */     double d4 = lon2;
/*  685 */     double d5 = Math.toRadians(d2 - d1);
/*  686 */     double d6 = Math.toRadians(d4 - d3);
/*      */     
/*  688 */     double d7 = Math.sin(d5 / 2.0D) * Math.sin(d5 / 2.0D) + Math.cos(Math.toRadians(d1)) * Math.cos(Math.toRadians(d2)) * Math.sin(d6 / 2.0D) * Math.sin(d6 / 2.0D);
/*  689 */     double d8 = 2.0D * Math.asin(Math.sqrt(d7));
/*  690 */     double d9 = c * d8;
/*  691 */     double d10 = d9 / 1.0D;
/*  692 */     DecimalFormat decimalFormat = new DecimalFormat("####");
/*  693 */     int i = Integer.valueOf(decimalFormat.format(d10)).intValue();
/*  694 */     double d11 = d9 % 1000.0D;
/*  695 */     int j = Integer.valueOf(decimalFormat.format(d11)).intValue();
/*      */ 
/*      */ 
/*      */     
/*  699 */     if (returnType.equals("METER")) {
/*  700 */       return j;
/*      */     }
/*  702 */     return c * d8;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int dpToPx(Context context, float dpValue) {
/*  707 */     DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
/*  708 */     return Math.round(dpValue * displayMetrics.xdpi / 160.0F);
/*      */   }
/*      */   
/*      */   public static int pxToDp(Context context, float pxValue) {
/*  712 */     DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
/*  713 */     return Math.round(pxValue / displayMetrics.xdpi / 160.0F);
/*      */   }
/*      */   
/*      */   public static Intent getLauncherIntent(Context mContext) {
    /*  724 */     Log.e("test", mContext.getPackageName());

    /*  717 */     Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
/*  718 */     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
/*  719 */     return intent;
/*      */   }
/*      */   
public static Intent getLauncherIntent(Context mContext, boolean isClearAllActivities) {
/*  723 */     Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
/*  724 */     Log.e("test", mContext.getPackageName());
    if (isClearAllActivities) {
/*  725 */       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
/*      */     } else {
/*  727 */       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
/*      */     } 
/*  729 */     return intent;
/*      */   }
/*      */   
/*      */   public static Intent getPreviousIntent(Context context) {
/*  733 */     if (context == null) {
/*  734 */       return null;
/*      */     }
/*      */     
/*  737 */     Intent intent = null;
/*  738 */     if (context.getSystemService(Context.ACTIVITY_SERVICE) != null) {
/*  739 */       ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
/*  740 */       if (activityManager != null && activityManager.getRecentTasks(1024, 0) != null) {
/*  741 */         List<ActivityManager.RecentTaskInfo> list = activityManager.getRecentTasks(1024, 0);
/*  742 */         String str = context.getPackageName();
/*      */         
/*  744 */         if (str != null && list != null && !list.isEmpty())
/*      */         {
/*  746 */           for (byte b = 0; b < list.size(); b++) {
/*  747 */             ActivityManager.RecentTaskInfo recentTaskInfo = list.get(b);
/*  748 */             if (recentTaskInfo != null && recentTaskInfo.baseIntent != null && recentTaskInfo.baseIntent.getComponent().getPackageName().equals(str)) {
/*  749 */               intent = recentTaskInfo.baseIntent;
/*  750 */               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  757 */     return intent;
/*      */   }
/*      */   
/*      */   public static String getFileExt(String fileName) {
/*  761 */     return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
/*      */   }
/*      */   
/*      */   public static String getUserDeviceCountryCode(Context context) {
/*  765 */     if (context == null) {
/*  766 */       return "";
/*      */     }
/*      */     try {
/*  769 */       TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
/*  770 */       String str1 = telephonyManager.getSimCountryIso();
/*  771 */       if (str1 != null && str1.length() == 2)
/*  772 */         return str1.toLowerCase(Locale.US); 
/*  773 */       if (telephonyManager.getPhoneType() != 2) {
/*  774 */         String str2 = telephonyManager.getNetworkCountryIso();
/*  775 */         if (str2 != null && str2.length() == 2) {
/*  776 */           return str2.toLowerCase(Locale.US);
/*      */         }
/*      */       } 
/*  779 */     } catch (Exception exception) {
/*  780 */       System.out.println("TelephonyError: Details: " + exception.getMessage());
/*      */     } 
/*      */     
/*  783 */     String str = "";
/*      */     try {
/*  785 */       if (Build.VERSION.SDK_INT >= 24) {
/*  786 */         str = context.getResources().getConfiguration().getLocales().get(0).getCountry();
/*      */       } else {
/*  788 */         str = (context.getResources().getConfiguration()).locale.getCountry();
/*      */       } 
/*  790 */     } catch (Exception exception) {
/*  791 */       System.out.println("LocalizedCountryCodeError: Details:" + exception.getMessage());
/*      */     } 
/*  793 */     return str;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setBlurImage(Bitmap bitmap_profile_icon, ImageView profileimageback) {
/*  798 */     Bitmap bitmap = fastblur(bitmap_profile_icon, 95);
/*  799 */     profileimageback.setImageBitmap(bitmap);
/*  800 */     profileimageback.invalidate();
/*      */   }
/*      */ 
/*      */   
/*      */   public static Bitmap fastblur(Bitmap sentBitmap, int radius) {
/*  805 */     Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
/*      */     
/*  807 */     if (radius < 1) {
/*  808 */       return null;
/*      */     }
/*      */     
/*  811 */     int i = bitmap.getWidth();
/*  812 */     int j = bitmap.getHeight();
/*      */     
/*  814 */     int[] arrayOfInt1 = new int[i * j];
/*  815 */     bitmap.getPixels(arrayOfInt1, 0, i, 0, 0, i, j);
/*      */     
/*  817 */     int k = i - 1;
/*  818 */     int m = j - 1;
/*  819 */     int n = i * j;
/*  820 */     int i1 = radius + radius + 1;
/*      */     
/*  822 */     int[] arrayOfInt2 = new int[n];
/*  823 */     int[] arrayOfInt3 = new int[n];
/*  824 */     int[] arrayOfInt4 = new int[n];
/*      */     
/*  826 */     int[] arrayOfInt5 = new int[Math.max(i, j)];
/*      */     
/*  828 */     int i5 = i1 + 1 >> 1;
/*  829 */     i5 *= i5;
/*  830 */     int[] arrayOfInt6 = new int[256 * i5]; int i2;
/*  831 */     for (i2 = 0; i2 < 256 * i5; i2++) {
/*  832 */       arrayOfInt6[i2] = i2 / i5;
/*      */     }
/*      */     
/*  835 */     int i3 = 0, i4 = i3;
/*      */     
/*  837 */     int[][] arrayOfInt = new int[i1][3];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  842 */     int i6 = radius + 1;
/*      */     
/*      */     byte b2;
/*      */     
/*  846 */     for (b2 = 0; b2 < j; b2++) {
/*  847 */       int i9 = 0, i8 = i9, i7 = i8, i13 = i7, i12 = i13, i11 = i12, i16 = i11, i15 = i16, i14 = i15;
/*  848 */       for (i2 = -radius; i2 <= radius; i2++) {
/*  849 */         int i17 = arrayOfInt1[i3 + Math.min(k, Math.max(i2, 0))];
/*  850 */         int[] arrayOfInt7 = arrayOfInt[i2 + radius];
/*  851 */         arrayOfInt7[0] = (i17 & 0xFF0000) >> 16;
/*  852 */         arrayOfInt7[1] = (i17 & 0xFF00) >> 8;
/*  853 */         arrayOfInt7[2] = i17 & 0xFF;
/*  854 */         int i18 = i6 - Math.abs(i2);
/*  855 */         i7 += arrayOfInt7[0] * i18;
/*  856 */         i8 += arrayOfInt7[1] * i18;
/*  857 */         i9 += arrayOfInt7[2] * i18;
/*  858 */         if (i2 > 0) {
/*  859 */           i14 += arrayOfInt7[0];
/*  860 */           i15 += arrayOfInt7[1];
/*  861 */           i16 += arrayOfInt7[2];
/*      */         } else {
/*  863 */           i11 += arrayOfInt7[0];
/*  864 */           i12 += arrayOfInt7[1];
/*  865 */           i13 += arrayOfInt7[2];
/*      */         } 
/*      */       } 
/*  868 */       int i10 = radius;
/*      */       
/*  870 */       for (byte b = 0; b < i; b++) {
/*      */         
/*  872 */         arrayOfInt2[i3] = arrayOfInt6[i7];
/*  873 */         arrayOfInt3[i3] = arrayOfInt6[i8];
/*  874 */         arrayOfInt4[i3] = arrayOfInt6[i9];
/*      */         
/*  876 */         i7 -= i11;
/*  877 */         i8 -= i12;
/*  878 */         i9 -= i13;
/*      */         
/*  880 */         int i18 = i10 - radius + i1;
/*  881 */         int[] arrayOfInt7 = arrayOfInt[i18 % i1];
/*      */         
/*  883 */         i11 -= arrayOfInt7[0];
/*  884 */         i12 -= arrayOfInt7[1];
/*  885 */         i13 -= arrayOfInt7[2];
/*      */         
/*  887 */         if (b2 == 0) {
/*  888 */           arrayOfInt5[b] = Math.min(b + radius + 1, k);
/*      */         }
/*  890 */         int i17 = arrayOfInt1[i4 + arrayOfInt5[b]];
/*      */         
/*  892 */         arrayOfInt7[0] = (i17 & 0xFF0000) >> 16;
/*  893 */         arrayOfInt7[1] = (i17 & 0xFF00) >> 8;
/*  894 */         arrayOfInt7[2] = i17 & 0xFF;
/*      */         
/*  896 */         i14 += arrayOfInt7[0];
/*  897 */         i15 += arrayOfInt7[1];
/*  898 */         i16 += arrayOfInt7[2];
/*      */         
/*  900 */         i7 += i14;
/*  901 */         i8 += i15;
/*  902 */         i9 += i16;
/*      */         
/*  904 */         i10 = (i10 + 1) % i1;
/*  905 */         arrayOfInt7 = arrayOfInt[i10 % i1];
/*      */         
/*  907 */         i11 += arrayOfInt7[0];
/*  908 */         i12 += arrayOfInt7[1];
/*  909 */         i13 += arrayOfInt7[2];
/*      */         
/*  911 */         i14 -= arrayOfInt7[0];
/*  912 */         i15 -= arrayOfInt7[1];
/*  913 */         i16 -= arrayOfInt7[2];
/*      */         
/*  915 */         i3++;
/*      */       } 
/*  917 */       i4 += i;
/*      */     } 
/*  919 */     for (byte b1 = 0; b1 < i; b1++) {
/*  920 */       int i9 = 0, i8 = i9, i7 = i8, i14 = i7, i13 = i14, i12 = i13, i17 = i12, i16 = i17, i15 = i16;
/*  921 */       int i10 = -radius * i;
/*  922 */       for (i2 = -radius; i2 <= radius; i2++) {
/*  923 */         i3 = Math.max(0, i10) + b1;
/*      */         
/*  925 */         int[] arrayOfInt7 = arrayOfInt[i2 + radius];
/*      */         
/*  927 */         arrayOfInt7[0] = arrayOfInt2[i3];
/*  928 */         arrayOfInt7[1] = arrayOfInt3[i3];
/*  929 */         arrayOfInt7[2] = arrayOfInt4[i3];
/*      */         
/*  931 */         int i18 = i6 - Math.abs(i2);
/*      */         
/*  933 */         i7 += arrayOfInt2[i3] * i18;
/*  934 */         i8 += arrayOfInt3[i3] * i18;
/*  935 */         i9 += arrayOfInt4[i3] * i18;
/*      */         
/*  937 */         if (i2 > 0) {
/*  938 */           i15 += arrayOfInt7[0];
/*  939 */           i16 += arrayOfInt7[1];
/*  940 */           i17 += arrayOfInt7[2];
/*      */         } else {
/*  942 */           i12 += arrayOfInt7[0];
/*  943 */           i13 += arrayOfInt7[1];
/*  944 */           i14 += arrayOfInt7[2];
/*      */         } 
/*      */         
/*  947 */         if (i2 < m) {
/*  948 */           i10 += i;
/*      */         }
/*      */       } 
/*  951 */       i3 = b1;
/*  952 */       int i11 = radius;
/*  953 */       for (b2 = 0; b2 < j; b2++) {
/*      */         
/*  955 */         arrayOfInt1[i3] = 0xFF000000 & arrayOfInt1[i3] | arrayOfInt6[i7] << 16 | arrayOfInt6[i8] << 8 | arrayOfInt6[i9];
/*      */         
/*  957 */         i7 -= i12;
/*  958 */         i8 -= i13;
/*  959 */         i9 -= i14;
/*      */         
/*  961 */         int i19 = i11 - radius + i1;
/*  962 */         int[] arrayOfInt7 = arrayOfInt[i19 % i1];
/*      */         
/*  964 */         i12 -= arrayOfInt7[0];
/*  965 */         i13 -= arrayOfInt7[1];
/*  966 */         i14 -= arrayOfInt7[2];
/*      */         
/*  968 */         if (b1 == 0) {
/*  969 */           arrayOfInt5[b2] = Math.min(b2 + i6, m) * i;
/*      */         }
/*  971 */         int i18 = b1 + arrayOfInt5[b2];
/*      */         
/*  973 */         arrayOfInt7[0] = arrayOfInt2[i18];
/*  974 */         arrayOfInt7[1] = arrayOfInt3[i18];
/*  975 */         arrayOfInt7[2] = arrayOfInt4[i18];
/*      */         
/*  977 */         i15 += arrayOfInt7[0];
/*  978 */         i16 += arrayOfInt7[1];
/*  979 */         i17 += arrayOfInt7[2];
/*      */         
/*  981 */         i7 += i15;
/*  982 */         i8 += i16;
/*  983 */         i9 += i17;
/*      */         
/*  985 */         i11 = (i11 + 1) % i1;
/*  986 */         arrayOfInt7 = arrayOfInt[i11];
/*      */         
/*  988 */         i12 += arrayOfInt7[0];
/*  989 */         i13 += arrayOfInt7[1];
/*  990 */         i14 += arrayOfInt7[2];
/*      */         
/*  992 */         i15 -= arrayOfInt7[0];
/*  993 */         i16 -= arrayOfInt7[1];
/*  994 */         i17 -= arrayOfInt7[2];
/*      */         
/*  996 */         i3 += i;
/*      */       } 
/*      */     } 
/*      */     
/* 1000 */     bitmap.setPixels(arrayOfInt1, 0, i, 0, 0, i, j);
/*      */     
/* 1002 */     return bitmap;
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getScreenPixelWidth(Context mContext) {
/* 1007 */     DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
/* 1008 */     return displayMetrics.widthPixels;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float getScreenPixelHeight(Context mContext) {
/* 1014 */     DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
/* 1015 */     return displayMetrics.heightPixels;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getWidthOfBanner(Context mContext, int widthOffsetDpValueInPixel) {
/* 1021 */     return (int)(getScreenPixelWidth(mContext) - widthOffsetDpValueInPixel);
/*      */   }
/*      */   
/*      */   public static int getHeightOfBanner(Context mContext, int widthOffsetDpValueInPixel, String ratio) {
/* 1025 */     if (ratio.equalsIgnoreCase("4:3")) {
/* 1026 */       return (int)(getWidthOfBanner(mContext, widthOffsetDpValueInPixel) / 1.33333333333D);
/*      */     }
/* 1028 */     return (int)(getWidthOfBanner(mContext, widthOffsetDpValueInPixel) / 1.77777778D);
/*      */   }
/*      */   
/*      */   public static String getResizeImgURL(Context mContext, String imgUrl, int width, int height) {
/* 1032 */     String str1 = GeneralFunctions.retrieveValue("SERVERURL", mContext);
/* 1033 */     imgUrl = imgUrl.replace(" ", "%20");
/* 1034 */     String str2 = str1.endsWith("/") ? str1 : (str1 + "/");
/* 1035 */     str2 = str2 + "resizeImg.php?src=" + imgUrl + "&w=" + width + "&h=" + height;
/* 1036 */     return str2;
/*      */   }
/*      */   
/*      */   public static String getResizeImgURL(Context mContext, String imgUrl, int width, int height, int MAX_WIDTH) {
/* 1040 */     String str1 = GeneralFunctions.retrieveValue("SERVERURL", mContext);
/* 1041 */     imgUrl = imgUrl.replace(" ", "%20");
/* 1042 */     String str2 = str1.endsWith("/") ? str1 : (str1 + "/");
/* 1043 */     str2 = str2 + "resizeImg.php?src=" + imgUrl + "&w=" + width + "&h=" + height + "&IMG_MAX_WIDTH=" + MAX_WIDTH;
/* 1044 */     return str2;
/*      */   }
/*      */   
/*      */   public static String getResizeImgURL(Context mContext, String imgUrl, int width, int height, float MAX_HEIGHT) {
/* 1048 */     String str1 = GeneralFunctions.retrieveValue("SERVERURL", mContext);
/* 1049 */     imgUrl = imgUrl.replace(" ", "%20");
/* 1050 */     String str2 = str1.endsWith("/") ? str1 : (str1 + "/");
/* 1051 */     str2 = str2 + "resizeImg.php?src=" + imgUrl + "&w=" + width + "&h=" + height + "&IMG_MAX_HEIGHT=" + MAX_HEIGHT;
/* 1052 */     return str2;
/*      */   }
/*      */   
/*      */   public static String getResizeImgURL(Context mContext, String imgUrl, int width, int height, int MAX_WIDTH, int MAX_HEIGHT) {
/* 1056 */     String str1 = GeneralFunctions.retrieveValue("SERVERURL", mContext);
/* 1057 */     imgUrl = imgUrl.replace(" ", "%20");
/* 1058 */     String str2 = str1.endsWith("/") ? str1 : (str1 + "/");
/* 1059 */     str2 = str2 + "resizeImg.php?src=" + imgUrl + "&w=" + width + "&h=" + height + "&IMG_MAX_WIDTH=" + MAX_WIDTH + "&IMG_MAX_HEIGHT=" + MAX_HEIGHT;
/* 1060 */     return str2;
/*      */   }
/*      */   
/*      */   public static boolean isAppInstalled(Context context, String packageName) {
/*      */     try {
/* 1065 */       context.getPackageManager().getApplicationInfo(packageName, 0);
/* 1066 */       return true;
/* 1067 */     } catch (PackageManager.NameNotFoundException nameNotFoundException) {
/* 1068 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void showSoftKeyboard(Context mContext, EditText view) {
/* 1073 */     if (!(mContext instanceof Activity)) {
/*      */       return;
/*      */     }
/*      */     
/* 1077 */     if (view.requestFocus()) {
/* 1078 */       ((Activity)mContext).getWindow().setSoftInputMode(4);
/*      */       
/* 1080 */       InputMethodManager inputMethodManager = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
/* 1081 */       inputMethodManager.toggleSoftInput(2, 1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void hideSoftKeyboard(Context mContext, View view) {
/* 1087 */     if (!(mContext instanceof Activity)) {
/*      */       return;
/*      */     }
/* 1090 */     ((Activity)mContext).getWindow().setSoftInputMode(2);
/* 1091 */     InputMethodManager inputMethodManager = (InputMethodManager)((Activity)mContext).getSystemService(Context.INPUT_METHOD_SERVICE);
/* 1092 */     inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
/*      */   }
/*      */   
/*      */   public static boolean textIsRTLWords(String text) {
/*      */     try {
/* 1097 */       for (char c : text.toCharArray()) {
/* 1098 */         if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.ARABIC || (Build.VERSION.SDK_INT >= 19 && Character.UnicodeBlock.of(c) == Character.UnicodeBlock.OLD_PERSIAN) || Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HEBREW) {
/* 1099 */           return true;
/*      */         }
/*      */       } 
/*      */       
/* 1103 */       for (byte b = 0; b < Character.codePointCount(text, 0, text.length()); b++) {
/* 1104 */         int i = text.codePointAt(b);
/* 1105 */         if ((i >= 1536 && i <= 1791) || i == 64394 || i == 1662 || i == 1670 || i == 1711)
/* 1106 */           return true; 
/*      */       } 
/* 1108 */     } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */     
/* 1112 */     return false;
/*      */   }
/*      */   
/*      */   public static void setBottomLineColor(Context mContext, View view, int colorOfBottomLine) {
/* 1116 */     if (view == null) {
/*      */       return;
/*      */     }
/* 1119 */     Drawable drawable = view.getBackground();
/* 1120 */     drawable = DrawableCompat.wrap(drawable);
/* 1121 */     DrawableCompat.setTint(drawable, mContext.getResources().getColor(colorOfBottomLine));
/* 1122 */     view.setBackground(drawable);
/*      */   }
/*      */   
/*      */   public static int dpToPx(float dp, Context context) {
/* 1126 */     return dpToPx(dp, context.getResources());
/*      */   }
/*      */   
/*      */   public static int dpToPx(float dp, Resources resources) {
/* 1130 */     float f = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
/* 1131 */     return (int)f;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Bitmap getBitmapFromView(View view) {
/* 1137 */     view.measure(0, 0);
/* 1138 */     view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
/* 1139 */     view.buildDrawingCache();
/*      */     
/* 1141 */     Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
/*      */     
/* 1143 */     Canvas canvas = new Canvas(bitmap);
/*      */     
/* 1145 */     Drawable drawable = view.getBackground();
/* 1146 */     if (drawable != null) {
/*      */       
/* 1148 */       drawable.draw(canvas);
/*      */     } else {
/*      */       
/* 1151 */       canvas.drawColor(-1);
/*      */     } 
/* 1153 */     view.draw(canvas);
/*      */     
/* 1155 */     return bitmap;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getSDKInt() {
/* 1160 */     return Build.VERSION.SDK_INT;
/*      */   }
/*      */   
/*      */   public static boolean isValidImageResolution(String path) {
/* 1164 */     BitmapFactory.Options options = new BitmapFactory.Options();
/* 1165 */     options.inJustDecodeBounds = true;
/*      */     
/* 1167 */     BitmapFactory.decodeFile(path, options);
/* 1168 */     int i = options.outWidth;
/* 1169 */     int j = options.outHeight;
/*      */     
/* 1171 */     if (i >= 256 && j >= 256) {
/* 1172 */       return true;
/*      */     }
/* 1174 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sendBroadCast(Context mContext, String action) {
/* 1179 */     Intent intent = new Intent(action);
/* 1180 */     mContext.sendBroadcast(intent);
/*      */   }
/*      */   
/*      */   public static boolean isMyServiceRunning(Class<?> serviceClass, Context context) {
/* 1184 */     ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
/* 1185 */     for (ActivityManager.RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(2147483647)) {
/* 1186 */       if (serviceClass.getName().equals(runningServiceInfo.service.getClassName())) {
/* 1187 */         return true;
/*      */       }
/*      */     } 
/* 1190 */     return false;
/*      */   }
/*      */   
/*      */   public static String[] generateImageParams(String key, String content) {
/* 1194 */     String[] arrayOfString = new String[2];
/* 1195 */     arrayOfString[0] = key;
/* 1196 */     arrayOfString[1] = content;
/*      */     
/* 1198 */     return arrayOfString;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String formatDate(String inputFormat, String outputFormat, String inputDate) {
/* 1203 */     Date date = null;
/* 1204 */     String str = "";
/*      */     
/* 1206 */     SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(inputFormat, Locale.getDefault());
/* 1207 */     SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(outputFormat, Locale.getDefault());
/*      */     
/*      */     try {
/* 1210 */       date = simpleDateFormat1.parse(inputDate);
/* 1211 */       str = simpleDateFormat2.format(date);
/*      */     }
/* 1213 */     catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */     
/* 1217 */     return str;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void vibratePhone(Context mContext, long milliSeconds) {
/*      */     try {
/* 1223 */       Vibrator vibrator = (Vibrator)mContext.getSystemService(Context.VIBRATOR_SERVICE);
/*      */       
/* 1225 */       if (Build.VERSION.SDK_INT >= 26) {
/* 1226 */         vibrator.vibrate(VibrationEffect.createOneShot(milliSeconds, -1));
/*      */       } else {
/* 1228 */         vibrator.vibrate(milliSeconds);
/*      */       } 
/* 1230 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void playNotificationSound(Context mContext) {
/*      */     try {
/* 1237 */       Uri uri = RingtoneManager.getDefaultUri(2);
/* 1238 */       Ringtone ringtone = RingtoneManager.getRingtone(mContext, uri);
/* 1239 */       ringtone.play();
/* 1240 */     } catch (Exception exception) {
/* 1241 */       exception.printStackTrace();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void dismissBackGroundNotification(Context context) {
/* 1246 */     NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
/* 1247 */     notificationManager.cancel(12);
/* 1248 */     notificationManager.cancelAll();
/*      */   }
/*      */   
/*      */   public static boolean isClassExist(String className) {
/*      */     try {
/* 1253 */       Class.forName(className);
/* 1254 */       return true;
/* 1255 */     } catch (Exception exception) {
/* 1256 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean isResourceFileExist(Context mContext, String resourceName, String resourcePath) {
/*      */     try {
/* 1262 */       int i = mContext.getResources().getIdentifier(resourceName, resourcePath, mContext.getPackageName());
/* 1263 */       if (i != 0) {
/* 1264 */         return true;
/*      */       }
/* 1266 */     } catch (Exception exception) {}
/*      */     
/* 1268 */     return false;
/*      */   }
/*      */   
/*      */   public static String toBase64(Bitmap image) {
/* 1272 */     Bitmap bitmap = image;
/* 1273 */     String str = "";
/*      */     
/*      */     try {
/* 1276 */       if (bitmap != null) {
/* 1277 */         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 1278 */         bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
/* 1279 */         byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
/* 1280 */         str = Base64.encodeToString(arrayOfByte, 0);
/*      */       } 
/* 1282 */     } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */     
/* 1286 */     return str;
/*      */   }
/*      */   
/*      */   public static Bitmap fromBase64(String input) {
/*      */     try {
/* 1291 */       byte[] arrayOfByte = Base64.decode(input, 0);
/* 1292 */       if (arrayOfByte != null && arrayOfByte.length > 0) {
/* 1293 */         return BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);
/*      */       }
/* 1295 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/* 1298 */     return null;
/*      */   }
/*      */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/utils/Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */