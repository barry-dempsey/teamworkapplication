package com.dempsey.teamworkapp

import android.app.Application
import com.dempsey.teamwork.Teamwork

class TeamworkApplication: Application(){

  override fun onCreate() {
    super.onCreate()
    Teamwork.initialize(this)
  }

}