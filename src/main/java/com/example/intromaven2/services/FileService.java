package com.example.intromaven2.services;

import java.io.File;

public interface FileService {
    // RECIPE ↓
    File getRecipeFile();
    boolean saveRecipeFile(String jason);
    String readRecipeFile();

    boolean cleanRecipeFile();

    // INGREDIENT ↓
    File getIngredientFile();
    boolean saveIngredientFile(String jason);
    String readIngredientFile();

    boolean cleanIngredientFile();
}