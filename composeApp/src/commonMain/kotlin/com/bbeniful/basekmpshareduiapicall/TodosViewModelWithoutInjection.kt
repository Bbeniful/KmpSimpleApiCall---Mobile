package com.bbeniful.basekmpshareduiapicall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodosViewModelWithoutInjection(private val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow(TodoUIState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            updateLoading(value = true)
            repository.getTodos().collectLatest { list ->
                updateLoading(value = false)
                updateList(list = list)
            }
        }
    }

    private fun updateList(list: List<Todo>) {
        _uiState.update { state ->
            state.copy(
                todos = list
            )
        }
    }

    private fun updateLoading(value: Boolean) = _uiState.update { it.copy(isLoading = value) }
}