package com.blade.common.utils.okhttp;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * TODO:
 * 参照： https://square.github.io/okhttp
 * @author blade
 * 2023/3/10 15:47
 */
public class OkhttpUtil {

    public static void main(String[] args) {
        String url = "http://localhost:8080/orgQuReInfo/findPhones";
        String content = "{\"caseCode\":\"JSCS-20220708-001-000005\",\"customerParentId\":100004,\"userId\":1001}";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), content);
        Headers headers = new Headers.Builder()
                .add("descFlag", "1")
                .build();
        Request request = new Request.Builder()
                .headers(headers)
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
