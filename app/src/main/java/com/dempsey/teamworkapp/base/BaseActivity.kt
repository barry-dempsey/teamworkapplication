package com.dempsey.teamworkapp.base

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dempsey.teamworkapp.R
import com.dempsey.teamworkapp.extensions.inTransaction

abstract class BaseActivity<P: BasePresenter>: AppCompatActivity() {

  protected lateinit var presenter: P

  @SuppressLint("RestrictedApi")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setHomeButtonEnabled(true)
    supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    presenter = instantiatePresenter()
  }

  protected fun addFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction { add(R.id.fragment_content, fragment) }
  }

  protected fun replaceFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction { replace(R.id.fragment_content, fragment) }
  }

  abstract fun instantiatePresenter(): P

  protected fun connectivityManager() = getSystemService(
      Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}