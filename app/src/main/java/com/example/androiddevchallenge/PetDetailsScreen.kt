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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PetDetailsScreen(pet: Pet = Pet()) {

    val infos = listOf("Gender", "Breed").zip(
        listOf(pet.gender, pet.breed)
    )

    Column {
        CoilImage(
            data = pet.imageUrl,
            null,
            modifier = Modifier.height(200.dp),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = pet.name,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                for ((title, value) in infos) {
                    InfoSectionRow(title, value)
                }
            }

            Button(
                onClick = {},
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = "Call Shelter")
            }
        }

        InfoSectionColumn("About", pet.about)
    }
}

@Composable
fun InfoSectionColumn(title: String = "Title", value: String = "Info") {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = value,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun InfoSectionRow(title: String = "Title", value: String = "Info") {
    Row {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = value,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview("Info Section Column")
@Composable
fun InfoSectionColumnPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            InfoSectionColumn()
        }
    }
}

@Preview("Pet Details", widthDp = 360, heightDp = 640)
@Composable
fun PetDetailPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            PetDetailsScreen()
        }
    }
}
