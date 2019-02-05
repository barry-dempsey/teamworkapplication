package com.dempsey.teamworkapp.view

import android.os.Bundle
import android.view.View
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamworkapp.presenter.login.LoginContract
import com.dempsey.teamworkapp.R.layout
import com.dempsey.teamworkapp.R.string
import com.dempsey.teamworkapp.base.BaseActivity
import com.dempsey.teamworkapp.presenter.login.LoginPresenter
import com.dempsey.teamworkapp.presenter.project.ProjectsContract
import com.dempsey.teamworkapp.utils.MessageBanner
import com.dempsey.teamworkapp.utils.MessageType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LoginContract.View,
    BaseActivity<LoginPresenter>(), ProjectsContract.Delegate {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    doLogin()
  }

  private fun doLogin() {
    presenter.loginUser(getString(string.api_key))
  }

  override fun instantiatePresenter(): LoginPresenter {
    return LoginPresenter.newInstance(this)
  }

  override fun showProgress() {
    progress_bar.visibility = View.VISIBLE
  }

  override fun hideProgress() {
    progress_bar.visibility = View.INVISIBLE
  }

  override fun updateLoading(show: Boolean) {
    if (show) showProgress() else hideProgress()
  }

  override fun showSuccess() {
    addFragment(ProjectsFragment.newInstance(this))
  }

  override fun startTasksFragment(projectId: String) {
    replaceFragment(TasksViewFragment.newInstance(projectId, this))
  }

  override fun startDetailFragment(project: Project) {
    replaceFragment(ProjectDetailFragment.newInstance(project, this))
  }

  override fun showError(error: Int) {
    MessageBanner(this).showBanner(error, MessageType.ERROR)
  }

  override fun onDestroy() {
    presenter.onViewDestroyed()
    super.onDestroy()
  }

  override fun onBackPressed() {
    if (supportFragmentManager.backStackEntryCount == 0) {
      finish()
    } else {
      super.onBackPressed()
    }
  }
}
