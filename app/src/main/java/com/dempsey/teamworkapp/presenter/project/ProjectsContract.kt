package com.dempsey.teamworkapp.presenter.project

import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamworkapp.base.BaseDelegate

interface ProjectsContract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun showLoadingMessage()

        fun showProjectsForUser(projects: Projects)

        fun handleError(error: Throwable)

        fun reloadData(projects: List<Project>)

    }

    interface ActionListener {

        fun getProjects()

        fun sortByDueData(projects: List<Project>)

    }

    interface Delegate: BaseDelegate {

        fun startTasksFragment(projectId: String)

        fun startDetailFragment(project: Project)

    }

}