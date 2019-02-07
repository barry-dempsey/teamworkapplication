package com.dempsey.teamworkapp.presenter.task

import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamwork.data.model.TodoList

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

    interface Delegate {

        fun startTodoListFragment(todoList: TodoList)

    }

}