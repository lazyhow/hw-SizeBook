package com.example.hw_sizebook;


public class PersonActivity implements java.io.Serializable {

    public String name;
    public String date;
    public int neck;
    public int bust;
    public int chest;
    public int waist;
    public int hip;
    public int inseam;
    public String comment;

    public PersonActivity(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getNeck() {
        return neck;
    }
    public void setNeck(int neck) {
        this.neck = neck;
    }
    public int getBust() {
        return bust;
    }
    public void setBust(int bust) {
        this.bust = bust;
    }
    public int getChest() {
        return chest;
    }
    public void setChest(int chest) {
        this.chest = chest;
    }
    public int getWaist() {
        return waist;
    }
    public void setWaist(int waist) {
        this.waist = waist;
    }
    public int getHip() {
        return hip;
    }
    public void setHip(int hip) {
        this.hip = hip;
    }
    public int getInseam() {
        return inseam;
    }
    public void setInseam(int inseam) {
        this.inseam = inseam;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}