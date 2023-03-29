package com.example.intromaven2.controllers;

import com.example.intromaven2.model.Recipe;
import com.example.intromaven2.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Locale;

@Tag(name = "Рецепты", description = "Опции работы с рецептами")
@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @Operation(
            summary = "Добавление рецепта",
            description = "Введите информацию о рецепте, в соответсвии с приведенным ниже примером")
    @PostMapping("/add")
    public String addRecipe (@RequestBody Recipe recipe){
        return "Рецепт успешно добавлен (ID: " + recipeService.addRecipe(recipe) + ")";
    }

    @Operation(
            summary = "Редактирование рецепта",
            description = "1. Введите id рецепта, который Вы хотите отредактировать; 2. Введите новую информацию")
    @PutMapping("/edit/{id}")
    public boolean editRecipe (@PathVariable("id") int id, @RequestBody Recipe recipe){
        return recipeService.editeRecipe(id, recipe);
    }

    @Operation(
            summary = "Удаление рецепта",
            description = "Введите id рецепта, который Вы хотите удалить")
    @Parameters (value = {
            @Parameter (name = "ID", example = "1")
    })
    @DeleteMapping("/remove/{id}")
    public boolean removeRecipe (@PathVariable("id") int id){
        return recipeService.removeRecipe(id);
    }


    @Operation(
            summary = "Получение рецепта по ID",
            description = "Введите id рецепта, который Вы хотите получить")
    @GetMapping("/get/byId/{id}")
    public Recipe getRecipe(@PathVariable int id){
        return recipeService.getRecipe(id);

    }


    @Operation(
            summary = "Получение всех рецептов")
    @GetMapping("/get/all")
    public ArrayList<Recipe> getAllRecipe(){
        return recipeService.getAllRecipe();
    }
}