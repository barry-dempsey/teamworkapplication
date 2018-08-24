package com.dempsey.teamworkapp

import com.dempsey.teamwork.data.model.Project

class AppProjectsBusiness: ProjectsBusiness {


  override fun filterByDueDate(projects: List<Project>): List<Project> {
    return ArrayList(projects)
        .sortedWith(compareByDescending<Project> { it.endDate })
  }

  companion object {

    @JvmStatic
    fun newInstance(): AppProjectsBusiness =
        AppProjectsBusiness()

  }
}