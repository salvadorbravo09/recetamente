package com.receta.mente.app.services;

import com.receta.mente.app.models.Recipe;
import com.receta.mente.app.models.User;

import java.util.List;

public interface RecipeService {

    List<Recipe> findAllRecipe();

    Recipe findRecipeById(Long id) throws Exception;

    Recipe createRecipe(Recipe recipe, User user);

    Recipe updateRecipe(Recipe recipe, Long id) throws Exception;

    void deleteRecipe(Long id) throws Exception;

    Recipe likeRecipe(Long recipeId, User user) throws Exception;

}
