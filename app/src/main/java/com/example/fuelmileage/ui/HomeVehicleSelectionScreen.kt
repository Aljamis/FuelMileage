package com.example.fuelmileage.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.fuelmileage.common.DateTimeToString
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
    , onEditVehicle: (Vehicle) -> Unit
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
                VehicleItem(
                    theVehicle = vehicle
                    , onEditVehicle
                    , modifier = Modifier.clickable {
                        Log.w("Click Item" , "Select : "+ vehicle.displayName )
                        onSelectVehicleClicked( vehicle )
                    }
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
fun VehicleItem(
    theVehicle: Vehicle
    , onEditVehicle: (Vehicle) -> Unit
    , modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.padding(start = 5.dp, bottom = 0.dp, end = 5.dp),
        elevation = CardDefaults.cardElevation( defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = theVehicle.displayName,
                    style = MaterialTheme.typography.titleLarge
                )
                Icon(imageVector = Icons.Filled.Edit, contentDescription = ""
                    , Modifier.clickable {
                        Log.w("Click Item" , "Delete : "+ theVehicle.displayName )
                        onEditVehicle(theVehicle)
                    })
            }
        }

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
            , onEditVehicle = {}
            , modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}