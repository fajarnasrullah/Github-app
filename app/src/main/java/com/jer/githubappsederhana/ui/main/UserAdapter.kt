package com.jer.githubappsederhana.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jer.githubappsederhana.R
import com.jer.githubappsederhana.databinding.ListItemUserBinding
import com.jer.githubappsederhana.response.ItemsItem
import com.jer.githubappsederhana.response.SearchUserResponse
import com.jer.githubappsederhana.ui.detail.DetailActivity
import com.jer.githubappsederhana.ui.follow.FollowFragment


class UserAdapter(private val context: Context): ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("username", user.login)
            context.startActivity(intent)
        }



    }
    class MyViewHolder(val binding: ListItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem){
            binding.usernameList.text = user.login

            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(binding.imgList)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

