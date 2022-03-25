package com.example.data.network

import com.example.data.constants.Constants.BASE_URL
import com.example.data.network.remote.apiservices.CharacterApiService
import com.example.data.network.remote.apiservices.EpisodeApiService
import com.example.data.network.remote.apiservices.FilterApiService
import com.example.data.network.remote.apiservices.LocationApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient: OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(providerInspector())
            .connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build()

    private fun providerInspector(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val retrofitClient =
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

    fun providerApiService(): CharacterApiService {
        return retrofitClient.create(CharacterApiService::class.java)
    }

    fun providerEpisodeApiService(): EpisodeApiService {
        return retrofitClient.create(EpisodeApiService::class.java)
    }

    fun provideLocationService(): LocationApiService {
        return retrofitClient.create(LocationApiService::class.java)
    }

    fun provideFilterApiService(): FilterApiService {
        return retrofitClient.create(FilterApiService::class.java)
    }
}