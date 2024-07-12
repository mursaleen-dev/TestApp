package com.firstbit.composeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firstbit.composeapp.model.Medicine
import com.firstbit.composeapp.repository.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(private val medicineRepository: MedicineRepository) :
    ViewModel() {
    private val _medicines = MutableLiveData<List<Medicine>>()
    val medicines: LiveData<List<Medicine>> = _medicines

    fun fetchMedicines() {
        viewModelScope.launch {
            _medicines.value = medicineRepository.getAllMedicines()
        }
    }

    fun insertMedicine(medicine: Medicine) {
        viewModelScope.launch {
            medicineRepository.insertMedicine()
        }
    }

}