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

class TodosViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TodoUIState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTodos().collectLatest { list ->
                println("list: $list")
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

}