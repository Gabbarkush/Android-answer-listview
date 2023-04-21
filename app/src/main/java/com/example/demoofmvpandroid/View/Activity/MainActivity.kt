package com.example.demoofmvpandroid.View.Activity

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import com.example.demoofmvpandroid.data.Modal.MoviesModel.MoviesModelBean
import com.example.demoofmvpandroid.data.Modal.MoviesModel.MoviesModelData
import com.example.demoofmvpandroid.R
import com.example.demoofmvpandroid.View.Adapter.MovieListAdapter
import com.example.demoofmvpandroid.View.Interfaces.ResponseCallback
import com.example.demoofmvpandroid.data.RetrofitRequest.AppController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_appbar.*

class MainActivity : AppCompatActivity(),ResponseCallback {

    var moviesModelData:ArrayList<MoviesModelData> = ArrayList()
    var adapter: MovieListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    AppController.instance!!.dataManager!!.getmovies(this,this)
                    true

                }
                R.id.search -> {
                    AppController.instance!!.dataManager!!.topstar(this,this)

                    true
                }
                else -> false
            }
        }
        AppController.instance!!.dataManager!!.getmovies(this,this)
        search_item.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val filteredValues: ArrayList<MoviesModelData> = ArrayList()
                if (moviesModelData != null) {
                    for (i in moviesModelData!!.indices){
                        if (moviesModelData?.get(i) != null) {
                            val item = moviesModelData?.get(i)
                            var searchitem = search_item.getText().toString().toLowerCase()
                                .trim({ it <= ' ' })
                            var newsearchitem = searchitem.replace("[^a-zA-Z0-9]".toRegex(), "")
                            println("================================$newsearchitem")

                            if (item!!.originalTitle!!.toLowerCase()?.trim({ it <= ' ' })!!
                                    .replace("[^a-zA-Z0-9]".toRegex(), "").contains(
                                        search_item.getText().toString().toLowerCase()
                                            .trim({ it <= ' ' })
                                            .replace("[^a-zA-Z0-9]".toRegex(), "")
                                    )
                            ) {
                                filteredValues.add(item!!)
                            }
                        }
                    }
                    if (adapter != null) {
                        adapter!!.updateList(filteredValues)
                    }

                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
    override fun <T> success(t: T, context: Context?) {

        if(t is MoviesModelBean){
            moviesModelData.clear()
            moviesModelData.addAll(t.results!!)
            adapter = MovieListAdapter(moviesModelData)
            recyclerView_movies.adapter = adapter
        }

    }

    override fun <T> onFalse(t: T, context: Context?) {

    }

    override fun failure(t: Throwable?, context: Context?) {
        println(t)
    }
}