package com.dempsey.teamworkapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "project")
data class ProjectEntity(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @NonNull
        var id: String,
        @ColumnInfo(name = "name")
        var name: String? = null,
        @ColumnInfo(name = "description")
        var description: String? = null,
        @ColumnInfo(name = "lastChangedOn")
        var lastChangedOn: String? = null,
        @ColumnInfo(name = "showAnnouncement")
        var showAnnouncement: Boolean = false,
        @ColumnInfo(name = "createdOn")
        var createdOn: String? = null
)
