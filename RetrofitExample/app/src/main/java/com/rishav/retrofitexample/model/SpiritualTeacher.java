package com.rishav.retrofitexample.model;

public class SpiritualTeacher {
    private String name, quote;
    private int image;
    private boolean isSelected;

    public SpiritualTeacher(String name, String quote, int image) {
        this.name = name;
        this.quote = quote;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public int getImage() {
        return image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}