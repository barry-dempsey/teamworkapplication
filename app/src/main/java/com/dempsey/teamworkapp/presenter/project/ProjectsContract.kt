package com.dempsey.teamworkapp.presenter.project

import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamwork.data.model.Projects

interface ProjectsContract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun showLoadingMessage(message: Int)

        fun showProjectsForUser(projects: Projects)

        fun handleError(error: Throwable)

        fun reloadData(projects: List<Project>)

    }

    interface ActionListener {

        fun getProjects()

        fun sortByDueData(projects: List<Project>)

    }

    interface Delegate {

        fun startTasksFragment(projectId: String)

        fun startDetailFragment(project: Project)

    }

}