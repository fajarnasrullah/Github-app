package com.jer.githubappsederhana.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.jer.githubappsederhana.BuildConfig
import com.jer.githubappsederhana.database.GitHub
import com.jer.githubappsederhana.database.GithubDao
import com.jer.githubappsederhana.database.GithubRoomDatabase
import com.jer.githubappsederhana.response.SearchUserResponse
import com.jer.githubappsederhana.retrofit.ApiService
import com.jer.githubappsederhana.utils.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Constructor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UsersRepository  (


//    private val apiService : ApiService,

    application: Application
//    private val appExecutors: AppExecutors

    ) {


    private var githubDao : GithubDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private val result = MediatorLiveData<Result<List<GitHub>>>()
//
//    fun getHeadlineNews(): LiveData<Result<List<GitHub>>> {
//        result.value = Result.Loading
//        val client = apiService.searchUsers(BuildConfig.API_KEY)
//        client.enqueue(object : Callback<SearchUserResponse> {
//            override fun onResponse(call: Call<SearchUserResponse>, response: Response<SearchUserResponse>) {
//                if (response.isSuccessful) {
//                    val users = response.body()?.items
//                    val usersList = ArrayList<GitHub>()
//                    appExecutors.diskIO.execute {
//                        users?.forEach { user ->
////                            val isBookmarked = githubDao.isUserBookmarked(user.login)
//                            val userrr = GitHub(
//                                user.login,
//                                user.avatarUrl
////                                isBookmarked
//                            )
//                            usersList.add(userrr)
//                        }
//                        githubDao.delete(GitHub())
//                        githubDao.insert(GitHub())
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
//                result.value = Result.Error(t.message.toString())
//            }
//        })
////        val localData = githubDao.getNews()
////        result.addSource(localData) { newData: List<NewsEntity> ->
////            result.value = Result.Success(newData)
////        }
//        return result
//    }


//    fun getHeadlineNews(): LiveData<Result<List<GitHub>>> {
//        val result = MediatorLiveData<Result<List<GitHub>>>()
//        result.value = Result.Loading
//
//        val client = apiService.searchUsers(BuildConfig.API_KEY)
//        client.enqueue(object : Callback<SearchUserResponse> {
//            override fun onResponse(call: Call<SearchUserResponse>, response: Response<SearchUserResponse>) {
//                if (response.isSuccessful) {
//                    val users = response.body()?.items ?: emptyList()
//                    val usersList = mutableListOf<GitHub>()
//
//                    appExecutors.diskIO.execute {
//                        users.forEach { user ->
//                            val isBookmarked = githubDao.getFavoriteUserByUsername(user.login) != null
//                            val githubUser = GitHub(user.login, user.avatarUrl, isBookmarked)
//                            usersList.add(githubUser)
//                        }
//                        githubDao.deleteAll()
//                        githubDao.insert(usersList)
//                    }
//
//                    result.postValue(Result.Success(usersList))
//                } else {
//                    result.postValue(Result.Error("Failed to load data from server"))
//                }
//            }
//
//            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
//                result.postValue(Result.Error(t.message ?: "Unknown error"))
//            }
//        })
//
//        return result
//    }



//    fun getHeadliNewneNews(): LiveData<Result<List<GitHub>>> {
//        result.value = Result.Loading
//        val client = apiService.searchUsers(BuildConfig.API_KEY)
//        client.enqueue(object : Callback<SearchUserResponse> {
//            override fun onResponse(call: Call<SearchUserResponse>, response: Response<SearchUserResponse>) {
//                if (response.isSuccessful) {
//                    val users = response.body()?.items
//                    val usersList = ArrayList<GitHub>()
//                    appExecutors.diskIO.execute {
//                        users?.forEach { user ->
//                            val githubUser = GitHub(
//                                user.login,
//                                user.avatarUrl
//                            )
//                            githubDao.insert(githubUser)
//                            usersList.add(githubUser)
//                        }
//                        result.postValue(Result.Success(usersList))
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
//                result.postValue(Result.Error(t.message.toString()))
//            }
//        })
//        return result
//    }









    init {
        val db = GithubRoomDatabase.getDatabase(application)
        githubDao = db.githubDao()
    }

//
//    fun getFavoriteUsers(username: String): LiveData<List<GitHub>> {
//        return githubDao.getFavoriteUserByUsername(username)
//    }

    fun getFavoriteUsers(username: String): LiveData<List<GitHub>> = githubDao.getFavoriteUserByUsername(username)



//    fun insert(user: GitHub) {
//        appExecutors.diskIO.execute { githubDao.insert(user) }
//    }
//
//    fun delete(user: GitHub) {
//        appExecutors.diskIO.execute { githubDao.delete(user) }
//    }



    fun insert(users: GitHub) {
        executorService.execute { githubDao.insert(users) }
    }
    fun delete(github: GitHub) {
        executorService.execute { githubDao.delete(github) }
    }

//    fun getBookmarkedUsers(): LiveData<List<GitHub>> {
//        return githubDao.getBookmarkedUsers()
//    }

    fun getBookmarkedUsers(): LiveData<List<GitHub>> = githubDao.getBookmarkedUsers()



//    fun setBookmarkedUsers(users: GitHub, bookmarkState: Boolean) {
//        appExecutors.diskIO.execute {
//            users.isBookmarked = bookmarkState
//            githubDao.update(users)
//        }
//    }


//    companion object {
//        @Volatile
//        private var instance: UsersRepository? = null
//        fun getInstance(
//            apiService: ApiService,
//            githubDao: GithubDao ,
////            appExecutors: AppExecutors,
//            application: Application,
//
//        ): UsersRepository =
//            instance ?: synchronized(this) {
//                instance ?: UsersRepository( apiService, githubDao, application)
//            }.also { instance = it }
//    }
}