import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import tees.ac.uk.mad.w9538022.recipeapp.navigation.RecipeAppNav
import tees.ac.uk.mad.w9538022.recipeapp.navigation.Router
import tees.ac.uk.mad.w9538022.recipeapp.screens.login
import tees.ac.uk.mad.w9538022.recipeapp.screens.recipe
import tees.ac.uk.mad.w9538022.recipeapp.screens.signup
import tees.ac.uk.mad.w9538022.recipeapp.viewmodel.RecipeViewModel

@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = Router.current) { current ->
            when (current.value) {
                is RecipeAppNav.signup -> {
                    signup()
                }

                is RecipeAppNav.login -> {
                    login()
                }

                is RecipeAppNav.recipe -> {
                    var model = RecipeViewModel()
                    recipe(model)
                }
                else -> {}
            }
        }
    }
}