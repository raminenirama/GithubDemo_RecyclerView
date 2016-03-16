package com.ramakrishna.githubdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ramakrishna.githubdemo.R;
import com.ramakrishna.githubdemo.adapter.IssueViewAdapter;
import com.ramakrishna.githubdemo.interfaces.IRecyclerItemClickListener;
import com.ramakrishna.githubdemo.model.RepoIssueData;
import com.ramakrishna.githubdemo.network.ConnectionDetector;
import com.ramakrishna.githubdemo.request.RequestError;
import com.ramakrishna.githubdemo.request.VolleyRequestQueue;
import com.ramakrishna.githubdemo.utils.ApplicationURL;
import com.ramakrishna.githubdemo.utils.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IssueFragment extends Fragment implements IRecyclerItemClickListener, Response.Listener, Response.ErrorListener
{
    private static final String REQUEST_TAG = "IssueFragment";

    private ProgressBar mProgressBarView;
    private IssueViewAdapter mIssueViewAdapter;

    private ArrayList<RepoIssueData> mRepoIssueDataList;

    private String mUsername = "octokit";
    private String mRepoName;

    public void setRepoName(String name)
    {
        mRepoName = name;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("RepoName", mRepoName);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View mainView = inflater.inflate(R.layout.fragment_main, container, false);
        init(mainView, savedInstanceState);
        getRepoList();
        return mainView;
    }

    private void init(View mainView, Bundle savedInstanceState)
    {
        if (savedInstanceState != null)
        {
            mRepoName = savedInstanceState.getString("RepoName");
        }

        mRepoIssueDataList = new ArrayList<>();

        mProgressBarView = (ProgressBar) mainView.findViewById(R.id.mProgressBarView);
        RecyclerView mRecyclerView = (RecyclerView) mainView.findViewById(R.id.mRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mIssueViewAdapter = new IssueViewAdapter(mRepoIssueDataList, this);
        mRecyclerView.setAdapter(mIssueViewAdapter);
    }

    private void getRepoList()
    {
        if (ConnectionDetector.isConnectedToInternet(getActivity()))
        {
            mProgressBarView.setVisibility(View.VISIBLE);
            VolleyRequestQueue lockatedVolleyRequestQueue = VolleyRequestQueue.getInstance(getActivity());
            lockatedVolleyRequestQueue.requestDataOverNetwork(REQUEST_TAG, Request.Method.GET, ApplicationURL.repoIssuesURL + mUsername + "/" + mRepoName + "/issues", null, this, this);
        }
        else
        {
            Utilities.showToastMessage(getActivity(), getActivity().getResources().getString(R.string.internet_connection_error));
        }
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        if (getActivity() != null)
        {
            mProgressBarView.setVisibility(View.INVISIBLE);
            RequestError.onRequestError(getActivity(), error);
        }
    }

    @Override
    public void onResponse(Object response)
    {
        if (getActivity() != null)
        {
            mProgressBarView.setVisibility(View.INVISIBLE);
            JSONArray jsonArray = (JSONArray) response;
            try
            {
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject repoObject = jsonArray.getJSONObject(i);
                    RepoIssueData repoData = new RepoIssueData(repoObject);
                    mRepoIssueDataList.add(mRepoIssueDataList.size(), repoData);
                }

                mIssueViewAdapter.notifyDataSetChanged();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRecyclerItemClick(View view, int position)
    {

    }
}
