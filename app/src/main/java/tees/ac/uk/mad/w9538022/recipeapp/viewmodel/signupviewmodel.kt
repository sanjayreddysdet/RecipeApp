package tees.ac.uk.mad.w9538022.recipeapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import tees.ac.uk.mad.w9538022.recipeapp.navigation.RecipeAppNav
import tees.ac.uk.mad.w9538022.recipeapp.navigation.Router

class signupviewmodel : ViewModel() {
    private var email = "";
    private var password = "";

    fun setEmail(value: String) {
        email = value
    }

    fun setPassword(value: String) {
        password = value
    }

    fun registerUser()
    {
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Router.MoveTo(RecipeAppNav.recipe)
                }
            }
            .addOnFailureListener {

            }
    }
}