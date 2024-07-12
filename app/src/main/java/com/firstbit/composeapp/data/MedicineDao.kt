package com.firstbit.composeapp.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.firstbit.composeapp.model.Medicine

@Dao
interface MedicineDao {
    @Query("SELECT * FROM Medicine")
    suspend fun getAll(): List<Medicine>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg medicines: Medicine)
}

@Database(entities = [Medicine::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}