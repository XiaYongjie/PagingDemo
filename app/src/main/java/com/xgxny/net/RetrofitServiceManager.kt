package com.xgxny.net

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class RetrofitServiceManager private constructor() {
    private  val DEFAULT_TIME_OUT = 5//超时时间 5s
    private val DEFAULT_READ_TIME_OUT = 10
    private var mRetrofit: Retrofit
    init {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
        builder.writeTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
        builder.readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
        mRetrofit = Retrofit.Builder()
            .client(builder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://cirno.linovel.net/demo/")
            .build()
    }

    companion object {
        fun getInstance() = SingletonHolder.instance
    }

    private object  SingletonHolder {
        val instance  = RetrofitServiceManager()
    }
    fun <T> create(service: Class<T>): T {
        return mRetrofit.create(service)
    }

}