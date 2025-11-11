package com.bbeniful.basekmpshareduiapicall

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Datasource {

    suspend fun getTodos(): Flow<List<Todo>>

    suspend fun getTodoById(id: Int): Flow<Todo>
}

class DatasourceImpl(private val client: HttpClient) : Datasource {

    override suspend fun getTodoById(id: Int) = flow {
        emit(client.get("").body<Todo>())
    }

    override suspend fun getTodos() = flow {
        emit(client.get("https://jsonplaceholder.typicode.com/todos").body<List<Todo>>())
    }

}