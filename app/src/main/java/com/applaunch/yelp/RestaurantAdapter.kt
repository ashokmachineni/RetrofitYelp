package com.applaunch.yelp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RestaurantAdapter(val context:Context,val restaurants:List<YelpRestaurant>):
    RecyclerView.Adapter<RestaurantAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        val ratings:TextView
        val imags:ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = itemView.findViewById(R.id.textView)
            ratings = itemView.findViewById(R.id.ratings)
            imags = itemView.findViewById(R.id.imag)
        }
        fun bind(restaurant: YelpRestaurant) {

            textView.text = restaurant.name
            ratings.text = restaurant.rating.toString()
            Glide.with(itemView).load(restaurant.image_url).into(imags)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_views,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int = restaurants.size
    }


