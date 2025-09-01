/*     */
package com.pubnub.api.managers;
/*     */
/*     */

import com.pubnub.api.enums.PNOperationType;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class TelemetryManager
        /*     */ {
    /*     */   private static final int TIMESTAMP_DIVIDER = 1000;
    /*     */   private static final double MAXIMUM_LATENCY_DATA_AGE = 60.0D;
    /*     */   private static final int CLEAN_UP_INTERVAL = 1;
    /*     */   private static final int CLEAN_UP_INTERVAL_MULTIPLIER = 1000;
    /*     */   private Timer timer;
    /*     */   private Map<String, List<Map<String, Double>>> latencies;

    /*     */
    /*     */
    public TelemetryManager() {
        /*  27 */
        this.latencies = new HashMap<>();
        /*  28 */
        startCleanUpTimer();
        /*     */
    }

    /*     */
    /*     */
    private static double averageLatencyFromData(List<Map<String, Double>> endpointLatencies) {
        /*  32 */
        double d = 0.0D;
        /*  33 */
        for (Map<String, Double> map : endpointLatencies) {
            /*  34 */
            d += ((Double) map.get("l")).doubleValue();
            /*     */
        }
        /*     */
        /*  37 */
        return d / endpointLatencies.size();
        /*     */
    }

    /*     */
    /*     */
    /*     */
    private static String endpointNameForOperation(PNOperationType type) {
        /*  42 */
        String str = "time";
        switch (type)
            /*     */ {
            case PNPublishOperation:
                /*  44 */
                str = "pub";
                return str;
            case PNHistoryOperation:
            case PNFetchMessagesOperation:
            case PNDeleteMessagesOperation:
                str = "hist";
                return str;
            case PNUnsubscribeOperation:
            case PNWhereNowOperation:
            case PNHereNowOperation:
            case PNHeartbeatOperation:
            case PNSetStateOperation:
            case PNGetState:
                str = "pres";
                return str;
            case PNAddChannelsToGroupOperation:
            case PNRemoveChannelsFromGroupOperation:
            case PNChannelGroupsOperation:
            case PNRemoveGroupOperation:
            case PNChannelsForGroupOperation:
                str = "cg";
                return str;
            case PNPushNotificationEnabledChannelsOperation:
            case PNAddPushNotificationsOnChannelsOperation:
            case PNRemovePushNotificationsFromChannelsOperation:
            case PNRemoveAllPushNotificationsOperation:
                str = "push";
                return str;
            case PNAccessManagerAudit:
            case PNAccessManagerGrant:
                str = "pam";
                return str;
        }
        return str;
        /*     */
    }

    /*     */
    /*     */
    public synchronized Map<String, String> operationsLatency() {
        /*  85 */
        HashMap<Object, Object> hashMap = new HashMap<>();
        /*  86 */
        for (Map.Entry<String, List<Map<String, Double>>> entry : this.latencies.entrySet()) {
            /*  87 */
            String str = "l_".concat((String) entry.getKey());
            /*  88 */
            double d = averageLatencyFromData((List<Map<String, Double>>) entry.getValue());
            /*  89 */
            if (d > 0.0D) {
                /*  90 */
                hashMap.put(str, Double.toString(d));
                /*     */
            }
            /*     */
        }
        /*  93 */
        return (Map) hashMap;
        /*     */
    }

    /*     */
    /*     */
    public synchronized void storeLatency(long latency, PNOperationType type) {
        /*  97 */
        if (type != PNOperationType.PNSubscribeOperation && latency > 0L) {
            /*  98 */
            String str = endpointNameForOperation(type);
            /*  99 */
            if (str != null) {
                /* 100 */
                double d = (new Date()).getTime() / 1000.0D;
                /*     */
                /* 102 */
                List<Map<String, Double>> list = this.latencies.get(str);
                /* 103 */
                if (list == null) {
                    /* 104 */
                    list = new ArrayList();
                    /* 105 */
                    this.latencies.put(str, list);
                    /*     */
                }
                /*     */
                /* 108 */
                HashMap<String, Double> hashMap = new HashMap<>();
                /* 109 */
                hashMap.put("d", Double.valueOf(d));
                /* 110 */
                hashMap.put("l", Double.valueOf(latency / 1000.0D));
                /* 111 */
                list.add(hashMap);
                /*     */
            }
            /*     */
        }
        /*     */
    }

    /*     */
    /*     */
    private synchronized void cleanUpTelemetryData() {
        /* 117 */
        double d = (new Date()).getTime() / 1000.0D;
        /* 118 */
        ArrayList<String> arrayList = new ArrayList(this.latencies.keySet());
        /* 119 */
        for (String str : arrayList) {
            /* 120 */
            ArrayList<Map> arrayList1 = new ArrayList();
            /* 121 */
            List<Map<String, Double>> list = this.latencies.get(str);
            /* 122 */
            for (Map map : list) {
                /* 123 */
                if (d - ((Double) map.get("d")).doubleValue() > 60.0D) {
                    /* 124 */
                    arrayList1.add(map);
                    /*     */
                }
                /*     */
            }
            /* 127 */
            if (arrayList1.size() > 0) {
                /* 128 */
                list.removeAll(arrayList1);
                /*     */
            }
            /* 130 */
            if (list.size() == 0) {
                /* 131 */
                this.latencies.remove(str);
                /*     */
            }
            /*     */
        }
        /*     */
    }

    /*     */
    /*     */
    private void startCleanUpTimer() {
        /* 137 */
        long l = 1000L;
        /*     */
        /* 139 */
        stopCleanUpTimer();
        /* 140 */
        this.timer = new Timer();
        /* 141 */
        this.timer.schedule(new TimerTask()
                /*     */ {
            /*     */
            public void run() {
                /* 144 */
                TelemetryManager.this.cleanUpTelemetryData();
                /*     */
            }
            /*     */
        }, l, l);
        /*     */
    }

    /*     */
    /*     */
    public void stopCleanUpTimer() {
        /* 150 */
        if (this.timer != null) {
            /* 151 */
            this.timer.cancel();
            /* 152 */
            this.timer = null;
            /*     */
        }
        /*     */
    }
    /*     */
}


/* Location:              /Users/anasnadeem/Downloads/projectlibfiledriver/classes.jar!/com/pubnub/api/managers/TelemetryManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */