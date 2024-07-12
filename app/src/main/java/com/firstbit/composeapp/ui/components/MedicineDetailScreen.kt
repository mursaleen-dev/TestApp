package com.firstbit.composeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.firstbit.composeapp.model.Medicine

@Composable
fun MedicineDetailScreen(medicine: Medicine?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Name: ${medicine?.name}")
        Text(text = "Dose: ${medicine?.dose}")
        Text(text = "Strength: ${medicine?.strength}")
    }
}