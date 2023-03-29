package com.example.intromaven2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Recipe {


    String name;
    int cookingTime;
    ArrayList<Ingredient> ingredients;
    ArrayList <String> steps;

}