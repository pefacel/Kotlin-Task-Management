package com.pefacel.taskmanagementapp.tasks.data


import com.pefacel.taskmanagementapp.tasks.core.DateHelper
import com.pefacel.taskmanagementapp.tasks.data.database.TaskDao
import com.pefacel.taskmanagementapp.tasks.data.database.entity.TaskEntity
import com.pefacel.taskmanagementapp.tasks.data.network.TaskApiService
import com.pefacel.taskmanagementapp.tasks.data.network.model.TaskModel
import javax.inject.Inject


class TaskRepository @Inject constructor(
    private val taskDao: TaskDao, private val taskApiService: TaskApiService
) {

    suspend fun getTasksFromNetwork(): List<TaskModel> {
        val response = taskApiService.getTasks()
        return response.body()?.data ?: emptyList()
    }

    suspend fun getTasksFromDatabase(): List<TaskEntity> {
        return taskDao.getAllTask()
    }

    suspend fun deleteTask(taskEntity: TaskEntity) {
        return taskDao.deleteTask(taskEntity)
    }

    suspend fun saveTasksOnDatabase(): List<TaskEntity> {
        val tasksFromNetwork = getTasksFromNetwork()

        tasksFromNetwork.map { task ->
            val taskEntity = TaskEntity(
                uuid = task.id,
                content = task.content,
                image = task.image,
                completed = task.isDone,
                title = task.title,
                creationDate = DateHelper().formatDate(task.creation_date),
                dueDate = DateHelper().formatDate(task.due_date)

            )
            taskDao.saveTask(taskEntity)
        }
        return getTasksFromDatabase()
    }

    suspend fun updateTask(taskEntity: TaskEntity) {
        return taskDao.updateTask(taskEntity)
    }

}