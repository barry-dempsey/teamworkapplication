package com.dempsey.teamworkapp.presenter.project

import android.content.Context
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamworkapp.business.AppProjectsBusiness
import com.dempsey.teamworkapp.base.BasePresenter
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
                        { error -> view.handleError(error)}
                )
    }

    private fun handleProjectsResponse(projects: Projects) {
        business.storeProjects(projects)
        view.showProjectsForUser(projects)
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