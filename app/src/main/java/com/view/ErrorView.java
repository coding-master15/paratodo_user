/*     */ package com.view;
/*     */ 
/*     */ import android.animation.LayoutTransition;
/*     */ import android.content.Context;
/*     */ import android.content.res.TypedArray;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Build;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.TextView;

import com.paratodo.user.R;


/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ErrorView
/*     */   extends LinearLayout
/*     */ {
/*     */   public static final int ALIGNMENT_LEFT = 0;
/*     */   public static final int ALIGNMENT_CENTER = 1;
/*     */   public static final int ALIGNMENT_RIGHT = 2;
/*     */   private ImageView a;
/*     */   private TextView b;
/*     */   private TextView c;
/*     */   private TextView d;
/*     */   private RetryListener e;
/*     */   
/*     */   public ErrorView(Context context) {
/*  57 */     this(context, (AttributeSet)null);
/*     */   }
/*     */   
/*     */   public ErrorView(Context context, AttributeSet attrs) {
/*  61 */     this(context, attrs, R.attr.ev_style);
/*     */   }
/*     */   
/*     */   public ErrorView(Context context, AttributeSet attrs, int defStyle) {
/*  65 */     this(context, attrs, defStyle, 0);
/*     */   }
/*     */   
/*     */   public ErrorView(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
/*  69 */     super(context, attrs);
/*  70 */     a(attrs, defStyle, defStyleRes);
/*     */   }
/*     */   
/*     */   private void a(AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
/*  74 */     TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.ErrorView, paramInt1, paramInt2);
/*     */ 
/*     */     
/*  77 */     LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
/*  78 */     layoutInflater.inflate(R.layout.error_view_layout, (ViewGroup)this, true);
/*  79 */     setOrientation(LinearLayout.VERTICAL);
/*  80 */     setGravity(17);
/*     */ 
/*     */     
/*  83 */     /*  84 */
    setLayoutTransition(new LayoutTransition());
    /*     */
    /*     */
/*  87 */     this.a = (ImageView)findViewById(R.id.error_image);
/*  88 */     this.b = (TextView)findViewById(R.id.error_title);
/*  89 */     this.c = (TextView)findViewById(R.id.error_subtitle);
/*  90 */     this.d = (TextView)findViewById(R.id.error_retry);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 109 */       int i = typedArray.getResourceId(R.styleable.ErrorView_ev_errorImage, R.mipmap.error_view_cloud);
/* 110 */       String str1 = typedArray.getString(R.styleable.ErrorView_ev_title);
/* 111 */       int j = typedArray.getColor(R.styleable.ErrorView_ev_titleColor, 
/* 112 */           getResources().getColor(R.color.error_view_text));
/* 113 */       String str2 = typedArray.getString(R.styleable.ErrorView_ev_subtitle);
/* 114 */       int k = typedArray.getColor(R.styleable.ErrorView_ev_subtitleColor, 
/* 115 */           getResources().getColor(R.color.error_view_text_light));
/* 116 */       boolean bool1 = typedArray.getBoolean(R.styleable.ErrorView_ev_showTitle, true);
/* 117 */       boolean bool2 = typedArray.getBoolean(R.styleable.ErrorView_ev_showSubtitle, true);
/* 118 */       boolean bool3 = typedArray.getBoolean(R.styleable.ErrorView_ev_showRetryButton, true);
/* 119 */       String str3 = typedArray.getString(R.styleable.ErrorView_ev_retryButtonText);
/* 120 */       int m = typedArray.getResourceId(R.styleable.ErrorView_ev_retryButtonBackground, R.drawable.error_view_retry_button_background);
/*     */       
/* 122 */       int n = typedArray.getColor(R.styleable.ErrorView_ev_retryButtonTextColor,
/* 123 */           getResources().getColor(R.color.error_view_text_dark));
/* 124 */       int i1 = typedArray.getInt(R.styleable.ErrorView_ev_subtitleAlignment, 1);
/*     */       
/* 126 */       if (i != 0) {
/* 127 */         setImage(i);
/*     */       }
/*     */       
/* 130 */       if (str1 != null) {
/* 131 */         setTitle(str1);
/*     */       }
/*     */       
/* 134 */       if (str2 != null) {
/* 135 */         setSubtitle(str2);
/*     */       }
/*     */       
/* 138 */       if (str3 != null) {
/* 139 */         this.d.setText(str3);
/*     */       }
/*     */       
/* 142 */       if (!bool1) {
/* 143 */         this.b.setVisibility(View.GONE);
/*     */       }
/*     */       
/* 146 */       if (!bool2) {
/* 147 */         this.c.setVisibility(View.GONE);
/*     */       }
/*     */       
/* 150 */       if (!bool3) {
/* 151 */         this.d.setVisibility(View.GONE);
/*     */       }
/*     */       
/* 154 */       this.b.setTextColor(j);
/* 155 */       this.c.setTextColor(k);
/*     */       
/* 157 */       this.d.setTextColor(n);
/* 158 */       this.d.setBackgroundResource(m);
/*     */       
/* 160 */       setSubtitleAlignment(i1);
/*     */     } finally {
/* 162 */       typedArray.recycle();
/*     */     } 
/*     */     
/* 165 */     this.d.setOnClickListener(new OnClickListener()
/*     */         {
/*     */           public void onClick(View view) {
/* 168 */             if (e != null) {
/* 169 */               e.onRetry();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOnRetryListener(RetryListener listener) {
/* 182 */     this.e = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Config getConfig() {
/* 202 */     return Config.create()
/* 203 */       .image(getImage())
/* 204 */       .title(getTitle())
/* 205 */       .titleColor(getTitleColor())
/* 206 */       .subtitle(getSubtitle())
/* 207 */       .subtitleColor(getSubtitleColor())
/* 208 */       .retryVisible(isRetryButtonVisible())
/* 209 */       .retryText(getRetryButtonText())
/* 210 */       .retryTextColor(getRetryButtonTextColor())
/* 211 */       .build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConfig(Config config) {
/* 220 */     if (config.getImage() != null) {
/* 221 */       Object object = config.getImage();
/* 222 */       if (object instanceof Integer) {
/* 223 */         setImage(((Integer)object).intValue());
/* 224 */       } else if (object instanceof Drawable) {
/* 225 */         setImage((Drawable)object);
/* 226 */       } else if (object instanceof Bitmap) {
/* 227 */         setImage((Bitmap)object);
/*     */       } 
/*     */     } 
/*     */     
/* 231 */     if (config.getTitle() != null) {
/* 232 */       setTitle(config.getTitle());
/*     */     }
/*     */     
/* 235 */     if (config.getTitleColor() != 0) {
/* 236 */       setTitleColor(config.getTitleColor());
/*     */     }
/*     */     
/* 239 */     if (config.getSubtitle() != null) {
/* 240 */       setSubtitle(config.getSubtitle());
/*     */     }
/*     */     
/* 243 */     if (config.getSubtitleColor() != 0) {
/* 244 */       setSubtitleColor(config.getSubtitleColor());
/*     */     }
/*     */     
/* 247 */     showRetryButton(config.shouldShowRetryButton());
/*     */     
/* 249 */     if (config.getRetryButtonText() != null) {
/* 250 */       setRetryButtonText(config.getRetryButtonText());
/*     */     }
/*     */     
/* 253 */     if (config.getRetryButtonTextColor() != 0) {
/* 254 */       setRetryButtonTextColor(config.getRetryButtonTextColor());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setImageResource(int res) {
/* 266 */     setImage(res);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setImageDrawable(Drawable drawable) {
/* 277 */     setImage(drawable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setImageBitmap(Bitmap bitmap) {
/* 288 */     setImage(bitmap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImage(int res) {
/* 297 */     this.a.setImageResource(res);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImage(Drawable drawable) {
/* 306 */     this.a.setImageDrawable(drawable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Drawable getImage() {
/* 313 */     return this.a.getDrawable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImage(Bitmap bitmap) {
/* 322 */     this.a.setImageBitmap(bitmap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String text) {
/* 331 */     this.b.setText(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 338 */     return this.b.getText().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(int res) {
/* 347 */     this.b.setText(res);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTitleColor() {
/* 354 */     return this.b.getCurrentTextColor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitleColor(int res) {
/* 363 */     this.b.setTextColor(res);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubtitle(String exception) {
/* 372 */     this.c.setText(exception);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubtitle() {
/* 379 */     return this.c.getText().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubtitle(int res) {
/* 388 */     this.c.setText(res);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubtitleColor() {
/* 395 */     return this.c.getCurrentTextColor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubtitleColor(int res) {
/* 404 */     this.c.setTextColor(res);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRetryButtonText(String text) {
/* 413 */     this.d.setText(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRetryButtonText() {
/* 420 */     return this.d.getText().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRetryButtonText(int res) {
/* 429 */     this.d.setText(res);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRetryButtonTextColor() {
/* 436 */     return this.d.getCurrentTextColor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRetryButtonTextColor(int color) {
/* 445 */     this.d.setTextColor(color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showTitle(boolean show) {
/* 452 */     this.b.setVisibility(show ? View.VISIBLE : View.GONE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTitleVisible() {
/* 459 */     return (this.b.getVisibility() == View.VISIBLE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showSubtitle(boolean show) {
/* 466 */     this.c.setVisibility(show ? View.VISIBLE : View.GONE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSubtitleVisible() {
/* 473 */     return (this.c.getVisibility() == View.VISIBLE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showRetryButton(boolean show) {
/* 480 */     this.d.setVisibility(show ? View.VISIBLE : View.GONE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRetryButtonVisible() {
/* 487 */     return (this.d.getVisibility() == View.VISIBLE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubtitleAlignment() {
/* 494 */     int i = this.c.getGravity();
/* 495 */     if (i == 3)
/* 496 */       return 0; 
/* 497 */     if (i == 1) {
/* 498 */       return 1;
/*     */     }
/* 500 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubtitleAlignment(int alignment) {
/* 508 */     if (alignment == 0) {
/* 509 */       this.c.setGravity(3);
/* 510 */     } else if (alignment == 1) {
/* 511 */       this.c.setGravity(1);
/*     */     } else {
/* 513 */       this.c.setGravity(5);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Config
/*     */   {
/*     */     private Object a;
/*     */     
/*     */     private String b;
/*     */     
/*     */     private int c;
/*     */     
/*     */     private String d;
/*     */     
/*     */     private int e;
/*     */     
/*     */     private boolean f = true;
/*     */     
/*     */     private String g;
/*     */     private int h;
/*     */     
/*     */     public static Builder create() {
/* 536 */       return new Builder();
/*     */     }
/*     */     
/*     */     public Object getImage() {
/* 540 */       return this.a;
/*     */     }
/*     */     
/*     */     public String getTitle() {
/* 544 */       return this.b;
/*     */     }
/*     */     
/*     */     public int getTitleColor() {
/* 548 */       return this.c;
/*     */     }
/*     */     
/*     */     public String getSubtitle() {
/* 552 */       return this.d;
/*     */     }
/*     */     
/*     */     public int getSubtitleColor() {
/* 556 */       return this.e;
/*     */     }
/*     */     
/*     */     public boolean shouldShowRetryButton() {
/* 560 */       return this.f;
/*     */     }
/*     */     
/*     */     public String getRetryButtonText() {
/* 564 */       return this.g;
/*     */     }
/*     */     
/*     */     public int getRetryButtonTextColor() {
/* 568 */       return this.h;
/*     */     }
/*     */     
/*     */     private Config() {}
/*     */     
/*     */     public static class Builder
/*     */     {
/* 575 */       private Config a = new Config();
/*     */ 
/*     */       
/*     */       public Builder image(int res) {
/* 579 */         a.a = Integer.valueOf(res);
/* 580 */         return this;
/*     */       }
/*     */       
/*     */       public Builder image(Drawable drawable) {
/* 584 */         a.a = drawable;
/* 585 */         return this;
/*     */       }
/*     */       
/*     */       public Builder image(Bitmap bitmap) {
/* 589 */         a.a = bitmap;
/* 590 */         return this;
/*     */       }
/*     */       
/*     */       public Builder title(String title) {
/* 594 */         a.b = title;
/* 595 */         return this;
/*     */       }
/*     */       
/*     */       public Builder titleColor(int color) {
/* 599 */         a.c = color;
/* 600 */         return this;
/*     */       }
/*     */       
/*     */       public Builder subtitle(String subtitle) {
/* 604 */         a.d = subtitle;
/* 605 */         return this;
/*     */       }
/*     */       
/*     */       public Builder subtitleColor(int color) {
/* 609 */         a.e = color;
/* 610 */         return this;
/*     */       }
/*     */       
/*     */       public Builder retryVisible(boolean visible) {
/* 614 */         a.f = visible;
/* 615 */         return this;
/*     */       }
/*     */       
/*     */       public Builder retryText(String text) {
/* 619 */         a.g = text;
/* 620 */         return this;
/*     */       }
/*     */       
/*     */       public Builder retryTextColor(int color) {
/* 624 */         a.h = color;
/* 625 */         return this;
/*     */       }
/*     */       
/*     */       private Builder() {}
/* 629 */       public Config build() { return this.a; } } } public static class Builder { public Config build() { return this.a; }
/*     */ 
/*     */     
/*     */     private Config a;
/*     */     
/*     */     private Builder() {
/*     */       this.a = new Config();
/*     */     }
/*     */     
/*     */     public Builder image(int res) {
    /* 579 */         a.a = Integer.valueOf(res);
    /* 580 */         return this;
    /*     */       }
        /*     */
        /*     */       public Builder image(Drawable drawable) {
            /* 584 */         a.a = drawable;
            /* 585 */         return this;
            /*     */       }
        /*     */
        /*     */       public Builder image(Bitmap bitmap) {
            /* 589 */         a.a = bitmap;
            /* 590 */         return this;
            /*     */       }
        /*     */
        /*     */       public Builder title(String title) {
            /* 594 */         a.b = title;
            /* 595 */         return this;
            /*     */       }
        /*     */
        /*     */       public Builder titleColor(int color) {
            /* 599 */         a.c = color;
            /* 600 */         return this;
            /*     */       }
        /*     */
        /*     */       public Builder subtitle(String subtitle) {
            /* 604 */         a.d = subtitle;
            /* 605 */         return this;
            /*     */       }
        /*     */
        /*     */       public Builder subtitleColor(int color) {
            /* 609 */         a.e = color;
            /* 610 */         return this;
            /*     */       }
        /*     */
        /*     */       public Builder retryVisible(boolean visible) {
            /* 614 */         a.f = visible;
            /* 615 */         return this;
            /*     */       }
        /*     */
        /*     */       public Builder retryText(String text) {
            /* 619 */         a.g = text;
            /* 620 */         return this;
            /*     */       }
        /*     */
        /*     */       public Builder retryTextColor(int color) {
            /* 624 */         a.h = color;
            /* 625 */         return this;
            /*     */       } }
/*     */ 
/*     */   
/*     */   public static interface RetryListener {
/*     */     void onRetry();
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/view/ErrorView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */