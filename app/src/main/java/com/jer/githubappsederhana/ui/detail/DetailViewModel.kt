package com.jer.githubappsederhana.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jer.githubappsederhana.response.DetailUserResponse
import com.jer.githubappsederhana.response.FollowUserResponseItem
import com.jer.githubappsederhana.response.ItemsItem
import com.jer.githubappsederhana.response.SearchUserResponse
import com.jer.githubappsederhana.retrofit.ApiService
import com.jer.githubappsederhana.retrofit.RetrofitClient
import com.jer.githubappsederhana.ui.main.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {



    private val apiService = RetrofitClient.getApiService(ApiService::class.java)

    private val searchUserDetail = MutableLiveData<String>()

    private val _userDetail = MutableLiveData<DetailUserResponse>()
    val userDetail: LiveData<DetailUserResponse> get() = _userDetail
    private val _isLoading2 = MutableLiveData<Boolean>()
    val isLoading2: LiveData<Boolean> = _isLoading2
    private val _followers = MutableLiveData<List<ItemsItem>>()
    val followers: LiveData<List<ItemsItem>> = _followers
    private val _following = MutableLiveData<List<ItemsItem>>()
    val following: LiveData<List<ItemsItem>> = _following
//    private val _isLoading2 = MutableLiveData<Boolean>()
//    val isLoading2: LiveData<Boolean> = _isLoading2


    companion object {
        private const val TAG = "DetailViewModel"
//        private const val USER_ID = "fajar"
    }





    fun findDetailUser(username: String) {
        _isLoading2.value = true
        apiService.getDetailUser(username).enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(call: Call<DetailUserResponse>, response: Response<DetailUserResponse>) {
                _isLoading2.value = false
                if (response.isSuccessful) {
                    _userDetail.value = response.body()
                } else {
                    // Handle error response
                    Log.e(TAG, "onFailure: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                // Handle network error
                _isLoading2.value = false
                Log.e(TAG, "onFailure: ${t.message}")

            }
        })
    }


    fun getFollowersUser(username: String) {
        _isLoading2.value = true
        apiService.getFollowers(username).enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading2.value = false
                if (response.isSuccessful) {
                    _followers.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading2.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }


    fun getFollowingUser(username: String) {
        _isLoading2.value = true
        apiService.getFollowing(username).enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading2.value = false
                if (response.isSuccessful) {
                    _following.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading2.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }


//    init {
//        searchUserDetail.observeForever { username ->
//            if (!username.isNullOrEmpty()) {
//                findDetail(username)
//            }
//        }
//    }


//    fun setSearchUserDetail(username: String) {
//        searchUserDetail.value = username
//    }



//    private fun findDetail(username: String) {
////        _isLoading2.value = true
//
//        val client = RetrofitClient.getApiService(ApiService::class.java).getDetailUser(username)
//        client.enqueue(object : Callback<DetailUserResponse> {
//            override fun onResponse(
//                call: Call<DetailUserResponse>,
//                response: Response<DetailUserResponse>
//            ) {
////                _isLoading2.value = false
//                if (response.isSuccessful) {
//
//                    _userDetail.value = response.body()
//
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
////                _isLoading2.value = false
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }









//    private val _detailUser = MutableLiveData<DetailUserResponse>()
//    val detailUser: LiveData<DetailUserResponse> = _detailUser
//    private val _isLoading2 = MutableLiveData<Boolean>()
//    val isLoading2: LiveData<Boolean> = _isLoading2
//    companion object {
//        private const val TAG2 = "MainViewModel"
//        private const val USER_ID2 = "fajar"
//    }
//
//    init {
//        findDetail()
//    }
//
//
//    private fun findDetail() {
//        _isLoading2.value = true
//
//        val client = RetrofitClient.getApiService().getDetailUser()
//        client.enqueue(object : Callback<DetailUserResponse> {
//            override fun onResponse(
//                call: Call<SearchUserResponse>,
//                response: Response<SearchUserResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//
//                    _listUser.value = response.body()?.items
//
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }
}