package com.zhukovskii.scorecrunch.util

import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

private fun parseDate(dateString: String) = LocalDate.parse(dateString, formatter)

fun String.isBefore(): Boolean {
    val inputDate = parseDate(this)
    return inputDate.isBefore(LocalDate.now())
}

fun String.isAfter(): Boolean {
    val inputDate = parseDate(this)
    return inputDate.isAfter(LocalDate.now())
}

fun String.formatDate(): String {
    val zonedDateTime = ZonedDateTime.parse(this)
    val formatter = DateTimeFormatter.ofPattern("dd MMMM HH:mm")
        .withZone(ZoneId.systemDefault())
    return zonedDateTime.format(formatter)
}
