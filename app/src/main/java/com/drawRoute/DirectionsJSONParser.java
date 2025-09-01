/*    */ package com.drawRoute;
/*    */ 
/*    */

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DirectionsJSONParser
/*    */ {
/*    */   public List<List<HashMap<String, String>>> parse(JSONObject jObject) {
/* 17 */     ArrayList<ArrayList<HashMap<Object, Object>>> arrayList = new ArrayList();
/* 18 */     JSONArray jSONArray1 = null;
/* 19 */     JSONArray jSONArray2 = null;
/* 20 */     JSONArray jSONArray3 = null;
/*    */ 
/*    */     
/*    */     try {
/* 24 */       jSONArray1 = jObject.getJSONArray("routes");
/*    */       
/* 26 */       for (byte b = 0; b < jSONArray1.length(); b++) {
/* 27 */         jSONArray2 = ((JSONObject)jSONArray1.get(b)).getJSONArray("legs");
/* 28 */         ArrayList<HashMap<Object, Object>> arrayList1 = new ArrayList();
/*    */         
/* 30 */         for (byte b1 = 0; b1 < jSONArray2.length(); b1++) {
/* 31 */           jSONArray3 = ((JSONObject)jSONArray2.get(b1)).getJSONArray("steps");
/*    */           
/* 33 */           for (byte b2 = 0; b2 < jSONArray3.length(); b2++) {
/* 34 */             String str = "";
/* 35 */             str = (String)((JSONObject)((JSONObject)jSONArray3.get(b2)).get("polyline")).get("points");
/* 36 */             List<LatLng> list = a(str);
/*    */             
/* 38 */             for (byte b3 = 0; b3 < list.size(); b3++) {
/* 39 */               HashMap<Object, Object> hashMap = new HashMap<>();
/* 40 */               hashMap.put("lat", Double.toString(((LatLng)list.get(b3)).latitude));
/* 41 */               hashMap.put("lng", Double.toString(((LatLng)list.get(b3)).longitude));
/* 42 */               arrayList1.add(hashMap);
/*    */             } 
/*    */           } 
/* 45 */           arrayList.add(arrayList1);
/*    */         }
/*    */       
/*    */       } 
/* 49 */     } catch (JSONException jSONException) {
/* 50 */       jSONException.printStackTrace();
/* 51 */     } catch (Exception exception) {}
/*    */     
/* 53 */     return (List)arrayList;
/*    */   }
/*    */   
/*    */   private List<LatLng> a(String paramString) {
                return new ArrayList<>();
/*    */     // Byte code:
/*    */     //   0: new java/util/ArrayList
/*    */     //   3: dup
/*    */     //   4: invokespecial <init> : ()V
/*    */     //   7: astore_2
/*    */     //   8: iconst_0
/*    */     //   9: istore_3
/*    */     //   10: aload_1
/*    */     //   11: invokevirtual length : ()I
/*    */     //   14: istore #4
/*    */     //   16: iconst_0
/*    */     //   17: istore #5
/*    */     //   19: iconst_0
/*    */     //   20: istore #6
/*    */     //   22: iload_3
/*    */     //   23: iload #4
/*    */     //   25: if_icmpge -> 205
/*    */     //   28: iconst_0
/*    */     //   29: istore #8
/*    */     //   31: iconst_0
/*    */     //   32: istore #9
/*    */     //   34: aload_1
/*    */     //   35: iload_3
/*    */     //   36: iinc #3, 1
/*    */     //   39: invokevirtual charAt : (I)C
/*    */     //   42: bipush #63
/*    */     //   44: isub
/*    */     //   45: istore #7
/*    */     //   47: iload #9
/*    */     //   49: iload #7
/*    */     //   51: bipush #31
/*    */     //   53: iand
/*    */     //   54: iload #8
/*    */     //   56: ishl
/*    */     //   57: ior
/*    */     //   58: istore #9
/*    */     //   60: iinc #8, 5
/*    */     //   63: iload #7
/*    */     //   65: bipush #32
/*    */     //   67: if_icmpge -> 34
/*    */     //   70: iload #9
/*    */     //   72: iconst_1
/*    */     //   73: iand
/*    */     //   74: ifeq -> 86
/*    */     //   77: iload #9
/*    */     //   79: iconst_1
/*    */     //   80: ishr
/*    */     //   81: iconst_m1
/*    */     //   82: ixor
/*    */     //   83: goto -> 90
/*    */     //   86: iload #9
/*    */     //   88: iconst_1
/*    */     //   89: ishr
/*    */     //   90: istore #10
/*    */     //   92: iload #5
/*    */     //   94: iload #10
/*    */     //   96: iadd
/*    */     //   97: istore #5
/*    */     //   99: iconst_0
/*    */     //   100: istore #8
/*    */     //   102: iconst_0
/*    */     //   103: istore #9
/*    */     //   105: aload_1
/*    */     //   106: iload_3
/*    */     //   107: iinc #3, 1
/*    */     //   110: invokevirtual charAt : (I)C
/*    */     //   113: bipush #63
/*    */     //   115: isub
/*    */     //   116: istore #7
/*    */     //   118: iload #9
/*    */     //   120: iload #7
/*    */     //   122: bipush #31
/*    */     //   124: iand
/*    */     //   125: iload #8
/*    */     //   127: ishl
/*    */     //   128: ior
/*    */     //   129: istore #9
/*    */     //   131: iinc #8, 5
/*    */     //   134: iload #7
/*    */     //   136: bipush #32
/*    */     //   138: if_icmpge -> 105
/*    */     //   141: iload #9
/*    */     //   143: iconst_1
/*    */     //   144: iand
/*    */     //   145: ifeq -> 157
/*    */     //   148: iload #9
/*    */     //   150: iconst_1
/*    */     //   151: ishr
/*    */     //   152: iconst_m1
/*    */     //   153: ixor
/*    */     //   154: goto -> 161
/*    */     //   157: iload #9
/*    */     //   159: iconst_1
/*    */     //   160: ishr
/*    */     //   161: istore #11
/*    */     //   163: iload #6
/*    */     //   165: iload #11
/*    */     //   167: iadd
/*    */     //   168: istore #6
/*    */     //   170: new com/google/android/gms/maps/model/LatLng
/*    */     //   173: dup
/*    */     //   174: iload #5
/*    */     //   176: i2d
/*    */     //   177: ldc2_w 100000.0
/*    */     //   180: ddiv
/*    */     //   181: iload #6
/*    */     //   183: i2d
/*    */     //   184: ldc2_w 100000.0
/*    */     //   187: ddiv
/*    */     //   188: invokespecial <init> : (DD)V
/*    */     //   191: astore #12
/*    */     //   193: aload_2
/*    */     //   194: aload #12
/*    */     //   196: invokeinterface add : (Ljava/lang/Object;)Z
/*    */     //   201: pop
/*    */     //   202: goto -> 22
/*    */     //   205: aload_2
/*    */     //   206: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #58	-> 0
/*    */     //   #59	-> 8
/*    */     //   #60	-> 16
/*    */     //   #62	-> 22
/*    */     //   #63	-> 28
/*    */     //   #65	-> 34
/*    */     //   #66	-> 47
/*    */     //   #67	-> 60
/*    */     //   #68	-> 63
/*    */     //   #69	-> 70
/*    */     //   #70	-> 92
/*    */     //   #72	-> 99
/*    */     //   #73	-> 102
/*    */     //   #75	-> 105
/*    */     //   #76	-> 118
/*    */     //   #77	-> 131
/*    */     //   #78	-> 134
/*    */     //   #79	-> 141
/*    */     //   #80	-> 163
/*    */     //   #82	-> 170
/*    */     //   #83	-> 193
/*    */     //   #84	-> 202
/*    */     //   #85	-> 205
/*    */   }
/*    */ }


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/drawRoute/DirectionsJSONParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */