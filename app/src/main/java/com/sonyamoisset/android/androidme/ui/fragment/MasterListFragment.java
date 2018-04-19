package com.sonyamoisset.android.androidme.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sonyamoisset.android.androidme.R;
import com.sonyamoisset.android.androidme.data.AndroidImageAssets;
import com.sonyamoisset.android.androidme.ui.adapter.MasterListAdapter;

public class MasterListFragment extends Fragment {
    private OnImageClickListener callback;

    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

    public MasterListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list,
                container,
                false);

        MasterListAdapter adapter = new MasterListAdapter(getContext(),
                AndroidImageAssets.getAll());

        GridView gridView = rootView.findViewById(R.id.grid_view_images);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                callback.onImageSelected(position);
            }
        });

        return rootView;
    }
}
