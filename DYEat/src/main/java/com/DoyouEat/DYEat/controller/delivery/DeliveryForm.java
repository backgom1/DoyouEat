package com.DoyouEat.DYEat.controller.delivery;


import lombok.Data;


@Data
public class DeliveryForm {

    private String phone;

    private int count;

    private int price;

    private String address;

    private String street;

    private String userName;

    private int status;
}
