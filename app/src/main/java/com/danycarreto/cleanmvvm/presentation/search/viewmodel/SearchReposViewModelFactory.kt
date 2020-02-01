package com.danycarreto.cleanmvvm.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danycarreto.cleanmvvm.domain.usecases.GitHubUserUseCase

class SearchReposViewModelFactory(
    val userRepository: GitHubUserUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchReposViewModel(userRepository) as T
    }

}