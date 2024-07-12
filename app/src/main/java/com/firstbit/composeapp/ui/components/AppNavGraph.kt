package com.firstbit.composeapp.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.firstbit.composeapp.model.Medicine
import com.google.gson.Gson

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { SignInScreen { username -> navController.navigate("home/$username") } }
        composable("home/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username")
            HomeScreen(username ?: "", navController)
        }
        composable("medicineDetail/{medicine}") { backStackEntry ->
            val jsonString = backStackEntry.arguments?.getString("medicine")
            val medicine = Gson().fromJson(jsonString, Medicine::class.java)
            MedicineDetailScreen(medicine)
        }
    }
}