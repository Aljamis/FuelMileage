package com.example.fuelmileage.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fuelmileage.R
import com.example.fuelmileage.data.MileageEntry
import com.example.fuelmileage.data.Vehicle
import com.example.fuelmileage.ui.theme.FuelMileageTheme


@Composable
fun AddEditMileageScreen(
    editThisMileage:  MileageEntry?
    , modifier: Modifier = Modifier
) {
    var screenTitle: String
    var odometerReadingInput by remember { mutableStateOf("") }
    val odometerReading = odometerReadingInput.toDoubleOrNull() ?: 0.0

    var fuelVolumeLoadedInput by remember { mutableStateOf("") }
    val fuelVolumeLoaded = fuelVolumeLoadedInput.toDoubleOrNull() ?: ""

    var totalCostInput by remember { mutableStateOf("") }
    val totalCost = totalCostInput.toDoubleOrNull() ?: ""

    if (editThisMileage != null)
        screenTitle  = stringResource(id = R.string.editMilage)
    else
        screenTitle  = stringResource(id = R.string.addMileage)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
        , horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = screenTitle
            , fontSize = 25.sp
            , textAlign = TextAlign.Center
            , modifier = Modifier
                .padding(bottom = 10.dp , top = 5.dp)
        )

        EditInputField(
            value = odometerReadingInput
            , label = R.string.odometerReading
            , onValueChange = { odometerReadingInput = it }
            , keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
                , imeAction = ImeAction.Done
            )
            , modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        EditInputField(
            value = fuelVolumeLoadedInput
            , label = R.string.fuelLoaded
            , onValueChange = { fuelVolumeLoadedInput = it }
            , keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
                , imeAction = ImeAction.Next
            )
            , modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        EditInputField(
            value = totalCostInput
            , label = R.string.totalCost
            , onValueChange = { totalCostInput = it }
            , keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
                , imeAction = ImeAction.Done
            )
            , modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /*TODO*/ }
//            , enabled = allInputProvided(odometerReadingInput , vehicleYearInput , vehicleMakeInput , vehicleModelInput)
        ) {
            Text(text = stringResource(id = R.string.saveButton))
        }
    }
}






private fun saveVehicle(make: String, model: String, year: Int , trim: String, displayName: String , odometer: Double) {
    Vehicle(
        vehicleMake = make
        , vehicleModel = model
        , vehicleTrim = trim
        , vehicleYear = year
    )
}

private fun allInputProvided(odometer: Double , fuelVolume: Double , totalCost: Double) : Boolean {
    return(odometer > 0.0 && fuelVolume > 0.0 && totalCost > 0.0 )
}



@Preview(
    showBackground = true
    , showSystemUi = true
)
@Composable
fun AddEditMileagePreview() {
    FuelMileageTheme {
        AddEditMileageScreen(editThisMileage = null )
    }
}