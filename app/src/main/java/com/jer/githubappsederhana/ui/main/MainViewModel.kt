package com.jer.githubappsederhana.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jer.githubappsederhana.data.UsersRepository
import com.jer.githubappsederhana.database.GitHub
import com.jer.githubappsederhana.response.ItemsItem
import com.jer.githubappsederhana.response.SearchUserResponse
import com.jer.githubappsederhana.retrofit.ApiService
import com.jer.githubappsederhana.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class MainViewModel: ViewModel() {



    private val searchQuery = MutableLiveData<String>()

    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser: LiveData<List<ItemsItem>> = _listUser
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    companion object {
        private const val TAG = "MainViewModel"
        private const val USER_ID = "fajar"
    }

//    init {
//        findUser()
//    }

    init {

        searchQuery.observeForever { query ->
            if (!query.isNullOrEmpty()) {
                findUser(query)
            }
        }
    }


    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    private fun findUser(query: String) {
        _isLoading.value = true

        val client = RetrofitClient.getApiService(ApiService::class.java).searchUsers(query)
        client.enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {

                    _listUser.value = response.body()?.items

                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }


}