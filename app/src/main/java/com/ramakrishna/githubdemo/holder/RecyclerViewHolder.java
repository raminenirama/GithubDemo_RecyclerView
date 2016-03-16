package com.ramakrishna.githubdemo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ramakrishna.githubdemo.R;
import com.ramakrishna.githubdemo.interfaces.IRecyclerItemClickListener;
import com.ramakrishna.githubdemo.model.RepoData;
import com.ramakrishna.githubdemo.model.RepoIssueData;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private IRecyclerItemClickListener mIRecyclerItemClickListener;

    public RecyclerViewHolder(View itemView, IRecyclerItemClickListener listener)
    {
        super(itemView);
        mIRecyclerItemClickListener = listener;
    }

    public void onRepoViewHolder(RepoData repoData)
    {
        TextView mTextViewName = (TextView) itemView.findViewById(R.id.mTextViewName);
        mTextViewName.setText(repoData.getName());

        mTextViewName.setOnClickListener(this);
    }

    public void onRepoIssueViewHolder(RepoIssueData repoIssueData)
    {
        TextView mTextViewIssueName = (TextView) itemView.findViewById(R.id.mTextViewName);
        mTextViewIssueName.setText(repoIssueData.getTitle());
    }

    @Override
    public void onClick(View v)
    {
        onRowItemClicked(v);
    }

    private void onRowItemClicked(View view)
    {
        mIRecyclerItemClickListener.onRecyclerItemClick(view, getAdapterPosition());
    }
}
