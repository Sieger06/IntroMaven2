package com.example.intromaven2.services.impl;

import com.example.intromaven2.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
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
    @Value("${name.to.recipe.list.file}")
    String recipeList;


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

    @Override
    public boolean cleanRecipeFile(){
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

    @Override
    public boolean cleanIngredientFile(){
        try {
            Files.deleteIfExists(Path.of(FilesPath, ingredientFileName));
            Files.createFile(Path.of(FilesPath, ingredientFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //LAST HOMEWORK↓
    @Override
    public File getRecipeList() throws IOException{
        Files.deleteIfExists(Path.of(FilesPath, recipeList));
        String recipesByLine = Files.readString(Path.of(FilesPath,recipeFileName));
        FileWriter fw = new FileWriter(FilesPath+"/"+recipeList);
        String [] recipesInAssembly = recipesByLine.split(("\"\\d\":"));
        for (String recipes : recipesInAssembly) {
            String s1 = recipes.replace("}","\n")
                    .replaceAll("\\p{Punct}", "")
                    .replaceAll("name", "")
                    .replaceAll("cookingTime", "\nВремя приготовления: ")
                    .replaceAll("ingredients", " минут.\nИнгредиенты: \n")
                    .replaceAll("count0", " - ")
                    .replaceAll("count", " - ")
                    .replaceAll("measureUnit", " ")
                    .replaceAll("steps", "Инструкция приготовления: ")
                    .replaceAll("\\d  ","\n- ");
            fw.write(s1 + "\n");
        }

        fw.close();
        return new File(FilesPath, recipeList);
    }


}
