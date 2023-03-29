package com.example.intromaven2.services.impl;

import com.example.intromaven2.model.Ingredient;
import com.example.intromaven2.services.FileService;
import com.example.intromaven2.services.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private Map <Integer, Ingredient> ingredientMap = new HashMap<>();

    private final FileService fileService;

    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    private int countId = 1;
    @PostConstruct
    private void init () {readFromFile();}

    @Override
    public boolean addIngredient(Ingredient ingredient) {
        int id = countId++;
        ingredientMap.put(id, ingredient);
        saveToFile();
        return true;
    }
    @Override
    public boolean removeIngredient(int id){
        if (ingredientMap.containsKey(id)){
            ingredientMap.remove(id);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean editIngredient(int id, Ingredient ingredient){
        if (ingredientMap.containsKey(id)){
            ingredientMap.put(id, ingredient);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String infoIngredient (int id){
        return ingredientMap.get(id).toString();
    }

    @Override
    public ArrayList<Ingredient> getAllIngredient() {
        return new ArrayList<>(ingredientMap.values());
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return ingredientMap.get(id);
    }

    private void saveToFile(){
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            fileService.saveIngredientFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromFile(){
        try {
            String json = fileService.readIngredientFile();
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<Map <Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}