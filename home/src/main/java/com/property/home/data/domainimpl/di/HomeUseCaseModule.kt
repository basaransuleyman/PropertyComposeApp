package com.property.home.data.domainimpl.di

import com.property.core.utils.IODispatcher
import com.property.home.data.domainimpl.usecase.GetPropertyListUseCaseImpl
import com.property.home.data.network.datasource.GetPropertyListDataSource
import com.property.home.domain.usecase.GetPropertyListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object HomeUseCaseModule {

    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideGetInitialHomeUseCase(
        dataSource: GetPropertyListDataSource,
        @IODispatcher dispatcher: CoroutineContext
    ): GetPropertyListUseCase {
        return GetPropertyListUseCaseImpl(dataSource, dispatcher)
    }
}