package com.example.intromaven2.services.impl;

import com.example.intromaven2.model.Ingredient;
import com.example.intromaven2.model.Recipe;
import com.example.intromaven2.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Integer, Recipe> recipeMap = new HashMap<>();
    private IngredientServiceImpl ingredientService = new IngredientServiceImpl();

    private int countId = 1;

    @Override
    public int addRecipe(Recipe recipe) {
        int id = countId++;
        recipeMap.put(id, recipe);

        for (Ingredient ingredient : recipe.getIngredients()) {
            boolean b = ingredientService.addIngredient(ingredient);
            if (b == false) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return id;
    }


    @Override
    public boolean editeRecipe(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipeMap.get(id);
    }

    @Override
    public ArrayList<Recipe> getAllRecipe() {
        return new ArrayList<>(recipeMap.values());
    }

    @Override
    public void saveToFile() {

    }

    @Override
    public void readFromFile() {

    }
}