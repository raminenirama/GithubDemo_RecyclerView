package com.ramakrishna.githubdemo.model;

import org.json.JSONObject;

public class RepoData
{
    public String name;

    public RepoData(JSONObject jsonObject)
    {
        name = jsonObject.optString("name");
    }

    public String getName()
    {
        return name;
    }
}
