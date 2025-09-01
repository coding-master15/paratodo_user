/*    */ package com.pubnub.api.interceptors;
/*    */ 
/*    */

import androidx.annotation.NonNull;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*    */
/*    */ 
/*    */ 
/*    */ public class SignatureInterceptor
/*    */   implements Interceptor
/*    */ {
/*    */   private PubNub pubNub;
/*    */   
/*    */   public SignatureInterceptor(PubNub pubNubInstance) {
/* 18 */     this.pubNub = pubNubInstance;
/*    */   }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request1 = chain.request();
        Request request2 = PubNubUtil.requestSigner(request1, this.pubNub.getConfiguration(), this.pubNub.getTimestamp());
        return chain.proceed(request2);
    }



    /*    */
/*    */   
/*    */   /*public Response intercept(Chain chain) throws IOException {
*//* 23 *//*     Request request1 = chain.request();
*//* 24 *//*     Request request2 = PubNubUtil.requestSigner(request1, this.pubNub.getConfiguration(), this.pubNub.getTimestamp());
*//* 25 *//*     return chain.proceed(request2);
*//*    *//*   }*/

    /*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/interceptors/SignatureInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */