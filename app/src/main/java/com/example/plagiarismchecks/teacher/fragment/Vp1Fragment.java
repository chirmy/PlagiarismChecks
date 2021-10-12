package com.example.plagiarismchecks.teacher.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.plagiarismchecks.R;
import com.example.plagiarismchecks.teacher.adapter.MyRecycleViewAdapter;
import com.example.plagiarismchecks.teacher.bean.Task;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Vp1Fragment extends Fragment {

    private List<Task> mWokingList;
    private RecyclerView mRecyclerView;
    private MyRecycleViewAdapter myRecycleViewAdapter;//适配器
    private LinearLayoutManager mLinearManager;//布局管理器

    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.tc_home_vp1,null);


        mWokingList = new ArrayList();
        mRecyclerView = view1.findViewById(R.id.rv_home_workingTask);
        InitData();

        //RecycleView
        mLinearManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        myRecycleViewAdapter = new MyRecycleViewAdapter(mWokingList);
        mRecyclerView.setLayoutManager(mLinearManager);
        mRecyclerView.setAdapter(myRecycleViewAdapter);
        //myRecycleViewAdapter.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(getContext(),"RecyclerView点击事件",Toast.LENGTH_SHORT).show());

        SwipeRefreshLayout swipeRefreshLayout= view1.findViewById(R.id.srl_home_workingRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                InitData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                    }
                },2000);
            }
        });



        return view1;
    }
    /*recyclerView初始化数据*/
    public void InitData(){
        for(int i = 1; i < 10;i++){
            mWokingList.add(new Task("单片机作业任务"+i,"提交人数：44/98","重复率："+i));
        }
    }
}
