package com.example.intromaven2.controllers;

import com.example.intromaven2.model.Ingredient;
import com.example.intromaven2.services.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @PutMapping("/edit/{id}")
    public boolean editIngredient (@PathVariable int id, @RequestBody Ingredient ingredient){
        return ingredientService.editIngredient(id, ingredient);
    }
    @DeleteMapping("/remove/{id}")
    public boolean removeIngredient (@PathVariable int id){
        return ingredientService.removeIngredient(id);
    }
    @GetMapping("/get/{id}")
    public String getIngredient(@PathVariable int id){
        return ingredientService.infoIngredient(id);
    }
    @GetMapping("/getAll")
    public ArrayList<Ingredient> getAllIngredient(){
        return ingredientService.getAllIngredient();
    }

}