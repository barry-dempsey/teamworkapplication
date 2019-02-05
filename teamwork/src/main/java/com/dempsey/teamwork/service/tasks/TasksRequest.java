package com.dempsey.teamwork.service.tasks;

import com.dempsey.teamwork.data.model.ProjectTask;
import com.dempsey.teamwork.data.net.ApiClient;
import com.dempsey.teamwork.service.BaseRequest;

import io.reactivex.Observable;

public class TasksRequest extends BaseRequest<TasksService> {

    private static class TaskRequestLoader {
        private static final TasksRequest INSTANCE = new TasksRequest();
    }

    @Override
    public void init(ApiClient apiClient) {
        this.service = new TasksServiceImpl(apiClient, gson);
    }

    public static TasksRequest getInstance() {
        return TaskRequestLoader.INSTANCE;
    }

    public Observable<ProjectTask> getAllTasksForProject(final String projectId) {
        return service.getTasksForProject(projectId);
    }
}
