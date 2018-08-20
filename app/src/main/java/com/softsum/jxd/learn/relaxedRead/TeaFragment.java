package com.softsum.jxd.learn.relaxedRead;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.softsum.jxd.learn.R;
import com.softsum.jxd.learn.kanmeiziRecyc.MeiziAdapter;
import com.softsum.jxd.learn.tea.TeaBean;
import com.softsum.jxd.learn.tea.TeaRecycleAdapter;

import static android.content.ContentValues.TAG;

public class TeaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View view;
    private TeaRecycleAdapter teaRecycleAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tea_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.tea_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        teaRecycleAdapter = new TeaRecycleAdapter(recyclerView);
        recyclerView.setAdapter(teaRecycleAdapter);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d("TeaFragment", "setUserVisibleHint: Show");
            //相当于Fragment的onResume
        } else {
            Log.d("TeaFragment", "setUserVisibleHint: Hidvel");
            //相当于Fragment的onPause
        }
    }

    @Override
    public void onRefresh() {

    }
}
