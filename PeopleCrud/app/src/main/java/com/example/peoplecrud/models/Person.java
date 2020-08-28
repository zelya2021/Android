package com.example.peoplecrud.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    private String name;
    private String lastName;
    private String position;
}
