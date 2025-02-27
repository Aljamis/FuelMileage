package com.example.fuelmileage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fuelmileage.data.DataSource
import com.example.fuelmileage.ui.AddEditMileageScreen
import com.example.fuelmileage.ui.AddEditVehicleScreen
import com.example.fuelmileage.ui.MileageViewModel
import com.example.fuelmileage.ui.MileagesHistoryScreen
import com.example.fuelmileage.ui.VehicleSelectionScreen


enum class FuelMileageScreen() {
    SelectVehicle
    , AddEditVehicle
    , MileageHistory
    , AddEditMileage
}




@Composable
fun FuelMileageApp(
    viewModel: MileageViewModel = viewModel()
    , navController:  NavHostController = rememberNavController()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController
            , startDestination = FuelMileageScreen.SelectVehicle.name
            , modifier = Modifier
        ) {
            composable( route = FuelMileageScreen.SelectVehicle.name) {
                VehicleSelectionScreen(
                    savedVehicles = DataSource.vehicles
                    , onSelectVehicleClicked = {
                        viewModel.setVehicle( it )
                        /* TODO  Query vehicle history and populate  */
                        navController.navigate( FuelMileageScreen.MileageHistory.name )
                    }
                    , onAddVehicleClicked = {
                        viewModel.setVehicle( null )
                        navController.navigate( FuelMileageScreen.AddEditVehicle.name)
                    }
                    , onEditVehicle = {
                        viewModel.setVehicle( it )
                        navController.navigate( FuelMileageScreen.AddEditVehicle.name)
                    }
                )
            }
            
            composable( route = FuelMileageScreen.AddEditVehicle.name ) {
                AddEditVehicleScreen(
//                    editThisVehicle = null
                    editThisVehicle = uiState.selectedVehicle
                    , onSaveVehicleClicked = {
//                        navController.navigate( FuelMileageScreen.SelectVehicle.name)
                        navController.popBackStack()
                    }
                    , navigateHome = {
//                        navController.navigate( FuelMileageScreen.SelectVehicle.name)
                        /* TODO  pop or remove of navigation history stack */
                        navController.popBackStack( route = FuelMileageScreen.SelectVehicle.name , inclusive = false , saveState = false)
//                        navController.clearBackStack(route = FuelMileageScreen.SelectVehicle.name)
//                        navController.navigate( FuelMileageScreen.SelectVehicle.name)
//                        navController.navigateUp()
                        /* Can't do this because it violates principles of navigation */
                    }
                )
            }

            composable( route = FuelMileageScreen.MileageHistory.name ) {
                MileagesHistoryScreen(
                    uiState.selectedVehicle
                    , onSelectAddMileage = {
                        viewModel.setMileageHxEntry( null )  //  Clear out any previously selected
                        navController.navigate(FuelMileageScreen.AddEditMileage.name)
                    }
                    , onSelectMileageToEdit = {
                        viewModel.setMileageHxEntry( it )
                        navController.navigate( FuelMileageScreen.AddEditMileage.name)
                    }
                )
            }


            composable( route = FuelMileageScreen.AddEditMileage.name ) {
                AddEditMileageScreen(
//                  editThisMileage = DataSource.GetVehHistory( DataSource.vehicles[0] ) [4]
                    editThisMileage = uiState.selectedMileageEntry
                    , onSaveMileageEntry = {
                        navController.navigate( FuelMileageScreen.MileageHistory.name)
                    }
                )
            }
        }
    }
}