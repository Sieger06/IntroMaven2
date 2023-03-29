package com.example.intromaven2.services.impl;

import com.example.intromaven2.model.Ingredient;
import com.example.intromaven2.model.Recipe;
import com.example.intromaven2.services.FileService;
import com.example.intromaven2.services.IngredientService;
import com.example.intromaven2.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private IngredientService ingredientService;
    private FileService fileService;

    public RecipeServiceImpl(FileService fileService, IngredientService ingredientService) {
        this.fileService = fileService;
        this.ingredientService = ingredientService;
    }

    private int countId = 1;


    @PostConstruct
    private void init(){
        readFromFile();
    }

    @Override
    public int addRecipe(Recipe recipe) {
        int id = countId++;
        recipeMap.put(id, recipe);

        for (Ingredient ingredient : recipe.getIngredients()) {
            try {
                ingredientService.addIngredient(ingredient);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        saveToFile();
        return id;
    }

    @Override
    public boolean editeRecipe(int id, Recipe recipe){
        if (recipeMap.containsKey(id)){
            recipeMap.put(id, recipe);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeRecipe(int id){
        if (recipeMap.containsKey(id)){
            recipeMap.remove(id);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Recipe getRecipe (int id) {
        return recipeMap.get(id);
    }

    @Override
    public ArrayList<Recipe> getAllRecipe(){
        return new ArrayList<>(recipeMap.values());
    }

    @Override
    public void saveToFile(){
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveRecipeFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readFromFile(){
        String json = fileService.readRecipeFile();
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}