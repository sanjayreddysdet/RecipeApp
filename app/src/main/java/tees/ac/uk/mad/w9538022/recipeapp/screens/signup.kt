package tees.ac.uk.mad.w9538022.recipeapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tees.ac.uk.mad.w9538022.recipeapp.navigation.RecipeAppNav
import tees.ac.uk.mad.w9538022.recipeapp.navigation.Router
import tees.ac.uk.mad.w9538022.recipeapp.viewmodel.loginviewmodel
import tees.ac.uk.mad.w9538022.recipeapp.viewmodel.signupviewmodel

@Composable
fun signup(model: signupviewmodel = viewModel()){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                heading("Register")
                Spacer(modifier = Modifier.height(20.dp))
                input(
                    value = "Email",
                    onClick = {
                        model.setEmail(it)
                    }
                )
                passwordinput(
                    value = "Enter the Password",
                    onTextSelected = {
                        model.setPassword(it)
                    }
                )
                Spacer(modifier = Modifier.height(40.dp))
                button(
                    value = "Register",
                    onButtonClicked = {
                      model.registerUser()
                    },
                )
                Spacer(modifier = Modifier.height(20.dp))
                textclick(checkLogin = true, onTextSelected = {
                    Router.MoveTo(RecipeAppNav.login)
                })
            }
        }
    }
}