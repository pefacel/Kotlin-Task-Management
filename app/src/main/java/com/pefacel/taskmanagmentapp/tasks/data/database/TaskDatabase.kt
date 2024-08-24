package com.pefacel.taskmanagementapp.tasks.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pefacel.taskmanagementapp.tasks.data.database.entity.TaskEntity
import com.pefacel.taskmanagementapp.tasks.di.AppModule.TASK_DATABASE_NAME

@Database(entities = [TaskEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var instance: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, TaskDatabase::class.java, TASK_DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}

