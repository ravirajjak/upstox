package com.indie.upstox.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Akhil on 13/01/16.
 */
// TODO: 28/06/22 [Arrow] Move it to the common package
public abstract class DataBindingRecyclerViewAdapter extends RecyclerView.Adapter<DataBindingRecyclerViewHolder> {

    protected List<ViewModel> mViewModels = new ArrayList<>();

    private boolean isRecyclable = false;

    public DataBindingRecyclerViewAdapter(List<ViewModel> viewModels) {
        this.mViewModels = viewModels;
        onViewModelListChanged();
    }

    public DataBindingRecyclerViewAdapter(List<ViewModel> viewModels, boolean isRecyclable) {
        this.mViewModels = viewModels;
        this.isRecyclable = isRecyclable;
        onViewModelListChanged();
    }

    @Override
    public DataBindingRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);

        DataBindingRecyclerViewHolder viewHolder = new DataBindingRecyclerViewHolder<>(DataBindingUtil.bind(v));
        // TODO Will implement smooth scrolling in recyclerview in next version
        if (!isRecyclable) viewHolder.disableRecyclable();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataBindingRecyclerViewHolder holder, int position) {
        if (mViewModels != null && mViewModels.size() > position) {
            ViewModel viewModel = mViewModels.get(position);
            if (viewModel != null) {
                holder.getBinding().setVariable(com.indie.upstox.BR.vm, viewModel);
                holder.getBinding().executePendingBindings();
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getViewModelLayoutMap().get(mViewModels.get(position).getClass());
    }


    @Override
    public int getItemCount() {
        return mViewModels == null ? 0 : mViewModels.size();
    }

    public abstract Map<Class, Integer> getViewModelLayoutMap();


    public void onViewModelListChanged() {
        notifyDataSetChanged();
    }

    public void close() {
    }

    public void refreshList(List<ViewModel> viewModels) {
        mViewModels.clear();
        mViewModels.addAll(viewModels);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public ViewModel getViewModel(int position) {
        return mViewModels.get(position);
    }


}
