package com.example.demoofmvpandroid.data.RetrofitRequest

import com.example.demoofmvpandroid.data.RetrofitRequest.SharedPrefsHelper
import com.example.demoofmvpandroid.View.Common.Constants
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient (var sharedHelper: SharedPrefsHelper){

    var retrofit: Retrofit? = null
    private var httpClient: OkHttpClient.Builder? = null

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        setHttpClient()
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BaseUrl)
            .client(httpClient!!.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun setHttpClient() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient = OkHttpClient.Builder()
        httpClient!!.connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
        httpClient!!.addInterceptor(logging)
        httpClient!!.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer "+ sharedHelper[Constants.TOKEN, ""]!!) // <-- this is the important line
            //requestBuilder.addHeader("X-API-KEY", Constants.X_API_VALUE)

            val request = requestBuilder.build()
            chain.proceed(request)
        })
    }
}