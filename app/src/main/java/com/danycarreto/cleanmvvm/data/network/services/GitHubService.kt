package com.danycarreto.cleanmvvm.data.network.services

import com.danycarreto.cleanmvvm.data.network.models.response.GithubResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user:String): Single<GithubResponse>
    //Observable
    //Single
    //Flowable
}