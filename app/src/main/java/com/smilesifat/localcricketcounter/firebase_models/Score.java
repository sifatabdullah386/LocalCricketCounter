package com.smilesifat.localcricketcounter.firebase_models;

public class Score {

    private int balls;
    private int runs;
    private int wkts;
    private int wides;
    private int noballs;
    private int zeroRuns;
    private int oneRuns;
    private int twoRuns;
    private int threeRuns;
    private int fourRuns;
    private int sixRuns;
    private int legByRuns;
    private int byeRuns;

    public Score(){

    }

    public Score(int balls, int runs, int wkts, int wides, int noballs, int zeroRuns, int oneRuns, int twoRuns, int threeRuns, int fourRuns, int sixRuns, int legByRuns, int byeRuns) {
        this.balls = balls;
        this.runs = runs;
        this.wkts = wkts;
        this.wides = wides;
        this.noballs = noballs;
        this.zeroRuns = zeroRuns;
        this.oneRuns = oneRuns;
        this.twoRuns = twoRuns;
        this.threeRuns = threeRuns;
        this.fourRuns = fourRuns;
        this.sixRuns = sixRuns;
        this.legByRuns = legByRuns;
        this.byeRuns = byeRuns;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWkts() {
        return wkts;
    }

    public void setWkts(int wkts) {
        this.wkts = wkts;
    }

    public int getWides() {
        return wides;
    }

    public void setWides(int wides) {
        this.wides = wides;
    }

    public int getNoballs() {
        return noballs;
    }

    public void setNoballs(int noballs) {
        this.noballs = noballs;
    }

    public int getZeroRuns() {
        return zeroRuns;
    }

    public void setZeroRuns(int zeroRuns) {
        this.zeroRuns = zeroRuns;
    }

    public int getOneRuns() {
        return oneRuns;
    }

    public void setOneRuns(int oneRuns) {
        this.oneRuns = oneRuns;
    }

    public int getTwoRuns() {
        return twoRuns;
    }

    public void setTwoRuns(int twoRuns) {
        this.twoRuns = twoRuns;
    }

    public int getThreeRuns() {
        return threeRuns;
    }

    public void setThreeRuns(int threeRuns) {
        this.threeRuns = threeRuns;
    }

    public int getFourRuns() {
        return fourRuns;
    }

    public void setFourRuns(int fourRuns) {
        this.fourRuns = fourRuns;
    }

    public int getSixRuns() {
        return sixRuns;
    }

    public void setSixRuns(int sixRuns) {
        this.sixRuns = sixRuns;
    }

    public int getLegByRuns() {
        return legByRuns;
    }

    public void setLegByRuns(int legByRuns) {
        this.legByRuns = legByRuns;
    }

    public int getByeRuns() {
        return byeRuns;
    }

    public void setByeRuns(int byeRuns) {
        this.byeRuns = byeRuns;
    }
}
