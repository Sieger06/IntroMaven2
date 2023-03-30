package com.example.intromaven2.services;

import com.example.intromaven2.model.Ingredient;

import java.io.IOException;
import java.util.ArrayList;

public interface IngredientService {
    boolean addIngredient (Ingredient ingredient) throws IOException;

    boolean removeIngredient(int id);

    boolean editIngredient(int id, Ingredient ingredient);

    String infoIngredient(int id);

    ArrayList<Ingredient> getAllIngredient ();

    Ingredient getIngredientById(int id);
}