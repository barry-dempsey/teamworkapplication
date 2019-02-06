package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamwork.data.model.TodoList
import io.reactivex.Observable

interface TaskBusiness {

    fun getAllTasksForProject(projectId: String): Observable<ProjectTask>

    fun getTodoListForTask(taskId: String): Observable<TodoList>

}