/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Planning;

import java.util.ArrayList;

/**
 *
 * @author Duong Minh Quyen
 */
public class GeneralPlan {
    private int gpid;
    private Plan plan;
    private Product product;
    private int quantity;
    private float estimatedeffort;
    private int completedamount;
    private int remainedamount;
    private ArrayList<DetailProPlan> detailplanlist = new ArrayList<>();

    public int getCompletedamount() {
        return completedamount;
    }

    public void setCompletedamount(int completedamount) {
        this.completedamount = completedamount;
    }

    public int getRemainedamount() {
        return remainedamount;
    }

    public void setRemainedamount(int remainedamount) {
        this.remainedamount = remainedamount;
    }

    public ArrayList<DetailProPlan> getDetailplanlist() {
        return detailplanlist;
    }

    public void setDetailplanlist(ArrayList<DetailProPlan> detailplanlist) {
        this.detailplanlist = detailplanlist;
    }
    

    public int getGpid() {
        return gpid;
    }

    public void setGpid(int gpid) {
        this.gpid = gpid;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getEstimatedeffort() {
        return estimatedeffort;
    }

    public void setEstimatedeffort(float estimatedeffort) {
        this.estimatedeffort = estimatedeffort;
    }
    
}
