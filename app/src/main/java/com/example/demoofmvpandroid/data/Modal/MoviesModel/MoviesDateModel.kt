package com.example.demoofmvpandroid.data.Modal.MoviesModel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MoviesDateModel {

    @SerializedName("maximum")
    @Expose
    var maximum: String? = null

    @SerializedName("minimum")
    @Expose
    var minimum: String? = null
}