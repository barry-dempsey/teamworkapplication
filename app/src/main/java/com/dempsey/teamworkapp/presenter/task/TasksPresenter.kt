package com.dempsey.teamworkapp.presenter.task

import com.dempsey.teamwork.Teamwork
import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamworkapp.base.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TasksPresenter(
        private val view: TasksContract.View,
        private val io: Scheduler,
        private val mainThread: Scheduler
) : BasePresenter(), TasksContract.ActionListener {

    private lateinit var disposable: Disposable

    override fun getTasksForProject(projectId: String) {
        disposable = Teamwork.tasksRequest()
                .getAllTasksForProject(projectId)
                .subscribeOn(io)
                .doOnSubscribe{ view.showLoading() }
                .observeOn(mainThread)
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { projectTasks -> handleTasksResponse(projectTasks)},
                        { error -> handleErrorIfAny(error) }
                )
    }

    private fun handleErrorIfAny(error: Throwable) {
        view.hideLoading()
        view.showError(error)
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
                        view,
                        Schedulers.io(),
                        AndroidSchedulers.mainThread()
                )
    }
}