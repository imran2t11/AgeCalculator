package com.example.borhan.foodorder;

public class FoodAttributes {

    private String foodname;
    private double foodprice;
    private int foodimage;
    private String foodtype;

    public FoodAttributes(String foodname, double foodprice, int foodimage, String foodtype) {
        this.foodname = foodname;
        this.foodprice = foodprice;
        this.foodimage = foodimage;
        this.foodtype = foodtype;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public double getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(double foodprice) {
        this.foodprice = foodprice;
    }

    public int getFoodimage() {
        return foodimage;
    }

    public void setFoodimage(int foodimage) {
        this.foodimage = foodimage;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }
}
