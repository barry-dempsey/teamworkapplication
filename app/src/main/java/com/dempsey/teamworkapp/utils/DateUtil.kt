package com.dempsey.teamworkapp.utils

import java.lang.Long
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getSimpleTransactionDate(timestamp: String): String {
        val date = Date(Long.parseLong(timestamp) * 1000L)
        val expectedFormat = SimpleDateFormat("MMM yyyy", Locale.US)
        return expectedFormat.format(date)
    }

    fun getMonthFromTimeStamp(timestamp: String): String {
        val date = Date(Long.parseLong(timestamp) * 1000L)
        val expectedFormat = SimpleDateFormat("MMM", Locale.US)
        return expectedFormat.format(date)
    }

    fun getYearFromTimeStamp(timestamp: String): String {
        val date = Date(Long.parseLong(timestamp) * 1000L)
        val expectedFormat = SimpleDateFormat("yyyy", Locale.US)
        return expectedFormat.format(date)
    }

    fun getFormattedTransactionDate(timestamp: String): String {
        val date = Date(Long.parseLong(timestamp) * 1000L)
        val expectedFormat = SimpleDateFormat("EEE, MMM dd 'at' hh:mm a", Locale.US)
        return expectedFormat.format(date)
    }

    fun getFormattedTransactionDateThemeTwo(timestamp: String): String {
        val date = Date(Long.parseLong(timestamp) * 1000L)
        val expectedFormat = SimpleDateFormat("dd MMM '-' hh:mm a", Locale.US)
        return expectedFormat.format(date)
    }

    fun getLocaleFormattedDate(timestamp: String): String {
        val date = Date(Long.parseLong(timestamp) * 1000L)
        val dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.US)
        return dateFormat.format(date)
    }
}