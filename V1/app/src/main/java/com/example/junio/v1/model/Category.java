package com.example.junio.v1.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by junio on 04/10/17.
 */

public class Category {
    @SerializedName("id")
    private int idCategory;
    @SerializedName("descricao")
    private String name;
    @SerializedName("preco")
    private BigDecimal price;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
