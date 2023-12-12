package tees.ac.uk.mad.w9538022.recipeapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tees.ac.uk.mad.w9538022.recipeapp.model.RecipeModel
import tees.ac.uk.mad.w9538022.recipeapp.services.recipeApi

class RecipeViewModel(): ViewModel()
{
    private val BaseUrl : String = "https://www.themealdb.com/api/json/v1/1/"
    var recipeDetails by mutableStateOf<RecipeModel?>(null)
    private val api = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(recipeApi::class.java)

    init {
        getRandomRecipe()
    }

    fun getRandomRecipe() {
        viewModelScope.launch {
            recipeDetails = api.getRandomRecipe()
        }
    }

    fun searchRecipe(query: String) {
        viewModelScope.launch {
            recipeDetails = api.searchRecipe(query)
        }
    }
}