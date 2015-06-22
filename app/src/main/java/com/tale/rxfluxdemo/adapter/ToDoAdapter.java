package com.tale.rxfluxdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tale.recyclerviewadapter.SimpleRecyclerViewAdapter;
import com.tale.recyclerviewadapter.viewholder.BaseViewHolder;
import com.tale.recyclerviewadapter.viewholder.Text1VH;

/**
 * Author tale. Created on 6/22/15.
 */
public class ToDoAdapter extends SimpleRecyclerViewAdapter<String> {

    @Override
    public BaseViewHolder<String> onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return new Text1VH<>(view, android.R.id.text1);
    }
}
