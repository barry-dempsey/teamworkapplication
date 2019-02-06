package com.dempsey.teamworkapp.dao

import com.dempsey.teamwork.Teamwork
import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamwork.data.model.TodoList
import io.reactivex.Observable

class AppRemoteDao : RemoteDao {

    override fun getAllProjects(): Observable<Projects> =
            Teamwork.projectRequest().newGetAllProjectsRequest()

    override fun getTasksForProject(projectId: String): Observable<ProjectTask> =
            Teamwork.tasksRequest().getAllTasksForProject(projectId)

    override fun getTodoListForTasks(taskId: String):  Observable<TodoList> =
            Teamwork.tasksRequest().getTodoListForTask(taskId)

    companion object {
        @JvmStatic
        fun newInstance() = AppRemoteDao()
    }
}