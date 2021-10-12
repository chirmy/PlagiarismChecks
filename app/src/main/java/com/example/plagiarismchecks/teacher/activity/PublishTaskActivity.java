package com.example.plagiarismchecks.teacher.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.plagiarismchecks.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PublishTaskActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "PublishTaskActivity";
    private Toolbar publishToolbar;
    private Button btnHwType;
    private Button btnHwTerm;
    private Button btnHwLanguage;
    private Button btnHwSubmit;
    private int hwTypeChoice;
    private int hwLanguageChoice;
    final String[] types = {"文本作业","程序作业","图形作业"};
    final String[] languages = {"C语言","C++","Java","Python","text","doc"};
    private static int Year = -1;
    private static int MonthOfYear = -1;
    private static int DayOfMonth = -1;
    private static int HourOfDay = -1;
    private static int Minute = -1;
    DateFormat format = DateFormat.getDateTimeInstance();
    Calendar calendar = Calendar.getInstance(Locale.CHINA);



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tc_publish);
        //Init
        InitView();

        //点击事件
        btnHwType.setOnClickListener(this);
        btnHwTerm.setOnClickListener(this);
        btnHwLanguage.setOnClickListener(this);
        btnHwSubmit.setOnClickListener(this);

        //ActionBar
        setActionBar();

    }

    private void InitView(){
        publishToolbar = findViewById(R.id.toolbar_publish);
        btnHwType = findViewById(R.id.btn_publish_hwType);
        btnHwTerm = findViewById(R.id.btn_publish_hwTerm);
        btnHwLanguage = findViewById(R.id.btn_publish_hwlanguage);
        btnHwSubmit = findViewById(R.id.btn_publish_hwSubmit);
    }

    //设置状态栏为透明色
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if(hasFocus){
//            View decorView = getWindow().getDecorView();
//            int option=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    |View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//    }

    //设置actionbar透明色
    private void setActionBar(){
        setSupportActionBar(publishToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        //google的actionbar是分为上下两栏显示的，上面的代码只能设置顶部actionbar的背景色，
        //为了让下面的背景色一致，还需要添加一行代码：
        actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        //隐藏Label标签
        //actionBar.setDisplayShowTitleEnabled(false);
        publishToolbar.setTitle("发布新任务");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_publish_hwType:
                //Toast.makeText(this,"作业类型 Clicked",Toast.LENGTH_LONG).show();
                showHwTypeSingleChoiceDialog();
                break;
            case R.id.btn_publish_hwTerm:
                //Toast.makeText(this,"作业期限 Clicked",Toast.LENGTH_LONG).show();
                showTimePickerDialog(this, 0,calendar);
                showDatePickerDialog(this,7,calendar);

                break;
            case R.id.btn_publish_hwlanguage:
                //Toast.makeText(this,"检测语言 Clicked",Toast.LENGTH_LONG).show();
                showHwLanguageSingleChoiceDialog();
                break;
            case R.id.btn_publish_hwSubmit:
                Toast.makeText(this,"任务提交 Clicked",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    private void showHwTypeSingleChoiceDialog(){
        hwTypeChoice = -1;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(PublishTaskActivity.this);
        singleChoiceDialog.setTitle("作业类型");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(types, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hwTypeChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (hwTypeChoice != -1) {
                            Toast.makeText(PublishTaskActivity.this,
                                    "你选择了" + types[hwTypeChoice],
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        singleChoiceDialog.show();
    }

    private void showHwLanguageSingleChoiceDialog(){
        hwLanguageChoice = -1;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(PublishTaskActivity.this);
        singleChoiceDialog.setTitle("检测语言");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(languages, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hwLanguageChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (hwLanguageChoice != -1) {
                            Toast.makeText(PublishTaskActivity.this,
                                    "你选择了" + languages[hwLanguageChoice],
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        singleChoiceDialog.show();
    }

    /**
     * 日期选择
     * @param activity
     * @param themeResId
     * @param calendar
     */
    public static void showDatePickerDialog(Activity activity, int themeResId, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                Year = year;
                MonthOfYear = month;
                DayOfMonth = month;
                Toast.makeText(view.getContext(), "您选择了：" + Year+ "年" + (MonthOfYear + 1) + "月" + DayOfMonth + "日",Toast.LENGTH_LONG).show();

            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

//    /**
//     * 时间选择
//     * @param activity
//     * @param themeResId
//     * @param tv
//     * @param calendar
//     */
    public static void showTimePickerDialog(Activity activity,int themeResId,  Calendar calendar) {
        // Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        // 解释一哈，Activity是context的子类
        new TimePickerDialog( activity,themeResId,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //tv.setText("您选择了：" + hourOfDay + "时" + minute  + "分");
                        HourOfDay = hourOfDay;
                        Minute = minute;
                        Toast.makeText(view.getContext(),"您选择了：" + HourOfDay + "时" + Minute  + "分" ,Toast.LENGTH_LONG).show();
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                // true表示采用24小时制
                ,true).show();
    }















}
