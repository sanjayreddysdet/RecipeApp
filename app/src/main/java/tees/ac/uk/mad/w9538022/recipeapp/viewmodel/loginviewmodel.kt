package tees.ac.uk.mad.w9538022.recipeapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import tees.ac.uk.mad.w9538022.recipeapp.navigation.RecipeAppNav
import tees.ac.uk.mad.w9538022.recipeapp.navigation.Router

class loginviewmodel : ViewModel()
{
    // properties

    private var email = "";
    private var password = "";

    // set email
    fun setEmail(value: String) {
        email = value
    }

    // set password
    fun setPassword(value: String) {
        password = value
    }

    fun loginFirebase() {
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) { Router.MoveTo(RecipeAppNav.recipe) }
            }
            .addOnFailureListener {}
    }
}