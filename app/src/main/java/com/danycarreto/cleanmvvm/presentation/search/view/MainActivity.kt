package com.danycarreto.cleanmvvm.presentation.search.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.danycarreto.cleanmvvm.R
import com.danycarreto.cleanmvvm.data.network.repositories.UserNetworkRepository
import com.danycarreto.cleanmvvm.databinding.ActivityMainBinding
import com.danycarreto.cleanmvvm.domain.usecases.GitHubUserUseCase
import com.danycarreto.cleanmvvm.presentation.search.adapter.ReposAdapter
import com.danycarreto.cleanmvvm.presentation.search.viewmodel.SearchReposViewModel
import com.danycarreto.cleanmvvm.presentation.search.viewmodel.SearchReposViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        activityMainBinding.reposViewModel = ViewModelProviders.of(
            this,
            SearchReposViewModelFactory(
                GitHubUserUseCase(UserNetworkRepository())
            )
        ).get(SearchReposViewModel::class.java)


        activityMainBinding.reposViewModel?.repoMutableLiveData?.observe(
            this,
            Observer { repolist ->
                activityMainBinding.rvRepos?.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = ReposAdapter(repolist)
                }
            })
    }
}
