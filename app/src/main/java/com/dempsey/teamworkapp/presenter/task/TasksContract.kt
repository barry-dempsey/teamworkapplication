package com.dempsey.teamworkapp.presenter.task

import com.dempsey.teamwork.data.model.Task

interface TasksContract {

    interface View {

        fun displayProjectTasks(tasks: List<Task>)

        fun showError(error: Throwable)

    }

    interface ActionListener {

        fun getTasksForProject(projectId: String)

    }

}