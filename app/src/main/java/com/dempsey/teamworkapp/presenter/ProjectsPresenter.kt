package com.dempsey.teamworkapp.presenter

import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamworkapp.AppProjectsBusiness
import com.dempsey.teamworkapp.base.BasePresenter

class ProjectsPresenter(
    private val business: AppProjectsBusiness,
    private val view: ProjectsContract.View
): ProjectsContract.ActionListener, BasePresenter() {


  override fun onViewCreated() {
    view.showLoadingMessage()
  }

  override fun onViewDestroyed() {

  }

  override fun sortByDueData(projects: List<Project>) {
    view.reloadData(business.filterByDueDate(projects))
  }

  companion object {

    fun newInstance(view: ProjectsContract.View): ProjectsPresenter =
        ProjectsPresenter(
            AppProjectsBusiness.newInstance(),
            view)

  }
}