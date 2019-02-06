package com.dempsey.teamworkapp.dao.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dempsey.teamworkapp.data.ProjectEntity

@Dao
interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProjects(projectEntity: ProjectEntity)

    @Query("SELECT * FROM project")
    fun getProjectsFromCache(): ProjectEntity
}