package com.example.intromaven2.controllers;

import com.example.intromaven2.model.Ingredient;
import com.example.intromaven2.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Tag(name = "Ингредиенты", description = "Опции работы с ингредиентами")
@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @Operation(
            summary = "Редактирование ингридиента",
            description = "1. Введите id ингридиента, который Вы хотите отредактировать; 2. Введите новую информацию")
    @PutMapping("/edit/{id}")
    public boolean editIngredient (@PathVariable int id, @RequestBody Ingredient ingredient){
        return ingredientService.editIngredient(id, ingredient);
    }
    @Operation(
            summary = "Удаление ингидиента",
            description = "Введите id ингридиента, который Вы хотите удалить")
    @Parameters(value = {
            @Parameter(name = "ID", example = "1")
    })
    @DeleteMapping("/remove/{id}")
    public boolean removeIngredient (@PathVariable int id){
        return ingredientService.removeIngredient(id);
    }

    @Operation(
            summary = "Получение ингридиента по ID",
            description = "Введите id ингридиента, который Вы хотите получить")
    @GetMapping("/get/{id}")
    public String getIngredient(@PathVariable int id){
        return ingredientService.infoIngredient(id);
    }
    @Operation(
            summary = "Получение всех ингридиентов")
    @GetMapping("/getAll")
    public ArrayList<Ingredient> getAllIngredient(){
        return ingredientService.getAllIngredient();
    }

}