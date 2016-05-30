package com.cmq.http_base_http;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cuimingqiang on 16/5/19.
 */
public final class ApiManager {
    private static OkHttpClient client = null;
    private static ConcurrentHashMap<String, Object> APICache = new ConcurrentHashMap<>();
    private static String URL = null;
    public static void registerHostUrl(String url){
        URL = url;
    }
    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Accept", "application/json")
                                .addHeader("Accept-Charset", "utf-8")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(logging)
                .build();
    }
    public static <T> T getAPI(Class<T> clazz) {
        if(URL == null)throw new IllegalArgumentException("host must be init");
        T api = (T) APICache.get(clazz.getName());
        if (api == null) {
            synchronized (ApiManager.class) {
                if (api != null) return api;
                api = new Retrofit.Builder().client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .baseUrl(URL)
                        .build().create(clazz);
                APICache.put(clazz.getName(), api);
            }
        }
        return api;
    }
}
