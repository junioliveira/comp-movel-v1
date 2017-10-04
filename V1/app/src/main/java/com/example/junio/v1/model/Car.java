package com.example.junio.v1.model;

/**
 * Created by junio on 03/10/17.
 */

public class Car {
    private int nroChassi;
    private int idCategory;
    private String brand;
    private String model;

    public int getNroChassi() {
        return nroChassi;
    }

    public void setNroChassi(int nroChassi) {
        this.nroChassi = nroChassi;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
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
