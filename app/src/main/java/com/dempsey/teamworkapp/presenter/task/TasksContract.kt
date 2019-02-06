package com.dempsey.teamworkapp.presenter.task

import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamwork.data.model.TodoList
import com.dempsey.teamworkapp.base.BaseDelegate

interface TasksContract {

    interface View {

        fun showLoading()

        fun hideLoading()

        fun displayProjectTasks(tasks: ProjectTask)

        fun showError(error: Throwable)

        fun displayTodolist(todoList: TodoList)

    }

    interface ActionListener {

        fun getTasksForProject(projectId: String)

        fun getTodoListForTask(taskId: String)

    }

    interface Delegate : BaseDelegate {

        fun startTodoListFragment(todoList: TodoList)

    }

}