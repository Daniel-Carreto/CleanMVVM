package com.danycarreto.cleanmvvm.data.network

import com.danycarreto.cleanmvvm.data.network.services.GitHubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.github.com/"

class NetworkConnection {


    fun getNetworkConnection(): GitHubService {
        //Mostrar en el log las peticiones
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        //Objeto Retrofit que consultara a Githubservice
        //Esta configurado para convertir los objetos con GSON
        //La respuesta sera recibida con RXJava.
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create<GitHubService>(GitHubService::class.java)
    }

}