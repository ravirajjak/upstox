package com.indie.upstox.adapter;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by akhil on 12/12/15.
 */
public class DataBindingRecyclerViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T mBinding;

    public DataBindingRecyclerViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }

    public void disableRecyclable() {
        this.setIsRecyclable(false);
    }
}