/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Planning;

import java.sql.Date;
import java.util.ArrayList;
import model.auth.User;

/**
 *
 * @author Duong Minh Quyen
 */
public class Plan {
    private int pid;
    private String pname;
    private Date start;
    private Date end;
    private Department dept;
    private String status;
    private User createdby;
    private java.util.Date createdtime;
    private ArrayList<GeneralPlan> generalplan = new ArrayList<>();

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
    }

    public java.util.Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(java.util.Date createdtime) {
        this.createdtime = createdtime;
    }

    public ArrayList<GeneralPlan> getGeneralplan() {
        return generalplan;
    }

    public void setGeneralplan(ArrayList<GeneralPlan> generalplan) {
        this.generalplan = generalplan;
    }
    
    
}
