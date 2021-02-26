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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PetListScreen(
    pets: List<Pet> = listOf(Pet()),
    navigateTo: (Pet) -> Unit
) {
    PetList(pets = pets, navigateTo)
}

@Composable
fun PetItem(
    pet: Pet,
    navigateTo: (Pet) -> Unit
) {

    Row(
        modifier = Modifier
            .height(96.dp)
            .clickable(onClick = { navigateTo(pet) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(
            data = pet.imageUrl,
            null,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(width = 128.dp, height = 80.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )

        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = pet.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = pet.breed,
                style = MaterialTheme.typography.body2
            )
        }

        val genderResId = if (pet.gender == "Male") {
            R.drawable.baseline_male_24
        } else {
            R.drawable.baseline_female_24
        }

        val genderColor = if (pet.gender == "Male") {
            Color.Blue
        } else {
            Color.Red
        }

        Image(
            painter = painterResource(id = genderResId),
            null,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(24.dp),
            colorFilter = ColorFilter.tint(genderColor),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun PetList(pets: List<Pet>, navigateTo: (Pet) -> Unit) {
    LazyColumn {
        items(items = pets) { pet ->
            PetItem(pet = pet, navigateTo)
            Divider(color = Color.Black)
        }
    }
}

@Preview("Pet List", widthDp = 360, heightDp = 640)
@Composable
fun ListPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            PetListScreen(navigateTo = {})
        }
    }
}
