package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.data.model.TodoList
import io.reactivex.Observable

interface TodoListBusiness {

    fun getTodoListForTask(taskId: String): Observable<TodoList>
}