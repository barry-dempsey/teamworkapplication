package com.dempsey.teamworkapp.view

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.dempsey.teamwork.data.model.Project
import com.dempsey.teamworkapp.R
import com.dempsey.teamworkapp.R.id
import com.dempsey.teamworkapp.R.layout
import com.dempsey.teamworkapp.view.ProjectsActivity.ProjectSelector
import com.dempsey.teamworkapp.view.ProjectsAdapter.ProjectViewHolder

class ProjectsAdapter(
    private val projects: List<Project>,
    private val listener: ProjectSelector
): RecyclerView.Adapter<ProjectViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
  ): ProjectViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(layout.project_item, parent, false)
    return ProjectViewHolder(view)
  }

  override fun getItemCount(): Int {
    return projects.size
  }

  override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
    holder.bind(projects[position])
  }

  inner class ProjectViewHolder(view: View): ViewHolder(view) {

    private var rootView = view.findViewById<LinearLayout>(R.id.item_view)
    private var projectIdTv = view.findViewById(id.project_id) as TextView
    private var projectNameTv = view.findViewById(id.project_name) as TextView
    private var projectDescriptionTv = view.findViewById(id.project_description) as TextView

    fun bind(project: Project) {
      rootView.setOnClickListener { listener.onProjectSelected(project) }
      projectIdTv.text = project.id
      projectNameTv.text = project.name
      projectDescriptionTv.text = project.description
    }

  }

  interface ProjectSelected {

    fun onProjectSelected(project: Project)

  }
}