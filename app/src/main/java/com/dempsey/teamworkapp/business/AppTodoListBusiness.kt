package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.data.model.TodoList
import com.dempsey.teamworkapp.dao.AppRemoteDao
import com.dempsey.teamworkapp.dao.RemoteDao
import io.reactivex.Observable

class AppTodoListBusiness(
        private val remoteDao: RemoteDao
) : TodoListBusiness {

    override fun getTodoListForTask(taskId: String): Observable<TodoList> =
            remoteDao.getTodoListForTasks(taskId)

    companion object {
        @JvmStatic
        fun newInstance() = AppTodoListBusiness(AppRemoteDao.newInstance())
    }
}