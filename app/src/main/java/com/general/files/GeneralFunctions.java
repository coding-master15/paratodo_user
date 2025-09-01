//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.general.files;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.text.SpannableStringBuilder;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.InfoProvider;
import com.drawRoute.DirectionsJSONParser;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.R.id;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.paratodo.user.R;
import com.rest.RestClient;
import com.utils.Logger;
import com.utils.ScalingUtilities;
import com.utils.ScalingUtilities.ScalingLogic;
import com.utils.Utils;
import com.utils.WeViewFontConfig;
import com.view.ErrorView;
import com.view.ErrorView.Config;
import com.view.GenerateAlertBox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class GeneralFunctions {
    public static final int MY_PERMISSIONS_REQUEST = 51;
    public static final int MY_SETTINGS_REQUEST = 52;
    Context a;
    private String c = "";
    Map<String, Object> b = null;
    private static Map<String, Object> d = null;

    public GeneralFunctions(Context context, int backImgViewID) {
        this.a = context;
        if (Utils.SERVER_CONNECTION_URL.equalsIgnoreCase("")) {
            Log.e("SERVER URL ERROR", "::Utils.SERVER_CONNECTION_URL::must not be blank. Please set it in first line of onCreate Function of MyApp class.");
        }

        if (Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("Yes")) {
            Log.e("LNG_LBL_GN_FN", "RecreateWithBack");
        }

        this.a(this.a);
        this.a(backImgViewID);
    }

    public GeneralFunctions(Context context) {
        this.a = context;
        if (Utils.SERVER_CONNECTION_URL.equalsIgnoreCase("")) {
            Log.e("SERVER URL ERROR", "::Utils.SERVER_CONNECTION_URL::must not be blank. Please set it in first line of onCreate Function of MyApp class.");
        }

        if (Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("Yes")) {
            Log.e("LNG_LBL_GN_FN", "RecreateWithOUTBack");
        }

        this.a(this.a);
    }

    private void a(int var1) {
        if (this.a != null && this.a instanceof Activity) {
            if (!this.retrieveValue(Utils.LANGUAGE_IS_RTL_KEY).equals("") && this.retrieveValue(Utils.LANGUAGE_IS_RTL_KEY).equals(Utils.DATABASE_RTL_STR)) {
                this.forceRTLIfSupported((Activity)this.a);
                View var2 = this.getCurrentView((Activity)this.a);
                if (var2 != null && var2.findViewById(var1) != null && var2.findViewById(var1) instanceof ImageView) {
                    ImageView var3 = (ImageView)var2.findViewById(var1);
                    if (var3.getRotation() != 180.0F) {
                        var3.setRotation(180.0F);
                    }
                }
            } else {
                this.forceLTRIfSupported((Activity)this.a);
            }
        }

    }

    public boolean isRTLmode() {
        String var1 = this.retrieveValue(Utils.LANGUAGE_IS_RTL_KEY);
        return !var1.equals("") && var1.equals(Utils.DATABASE_RTL_STR);
    }

    public void forceRTLIfSupported(Dialog alertDialog) {
        if (alertDialog != null && alertDialog.getWindow() != null) {
            alertDialog.getWindow().getDecorView();
            alertDialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    public void forceLTRIfSupported(Dialog alertDialog) {
        if (alertDialog != null && alertDialog.getWindow() != null) {
            alertDialog.getWindow().getDecorView();
            alertDialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

    }

    public void forceRTLIfSupported(Activity act) {
        if (act != null && act.getWindow() != null) {
            act.getWindow().getDecorView();
            act.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    public void forceLTRIfSupported(Activity act) {
        if (act != null && act.getWindow() != null) {
            act.getWindow().getDecorView();
            act.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

    }

    public void forceRTLIfSupported(AlertDialog alertDialog) {
        if (alertDialog != null && alertDialog.getWindow() != null) {
            alertDialog.getWindow().getDecorView();
            alertDialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    public void forceRTLIfSupported(GenerateAlertBox generateAlert) {
        if (generateAlert != null && generateAlert.alertDialog != null && generateAlert.alertDialog.getWindow() != null && generateAlert.alertDialog.getWindow().getDecorView() != null) {
            generateAlert.alertDialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    public String getHasKey(Context act) {
        try {
            PackageInfo var2 = act.getPackageManager().getPackageInfo(act.getPackageName(), 64);
            Signature[] var3 = var2.signatures;
            int var4 = var3.length;
            byte var5 = 0;
            if (var5 < var4) {
                Signature var6 = var3[var5];
                MessageDigest var7 = MessageDigest.getInstance("SHA");
                var7.update(var6.toByteArray());
                return new String(Base64.encode(var7.digest(), 0));
            }
        } catch (PackageManager.NameNotFoundException var8) {
            System.out.println("name not found:" + var8.toString());
        } catch (NoSuchAlgorithmException var9) {
            System.out.println("no such an algorithm:" + var9.toString());
        } catch (Exception var10) {
            System.out.println("exception:" + var10.toString());
        }

        return "";
    }

    public boolean isLanguageLabelsAvail() {
        SharedPreferences var1 = PreferenceManager.getDefaultSharedPreferences(this.a);
        String var2 = var1.getString(Utils.languageLabelsKey, (String)null);
        return var2 != null && !var2.equals("");
    }

    public String retrieveLangLBl(String orig, String label) {
        return Utils.IS_OPTIMIZE_MODE_ENABLE ? this.a(orig, label) : this.b(orig, label);
    }

    public static void clearLanguageLabelsData() {
        d = null;
    }

    public static void clearAndResetLanguageLabelsData(Context mContext) {
        clearLanguageLabelsData();
        GeneralFunctions var1 = new GeneralFunctions(mContext);
        var1.retrieveLangLBl("", "LBL_BTN_OK_TXT");
    }

    private String a(String var1, String var2) {
        boolean var3 = var1.equals("");
        boolean var4 = var2.startsWith("LBL_");
        if (this.isLanguageLabelsAvail()) {
            if (d == null && this.c.equals("")) {
                this.c = this.retrieveValue(Utils.languageLabelsKey);
            }

            if (d == null && !this.c.equals("")) {
                JSONObject var5 = this.getJsonObject(this.c);
                HashMap var6 = new HashMap();
                if (var5 == null) {
                    return var3 ? (var4 ? var1 : var2) : var1;
                }

                Iterator var7 = var5.keys();
                if (var7 == null) {
                    return var3 ? (var4 ? var1 : var2) : var1;
                }

                String var8;
                Object var9;
                for(; var7.hasNext(); var6.put(var8, var9)) {
                    var8 = (String)var7.next();
                    var9 = this.getJsonValue(var8, var5);
                    if (var9 == null) {
                        var9 = "";
                    } else if (var9 instanceof JSONArray) {
                        var9 = this.toList((JSONArray)var9);
                    } else if (var9 instanceof JSONObject) {
                        var9 = this.toMap((JSONObject)var9);
                    }

                    if (var9 == null) {
                        var9 = "";
                    }
                }

                d = var6;
            }

            if (d != null) {
                if (d.get(var2) != null) {
                    return (String)d.get(var2);
                } else {
                    return var3 ? (var4 ? var1 : var2) : var1;
                }
            } else {
                if (this.c.equals("")) {
                    this.c = this.retrieveValue(Utils.languageLabelsKey);
                }

                if (this.getJsonValue(var2, this.c).equals("")) {
                    return var3 ? (var4 ? var1 : var2) : var1;
                } else {
                    return this.getJsonValue(var2, this.c);
                }
            }
        } else {
            return var3 ? (var4 ? var1 : var2) : var1;
        }
    }

    private String b(String var1, String var2) {
        boolean var3 = var1.equals("");
        boolean var4 = var2.startsWith("LBL_");
        boolean var5 = false;
        String var6 = "LNG_LBL_GN_FN";
        if (Utils.IS_APP_IN_DEBUG_MODE.equalsIgnoreCase("Yes")) {
            var5 = true;
        }

        if (this.isLanguageLabelsAvail()) {
            if (this.b == null && this.c.equals("")) {
                this.c = this.retrieveValue(Utils.languageLabelsKey);
            }

            if (this.b == null && !this.c.equals("")) {
                JSONObject var7 = this.getJsonObject(this.c);
                HashMap var8 = new HashMap();
                if (var7 == null) {
                    return var3 ? (var4 ? var1 : var2) : var1;
                }

                Iterator var9 = var7.keys();
                if (var9 == null) {
                    return var3 ? (var4 ? var1 : var2) : var1;
                }

                String var10;
                Object var11;
                for(; var9.hasNext(); var8.put(var10, var11)) {
                    var10 = (String)var9.next();
                    var11 = this.getJsonValue(var10, var7);
                    if (var11 == null) {
                        var11 = "";
                    } else if (var11 instanceof JSONArray) {
                        var11 = this.toList((JSONArray)var11);
                    } else if (var11 instanceof JSONObject) {
                        var11 = this.toMap((JSONObject)var11);
                    }

                    if (var11 == null) {
                        var11 = "";
                    }
                }

                this.b = var8;
            }

            if (this.b != null) {
                if (this.b.get(var2) != null) {
                    return (String)this.b.get(var2);
                } else {
                    return var3 ? (var4 ? var1 : var2) : var1;
                }
            } else {
                if (this.c.equals("")) {
                    this.c = this.retrieveValue(Utils.languageLabelsKey);
                }

                if (this.getJsonValue(var2, this.c).equals("")) {
                    return var3 ? (var4 ? var1 : var2) : var1;
                } else {
                    return this.getJsonValue(var2, this.c);
                }
            }
        } else {
            return var3 ? (var4 ? var1 : var2) : var1;
        }
    }

    public static boolean canDrawOverlaysUsingReflection(Context context) {
        try {
            AppOpsManager var1 = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            Class var2 = AppOpsManager.class;
            Method var3 = var2.getMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class);
            int var4 = (Integer)var3.invoke(var1, 24, Binder.getCallingUid(), context.getApplicationContext().getPackageName());
            return 0 == var4;
        } catch (Exception var5) {
            return false;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix var2 = new Matrix();
        switch (orientation) {
            case 1:
                return bitmap;
            case 2:
                var2.setScale(-1.0F, 1.0F);
                break;
            case 3:
                var2.setRotate(180.0F);
                break;
            case 4:
                var2.setRotate(180.0F);
                var2.postScale(-1.0F, 1.0F);
                break;
            case 5:
                var2.setRotate(90.0F);
                var2.postScale(-1.0F, 1.0F);
                break;
            case 6:
                var2.setRotate(90.0F);
                break;
            case 7:
                var2.setRotate(-90.0F);
                var2.postScale(-1.0F, 1.0F);
                break;
            case 8:
                var2.setRotate(-90.0F);
                break;
            default:
                return bitmap;
        }

        try {
            Bitmap var3 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), var2, true);
            bitmap.recycle();
            return var3;
        } catch (OutOfMemoryError var4) {
            var4.printStackTrace();
            return bitmap;
        }
    }

    public static Float parseFloatValue(float defaultValue, String strValue) {
        try {
            return Float.parseFloat(strValue);
        } catch (Exception var3) {
            return defaultValue;
        }
    }

    public static Double parseDoubleValue(double defaultValue, String strValue) {
        try {
            return Double.parseDouble(strValue.replace(",", ""));
        } catch (Exception var4) {
            return defaultValue;
        }
    }

    public static int parseIntegerValue(int defaultValue, String strValue) {
        try {
            Log.e("here", strValue);
            return Integer.parseInt(strValue);
        } catch (Exception var3) {
            Log.e("issue", "here");
            return defaultValue;
        }
    }

    public static long parseLongValue(long defaultValue, String strValue) {
        try {
            return Long.parseLong(strValue);
        } catch (Exception var4) {
            return defaultValue;
        }
    }

    public static String convertDecimalPlaceDisplay(double val) {
        return String.format(Locale.getDefault(), "%.2f", val).replace(",", ".");
    }

    public static DecimalFormat decimalFormat() {
        DecimalFormat var0 = new DecimalFormat("#.00");
        DecimalFormatSymbols var1 = DecimalFormatSymbols.getInstance();
        var1.setDecimalSeparator('.');
        var0.setDecimalFormatSymbols(var1);
        return var0;
    }

    public static HashMap<String, String> retrieveValue(HashMap<String, String> keysToRetrieve, Context mContext) {
        SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean var3 = RestClient.isEncAllowed(mContext, Utils.SERVER_CONNECTION_URL);

        String var6;
        String var7;
        for(Iterator var4 = keysToRetrieve.entrySet().iterator(); var4.hasNext(); keysToRetrieve.put(var6, var7)) {
            Map.Entry var5 = (Map.Entry)var4.next();
            var6 = (String)var5.getKey();
            var7 = var2.getString(var6, "");
            if (var7 != null && !var7.equalsIgnoreCase("") && var3) {
                try {
                    var7 = DataHelper.getInstance(mContext).decrypt(var7);
                } catch (Exception var9) {
                }
            }
        }

        return keysToRetrieve;
    }

    public static String retrieveValue(String key, Context mContext) {
        if (key.equalsIgnoreCase("SERVERURL") && !Utils.SERVER_CONNECTION_URL.equalsIgnoreCase("")) {
            return Utils.SERVER_CONNECTION_URL;
        } else {
            SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(mContext);
            String var3 = var2.getString(key, "");
            if (var3 != null && !var3.equalsIgnoreCase("") && RestClient.isEncAllowed(mContext, Utils.SERVER_CONNECTION_URL)) {
                try {
                    var3 = DataHelper.getInstance(mContext).decrypt(var3);
                } catch (Exception var5) {
                }
            }

            return var3;
        }
    }

    public HashMap<String, String> retrieveValue(HashMap<String, String> keysToRetrieve) {
        SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(this.a);
        boolean var3 = RestClient.isEncAllowed(this.a, Utils.SERVER_CONNECTION_URL);

        String var6;
        String var7;
        for(Iterator var4 = keysToRetrieve.entrySet().iterator(); var4.hasNext(); keysToRetrieve.put(var6, var7)) {
            Map.Entry var5 = (Map.Entry)var4.next();
            var6 = (String)var5.getKey();
            var7 = var2.getString(var6, "");
            if (var7 != null && !var7.equalsIgnoreCase("") && var3) {
                try {
                    var7 = DataHelper.getInstance(this.a).decrypt(var7);
                } catch (Exception var9) {
                }
            }
        }

        return keysToRetrieve;
    }

    public String retrieveValue(String key) {
        if (key.equalsIgnoreCase("SERVERURL") && !Utils.SERVER_CONNECTION_URL.equalsIgnoreCase("")) {
            return Utils.SERVER_CONNECTION_URL;
        } else {
            SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(this.a);
            String var3 = var2.getString(key, "");
            if (var3 != null && !var3.equalsIgnoreCase("") && RestClient.isEncAllowed(this.a, Utils.SERVER_CONNECTION_URL)) {
                try {
                    var3 = DataHelper.getInstance(this.a).decrypt(var3);
                } catch (Exception var5) {
                }
            }

            return var3;
        }
    }

    public void storeData(HashMap<String, String> dataMapToStore) {
        SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(this.a);
        SharedPreferences.Editor var3 = var2.edit();
        boolean var4 = RestClient.isEncAllowed(this.a, Utils.SERVER_CONNECTION_URL);

        String var7;
        String var9;
        for(Iterator var5 = dataMapToStore.entrySet().iterator(); var5.hasNext(); var3.putString(var7, var9)) {
            Map.Entry var6 = (Map.Entry)var5.next();
            var7 = (String)var6.getKey();
            String var8 = (String)var6.getValue();
            var9 = var8;
            if (!var8.equalsIgnoreCase("") && var4) {
                try {
                    var9 = DataHelper.getInstance(this.a).encrypt(var8);
                } catch (Exception var11) {
                }
            }
        }

        var3.commit();
    }

    public void storeData(String key, String data) {
        SharedPreferences var3 = PreferenceManager.getDefaultSharedPreferences(this.a);
        SharedPreferences.Editor var4 = var3.edit();
        String var5 = data;
        if (!data.equalsIgnoreCase("") && RestClient.isEncAllowed(this.a, Utils.SERVER_CONNECTION_URL)) {
            try {
                var5 = DataHelper.getInstance(this.a).encrypt(data);
            } catch (Exception var7) {
            }
        }

        var4.putString(key, var5);
        var4.commit();
    }

    public static void storeData(HashMap<String, String> dataMapToStore, Context mContext) {
        SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor var3 = var2.edit();
        boolean var4 = RestClient.isEncAllowed(mContext, Utils.SERVER_CONNECTION_URL);

        String var7;
        String var9;
        for(Iterator var5 = dataMapToStore.entrySet().iterator(); var5.hasNext(); var3.putString(var7, var9)) {
            Map.Entry var6 = (Map.Entry)var5.next();
            var7 = (String)var6.getKey();
            String var8 = (String)var6.getValue();
            var9 = var8;
            if (!var8.equalsIgnoreCase("") && var4) {
                try {
                    var9 = DataHelper.getInstance(mContext).encrypt(var8);
                } catch (Exception var11) {
                }
            }
        }

        var3.commit();
    }

    public static void storeData(String key, String data, Context mContext) {
        SharedPreferences var3 = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor var4 = var3.edit();
        String var5 = data;
        if (!data.equalsIgnoreCase("") && RestClient.isEncAllowed(mContext, Utils.SERVER_CONNECTION_URL)) {
            try {
                var5 = DataHelper.getInstance(mContext).encrypt(data);
            } catch (Exception var7) {
            }
        }

        var4.putString(key, var5);
        var4.commit();
    }

    public void removeValue(Context mContext, ArrayList<String> listOfKeysToRemove) {
        SharedPreferences var3 = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor var4 = var3.edit();
        Iterator var5 = listOfKeysToRemove.iterator();

        while(var5.hasNext()) {
            String var6 = (String)var5.next();
            var4.remove(var6);
        }

        var4.commit();
    }

    public void removeValue(Context mContext, String key) {
        SharedPreferences var3 = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor var4 = var3.edit();
        var4.remove(key);
        var4.commit();
    }

    public void removeValue(ArrayList<String> listOfKeysToRemove) {
        SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(this.a);
        SharedPreferences.Editor var3 = var2.edit();
        Iterator var4 = listOfKeysToRemove.iterator();

        while(var4.hasNext()) {
            String var5 = (String)var4.next();
            var3.remove(var5);
        }

        var3.commit();
    }

    public void removeValue(String key) {
        SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(this.a);
        SharedPreferences.Editor var3 = var2.edit();
        var3.remove(key);
        var3.commit();
    }

    public boolean containsKey(Context mContext, String key) {
        SharedPreferences var3 = PreferenceManager.getDefaultSharedPreferences(mContext);
        String var4 = var3.getString(key, (String)null);
        return var4 != null;
    }

    public boolean containsKey(String key) {
        SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(this.a);
        String var3 = var2.getString(key, (String)null);
        return var3 != null;
    }

    public static boolean prefHasKey(Context mContext, String key) {
        SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(mContext);
        return var2.contains(key);
    }

    public boolean prefHasKey(String key) {
        SharedPreferences var2 = PreferenceManager.getDefaultSharedPreferences(this.a);
        return var2.contains(key);
    }

    public String encryptData(String data) {
        String var2 = "";

        try {
            var2 = DataHelper.getInstance(this.a).encrypt(data);
        } catch (Exception var4) {
            var2 = "";
        }

        return var2;
    }

    public String decryptData(String data) {
        String var2 = "";

        try {
            var2 = DataHelper.getInstance(this.a).decrypt(data);
        } catch (Exception var4) {
            var2 = "";
        }

        return var2;
    }

    public static double calculationByLocation(double lat1, double lon1, double lat2, double lon2, String returnType) {
        short var9 = 6371;
        double var18 = Math.toRadians(lat2 - lat1);
        double var20 = Math.toRadians(lon2 - lon1);
        double var22 = Math.sin(var18 / 2.0) * Math.sin(var18 / 2.0) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(var20 / 2.0) * Math.sin(var20 / 2.0);
        double var24 = 2.0 * Math.asin(Math.sqrt(var22));
        double var26 = (double)var9 * var24;
        double var28 = var26 / 1.0;
        DecimalFormat var30 = new DecimalFormat("####");
        int var31 = Integer.valueOf(var30.format(var28));
        double var32 = var26 % 1000.0;
        if (returnType.equalsIgnoreCase("KM")) {
            return (double)var9 * var24;
        } else {
            return returnType.equalsIgnoreCase("Miles") ? (double)var9 * var24 * 0.621371 : var32;
        }
    }

    private void a(Context var1) {
        if (var1 != null) {
            new InfoProvider(var1);
        }
    }

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        } else {
            long var4 = (long)Math.pow(10.0, (double)places);
            value *= (double)var4;
            long var6 = Math.round(value);
            return (double)var6 / (double)var4;
        }
    }

    public String getDateFormatedType(String date, String originalformate, String targateformate, Locale locale) {
        String var5 = "";
        SimpleDateFormat var6 = new SimpleDateFormat(originalformate, locale);
        SimpleDateFormat var7 = new SimpleDateFormat(targateformate, locale);

        try {
            Date var8 = var6.parse(date);
            var5 = var7.format(var8);
        } catch (ParseException var9) {
            var9.printStackTrace();
        }

        return var5;
    }

    public Typeface getDefaultFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.defaultFont));
    }

    public boolean isPermisionGranted() {
        int var1 = ContextCompat.checkSelfPermission(this.a, "android.permission.WRITE_EXTERNAL_STORAGE");
        int var2 = ContextCompat.checkSelfPermission(this.a, "android.permission.CAMERA");
        return var1 == 0 && var2 == 0;
    }

    public String getTimezone() {
        TimeZone var1 = TimeZone.getDefault();
        return var1.getID() + "";
    }

    public GenerateAlertBox notifyRestartApp(String message) {
        GenerateAlertBox var2 = new GenerateAlertBox(this.a);
        var2.setContentMessage(this.retrieveLangLBl("", "LBL_BTN_TRIP_CANCEL_CONFIRM_TXT"), "");
        var2.setPositiveBtn(this.retrieveLangLBl("Ok", "LBL_BTN_OK_TXT"));
        var2.showAlertBox();
        return var2;
    }

    public void logOUTFrmFB() {
        LoginManager.getInstance().logOut();
    }

    public String wrapHtml(Context context, String html) {
        if (false) {
            try {
                return context.getString(this.isRTLmode() ? R.string.html_rtl : R.string.html, WeViewFontConfig.FONT_FAMILY_NAME, WeViewFontConfig.ASSETS_FONT_NAME, WeViewFontConfig.FONT_SIZE, WeViewFontConfig.FONT_COLOR, html);
            } catch (Exception var4) {
                Log.e("WebViewFontSet", "::" + var4.toString());
                return context.getString(this.isRTLmode() ? R.string.html_rtl : R.string.html, new Object[]{html});
            }
        } else {
            return context.getString(this.isRTLmode() ? R.string.html_rtl : R.string.html, new Object[]{html});
        }
    }

    public String getDateFormatedType(String date, String originalformate, String targateformate) {
        String var4 = "";
        String var5 = this.retrieveValue(Utils.GOOGLE_MAP_LANGUAGE_CODE_KEY);
        if (var5 == null || var5.trim().equalsIgnoreCase("")) {
            var5 = "en";
        }

        Locale var6 = new Locale(var5);
        SimpleDateFormat var7 = new SimpleDateFormat(originalformate);
        SimpleDateFormat var8 = new SimpleDateFormat(targateformate, var6);

        try {
            Date var9 = var7.parse(date);
            var4 = var8.format(var9);
        } catch (ParseException var10) {
            var10.printStackTrace();
        }

        return var4;
    }

    public List<Object> toList(JSONArray array) {
        ArrayList var2 = new ArrayList();

        for(int var3 = 0; var3 < array.length(); ++var3) {
            Object var4 = null;

            try {
                var4 = array.get(var3);
            } catch (JSONException var6) {
                var6.printStackTrace();
            }

            if (var4 != null) {
                if (var4 instanceof JSONArray) {
                    var4 = this.toList((JSONArray)var4);
                } else if (var4 instanceof JSONObject) {
                    var4 = this.toMap((JSONObject)var4);
                }

                var2.add(var4);
            }
        }

        return var2;
    }

    public Map<String, Object> toMap(JSONObject object) {
        HashMap var2 = new HashMap();

        String var4;
        Object var5;
        for(Iterator var3 = object.keys(); var3.hasNext(); var2.put(var4, var5)) {
            var4 = (String)var3.next();
            var5 = this.getJsonValue(var4, object);
            if (var5 instanceof JSONArray) {
                var5 = this.toList((JSONArray)var5);
            } else if (var5 instanceof JSONObject) {
                var5 = this.toMap((JSONObject)var5);
            }
        }

        return var2;
    }

    public static boolean checkDataAvail(String key, String response) {
        if (key != null && response != null) {
            try {
                JSONObject var2 = new JSONObject(response);
                String var3 = var2.getString(key);
                return !var3.equals("0") && var3.equals("1");
            } catch (Exception var4) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkDataAvail(String key, JSONObject response) {
        if (key != null && response != null) {
            try {
                String var2 = response.getString(key);
                return !var2.equals("0") && (var2.equals("1") || var2.equals("1.0"));
            } catch (Exception var3) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isJsonObj(String json) {
        try {
            new JSONObject(json);
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static Object getTypeOfJson(String data) {
        if (data == null) {
            return null;
        } else {
            try {
                Object var1 = (new JSONTokener(data)).nextValue();
                if (var1 instanceof JSONObject) {
                    return new JsonObject();
                }

                if (var1 instanceof JSONArray) {
                    return new JsonArray();
                }
            } catch (Exception var2) {
            }

            return null;
        }
    }

    public static boolean isJSONValid(String test) {
        if (test == null) {
            return false;
        } else {
            try {
                new JSONObject(test);
            } catch (Exception var4) {
                try {
                    new JSONArray(test);
                } catch (Exception var3) {
                    return false;
                }
            }

            return true;
        }
    }

    public Object getJsonValue(String key, JSONObject response) {
        if (response == null) {
            return "";
        } else {
            try {
                Object var3 = response.get(key);
                return var3 != null && !var3.equals("null") && !var3.equals("") ? var3 : "";
            } catch (Exception var4) {
                return "";
            }
        }
    }

    public String getJsonValueStr(String key, JSONObject response) {
        if (key != null && response != null) {
            try {
                String var3 = null;
                if (response.has(key)) {
                    var3 = response.getString(key);
                }

                return var3 != null && !var3.equals("null") && !var3.equals("") ? var3 : "";
            } catch (Exception var4) {
                return "";
            }
        } else {
            return "";
        }
    }

    public Object getValueFromJsonArr(JSONArray arr, int position) {
        if (arr == null) {
            return "";
        } else {
            try {
                return arr.get(position);
            } catch (Exception var4) {
                return "";
            }
        }
    }

    public String getJsonValue(String key, String response) {
        if (key != null && response != null) {
            try {
                JSONObject var3 = new JSONObject(response);
                if (!var3.isNull(key)) {
                    String var4 = var3.getString(key);
                    if (var4 != null && !var4.equals("null") && !var4.equals("")) {
                        return var4;
                    }
                }

                return "";
            } catch (Exception var5) {
                Log.e("sccc", var5.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }

    public int getIntValue(String key, String response) {
        if (key != null && response != null) {
            try {
                JSONObject var3 = new JSONObject(response);
                if (!var3.isNull(key)) {
                    Integer var4 = var3.getInt(key);
                    if (var4 != null) {
                        return var4;
                    }
                }

                return 0;
            } catch (Exception var5) {
                Log.e("sccc", var5.getMessage());
                return 0;
            }
        } else {
            return 0;
        }
    }

    public JSONArray getJsonArray(String key, String response) {
        if (key != null && response != null) {
            try {
                JSONObject var3 = new JSONObject(response);
                return var3.getJSONArray(key);
            } catch (JSONException var4) {
                return null;
            }
        } else {
            return null;
        }
    }

    public JSONArray getJsonArray(String key, JSONObject obj) {
        if (key != null && obj != null) {
            try {
                return obj.getJSONArray(key);
            } catch (Exception var4) {
                return null;
            }
        } else {
            return null;
        }
    }

    public JSONArray getJsonArray(String response) {
        if (response == null) {
            return null;
        } else {
            try {
                return new JSONArray(response);
            } catch (JSONException var3) {
                return null;
            }
        }
    }

    public JSONObject getJsonObject(JSONArray arr, int position) {
        if (arr == null) {
            return null;
        } else {
            try {
                return arr.getJSONObject(position);
            } catch (Exception var4) {
                return null;
            }
        }
    }

    public Object getJsonValue(JSONArray arr, int position) {
        if (arr == null) {
            return null;
        } else {
            try {
                return arr.get(position);
            } catch (Exception var4) {
                return null;
            }
        }
    }

    public JSONObject getJsonObject(String key, JSONObject obj) {
        if (key != null && obj != null) {
            try {
                return obj.getJSONObject(key);
            } catch (JSONException var4) {
                return null;
            }
        } else {
            return null;
        }
    }

    public JSONObject getJsonObject(String data) {
        if (data == null) {
            return null;
        } else {
            try {
                return new JSONObject(data);
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public JSONObject getJsonObject(String key, String response) {
        if (key != null && response != null) {
            try {
                JSONObject var3 = new JSONObject(response);
                JSONObject var4 = var3.getJSONObject(key);
                return var4 != null && !var4.equals("null") && !var4.equals("") ? var4 : null;
            } catch (Exception var5) {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean isJSONkeyAvail(String key, String response) {
        try {
            JSONObject var3 = new JSONObject(response);
            return var3.has(key) && !var3.isNull(key);
        } catch (Exception var4) {
            return false;
        }
    }

    public boolean isJSONArrKeyAvail(String key, String response) {
        if (key != null && response != null) {
            try {
                JSONObject var3 = new JSONObject(response);
                return var3.optJSONArray(key) != null;
            } catch (Exception var4) {
                return false;
            }
        } else {
            return false;
        }
    }

    public String generateDeviceToken() {
        if (!this.checkPlayServices()) {
            Logger.e("hello4", "hello4");
            return "";
        } else {
            final String[] var1 = {""};

            try {
                Task<String> task = FirebaseMessaging.getInstance().getToken();
                task.addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        Logger.e("hello5", task.getResult());
                        var1[0] = task.getResult();
                    }
                });
            } catch (Exception var3) {
                var3.printStackTrace();
                var1[0] = "";
            }
            Logger.e("hello4", var1[0]);

            return var1[0];


        }
    }

    public boolean checkPlayServices() {
        GoogleApiAvailability var1 = GoogleApiAvailability.getInstance();
        int var2 = var1.isGooglePlayServicesAvailable(this.a);
        if (var2 != 0) {
            if (var1.isUserResolvableError(var2)) {
                ((Activity)this.a).runOnUiThread(() -> {
                    var1.getErrorDialog((Activity)this.a, var2, 9000).show();
                });
            }

            return false;
        } else {
            return true;
        }
    }

    public String addSemiColonToPrice(String value) {
        return NumberFormat.getNumberInstance(Locale.US).format((long)parseIntegerValue(0, value));
    }

    public void storeUserData(String memberId) {
        this.storeData(Utils.iMemberId_KEY, memberId);
        this.storeData(Utils.isUserLogIn, "1");
    }

    public String getMemberId() {
        return this.isUserLoggedIn() ? this.retrieveValue(Utils.iMemberId_KEY) : "";
    }

    public String getServiceId() {
        return this.retrieveValue(Utils.iServiceId_KEY);
    }

    public boolean isReferralSchemeEnable() {
        String var1 = this.retrieveValue(Utils.REFERRAL_SCHEME_ENABLE);
        return !var1.equals("") && var1.equalsIgnoreCase("Yes");
    }

    public void logOutUser(Object calleeObj) {
        if (calleeObj != null && calleeObj.getClass().getSimpleName().equalsIgnoreCase("MyApp")) {
            this.removeValue(Utils.iMemberId_KEY);
            this.removeValue(Utils.isUserLogIn);
            this.removeValue(Utils.iHotelId_KEY);
            this.removeValue("User_Profile");
            this.removeValue("DeliverList");
            this.removeValue(Utils.WORKLOCATION);
            this.removeValue(Utils.SELECT_ADDRESS_ID);
            this.removeValue("DEFAULT_SERVICE_CATEGORY_ID");
            this.removeValue("DeliveryListMultiJson");
            this.removeValue("DeliveryListAll");
            this.removeValue("DeliveryListJSonMulti");
            this.removeValue("userHomeLocationLatitude");
            this.removeValue("userHomeLocationLongitude");
            this.removeValue("userHomeLocationAddress");
            this.removeValue("userWorkLocationLatitude");
            this.removeValue("userWorkLocationLongitude");
            this.removeValue("userWorkLocationAddress");
            this.removeValue("LIST_CONTACTS");
            this.removeValue("BFSE_SELECTED_CONTACT");

            Realm var2;
            try {
                var2 = Realm.getInstance((new RealmConfiguration.Builder()).deleteRealmIfMigrationNeeded().build());
                var2.beginTransaction();
                var2.deleteAll();
                var2.commitTransaction();
            } catch (Exception var4) {
                System.out.println("RealmDeleteError:" + var4.getMessage());
            }

            try {
                var2 = Realm.getDefaultInstance();
                var2.beginTransaction();
                var2.deleteAll();
                var2.commitTransaction();
            } catch (Exception var3) {
                System.out.println("RealmDeleteError:" + var3.getMessage());
            }
        } else {
            System.out.println("LogOutError:This method should not be used from anywhere. If you want to logout from device use method (logOutFromDevice) of MyApp:");
        }

    }

    public boolean isUserLoggedIn() {
        String var1 = this.retrieveValue(Utils.isUserLogIn);
        String var2 = this.retrieveValue(Utils.iMemberId_KEY);
        return !var1.equals("") && var1.equals("1") && !var2.trim().equals("");
    }

    public void sendHeartBeat() {
        this.a.sendBroadcast(new Intent("com.google.android.intent.action.GTALK_HEARTBEAT"));
        this.a.sendBroadcast(new Intent("com.google.android.intent.action.MCS_HEARTBEAT"));
    }

    public boolean isEmailValid(String email) {
        boolean var2 = false;
        String var3 = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,20}";
        String var4 = email.trim();
        Pattern var5 = Pattern.compile(var3, 2);
        Matcher var6 = var5.matcher(var4);
        if (var6.matches()) {
            var2 = true;
        }

        return var2;
    }

    @SuppressLint("ResourceType")
    public void generateErrorView(ErrorView errorView, String title, String subTitle) {
        errorView.setConfig(Config.create().title("").titleColor(this.a.getResources().getColor(17170444)).subtitle(this.retrieveLangLBl("", subTitle)).retryText(this.retrieveLangLBl("Retry", "LBL_RETRY_TXT")).retryTextColor(this.a.getResources().getColor(R.color.error_view_retry_btn_txt_color)).build());
    }

    public void showError() {
        InternetConnection var1 = new InternetConnection(this.a);
        String var2 = !var1.isNetworkConnected() && !var1.check_int() ? this.retrieveLangLBl("No Internet Connection", "LBL_NO_INTERNET_TXT") : this.retrieveLangLBl("Please try again.", "LBL_TRY_AGAIN_TXT");
        GenerateAlertBox var3 = new GenerateAlertBox(this.a);
        var3.setContentMessage("", var2);
        var3.setPositiveBtn(this.retrieveLangLBl("Ok", "LBL_BTN_OK_TXT"));
        var3.showAlertBox();
    }

    public void showError(boolean isClose) {
        InternetConnection var2 = new InternetConnection(this.a);
        String var3 = !var2.isNetworkConnected() && !var2.check_int() ? this.retrieveLangLBl("No Internet Connection", "LBL_NO_INTERNET_TXT") : this.retrieveLangLBl("Please try again.", "LBL_TRY_AGAIN_TXT");
        GenerateAlertBox var4 = new GenerateAlertBox(this.a);
        var4.setContentMessage("", var3);
        var4.setPositiveBtn(this.retrieveLangLBl("Ok", "LBL_BTN_OK_TXT"));
        var4.setBtnClickList((var2x) -> {
            if (isClose && this.a instanceof Activity) {
                ((Activity)this.a).onBackPressed();
            }

        });
        var4.showAlertBox();
    }

    public void showError(OnAlertButtonClickListener onAlertButtonClickListener) {
        InternetConnection var2 = new InternetConnection(this.a);
        String var3 = !var2.isNetworkConnected() && !var2.check_int() ? this.retrieveLangLBl("No Internet Connection", "LBL_NO_INTERNET_TXT") : this.retrieveLangLBl("Please try again.", "LBL_TRY_AGAIN_TXT");
        GenerateAlertBox var4 = new GenerateAlertBox(this.a);
        var4.setContentMessage("", var3);
        var4.setBtnClickList((var2x) -> {
            var4.closeAlertBox();
            if (onAlertButtonClickListener != null) {
                onAlertButtonClickListener.onAlertButtonClick(var2x);
            }

        });
        var4.setPositiveBtn(this.retrieveLangLBl("Ok", "LBL_BTN_OK_TXT"));
        var4.showAlertBox();
    }

    public GenerateAlertBox showGeneralMessage(String title, String message) {
        try {
            GenerateAlertBox var3 = new GenerateAlertBox(this.a);
            var3.setContentMessage(title, message);
            var3.setPositiveBtn(this.retrieveLangLBl("Ok", "LBL_BTN_OK_TXT"));
            var3.showAlertBox();
            return var3;
        } catch (Exception var4) {
            return null;
        }
    }

    public void showGeneralMessage(String title, String message, OnAlertButtonClickListener onAlertButtonClickListener) {
        try {
            GenerateAlertBox var4 = new GenerateAlertBox(this.a);
            var4.setContentMessage(title, message);
            var4.setPositiveBtn(this.retrieveLangLBl("Ok", "LBL_BTN_OK_TXT"));
            var4.setBtnClickList((var2) -> {
                var4.closeAlertBox();
                if (onAlertButtonClickListener != null) {
                    onAlertButtonClickListener.onAlertButtonClick(var2);
                }

            });
            var4.showAlertBox();
        } catch (Exception var5) {
        }

    }

    public void showGeneralMessage(String title, String message, boolean isCloseScreen) {
        try {
            GenerateAlertBox var4 = new GenerateAlertBox(this.a);
            var4.setContentMessage(title, message);
            var4.setPositiveBtn(this.retrieveLangLBl("Ok", "LBL_BTN_OK_TXT"));
            var4.setBtnClickList((var3) -> {
                var4.closeAlertBox();
                if (this.a instanceof Activity && isCloseScreen) {
                    ((Activity)this.a).onBackPressed();
                }

            });
            var4.showAlertBox();
        } catch (Exception var5) {
        }

    }

    public GenerateAlertBox showGeneralMessage(String title, String message, String negativeButton, String positiveButton, OnAlertButtonClickListener onAlertButtonClickListener) {
        try {
            GenerateAlertBox var6 = new GenerateAlertBox(this.a);
            var6.setContentMessage(title, message);
            var6.setNegativeBtn(negativeButton);
            var6.setPositiveBtn(positiveButton);
            var6.setBtnClickList((var2) -> {
                var6.closeAlertBox();
                if (onAlertButtonClickListener != null) {
                    onAlertButtonClickListener.onAlertButtonClick(var2);
                }

            });
            var6.showAlertBox();
            return var6;
        } catch (Exception var7) {
            return null;
        }
    }

    public GenerateAlertBox showGeneralMessage(String title, String message, String negativeButton, String positiveButton, String neutralButton, OnAlertButtonClickListener onAlertButtonClickListener) {
        try {
            GenerateAlertBox var7 = new GenerateAlertBox(this.a);
            var7.setContentMessage(title, message);
            var7.setNegativeBtn(negativeButton);
            var7.setPositiveBtn(positiveButton);
            var7.setNeutralBtn(neutralButton);
            var7.setBtnClickList((var2) -> {
                var7.closeAlertBox();
                if (onAlertButtonClickListener != null) {
                    onAlertButtonClickListener.onAlertButtonClick(var2);
                }

            });
            var7.showAlertBox();
            return var7;
        } catch (Exception var8) {
            return null;
        }
    }

    public boolean isLocationEnabled() {
        int var1 = 0;
        try {
            var1 = Secure.getInt(this.a.getContentResolver(), "location_mode");
        } catch (Settings.SettingNotFoundException var5) {
            var5.printStackTrace();
        }

        LocationManager var3 = (LocationManager)this.a.getSystemService(Context.LOCATION_SERVICE);
        boolean var4 = var3.isProviderEnabled("gps");
        return var1 != 0 && var4;
    }

    public boolean checkLocationPermission(boolean isPermissionDialogShown) {
        int var2 = ContextCompat.checkSelfPermission(this.a, "android.permission.ACCESS_FINE_LOCATION");
        int var3 = ContextCompat.checkSelfPermission(this.a, "android.permission.ACCESS_COARSE_LOCATION");
        if (var2 == 0 && var3 == 0) {
            return true;
        } else {
            if (!isPermissionDialogShown) {
                if (!(this.a instanceof Activity)) {
                    System.out.println("Context must be an instance of an activity.");
                } else {
                    ActivityCompat.requestPermissions((Activity)this.a, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 51);
                }
            }

            return false;
        }
    }

    public boolean checkLocationPermission(Context mContext, boolean isPermissionDialogShown) {
        int var3 = ContextCompat.checkSelfPermission(mContext, "android.permission.ACCESS_FINE_LOCATION");
        int var4 = ContextCompat.checkSelfPermission(mContext, "android.permission.ACCESS_COARSE_LOCATION");
        if (var3 == 0 && var4 == 0) {
            return true;
        } else {
            if (!isPermissionDialogShown) {
                if (!(mContext instanceof Activity)) {
                    System.out.println("Context must be an instance of an activity.");
                } else {
                    ActivityCompat.requestPermissions((Activity)mContext, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 51);
                }
            }

            return false;
        }
    }

    public boolean isStoragePermissionGranted() {
        if (VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this.a, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                return true;
            } else {
                if (this.a instanceof Activity) {
                    ActivityCompat.requestPermissions((Activity)this.a, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 51);
                }

                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isCameraPermissionGranted() {
        if (VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this.a, "android.permission.CAMERA") == 0) {
                return true;
            } else {
                if (this.a instanceof Activity) {
                    ActivityCompat.requestPermissions((Activity)this.a, new String[]{"android.permission.CAMERA"}, 51);
                }

                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isCallPermissionGranted(boolean openDialog) {
        int var2 = ContextCompat.checkSelfPermission(this.a, "android.permission.RECORD_AUDIO");
        int var3 = ContextCompat.checkSelfPermission(this.a, "android.permission.READ_PHONE_STATE");
        if (var2 == 0 && var3 == 0) {
            return true;
        } else {
            if (openDialog) {
                if (!(this.a instanceof Activity)) {
                    System.out.println("Context must be an instance of an activity.");
                } else {
                    ActivityCompat.requestPermissions((Activity)this.a, new String[]{"android.permission.READ_PHONE_STATE", "android.permission.RECORD_AUDIO"}, 51);
                }
            }

            return false;
        }
    }

    public boolean isLocationPermissionGranted(boolean openDialog) {
        int var2 = ContextCompat.checkSelfPermission(this.a, "android.permission.ACCESS_FINE_LOCATION");
        int var3 = ContextCompat.checkSelfPermission(this.a, "android.permission.ACCESS_COARSE_LOCATION");
        int var4 = ContextCompat.checkSelfPermission(this.a, "android.permission.WRITE_EXTERNAL_STORAGE");
        int var5 = ContextCompat.checkSelfPermission(this.a, "android.permission.CAMERA");
        if (var2 == 0 && var3 == 0) {
            return true;
        } else {
            if (openDialog) {
                if (!(this.a instanceof Activity)) {
                    System.out.println("Context must be an instance of an activity.");
                } else {
                    ActivityCompat.requestPermissions((Activity)this.a, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 51);
                }
            }

            return false;
        }
    }

    public String[] generateImageParams(String key, String content) {
        String[] var3 = new String[]{key, content};
        return var3;
    }

    public boolean isAllPermissionGranted(boolean openDialog, ArrayList<String> otherPermissionsArr) {
        boolean var3 = false;

        for (String var5 : otherPermissionsArr) {
            int isAllowed = ContextCompat.checkSelfPermission(this.a, var5);
            if (isAllowed != PackageManager.PERMISSION_GRANTED) {
                Logger.e("permission", var5);
                var3 = true;
                break;
            }
        }

        if (var3) {
            if (openDialog) {
                if (!(this.a instanceof Activity)) {
                    System.out.println("Context must be an instance of an activity.");
                } else {
                    ActivityCompat.requestPermissions((Activity)this.a, (String[])otherPermissionsArr.toArray(new String[otherPermissionsArr.size()]), 51);
                }
            }

            return false;
        } else {
            return true;
        }
    }

    public boolean isAllPermissionGranted(boolean openDialog) {
        int var2 = ContextCompat.checkSelfPermission(this.a, "android.permission.ACCESS_FINE_LOCATION");
        int var3 = ContextCompat.checkSelfPermission(this.a, "android.permission.ACCESS_COARSE_LOCATION");
        if (var2 == 0 && var3 == 0) {
            return true;
        } else {
            if (openDialog) {
                if (!(this.a instanceof Activity)) {
                    System.out.println("Context must be an instance of an activity.");
                } else {
                    ActivityCompat.requestPermissions((Activity)this.a, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 51);
                }
            }

            return false;
        }
    }

    public boolean isCameraStoragePermissionGranted() {
        int var1 = ContextCompat.checkSelfPermission(this.a, "android.permission.WRITE_EXTERNAL_STORAGE");
        int var2 = ContextCompat.checkSelfPermission(this.a, "android.permission.CAMERA");
        if (var1 == 0 && var2 == 0) {
            return true;
        } else {
            if (!(this.a instanceof Activity)) {
                System.out.println("Context must be an instance of an activity.");
            } else {
                boolean var3 = !this.retrieveValue("CAMERA_PERMISSION_ASKED").trim().equalsIgnoreCase("Yes") || ActivityCompat.shouldShowRequestPermissionRationale((Activity)this.a, "android.permission.WRITE_EXTERNAL_STORAGE") && ActivityCompat.shouldShowRequestPermissionRationale((Activity)this.a, "android.permission.CAMERA");
                if (var3) {
                    this.storeData("CAMERA_PERMISSION_ASKED", "Yes");
                    ActivityCompat.requestPermissions((Activity)this.a, new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}, 51);
                } else {
                    this.showGeneralMessage("", this.retrieveLangLBl("We need camera and storage permission to perform necessary  task. Please permit mentioned permissions  through Settings screen.", "LBL_NOTIFY_PERMISSION_CAMERA"), this.retrieveLangLBl("Cancel", "LBL_CANCEL_TXT"), this.retrieveLangLBl("Ok", "LBL_BTN_OK_TXT"), (var1x) -> {
                        if (var1x == 1) {
                            this.openSettings(false);
                        }

                    });
                }
            }

            return false;
        }
    }

    public void openSettings(boolean isOpenForResult) {
        if (this.a instanceof Activity) {
            Utils.hideKeyboard((Activity)this.a);
            Intent var2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            Uri var3 = Uri.fromParts("package", this.a.getApplicationInfo().packageName, (String)null);
            var2.setData(var3);
            if (isOpenForResult) {
                ((Activity)this.a).startActivityForResult(var2, 52);
            } else {
                ((Activity)this.a).startActivity(var2);
            }
        }

    }

    public void openSettings() {
        if (this.a instanceof Activity) {
            Utils.hideKeyboard((Activity)this.a);
            Intent var1 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            Uri var2 = Uri.fromParts("package", this.a.getApplicationInfo().packageName, (String)null);
            var1.setData(var2);
            ((Activity)this.a).startActivityForResult(var1, 52);
        }

    }

    public GenerateAlertBox notifyRestartApp() {
        GenerateAlertBox var1 = new GenerateAlertBox(this.a);
        var1.setContentMessage(this.retrieveLangLBl("", "LBL_BTN_TRIP_CANCEL_CONFIRM_TXT"), this.retrieveLangLBl("In order to apply changes restarting app is required. Please wait.", "LBL_NOTIFY_RESTART_APP_TO_CHANGE"));
        var1.setPositiveBtn(this.retrieveLangLBl("Ok", "LBL_BTN_OK_TXT"));
        var1.showAlertBox();
        return var1;
    }

    public GenerateAlertBox notifyRestartApp(String title, String contentMsg) {
        GenerateAlertBox var3 = new GenerateAlertBox(this.a);
        var3.setContentMessage(title, contentMsg);
        var3.setPositiveBtn(this.retrieveLangLBl("Ok", "LBL_BTN_OK_TXT"));
        var3.showAlertBox();
        return var3;
    }

    public void restartApp() {
        try {
            if (this.a instanceof Activity) {
                (new StartActProcess(this.a)).startAct(Utils.getLauncherIntent(this.a));
                ((Activity)this.a).setResult(0);

                try {
                    ActivityCompat.finishAffinity((Activity)this.a);
                } catch (Exception var2) {
                    Log.e("error", Objects.requireNonNull(var2.getMessage()));
                }
            } else {
                (new StartActProcess(this.a)).startAct(Utils.getLauncherIntent(this.a, true));
            }
        } catch (Exception var3) {
            Log.e("error", Objects.requireNonNull(var3.getMessage()));
        }

        Utils.runGC();
    }

    @SuppressLint("ResourceType")
    public View getCurrentView(Activity act) {
        return act.findViewById(16908290);
    }

    public void showMessage(View view, String message) {
        Snackbar var3 = Snackbar.make(view, message, 0);
        View var4 = var3.getView();

        try {
            TextView var5 = (TextView)var4.findViewById(id.snackbar_text);
            var5.setMaxLines(5);
        } catch (Exception var6) {
        }

        var3.show();
    }

    public void showMessage(View view, String message, int textColor) {
        Snackbar var4 = Snackbar.make(view, message, 0);
        View var5 = var4.getView();

        try {
            TextView var6 = (TextView)var5.findViewById(id.snackbar_text);
            var6.setMaxLines(5);
            var6.setTextColor(textColor);
        } catch (Exception var7) {
        }

        var4.show();
    }

    public String getSelectedCarTypeData(String selectedCarTypeId, String jsonArrKey, String dataKey, String json) {
        JSONArray var5 = this.getJsonArray(jsonArrKey, json);

        for(int var6 = 0; var6 < var5.length(); ++var6) {
            JSONObject var7 = this.getJsonObject(var5, var6);
            String var8 = this.getJsonValueStr("iVehicleTypeId", var7);
            if (var8.equals(selectedCarTypeId)) {
                return this.getJsonValue(dataKey, var7.toString());
            }
        }

        return "";
    }

    public String getSelectedCarTypeData(String selectedCarTypeId, ArrayList<HashMap<String, String>> dataList, String dataKey) {
        for(int var4 = 0; var4 < dataList.size(); ++var4) {
            HashMap var5 = (HashMap)dataList.get(var4);
            String var6 = (String)var5.get("iVehicleTypeId");
            if (var6.equals(selectedCarTypeId)) {
                return (String)var5.get(dataKey);
            }
        }

        return "";
    }

    public void deleteTripStatusMessages() {
        SharedPreferences var1 = PreferenceManager.getDefaultSharedPreferences(this.a);
        Map var2 = var1.getAll();
        Iterator var3 = var2.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry var4 = (Map.Entry)var3.next();
            if (((String)var4.getKey()).contains("TRIP_STATUS_MSG_")) {
                Long var5 = System.currentTimeMillis() - 86400000L;
                long var6 = parseLongValue(0L, var4.getValue().toString());
                if (var5 >= var6) {
                    this.removeValue((String)var4.getKey());
                }
            }
        }

    }

    public PolylineOptions getGoogleRouteOptions(String directionJson, int width, int color) {
        PolylineOptions var4 = new PolylineOptions();

        try {
            DirectionsJSONParser var5 = new DirectionsJSONParser();
            List var6 = var5.parse(new JSONObject(directionJson));
            ArrayList var7 = new ArrayList();
            if (var6.size() <= 0) {
                return null;
            } else {
                List var8 = (List)var6.get(0);

                for(int var9 = 0; var9 < var8.size(); ++var9) {
                    HashMap var10 = (HashMap)var8.get(var9);
                    double var11 = Double.parseDouble((String)var10.get("lat"));
                    double var13 = Double.parseDouble((String)var10.get("lng"));
                    LatLng var15 = new LatLng(var11, var13);
                    var7.add(var15);
                }

                var4.addAll(var7);
                var4.width((float)width);
                var4.color(color);
                return var4;
            }
        } catch (Exception var16) {
            return null;
        }
    }

    public void verifyMobile(Bundle bn, Fragment fragment, Class classToOpen) {
        GenerateAlertBox var4 = new GenerateAlertBox(this.a);
        var4.setCancelable(false);
        var4.setBtnClickList((var5) -> {
            var4.closeAlertBox();
            if (var5 != 0) {
                if (fragment == null) {
                    (new StartActProcess(this.a)).startActForResult(classToOpen, bn, 52);
                } else {
                    (new StartActProcess(this.a)).startActForResult(fragment, classToOpen, 52, bn);
                }

            }
        });
        var4.setContentMessage("", this.retrieveLangLBl("", "LBL_VERIFY_MOBILE_CONFIRM_MSG"));
        var4.setPositiveBtn(this.retrieveLangLBl("", "LBL_BTN_OK_TXT"));
        var4.setNegativeBtn(this.retrieveLangLBl("", "LBL_CANCEL_TXT"));
        var4.showAlertBox();
    }

    public boolean canDrawOverlayViews(Context con) {
        if (VERSION.SDK_INT < 21) {
            return true;
        } else {
            try {
                return VERSION.SDK_INT >= 23 ? Settings.canDrawOverlays(con) : canDrawOverlaysUsingReflection(con);
            } catch (NoSuchMethodError var3) {
                return canDrawOverlaysUsingReflection(con);
            }
        }
    }

    public String decodeFile(String path, int DESIREDWIDTH, int DESIREDHEIGHT, String tempImgName) {
        String var5 = null;
        Bitmap var6 = null;

        try {
            int var7 = Utils.getExifRotation(path);
            Bitmap var8 = ScalingUtilities.decodeFile(path, DESIREDWIDTH, DESIREDHEIGHT, ScalingLogic.CROP);
            if (var8.getWidth() <= DESIREDWIDTH && var8.getHeight() <= DESIREDHEIGHT) {
                if (var8.getWidth() > var8.getHeight()) {
                    var6 = ScalingUtilities.createScaledBitmap(var8, var8.getHeight(), var8.getHeight(), ScalingLogic.CROP);
                } else {
                    var6 = ScalingUtilities.createScaledBitmap(var8, var8.getWidth(), var8.getWidth(), ScalingLogic.CROP);
                }
            } else {
                var6 = ScalingUtilities.createScaledBitmap(var8, DESIREDWIDTH, DESIREDHEIGHT, ScalingLogic.CROP);
            }

            var6 = rotateBitmap(var6, var7);
            String var9 = Environment.getExternalStorageDirectory().toString();
            File var10 = new File(var9 + "/" + "TempImages");
            if (!var10.exists()) {
                var10.mkdir();
            }

            File var11 = new File(var10.getAbsolutePath(), tempImgName);
            var5 = var11.getAbsolutePath();
            FileOutputStream var12 = null;

            try {
                var12 = new FileOutputStream(var11);
                var6.compress(CompressFormat.JPEG, 60, var12);
                var12.flush();
                var12.close();
            } catch (FileNotFoundException var14) {
                var14.printStackTrace();
            } catch (Exception var15) {
                var15.printStackTrace();
            }

            var6.recycle();
        } catch (Throwable var16) {
        }

        return var5 == null ? path : var5;
    }

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager var2 = (ActivityManager)this.a.getSystemService(Context.ACTIVITY_SERVICE);
        Iterator var3 = var2.getRunningServices(Integer.MAX_VALUE).iterator();

        ActivityManager.RunningServiceInfo var4;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            var4 = (ActivityManager.RunningServiceInfo)var3.next();
        } while(!serviceClass.getName().equals(var4.service.getClassName()));

        return true;
    }

    public Bitmap writeTextOnDrawable(Context mContext, int drawableId, String text, boolean iswhite, int fontPathToLoad) {
        Bitmap var6 = BitmapFactory.decodeResource(mContext.getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);
        Typeface var7 = Typeface.createFromAsset(mContext.getAssets(), mContext.getResources().getString(fontPathToLoad));
        Paint var8 = new Paint();
        var8.setStyle(Style.FILL);
        if (iswhite) {
            var8.setColor(-1);
        } else {
            var8.setColor(-16777216);
        }

        var8.setTypeface(var7);
        var8.setTextAlign(Align.CENTER);
        var8.setTextSize((float)Utils.dipToPixels(mContext, 14.0F));
        Rect var9 = new Rect();
        var8.getTextBounds(text, 0, text.length(), var9);
        Canvas var10 = new Canvas(var6);
        if (var9.width() >= var10.getWidth() - 4) {
            var8.setTextSize((float)Utils.dipToPixels(mContext, 14.0F));
        }

        int var11 = var10.getWidth() / 2 - 2;
        int var12 = (int)((float)(var10.getHeight() / 4) - (var8.descent() + var8.ascent()) / 2.0F);
        String[] var13 = text.split("\n");
        int var14 = var13.length;

        for(int var15 = 0; var15 < var14; ++var15) {
            String var16 = var13[var15];
            var10.drawText(var16, (float)var11, (float)var12, var8);
            var8.setTextSize((float)Utils.dipToPixels(mContext, 14.0F));
            var12 = (int)((float)var12 + (var8.descent() - var8.ascent()));
        }

        return var6;
    }

    public void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore, final int color, final int dimension, final ResizableTexViewClickListener listener) {
        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }

        ViewTreeObserver var8 = tv.getViewTreeObserver();
        var8.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ViewTreeObserver var1 = tv.getViewTreeObserver();
                var1.removeGlobalOnLayoutListener(this);
                int var2;
                String var3;
                if (maxLine == 0) {
                    var2 = tv.getLayout().getLineEnd(0);
                    var3 = tv.getText().subSequence(0, var2 - expandText.length() + 1) + " " + expandText;
                    tv.setText(var3);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(GeneralFunctions.this.a(tv.getText().toString(), tv, maxLine, expandText, viewMore, color, dimension, listener), BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    var2 = tv.getLayout().getLineEnd(maxLine - 1);
                    var3 = tv.getText().subSequence(0, var2 - expandText.length() + 1) + " " + expandText;
                    tv.setText(var3);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(GeneralFunctions.this.a(tv.getText().toString(), tv, maxLine, expandText, viewMore, color, dimension, listener), BufferType.SPANNABLE);
                } else if (tv.getLineCount() > maxLine) {
                    var2 = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    var3 = tv.getText().subSequence(0, var2) + " " + expandText;
                    tv.setText(var3);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(GeneralFunctions.this.a(tv.getText().toString(), tv, var2, expandText, viewMore, color, dimension, listener), BufferType.SPANNABLE);
                }

            }
        });
    }

    public void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore, final int color, final int dimension) {
        if (tv.getTag() == null) {
            HashMap var7 = new HashMap();
            var7.put("TitleOfTextView", tv.getText().toString());
            var7.put("OrigMaxLines", "" + maxLine);
            tv.setTag(var7);
        }

        ViewTreeObserver var8 = tv.getViewTreeObserver();
        var8.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ViewTreeObserver var1 = tv.getViewTreeObserver();
                var1.removeGlobalOnLayoutListener(this);
                int var2;
                String var3;
                if (maxLine == 0) {
                    var2 = tv.getLayout().getLineEnd(0);
                    var3 = tv.getText().subSequence(0, var2 - expandText.length() + 1) + " " + expandText;
                    tv.setText(var3);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(GeneralFunctions.this.a(tv.getText().toString(), tv, maxLine, expandText, viewMore, color, dimension, (ResizableTexViewClickListener)null), BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    var2 = tv.getLayout().getLineEnd(maxLine - 1);
                    var3 = tv.getText().subSequence(0, var2 - expandText.length() + 1) + " " + expandText;
                    tv.setText(var3);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(GeneralFunctions.this.a(tv.getText().toString(), tv, maxLine, expandText, viewMore, color, dimension, (ResizableTexViewClickListener)null), BufferType.SPANNABLE);
                } else if (tv.getLineCount() > maxLine) {
                    var2 = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    var3 = tv.getText().subSequence(0, var2) + " " + expandText;
                    tv.setText(var3);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(GeneralFunctions.this.a(tv.getText().toString(), tv, var2, expandText, viewMore, color, dimension, (ResizableTexViewClickListener)null), BufferType.SPANNABLE);
                }

            }
        });
    }

    private SpannableStringBuilder a(String var1, final TextView var2, int var3, String var4, final boolean var5, int var6, int var7, final ResizableTexViewClickListener var8) {
        SpannableStringBuilder var9 = new SpannableStringBuilder(var1);
        if (var1.contains(var4)) {
            var9.setSpan(new MyClickableSpan(this.a, var6, var7) {
                public void onClick(View widget) {
                    if (var8 != null) {
                        var8.onResizableTextViewClick(var5);
                    } else {
                        if (var2.getTag() != null && var2.getTag() instanceof HashMap) {
                            HashMap var2x = (HashMap)var2.getTag();
                            if (var5) {
                                var2.setLayoutParams(var2.getLayoutParams());
                                var2.setText(var2x.get("TitleOfTextView").toString(), BufferType.SPANNABLE);
                                var2.invalidate();
                                GeneralFunctions.this.makeTextViewResizable(var2, -5, "\n- " + GeneralFunctions.this.retrieveLangLBl("Less", "LBL_LESS_TXT"), false, this.f, this.g);
                            } else {
                                var2.setLayoutParams(var2.getLayoutParams());
                                var2.setText(var2x.get("TitleOfTextView").toString(), BufferType.SPANNABLE);
                                var2.invalidate();
                                GeneralFunctions.this.makeTextViewResizable(var2, GeneralFunctions.parseIntegerValue(2, var2x.get("OrigMaxLines").toString()), "...\n+ " + GeneralFunctions.this.retrieveLangLBl("View More", "LBL_VIEW_MORE_TXT"), true, this.f, this.g);
                            }
                        }

                    }
                }
            }, var1.indexOf(var4), var1.indexOf(var4) + var4.length(), 0);
        }

        return var9;
    }

    public void saveGoOnlineInfo() {
        this.storeData(Utils.GO_ONLINE_KEY, "Yes");
        this.storeData(Utils.LAST_FINISH_TRIP_TIME_KEY, "" + Calendar.getInstance().getTimeInMillis());
    }

    public String getLocationUpdateChannel() {
        return "ONLINE_DRIVER_LOC_" + this.getMemberId();
    }

    public String buildLocationJson(Location location) {
        if (location != null) {
            try {
                JSONObject var2 = new JSONObject();
                var2.put("MsgType", "LocationUpdate");
                var2.put("iDriverId", this.getMemberId());
                var2.put("vLatitude", location.getLatitude());
                var2.put("vLongitude", location.getLongitude());
                var2.put("ChannelName", this.getLocationUpdateChannel());
                var2.put("LocTime", System.currentTimeMillis() + "");
                return var2.toString();
            } catch (Exception var3) {
                return "";
            }
        } else {
            return "";
        }
    }

    public String buildLocationJson(Location location, String msgType) {
        if (location != null) {
            try {
                JSONObject var3 = new JSONObject();
                var3.put("MsgType", msgType);
                var3.put("iDriverId", this.getMemberId());
                var3.put("vLatitude", location.getLatitude());
                var3.put("vLongitude", location.getLongitude());
                var3.put("ChannelName", this.getLocationUpdateChannel());
                var3.put("LocTime", System.currentTimeMillis() + "");
                return var3.toString();
            } catch (Exception var4) {
                return "";
            }
        } else {
            return "";
        }
    }

    public String buildRequestCancelJson(String iUserId, String vMsgCode) {
        try {
            JSONObject var3 = new JSONObject();
            var3.put("MsgType", "TripRequestCancel");
            var3.put("Message", "TripRequestCancel");
            var3.put("iDriverId", this.getMemberId());
            var3.put("iUserId", iUserId);
            var3.put("iTripId", vMsgCode);
            return var3.toString();
        } catch (Exception var4) {
            return "";
        }
    }

    public String convertNumberWithRTL(String data) {
        String var2 = "";
        NumberFormat var3 = null;

        try {
            String var4 = this.retrieveValue(Utils.GOOGLE_MAP_LANGUAGE_CODE_KEY);
            if (var4 == null || var4.trim().equalsIgnoreCase("")) {
                var4 = "en";
            }

            Locale var5 = new Locale(var4);
            var3 = NumberFormat.getInstance(var5);
            if (data != null && !data.equals("")) {
                for(int var6 = 0; var6 < data.length(); ++var6) {
                    char var7 = data.charAt(var6);
                    if (Character.isDigit(var7)) {
                        var2 = var2 + var3.format((long)Integer.parseInt(String.valueOf(var7)));
                    } else {
                        var2 = var2 + var7;
                    }
                }
            }

            return var2;
        } catch (Exception var8) {
            System.out.println("Exception umber: " + var8.toString());
            return data;
        }
    }

    public String getCurrentdate() {
        Calendar var1 = Calendar.getInstance();
        SimpleDateFormat var2 = new SimpleDateFormat(Utils.dateFormateForBooking);
        return var2.format(var1.getTime());
    }

    public String formatUpto2Digit(float discount) {
        return "" + (double)Math.round((double)discount * 100.0) / 100.0;
    }

    public String formatUpto2Digit(double discount) {
        return "" + (double)Math.round(discount * 100.0) / 100.0;
    }

    public Bundle createChatBundle(JSONObject obj_msg) {
        Bundle var2 = new Bundle();
        var2.putString("iFromMemberId", this.getJsonValueStr("iFromMemberId", obj_msg));
        var2.putString("FromMemberImageName", this.getJsonValueStr("FromMemberImageName", obj_msg));
        var2.putString("iTripId", this.getJsonValueStr("iTripId", obj_msg));
        var2.putString("FromMemberName", this.getJsonValueStr("FromMemberName", obj_msg));
        var2.putString("vBookingNo", this.getJsonValueStr("vBookingNo", obj_msg));
        return var2;
    }

    private JSONArray a(JSONArray var1, JSONArray var2) throws JSONException {
        JSONArray var3 = new JSONArray();

        int var4;
        for(var4 = 0; var4 < var1.length(); ++var4) {
            var3.put(var1.get(var4));
        }

        for(var4 = 0; var4 < var2.length(); ++var4) {
            var3.put(var2.get(var4));
        }

        return var3;
    }

    public boolean isDeliverOnlyEnabled() {
        return this.retrieveValue(Utils.ONLYDELIVERALL_KEY).equalsIgnoreCase("Yes");
    }

    public boolean isAnyDeliverOptionEnabled() {
        return this.retrieveValue(Utils.ONLYDELIVERALL_KEY).equalsIgnoreCase("Yes") || this.retrieveValue(Utils.DELIVERALL_KEY).equalsIgnoreCase("Yes");
    }

    public void removeAllRealmData(Realm realm) {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public void resetMultiStoredDetails() {
        if (this.prefHasKey("DeliveryListMultiJson")) {
            this.removeValue("DeliveryListMultiJson");
        }

        if (this.prefHasKey("DeliveryListAll")) {
            this.removeValue("DeliveryListAll");
        }

        if (this.prefHasKey("DeliveryListJSonMulti")) {
            this.removeValue("DeliveryListJSonMulti");
        }

    }

    public void resetStoredDetails() {
        if (this.prefHasKey("DeliveryListMultiJson")) {
            this.removeValue("DeliveryListMultiJson");
        }

        if (this.prefHasKey("DeliveryListAll")) {
            this.removeValue("DeliveryListAll");
        }

        if (this.prefHasKey("DeliveryListJSonMulti")) {
            this.removeValue("DeliveryListJSonMulti");
        }

        this.removeValue("LIST_CONTACTS");
        this.removeValue("BFSE_SELECTED_CONTACT");
    }

    public JSONArray getJsonArray(JSONArray arr, int position) {
        try {
            return arr.getJSONArray(position);
        } catch (JSONException var4) {
            return null;
        }
    }

    public String getCurrentDayName() {
        Calendar var1 = Calendar.getInstance();
        new SimpleDateFormat(Utils.dateFormateForBooking);
        return (String)DateFormat.format("EEEE", var1.getTime());
    }

    public String getCurrentDateHourMin() {
        Calendar var1 = Calendar.getInstance();
        SimpleDateFormat var2 = new SimpleDateFormat(Utils.OriginalDateFormate);
        return var2.format(var1.getTime());
    }

    public String getCurrentGregorianDateHourMin() {
        Calendar var1 = Calendar.getInstance(Locale.ENGLISH);
        SimpleDateFormat var2 = new SimpleDateFormat(Utils.OriginalDateFormate, Locale.ENGLISH);
        return var2.format(var1.getTime());
    }

    public boolean isMultiDelivery() {
        String var1 = this.retrieveValue(Utils.ENABLE_MULTI_DELIVERY_KEY);
        boolean var2 = false;
        if (Utils.checkText(var1) && var1.equalsIgnoreCase("Yes")) {
            var2 = true;
        }

        return var2;
    }

    public String getHotelId() {
        return this.retrieveValue(Utils.iHotelId_KEY);
    }

    public void storeHotelData(String memberId) {
        this.storeData(Utils.iHotelId_KEY, memberId);
        this.storeData(Utils.isUserLogIn, "1");
    }

    public interface OnAlertButtonClickListener {
        void onAlertButtonClick(int var1);
    }

    public interface ResizableTexViewClickListener {
        void onResizableTextViewClick(boolean var1);
    }
}
