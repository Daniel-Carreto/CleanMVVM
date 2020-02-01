package com.danycarreto.cleanmvvm.data.network.repositories

import com.danycarreto.cleanmvvm.data.network.NetworkConnection
import com.danycarreto.cleanmvvm.data.network.models.response.GithubResponse
import com.danycarreto.cleanmvvm.domain.repositories.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserNetworkRepository : UserRepository {
    override fun getUserRepos(user: String): Single<List<GithubResponse>> {
        return NetworkConnection().getNetworkConnection()
            .getUserRepos(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}