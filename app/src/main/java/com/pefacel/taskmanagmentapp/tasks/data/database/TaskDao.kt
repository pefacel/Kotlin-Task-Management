package com.pefacel.taskmanagementapp.tasks.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pefacel.taskmanagementapp.tasks.data.database.entity.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_entity ")
    suspend fun getAllTask(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

}