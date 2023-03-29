package com.example.intromaven2.services;

import java.io.File;

public interface FileService {
    // RECIPE ↓
    File getRecipeFile();
    boolean saveRecipeFile(String jason);
    String readRecipeFile();

    // INGREDIENT ↓
    File getIngredientFile();
    boolean saveIngredientFile(String jason);
    String readIngredientFile();
}