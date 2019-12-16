package com.example.plb.Utils;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by rose on 2019/12/11.
 */

public class NetWorkUtils {
    static OkHttpClient client = new OkHttpClient();
    static MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
    private static String TAG = "NetWorkUtils";

    public static String post(String url, String parse){
        String result = "";
        RequestBody requestBody = RequestBody.create(mediaType,parse);
        try {
            Request request = new Request.Builder()
                    .post(requestBody)
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String get(String url, String parse) throws IOException {
        String result = "";
        String path = url+"/"+parse;

        Log.e(TAG, "get: path ---> "+path);
        try {
            Request request = new Request.Builder()
                    .url(path)
                    .build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String postFrom(String url,HashMap<String,String> params) throws IOException {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for(Object key : params.keySet()){
            keys.add(key.toString());
            values.add(params.get(key));
        }
        FormBody formBody = new FormBody(keys,values);

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
