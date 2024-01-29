package com.jer.githubappsederhana.ui.forMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jer.githubappsederhana.R
import com.jer.githubappsederhana.databinding.ActivityFavBinding
import com.jer.githubappsederhana.response.ItemsItem
import com.jer.githubappsederhana.ui.detail.GithubViewModel
import com.jer.githubappsederhana.ui.detail.ViewModelFactory
import com.jer.githubappsederhana.ui.main.MainViewModel
import com.jer.githubappsederhana.ui.main.UserAdapter

class FavActivity : AppCompatActivity() {


    private lateinit var binding: ActivityFavBinding
    private val githubViewModel by viewModels<GithubViewModel>(){
        ViewModelFactory.getInstance(application)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_fav)

        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFav.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.recyclerViewFav.addItemDecoration(itemDecoration)

        githubViewModel.getAllUsers().observe(this) { users ->
            val items = arrayListOf<ItemsItem>()
            val adapter = UserAdapter(this)
            users.map {

                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl)
                items.add(item)


//                val item = it.avatarUrl?.let { it1 -> ItemsItem(login = it.username, avatarUrl = it1) }
//                if (item != null) {
//                    items.add(item)
//                }
//                val item = it.avatarUrl?.let { it1 -> ItemsItem(login = it.username, avatarUrl = it1) }
//                if (item != null) {
//                    items.add(item)
//                }
            }
            adapter.submitList(items)

            binding.recyclerViewFav.adapter = adapter



        }




//        viewModel.getFavoriteUser().observe(this) { users ->
//            val items = arrayListOf<ItemsItem>()
//            users.map {
//                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl)
//                items.add(item)
//            }
//            adapter.submitList(items)
//        }
    }
}