package tees.ac.uk.mad.w9538022.recipeapp.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.lang.ProcessBuilder.Redirect

object Router {

    var current: MutableState<RecipeAppNav> = mutableStateOf(RecipeAppNav.login)
    fun MoveTo(destination : RecipeAppNav){
        current.value = destination
    }
}