package com.dempsey.teamworkapp.presenter.task

import com.dempsey.teamwork.Teamwork
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
                .observeOn(mainThread)
                .subscribe(
                        { projectTasks -> view.displayProjectTasks(projectTasks.taskList)},
                        { error -> view.showError(error)}
                )
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