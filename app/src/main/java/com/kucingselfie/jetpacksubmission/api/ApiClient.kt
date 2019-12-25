package com.kucingselfie.jetpacksubmission.api

import com.kucingselfie.jetpacksubmission.common.Constant.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun create(): ApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}