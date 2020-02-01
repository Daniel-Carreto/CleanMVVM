package com.danycarreto.cleanmvvm.domain.usecases

import com.danycarreto.cleanmvvm.data.network.models.response.GithubResponse
import com.danycarreto.cleanmvvm.domain.repositories.UserRepository
import io.reactivex.Observable

class GitHubUserUseCase(private val userRepository: UserRepository) {


    fun getUserRepos(user: String): Observable<List<GithubResponse>> {
        return userRepository.getUserRepos(user)
            .toObservable()
            .doOnNext {
                if (it.isNotEmpty()) {
                    return@doOnNext
                } else {
                    throw Exception("Error con los datos")
                }
            }.doOnError {
                if (it.message.orEmpty().isNotEmpty()) {
                    throw Exception("No se establece conexión a internet")
                } else {
                    return@doOnError
                }
            }
    }


}