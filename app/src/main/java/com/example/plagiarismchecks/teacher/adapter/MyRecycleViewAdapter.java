package com.example.plagiarismchecks.teacher.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plagiarismchecks.R;
import com.example.plagiarismchecks.teacher.bean.Task;
import com.example.plagiarismchecks.teacher.interfac.OnRecyItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyHolder> {

    private List<Task> mTaskLists;

    private OnRecyItemClickListener mOnItemClickListener;//声明接口



    public MyRecycleViewAdapter(List<Task> list){
        mTaskLists = list;
    }

//    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
//        mOnItemClickListener = (OnRecyItemClickListener) onItemClickListener;
//    }


    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tc_home_item,parent,false);
        MyHolder holder = new MyHolder(view);

        //直接实现点击事件
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "recyclerView clicked",Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyRecycleViewAdapter.MyHolder holder, int position) {
        Task task = mTaskLists.get(position);
        holder.tvTitle.setText(task.getTaskTitle());
        holder.tvSubmitSum.setText(task.getTaskSubmitNum());
        holder.tvRepetRate.setText(task.getTaskRepeatRate());

        View itemView = ((RelativeLayout)holder.itemView).getChildAt(0);
        if(mOnItemClickListener!=null){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTaskLists.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;//作业标题
        Button btnView;//点击查看
        TextView tvSubmitSum;//提交人数
        TextView tvRepetRate;//重复率


        public MyHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_homeRvItem_title);
            btnView = itemView.findViewById(R.id.btn_homeRvItem_view);
            tvSubmitSum = itemView.findViewById(R.id.tv_homeRvItem_submitSum);
            tvRepetRate = itemView.findViewById(R.id.tv_homeRvItem_repetRate);
        }
    }

 }
