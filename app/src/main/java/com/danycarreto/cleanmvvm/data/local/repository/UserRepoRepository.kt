package com.danycarreto.cleanmvvm.data.local.repository

import com.danycarreto.cleanmvvm.data.local.dao.UserRepoDao
import com.danycarreto.cleanmvvm.data.local.entity.UserRepo

class UserRepoRepository(val userRepoDao:UserRepoDao) {

    val allRepos = userRepoDao.getUserRepoLiveData()

    suspend fun insert(userRepo: UserRepo){
        userRepoDao.insertUserRepo(userRepo)
    }

    suspend fun getRepoByName(name: String): List<UserRepo>? {
        return userRepoDao.getUserByName(name)
    }

}