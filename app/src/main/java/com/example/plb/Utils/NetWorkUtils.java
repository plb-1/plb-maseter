package com.example.plb.Utils;

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
}
