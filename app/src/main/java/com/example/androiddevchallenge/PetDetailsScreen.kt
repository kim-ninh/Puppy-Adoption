package com.example.androiddevchallenge

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun PetDetailsScreen(pet: Pet = Pet()){
    Text("This is ${pet.name}")
}