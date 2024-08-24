package com.pefacel.taskmanagementapp.tasks.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pefacel.taskmanagementapp.tasks.data.database.TaskDatabase
import com.pefacel.taskmanagementapp.tasks.data.network.TaskApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val TASK_DATABASE_NAME = "task-db"
    val TASK_ENDPOINT_URL = "https://curso-android-55-1.vercel.app"

    @Provides
    @Singleton
    @Named("taskRetrofit")
    fun provideTaskRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder().baseUrl(TASK_ENDPOINT_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideTaskApiService(@Named("taskRetrofit") retrofit: Retrofit): TaskApiService {
        return retrofit.create(TaskApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideTaskDao(db: TaskDatabase) = db.taskDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = TaskDatabase.getDatabase(appContext)

}