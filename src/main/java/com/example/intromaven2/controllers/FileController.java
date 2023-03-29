package com.example.intromaven2.controllers;

import com.example.intromaven2.services.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.json\"").
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
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Ingredient.json\"").
                    body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping (value = "/importRecipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> upLoadRecipeFile(@RequestParam MultipartFile recipeFile){
        fileService.cleanRecipeFile();
        File file = fileService.getRecipeFile();

        try (FileOutputStream fos = new FileOutputStream(file)){
            IOUtils.copy(recipeFile.getInputStream(), fos);
            return  ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping (value = "/importIngredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> upLoadIngredientFile(@RequestParam MultipartFile ingredientFile){
        fileService.cleanIngredientFile();
        File file = fileService.getIngredientFile();

        try (FileOutputStream fos = new FileOutputStream(file)){
            IOUtils.copy(ingredientFile.getInputStream(), fos);
            return  ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}