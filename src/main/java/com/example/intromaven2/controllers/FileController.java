package com.example.intromaven2.controllers;

import com.example.intromaven2.services.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping (value = "/exportRecipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadRecipeFile () throws FileNotFoundException {
        File recipeFile = fileService.getRecipeFile();
        if (recipeFile.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(recipeFile));
            return ResponseEntity.ok().
                    contentType(MediaType.APPLICATION_JSON).
                    contentLength(recipeFile.length()).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"Recipes.json\"").
                    body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping (value = "/exportIngredient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadIngredientFile () throws FileNotFoundException {
        File ingredientFile = fileService.getIngredientFile();
        if (ingredientFile.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(ingredientFile));
            return ResponseEntity.ok().
                    contentType(MediaType.APPLICATION_OCTET_STREAM).
                    contentLength(ingredientFile.length()).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"Ingredient.json\"").
                    body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}