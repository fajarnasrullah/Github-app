package com.jer.githubappsederhana.ui.detail

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jer.githubappsederhana.R
import com.jer.githubappsederhana.data.Result
import com.jer.githubappsederhana.database.GitHub
import com.jer.githubappsederhana.databinding.ActivityDetailBinding
import com.jer.githubappsederhana.response.DetailUserResponse
import com.jer.githubappsederhana.response.FollowUserResponseItem
import com.jer.githubappsederhana.response.ItemsItem
import com.jer.githubappsederhana.ui.follow.SectionPagerAdapter
import com.jer.githubappsederhana.ui.main.MainViewModel
import com.jer.githubappsederhana.ui.main.UserAdapter

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private lateinit var sectionsPagerAdapter: SectionPagerAdapter

    private val githubViewModel by viewModels<GithubViewModel>(){
        ViewModelFactory.getInstance(application)
    }

    private var isFavorite = false
//    private lateinit var viewPager: ViewPager2
//    private lateinit var tabLayout: TabLayout





    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)


        // dapetin username dari intent
        val username = intent.getStringExtra("username") ?: ""

        // Memanggil fungsi getUserDetail() dari ViewModel
        viewModel.findDetailUser(username)


        viewModel.userDetail.observe(this, Observer { userDetail ->
            // Tampilkan data userDetail di UI (misalnya, textViews)
            updateUI(userDetail)

        })



        viewModel.isLoading2.observe(this) {
            showLoading2(it)
        }

        githubViewModel.getAllUsers().observe(this, Observer { github ->

            binding.fabFavorite.setOnClickListener {
                // Jika user belum ditandai sebagai favorit, tambahkan ke database
                if (!isFavorite) {
                    val username = intent.getStringExtra("username") ?: ""
                    val avatarUrl = viewModel.userDetail.value?.avatarUrl ?: ""

                    val githubUser = GitHub(username, avatarUrl)
                    // Memanggil fungsi insert pada viewModel
                    githubViewModel.insert(githubUser)
                    setFav(true)
                } else {
                    // Jika sudah ditandai sebagai favorit, implementasikan logika hapus di sini
                    val username = intent.getStringExtra("username") ?: ""
                    val avatarUrl2 = viewModel.userDetail.value?.avatarUrl ?: ""
                    val githubUser = GitHub(username, avatarUrl2)
                    githubViewModel.delete(githubUser)
                    setFav(false)
                }
            }

        })





//        binding.tabLayout
//        binding.tabLayout
//        binding.viewPager


        sectionsPagerAdapter = SectionPagerAdapter(this)
        sectionsPagerAdapter.username = username
//        sectionsPagerAdapter.username = username
//        tabLayout = findViewById(R.id.tabLayout)
//        viewPager = findViewById(R.id.viewPager)
//
//        viewPager.adapter = sectionsPagerAdapter
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = when (position) {
//                0 -> "Followers"
//                1 -> "Following"
//                else -> ""
//            }
//        }.attach()


//        val sectionsPagerAdapter = SectionPagerAdapter(this)
//        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
//        viewPager.adapter = sectionsPagerAdapter
        val viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

//        val tabs: TabLayout = findViewById(R.id.tabLayout)
        val tabs = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f



        // Memanggil fungsi getFollowersUser() dari ViewModel
        viewModel.getFollowersUser(username)
        viewModel.getFollowingUser(username)


//        viewModel.userDetail.observe(this, Observer { userDetail ->
//            // Tampilkan data userDetail di UI (misalnya, textViews)
//            updateUI(userDetail)
//
//        })

//        val gitAdapter = GithubAdapter { news ->
//            if (news){
//                viewModel.deleteNews(news)
//            } else {
//                viewModel.saveNews(news)
//            }
//        }




//        githubViewModel.getBookmarkedUsers().observe(this) { bookmarkedUsers ->
//
//            git.submitList(bookmarkedNews)
//        }

//




//        githubViewModel.getFavUsers(username).observe(this, Observer { favUser ->
//            if(favUser == null){
//                setFav(false)
//                binding.fabFavorite.setOnClickListener {
//                    val favUser = GitHub(DetailUserRespons)
//                    githubViewModel.insertFav(favUser)
//                }
//            }else {
//                setFav(true)
//                binding.fabFavorite.setOnClickListener {
//                    viewModel.delete(favUser)
//                }
//            }
//        })

//        githubViewModel.getHeadlineNews().observe(this, Observer { result ->
//            when (result) {
//                is Result.Success -> {
//                    val newsData = result.data
//                }
//                is Result.Error -> {
//
//                    Toast.makeText(
//                        this,
//                        "Terjadi kesalahan: ${result.error}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        })





//        viewModel.isLoading2.observe(this) {
//            showLoading2(it)
//        }
    }


//        // Inisialisasi ViewModel
//        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
//
//        val username = intent.getStringExtra("username") ?: ""
//        viewModel.findDetailUser(username)
//
//
////        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
////        detailViewModel.userDetail.observe(this) { userDetailGithub ->
//////            setUserDetail(userDetailGithub)
////        }
//

//
//
//        viewModel.userDetail.observe(this, Observer { userDetail ->
//            // Tampilkan data userDetail di UI (misalnya, textViews)
//            updateUI(userDetail)
//        })




//    private fun setUserDetail(userDetailGithub: DetailUserResponse) {
//        val adapter = UserAdapter()
//        adapter.submitList(userDetailGithub)
//        binding.recyclerView.adapter = adapter
////        binding.searchView.setText("")
//
//    }

    fun updateUI(userDetail: DetailUserResponse) {
        val usernameTextView: TextView = findViewById(R.id.username_detail)
        usernameTextView.text = userDetail?.login
        val fullnameTextView: TextView = findViewById(R.id.nama_detail)
        fullnameTextView.text = userDetail?.name
        val followersTextView: TextView = findViewById(R.id.followers_detail)
        followersTextView.text = userDetail?.followers.toString()
        val followingTextView: TextView  = findViewById(R.id.following_detail)
        followingTextView.text = userDetail?.following.toString()


        val avatarImageView: ImageView = findViewById(R.id.imageDetail)
        Glide.with(this)
            .load(userDetail?.avatarUrl)
            .into(avatarImageView)
//                .load(user.avatarUrl)
//                .into(imageViewAvatar)
    }


    private fun setFav(isfavorite: Boolean){
        this.isFavorite = isfavorite
        if (isfavorite){
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        }else{
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_border_favorite))
        }
    }


//        fun updateUI(userDetail: DetailUserResponse) {
//            val usernameTextView: TextView = findViewById(R.id.username_detail)
//            usernameTextView.text = userDetail?.login
//            val fullnameTextView: TextView = findViewById(R.id.nama_detail)
//            fullnameTextView.text = userDetail?.login
//
//            val avatarImageView: ImageView = findViewById(R.id.imageDetail)
//            Glide.with(this)
//                .load(userDetail?.avatarUrl)
//                .into(avatarImageView)
////                .load(user.avatarUrl)
////                .into(imageViewAvatar)


    private fun showLoading2(isLoading2: Boolean) {
        if (isLoading2) {
            binding.progressBar2.visibility = View.VISIBLE
        } else {
            binding.progressBar2.visibility = View.GONE
        }
    }

}