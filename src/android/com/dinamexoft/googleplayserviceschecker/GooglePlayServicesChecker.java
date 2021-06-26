package com.dinamexoft.googleplayserviceschecker;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlayServicesChecker extends CordovaPlugin {

    private static final String APPTAG = "GooglePlayServicesChecker";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("check")) {
            Log.i(APPTAG, "checkPlayServices");
            JSONObject json = new JSONObject();
            GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
            int googleResult = googleAPI.isGooglePlayServicesAvailable(this.cordova.getContext());
            if (googleResult == com.google.android.gms.common.ConnectionResult.SUCCESS) {
                Log.i(APPTAG, "Google Services Exist");
                json.put("googleStatus", true);
            }else{
              json.put("googleStatus", false);
            }

            HuaweiApiAvailability huaweiAPI = HuaweiApiAvailability.getInstance();
            int resultHuawei = huaweiAPI.isHuaweiMobileServicesAvailable(this.cordova.getContext());
            if (resultHuawei == com.huawei.hms.api.HuaweiApiAvailability.SUCCESS) {
                Log.i(APPTAG, "Huawei Services Exist");
                json.put("huaweiStatus", true);
            }else{
              json.put("huaweiStatus", false);
            }

            callbackContext.success(json);
            return true;
        } else {
            callbackContext.error("Method not allowed");
            return false;
        }
    }
}
