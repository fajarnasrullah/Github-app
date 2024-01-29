package com.jer.githubappsederhana.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jer.githubappsederhana.data.UsersRepository
import com.jer.githubappsederhana.database.GitHub
import com.jer.githubappsederhana.database.GithubDao
import com.jer.githubappsederhana.response.DetailUserResponse

class GithubViewModel(application: Application) : ViewModel() {
//    fun getHeadlineNews() = usersRepository.getHeadlineNews()

    private val usersRepository: UsersRepository = UsersRepository(application)
    private val username: String = ""
    fun getAllGithub(): LiveData<List<GitHub>> = usersRepository.getFavoriteUsers(username)

    fun getAllUsers(): LiveData<List<GitHub>> = usersRepository.getBookmarkedUsers()


//    fun getFavUsers(username:String) = usersRepository.getFavoriteUsers(username)
//
    fun getBookmarkedUsers() = usersRepository.getBookmarkedUsers()
//    fun saveUsers(users: GitHub) {
//        usersRepository.setBookmarkedUsers(users, true)
//    }
//    fun deleteUsers(users: GitHub) {
//        usersRepository.setBookmarkedUsers(users, false)
//    }


    fun insert(users: GitHub) {
        usersRepository.insert(users)
    }
    fun delete(users: GitHub) {
        usersRepository.delete(users)
    }

}