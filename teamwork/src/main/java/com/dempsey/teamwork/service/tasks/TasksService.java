package com.dempsey.teamwork.service.tasks;

import com.dempsey.teamwork.data.model.ProjectTask;

import io.reactivex.Observable;

public interface TasksService {

    Observable<ProjectTask> getTasksForProject(final String projectId);

}
