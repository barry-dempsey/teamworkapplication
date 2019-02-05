package com.dempsey.teamwork.service.tasks;

import com.dempsey.teamwork.data.model.Project;
import com.dempsey.teamwork.data.model.ProjectTask;
import com.dempsey.teamwork.data.net.ApiClient;
import com.dempsey.teamwork.service.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public class TasksServiceImpl implements TasksService {

    private static final Type TASK_LIST_TYPE = new TypeToken<ArrayList<Project>>() {}.getType();
    private static final String PROJECTS_WITH_ID_URL_PATH = "/projects/%s.json";
    private final ApiClient apiClient;
    private final Gson gson;

    TasksServiceImpl(final ApiClient apiClient, final Gson gson) {
        this.apiClient = apiClient;
        this.gson = gson;
    }

    @Override
    public Observable<ProjectTask> getTasksForProject(String projectId) {
        return apiClient.withPath(projectsUrlPath(projectId))
                .get()
                .map(Util::getContent)
                .map(content -> gson.fromJson(content, TASK_LIST_TYPE));
    }

    private String projectsUrlPath(@NonNull String projectId) {
        return String.format(PROJECTS_WITH_ID_URL_PATH, projectId);
    }
}
