package com.dempsey.teamworkapp.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dempsey.teamworkapp.R
import com.dempsey.teamworkapp.extensions.inTransaction

abstract class BaseActivity<P: BasePresenter>: AppCompatActivity() {

  protected lateinit var presenter: P

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter = instantiatePresenter()
  }

  fun isConnectedOrConnecting(f: ()-> Unit) {
    val activeNetworkInfo = connectivityManager().activeNetworkInfo
    if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting) {
      f()
    }
  }

  protected fun addFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction { add(R.id.fragment_content, fragment) }
  }

  abstract fun instantiatePresenter(): P

  private fun connectivityManager() = getSystemService(
      Context.CONNECTIVITY_SERVICE) as ConnectivityManager


}