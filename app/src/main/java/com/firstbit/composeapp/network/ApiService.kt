package com.firstbit.composeapp.network

import com.firstbit.composeapp.Constants.API_URL
import com.firstbit.composeapp.model.Medicine
import com.firstbit.composeapp.model.Medicines
import retrofit2.http.GET

interface ApiService {
    @GET(API_URL)
    suspend fun getMedicines(): Medicines
}
