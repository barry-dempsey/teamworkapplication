package com.dempsey.teamworkapp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dempsey.teamwork.data.model.Task
import com.dempsey.teamworkapp.R

class TasksAdapter(
        private val tasks: List<Task>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_task_detail, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as TaskViewHolder).onBind(tasks[position])

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titleView = itemView.findViewById<TextView>(R.id.task_header)
        private val descriptionView = itemView.findViewById<TextView>(R.id.task_description)

        fun onBind(task: Task) {
            titleView.text = task.name
            descriptionView.text = task.description
        }
    }
}