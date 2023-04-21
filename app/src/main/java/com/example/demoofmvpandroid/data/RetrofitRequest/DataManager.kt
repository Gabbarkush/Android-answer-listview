package com.example.demoofmvpandroid.data.RetrofitRequest

import android.content.Context
import com.example.demoofmvpandroid.View.Common.Common
import com.example.demoofmvpandroid.View.Interfaces.ResponseCallback


class DataManager(
    var mSharedPrefsHelper: SharedPrefsHelper,
    var mServiceHandler: ServiceHandler,
    var common: Common
) {
    fun getmovies(responseCallback: ResponseCallback,  context:Context) {

        mServiceHandler.getmovies(responseCallback,context)
    }
    fun topstar(responseCallback: ResponseCallback,  context:Context) {

        mServiceHandler.topstar(responseCallback,context)
    }

}

