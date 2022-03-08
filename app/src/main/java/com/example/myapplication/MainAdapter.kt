package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*


class MainAdapter(val homefeed  : model ) : RecyclerView.Adapter<customViewHolder>(){


    override fun getItemCount(): Int {
        return homefeed.articles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customViewHolder {
        val layoutInflate= LayoutInflater.from(parent.context)
        val cellforrow = layoutInflate.inflate(R.layout.row,parent, false)
        return customViewHolder(cellforrow)
    }

    override fun onBindViewHolder(holder: customViewHolder, position: Int) {
        val titles = homefeed.articles.get(position)
        holder.v.textView1.text = titles.author
        val thumbnailImageview = holder.v.imageView1
        Picasso.with(holder.v.context).load(titles.urlToImage).into(thumbnailImageview)
    }
}

class customViewHolder(val v : View) : RecyclerView.ViewHolder(v){

}