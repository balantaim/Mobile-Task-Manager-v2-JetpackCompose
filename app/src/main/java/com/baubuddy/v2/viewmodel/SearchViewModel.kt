package com.baubuddy.v2.viewmodel

import androidx.lifecycle.ViewModel
import com.baubuddy.v2.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

}