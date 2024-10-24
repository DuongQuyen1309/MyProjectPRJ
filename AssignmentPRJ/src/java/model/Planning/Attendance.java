/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Planning;

/**
 *
 * @author Duong Minh Quyen
 */
public class Attendance {

    private int aid;
    private int waeid;
    private int actualquantity;
    private float alpha;
    private String note;

    // Getters and Setters
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getWaeid() {
        return waeid;
    }

    public void setWaeid(int waeid) {
        this.waeid = waeid;
    }

    public int getActualquantity() {
        return actualquantity;
    }

    public void setActualquantity(int actualquantity) {
        this.actualquantity = actualquantity;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
