package com.dempsey.teamworkapp

import com.dempsey.teamwork.data.model.Project

interface ProjectsBusiness {

  fun filterByDueDate(projects: List<Project>): List<Project>

}