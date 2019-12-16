package com.kucingselfie.dicodingjetpacksubmission1.di

import com.kucingselfie.dicodingjetpacksubmission1.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}