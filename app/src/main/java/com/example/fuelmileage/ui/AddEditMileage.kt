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
import com.example.fuelmileage.data.DataSource
import com.example.fuelmileage.data.MileageEntry
import com.example.fuelmileage.data.Vehicle
import com.example.fuelmileage.ui.theme.FuelMileageTheme
import java.time.LocalDateTime


@Composable
fun AddEditMileageScreen(
    editThisMileage:  MileageEntry?
    , onSaveMileageEntry: () -> Unit
    , modifier: Modifier = Modifier
) {
    var screenTitle: String
    var odometerReadingInput by remember { mutableStateOf("") }
    val odometerReading = odometerReadingInput.toDoubleOrNull() ?: 0.0

    var fuelVolumeLoadedInput by remember { mutableStateOf("") }
    val fuelVolumeLoaded = fuelVolumeLoadedInput.toDoubleOrNull() ?: 0.0

    var totalCostInput by remember { mutableStateOf("") }
    val totalCost = totalCostInput.toDoubleOrNull() ?: 0.0

    if (editThisMileage != null) {
        screenTitle  = stringResource(id = R.string.editMilage)
        odometerReadingInput = editThisMileage.odometerReading.toString()
        fuelVolumeLoadedInput = editThisMileage.gallonsLoaded.toString()
        totalCostInput = editThisMileage.costOfFuel.toString()
    }
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
                , imeAction = ImeAction.Next
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
            onClick = { SaveMileageEntry(odometerReading , fuelVolumeLoaded , totalCost
                , onSaveMileageEntry
            ) }
            , enabled = allInputProvided(odometerReading , fuelVolumeLoaded , totalCost)
        ) {
            Text(text = stringResource(id = R.string.saveMileage))
        }
    }
}





private fun allInputProvided(odometer: Double , fuelVolume: Double , totalCost: Double) : Boolean {
    return(odometer > 0.0 && fuelVolume > 0.0 && totalCost > 0.0 )
}

private fun SaveMileageEntry(
    odometer: Double , fuelVolume: Double , totalCost: Double
    , navToNextScreen: () -> Unit
) {
    DataSource.AddMileageEntry(
        MileageEntry(
            odometerReading = odometer
            , dateFueledUp = LocalDateTime.now()
            , gallonsLoaded = fuelVolume
            , costOfFuel = totalCost
        )
    )
    navToNextScreen()
}



@Preview(
    showBackground = true
    , showSystemUi = true
)
@Composable
fun AddMileagePreview() {
    FuelMileageTheme {
        AddEditMileageScreen(editThisMileage = null , {} )
    }
}
@Preview(
    showBackground = true
    , showSystemUi = true
)@Composable
fun EditMileagePreview() {
    FuelMileageTheme {
        AddEditMileageScreen(editThisMileage = DataSource.GetVehHistory( DataSource.vehicles[0] ) [4] , {} )
    }
}