package com.pefacel.taskmanagementapp.tasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.pefacel.taskmanagementapp.tasks.data.database.entity.TaskEntity
import com.pefacel.taskmanagementapp.tasks.domain.DeleteTaskUseCase
import com.pefacel.taskmanagementapp.tasks.domain.GetTasksUseCase
import com.pefacel.taskmanagementapp.tasks.domain.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val _task = MutableLiveData<List<TaskEntity>>()
    private val _isLoading = MutableLiveData<Boolean>(false)
    private val _currentTask = MutableLiveData<TaskEntity?>(null)

    val tasks: LiveData<List<TaskEntity>> = _task
    val currentTask: LiveData<TaskEntity?> = _currentTask
    val isLoading: LiveData<Boolean> = _isLoading

    fun getTasks() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = getTasksUseCase()
            if (result.isNotEmpty()) {
                _task.postValue(result)
            }
            _isLoading.postValue(false)
        }
    }

    fun setCurrentTask(task: TaskEntity?) {
        _currentTask.postValue(task)
    }

    fun updateCheckboxTask(task: TaskEntity) {
        val newTask = task.copy(completed = !task.completed)
        viewModelScope.launch {
            updateTaskUseCase(newTask)
            getTasks()
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
            getTasks()
            _currentTask.postValue(null)
        }
    }

}