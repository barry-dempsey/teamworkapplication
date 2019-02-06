package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamwork.data.model.TodoList
import com.dempsey.teamworkapp.dao.AppRemoteDao
import com.dempsey.teamworkapp.dao.RemoteDao
import io.reactivex.Observable

class AppTaskBusiness(
        private val remoteDao: RemoteDao
) : TaskBusiness {

    override fun getAllTasksForProject(projectId: String): Observable<ProjectTask> =
            remoteDao.getTasksForProject(projectId)

    override fun getTodoListForTask(taskId: String): Observable<TodoList> =
            remoteDao.getTodoListForTasks(taskId)

    companion object {
        @JvmStatic
        fun newInstance() = AppTaskBusiness(AppRemoteDao.newInstance())
    }
}