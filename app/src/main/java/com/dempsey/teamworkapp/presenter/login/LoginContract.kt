package com.dempsey.teamworkapp.presenter.login

import com.dempsey.teamwork.data.model.Account

interface LoginContract {

  interface View {

    fun showLoading()

    fun hideLoading()

    fun showSuccess(account: Account)

    fun showError(error: Int)

  }

  interface ActionListener {

    fun loginUser(apiKey: String)

  }
}