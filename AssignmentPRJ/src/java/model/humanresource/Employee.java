/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.humanresource;
import java.sql.Date;
import model.Planning.Department;

/**
 *
 * @author Duong Minh Quyen
 */
public class Employee {
    private int eid;
    private String ename;
    private boolean gender;
    private Date dob;
    private String phone;
    private Department did;
    private String salarylevel;
    private String position;
    private int hourrate;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDid() {
        return did;
    }

    public void setDid(Department did) {
        this.did = did;
    }

    public String getSalarylevel() {
        return salarylevel;
    }

    public void setSalarylevel(String salarylevel) {
        this.salarylevel = salarylevel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHourrate() {
        return hourrate;
    }

    public void setHourrate(int hourrate) {
        this.hourrate = hourrate;
    }
    
}
