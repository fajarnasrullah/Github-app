package com.jer.githubappsederhana.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import com.bumptech.glide.request.RequestOptions
import com.jer.githubappsederhana.R
import com.jer.githubappsederhana.database.GitHub
import com.jer.githubappsederhana.databinding.ActivityDetailBinding
import com.jer.githubappsederhana.databinding.ListItemUserBinding
import com.jer.githubappsederhana.response.ItemsItem
import com.jer.githubappsederhana.ui.main.UserAdapter

//class GithubAdapter(private val onBookmarkClick: (GitHub) -> Unit) : ListAdapter<GitHub, GithubAdapter.MyViewHolder>(DIFF_CALLBACK) {
class GithubAdapter(private val listFav: List<GitHub>) : RecyclerView.Adapter<GithubAdapter.ViewHolder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_user, viewGroup, false))
    }

    override fun getItemCount() = listFav.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvusername.text = listFav[position].username
        Glide.with(viewHolder.itemView.context)
            .load(listFav[position].avatarUrl)
            .circleCrop()
            .into(viewHolder.imgProfile)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgProfile: ImageView = view.findViewById(R.id.img_list)
        val tvusername: TextView = view.findViewById(R.id.username_list)
    }










//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val binding = ActivityDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MyViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val users = getItem(position)
//        holder.bind(users)
//
////        val ivBookmark = holder.binding.fabFavorite
////        if (users.isBookmarked) {
////            ivBookmark.setImageDrawable(ContextCompat.getDrawable(ivBookmark.context, R.drawable))
////        } else {
////            ivBookmark.setImageDrawable(ContextCompat.getDrawable(ivBookmark.context, R.))
////        }
////        ivBookmark.setOnClickListener {
////            onBookmarkClick(users)
////        }
//    }
//
//    class MyViewHolder(val binding: ActivityDetailBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(users: GitHub) {
//            binding.usernameDetail.text = users.username
//
//            Glide.with(itemView.context)
//                .load(users.avatarUrl)
//
//                .into(binding.imageDetail)
////            itemView.setOnClickListener {
////                val intent = Intent(Intent.ACTION_VIEW)
////                intent.data = Uri.parse(users.url)
////                itemView.context.startActivity(intent)
////            }
//        }
//    }
//
//
//
//
//    companion object {
//        val DIFF_CALLBACK: DiffUtil.ItemCallback<GitHub> =
//            object : DiffUtil.ItemCallback<GitHub>() {
//                override fun areItemsTheSame(oldItem: GitHub, newItem: GitHub): Boolean {
//                    return oldItem.username == newItem.username
//                }
//
//                @SuppressLint("DiffUtilEquals")
//                override fun areContentsTheSame(oldItem: GitHub, newItem: GitHub): Boolean {
//                    return oldItem == newItem
//                }
//            }
//    }




}