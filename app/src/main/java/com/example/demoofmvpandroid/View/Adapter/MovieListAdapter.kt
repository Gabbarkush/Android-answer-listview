package com.example.demoofmvpandroid.View.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoofmvpandroid.data.Modal.MoviesModel.MoviesModelData
import com.example.demoofmvpandroid.R
import kotlinx.android.synthetic.main.movies_list_view.view.*

class MovieListAdapter(var moviesModelData: ArrayList<MoviesModelData>): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    var mContext: Context?= null
    var contactView: View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val inflater = LayoutInflater.from(mContext)
        contactView = inflater.inflate(R.layout.movies_list_view, parent, false)
        return ViewHolder(contactView!!)
    }

    override fun getItemCount(): Int {
        return moviesModelData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = moviesModelData[position]
        holder.BindView(data)
    }
    fun updateList(data: ArrayList<MoviesModelData>) {
        moviesModelData = data
        notifyDataSetChanged()

    }
    inner class ViewHolder(row: View): RecyclerView.ViewHolder(row) {
        var view = row
        var data: MoviesModelData? = null
        fun BindView(data: MoviesModelData) {
            this.data = data
            Glide.with(mContext!!)
                .load("https://s3.ap-south-1.amazonaws.com/browsecart.com/v1-docs/products/39/gallery_ab2bbcb4-0a8f-41f0-8486-7fd2bf31f3ff.jpg")
                .into(view.imageview)
            view.text_title.text = data.originalTitle
            view.movie_dis.text = data.overview

        }
    }

}