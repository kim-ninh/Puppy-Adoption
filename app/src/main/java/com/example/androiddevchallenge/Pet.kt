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

import kotlinx.serialization.Serializable

@Serializable
data class Pet(
    val petId: Int = 0,
    val name: String = "Finn",
    val about: String = "The beagle is a breed of small hound that is similar in appearance " +
        "to the much larger foxhound. The beagle is a scent hound, developed primarily " +
        "for hunting hare (beagling). Possessing a great sense of smell and " +
        "superior tracking instincts, the beagle is the primary breed used as detection dogs " +
        "for prohibited agricultural imports and foodstuffs in quarantine around the world. " +
        "The beagle is intelligent. It is a popular pet due to its size, good temper, and " +
        "a lack of inherited health problems.",
    val gender: String = "Male",
    val breed: String = "Beagle",
    val imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/d/de/Beagle_Upsy.jpg",
)
