package com.firstbit.composeapp.di

import android.app.Application
import androidx.room.Room
import com.firstbit.composeapp.Constants.API_URL
import com.firstbit.composeapp.data.AppDatabase
import com.firstbit.composeapp.data.MedicineDao
import com.firstbit.composeapp.network.ApiService
import com.firstbit.composeapp.repository.MedicineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Application): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "medicine_database"
        ).build()
    }

    @Provides
    fun provideMedicineDao(db: AppDatabase): MedicineDao {
        return db.medicineDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMedicineRepository(
        apiService: ApiService,
        medicineDao: MedicineDao
    ): MedicineRepository {
        return MedicineRepository(apiService, medicineDao)
    }
}