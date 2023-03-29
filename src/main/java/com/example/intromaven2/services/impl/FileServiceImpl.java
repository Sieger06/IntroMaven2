package com.example.intromaven2.services.impl;

import com.example.intromaven2.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    @Value("${path.to.recipe.file}")
    String FilesPath;
    @Value("${name.to.recipe.file}")
    String recipeFileName;
    @Value("${name.to.ingredient.file}")
    String ingredientFileName;


    // RECIPE ↓
    @Override
    public File getRecipeFile(){
        return new File(FilesPath + "/" + recipeFileName);
    }

    @Override
    public boolean saveRecipeFile(String jason){
        try {
            cleanRecipeFile();
            Files.writeString(Path.of(FilesPath, recipeFileName),jason);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readRecipeFile() {
        try {
            return Files.readString(Path.of(FilesPath, recipeFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanRecipeFile(){
        try {
            Files.deleteIfExists(Path.of(FilesPath, recipeFileName));
            Files.createFile(Path.of(FilesPath, recipeFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    // INGREDIENT ↓
    @Override
    public File getIngredientFile(){
        return new File(FilesPath + "/" + ingredientFileName);
    }

    @Override
    public boolean saveIngredientFile(String jason){
        try {
            cleanIngredientFile();
            Files.writeString(Path.of(FilesPath, ingredientFileName),jason);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readIngredientFile() {
        try {
            return Files.readString(Path.of(FilesPath, ingredientFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanIngredientFile(){
        try {
            Files.deleteIfExists(Path.of(FilesPath, ingredientFileName));
            Files.createFile(Path.of(FilesPath, ingredientFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
