package com.ramakrishna.githubdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ramakrishna.githubdemo.fragment.IssueFragment;
import com.ramakrishna.githubdemo.fragment.MainFragment;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.app_name);

        if (savedInstanceState == null)
        {
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.mLandingContainer, mainFragment).commit();
        }
    }
// Make changes
    public void createIssueFragment(String name)
    {
        IssueFragment issueFragment = new IssueFragment();
        issueFragment.setRepoName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.mLandingContainer, issueFragment).addToBackStack("IssueFragment").commit();
    }
}
