package com.dempsey.teamworkapp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dempsey.teamwork.data.model.Task
import com.dempsey.teamworkapp.R

class TasksAdapter(
        private val tasks: List<Task>,
        private val listener: TaskSelected
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_tasks_list, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as TaskViewHolder).onBind(tasks[position])

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val taskTitle = itemView.findViewById<TextView>(R.id.task_title)

        fun onBind(task: Task) {
            taskTitle.setOnClickListener { listener.onTaskSelected(task) }
            taskTitle.text = task.name
        }
    }

    interface TaskSelected {

        fun onTaskSelected(task: Task)

    }
}