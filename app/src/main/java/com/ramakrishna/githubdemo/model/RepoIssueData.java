package com.ramakrishna.githubdemo.model;

import org.json.JSONObject;

public class RepoIssueData
{
    public String title;

    public RepoIssueData(JSONObject jsonObject)
    {
        title = jsonObject.optString("title");
    }

    public String getTitle()
    {
        return title;
    }
}
