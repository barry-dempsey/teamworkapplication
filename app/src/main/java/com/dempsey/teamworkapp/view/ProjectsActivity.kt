package com.dempsey.teamworkapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamwork.data.model.Projects
import com.dempsey.teamworkapp.R.layout
import com.dempsey.teamworkapp.base.BaseActivity
import com.dempsey.teamworkapp.presenter.project.ProjectsPresenter
import kotlinx.android.synthetic.main.activity_main.recycler_view
import android.view.Menu
import android.view.MenuItem
import com.dempsey.teamworkapp.R
import com.dempsey.teamworkapp.presenter.project.ProjectsContract
import com.dempsey.teamworkapp.utils.MessageBanner
import com.dempsey.teamworkapp.utils.MessageType
import com.dempsey.teamworkapp.view.ProjectsAdapter.ProjectSelected

class ProjectsActivity:
    BaseActivity<ProjectsPresenter>(),
    ProjectsContract.View {

  private lateinit var projectList: List<Project>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    presenter.onViewCreated()
    initViews()
  }

  private fun initViews() {
    updateRecyclerVisivility(show = true)
    val projects = intent.getSerializableExtra(PROJECTS_EXTRA) as Projects
    projectList = projects.projectList

    initAdapter(projectList)
  }

  private fun updateRecyclerVisivility(show: Boolean) {
    when (show) {
      true -> recycler_view.visibility = View.VISIBLE
      false -> recycler_view.visibility = View.GONE
    }
  }

  private fun initAdapter(projectList: List<Project>) {
    reloadData(projectList)
  }

  override fun reloadData(projects: List<Project>) {
    val adapter = ProjectsAdapter(projectList, ProjectSelector())
    recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    recycler_view.adapter = adapter
    adapter.notifyDataSetChanged()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun showLoadingMessage() {
    MessageBanner(this).showBanner(getString(R.string.loading_projects), MessageType.SUCCESS)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.sort -> {
        presenter.sortByDueData(projectList)
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

  companion object {

    private const val PROJECTS_EXTRA = "projectsExtra"

    fun newInstance(context: Context, projects: Projects): Intent {
      return Intent(context, ProjectsActivity::class.java)
          .putExtra(PROJECTS_EXTRA, projects)
    }

  }

  inner class ProjectSelector: ProjectSelected {

    override fun onProjectSelected(project: Project) {
      updateRecyclerVisivility(show = false)
      addFragment(TasksViewFragment.newInstance(projectId = project.id))
    }

  }
}