package com.dempsey.teamworkapp.presenter.project

import com.dempsey.teamwork.Teamwork
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamworkapp.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectDetailPresenter(
        private val view: ProjectDetailContract.View
) : BasePresenter(), ProjectDetailContract.ActionListener {

    private lateinit var disposable: Disposable

    override fun onViewCreated() {}

    override fun onViewDestroyed() {
        disposable.dispose()
    }

    override fun updateProjectDetails(project: Project) {
        disposable = Teamwork.projectRequest()
                .updateProject(project)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { view.showSuccess() },
                        { view.showError() }
                )
    }

    companion object {

        @JvmStatic
        fun newInstance(view: ProjectDetailContract.View) =
                ProjectDetailPresenter(view)
    }
}