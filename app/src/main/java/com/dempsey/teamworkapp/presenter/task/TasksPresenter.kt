package com.dempsey.teamworkapp.presenter.task

import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamwork.data.model.TodoList
import com.dempsey.teamworkapp.base.BasePresenter
import com.dempsey.teamworkapp.business.AppTaskBusiness
import com.dempsey.teamworkapp.business.TaskBusiness
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TasksPresenter(
        private val business: TaskBusiness,
        private val view: TasksContract.View,
        private val io: Scheduler,
        private val mainThread: Scheduler
) : BasePresenter(), TasksContract.ActionListener {

    private lateinit var disposable: Disposable

    override fun getTasksForProject(projectId: String) {
        disposable = business.getAllTasksForProject(projectId)
                .subscribeOn(io)
                .doOnSubscribe { view.showLoading() }
                .observeOn(mainThread)
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { projectTasks -> handleTasksResponse(projectTasks)},
                        { error -> handleErrorIfAny(error) }
                )
    }

    override fun getTodoListForTask(taskId: String) {
        disposable = business.getTodoListForTask(taskId)
                .subscribeOn(io)
                .doOnSubscribe { view.showLoading() }
                .observeOn(mainThread)
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { todoList -> handleTodoListResponse(todoList)},
                        { error -> handleErrorIfAny(error) }
                )
    }

    private fun handleErrorIfAny(error: Throwable) {
        view.showError(error)
    }

    private fun handleTodoListResponse(todoList: TodoList) {
        view.displayTodolist(todoList)
    }

    private fun handleTasksResponse(projectTasks: ProjectTask) {
        view.displayProjectTasks(projectTasks)
    }

    override fun onViewCreated() {}

    override fun onViewDestroyed() {
        disposable.dispose()
    }

    companion object {

        fun newInstance(view: TasksContract.View) =
                TasksPresenter(
                        AppTaskBusiness.newInstance(),
                        view,
                        Schedulers.io(),
                        AndroidSchedulers.mainThread()
                )
    }
}