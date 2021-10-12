package com.example.plagiarismchecks.teacher.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment>mlist;

    public ViewPagerAdapter(@NonNull @NotNull FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.mlist = list;
    }


    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }
}
