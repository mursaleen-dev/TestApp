package com.firstbit.composeapp.ui.components

import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.firstbit.composeapp.model.Medicine
import com.firstbit.composeapp.viewmodel.MedicineViewModel
import com.google.gson.Gson

@Composable
fun HomeScreen(username: String, navController: NavHostController) {
    val viewModel: MedicineViewModel = hiltViewModel()
    val medicinesState = viewModel.medicines.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchMedicines()
    }
    Column(modifier = Modifier.padding(top = 100.dp, start = 20.dp, end = 20.dp)) {
        Text(
            text = "${getGreetingMessage()}, $username!",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        MedicineList(medicines = medicinesState.value) { medicine ->
            val medicineString = Gson().toJson(medicine)
            navController.navigate("medicineDetail/${medicineString}")
            viewModel.insertMedicine(medicine)
        }
    }
}

@Composable
fun MedicineList(medicines: List<Medicine>, onMedicineClick: (Medicine) -> Unit) {
    LazyColumn {
        items(medicines) { medicine ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onMedicineClick(medicine) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Name: ${medicine.name}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Dose: ${medicine.dose}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Strength: ${medicine.strength}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

fun getGreetingMessage(): String {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when {
        currentHour < 12 -> "Good Morning"
        currentHour < 18 -> "Good Afternoon"
        else -> "Good Evening"
    }
}
