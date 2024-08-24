package com.pefacel.taskmanagementapp.tasks.data.network

import com.pefacel.taskmanagementapp.tasks.data.network.model.TasksModelResponse
import retrofit2.Response
import retrofit2.http.GET

interface TaskApiService {
    @GET("/")
    suspend fun getTasks(): Response<TasksModelResponse>
}