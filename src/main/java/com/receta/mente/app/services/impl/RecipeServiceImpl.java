package com.receta.mente.app.services.impl;

import com.receta.mente.app.models.Recipe;
import com.receta.mente.app.models.User;
import com.receta.mente.app.repositories.RecipeRepository;
import com.receta.mente.app.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            return optionalRecipe.get();
        }
        throw new Exception("recipe not found with id: " + id);
    }

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe createdRecipe = new Recipe();
        createdRecipe.setTitle(recipe.getTitle());
        createdRecipe.setImage(recipe.getImage());
        createdRecipe.setDescription(recipe.getDescription());
        createdRecipe.setUser(user);
        createdRecipe.setCreatedAt(LocalDateTime.now());
        return recipeRepository.save(createdRecipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Recipe updatedRecipe = findRecipeById(id);
        if (recipe.getTitle() != null) {
            updatedRecipe.setTitle(recipe.getTitle());
        }
        if (recipe.getImage() != null) {
            updatedRecipe.setImage(recipe.getImage());
        }
        if (recipe.getDescription() != null) {
            updatedRecipe.setDescription(recipe.getDescription());
        }
        return recipeRepository.save(updatedRecipe);
    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
        findRecipeById(id);
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws Exception {
        Recipe recipe = findRecipeById(recipeId);
        if (recipe.getLikes().contains(user.getId())) {
            recipe.getLikes().remove(user.getId());
        } else {
            recipe.getLikes().add(user.getId());
        }
        return null;
    }
}
