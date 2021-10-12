package com.example.plagiarismchecks.teacher.bean;

public class Task {
    private String taskTitle;//任务标题

    private String taskSubmitNum;//任务提交人数

    private String taskRepeatRate;//任务作业重复率

    public Task(String taskTitle, String taskSubmitNum, String taskRepeatRate) {
        this.taskTitle = taskTitle;
        this.taskSubmitNum = taskSubmitNum;
        this.taskRepeatRate = taskRepeatRate;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskSubmitNum() {
        return taskSubmitNum;
    }

    public void setTaskSubmitNum(String taskSubmitNum) {
        this.taskSubmitNum = taskSubmitNum;
    }

    public String getTaskRepeatRate() {
        return taskRepeatRate;
    }

    public void setTaskRepeatRate(String taskRepeatRate) {
        this.taskRepeatRate = taskRepeatRate;
    }
}
