package com.example.plagiarismchecks.teacher.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.plagiarismchecks.R;
import com.example.plagiarismchecks.teacher.adapter.MyRecycleViewAdapter;
import com.example.plagiarismchecks.teacher.adapter.ViewPagerAdapter;
import com.example.plagiarismchecks.teacher.fragment.Vp1Fragment;
import com.example.plagiarismchecks.teacher.fragment.Vp2Fragment;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar homeToolbar;
    private Button btnNewTask;
    private ViewPager myviewPager;
    private RadioButton rbtnWorking;
    private RadioButton rbtnFinished;
    private List<Fragment> list;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tc_home);

        InitView();

        //ActionBar
        setActionBar();

        //设置监听方式
        btnNewTask.setOnClickListener(this);
        rbtnWorking.setOnClickListener(this);
        rbtnFinished.setOnClickListener(this);
        myviewPager.setOnPageChangeListener(new MypagerChangeListener());

        //把fragment放到list集合里面
        list = new ArrayList<>();
        list.add(new Vp1Fragment());
        list.add(new Vp2Fragment());
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),list);
        myviewPager.setAdapter(adapter);
        myviewPager.setCurrentItem(0);  //初始化显示第一个页面

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        //设置高斯模糊
        //ImageView HomeBg = findViewById(R.id.imgViewBg);

        //int resource = R.drawable.home_bg;
        //Glide.with(this).load(resource).apply(RequestOptions.bitmapTransform(new BlurTransformation(this,10,3))).into(HomeBg);
        //Glide.with(this).load(resource).into(HomeBg);

    }
    /*初始化各控件*/
    private void InitView(){
        homeToolbar = findViewById(R.id.toolbar_home);
        btnNewTask = findViewById(R.id.btn_home_newTask);
        rbtnWorking = findViewById(R.id.rbtn_home_working);
        rbtnFinished = findViewById(R.id.rbtn_home_finished);
        myviewPager = findViewById(R.id.vp_home_tasks);

    }

    //设置状态栏为透明色
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            View decorView = getWindow().getDecorView();
            int option=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //设置actionbar透明色
   private void setActionBar(){

        setSupportActionBar(homeToolbar);
        ActionBar actionBar=getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        //google的actionbar是分为上下两栏显示的，上面的代码只能设置顶部actionbar的背景色，
        //为了让下面的背景色一致，还需要添加一行代码：
        actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        //隐藏Label标签
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setTitle("智助教");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toobar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.localCheckup_item:
                Toast.makeText(this,"本地查重 clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.classes_item:
                Toast.makeText(this,"我的班级 clicked",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting_item:
                Toast.makeText(this,"设置 clicked",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_home_newTask:
                //Toast.makeText(this,"发起新任务 clicked",Toast.LENGTH_SHORT).show();
                Intent publishIntent = new Intent(this,PublishTaskActivity.class);
                startActivity(publishIntent);

                break;
            case R.id.rbtn_home_working:
                myviewPager.setCurrentItem(0);

                //Toast.makeText(this,"任务中 clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbtn_home_finished:
                myviewPager.setCurrentItem(1);

                //Toast.makeText(this,"已结束 clicked",Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }
    }

    public class MypagerChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    rbtnWorking.setChecked(true);
                    break;
                case 1:
                    rbtnFinished.setChecked(true);
                    break;
                default:
                    break;

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

}
