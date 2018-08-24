package com.dempsey.teamworkapp.presenter

import com.dempsey.teamwork.data.model.Project

interface ProjectDetailContract {

  interface View {

    fun showLoading()

    fun hideLoading()

    fun showSuccess()

    fun showError()

  }

  interface ActionListener {

    fun updateProjectDetails(project: Project)

  }
}