package com.dempsey.teamworkapp.dao.remote

import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamwork.data.model.TodoList
import io.reactivex.Observable

interface RemoteDao {

    fun getAllProjects(): Observable<Projects>

    fun getTasksForProject(projectId: String): Observable<ProjectTask>

    fun getTodoListForTasks(taskId: String): Observable<TodoList>
}