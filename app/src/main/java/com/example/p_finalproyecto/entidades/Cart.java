package com.example.p_finalproyecto.entidades;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Producto> shopCart;

    public ArrayList<Producto> getShopCart(){
        return shopCart;
    }

    public void setShopCart(ArrayList<Producto> value){
        this.shopCart = value;
    }
}
