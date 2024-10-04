package com.example.fuelmileage.ui

import androidx.lifecycle.ViewModel
import com.example.fuelmileage.data.MileageUiState
import com.example.fuelmileage.data.Vehicle
import com.example.fuelmileage.data.VehicleHistory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/**
 * [MileageViewModel] holds information about
 *  - current vehicle selected
 *  - History of the current vehicle selected
 */
class MileageViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MileageUiState())
    val uiState: StateFlow<MileageUiState> = _uiState.asStateFlow()

    fun setVehicle(selectedVehicle:  Vehicle) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedVehicle = selectedVehicle
            )
        }
    }
//    fun setVehicleID(vehicleID:  Int) {
//        _uiState.update { currentState ->
//            currentState.copy(
//                selectedVehicleID = vehicleID
//            )
//        }
//    }

}