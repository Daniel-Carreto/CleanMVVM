package com.danycarreto.cleanmvvm.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danycarreto.cleanmvvm.data.local.entity.UserRepo

@Dao
interface UserRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRepo(userRepo: UserRepo)

    @Query("SELECT * FROM user_repo_table")
    suspend fun getUserRepo(): List<UserRepo>?

    @Query("SELECT * FROM user_repo_table")
    fun getUserRepoLiveData(): LiveData<List<UserRepo>>?

    @Query("SELECT * FROM user_repo_table where name = :name")
    fun getUserByName(name:String): LiveData<List<UserRepo>>?

    //@Query("SELECT * FROM user_repo_table where name = :name")
    //suspend fun getUserByName(name:String): List<UserRepo>

}