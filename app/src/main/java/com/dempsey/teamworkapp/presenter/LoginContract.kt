package com.dempsey.teamworkapp.presenter

import com.dempsey.teamwork.data.model.Projects

interface LoginContract {

  interface View {

    fun showProgress()

    fun hideProgress()

    fun showSuccess()

    fun showError(error: Int)

    fun showProjectsForUser(projects: Projects)

  }

  interface ActionListener {

    fun loginUser(apiKey: String)

    fun getProjects()

  }

}