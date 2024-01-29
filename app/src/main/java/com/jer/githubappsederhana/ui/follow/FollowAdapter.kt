package com.jer.githubappsederhana.ui.follow

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jer.githubappsederhana.R
import com.jer.githubappsederhana.databinding.FragmentFollowBinding
import com.jer.githubappsederhana.databinding.ListItemUserBinding
import com.jer.githubappsederhana.response.FollowUserResponseItem
import com.jer.githubappsederhana.response.ItemsItem

import com.jer.githubappsederhana.ui.detail.DetailActivity


class FollowAdapter(private val context: Context): ListAdapter<FollowUserResponseItem, FollowAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

//        holder.itemView.setOnClickListener {
//            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra("username", user.login)
//            context.startActivity(intent)
//        }

    }
    class MyViewHolder(val binding: ListItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: FollowUserResponseItem){
            binding.usernameList.text = user.login

            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(binding.imgList)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowUserResponseItem>() {
            override fun areItemsTheSame(oldItem: FollowUserResponseItem, newItem: FollowUserResponseItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: FollowUserResponseItem, newItem: FollowUserResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}







//class FollowAdapter(private val context: FollowFragment): RecyclerView.Adapter<FollowAdapter.ViewHolder>() {
//
//
//    private val userList = ArrayList<FollowUserResponseItem>()
//    private val followersList = mutableListOf<FollowUserResponseItem>()
//    private val followingList = mutableListOf<Following>()
//
////    private var users: List<User> = emptyList()
//
//    fun setData(data: List<FollowUserResponseItem>) {
//        userList.clear()
//        userList.addAll(data)
//        notifyDataSetChanged()
//    }
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
//        return ViewHolder(view)
//
//
//
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val user = userList[position]
//        holder.bind(user)
//
////        holder.itemView.setOnClickListener {
////            val intent = Intent(context, DetailActivity::class.java)
////            intent.putExtra("username", user.login)
////            context.startActivity(intent)
////        }
//
//
//
//    }
//
//    override fun getItemCount(): Int {
//        return userList.size
//    }
//
//    fun submitList(followersUser: List<FollowUserResponseItem>) {
//        TODO("Not yet implemented")
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(user: FollowUserResponseItem){
//            val textViewUsername = itemView.findViewById<TextView>(R.id.username_list)
//            val imageViewAvatar = itemView.findViewById<ImageView>(R.id.img_list)
//
//            textViewUsername.text = user.login
//            Glide.with(itemView.context)
//                .load(user.avatarUrl)
//                .into(imageViewAvatar)
//        }
//    }
//}
