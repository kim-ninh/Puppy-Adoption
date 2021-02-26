package com.example.androiddevchallenge

import android.app.Application
import android.content.res.AssetManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class PetViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val assetsManager: AssetManager = application.assets

    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> = _pets

    private val _selectedPet = MutableLiveData<Pet>()

    val selectedPet: LiveData<Pet> = _selectedPet

    fun selectPet(pet: Pet) {
        _selectedPet.value = pet
    }

    init {
        _pets.value = listOf(Pet())

        val string = assetsManager.open("pets.json").use {
            it.reader().readText()
        }

        val pets = Json.decodeFromString<List<Pet>>(string)
        _pets.value = pets
    }
}