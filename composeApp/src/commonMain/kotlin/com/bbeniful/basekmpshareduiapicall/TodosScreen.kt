package com.bbeniful.basekmpshareduiapicall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TodosScreen(viewModel: TodosViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier.padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            state.todos
        ) { todo ->
            Card(
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    val isCompleted = if (todo.completed) "Completed" else "Incompleted"
                    val completedColor = if (todo.completed) Color.Green else Color.Red
                    Text(text = todo.title)
                    Text(text = isCompleted, color = completedColor)
                }

            }
        }
    }
}

@Composable
fun TodosScreenWithouthViewModel() {
    val httpClient = remember { createClient() }
    val datasource = remember { DatasourceImpl(client = httpClient) }
    val repository = remember { RepositoryImpl(datasource = datasource) }
    val viewModel = remember { TodosViewModelWithoutInjection(repository = repository) }

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    state.todos
                ) { todo ->
                    Card(
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 12.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            val isCompleted = if (todo.completed) "Completed" else "Incompleted"
                            val completedColor = if (todo.completed) Color.Green else Color.Red
                            Text(text = todo.title)
                            Text(text = isCompleted, color = completedColor)
                        }

                    }
                }
            }
        }
    }


}