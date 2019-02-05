package com.dempsey.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectTask {
    @SerializedName("status")
    private String status;
    @SerializedName("task_list")
    private List<Task> taskList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
