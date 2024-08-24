package com.pefacel.taskmanagementapp.tasks.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.format.DateTimeFormatter

@Entity(tableName = "task_entity")
@Parcelize
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val uuid: UUID?,
    val content: String,
    val title: String,
    val creationDate: LocalDateTime,
    val dueDate: LocalDateTime,
    val image: String,
    val completed: Boolean,
) : Parcelable {
    val creationDateFormatted: String get() = creationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    val dueDateFormatted: String get() = dueDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}
