package com.example.demoofmvpandroid.data.Modal.MoviesModel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.util.*


class MoviesModelBean {
    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<MoviesModelData>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
}