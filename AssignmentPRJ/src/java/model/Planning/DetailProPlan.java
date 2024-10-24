/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Planning;
import java.sql.Date;
import java.util.ArrayList;
/**
 *
 * @author Duong Minh Quyen
 */
public class DetailProPlan {
    private int dppid;
    private int gpid;
    private Date date;
    private int sid;
    private int quantity;
    private String note;
    private ArrayList<WorkAssignmentEmp> worklist = new ArrayList<>();

    public ArrayList<WorkAssignmentEmp> getWorklist() {
        return worklist;
    }

    public void setWorklist(ArrayList<WorkAssignmentEmp> worklist) {
        this.worklist = worklist;
    }

    public int getDppid() {
        return dppid;
    }

    public void setDppid(int dppid) {
        this.dppid = dppid;
    }

    public int getGpid() {
        return gpid;
    }

    public void setGpid(int gpid) {
        this.gpid = gpid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
