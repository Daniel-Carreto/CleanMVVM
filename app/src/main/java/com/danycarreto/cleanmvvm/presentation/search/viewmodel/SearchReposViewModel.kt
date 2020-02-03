package com.danycarreto.cleanmvvm.presentation.search.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danycarreto.cleanmvvm.data.local.RepoRoomDatabase
import com.danycarreto.cleanmvvm.data.local.entity.UserRepo
import com.danycarreto.cleanmvvm.data.local.repository.UserRepoRepository
import com.danycarreto.cleanmvvm.data.network.models.response.GithubResponse
import com.danycarreto.cleanmvvm.domain.model.Repo
import com.danycarreto.cleanmvvm.domain.usecases.GitHubUserUseCase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchReposViewModel(
    val userRepository: GitHubUserUseCase,
    private val repoRoomDatabase: RepoRoomDatabase
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val repoMutableLiveData = MutableLiveData<List<Repo>>()
    val repoMediator = MediatorLiveData<List<UserRepo>>()
    val userInput = ObservableField<String>("")

    val userRepoRepository = UserRepoRepository(repoRoomDatabase.userRepoDao())

    fun getUserRepos(user: String) {
        compositeDisposable.add(
            userRepository.getUserRepos(user).subscribe({
                val repoList = ArrayList<Repo>()
                it.forEach {
                    insertData(it)
                    repoList.add(
                        Repo(
                            url = it.url.orEmpty(),
                            name = it.full_name.orEmpty(),
                            description = it.description.orEmpty()
                        )

                    )
                    println("Repo Name: ${it.name.orEmpty()}")
                }
                repoMutableLiveData.value = repoList
            }, {
                println("Error Message: ${it.message.orEmpty()}")
            })
        )

    }

    fun insertData(githubResponse: GithubResponse) = viewModelScope.launch{
        //Cuando muchos viewmodel interactuen (GlobalScope)
        userRepoRepository.insert(
            UserRepo(
                description = githubResponse.description.orEmpty(),
                name = githubResponse.name.orEmpty(),
                fullName =githubResponse.full_name.orEmpty(),
                url = githubResponse.url.orEmpty()
            )
        )
        //process()
        /*withContext(Dispatchers.Main){
            activity, fragment, ui en general
        }*/
    }

    fun onSearchClick() {
        getUserRepos(userInput.get().orEmpty())
    }


    fun getDataRepoFromMediator(){
        userRepoRepository.allRepos?.let {
            repoMediator.addSource(it, Observer {
                //Datos de la base de datos.
                it.forEach { 
                    println("MediatorLiveData ${it.name}")
                }
            })
        }
    }

    //jakeWharton

}