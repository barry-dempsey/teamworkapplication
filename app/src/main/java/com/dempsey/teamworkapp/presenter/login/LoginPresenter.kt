package com.dempsey.teamworkapp.presenter.login

import com.dempsey.teamwork.Teamwork
import com.dempsey.teamworkapp.R.string
import com.dempsey.teamworkapp.base.BasePresenter
import com.dempsey.teamworkapp.presenter.LoginContract
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter(
        private val view: LoginContract.View,
        private val schedulers: Scheduler,
        private val androidSchedulers: Scheduler
): LoginContract.ActionListener, BasePresenter() {

  private var disposable: Disposable? = null

  override fun loginUser(apiKey: String) {
    disposable = Teamwork.accountRequest()
        .newAuthenticateRequest(apiKey)
        .doOnSubscribe { view.showProgress() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnTerminate { view.hideProgress() }
        .subscribe(
            { _ ->  view.showSuccess()},
            { error -> handleError(error)}
        )
  }

  override fun getProjects() {
    disposable = Teamwork.projectRequest()
        .newGetAllProjectsRequest()
        .subscribeOn(schedulers)
        .observeOn(androidSchedulers)
        .doOnSubscribe { view.showProgress() }
        .doOnTerminate { view.hideProgress() }
        .subscribe(
            { projects ->  view.showProjectsForUser(projects) },
            { error -> handleError(error)}
        )
  }

  private fun handleError(error: Throwable?) {
    when (error?.message?.contains("401")) {

      true -> view.showError(string.unauthorised_error)

      false -> view.showError(string.server_error)

    }
  }

  override fun onViewCreated() {}

  override fun onViewDestroyed() {
    disposable?.dispose()
  }

  companion object {

    fun newInstance(view: LoginContract.View): LoginPresenter =
            LoginPresenter(
                    view,
                    Schedulers.io(),
                    AndroidSchedulers.mainThread())

  }
}