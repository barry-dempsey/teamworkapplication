package com.dempsey.teamworkapp.presenter.project

import com.dempsey.teamwork.data.model.Project

interface ProjectsContract {

  interface View {

    fun showLoadingMessage()

    fun reloadData(projects: List<Project>)

  }

  interface ActionListener {

    fun sortByDueData(projects: List<Project>)

  }

}