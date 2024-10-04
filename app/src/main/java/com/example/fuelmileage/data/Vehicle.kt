package com.example.fuelmileage.data

data class Vehicle (
    val vehicleID:  Int = 0,
    val vehicleMake: String = "",
    val vehicleModel: String = "",
    val vehicleYear: Int = 0,
    val vehicleTrim:  String = ""

    , val displayName:  String = ""
    //  The original Odomoeter reading will be stored in the first
    //  MileageEntry array record.
)