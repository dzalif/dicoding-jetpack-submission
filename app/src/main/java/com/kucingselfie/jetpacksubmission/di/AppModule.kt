package com.kucingselfie.jetpacksubmission.di

import com.kucingselfie.jetpacksubmission.api.ApiService
import com.kucingselfie.jetpacksubmission.common.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    @Singleton
    fun provideApiService() : ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }
}