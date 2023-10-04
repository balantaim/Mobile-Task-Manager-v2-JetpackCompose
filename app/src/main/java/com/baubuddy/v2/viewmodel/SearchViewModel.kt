package com.baubuddy.v2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baubuddy.v2.model.Task
import com.baubuddy.v2.network.GetDataFromTheServer
import com.baubuddy.v2.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList = _taskList.asStateFlow()
    var filteredList = mutableListOf<Task>()

//    private val _searchText = MutableStateFlow("")
//    val searchText = _searchText.asStateFlow()
//
//    private val _isSearching = MutableStateFlow(false)
//    val isSearching = _isSearching.asStateFlow()


    init{
        viewModelScope.launch(Dispatchers.IO) {
            //Get all data from the server
            val data = GetDataFromTheServer().run()
            updateDB(data)

            repository.readStoredData().distinctUntilChanged()
                .collect{ myTasks ->
                    if(myTasks.isNullOrEmpty()) {
                        Log.d("SearchViewModel", "task: Empty ")
                    }else{
                        _taskList.value = myTasks
                    }
                }
        }
    }

    fun searchEvent(){

    }

    suspend fun updateDB(tasks: List<Task>){
        if(tasks.isNotEmpty()){
            //Clean the old data
            repository.dropMyStoredData()
            //Inject new records
            for(task in tasks){
                repository.addTask(task)
            }
        }else{
            Log.d("MainViewModel", "updateDB: You are using offline DB")
        }
    }

}