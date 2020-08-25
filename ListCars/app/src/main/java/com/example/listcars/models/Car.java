package com.example.listcars.models;

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    private String mark;
    private String model;
    private String price;
}
