package com.dempsey.teamwork.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProjectTask {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("tasklists")
    @Expose
    private List<Tasklist> tasklists;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<Tasklist> getTasklists() {
        return tasklists;
    }

    public void setTasklists(List<Tasklist> tasklists) {
        this.tasklists = tasklists;
    }
}
