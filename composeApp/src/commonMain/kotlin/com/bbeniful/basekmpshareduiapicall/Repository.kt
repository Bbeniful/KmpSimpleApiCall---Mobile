package com.bbeniful.basekmpshareduiapicall

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getTodos(): Flow<List<Todo>>

    suspend fun getTodoById(id: Int): Flow<Todo>
}

class RepositoryImpl(
    private val datasource: Datasource
): Repository {
    override suspend fun getTodos() = datasource.getTodos()

    override suspend fun getTodoById(id: Int) = datasource.getTodoById(id = id)
}