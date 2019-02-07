package com.dempsey.teamworkapp.presenter.project

import android.content.Context
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamworkapp.R
import com.dempsey.teamworkapp.business.AppProjectsBusiness
import com.dempsey.teamworkapp.base.BasePresenter
import com.dempsey.teamworkapp.performOperationOnThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectsPresenter(
        private val business: AppProjectsBusiness,
        private val io: Scheduler,
        private val mainThread: Scheduler,
        private val view: ProjectsContract.View
) : ProjectsContract.ActionListener, BasePresenter() {

    private lateinit var disposable: Disposable

    override fun getProjects() {
        disposable = business.getProjects()
                .subscribeOn(io)
                .observeOn(mainThread)
                .subscribe(
                        { projects -> handleProjectsResponse(projects) },
                        { error -> handleError(error)}
                )
    }

    private fun handleError(error: Throwable) {
        view.handleError(error)
    }

    private fun handleProjectsResponse(projects: Projects) {
        performOperationOnThread(
                longOperation = { business.storeProjects(projects) },
                callback = { view.showLoadingMessage(R.string.saved_latest_projects) }
        )
        view.showProjectsForUser(projects)
        view.showLoadingMessage(R.string.loading_projects)
    }

    override fun onViewCreated() {
        getProjects()
    }

    override fun onViewDestroyed() {
        disposable.dispose()
    }

    override fun sortByDueData(projects: List<Project>) {
        view.reloadData(business.filterByDueDate(projects))
    }

    companion object {

        fun newInstance(
                view: ProjectsContract.View,
                context: Context
        ): ProjectsPresenter =
                ProjectsPresenter(
                        AppProjectsBusiness.newInstance(context),
                        Schedulers.io(),
                        AndroidSchedulers.mainThread(),
                        view)
    }
}