package com.dempsey.teamworkapp.business

import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamwork.data.model.TodoList
import com.dempsey.teamwork.service.project.ProjectRequest
import com.dempsey.teamworkapp.dao.local.UserDatabase
import com.dempsey.teamworkapp.dao.remote.RemoteDao
import io.reactivex.Observable
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppProjectsEntityBusinessTest {

    private lateinit var projectsBusiness: AppProjectsBusiness

    private lateinit var taskBusiness: AppTaskBusiness

    private lateinit var todoBusiness: AppTodoListBusiness

    @Mock private lateinit var remoteDao: RemoteDao

    @Mock private lateinit var database: UserDatabase

    @Before
    fun `set up`() {
        projectsBusiness = AppProjectsBusiness(remoteDao, database)
        taskBusiness = AppTaskBusiness(remoteDao)
        todoBusiness = AppTodoListBusiness(remoteDao)
    }

    @Test
    fun `test we get projects`() {
        val result = Projects()
        Mockito.`when`(remoteDao.getAllProjects()).thenReturn(Observable.just(result))
        assertNotNull(projectsBusiness.getProjects())
    }

    @Test
    fun `test we get tasks`() {
        val result = ProjectTask()
        Mockito.`when`(remoteDao.getTasksForProject(anyString())).thenReturn(Observable.just(result))
        assertNotNull(taskBusiness.getAllTasksForProject("id"))
    }

    @Test
    fun `test we get todoLists`() {
        val result = TodoList()
        Mockito.`when`(remoteDao.getTodoListForTasks(anyString())).thenReturn(Observable.just(result))
        assertNotNull(taskBusiness.getTodoListForTask("id"))
    }
}