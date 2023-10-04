package com.baubuddy.v2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baubuddy.v2.model.Task
import com.baubuddy.v2.network.GetDataFromTheServer
import com.baubuddy.v2.repository.TaskRepository
import com.baubuddy.v2.view.details.DetailsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    private val _currentTask = MutableStateFlow<List<Task>>(emptyList())
    val currentTask = _currentTask.asStateFlow()
//    private var task: Task = TODO()
//
//    suspend fun getInfo(id: String): Task {
//       task = repository.getTaskById(id)
//        return task
//    }

//    init{
//        viewModelScope.launch(Dispatchers.IO) {
//
//        }
//    }
    fun getTaskById(id: String) = viewModelScope.launch { repository.getTaskById(id) }
}