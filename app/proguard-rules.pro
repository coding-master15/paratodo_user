# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

-keepparameternames
-keepattributes Exceptions,InnerClasses,Signature,Deprecated, SourceFile,LineNumberTable,*Annotation*,EnclosingMethod

-keep class org.** { *; }
-keep class org.joda.convert.** { *; }
-keep class org.joda.time.** { *; }

-keep public class * {
      public protected *;
}

-keepclassmembernames class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String, boolean);
}

-keep class java.lang.** { *; }

#-keep public class *extends java.lang.annotation.Annotation {
#
#}

-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}

#-keepclassmembers,allowoptimization enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#-overloadaggressively
-dontskipnonpubliclibraryclassmembers
-dontskipnonpubliclibraryclasses
-dontshrink
-dontoptimize
-adaptclassstrings
-dontwarn java.lang.invoke.**
-dontwarn javax.annotation.GuardedBy
-dontwarn javax.annotation.**
-dontwarn org.xmlpull.v1.**
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn javax.net.ssl
-dontwarn javax.**
-dontwarn lombok.**
-dontwarn org.apache.**
-dontwarn com.squareup.**
-dontwarn com.sun.**
-dontwarn **retrofit**
#-dontwarn org.joda.**

-dontwarn com.google.android.libraries.places.api.Places
-dontwarn com.google.android.libraries.places.api.model.AddressComponent
-dontwarn com.google.android.libraries.places.api.model.AddressComponents
-dontwarn com.google.android.libraries.places.api.model.AutocompletePrediction
-dontwarn com.google.android.libraries.places.api.model.AutocompleteSessionToken
-dontwarn com.google.android.libraries.places.api.model.Place$Field
-dontwarn com.google.android.libraries.places.api.model.Place
-dontwarn com.google.android.libraries.places.api.model.TypeFilter
-dontwarn com.google.android.libraries.places.api.net.FetchPlaceRequest
-dontwarn com.google.android.libraries.places.api.net.FetchPlaceResponse
-dontwarn com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest$Builder
-dontwarn com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
-dontwarn com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
-dontwarn com.google.android.libraries.places.api.net.PlacesClient
-dontwarn com.google.api.client.http.GenericUrl
-dontwarn com.google.api.client.http.HttpHeaders
-dontwarn com.google.api.client.http.HttpRequest
-dontwarn com.google.api.client.http.HttpRequestFactory
-dontwarn com.google.api.client.http.HttpResponse
-dontwarn com.google.api.client.http.HttpTransport
-dontwarn com.google.api.client.http.javanet.NetHttpTransport$Builder
-dontwarn com.google.api.client.http.javanet.NetHttpTransport
-dontwarn com.stripe.android.stripecardscan.cardscan.CardScanSheet$CardScanResultCallback
-dontwarn com.stripe.android.stripecardscan.cardscan.CardScanSheet$Companion
-dontwarn com.stripe.android.stripecardscan.cardscan.CardScanSheet
-dontwarn com.stripe.android.stripecardscan.cardscan.CardScanSheetResult$Completed
-dontwarn com.stripe.android.stripecardscan.cardscan.CardScanSheetResult$Failed
-dontwarn com.stripe.android.stripecardscan.cardscan.CardScanSheetResult
-dontwarn com.stripe.android.stripecardscan.cardscan.exception.UnknownScanException
-dontwarn com.stripe.android.stripecardscan.payment.card.ScannedCard
-dontwarn org.slf4j.Logger
-dontwarn org.slf4j.LoggerFactory

-keepclasseswithmembernames class * {
    native <methods>;
}

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-dontwarn org.apache.http.annotation.**

-keep class com.sinch.** { *; }
-keep interface com.sinch.** { *; }
-keep class org.webrtc.** { *; }

-keep class com.flutterwave.raveandroid.** { *; }
-keep class com.flutterwave.** { *; }

-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }
