package tees.ac.uk.mad.w9538022.recipeapp.services

import retrofit2.http.GET
import retrofit2.http.Query
import tees.ac.uk.mad.w9538022.recipeapp.model.RecipeModel

interface recipeApi {

 @GET("random.php")
 suspend fun getRandomRecipe(): RecipeModel

 @GET("search.php")
 suspend fun searchRecipe(@Query("s") searchQuery: String): RecipeModel
 }