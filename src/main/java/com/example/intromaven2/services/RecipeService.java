package com.example.intromaven2.services;

import com.example.intromaven2.model.Recipe;

import java.util.ArrayList;

public interface RecipeService {
    int addRecipe (Recipe recipe);

    boolean editeRecipe(int id, Recipe recipe);

    boolean removeRecipe(int id);

    Recipe getRecipe (int id);

    ArrayList<Recipe> getAllRecipe();

    void saveToFile();

    void readFromFile();
}
