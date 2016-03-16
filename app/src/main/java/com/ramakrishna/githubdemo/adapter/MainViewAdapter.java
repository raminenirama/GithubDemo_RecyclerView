package com.ramakrishna.githubdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramakrishna.githubdemo.R;
import com.ramakrishna.githubdemo.holder.RecyclerViewHolder;
import com.ramakrishna.githubdemo.interfaces.IRecyclerItemClickListener;
import com.ramakrishna.githubdemo.model.RepoData;

import java.util.ArrayList;

public class MainViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>
{
    private ArrayList<RepoData> mRepoData;
    private IRecyclerItemClickListener mIRecyclerItemClickListener;

    public MainViewAdapter(ArrayList<RepoData> data, IRecyclerItemClickListener listener)
    {
        mRepoData = data;
        mIRecyclerItemClickListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_row, parent, false);
        return new RecyclerViewHolder(itemView, mIRecyclerItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {
        holder.onRepoViewHolder(mRepoData.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mRepoData.size();
    }
}
