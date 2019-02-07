package com.dempsey.teamworkapp.view

import android.os.Bundle
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamworkapp.R
import com.dempsey.teamworkapp.presenter.project.ProjectDetailPresenter
import com.dempsey.teamworkapp.base.BaseFragment
import com.dempsey.teamworkapp.presenter.project.ProjectDetailContract
import com.dempsey.teamworkapp.utils.DateUtil
import com.dempsey.teamworkapp.utils.MessageBanner
import com.dempsey.teamworkapp.utils.MessageType
import kotlinx.android.synthetic.main.fragment_detail.project_description
import kotlinx.android.synthetic.main.fragment_detail.project_name
import kotlinx.android.synthetic.main.fragment_detail.title_view
import kotlinx.android.synthetic.main.fragment_detail.update_button
import kotlinx.android.synthetic.main.fragment_project_item.*

class ProjectDetailFragment : BaseFragment<ProjectDetailPresenter>(

), ProjectDetailContract.View {

    override fun layoutId(): Int = R.layout.fragment_detail

    private lateinit var project: Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        project = arguments!![PROJECT_BUNDLE] as Project
        hideLoading()
    }

    override fun setUpUi() {
        title_view.text = project.name
        project_name.setText(project.name)
        project_date.text = DateUtil.getPrettyDateFromStamp(project.createdOn)
        project_description.setText(project.description)
        update_button.setOnClickListener { validateInputAndSend() }
    }

    private fun validateInputAndSend() {
        presenter.updateProjectDetails(project)
    }

    override fun instantiatePresenter() = ProjectDetailPresenter.newInstance(this)

    override fun showSuccess() {
        MessageBanner(context as MainActivity).showBanner(getString(R.string.success), MessageType.SUCCESS)
    }

    override fun showError() {
        MessageBanner(context as MainActivity).showBanner(getString(R.string.error), MessageType.ERROR)
    }

    companion object {

        private const val PROJECT_BUNDLE = "projectExtra"

        @JvmStatic
        fun newInstance(
                project: Project
        ) = ProjectDetailFragment().apply {
            arguments = Bundle().apply { putSerializable(PROJECT_BUNDLE, project) }
        }
    }
}