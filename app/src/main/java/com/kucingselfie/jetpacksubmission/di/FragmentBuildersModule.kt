package com.kucingselfie.jetpacksubmission.di

import com.kucingselfie.jetpacksubmission.movie.detail.DetailMovieFragment
import com.kucingselfie.jetpacksubmission.movie.list.MovieFragment
import com.kucingselfie.jetpacksubmission.tvshow.detail.DetailTvShowFragment
import com.kucingselfie.jetpacksubmission.tvshow.list.TVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment(): TVShowFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailTvShowFragment(): DetailTvShowFragment

}