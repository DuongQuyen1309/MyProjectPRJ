/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Planning;

import model.humanresource.Employee;

/**
 *
 * @author Duong Minh Quyen
 */
public class WorkAssignmentEmp {
    private int waeid;
    private int dppid;
    private Employee eid;
    private int orderquantity;
    private Attendance attendedPerson;

    public Attendance getAttendedPerson() {
        return attendedPerson;
    }

    public void setAttendedPerson(Attendance attendedPerson) {
        this.attendedPerson = attendedPerson;
    }

    public int getWaeid() {
        return waeid;
    }

    public void setWaeid(int waeid) {
        this.waeid = waeid;
    }

    public int getDppid() {
        return dppid;
    }

    public void setDppid(int dppid) {
        this.dppid = dppid;
    }

    public Employee getEid() {
        return eid;
    }

    public void setEid(Employee eid) {
        this.eid = eid;
    }

    public int getOrderquantity() {
        return orderquantity;
    }

    public void setOrderquantity(int orderquantity) {
        this.orderquantity = orderquantity;
    }
    
}
