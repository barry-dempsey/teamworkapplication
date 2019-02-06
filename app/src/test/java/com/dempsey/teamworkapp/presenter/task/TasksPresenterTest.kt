package com.dempsey.teamworkapp.presenter.task

import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamworkapp.business.TaskBusiness
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TasksPresenterTest {

    private lateinit var tasksPresenter: TasksPresenter

    @Mock private lateinit var view: TasksContract.View

    @Mock private lateinit var business: TaskBusiness

    @Mock private lateinit var testScheduler: TestScheduler

    @Before
    fun `set up`() {
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        tasksPresenter = TasksPresenter(business, view, testScheduler, testScheduler)
    }

    @Test
    fun `test for successful call to get projects`() {
        val projectTask = ProjectTask()
        val projectId = "projectId"
        Mockito.`when`(business.getAllTasksForProject(projectId)).thenReturn(Observable.just(projectTask))
        tasksPresenter.getTasksForProject(projectId)
        testScheduler.triggerActions()
        Mockito.verify(view).showLoading()
        Mockito.verify(view).displayProjectTasks(projectTask)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun `test for unsuccessful call to get projects`() {
        val projectId = "projectId"
        val error = Throwable("server error")
        Mockito.`when`(business.getAllTasksForProject(projectId)).thenReturn(Observable.error(error))
        tasksPresenter.getTasksForProject(projectId)
        testScheduler.triggerActions()
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showError(error)
        Mockito.verify(view).hideLoading()
    }
}