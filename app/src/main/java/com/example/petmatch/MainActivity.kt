package com.example.petmatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petmatch.data.MascotaRepository
import com.example.petmatch.data.local.AppDatabase
import com.example.petmatch.ui.screens.FormScreen
import com.example.petmatch.ui.screens.ListScreen
import com.example.petmatch.ui.viewmodel.MascotaViewModel
import com.example.petmatch.ui.viewmodel.MascotaViewModelFactory
import com.example.petmatch.ui.theme.PetMatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = MascotaRepository(database.mascotaDao())

        setContent {
            PetMatchTheme {
                var currentScreen by remember { mutableStateOf("list") }

                val viewModel: MascotaViewModel = viewModel(
                    factory = MascotaViewModelFactory(repository)
                )

                when (currentScreen) {
                    "list" -> ListScreen(
                        viewModel = viewModel,
                        onNavigateToForm = { currentScreen = "form" }
                    )
                    "form" -> FormScreen(
                        viewModel = viewModel,
                        onNavigateToList = { currentScreen = "list" }
                    )
                }
            }
        }
    }
}
