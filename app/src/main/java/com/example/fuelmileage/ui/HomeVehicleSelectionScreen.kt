package com.example.fuelmileage.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
import com.example.fuelmileage.R
import com.example.fuelmileage.data.DataSource
import com.example.fuelmileage.data.Vehicle
import com.example.fuelmileage.data.VehicleHistory
import com.example.fuelmileage.ui.theme.FuelMileageTheme


@Composable
fun VehicleSelectionScreen(
    savedVehicles: List<Vehicle>
//    , onSelectVehicleClicked: (Int) -> Unit
    , onSelectVehicleClicked: (Vehicle) -> Unit
    , onAddVehicleClicked: () -> Unit
    , modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
        , verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
            , horizontalAlignment = Alignment.CenterHorizontally
            , verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            savedVehicles.forEach { vehicle ->
                SelectVehicleButton(
                    theVehicle = vehicle
                    , onClick = { onSelectVehicleClicked( vehicle )  }
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
            , horizontalAlignment = Alignment.CenterHorizontally
            , verticalArrangement = Arrangement.spacedBy( dimensionResource( R.dimen.padding_medium) )
        ) {
            Button(onClick = onAddVehicleClicked ) {
                Text(stringResource(id = R.string.newVehicle))
            }
        }
    }
}




@Composable
fun SelectVehicleButton(
    theVehicle: Vehicle
    , onClick: () -> Unit
    , modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick
        , modifier = modifier.widthIn(min = 250.dp)
    ) {
        Text( theVehicle.displayName )
    }
}



@Preview(
    showBackground = true
    , showSystemUi = true
)
@Composable
fun VehicleSelectionScreenPreview() {
    FuelMileageTheme {
        VehicleSelectionScreen(
            savedVehicles = DataSource.vehicles
            , onSelectVehicleClicked = {}
            , onAddVehicleClicked = {}
            , modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}