package com.kucingselfie.jetpacksubmission.data.source.remote;

import android.os.Handler;

import com.kucingselfie.jetpacksubmission.api.ApiClient;
import com.kucingselfie.jetpacksubmission.api.response.MovieResponse;
import com.kucingselfie.jetpacksubmission.api.response.TVShowResponse;
import com.kucingselfie.jetpacksubmission.model.DetailModel;
import com.kucingselfie.jetpacksubmission.model.DetailTvShowModel;
import com.kucingselfie.jetpacksubmission.model.Movie;
import com.kucingselfie.jetpacksubmission.model.TVShow;
import com.kucingselfie.jetpacksubmission.util.EspressoIdlingResourceJava;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kucingselfie.jetpacksubmission.common.Constant.API_KEY;

@Singleton
public class RemoteRepositoryJava {
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private ApiClient apiClient;
    private Handler handler = new Handler();

    public RemoteRepositoryJava(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public void getMovies(final LoadMoviesCallback callback) {
        EspressoIdlingResourceJava.increment();
        handler.postDelayed(() -> apiClient.create().getMovies(API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body() != null ? response.body().getResults() : null);
                    EspressoIdlingResourceJava.decrement();
                }
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                callback.onError(t.getMessage());
            }
        }), SERVICE_LATENCY_IN_MILLIS);
    }

    public void getTVShows(final LoadTvShowsCallback callback) {
        EspressoIdlingResourceJava.increment();
        handler.postDelayed(() -> apiClient.create().getTvShows(API_KEY).enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVShowResponse> call, @NotNull Response<TVShowResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body() != null ? response.body().getResults() : null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<TVShowResponse> call, @NotNull Throwable t) {
                callback.onError(t.getMessage());
            }
        }), SERVICE_LATENCY_IN_MILLIS);
    }

    public void getMovieDetail(int id, LoadDetailCallback callback) {
        EspressoIdlingResourceJava.increment();
        handler.postDelayed(() -> apiClient.create().getMovieDetail(id, API_KEY).enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(@NotNull Call<DetailModel> call, @NotNull Response<DetailModel> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                    EspressoIdlingResourceJava.decrement();
                }
            }

            @Override
            public void onFailure(@NotNull Call<DetailModel> call, @NotNull Throwable t) {
                callback.onError(t.getMessage());
            }
        }), SERVICE_LATENCY_IN_MILLIS);
    }

    public void getTVShowDetail(int id, LoadDetailTvShowCallback callback) {
        EspressoIdlingResourceJava.increment();
        handler.postDelayed(() -> {
            apiClient.create().getTvDetail(id, API_KEY).enqueue(new Callback<DetailTvShowModel>() {
                @Override
                public void onResponse(@NotNull Call<DetailTvShowModel> call, @NotNull Response<DetailTvShowModel> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    }
                }

                @Override
                public void onFailure(@NotNull Call<DetailTvShowModel> call, @NotNull Throwable t) {
                    callback.onError(t.getMessage());
                }
            });
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadMoviesCallback {
        void onSuccess(List<Movie> response);
        void onError(String message);
    }

    public interface LoadTvShowsCallback {
        void onSuccess(List<TVShow> response);
        void onError(String message);
    }

    public interface LoadDetailCallback {
        void onSuccess(DetailModel response);
        void onError(String message);
    }

    public interface LoadDetailTvShowCallback {
        void onSuccess(DetailTvShowModel response);
        void onError(String message);
    }
}
