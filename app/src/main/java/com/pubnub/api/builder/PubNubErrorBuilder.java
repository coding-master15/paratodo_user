/*     */ package com.pubnub.api.builder;
/*     */ 
/*     */ import com.pubnub.api.PubNubError;
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
/*     */ public final class PubNubErrorBuilder
/*     */ {
/*     */   public static final int PNERR_TIMEOUT = 100;
/*     */   public static final int PNERR_PUBNUB_ERROR = 101;
/*     */   public static final int PNERR_CONNECT_EXCEPTION = 102;
/*     */   public static final int PNERR_HTTP_ERROR = 103;
/*     */   public static final int PNERR_CLIENT_TIMEOUT = 104;
/*     */   public static final int PNERR_ULSSIGN_ERROR = 105;
/*     */   public static final int PNERR_NETWORK_ERROR = 106;
/*     */   public static final int PNERR_PUBNUB_EXCEPTION = 108;
/*     */   public static final int PNERR_DISCONNECT = 109;
/*     */   public static final int PNERR_DISCONN_AND_RESUB = 110;
/*     */   public static final int PNERR_GATEWAY_TIMEOUT = 111;
/*     */   public static final int PNERR_FORBIDDEN = 112;
/*     */   public static final int PNERR_UNAUTHORIZED = 113;
/*     */   public static final int PNERR_SECRET_KEY_MISSING = 114;
/*     */   public static final int PNERR_ENCRYPTION_ERROR = 115;
/*     */   public static final int PNERR_DECRYPTION_ERROR = 116;
/*     */   public static final int PNERR_INVALID_JSON = 117;
/*     */   public static final int PNERR_GETINPUTSTREAM = 118;
/*     */   public static final int PNERR_MALFORMED_URL = 119;
/*     */   public static final int PNERR_URL_OPEN = 120;
/*     */   public static final int PNERR_JSON_ERROR = 121;
/*     */   public static final int PNERR_PROTOCOL_EXCEPTION = 122;
/*     */   public static final int PNERR_READINPUT = 123;
/*     */   public static final int PNERR_BAD_GATEWAY = 124;
/*     */   public static final int PNERR_INTERNAL_ERROR = 125;
/*     */   public static final int PNERR_PARSING_ERROR = 126;
/*     */   public static final int PNERR_BAD_REQUEST = 127;
/*     */   public static final int PNERR_HTTP_RC_ERROR = 128;
/*     */   public static final int PNERR_NOT_FOUND = 129;
/*     */   public static final int PNERR_HTTP_SUBSCRIBE_TIMEOUT = 130;
/*     */   public static final int PNERR_INVALID_ARGUMENTS = 131;
/*     */   public static final int PNERR_CHANNEL_MISSING = 132;
/*     */   public static final int PNERR_CONNECTION_NOT_SET = 133;
/*     */   public static final int PNERR_CHANNEL_GROUP_PARSING_ERROR = 134;
/*     */   public static final int PNERR_CRYPTO_ERROR = 135;
/*     */   public static final int PNERR_GROUP_MISSING = 136;
/*     */   public static final int PNERR_AUTH_KEYS_MISSING = 137;
/*     */   public static final int PNERR_SUBSCRIBE_KEY_MISSING = 138;
/*     */   public static final int PNERR_PUBLISH_KEY_MISSING = 139;
/*     */   public static final int PNERR_STATE_MISSING = 140;
/*     */   public static final int PNERR_CHANNEL_AND_GROUP_MISSING = 141;
/*     */   public static final int PNERR_MESSAGE_MISSING = 142;
/*     */   public static final int PNERR_PUSH_TYPE_MISSING = 143;
/*     */   public static final int PNERR_DEVICE_ID_MISSING = 144;
/* 235 */   public static final PubNubError PNERROBJ_TIMEOUT = PubNubError.builder()
/* 236 */     .errorCode(100)
/* 237 */     .message("Timeout Occurred")
/* 238 */     .build();
/*     */   
/* 240 */   public static final PubNubError PNERROBJ_INTERNAL_ERROR = PubNubError.builder()
/* 241 */     .errorCode(125)
/* 242 */     .message("Internal Error")
/* 243 */     .build();
/*     */   
/* 245 */   public static final PubNubError PNERROBJ_ENCRYPTION_ERROR = PubNubError.builder()
/* 246 */     .errorCode(115)
/* 247 */     .message("Error while encrypting message to be published to PubNub Cloud. Please contact support with error details.")
/* 248 */     .build();
/*     */   
/* 250 */   public static final PubNubError PNERROBJ_DECRYPTION_ERROR = PubNubError.builder()
/* 251 */     .errorCode(116)
/* 252 */     .message("Decryption Error. Please contact support with error details.")
/* 253 */     .build();
/*     */   
/* 255 */   public static final PubNubError PNERROBJ_INVALID_JSON = PubNubError.builder()
/* 256 */     .errorCode(117)
/* 257 */     .message("Invalid Json. Please contact support with error details.")
/* 258 */     .build();
/*     */   
/* 260 */   public static final PubNubError PNERROBJ_JSON_ERROR = PubNubError.builder()
/* 261 */     .errorCode(121)
/* 262 */     .message("JSON Error while processing API response. Please contact support with error details.")
/* 263 */     .build();
/*     */   
/* 265 */   public static final PubNubError PNERROBJ_MALFORMED_URL = PubNubError.builder()
/* 266 */     .errorCode(119)
/* 267 */     .message("Malformed URL. Please contact support with error details.")
/* 268 */     .build();
/*     */   
/* 270 */   public static final PubNubError PNERROBJ_PUBNUB_ERROR = PubNubError.builder()
/* 271 */     .errorCode(101)
/* 272 */     .message("PubNub Error")
/* 273 */     .build();
/*     */   
/* 275 */   public static final PubNubError PNERROBJ_URL_OPEN = PubNubError.builder()
/* 276 */     .errorCode(120)
/* 277 */     .message("Error opening url. Please contact support with error details.")
/* 278 */     .build();
/*     */   
/* 280 */   public static final PubNubError PNERROBJ_PROTOCOL_EXCEPTION = PubNubError.builder()
/* 281 */     .errorCode(122)
/* 282 */     .message("Protocol Exception. Please contact support with error details.")
/* 283 */     .build();
/*     */   
/* 285 */   public static final PubNubError PNERROBJ_CONNECT_EXCEPTION = PubNubError.builder()
/* 286 */     .errorCode(102)
/* 287 */     .message("Connect Exception. Please verify if network is reachable.")
/* 288 */     .build();
/*     */   
/* 290 */   public static final PubNubError PNERROBJ_HTTP_RC_ERROR = PubNubError.builder()
/* 291 */     .errorCode(128)
/* 292 */     .message("Unable to get PnResponse Code. Please contact support with error details.")
/* 293 */     .build();
/*     */   
/* 295 */   public static final PubNubError PNERROBJ_GETINPUTSTREAM = PubNubError.builder()
/* 296 */     .errorCode(118)
/* 297 */     .message("Unable to get Input Stream Please contact support with error details.")
/* 298 */     .build();
/*     */   
/* 300 */   public static final PubNubError PNERROBJ_READINPUT = PubNubError.builder()
/* 301 */     .errorCode(123)
/* 302 */     .message("Unable to read Input Stream. Please contact support with error details.")
/* 303 */     .build();
/*     */   
/* 305 */   public static final PubNubError PNERROBJ_BAD_REQUEST = PubNubError.builder()
/* 306 */     .errorCode(127)
/* 307 */     .message("Bad request. Please contact support with error details.")
/* 308 */     .build();
/*     */   
/* 310 */   public static final PubNubError PNERROBJ_HTTP_ERROR = PubNubError.builder()
/* 311 */     .errorCode(103)
/* 312 */     .message("HTTP Error. Please check network connectivity. Please contact support with error details if issue persists.")
/* 313 */     .build();
/*     */   
/* 315 */   public static final PubNubError PNERROBJ_BAD_GATEWAY = PubNubError.builder()
/* 316 */     .errorCode(124)
/* 317 */     .message("Bad Gateway. Please contact support with error details.")
/* 318 */     .build();
/*     */   
/* 320 */   public static final PubNubError PNERROBJ_CLIENT_TIMEOUT = PubNubError.builder()
/* 321 */     .errorCode(104)
/* 322 */     .message("Client Timeout")
/* 323 */     .build();
/*     */   
/* 325 */   public static final PubNubError PNERROBJ_GATEWAY_TIMEOUT = PubNubError.builder()
/* 326 */     .errorCode(111)
/* 327 */     .message("Gateway Timeout")
/* 328 */     .build();
/*     */   
/* 330 */   public static final PubNubError PNERROBJ_5023_INTERNAL_ERROR = PubNubError.builder()
/* 331 */     .errorCode(125)
/* 332 */     .message("Internal Server Error. Please contact support with error details.")
/* 333 */     .build();
/*     */   
/* 335 */   public static final PubNubError PNERROBJ_PARSING_ERROR = PubNubError.builder()
/* 336 */     .errorCode(126)
/* 337 */     .message("Parsing Error")
/* 338 */     .build();
/*     */   
/* 340 */   public static final PubNubError PNERROBJ_PUBNUB_EXCEPTION = PubNubError.builder()
/* 341 */     .errorCode(108)
/* 342 */     .message("PubNub Exception")
/* 343 */     .build();
/*     */   
/* 345 */   public static final PubNubError PNERROBJ_DISCONNECT = PubNubError.builder()
/* 346 */     .errorCode(109)
/* 347 */     .message("Disconnect")
/* 348 */     .build();
/*     */   
/* 350 */   public static final PubNubError PNERROBJ_DISCONN_AND_RESUB = PubNubError.builder()
/* 351 */     .errorCode(110)
/* 352 */     .message("Disconnect and Resubscribe")
/* 353 */     .build();
/*     */   
/* 355 */   public static final PubNubError PNERROBJ_FORBIDDEN = PubNubError.builder()
/* 356 */     .errorCode(112)
/* 357 */     .message("Authentication Failure. Incorrect Authentication Key")
/* 358 */     .build();
/*     */   
/* 360 */   public static final PubNubError PNERROBJ_UNAUTHORIZED = PubNubError.builder()
/* 361 */     .errorCode(113)
/* 362 */     .message("Authentication Failure. Authentication Key is missing")
/* 363 */     .build();
/*     */   
/* 365 */   public static final PubNubError PNERROBJ_SECRET_KEY_MISSING = PubNubError.builder()
/* 366 */     .errorCode(114)
/* 367 */     .message("ULS configuration failed. Secret Key not configured.")
/* 368 */     .build();
/*     */   
/* 370 */   public static final PubNubError PNERROBJ_SUBSCRIBE_KEY_MISSING = PubNubError.builder()
/* 371 */     .errorCode(138)
/* 372 */     .message("ULS configuration failed. Subscribe Key not configured.")
/* 373 */     .build();
/*     */   
/* 375 */   public static final PubNubError PNERROBJ_PUBLISH_KEY_MISSING = PubNubError.builder()
/* 376 */     .errorCode(139)
/* 377 */     .message("ULS configuration failed. Publish Key not configured.")
/* 378 */     .build();
/*     */   
/* 380 */   public static final PubNubError PNERROBJ_ULSSIGN_ERROR = PubNubError.builder()
/* 381 */     .errorCode(105)
/* 382 */     .message("Invalid Signature. Please contact support with error details.")
/* 383 */     .build();
/*     */   
/* 385 */   public static final PubNubError PNERROBJ_5075_NETWORK_ERROR = PubNubError.builder()
/* 386 */     .errorCode(106)
/* 387 */     .message("Network Error. Please verify if network is reachable.")
/* 388 */     .build();
/*     */   
/* 390 */   public static final PubNubError PNERROBJ_NOT_FOUND_ERROR = PubNubError.builder()
/* 391 */     .errorCode(129)
/* 392 */     .message("Page Not Found Please verify if network is reachable. Please contact support with error details.")
/* 393 */     .build();
/*     */   
/* 395 */   public static final PubNubError PNERROBJ_SUBSCRIBE_TIMEOUT = PubNubError.builder()
/* 396 */     .errorCode(130)
/* 397 */     .message("Subscribe Timeout.")
/* 398 */     .build();
/*     */   
/* 400 */   public static final PubNubError PNERROBJ_INVALID_ARGUMENTS = PubNubError.builder()
/* 401 */     .errorCode(131)
/* 402 */     .message("INVALID ARGUMENTS.")
/* 403 */     .build();
/*     */   
/* 405 */   public static final PubNubError PNERROBJ_CHANNEL_MISSING = PubNubError.builder()
/* 406 */     .errorCode(132)
/* 407 */     .message("Channel Missing.")
/* 408 */     .build();
/*     */   
/* 410 */   public static final PubNubError PNERROBJ_STATE_MISSING = PubNubError.builder()
/* 411 */     .errorCode(140)
/* 412 */     .message("State Missing.")
/* 413 */     .build();
/*     */   
/* 415 */   public static final PubNubError PNERROBJ_MESSAGE_MISSING = PubNubError.builder()
/* 416 */     .errorCode(142)
/* 417 */     .message("Message Missing.")
/* 418 */     .build();
/*     */   
/* 420 */   public static final PubNubError PNERROBJ_PUSH_TYPE_MISSING = PubNubError.builder()
/* 421 */     .errorCode(143)
/* 422 */     .message("Push Type Missing.")
/* 423 */     .build();
/*     */   
/* 425 */   public static final PubNubError PNERROBJ_DEVICE_ID_MISSING = PubNubError.builder()
/* 426 */     .errorCode(144)
/* 427 */     .message("Device Id Missing.")
/* 428 */     .build();
/*     */   
/* 430 */   public static final PubNubError PNERROBJ_CONNECTION_NOT_SET = PubNubError.builder()
/* 431 */     .errorCode(133)
/* 432 */     .message("PubNub Connection not set")
/* 433 */     .build();
/*     */   
/* 435 */   public static final PubNubError PNERROBJ_GROUP_MISSING = PubNubError.builder()
/* 436 */     .errorCode(136)
/* 437 */     .message("Group Missing.")
/* 438 */     .build();
/*     */   
/* 440 */   public static final PubNubError PNERROBJ_CHANNEL_AND_GROUP_MISSING = PubNubError.builder()
/* 441 */     .errorCode(141)
/* 442 */     .message("Channel and Group Missing.")
/* 443 */     .build();
/*     */   
/* 445 */   public static final PubNubError PNERROBJ_AUTH_KEYS_MISSING = PubNubError.builder()
/* 446 */     .errorCode(137)
/* 447 */     .message("Auth Keys Missing.")
/* 448 */     .build();
/*     */   
/* 450 */   public static final PubNubError PNERROBJ_CHANNEL_GROUP_PARSING_ERROR = PubNubError.builder()
/* 451 */     .errorCode(134)
/* 452 */     .message("Channel group name is invalid")
/* 453 */     .build();
/*     */   
/* 455 */   public static final PubNubError PNERROBJ_CRYPTO_ERROR = PubNubError.builder()
/* 456 */     .errorCode(135)
/* 457 */     .message("Error while encrypting/decrypting message. Please contact support with error details.")
/* 458 */     .build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PubNubError createCryptoError(int code, String message) {
/* 465 */     return PubNubError.builder()
/* 466 */       .errorCode(135)
/* 467 */       .errorCodeExtended(code)
/* 468 */       .message("Error while encrypting/decrypting message. Please contact support with error details. - ".concat(message))
/* 469 */       .build();
/*     */   }
/*     */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/builder/PubNubErrorBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */