package com.property.home.data.network.di

import com.property.home.data.network.HomeApi
import com.property.home.data.network.datasource.GetPropertyListDataSource
import com.property.home.data.network.datasource.GetPropertyListDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeDatasourceModule {

    @Singleton
    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideInitialResponseDataSource(apiService: HomeApi): GetPropertyListDataSource {
        return GetPropertyListDataSourceImpl(apiService)
    }

}