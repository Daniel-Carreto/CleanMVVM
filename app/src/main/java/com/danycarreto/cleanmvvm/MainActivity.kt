package com.danycarreto.cleanmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.danycarreto.cleanmvvm.data.network.repositories.UserNetworkRepository
import com.danycarreto.cleanmvvm.databinding.ActivityMainBinding
import com.danycarreto.cleanmvvm.domain.usecases.GitHubUserUseCase
import com.danycarreto.cleanmvvm.presentation.search.viewmodel.SearchReposViewModel
import com.danycarreto.cleanmvvm.presentation.search.viewmodel.SearchReposViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.reposViewModel = ViewModelProviders.of(
            this,
            SearchReposViewModelFactory(
                GitHubUserUseCase(UserNetworkRepository())
            )
        )
            .get(SearchReposViewModel::class.java)
    }
}
