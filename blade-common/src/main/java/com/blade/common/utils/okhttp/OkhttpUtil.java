package com.blade.common.utils.okhttp;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * TODO:
 * 参照： https://square.github.io/okhttp
 * @author blade
 * 2023/3/10 15:47
 */
public class OkhttpUtil {

    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create("", MediaType.get("application/json; charset=utf-8"));
    }
}
