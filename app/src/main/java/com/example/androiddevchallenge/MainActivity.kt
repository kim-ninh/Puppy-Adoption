/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme


class MainActivity : AppCompatActivity() {
    private val viewModel: PetViewModel by viewModels<PetViewModel> {
        ViewModelProvider.AndroidViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MainActivityScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainActivityScreen(viewModel: PetViewModel) {
    val pets: List<Pet> by viewModel.pets.observeAsState(listOf())
    val pet: Pet by viewModel.selectedPet.observeAsState(Pet())

    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController, startDestination = "petlist") {
            composable("petlist") {
                PetListScreen(pets) {
                    viewModel.selectPet(it)
                    navController.navigate("petdetails")
                }
            }
            composable("petdetails") { PetDetailsScreen(pet) }
        }
    }
}