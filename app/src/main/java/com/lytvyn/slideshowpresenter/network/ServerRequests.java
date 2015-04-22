package com.lytvyn.slideshowpresenter.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;


import java.util.HashMap;
import java.util.Map;

public class ServerRequests {
    private static String POST_STATUS = "http://www.iter8.treasure8.com/keep-alive/key/96f1c840bf799759e65497b775084a84/";
    private static String TAG = ServerRequests.class.getSimpleName();

    public static void postDeviceStatusRequest(
            Context context,
            final JsonObject status) {

        StringRequest request = new StringRequest(
                Request.Method.POST,
                POST_STATUS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String s) {
                        Log.i(TAG, "POST_SUCCESS: " + s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i(TAG, "POST_ERROR: " + volleyError.toString());
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Host", "www.iter8.treasure8.com");
                headers.put("Content-type", "application/x-www-form-urlencoded");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("device_id", status.get("device_id").toString());
                params.put("battery_level", status.get("battery_level").toString());
                params.put("location_longitude", status.get("location_longitude").toString());
                params.put("location_latitude", status.get("location_latitude").toString());
                params.put("is_slideshow_running", status.get("is_slideshow_running").toString());

                return params;
            }
        };

        VolleyQueue.add(context, request);
    }
}