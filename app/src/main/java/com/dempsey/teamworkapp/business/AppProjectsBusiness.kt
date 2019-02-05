package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.Teamwork
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamwork.data.model.Projects
import io.reactivex.Observable

class AppProjectsBusiness : ProjectsBusiness {

    override fun getProjects(): Observable<Projects> =
            Teamwork.projectRequest()
                    .newGetAllProjectsRequest()

    override fun filterByDueDate(projects: List<Project>): List<Project> {
        return ArrayList(projects)
                .sortedWith(compareByDescending<Project> { it.endDate })
    }

    companion object {

        @JvmStatic
        fun newInstance(): AppProjectsBusiness =
                AppProjectsBusiness()

    }
}