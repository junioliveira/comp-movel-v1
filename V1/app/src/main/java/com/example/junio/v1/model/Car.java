package com.example.junio.v1.model;

/**
 * Created by junio on 03/10/17.
 */

public class Car {
    private int nroChassi;
    private Category category;
    private String brand;
    private String model;

    public int getNroChassi() {
        return nroChassi;
    }

    public void setNroChassi(int nroChassi) {
        this.nroChassi = nroChassi;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
