package com.example.demoofmvpandroid.View.Interfaces

import android.content.Context

interface ResponseCallback {

    fun <T> success(t: T, context: Context?)

    fun <T> onFalse(t: T, context: Context?)

    fun failure(t: Throwable?, context: Context?)
}