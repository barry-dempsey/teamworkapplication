package com.dempsey.teamworkapp.presenter.task

import com.dempsey.teamwork.data.model.ProjectTask

interface TasksContract {

    interface View {

        fun showLoading()

        fun hideLoading()

        fun displayProjectTasks(tasks: ProjectTask)

        fun showError(error: Throwable)

    }

    interface ActionListener {

        fun getTasksForProject(projectId: String)

    }

}