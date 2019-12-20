package com.kucingselfie.jetpacksubmission.di

import com.kucingselfie.jetpacksubmission.ui.movie.list.MovieFragment
import com.kucingselfie.jetpacksubmission.ui.tvshow.TVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment(): TVShowFragment
}