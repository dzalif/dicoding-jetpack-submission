package com.kucingselfie.jetpacksubmission.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kucingselfie.jetpacksubmission.movie.detail.DetailMovieViewModel
import com.kucingselfie.jetpacksubmission.movie.list.MovieViewModel
import com.kucingselfie.jetpacksubmission.tvshow.detail.DetailTvShowViewModel
import com.kucingselfie.jetpacksubmission.tvshow.list.TvShowViewModel
import com.kucingselfie.jetpacksubmission.viewmodel.MovieViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel::class)
    abstract fun bindTvShowViewModel(viewModel: TvShowViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailTvShowViewModel::class)
    abstract fun bindDetailTvShowViewModel(viewModel: DetailTvShowViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MovieViewModelFactory): ViewModelProvider.Factory
}