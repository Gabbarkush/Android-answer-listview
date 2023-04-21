package com.example.demoofmvpandroid.View.Interfaces

import com.example.demoofmvpandroid.data.Modal.MoviesModel.MoviesModelBean
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {


    @GET("now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    fun getmovies(): Call<MoviesModelBean>

    @GET("top_rated?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    fun topstar(): Call<MoviesModelBean>


}