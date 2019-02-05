package com.dempsey.teamworkapp.presenter.task

import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.junit.Before
import org.mockito.Mock

class TasksPresenterTest {

    private lateinit var tasksPresenter: TasksPresenter

    @Mock private lateinit var view: TasksContract.View

    @Mock private lateinit var testScheduler: TestScheduler

    @Before
    fun `set up`() {
        tasksPresenter = TasksPresenter(view, testScheduler, testScheduler)
    }

    @Test
    fun getTasksForProject() {

    }

    @Test
    fun onViewCreated() {
    }

    @Test
    fun onViewDestroyed() {
    }
}