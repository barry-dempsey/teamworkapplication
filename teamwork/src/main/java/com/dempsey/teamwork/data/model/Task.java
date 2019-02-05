package com.dempsey.teamwork.data.model;

import com.google.gson.annotations.SerializedName;

public class Task {
    @SerializedName("name")
    private String name;
    @SerializedName("pinned")
    private boolean pinned;
    @SerializedName("description")
    private String description;
    @SerializedName("complete")
    private boolean complete;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
