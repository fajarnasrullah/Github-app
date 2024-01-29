package com.jer.githubappsederhana.retrofit

import com.jer.githubappsederhana.response.DetailUserResponse
import com.jer.githubappsederhana.response.FollowUserResponseItem
import com.jer.githubappsederhana.response.ItemsItem
import com.jer.githubappsederhana.response.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String
    ) : Call<SearchUserResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>

}