package com.dangolawski.models;

public class MatrixItem {

    private String key1;

    private String key2;

    private double value;

    public MatrixItem(String key1, String key2, double value) {
        this.key1 = key1;
        this.key2 = key2;
        this.value = value;
    }

    // SETTERS //
    public void setKey1(String key1) { this.key1 = key1; }

    public void setKey2(String key2) { this.key2 = key2; }

    public void setValue(double value) { this.value = value; }

    // GETTERS //
    public String getKey1() { return key1; }

    public String getKey2() { return key2; }

    public double getValue() { return value; }
}
