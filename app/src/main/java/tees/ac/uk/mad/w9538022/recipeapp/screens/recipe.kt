package tees.ac.uk.mad.w9538022.recipeapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import tees.ac.uk.mad.w9538022.recipeapp.navigation.RecipeAppNav
import tees.ac.uk.mad.w9538022.recipeapp.navigation.Router
import tees.ac.uk.mad.w9538022.recipeapp.ui.theme.Purple40
import tees.ac.uk.mad.w9538022.recipeapp.ui.theme.PurpleGrey80
import tees.ac.uk.mad.w9538022.recipeapp.viewmodel.RecipeViewModel


@Composable
fun recipe(viewModel: RecipeViewModel) {
    var searchText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AppToolBar() }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize())
                {
                    // Fetch a random recipe initially
                    LaunchedEffect(Unit) {
                        isLoading = true
                        viewModel.getRandomRecipe()
                        isLoading = false
                    }
                    // Search Bar
                    SearchBar(
                        searchText = searchText,
                        onSearchTextChanged = { newText -> searchText = newText }
                    )
                    // Search functionality
                    LaunchedEffect(searchText) {
                        if (searchText.isNotEmpty()) {
                            isLoading = true
                            viewModel.searchRecipe(searchText)
                            isLoading = false
                        }
                    }

                    // Display the first meal's details if available
                    viewModel.recipeDetails?.meals?.firstOrNull()?.let { meal ->
                        Image(
                            painter = rememberImagePainter(meal.strMealThumb),
                            contentDescription = "Recipe Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                        )

                        StyledText("Name", normalText = meal.strMeal)
                        Spacer(modifier = Modifier.height(2.dp))
                        StyledText("Category", normalText = meal.strCategory)
                        Spacer(modifier = Modifier.height(2.dp))
                        StyledText("Area", normalText = meal.strArea)
                        Spacer(modifier = Modifier.height(2.dp))
                        StyledText("Instructions", normalText = meal.strInstructions)
                        // Display ingredients and measurements
                        val ingredients = listOf(
                            meal.strIngredient1 to meal.strMeasure1,
                            meal.strIngredient2 to meal.strMeasure2,
                            meal.strIngredient3 to meal.strMeasure3,
                            meal.strIngredient4 to meal.strMeasure4
                        )
                        ScrollableIngredientList(ingredients)
                    }
                }
                // Circular Progress Indicator
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
@Composable
fun ScrollableIngredientList(ingredients: List<Pair<String, String>>) {
    LazyColumn {
        items(ingredients.size) { index ->
            val (ingredient, measure) = ingredients[index]
            if (ingredient.isNotEmpty() && measure.isNotEmpty()) {
                Text("Ingredient: $ingredient, Measure: $measure")
            }
        }
    }
}
@Composable
fun SearchBar(searchText: String, onSearchTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        label = { Text(text = "Search for a Recipe") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AppToolBar() {
    TopAppBar(
        title = { Text(text = "Recipe Finder") },
        actions = {
            IconButton(onClick = { Router.MoveTo(RecipeAppNav.login)}) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = "Logout",
                    tint = Purple40
                )
            }
        },
        backgroundColor = PurpleGrey80
    )
}


@Composable
fun StyledText(boldText: String, normalText: String) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(boldText + " : ")
        }
        append(normalText)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth() // Fill the max width of the parent
            .padding(4.dp) // Add padding around the text
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp) // Add padding around the Text
        )
    }
}
