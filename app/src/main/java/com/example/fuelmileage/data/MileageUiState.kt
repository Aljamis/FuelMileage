package com.example.fuelmileage.data

data class MileageUiState (
    /**  The current selected vehicle */
    val selectedVehicle : Vehicle? = null
    , val selectedMileageEntry : MileageEntry? = null
)