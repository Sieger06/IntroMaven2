package com.example.intromaven2.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Recipe {

    String name;
    int cookingTime;
    ArrayList<Ingredient> ingredients;
    ArrayList <String> steps;


}