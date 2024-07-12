package com.firstbit.composeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Medicine(
    @PrimaryKey val id: Int,
    var name: String,
    var dose: String,
    var strength: String
)

data class Medicines(
    @SerializedName("medicines")
    val medicines: List<Medicine>
)