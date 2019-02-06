package com.dempsey.teamworkapp.presenter.todolist

interface TodoListContract {

    interface View

    interface ActionListener {

        fun getTodoListForTask(id: String)

    }

}