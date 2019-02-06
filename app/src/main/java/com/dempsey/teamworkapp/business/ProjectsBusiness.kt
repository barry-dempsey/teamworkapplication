package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamwork.data.model.Projects
import io.reactivex.Observable

interface ProjectsBusiness {

    fun getProjects(): Observable<Projects>

    fun storeProjects(projects: Projects)

    fun filterByDueDate(projects: List<Project>): List<Project>

}