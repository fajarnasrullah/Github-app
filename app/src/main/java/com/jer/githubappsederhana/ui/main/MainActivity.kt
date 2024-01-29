package com.jer.githubappsederhana.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.switchmaterial.SwitchMaterial
import com.jer.githubappsederhana.R
import com.jer.githubappsederhana.databinding.ActivityMainBinding
import com.jer.githubappsederhana.response.ItemsItem
import com.jer.githubappsederhana.ui.forMenu.FavActivity
import com.jer.githubappsederhana.ui.forMenu.SettingPreferences
import com.jer.githubappsederhana.ui.forMenu.SettingsActivity
import com.jer.githubappsederhana.ui.forMenu.SettingsVMFactory
import com.jer.githubappsederhana.ui.forMenu.SettingsViewModel
import com.jer.githubappsederhana.ui.forMenu.dataStore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

//    private lateinit var userAdapter: UserAdapter
//    private val githubApiService = RetrofitClient.createService(ApiService::class.java)

    companion object {
        private const val TAG = "MainActivity"
        private const val USER = "fajar"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        mainViewModel.listUser.observe(this) { userGithub ->
            setUserData(userGithub)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }


        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
            searchView
                .editText
                .addTextChangedListener { editable ->
                    val query = editable.toString()
                    mainViewModel.setSearchQuery(query)
                }

        }





        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.recyclerView.addItemDecoration(itemDecoration)


        binding.searchBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.btnMenuFav -> {
                    val intent = Intent(this, FavActivity:: class.java)
                    startActivity(intent)
                    true
                }
                R.id.btnMenuDarkmode -> {
                    val intent = Intent(this, SettingsActivity:: class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }



        val pref = SettingPreferences.getInstance(application.dataStore)
        val vm_setting = ViewModelProvider(this, SettingsVMFactory(pref)).get(
            SettingsViewModel::class.java
        )



//
        vm_setting.getThemeSettings().observe(this) { darkMode: Boolean ->
            if (darkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }


        }


















        // Inisialisasi SearchView
//        val searchView = findViewById<SearchView>(R.id.searchView)
//        val searchView = binding.searchView
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // Panggil fungsi pencarian saat pengguna menekan tombol cari
//                if (!query.isNullOrBlank()) {
//                    searchGithubUsers(query)
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Tindakan saat teks pencarian berubah (optional)
//                return true
//            }
//        })
//
//
//
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//        val recyclerViewUsers = binding.recyclerViewUsers
//        // Konfigurasi RecyclerView
//        userAdapter = UserAdapter(emptyList()) { selectedUser ->
//            // Handle item click event (pindah ke halaman detail, dll.)
//        }
//
//        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(this)
//        binding.recyclerViewUsers.adapter = userAdapter
//
//        // Mengambil data pengguna GitHub dari API menggunakan Retrofit
//        searchGithubUsers("YOUR_SEARCH_KEYWORD")
    }






    private fun setUserData(userGithub: List<ItemsItem>) {
        val adapter = UserAdapter(this)
        adapter.submitList(userGithub)
        binding.recyclerView.adapter = adapter
//        binding.searchView.setText("")

    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }





//    private fun searchGithubUsers(query: String) {
//        val call = githubApiService.searchUsers(query)
//        call.enqueue(object : Callback<SearchUserResponse> {
//            override fun onResponse(
//                call: Call<SearchUserResponse>,
//                response: Response<SearchUserResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val users = response.body()?.items ?: emptyList()
//                    updateRecyclerView(users)
//                } else {
//                    // Handle error response from API
//                }
//            }
//
//            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
//                // Handle network errors
//            }
//        })
//    }
//
//    private fun updateRecyclerView(users: List<ItemsItem>) {
//        userAdapter = UserAdapter(users) { selectedUser ->
//            // Handle item click event (pindah ke halaman detail, dll.)
//        }
//        binding.recyclerViewUsers.adapter = userAdapter
//    }
}