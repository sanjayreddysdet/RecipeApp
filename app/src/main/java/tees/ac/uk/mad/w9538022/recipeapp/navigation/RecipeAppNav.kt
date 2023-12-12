package tees.ac.uk.mad.w9538022.recipeapp.navigation

import android.view.Surface

sealed class RecipeAppNav {
    object signup : RecipeAppNav()
    object login : RecipeAppNav()
    object recipe : RecipeAppNav()
}


