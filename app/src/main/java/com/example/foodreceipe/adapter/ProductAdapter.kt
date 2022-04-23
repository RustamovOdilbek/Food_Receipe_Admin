package com.example.foodreceipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodreceipe.R
import com.example.foodreceipe.model.Post

class ProductAdapter (var context: Context, var items: ArrayList<Post>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shopping_list_view, parent, false)
        return ShopViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = items[position]

        if (holder is ShopViewHolder){
            val iv_photo = holder.iv_photo
            val tv_name = holder.tv_name
            val tv_cost = holder.tv_cost

            Glide.with(context).load(item.postImg).into(iv_photo)

            tv_name.text = item.name
            tv_cost.text = item.price
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ShopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv_photo: ImageView
        val tv_name: TextView
        val tv_cost: TextView

        init {
            iv_photo = view.findViewById(R.id.iv_photo)
            tv_name = view.findViewById(R.id.tv_name)
            tv_cost = view.findViewById(R.id.tv_cost)
        }
    }
    }