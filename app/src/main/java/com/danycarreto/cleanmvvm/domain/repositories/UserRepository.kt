package com.danycarreto.cleanmvvm.domain.repositories

import com.danycarreto.cleanmvvm.data.network.models.response.GithubResponse
import io.reactivex.Single

interface UserRepository {
    fun getUserRepos(user: String): Single<List<GithubResponse>>
}