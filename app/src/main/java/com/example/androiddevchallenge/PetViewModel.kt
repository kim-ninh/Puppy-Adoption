package com.example.androiddevchallenge

import android.app.Application
import android.content.res.AssetManager
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch {
            val pets = withContext(Dispatchers.IO) {
                val string = assetsManager.open("pets.json").use {
                    it.reader().readText()
                }
                Json.decodeFromString<List<Pet>>(string)
            }

            _pets.value = pets
        }
    }
}