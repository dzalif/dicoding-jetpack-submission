package com.kucingselfie.jetpacksubmission.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kucingselfie.jetpacksubmission.ui.movie.detail.DetailViewModel
import com.kucingselfie.jetpacksubmission.ui.movie.list.MovieViewModel
import com.kucingselfie.jetpacksubmission.ui.tvshow.TvshowViewModel
import com.kucingselfie.jetpacksubmission.viewmodel.MovieViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(movieViewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvshowViewModel::class)
    abstract fun bindTvShowViewModel(tvShowViewModel: TvshowViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MovieViewModelFactory): ViewModelProvider.Factory
}