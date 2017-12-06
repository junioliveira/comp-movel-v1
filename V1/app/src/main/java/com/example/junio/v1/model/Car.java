package com.example.junio.v1.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by junio on 03/10/17.
 */

public class Car {
    @SerializedName("id")
    private Integer nroChassi;
    @SerializedName("categoria")
    private Category category;
    @SerializedName("marca")
    private String brand;
    @SerializedName("modelo")
    private String model;
    @SerializedName("placa")
    private String board;

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
