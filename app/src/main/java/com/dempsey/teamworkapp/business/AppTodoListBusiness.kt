package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.Teamwork
import com.dempsey.teamwork.data.model.TodoList
import io.reactivex.Observable

class AppTodoListBusiness : TodoListBusiness {

    override fun getTodoListForTask(taskId: String): Observable<TodoList>  =
            Teamwork.tasksRequest().getTodoListForTask(taskId)

    companion object {
        @JvmStatic
        fun newInstance() = AppTodoListBusiness()
    }
}