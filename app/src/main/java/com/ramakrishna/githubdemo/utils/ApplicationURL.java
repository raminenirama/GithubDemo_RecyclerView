package com.ramakrishna.githubdemo.utils;

public class ApplicationURL
{
    private static final String BaseURL = "https://api.github.com/";

    public static String repoURL;
    public static String repoIssuesURL;

    static
    {
        repoURL = BaseURL + "orgs/";
        repoIssuesURL = BaseURL + "repos/";
    }
}
