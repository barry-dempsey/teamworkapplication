package com.dempsey.teamworkapp.presenter.project

import com.dempsey.teamwork.Teamwork
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamworkapp.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProjectDetailPresenter(
    private val view: ProjectDetailContract.View
): BasePresenter(), ProjectDetailContract.ActionListener {

  override fun onViewCreated() {}

  override fun onViewDestroyed() {}

  override fun updateProjectDetails(project: Project) {
    Teamwork.projectRequest()
        .updateProject(project)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { _ -> view.showSuccess()},
            { _ -> view.showError() }
        ).dispose()
  }

  companion object {

    fun newInstance(view: ProjectDetailContract.View): ProjectDetailPresenter =
            ProjectDetailPresenter(view)

  }

}