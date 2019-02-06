package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamworkapp.dao.AppRemoteDao
import com.dempsey.teamworkapp.dao.RemoteDao
import io.reactivex.Observable

class AppProjectsBusiness(
        private val remoteDao: RemoteDao
) : ProjectsBusiness {

    override fun getProjects(): Observable<Projects> = remoteDao.getAllProjects()

    override fun filterByDueDate(projects: List<Project>): List<Project> {
        return ArrayList(projects)
                .sortedWith(compareByDescending<Project> { it.endDate })
    }

    companion object {

        @JvmStatic
        fun newInstance(): AppProjectsBusiness =
                AppProjectsBusiness(AppRemoteDao.newInstance())

    }
}