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

import android.app.Application
import android.content.res.AssetManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
