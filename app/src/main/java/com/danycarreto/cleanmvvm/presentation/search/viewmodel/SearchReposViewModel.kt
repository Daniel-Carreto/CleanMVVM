package com.danycarreto.cleanmvvm.presentation.search.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.danycarreto.cleanmvvm.domain.usecases.GitHubUserUseCase
import io.reactivex.disposables.CompositeDisposable

class SearchReposViewModel(
    val userRepository: GitHubUserUseCase
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val userInput = ObservableField<String>("")

    fun getUserRepos(user: String) {
        compositeDisposable.add(
            userRepository.getUserRepos(user).subscribe({
                it.forEach {
                    println("Repo Name: ${it.name.orEmpty()}")
                }
            }, {
                println("Error Message: ${it.message.orEmpty()}")
            })
        )

    }


    fun onSearchClick() {
        getUserRepos(userInput.get().orEmpty())
    }

    //jakeWharton

}