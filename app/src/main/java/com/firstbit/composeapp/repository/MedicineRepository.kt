package com.firstbit.composeapp.repository

import com.firstbit.composeapp.data.MedicineDao
import com.firstbit.composeapp.model.Medicine
import com.firstbit.composeapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MedicineRepository @Inject constructor(
    private val apiService: ApiService,
    private val medicineDao: MedicineDao
) {

    suspend fun getAllMedicines(): List<Medicine> = apiService.getMedicines().medicines

    suspend fun insertMedicine(vararg medicines: Medicine) = withContext(Dispatchers.IO) {
        medicineDao.insertAll(*medicines)
    }
}