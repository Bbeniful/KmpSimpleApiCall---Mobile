package com.bbeniful.basekmpshareduiapicall

import androidx.compose.runtime.Stable

@Stable
data class TodoUIState(
 val todos: List<Todo> = emptyList(),
    val isLoading: Boolean = false

)