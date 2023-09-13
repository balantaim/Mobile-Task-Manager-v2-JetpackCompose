package com.baubuddy.v2.repository

import com.baubuddy.v2.model.TaskDao
import com.baubuddy.v2.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    suspend fun addTask(task: Task) = taskDao.addTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    suspend fun dropMyStoredData() = taskDao.dropMyStoredData()
    suspend fun getTaskById(id: String): Task = taskDao.getTaskById(id)
    fun readStoredData(): Flow<List<Task>> = taskDao.readStoredData()
        .flowOn(Dispatchers.IO)
        .conflate()
}