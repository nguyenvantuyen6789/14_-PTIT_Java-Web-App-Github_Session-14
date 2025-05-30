package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter, toString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private int id;

    private String productName;

    private int price;

}
