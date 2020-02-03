package com.danycarreto.cleanmvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danycarreto.cleanmvvm.data.local.dao.UserRepoDao
import com.danycarreto.cleanmvvm.data.local.entity.UserRepo

@Database(entities = [
UserRepo::class
], version = 1)
abstract class RepoRoomDatabase: RoomDatabase() {
    abstract fun userRepoDao(): UserRepoDao


    companion object {
        @Volatile
        private var INSTANCE: RepoRoomDatabase? = null

        fun getDatabase(context: Context): RepoRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RepoRoomDatabase::class.java,
                    "repo_database.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}