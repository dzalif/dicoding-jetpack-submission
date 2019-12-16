package com.kucingselfie.dicodingjetpacksubmission1.di

import com.kucingselfie.dicodingjetpacksubmission1.api.ApiService
import com.kucingselfie.dicodingjetpacksubmission1.util.LiveDataCallAdapterFactory
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }
}