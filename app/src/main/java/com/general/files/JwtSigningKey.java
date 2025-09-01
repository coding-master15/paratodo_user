// Copyright 2020 Sinch AB

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at

// http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.general.files;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import android.util.Base64;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/** This class is used to derive a <i>JWT</i> signing key from a <i>Sinch Application Secret</i>. */
public class JwtSigningKey {

    public static String formatDate(Date dt) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyyMMdd");
        return spf.format(dt);
    }

    public static String keyId(Date issuedAt) {
        return "hkdfv1-" + formatDate(issuedAt);
    }

    /**
     * @param applicationSecret <i>Sinch Application Secret</i> (in base64-encoded format)
     * @param issuedAt Time when signing key is issued/created.
     * @return A derived signing secret key.
     */
    public static byte[] deriveSigningKey(String applicationSecret, Date issuedAt) {
        return deriveSigningKey(Base64.decode(applicationSecret, Base64.DEFAULT), issuedAt);
    }

    public static byte[] deriveSigningKey(byte[] applicationSecret, Date issuedAt) {
        return hmacSha256(applicationSecret, formatDate(issuedAt));
    }

    private static byte[] hmacSha256(byte[] key, String message) {
        if (null == key || key.length == 0)
            throw new IllegalArgumentException("Invaid input key to HMAC-256");

        if (null == message)
            throw new IllegalArgumentException("Input message to HMAC-256 must not be null");

        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA256");
            mac.init(keySpec);
            return mac.doFinal(message.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}