package com.example.demoofmvpandroid.data.RetrofitRequest

import android.content.Context
import com.example.demoofmvpandroid.data.RetrofitRequest.SharedPrefsHelper
import com.example.demoofmvpandroid.View.Common.Common
import com.example.demoofmvpandroid.View.Common.Common.Companion.isNetworkConnected
import com.example.demoofmvpandroid.View.Interfaces.ResponseCallback
import com.example.demoofmvpandroid.View.Interfaces.WebServices
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceHandler(
    var apiClient: APIClient,
    var mSharedPrefsHelper: SharedPrefsHelper,
    var common: Common){


    var builder: WebServices?= null
    init {
        builder = apiClient.retrofit!!.create(WebServices::class.java)
    }

    fun getmovies(responseCallback: ResponseCallback, context: Context) {
        val call = builder!!.getmovies()
        getResponse(call, responseCallback, context)
    }

    fun topstar(responseCallback: ResponseCallback, context: Context) {
        val call = builder!!.topstar()
        getResponse(call, responseCallback, context)
    }
    private fun <T> getResponse(
        call: Call<T>,
        responseCallback: ResponseCallback?,
        context: Context?
    ) {
        try {
            if (!isNetworkConnected) {
                common.showError(context!!)
                return
            }
            if (context != null) {
                common.showProgressDialog(context)
            }
            call.enqueue(object : Callback<T?> {
                override fun onResponse(call: Call<T?>, response: Response<T?>) {
                    if (context != null) common.dismissDialog()
                    if (responseCallback != null) {
                        println("==========================================="+response)
                        if (response.body() != null) {
                            responseCallback.success(response.body(),context)
                        } else {
                            try {
                                responseCallback.onFalse(response,context)
                                when (response.code()) {
                                    404 ->
                                        if(!response.isSuccessful()) {
                                            var jsonObject : JSONObject?  = null;
                                            try {
                                                jsonObject = JSONObject(response.errorBody()!!.string());
                                                var message:String = jsonObject.getString("message");
                                                common.showSneakBar(context,message)
                                            } catch (e: JSONException) {
                                                e.printStackTrace();
                                            }
                                        }
                                    500 ->
                                        if(!response.isSuccessful()) {
                                            var jsonObject : JSONObject?  = null;
                                            try {
                                                jsonObject = JSONObject(response.errorBody()!!.string());
                                                var message:String = jsonObject.getString("message");
                                                common.showSneakBar(context,message)
                                            } catch (e: JSONException) {
                                                e.printStackTrace();
                                            }
                                        }

                                    403 ->
                                        if(!response.isSuccessful()) {
                                            var jsonObject : JSONObject?  = null;
                                            try {
                                                jsonObject = JSONObject(response.errorBody()!!.string());
                                                var message:String = jsonObject.getString("message");
                                                common.showSneakBar(context,message)
                                            } catch (e: JSONException) {
                                                e.printStackTrace();
                                            }
                                        }
                                    400->
                                        if(!response.isSuccessful()) {
                                            var jsonObject : JSONObject?  = null;
                                            try {
                                                jsonObject = JSONObject(response.errorBody()!!.string());
                                                var message:String = jsonObject.getString("message");
                                                common.showSneakBar(context,message)
                                            } catch (e: JSONException) {
                                                e.printStackTrace();
                                            }
                                        }

                                    else ->

                                        if(!response.isSuccessful()) {
                                            var jsonObject : JSONObject?  = null;
                                            try {
                                                jsonObject = JSONObject(response.errorBody()!!.string());
                                                var message:String = jsonObject.getString("message");
                                                common.showSneakBar(context,message)
                                            } catch (e: JSONException) {
                                                e.printStackTrace();
                                            }
                                        }

                                        /*Toast.makeText(
                                        context,
                                        "unknown error",
                                        Toast.LENGTH_SHORT
                                    ).show()*/
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                    println("---------------execute--------------")
                }

                override fun onFailure(call: Call<T?>, t: Throwable) {
                    if (context != null)
                        common.dismissDialog()
                    if (responseCallback != null)
                        responseCallback.failure(t,context)
                }
            })
        } catch (e: Exception) {
            println("---------------error--------------")
            e.printStackTrace()
        }
    }




}
