package com.chengdu.ai.util;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpClientUtil {
	public static String sendJSON(String url, String json) {
		OkHttpClient ok = new OkHttpClient();
        MediaType mt = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mt, json);
        Request request = new Request.Builder().url(url).post(body).addHeader("Content-Type", "application/json")
        		.addHeader("cache-control", "no-cache").build();
        Response response = null;
        String result = null;
        try {
			response = ok.newCall(request).execute();
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return result;
	}
}
