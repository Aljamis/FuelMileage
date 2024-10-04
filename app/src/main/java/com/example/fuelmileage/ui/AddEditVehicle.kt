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
import com.example.fuelmileage.data.Vehicle
import com.example.fuelmileage.ui.theme.FuelMileageTheme


@Composable
fun AddEditVehicleScreen(
    editThisVehicle:  Vehicle?
    , onSaveVehicleClicked: () -> Unit
    , modifier: Modifier = Modifier
) {
    var screenTitle: String

    var vehicleDislplayNameInput by remember { mutableStateOf("") }
    val vehicleDislplayName = vehicleDislplayNameInput.toString() ?: ""

    var vehicleMakeInput by remember { mutableStateOf("") }
    val vehicleMake = vehicleMakeInput.toString() ?: ""

    var vehicleModelInput by remember { mutableStateOf("") }
    val vehicleModel = vehicleModelInput.toString() ?: ""

    var vehicleYearInput by remember { mutableStateOf("") }
    val vehicleYear = vehicleYearInput.toIntOrNull() ?: 0

    var odometerReadingInput by remember { mutableStateOf("") }
    val odometerReading = odometerReadingInput.toDoubleOrNull() ?: 0.0

    if (editThisVehicle != null)
        screenTitle  = stringResource(id = R.string.editVehicle)
    else
        screenTitle  = stringResource(id = R.string.addVehicle)

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
            value = vehicleDislplayNameInput
            , label = R.string.displayName
            , onValueChange = { vehicleDislplayNameInput = it }
            , keyboardOptions = KeyboardText(moveToNext = true)
            , modifier = Modifier
                .padding(bottom = 36.dp)
                .fillMaxWidth()
        )

        EditInputField(
            value = vehicleMakeInput
            , label = R.string.vehicleMake
            , onValueChange = { vehicleMakeInput = it }
            , keyboardOptions = KeyboardText(moveToNext = true)
            , modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        EditInputField(
            value = vehicleModelInput
            , label = R.string.vehicleModel
            , onValueChange = { vehicleModelInput = it }
            , keyboardOptions =  KeyboardText(moveToNext = true)
            , modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        EditInputField(
            value = vehicleYearInput
            , label = R.string.vehicleYear
            , onValueChange = { vehicleYearInput = it }
            , keyboardOptions = KeyboardNumeric(moveToNext = true)
            , modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        EditInputField(
            value = odometerReadingInput
            , label = R.string.odometerReading
            , onValueChange = { odometerReadingInput = it }
            , keyboardOptions = KeyboardNumeric(moveToNext = false)
            , modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onSaveVehicleClicked
            , enabled = allInputProvided(vehicleMakeInput , vehicleModelInput , vehicleYearInput , vehicleDislplayNameInput)
        ) {
            Text(text = stringResource(id = R.string.saveButton))
        }
    }
}


/**
 *
 */
fun KeyboardNumeric(moveToNext: Boolean) :KeyboardOptions  {
    return  KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number
        , imeAction = (if (moveToNext) ImeAction.Next   else  ImeAction.Done) as ImeAction
    )
}
fun KeyboardText(moveToNext: Boolean) :KeyboardOptions  {
    return  KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text
        , imeAction = (if (moveToNext) ImeAction.Next   else  ImeAction.Done) as ImeAction
    )
}


@Composable
fun EditInputField(
    value : String
    , @StringRes label : Int
    , keyboardOptions: KeyboardOptions
    , onValueChange :  (String) -> Unit
    , modifier: Modifier
) {
    TextField(
        value = value
        , onValueChange = onValueChange
        , label = { Text(stringResource(id = label)) }
        , singleLine = true
        , keyboardOptions = keyboardOptions
        , modifier = modifier
    )
}



private fun saveVehicle(make: String, model: String, year: Int , displayName: String ) {
    Vehicle(
        vehicleMake = make
        , vehicleModel = model
        , displayName = displayName
        , vehicleYear = year
        ,
    )
}

private fun allInputProvided( make: String, model: String, year: String , displayName: String) : Boolean {
    return(make.isNotEmpty() && model.isNotEmpty() && displayName.isNotEmpty() && year.isNotEmpty() )
}



@Preview(
    showBackground = true
    , showSystemUi = true
)
@Composable
fun AddEditPreview() {
    FuelMileageTheme {
        AddEditVehicleScreen(editThisVehicle = null , {} )
    }
}