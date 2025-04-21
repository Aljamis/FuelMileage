package com.example.fuelmileage.data

data class Vehicle (
    var vehicleID:  Int = 0,
    var vehicleMake: String = "",
    var vehicleModel: String = "",
    var vehicleYear: Int = 0,
    var vehicleTrim:  String = ""

    , var displayName:  String = ""
    //  The original Odomoeter reading will be stored in the first
    //  MileageEntry array record.
)