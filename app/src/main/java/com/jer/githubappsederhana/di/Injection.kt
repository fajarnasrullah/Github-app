package com.jer.githubappsederhana.di

import android.app.Application
import android.content.Context
import com.jer.githubappsederhana.data.UsersRepository
import com.jer.githubappsederhana.database.GithubRoomDatabase
import com.jer.githubappsederhana.retrofit.ApiService
import com.jer.githubappsederhana.retrofit.RetrofitClient
import com.jer.githubappsederhana.utils.AppExecutors

object Injection {

//    fun provideRepository(context: Context): UsersRepository {
//        val apiService = RetrofitClient.getApiService(ApiService::class.java)
//        val database = GithubRoomDatabase.getDatabase(context)
//        val dao = database.githubDao()
////        val appExecutors = AppExecutors()
//        val application = Application()
//
//        return UsersRepository.getInstance( apiService, dao, application )
//    }

}



