package com.ly.excel.jira;

import com.alibaba.excel.annotation.ExcelProperty;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.Date;

public class JiraData {
    @ExcelProperty("任务名称")
    private String name;//任务名称
    @ExcelProperty("任务描述")
    private String description;//任务描述
    @ExcelProperty("部门")
    private String department;//部门
    @ExcelProperty("需求子条目")
    private String subentry;//需求子条目
    @ExcelProperty("设计负责人")
    private String designHeads;//设计负责人
    @ExcelProperty("编码负责人")
    private String codingHeads;//编码负责人
    @ExcelProperty("功能测试负责人")
    private String funTestingHeads;//功能测试负责人
    @ExcelProperty("设计到期日")
    private String designExpiryDate;//设计到期日
    @ExcelProperty("编码到期日")
    private String codingExpiryDate;//编码到期日
    @ExcelProperty("功能测试到期日")
    private String funTestingExpiryDate;//功能测试到期日
    @ExcelProperty("是否核心程序")
    private String isCoreProgram;//是否核心程序
    @ExcelProperty("jira描述")
    private String jiraDescription;//jira描述

    @Override
    public String toString() {
        return "JiraData{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", department='" + department + '\'' +
                ", subentry='" + subentry + '\'' +
                ", designHeads='" + designHeads + '\'' +
                ", codingHeads='" + codingHeads + '\'' +
                ", funTestingHeads='" + funTestingHeads + '\'' +
                ", designExpiryDate='" + designExpiryDate + '\'' +
                ", codingExpiryDate='" + codingExpiryDate + '\'' +
                ", funTestingExpiryDate='" + funTestingExpiryDate + '\'' +
                ", isCoreProgram='" + isCoreProgram + '\'' +
                ", jiraDescription='" + jiraDescription + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSubentry() {
        return subentry;
    }

    public void setSubentry(String subentry) {
        this.subentry = subentry;
    }

    public String getDesignHeads() {
        return designHeads;
    }

    public void setDesignHeads(String designHeads) {
        this.designHeads = designHeads;
    }

    public String getCodingHeads() {
        return codingHeads;
    }

    public void setCodingHeads(String codingHeads) {
        this.codingHeads = codingHeads;
    }

    public String getFunTestingHeads() {
        return funTestingHeads;
    }

    public void setFunTestingHeads(String funTestingHeads) {
        this.funTestingHeads = funTestingHeads;
    }

    public String getDesignExpiryDate() {
        return designExpiryDate;
    }

    public void setDesignExpiryDate(String designExpiryDate) {
        this.designExpiryDate = designExpiryDate;
    }

    public String getCodingExpiryDate() {
        return codingExpiryDate;
    }

    public void setCodingExpiryDate(String codingExpiryDate) {
        this.codingExpiryDate = codingExpiryDate;
    }

    public String getFunTestingExpiryDate() {
        return funTestingExpiryDate;
    }

    public void setFunTestingExpiryDate(String funTestingExpiryDate) {
        this.funTestingExpiryDate = funTestingExpiryDate;
    }

    public String getIsCoreProgram() {
        return isCoreProgram;
    }

    public void setIsCoreProgram(String isCoreProgram) {
        this.isCoreProgram = isCoreProgram;
    }

    public String getJiraDescription() {
        return jiraDescription;
    }

    public void setJiraDescription(String jiraDescription) {
        this.jiraDescription = jiraDescription;
    }
}
