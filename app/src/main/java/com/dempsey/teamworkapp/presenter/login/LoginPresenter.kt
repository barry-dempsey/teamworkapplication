package com.dempsey.teamworkapp.presenter.login

import com.dempsey.teamwork.Teamwork
import com.dempsey.teamworkapp.R.string
import com.dempsey.teamworkapp.base.BasePresenter
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
        .doOnSubscribe { view.showLoading() }
        .subscribeOn(schedulers)
        .observeOn(androidSchedulers)
        .doOnTerminate { view.hideLoading() }
        .subscribe(
            { account -> view.showSuccess(account)},
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
    @JvmStatic
    fun newInstance(view: LoginContract.View): LoginPresenter =
            LoginPresenter(
                    view,
                    Schedulers.io(),
                    AndroidSchedulers.mainThread())
  }
}