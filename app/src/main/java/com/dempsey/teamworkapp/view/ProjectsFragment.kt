package com.dempsey.teamworkapp.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamworkapp.presenter.project.ProjectsPresenter
import android.view.MenuItem
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamworkapp.R
import com.dempsey.teamworkapp.base.BaseFragment
import com.dempsey.teamworkapp.presenter.project.ProjectsContract
import com.dempsey.teamworkapp.utils.MessageBanner
import com.dempsey.teamworkapp.utils.MessageType
import kotlinx.android.synthetic.main.activity_main.*

class ProjectsFragment : BaseFragment<ProjectsPresenter>(
), ProjectsContract.View {

    private lateinit var delegate: ProjectsContract.Delegate

    private var projectList: List<Project>? = null

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onViewCreated()
        setHasOptionsMenu(true)
    }

    override fun setUpUi() {
        updateRecyclerVisibility(show = true)
    }

    override fun reloadData(projects: List<Project>) {
        presenter.getProjects()
    }

    private fun updateRecyclerVisibility(show: Boolean) {
        when (show) {
            true -> recycler_view.visibility = View.VISIBLE
            false -> recycler_view.visibility = View.GONE
        }
    }

    override fun showProgress() {
        delegate.updateLoading(show = true)
    }

    override fun hideProgress() {
        delegate.updateLoading(show = false)
    }

    override fun showProjectsForUser(projects: Projects) {
        this.projectList = projects.projectList
        val adapter = ProjectsAdapter(projects.projectList, ProjectSelector())
        recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_view.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun handleError(error: Throwable) {
        MessageBanner(context as MainActivity).showBanner(error.message!!, MessageType.ERROR)
    }

    override fun showLoadingMessage() {
        MessageBanner(context as MainActivity).showBanner(getString(R.string.loading_projects), MessageType.SUCCESS)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort -> {
                presenter.sortByDueData(projectList!!)
                return true
            }
            R.id.refresh -> {
                presenter.onViewCreated()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun instantiatePresenter(): ProjectsPresenter {
        return ProjectsPresenter.newInstance(this)
    }

    inner class ProjectSelector : ProjectsAdapter.ProjectSelected {

        override fun onProjectSelectedForDetail(project: Project): Boolean {
            delegate.startDetailFragment(project = project)
            return false
        }

        override fun onProjectSelectedForTasks(project: Project) {
            delegate.startTasksFragment(projectId = project.id)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(delegate: ProjectsContract.Delegate) = ProjectsFragment().apply {
            this.delegate = delegate
        }
    }
}