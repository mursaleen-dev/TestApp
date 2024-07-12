package com.firstbit.composeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.firstbit.composeapp.model.Medicine
import com.firstbit.composeapp.repository.MedicineRepository
import com.firstbit.composeapp.viewmodel.MedicineViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MedicineViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()  // Ensures LiveData runs synchronously

    private lateinit var viewModel: MedicineViewModel
    private lateinit var repository: MedicineRepository

    private val dispatcher = StandardTestDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)


    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = mockk(relaxed = true)
        viewModel = MedicineViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchMedicines updates LiveData with data from repository`() = testScope.runTest {
        // Arrange
        val dummyMedicines = listOf(
            Medicine(id = 1, name = "Med1", dose = "Dose1", strength = "Strength1"),
            Medicine(id = 2, name = "Med2", dose = "Dose2", strength = "Strength2")
        )
        coEvery { repository.getAllMedicines() } returns dummyMedicines

        // Act
        viewModel.fetchMedicines()

        // Assert
        val value = viewModel.medicines.getOrAwaitValue()
        assertEquals(dummyMedicines, value)
    }

    @Test
    fun `insertMedicine calls repository insertMedicine`() = testScope.runTest {
        // Arrange
        val medicine = Medicine(id = 1, name = "Med1", dose = "Dose1", strength = "Strength1")

        // Act
        viewModel.insertMedicine(medicine)

        // Assert
        coVerify { repository.insertMedicine(medicine) }  // Verifies that the repository method is called
    }

    @Test
    fun `fetchMedicines handles empty result from repository`() = testScope.runTest {
        // Arrange
        coEvery { repository.getAllMedicines() } returns emptyList()

        // Act
        viewModel.fetchMedicines()

        // Assert
        val value = viewModel.medicines.getOrAwaitValue()
        assertEquals(emptyList<Medicine>(), value)
    }

    @Test
    fun `fetchMedicines calls repository getAllMedicines`() = testScope.runTest {
        // Act
        viewModel.fetchMedicines()

        // Assert
        coVerify { repository.getAllMedicines() }  // Verifies that the repository method is called
    }

    @Test
    fun `insertMedicine does not throw an exception`() = testScope.runTest {
        // Arrange
        val medicine = Medicine(id = 1, name = "Med1", dose = "Dose1", strength = "Strength1")

        // Act and Assert
        try {
            viewModel.insertMedicine(medicine)
        } catch (e: Exception) {
            assert(false) { "Exception should not be thrown" }
        }
    }
}