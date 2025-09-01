/*      */
package com.view.calendarview;
/*      */
/*      */

import android.annotation.SuppressLint;
/*      */ import android.content.Context;
/*      */ import android.content.res.TypedArray;
/*      */ import android.graphics.Color;
/*      */ import android.graphics.ColorFilter;
/*      */ import android.graphics.PorterDuff;
/*      */ import android.graphics.PorterDuffColorFilter;
/*      */ import android.graphics.Typeface;
/*      */ import android.os.Build;
/*      */ import android.util.AttributeSet;
/*      */ import android.view.LayoutInflater;
/*      */ import android.view.View;
/*      */ import android.view.ViewGroup;
/*      */ import android.widget.ImageView;
/*      */ import android.widget.LinearLayout;
/*      */ import android.widget.RelativeLayout;
/*      */ import android.widget.TextView;
/*      */ import com.general.files.GeneralFunctions;
/*      */ import com.paratodo.user.R;
import com.utils.OnSwipeTouchListener;
/*      */ import com.utils.Utils;
/*      */ import com.view.CreateRoundedView;
/*      */ import com.view.MTextView;
/*      */ import java.text.DateFormatSymbols;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import org.json.JSONObject;

/*      */
/*      */
/*      */
/*      */
/*      */ public class CustomCalendarView
        /*      */ extends LinearLayout
        /*      */ {
    /*      */   private Context f;
    /*      */   private View g;
    /*      */   private ImageView h;
    /*      */   private ImageView i;
    /*      */   private ImageView j;
    /*   46 */   public Calendar registrationDateCal = null;

    /*      */
    /*   48 */   public enum DataRepresentStyle {SQUARE, ROUND, BOTTOM_BAR;}

    /*      */
    /*   50 */   private static int k = 0;
    /*   51 */   private static int l = 1;
    /*   52 */   private static int m = 2;
    /*      */
    /*   54 */   private static int n = 0;
    /*   55 */   private static int o = 1;
    /*      */
    /*   57 */   private static int p = 0;
    /*   58 */   private static int q = 1;
    /*      */
    /*   60 */   private static int r = 0;
    /*   61 */   private static int s = 1;
    /*      */
    /*      */   private CalendarListener t;
    /*      */
    /*      */   public Calendar currentCalendar;
    /*      */
    /*      */   private Date u;
    /*      */
    /*      */   private Locale v;
    /*      */   private boolean w = false;
    /*      */   private Date x;
    /*      */   private Typeface y;
    /*   73 */   private int z = 1;
    /*      */
    /*   75 */   private List<DayDecorator> A = null;
    /*      */
    /*      */   private static final String B = "dayOfWeek";
    /*      */
    /*      */   private static final String C = "dayOfMonthText";
    /*      */   private static final String D = "dayOfMonthContainer";
    /*      */   private int E;
    /*      */   private int F;
    /*      */   private int G;
    /*      */   private int H;
    /*      */   private int I;
    /*      */   private int J;
    /*      */   private int K;
    /*      */   private int L;
    /*      */   private int M;
    /*   90 */   private int N = -1;
    /*      */
    /*      */   private int O;
    /*      */   private int P;
    /*      */   private int Q;
    /*      */   private int Rst;
    /*      */   private int S;
    /*      */   private int T;
    /*      */   private int U;
    /*      */   private boolean V;
    /*      */   private int W;
    /*      */   private int aa;
    /*      */   private int ab;
    /*      */   private int ac;
    /*      */   private int ad;
    /*  105 */   private int ae = -1;
    /*  106 */   private int af = -1;
    /*  107 */   private int ag = -1;
    /*      */
    /*      */   private LinearLayout ah;
    /*      */
    /*  111 */   private String ai = "";
    /*      */
    /*  113 */   private String aj = "";
    /*  114 */   private String ak = "";
    /*  115 */   private String al = "";
    /*      */
    /*      */   private boolean am = true;
    /*      */
    /*      */   private boolean an = false;
    /*      */
    /*      */   private boolean ao = false;
    /*      */
    /*      */   private boolean ap = true;
    /*      */   private boolean aq = false;
    /*      */   private float ar;
    /*      */   private float as;
    /*      */   private float at;
    /*      */   private Calendar au;
    /*      */   private View av;
    /*  130 */   private String aw = "";
    /*      */
    /*  132 */   private int ax = 0;
    /*      */
    /*      */   private boolean ay = true;
    /*      */ Calendar a;
    /*      */ String b;
    /*      */ JSONObject c;
    /*      */ GeneralFunctions d;
    /*  139 */ View e = null;
    /*      */
    /*      */   private CalendarEventListener az;
    /*      */
    /*      */   private LinearLayout aA;
    /*      */
    /*      */   private boolean aB = false;
    /*      */
    /*      */   private ArrayList<String> aC;
    /*  148 */   private int aD = -1;
    /*  149 */   private int aE = -1;
    /*  150 */   private int aF = -1;
    /*      */
    /*      */   private RelativeLayout.LayoutParams aG;
    /*      */   private RelativeLayout.LayoutParams aH;
    /*      */   private OnClickListener aI;

    /*      */
    /*      */
    private void b() {
        /*  157 */
        if (this.d == null) {
            /*  158 */
            this.d = new GeneralFunctions(this.f);
            /*      */
        }
        /*      */
        /*  161 */
        this.b = this.d.retrieveValue("User_Profile");
        /*  162 */
        this.c = this.d.getJsonObject(this.b);
        /*      */
        /*      */
        /*  165 */
        String str1 = this.d.retrieveValue("User_Profile");
        /*      */
        /*  167 */
        String str2 = this.d.getJsonValue("RegistrationDate", str1);
        /*      */
        /*  169 */
        if (!this.d.getJsonValueStr("RegistrationDate", this.c).equalsIgnoreCase("")) {
            /*  170 */
            Date date = Utils.convertStringToDate("yyyy-MM-dd", this.d.getJsonValueStr("RegistrationDate", this.c));
            /*      */
            /*  172 */
            Calendar calendar = Calendar.getInstance();
            /*  173 */
            calendar.setTime(date);
            /*  174 */
            this.registrationDateCal = calendar;
            /*      */
        }
        /*      */
    }

    /*      */
    /*      */
    public CustomCalendarView(Context mContext) {
        /*  179 */
        this(mContext, (AttributeSet) null);
        /*  180 */
        this.f = mContext;
        /*      */
        /*  182 */
        if (isInEditMode()) {
            /*      */
            return;
            /*      */
        }
        /*      */
        /*  186 */
        this.d = new GeneralFunctions(mContext);
        /*  187 */
        b();
        /*      */
    }

    /*      */
    /*      */
    public CustomCalendarView(Context mContext, AttributeSet attrs, LinearLayout headerContainerParentView) {
        /*  191 */
        super(mContext, attrs);
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /* 1304 */
        this.aI = new OnClickListener()
                /*      */ {
            /*      */
            /*      */
            public void onClick(View view)
            /*      */ {
                /* 1309 */
                ViewGroup viewGroup = (ViewGroup) view;
                /* 1310 */
                String str = (String) viewGroup.getTag();
                /* 1311 */
                str = str.substring("dayOfMonthContainer".length(), str.length());
                /* 1312 */
                TextView textView = (TextView) view.findViewWithTag("dayOfMonthText" + str);
                /*      */
                /*      */
                /*      */
                /* 1316 */
                Calendar calendar = Calendar.getInstance();
                /* 1317 */
                calendar.setFirstDayOfWeek(getFirstDayOfWeek());
                /* 1318 */
                calendar.setTime(currentCalendar.getTime());
                /* 1319 */
                calendar.set(5, Integer.valueOf(textView.getText().toString()).intValue());
                /*      */
                /* 1321 */
                if (!a(calendar)) {
                    /*      */
                    return;
                    /*      */
                }
                /*      */
                /*      */
                /* 1326 */
                markDayAsCurrentDay(currentCalendar);
                /*      */
                /* 1328 */
                if (a(currentCalendar) == a(calendar)) {
                    /* 1329 */
                    refreshCalendar(calendar);
                    /*      */
                }
                /*      */
                /* 1332 */
                markDayAsSelectedDay(calendar.getTime());
                /*      */
                /* 1334 */
                if (az != null) {
                    /* 1335 */
                    az.onCalendarDateSelected(calendar.getTime());
                    /*      */
                }
                /*      */
                /* 1338 */
                if (t != null)
                    /* 1339 */ t.onDateSelected(calendar.getTime());
            }
        };
        if (isInEditMode()) return;
        this.f = mContext;
        this.ah = headerContainerParentView;
        this.d = new GeneralFunctions(mContext);
        b();
        if (Build.VERSION.SDK_INT >= 3 && isInEditMode()) return;
        a(attrs);
        initCalendar();
    }

    public CustomCalendarView(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        this.aI = new OnClickListener() {
            public void onClick(View view) {
                ViewGroup viewGroup = (ViewGroup) view;
                String str = (String) viewGroup.getTag();
                str = str.substring("dayOfMonthContainer".length(), str.length());
                TextView textView = (TextView) view.findViewWithTag("dayOfMonthText" + str);
                Calendar calendar = Calendar.getInstance();
                calendar.setFirstDayOfWeek(getFirstDayOfWeek());
                calendar.setTime(currentCalendar.getTime());
                calendar.set(5, Integer.valueOf(textView.getText().toString()).intValue());
                if (!a(calendar)) return;
                markDayAsCurrentDay(currentCalendar);
                if (a(currentCalendar) == a(calendar))
                    refreshCalendar(calendar);
                markDayAsSelectedDay(calendar.getTime());
                if (az != null)
                    az.onCalendarDateSelected(calendar.getTime());
                if (t != null)
                    t.onDateSelected(calendar.getTime());
            }
        };
        this.f = mContext;
        if (isInEditMode()) return;
        this.d = new GeneralFunctions(mContext);
        b();
        if (Build.VERSION.SDK_INT >= 3 && isInEditMode()) return;
        a(attrs);
        if (this.am) initCalendar();
    }

    /*      */
    public void initCalendar() {
        c();
        setOnTouchListener((OnTouchListener) new OnSwipeTouchListener(this.f) {
            /*      */
            public void onSwipeRight() {
                performClick();
            }

            /*      */
            public void onSwipeLeft() {
                performClick();
            }
            /*      */
        });
    }

    /*      */
    private void a(AttributeSet paramAttributeSet) {
        TypedArray typedArray = this.f.obtainStyledAttributes(paramAttributeSet, R.styleable.CustomCalendarView, 0, 0);
        this.H = typedArray.getColor(R.styleable.CustomCalendarView_calendarBackgroundColor, getResources().getColor(R.color.white));
        this.S = typedArray.getColor(R.styleable.CustomCalendarView_titleLayoutBackgroundColor, getResources().getColor(R.color.black));
        this.U = typedArray.getColor(R.styleable.CustomCalendarView_calendarTitleTextColor, getResources().getColor(R.color.black));
        this.Q = typedArray.getColor(R.styleable.CustomCalendarView_weekLayoutBackgroundColor, getResources().getColor(R.color.white));
        this.Rst = typedArray.getColor(R.styleable.CustomCalendarView_daysContainerBackgroundColor, getResources().getColor(R.color.white));
        this.W = typedArray.getColor(R.styleable.CustomCalendarView_dayOfWeekTextColor, getResources().getColor(R.color.black));
        this.aa = typedArray.getColor(R.styleable.CustomCalendarView_dayOfMonthTextColor, getResources().getColor(R.color.black));
        this.E = typedArray.getColor(R.styleable.CustomCalendarView_disabledDayBackgroundColor, getResources().getColor(R.color.day_disabled_background_color_prj));
        this.F = typedArray.getColor(R.styleable.CustomCalendarView_disabledDayTextColor, getResources().getColor(R.color.day_disabled_text_color_prj));
        this.G = typedArray.getColor(R.styleable.CustomCalendarView_disabledDayTextColor, getResources().getColor(R.color.day_disabled_text_color_prj));
        this.I = typedArray.getColor(R.styleable.CustomCalendarView_selectedDayBackgroundColor, Color.parseColor("#FFFFFF"));
        this.J = typedArray.getColor(R.styleable.CustomCalendarView_nonActiveDataBGColor, Color.parseColor("#FFFFFF"));
        this.K = typedArray.getColor(R.styleable.CustomCalendarView_nonActiveDataTextColor, Color.parseColor("#FFFFFF"));
        this.L = typedArray.getColor(R.styleable.CustomCalendarView_activeDataBGColor, Color.parseColor("#FFFFFF"));
        this.M = typedArray.getColor(R.styleable.CustomCalendarView_activeDataTextColor, Color.parseColor("#FFFFFF"));
        this.N = typedArray.getInteger(R.styleable.CustomCalendarView_dataRepresentStyle, -1);
        this.O = typedArray.getInteger(R.styleable.CustomCalendarView_selectedDayBackgroundStyle, 0);
        this.P = typedArray.getInteger(R.styleable.CustomCalendarView_headerDateShowType, 0);
        this.ab = typedArray.getInteger(R.styleable.CustomCalendarView_currentDayShowType, 0);
        this.ai = typedArray.getString(R.styleable.CustomCalendarView_headerDateFormat);
        this.aj = typedArray.getString(R.styleable.CustomCalendarView_titleTextTypeFace);
        this.ak = typedArray.getString(R.styleable.CustomCalendarView_weekDaysTypeFace);
        this.al = typedArray.getString(R.styleable.CustomCalendarView_daysTypeFace);
        this.T = typedArray.getColor(R.styleable.CustomCalendarView_selectedDayTextColor, Color.parseColor("#1c1c1c"));
        this.ac = typedArray.getColor(R.styleable.CustomCalendarView_currentDayOfMonthColor, Color.parseColor("#000BFF"));
        this.ad = typedArray.getColor(R.styleable.CustomCalendarView_currentDayOfMonthBackgroundColor, Color.parseColor("#FFFFFF"));
        this.ag = typedArray.getResourceId(R.styleable.CustomCalendarView_arrowImage, -1);
        this.ae = typedArray.getResourceId(R.styleable.CustomCalendarView_leftImage, R.mipmap.ic_arrow_right_prj);
        this.af = typedArray.getResourceId(R.styleable.CustomCalendarView_rightImage, R.mipmap.ic_arrow_right_prj);
        this.an = typedArray.getBoolean(R.styleable.CustomCalendarView_setCurrentDayAsSelected, false);
        this.am = typedArray.getBoolean(R.styleable.CustomCalendarView_initializeOnAttrLoad, true);
        this.ao = typedArray.getBoolean(R.styleable.CustomCalendarView_enableSelectedDayColorChange, false);
        this.ap = typedArray.getBoolean(R.styleable.CustomCalendarView_enableArrowImageRotation, true);
        this.aq = typedArray.getBoolean(R.styleable.CustomCalendarView_newDesignLayout, false);
        this.ar = typedArray.getDimensionPixelSize(R.styleable.CustomCalendarView_sizeOfDayView, getContext().getResources().getDimensionPixelSize(R.dimen._22sdp));
        this.as = typedArray.getDimensionPixelSize(R.styleable.CustomCalendarView_textSizeOfDayView, getContext().getResources().getDimensionPixelSize(R.dimen._14ssp));
        this.at = typedArray.getDimensionPixelSize(R.styleable.CustomCalendarView_textSizeOfWeekDays, getContext().getResources().getDimensionPixelSize(R.dimen._14ssp));
        this.aw = typedArray.getString(R.styleable.CustomCalendarView_cusTypeFace);
        if (this.aj == null) this.aj = "";
        if (this.ak == null) this.ak = "";
        if (this.al == null) this.al = "";
        if (this.aw == null) this.aw = "";
        typedArray.recycle();
    }

    /* 1345 */
    private void c() {
        LayoutInflater layoutInflater = (LayoutInflater) this.f.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.g = layoutInflater.inflate(this.aq ? R.layout.custom_calendar_layout_compact_prj : R.layout.custom_calendar_layout_prj, (ViewGroup) this, true);
        this.aA = (LinearLayout) this.g.findViewById(R.id.titleLayoutContainer);
        if (this.ah != null) {
            int i = getResources().getDimensionPixelOffset(R.dimen._35sdp);
            ViewGroup.LayoutParams layoutParams1 = this.aA.getLayoutParams();
            layoutParams1.height = i;
            this.aA.setLayoutParams(layoutParams1);
            ViewGroup.LayoutParams layoutParams2 = this.ah.getLayoutParams();
            layoutParams2.height = i;
            this.ah.setLayoutParams(layoutParams2);
        }
        Locale locale = (this.f.getResources().getConfiguration()).locale;
        Calendar calendar = Calendar.getInstance(locale);
        setFirstDayOfWeek(1);
        refreshCalendar(calendar);
        this.a = calendar;
    }

    public void setLeftImage(int leftImageResource) {
        if (this.h == null) return;
        this.ae = leftImageResource;
        if (leftImageResource != -1) this.h.setImageResource(leftImageResource);
    }

    public void setRightImage(int rightImageResource) {
        if (this.i == null) return;
        this.af = rightImageResource;
        if (rightImageResource != -1) this.i.setImageResource(rightImageResource);
    }

    public void setArrowImage(int arrowImageResource) {
        if (this.j == null) return;
        this.ag = arrowImageResource;
        if (arrowImageResource != -1) {
            this.j.setImageResource(arrowImageResource);
            this.j.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleLayoutBGColor(int calendarTitleBackgroundColor) {
        if (this.g == null) return;
        this.S = calendarTitleBackgroundColor;
        View view = getTitleLayout();
        if (view != null) view.setBackgroundColor(calendarTitleBackgroundColor);
    }

    public void setWeekLayoutBGColor(int weekLayoutBackgroundColor) {
        if (this.g == null) return;
        this.Q = weekLayoutBackgroundColor;
        View view = this.g.findViewById(R.id.weekLayout);
        if (view != null) view.setBackgroundColor(weekLayoutBackgroundColor);
    }

    public void setDaysContainerLayoutBGColor(int daysContainerBackgroundColor) {
        if (this.g == null) return;
        this.Rst = daysContainerBackgroundColor;
        View view = this.g.findViewById(R.id.daysContainer);
        if (view != null) view.setBackgroundColor(daysContainerBackgroundColor);
    }

    public void hideHeaderView() {
        View view = getTitleLayout();
        if (view != null) view.setVisibility(View.GONE);
    }

    public void showHeaderView() {
        View view = getTitleLayout();
        if (view != null) view.setVisibility(View.VISIBLE);
    }

    public void hideDateSelectionView() {
        if (this.g == null) return;
        View view1 = this.g.findViewById(R.id.weekLayout);
        if (view1 != null) view1.setVisibility(View.GONE);
        View view2 = this.g.findViewById(R.id.daysContainer);
        if (view2 != null) view2.setVisibility(View.GONE);
    }

    public void showDateSelectionView() {
        if (this.g == null) return;
        View view1 = this.g.findViewById(R.id.weekLayout);
        if (view1 != null) view1.setVisibility(View.VISIBLE);
        View view2 = this.g.findViewById(R.id.daysContainer);
        if (view2 != null) view2.setVisibility(View.VISIBLE);
    }

    public void setRightImageTint(int color) {
        if (this.i != null)
            this.i.setColorFilter((ColorFilter) new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
    }

    public void setLeftImageTint(int color) {
        if (this.h != null)
            this.h.setColorFilter((ColorFilter) new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
    }

    public void setArrowImageTint(int color) {
        if (this.j != null)
            this.j.setColorFilter((ColorFilter) new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
    }

    public void setTitleTextColor(int calendarTitleTextColor) {
        if (this.g == null) return;
        this.V = true;
        this.U = calendarTitleTextColor;
        if (getTitleLayout() != null) {
            MTextView mTextView = (MTextView) getTitleLayout().findViewById(R.id.dateTitle);
            if (mTextView != null) mTextView.setTextColor(calendarTitleTextColor);
        }
    }

    public View getHeaderView() {
        return getTitleLayout();
    }

    public View getCalendarContentView() {
        if (this.g == null) return null;
        return this.g.findViewById(R.id.calendarContentView);
    }

    public View getDaysContainerView() {
        if (this.g == null) return null;
        return this.g.findViewById(R.id.daysContainer);
    }

    public void setDataRepresentStyle(DataRepresentStyle drStyle) {
        switch (drStyle.ordinal()) {
            case 1:
                this.N = k;
                break;
            case 2:
                this.N = l;
                break;
            case 3:
                this.N = m;
                break;
        }
    }

    public void setActiveDataColor(int nonActiveDataBGColor, int nonActiveDataTextColor, int activeDataBGColor, int activeDataTextColor) {
        this.J = nonActiveDataBGColor;
        this.K = nonActiveDataTextColor;
        this.L = activeDataBGColor;
        this.M = activeDataTextColor;
    }

    public void setActiveData(String dates_str) {
        if (dates_str == null || dates_str.trim().equalsIgnoreCase("")) {
            this.aC = new ArrayList<>();
            refreshCalendar((Calendar) getCurrentCalendar().clone());
            return;
        }
        String[] arrayOfString = dates_str.replaceAll("\\s+", "").split(",");
        ArrayList<String> arrayList = new ArrayList();
        for (String str : arrayOfString)
            arrayList.add("" + GeneralFunctions.parseIntegerValue(0, str));
        this.aC = arrayList;
        refreshCalendar((Calendar) getCurrentCalendar().clone());
    }

    public List<DayDecorator> getDecorators() {
        return this.A;
    }

    private View getTitleLayout() {
        if (this.ah != null) {
            if (!this.aB) {
                View view = LayoutInflater.from(this.f).inflate(this.aq ? R.layout.custom_calendar_title_layout_compact_prj : R.layout.custom_calendar_title_layout_prj, null);
                view.setLayoutParams((ViewGroup.LayoutParams) new LayoutParams(-1, -2));
                if (this.ah.getChildCount() > 0) this.ah.removeAllViews();
                this.ah.addView(view);
                this.aB = true;
            }
            this.aA.setVisibility(View.GONE);
            return this.ah.getChildAt(0);
        }
        if (!this.aB) {
            View view = LayoutInflater.from(this.f).inflate(this.aq ? R.layout.custom_calendar_title_layout_compact_prj : R.layout.custom_calendar_title_layout_prj, null);
            view.setLayoutParams((ViewGroup.LayoutParams) new LayoutParams(-1, -2));
            if (this.aA.getChildCount() > 0) this.aA.removeAllViews();
            this.aA.addView(view);
            this.aB = true;
        }
        return this.aA.getChildAt(0);
    }

    private void d() {
        View view = getTitleLayout();
        if (view == null) return;
        view.setBackgroundColor(this.S);
        if (this.d == null) this.d = new GeneralFunctions(this.f);
        String str = (new DateFormatSymbols(this.v)).getShortMonths()[this.currentCalendar.get(2)].toString();
        str = str.substring(0, 1).toUpperCase() + str.subSequence(1, str.length());
        MTextView mTextView = (MTextView) view.findViewById(R.id.dateTitle);
        if (this.aj != null && !this.aj.equalsIgnoreCase("")) mTextView.changeTypeFace(this.aj);
        mTextView.setTextColor(this.U);
        mTextView.setText(str + " " + this.d.convertNumberWithRTL(this.currentCalendar.get(1) + ""));
        if (!this.V) mTextView.setTextColor(getResources().getColor(R.color.white));
        if (null != getCustomTypeface()) mTextView.setTypeface(getCustomTypeface(), Typeface.BOLD);
        this.h = (ImageView) view.findViewById(R.id.leftButton);
        this.i = (ImageView) view.findViewById(R.id.rightButton);
        this.j = (ImageView) view.findViewById(R.id.arrowImgView);
        this.av = view.findViewById(R.id.titleContainerView);
        setLeftImage(this.ae);
        setRightImage(this.af);
        setArrowImage(this.ag);
        this.av.setOnClickListener(paramView -> {
            if (this.az != null) this.az.onCalendarTitleViewClick();
        });
        if (this.ap) if (!this.d.isRTLmode()) {
            this.h.setRotation(180.0F);
        } else {
            this.i.setRotation(180.0F);
        }
        this.h.setOnClickListener(paramView -> {
            this.ax--;
            this.currentCalendar = Calendar.getInstance(Locale.getDefault());
            if (this.P == q) {
                this.currentCalendar.add(5, this.ax);
            } else {
                this.currentCalendar.add(2, this.ax);
            }
            if (this.registrationDateCal != null && (this.currentCalendar.after(this.registrationDateCal) || this.currentCalendar.get(2) == this.registrationDateCal.get(2))) {
                this.currentCalendar = Calendar.getInstance(Locale.getDefault());
                if (this.P == q) {
                    this.currentCalendar.add(5, this.ax);
                } else {
                    this.currentCalendar.add(2, this.ax);
                }
                refreshCalendar((Calendar) this.currentCalendar.clone());
                if (this.az != null) this.az.onCalendarMonthChanged(this.currentCalendar.getTime());
                if (this.t != null) this.t.onMonthChanged(this.currentCalendar.getTime());
            } else {
                this.ax++;
            }
        });
        this.i.setOnClickListener(paramView -> {
            this.ax++;
            this.currentCalendar = Calendar.getInstance(Locale.getDefault());
            if (this.P == q) {
                this.currentCalendar.add(5, 1);
            } else {
                this.currentCalendar.add(2, this.ax);
            }
            refreshCalendar((Calendar) this.currentCalendar.clone());
            if (this.currentCalendar.before(this.a) || this.currentCalendar.get(2) == this.a.get(2)) {
                if (this.az != null) this.az.onCalendarMonthChanged(this.currentCalendar.getTime());
                if (this.t != null) this.t.onMonthChanged(this.currentCalendar.getTime());
            } else {
                this.ax--;
                this.currentCalendar = Calendar.getInstance(Locale.getDefault());
                if (this.P == q) {
                    this.currentCalendar.add(5, 1);
                } else {
                    this.currentCalendar.add(2, this.ax);
                }
                refreshCalendar((Calendar) this.currentCalendar.clone());
            }
        });
        if (this.P == q) {
            this.h.setOnClickListener(null);
            this.i.setOnClickListener(null);
            this.h.setOnClickListener(paramView -> {
                if (this.currentCalendar != null) {
                    Calendar calendar = (Calendar) this.currentCalendar.clone();
                    calendar.add(5, -1);
                    if (a(calendar)) {
                        this.currentCalendar.add(5, -1);
                        refreshCalendar((Calendar) this.currentCalendar.clone());
                        markDayAsSelectedDay(((Calendar) this.currentCalendar.clone()).getTime());
                        if (this.az != null)
                            this.az.onCalendarDateSelected(this.currentCalendar.getTime());
                        if (this.t != null) this.t.onDateSelected(this.currentCalendar.getTime());
                    }
                }
            });
            this.i.setOnClickListener(paramView -> {
                if (this.currentCalendar != null) {
                    Calendar calendar = (Calendar) this.currentCalendar.clone();
                    calendar.add(5, 1);
                    if (a(calendar)) {
                        this.currentCalendar.add(5, 1);
                        refreshCalendar((Calendar) this.currentCalendar.clone());
                        markDayAsSelectedDay(((Calendar) this.currentCalendar.clone()).getTime());
                        if (this.az != null)
                            this.az.onCalendarDateSelected(this.currentCalendar.getTime());
                        if (this.t != null) this.t.onDateSelected(this.currentCalendar.getTime());
                    }
                }
            });
        }
    }

    private MTextView getHeaderDateTitleTxtView() {
        if (getTitleLayout() == null) return new MTextView(getContext());
        return (MTextView) getTitleLayout().findViewById(R.id.dateTitle);
    }

    @SuppressLint({"DefaultLocale"})
    private void e() {
        View view = this.g.findViewById(R.id.weekLayout);
        view.setBackgroundColor(this.Q);
        String[] arrayOfString = (new DateFormatSymbols(this.v)).getShortWeekdays();
        for (byte b = 1; b < arrayOfString.length; b++) {
            String str = arrayOfString[b];
            if (str.length() > 3) str = str.substring(0, 3).toUpperCase();
            MTextView mTextView = (MTextView) this.g.findViewWithTag("dayOfWeek" + a(b, this.currentCalendar));
            mTextView.setText(str);
            mTextView.setTextColor(this.W);
            if (null != getCustomTypeface()) mTextView.setTypeface(getCustomTypeface());
            if (this.ak != null && !this.ak.equalsIgnoreCase("")) mTextView.changeTypeFace(this.ak);
            if (this.aq) {
                mTextView.setTextSize(0, this.at);
                mTextView.setIncludeFontPadding(false);
            }
        }
    }

    private void f() {
        View view = this.g.findViewById(R.id.daysContainer);
        view.setBackgroundColor(this.Rst);
        Calendar calendar1 = Calendar.getInstance(this.v);
        calendar1.setTime(this.currentCalendar.getTime());
        calendar1.set(5, 1);
        calendar1.setFirstDayOfWeek(getFirstDayOfWeek());
        int i = calendar1.get(7);
        int j = a(i, calendar1);
        int k = calendar1.getActualMaximum(5);
        Calendar calendar2 = (Calendar) calendar1.clone();
        calendar2.add(5, -(j - 1));
        int m = 42 - k + j - 1;
        Calendar calendar3 = null;
        byte b1 = 0;
        for (byte b2 = 1; b2 < 43; b2++) {
            ViewGroup viewGroup1 = (ViewGroup) this.g.findViewWithTag("dayOfMonthContainer" + b2);
            DayView dayView1 = (DayView) this.g.findViewWithTag("dayOfMonthText" + b2);
            if (dayView1 != null) {
                if (this.aq) {
                    ViewGroup.LayoutParams layoutParams = dayView1.getLayoutParams();
                    layoutParams.width = (int) this.ar;
                    layoutParams.height = (int) this.ar;
                    dayView1.setLayoutParams(layoutParams);
                    dayView1.setTextSize(0, this.as);
                    dayView1.setIncludeFontPadding(false);
                }
                viewGroup1.setOnClickListener(null);
                try {
                    dayView1.changeTypeFace(this.aw);
                } catch (Exception exception) {
                }
                dayView1.bind(calendar2.getTime(), getDecorators());
                dayView1.setVisibility(View.VISIBLE);
                if (null != getCustomTypeface()) dayView1.setTypeface(getCustomTypeface());
                if (this.al != null && !this.al.equalsIgnoreCase(""))
                    dayView1.changeTypeFace(this.al);
                if (this.N != -1 && dayView1.getParent() != null && dayView1.getParent() instanceof RelativeLayout) {
                    RelativeLayout relativeLayout = (RelativeLayout) dayView1.getParent();
                    if (relativeLayout != null) relativeLayout.setBackgroundColor(0);
                    if (relativeLayout != null && relativeLayout.getChildCount() > 1) try {
                        relativeLayout.removeViewAt(1);
                    } catch (Exception exception) {
                    }
                }
                if (isSameMonth(calendar1, calendar2)) {
                    viewGroup1.setOnClickListener(this.aI);
                    dayView1.setBackgroundColor(this.H);
                    dayView1.setTextColor(this.W);
                    boolean bool = markDayAsCurrentDay(calendar2);
                    if (bool) {
                        calendar3 = calendar2;
                        if (this.au == null) this.au = calendar3;
                        if (this.an) markDayAsSelectedDay(this.au.getTime());
                    }
                    ViewGroup viewGroup2 = viewGroup1;
                    if ((this.registrationDateCal != null && calendar2.before(this.registrationDateCal) && !bool) || (calendar3 != null && calendar3.after(calendar1) && !bool)) {
                        dayView1.setOnClickListener(null);
                        dayView1.setTextColor(this.F);
                        dayView1.setBackgroundColor(this.E);
                    } else {
                        dayView1.setOnTouchListener((OnTouchListener) new OnSwipeTouchListener(this.f) {
                            /*      */
                            public void onSwipeRight() {
                                performClick();
                            }

                            public void onSwipeLeft() {
                                performClick();
                            }

                            public void onClick() {
                                performClick();
                            }
                            /*      */
                        });
                        b1++;
                        if (this.N != -1 && dayView1.getParent() != null && dayView1.getParent() instanceof RelativeLayout && !bool) {
                            RelativeLayout relativeLayout = (RelativeLayout) dayView1.getParent();
                            String str = "" + b1;
                            if (this.aD == -1)
                                this.aD = getResources().getDimensionPixelOffset(R.dimen._2sdp);
                            if (this.aE == -1)
                                this.aE = getResources().getDimensionPixelOffset(R.dimen._20sdp);
                            if (this.aF == -1)
                                this.aF = getResources().getDimensionPixelOffset(R.dimen._22sdp);
                            if (relativeLayout != null && relativeLayout.getChildCount() == 1)
                                if (this.N == m) {
                                    View view1 = new View(this.f);
                                    dayView1.measure(0, 0);
                                    if (this.aG == null) {
                                        this.aG = new RelativeLayout.LayoutParams(this.aE, this.aD);
                                        this.aG.topMargin = dayView1.getMeasuredHeight();
                                        this.aG.addRule(14);
                                    }
                                    view1.setLayoutParams((ViewGroup.LayoutParams) this.aG);
                                    relativeLayout.addView(view1);
                                    if (this.aC.contains(str)) {
                                        view1.setBackgroundColor(this.L);
                                    } else {
                                        view1.setBackgroundColor(this.J);
                                    }
                                } else if (this.N == l) {
                                    dayView1.a = true;
                                    boolean bool1 = this.aC.contains(str);
                                    if (bool1) {
                                        dayView1.setBackgroundColor(this.L);
                                        dayView1.setTextColor(this.M);
                                    } else {
                                        dayView1.setBackgroundColor(this.J);
                                        dayView1.setTextColor(this.K);
                                    }
                                } else if (this.N == k) {
                                    if (this.aC.contains(str)) {
                                        relativeLayout.setBackgroundColor(this.L);
                                        dayView1.setTextColor(this.M);
                                    } else {
                                        relativeLayout.setBackgroundColor(this.J);
                                        dayView1.setTextColor(this.K);
                                    }
                                    dayView1.setBackgroundColor(0);
                                }
                        }
                    }
                } else {
                    dayView1.setTextColor(this.F);
                    dayView1.setBackgroundColor(this.E);
                    if (!isOverflowDateVisible()) {
                        dayView1.setVisibility(View.GONE);
                    } else if (b2 >= 36 && m / 7.0F >= 1.0F) {
                        dayView1.setVisibility(View.GONE);
                    }
                    dayView1.setOnTouchListener((OnTouchListener) new OnSwipeTouchListener(this.f) {
                        /*      */
                        public void onSwipeRight() {
                            performClick();
                        }

                        public void onSwipeLeft() {
                            performClick();
                        }
                        /* 1349 */
                    });
                }
                dayView1.decorate();
                calendar2.add(5, 1);
                j++;
            }
        }
        ViewGroup viewGroup = (ViewGroup) this.g.findViewWithTag("weekRow6");
        DayView dayView = (DayView) this.g.findViewWithTag("dayOfMonthText36");
        if (dayView.getVisibility() != View.VISIBLE) {
            viewGroup.setVisibility(View.GONE);
        } else {
            viewGroup.setVisibility(View.VISIBLE);
        }
    }

    private boolean a(Calendar paramCalendar) {
        if (this.registrationDateCal != null && !paramCalendar.before(this.registrationDateCal) && !paramCalendar.after(getTodaysCalendar()))
            return true;
        return false;
    }

    public boolean isSameMonth(Calendar c1, Calendar c2) {
        if (c1 == null || c2 == null) return false;
        return (c1.get(0) == c2.get(0) && c1.get(1) == c2.get(1) && c1.get(2) == c2.get(2));
    }

    public static boolean isToday(Calendar calendar) {
        return isSameDay(calendar, Calendar.getInstance());
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null)
            throw new IllegalArgumentException("The dates must not be null");
        return (cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6));
    }

    private void a(Date paramDate) {
        if (paramDate != null) {
            Calendar calendar = getTodaysCalendar();
            calendar.setFirstDayOfWeek(getFirstDayOfWeek());
            calendar.setTime(paramDate);
            DayView dayView = b(calendar);
            dayView.setBackgroundColor(this.H);
            dayView.setTextColor(this.W);
            dayView.decorate();
        }
    }

    private DayView b(Calendar paramCalendar) {
        return (DayView) a("dayOfMonthText", paramCalendar);
    }

    private int c(Calendar paramCalendar) {
        int i = d(paramCalendar);
        int j = paramCalendar.get(5);
        return j + i;
    }

    private int d(Calendar paramCalendar) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(getFirstDayOfWeek());
        calendar.setTime(paramCalendar.getTime());
        calendar.set(5, 1);
        int i = calendar.getFirstDayOfWeek();
        int j = calendar.get(7);
        if (i == 1) return j - 1;
        if (j == 1) return 6;
        return j - 2;
    }

    private int a(int paramInt, Calendar paramCalendar) {
        int i = paramCalendar.getFirstDayOfWeek();
        if (i == 1) return paramInt;
        if (paramInt == 1) return 7;
        return paramInt - 1;
    }

    private View a(String paramString, Calendar paramCalendar) {
        int i = c(paramCalendar);
        return this.g.findViewWithTag(paramString + i);
    }

    public Calendar getTodaysCalendar() {
        Calendar calendar = Calendar.getInstance((this.f.getResources().getConfiguration()).locale);
        calendar.setFirstDayOfWeek(getFirstDayOfWeek());
        return calendar;
    }

    @SuppressLint({"DefaultLocale"})
    public void refreshCalendar(Calendar currentCalendar) {
        this.currentCalendar = currentCalendar;
        this.currentCalendar.setFirstDayOfWeek(getFirstDayOfWeek());
        this.v = (this.f.getResources().getConfiguration()).locale;
        d();
        g();
    }

    private void g() {
        e();
        f();
    }

    public int getFirstDayOfWeek() {
        return this.z;
    }

    public void setFirstDayOfWeek(int firstDayOfWeek) {
        this.z = firstDayOfWeek;
    }

    public boolean markDayAsCurrentDay(Calendar calendar) {
        if (calendar != null && isToday(calendar)) {
            DayView dayView = b(calendar);
            if (this.ab == r) {
                dayView.setTextColor(this.ac);
                dayView.setBackgroundColor(this.ad);
            }
            if (this.u == null) this.u = calendar.getTime();
            if (this.az != null && !this.w) {
                this.w = true;
                this.az.onCalendarCurrentDayFound(calendar.getTime());
            }
            if (this.O == o && this.ab == r) {
                if (dayView.getMeasuredHeight() < 20) dayView.measure(0, 0);
                new CreateRoundedView(this.ad, dayView.getMeasuredHeight() / 2, 2, this.ad, (View) dayView);
            }
            return true;
        }
        return false;
    }

    public void markDayAsSelectedDay(Date currentDate) {
        Calendar calendar = getTodaysCalendar();
        calendar.setFirstDayOfWeek(getFirstDayOfWeek());
        calendar.setTime(currentDate);
        if (this.x != null) a(this.x);
        b(currentDate);
        if (this.P == q) {
            if (this.ai == null || this.ai.trim().equalsIgnoreCase("")) this.ai = "MMM dd, yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.ai, Locale.getDefault());
            getHeaderDateTitleTxtView().setText(simpleDateFormat.format(getCurrentCalendar().getTime()));
        }
        DayView dayView = b(calendar);
        if (this.ao) {
            dayView.setBackgroundColor(this.I);
            dayView.setTextColor(this.T);
        }
        if (this.O == o) {
            if (dayView.getMeasuredHeight() < 20) dayView.measure(0, 0);
            new CreateRoundedView(this.I, dayView.getMeasuredHeight() / 2, 2, this.I, (View) dayView);
        }
    }

    private void b(Date paramDate) {
        this.x = paramDate;
    }

    public void setCalendarListener(CalendarListener calendarListener) {
        this.t = calendarListener;
    }

    public void setDecorators(List<DayDecorator> decorators) {
        this.A = decorators;
    }

    /*      */
    /*      */
    /*      */
    public boolean isOverflowDateVisible() {
        /* 1353 */
        return this.ay;
        /*      */
    }

    /*      */
    /*      */
    public void setShowOverflowDate(boolean isOverFlowEnabled) {
        /* 1357 */
        this.ay = isOverFlowEnabled;
        /*      */
    }

    /*      */
    /*      */
    public void setCustomTypeface(Typeface customTypeface) {
        /* 1361 */
        this.y = customTypeface;
        /* 1362 */
        refreshCalendar(this.currentCalendar);
        /*      */
    }

    /*      */
    /*      */
    public void setTitleViewTypeFace(String titleTextTypeFace) {
        /* 1366 */
        this.aj = titleTextTypeFace;
        /* 1367 */
        refreshCalendar(this.currentCalendar);
        /*      */
    }

    /*      */
    /*      */
    public void setWeekDaysTypeFace(String weekDaysTypeFace) {
        /* 1371 */
        this.ak = weekDaysTypeFace;
        /* 1372 */
        refreshCalendar(this.currentCalendar);
        /*      */
    }

    /*      */
    /*      */
    public void setDaysTypeFace(String daysTypeFace) {
        /* 1376 */
        this.al = daysTypeFace;
        /* 1377 */
        refreshCalendar(this.currentCalendar);
        /*      */
    }

    /*      */
    /*      */
    public Typeface getCustomTypeface() {
        /* 1381 */
        return this.y;
        /*      */
    }

    /*      */
    /*      */
    public Calendar getCurrentCalendar() {
        /* 1385 */
        return this.currentCalendar;
        /*      */
    }

    /*      */
    /*      */
    public Date getTodayDate() {
        /* 1389 */
        if (this.u == null) {
            /* 1390 */
            return Calendar.getInstance().getTime();
            /*      */
        }
        /* 1392 */
        return this.u;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public void setCalendarEventListener(CalendarEventListener mCalendarEventListener) {
        /* 1410 */
        this.az = mCalendarEventListener;
        /*      */
        /* 1412 */
        if (mCalendarEventListener != null && !this.w && this.u != null) {
            /* 1413 */
            this.w = true;
            /* 1414 */
            mCalendarEventListener.onCalendarCurrentDayFound(this.u);
            /*      */
        }
        /*      */
    }

    /*      */
    /*      */   public static interface CalendarEventListener {
        /*      */     void onCalendarTitleViewClick();

        /*      */
        /*      */     void onCalendarPreviousButtonClick();

        /*      */
        /*      */     void onCalendarNextButtonClick();

        /*      */
        /*      */     void onCalendarDateSelected(Date param1Date);

        /*      */
        /*      */     void onCalendarMonthChanged(Date param1Date);

        /*      */
        /*      */     void onCalendarCurrentDayFound(Date param1Date);
        /*      */
    }
    /*      */
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/calendarview/CustomCalendarView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */