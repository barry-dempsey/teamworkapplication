package com.dempsey.teamworkapp.presenter.login

interface LoginContract {

  interface View {

    fun showLoading()

    fun hideLoading()

    fun showSuccess()

    fun showError(error: Int)

  }

  interface ActionListener {

    fun loginUser(apiKey: String)

  }
}