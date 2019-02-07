package com.dempsey.teamworkapp.business

import android.content.Context
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamworkapp.dao.local.DataBaseProvider
import com.dempsey.teamworkapp.dao.local.UserDatabase
import com.dempsey.teamworkapp.dao.remote.AppRemoteDao
import com.dempsey.teamworkapp.dao.remote.RemoteDao
import com.dempsey.teamworkapp.data.ProjectEntity
import io.reactivex.Observable

class AppProjectsBusiness(
        private val remoteDao: RemoteDao,
        private val database: UserDatabase
) : ProjectsBusiness {

    override fun getProjects(): Observable<Projects> = remoteDao.getAllProjects()

    override fun storeProjects(projects: Projects) {
        database.localDao().saveProjects(prepareDataForCache(projects))
    }

    override fun getProjectsFromCache(): List<Project> {
        val project = database.localDao().getProjectsFromCache()
        val projectToDisplay = Project.newBuilder().apply{
            this.id(project.id)
            this.name(project.name)
            this.description(project.description)
            this.createdOn(project.createdOn)
            this.lastChangedOn(project.lastChangedOn)
        }.build()
        return arrayListOf(projectToDisplay)
    }

    private fun prepareDataForCache(projects: Projects): ProjectEntity {
        for (project in projects.projectList) {
            val projectEntity = ProjectEntity(
                    project.id,
                    project.name,
                    project.description,
                    project.lastChangedOn,
                    project.isShowAnnouncement,
                    project.createdOn
            )
            return projectEntity
        }
        return ProjectEntity("")
    }

    override fun filterByDueDate(projects: List<Project>): List<Project> {
        return ArrayList(projects)
                .sortedWith(compareByDescending<Project> { it.endDate })
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context): AppProjectsBusiness =
                AppProjectsBusiness(
                        AppRemoteDao.newInstance(),
                        DataBaseProvider.getInstance(context))
    }
}