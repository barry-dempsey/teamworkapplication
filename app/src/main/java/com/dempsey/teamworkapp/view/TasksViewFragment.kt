package com.dempsey.teamworkapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.dempsey.teamwork.data.model.ProjectTask
import com.dempsey.teamworkapp.R
import com.dempsey.teamworkapp.base.BaseDelegate
import com.dempsey.teamworkapp.base.BaseFragment
import com.dempsey.teamworkapp.presenter.task.TasksContract
import com.dempsey.teamworkapp.presenter.task.TasksPresenter
import com.dempsey.teamworkapp.utils.MessageBanner
import com.dempsey.teamworkapp.utils.MessageType
import kotlinx.android.synthetic.main.fragment_task_layout.*

private const val PROJECT_ID_BUNDLE = "projectIdBundle"

class TasksViewFragment : BaseFragment<TasksPresenter>(), TasksContract.View {

    private var delegate: BaseDelegate? = null

    override fun layoutId() = R.layout.fragment_task_layout

    override fun instantiatePresenter() = TasksPresenter.newInstance(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getTasksForProject(arguments!![PROJECT_ID_BUNDLE] as String)
    }

    override fun setUpUi() {
    }

    override fun displayProjectTasks(tasks: ProjectTask) {
        val taskAdapter = TasksAdapter(tasks.tasks)
        tasks_recycler_view.adapter = taskAdapter
        tasks_recycler_view.layoutManager = LinearLayoutManager(context!!)
    }

    override fun showError(error: Throwable) {
        MessageBanner(context!! as AppCompatActivity).showBanner(error.message!!, MessageType.ERROR)
    }

    override fun showLoading() {
        delegate?.updateLoading(show = true)
    }

    override fun hideLoading() {
        delegate?.updateLoading(show = false)
    }

    companion object {

        fun newInstance(projectId: String, delegate: BaseDelegate): TasksViewFragment {
            val bundle = Bundle().apply {
                putString(PROJECT_ID_BUNDLE, projectId)
            }
            return TasksViewFragment().apply {
                this.arguments = bundle
                this.delegate = delegate
            }
        }
    }
}