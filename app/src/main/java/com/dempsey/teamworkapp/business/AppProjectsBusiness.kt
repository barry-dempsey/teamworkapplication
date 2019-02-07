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
        database.localDao().saveAllProjects(prepareDataForCache(projects))
    }

    override fun getProjectsFromCache(): Projects {
        val projectEntities = database.localDao().getProjectsFromCache()
        val projects = ArrayList<Project>()
        for (projectEntity in projectEntities) {
            Project.newBuilder().apply {
                this.id(projectEntity.id)
                this.name(projectEntity.name)
                this.description(projectEntity.description)
                this.createdOn(projectEntity.createdOn)
                this.showAnnouncement(projectEntity.showAnnouncement)
                projects.add(this.build())
            }
        }
        return Projects().apply {
            this.projectList = projects
        }
    }

    private fun prepareDataForCache(projects: Projects): List<ProjectEntity> {
        val projectEntities = ArrayList<ProjectEntity>()
        for (project in projects.projectList) {
            val projectEntity = ProjectEntity(
                    project.id,
                    project.name,
                    project.description,
                    project.lastChangedOn,
                    project.isShowAnnouncement,
                    project.createdOn
            )
            projectEntities.add(projectEntity)
        }
        return projectEntities
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