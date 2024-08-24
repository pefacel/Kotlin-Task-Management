package com.pefacel.taskmanagementapp.tasks.core

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateHelper {

    fun dateIsOverTheLimitDate(date: LocalDateTime): Boolean {
        val today = dateToday()
        val diff: Int = date.compareTo(today)
        if (diff > 0) {
            return true
        } else {
            return false
        }
    }

    fun dateToday(): LocalDateTime {
        return LocalDateTime.now()
    }

    fun formatDate(date: String): LocalDateTime {
        val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val ld: LocalDate = LocalDate.parse(date, DATE_FORMATTER)
        val ldt: LocalDateTime = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime())
        return ldt
    }

}