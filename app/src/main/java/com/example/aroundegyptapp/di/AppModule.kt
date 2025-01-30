package com.example.app.di

import com.example.aroundegyptapp.data.network.ApiService
import com.example.aroundegyptapp.data.repo.DetailsRepo
import com.example.aroundegyptapp.data.repo.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://aroundegypt.34ml.com/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(apiService: ApiService): HomeRepository {
        return HomeRepository(apiService)
    }
    @Provides
    @Singleton
    fun provideDetailsRepository(apiService: ApiService): DetailsRepo {
        return DetailsRepo(apiService)
    }
}
