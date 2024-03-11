package com.property.home.data.domainimpl.usecase

import com.property.core.utils.IODispatcher
import com.property.home.data.domainimpl.mapper.toDomainModel
import com.property.home.data.network.datasource.GetPropertyListDataSource
import com.property.core.model.Property
import com.property.home.domain.usecase.GetPropertyListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class GetPropertyListUseCaseImpl @Inject constructor(
    private val dataSource: GetPropertyListDataSource,
    @IODispatcher private val dispatcher: CoroutineContext
) : GetPropertyListUseCase {

    override fun getPropertyList(): Flow<List<Property>> =
        flow {
            val initialData = dataSource.getPropertyList().toDomainModel()
            emit(initialData)
        }.flowOn(dispatcher)
}