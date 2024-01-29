package com.jer.githubappsederhana.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GitHub::class], version = 1)
abstract class GithubRoomDatabase : RoomDatabase() {

    abstract fun githubDao(): GithubDao
    companion object {
        @Volatile
        private var INSTANCE: GithubRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): GithubRoomDatabase {
            if (INSTANCE == null) {
                synchronized(GithubRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        GithubRoomDatabase::class.java, "github_database")
                        .build()
                }
            }
            return INSTANCE as GithubRoomDatabase
        }
    }

}