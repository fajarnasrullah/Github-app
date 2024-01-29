package com.jer.githubappsederhana.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface GithubDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(github: GitHub)
    @Update
    fun update(github: GitHub)
    @Delete
    fun delete(github: GitHub)
//    @Query("SELECT * from github ORDER BY id ASC")
//    fun getAllGithub(): LiveData<List<GitHub>>



    @Query("SELECT * FROM GitHub WHERE username = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<List<GitHub>>





//    @Query("SELECT EXISTS(SELECT * FROM github WHERE username = :username AND bookmarked = 1)")
//    fun isUserBookmarked(username: String): Boolean


    @Query("SELECT * FROM github")
    fun getBookmarkedUsers(): LiveData<List<GitHub>>


//    @Query("DELETE FROM github WHERE bookmarked = 0")
//    fun deleteAll()
//
//    @Query("INSERT INTO github WHERE bookmarked = 1")
//    fun insertAll()


}
