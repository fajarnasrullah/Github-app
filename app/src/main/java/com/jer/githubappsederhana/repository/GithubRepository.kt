package com.jer.githubappsederhana.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.jer.githubappsederhana.database.GitHub
import com.jer.githubappsederhana.database.GithubDao
import com.jer.githubappsederhana.database.GithubRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
//
//class GithubRepository(application: Application) {
//
//    private val mGithubDao: GithubDao
//    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
//    init {
//        val db = GithubRoomDatabase.getDatabase(application)
//        mGithubDao = db.githubDao()
//    }
//    fun getAllGithub(): LiveData<List<GitHub>> = mGithubDao.getFavoriteUserByUsername(username=toString())
//    fun insert(github: List<GitHub>) {
//        executorService.execute { mGithubDao.insert(github) }
//    }
//    fun delete(github: List<GitHub>) {
//        executorService.execute { mGithubDao.delete(github) }
//    }
////    fun update(github: GitHub) {
////        executorService.execute { mGithubDao.update(github) }
////    }
//
//}