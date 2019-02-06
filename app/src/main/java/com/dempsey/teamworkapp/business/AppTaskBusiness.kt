package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.Teamwork
import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamwork.data.model.TodoList
import io.reactivex.Observable

class AppTaskBusiness : TaskBusiness {

    override fun getAllTasksForProject(projectId: String): Observable<ProjectTask> =
            Teamwork.tasksRequest().getAllTasksForProject(projectId)

    override fun getTodoListForTask(taskId: String): Observable<TodoList> =
            Teamwork.tasksRequest().getTodoListForTask(taskId)

    companion object {
        @JvmStatic
        fun newInstance() = AppTaskBusiness()
    }
}