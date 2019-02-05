package com.dempsey.teamworkapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.dempsey.teamwork.data.model.Task
import com.dempsey.teamworkapp.R
import com.dempsey.teamworkapp.base.BaseFragment
import com.dempsey.teamworkapp.presenter.task.TasksContract
import com.dempsey.teamworkapp.presenter.task.TasksPresenter
import com.dempsey.teamworkapp.utils.MessageBanner
import com.dempsey.teamworkapp.utils.MessageType

private const val PROJECT_ID_BUNDLE = "projectIdBundle"

class TasksViewFragment : BaseFragment<TasksPresenter>(), TasksContract.View {

    override fun layoutId() = R.layout.fragment_task_detail

    override fun instantiatePresenter() = TasksPresenter.newInstance(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getTasksForProject(arguments!![PROJECT_ID_BUNDLE] as String)
    }

    override fun displayProjectTasks(tasks: List<Task>) {
        tasks.listIterator()
    }

    override fun showError(error: Throwable) {
        MessageBanner(context!! as AppCompatActivity).showBanner(error.message!!, MessageType.ERROR)
    }

    companion object {

        fun newInstance(projectId: String): TasksViewFragment {
            val bundle = Bundle().apply {
                putString(PROJECT_ID_BUNDLE, projectId)
            }
            return TasksViewFragment().apply {
                this.arguments = bundle
            }
        }
    }
}