package com.dempsey.teamworkapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.dempsey.mytaxiapplication.utils.MessageBanner
import com.dempsey.mytaxiapplication.utils.MessageType.ERROR
import com.dempsey.mytaxiapplication.utils.MessageType.SUCCESS
import com.dempsey.teamwork.data.model.Message
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamworkapp.presenter.ProjectDetailPresenter
import com.dempsey.teamworkapp.R.layout
import com.dempsey.teamworkapp.base.BaseActivity
import com.dempsey.teamworkapp.base.BasePresenter
import com.dempsey.teamworkapp.presenter.ProjectDetailContract
import kotlinx.android.synthetic.main.activity_detail.progress_bar
import kotlinx.android.synthetic.main.activity_detail.project_description
import kotlinx.android.synthetic.main.activity_detail.project_id
import kotlinx.android.synthetic.main.activity_detail.project_name
import kotlinx.android.synthetic.main.activity_detail.title_view
import kotlinx.android.synthetic.main.activity_detail.update_button

class ProjectDetailActivity:
    BaseActivity<BasePresenter>(),
    ProjectDetailContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_detail)

    initViews()
  }

  private fun initViews() {
    val project = intent.getSerializableExtra(PROJECT_EXTRA) as Project
    title_view.text = project.name
    project_name.setText(project.name)
    project_id.setText(project.id)
    project_description.setText(project.description)
    update_button.setOnClickListener{ validateInputAndSend() }
  }

  private fun validateInputAndSend() {

  }

  override fun instantiatePresenter(): BasePresenter {
    return ProjectDetailPresenter.newInstance(this)
  }

  override fun showLoading() {
    progress_bar.visibility = View.VISIBLE
  }

  override fun hideLoading() {
    progress_bar.visibility = View.INVISIBLE
  }

  override fun showSuccess() {
    MessageBanner(this).showBanner("Success", SUCCESS)
  }

  override fun showError() {
    MessageBanner(this).showBanner("Error", ERROR)
  }

  companion object {

    private const val PROJECT_EXTRA = "projectExtra"

    fun newInstance(
        context: Context,
        project: Project): Intent =
        Intent(context, ProjectDetailActivity::class.java)
            .putExtra(PROJECT_EXTRA, project)

  }
}