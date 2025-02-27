package com.example.fuelmileage.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fuelmileage.R
import com.example.fuelmileage.data.DataSource
import com.example.fuelmileage.data.Vehicle
import com.example.fuelmileage.ui.theme.FuelMileageTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditVehicleScreen(
    editThisVehicle:  Vehicle?
    , onSaveVehicleClicked: () -> Unit
    , navigateHome: () -> Unit
//    , modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            val screenTitle: String = if (editThisVehicle != null)
                stringResource(id = R.string.editVehicle) +": "+ editThisVehicle.displayName
            else
                stringResource(id = R.string.addVehicle)

            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(screenTitle)
                },
                navigationIcon = {
                    IconButton(onClick = navigateHome ) {
                        Icon(
                            imageVector = Icons.Sharp.Home
//                            , contentDescription = "Navigate Home"
                            , contentDescription = screenTitle

                        )
                    }
                }
            )
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            var vehicleDisplayNameInput by remember {  mutableStateOf(editThisVehicle?.displayName ?: "") }
//            val vehicleDisplayName = vehicleDisplayNameInput.toString() ?: ""

            var vehicleMakeInput by remember { mutableStateOf(editThisVehicle?.vehicleMake ?: "") }
//            val vehicleMake = vehicleMakeInput.toString() ?: ""

            var vehicleModelInput by remember { mutableStateOf(editThisVehicle?.vehicleModel ?: "") }
//            val vehicleModel = vehicleModelInput.toString() ?: ""

            var vehicleYearInput by remember { mutableStateOf(editThisVehicle?.vehicleYear?.toInt() ?: "") }
//            val vehicleYear = vehicleYearInput.toIntOrNull() ?: 0

            var odometerReadingInput by remember { mutableStateOf("") }
//            val odometerReading = odometerReadingInput.toDoubleOrNull() ?: 0.0

            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 20.dp)
                , horizontalAlignment = Alignment.CenterHorizontally
//                , verticalArrangement = Arrangement.Top
            ) {
                EditInputField(
                    value = vehicleDisplayNameInput
                    , label = R.string.displayName
                    , onValueChange = { vehicleDisplayNameInput = it }
                    , keyboardOptions = keyboardText(moveToNext = true)
                    , modifier = Modifier
                        .padding(bottom = 36.dp , top = 18.dp)
                        .fillMaxWidth()
                )
                EditInputField(
                    value = vehicleMakeInput
                    , label = R.string.vehicleMake
                    , onValueChange = { vehicleMakeInput = it }
                    , keyboardOptions = keyboardText(moveToNext = true)
                    , modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                )

                EditInputField(
                    value = vehicleModelInput
                    , label = R.string.vehicleModel
                    , onValueChange = { vehicleModelInput = it }
                    , keyboardOptions =  keyboardText(moveToNext = true)
                    , modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                )

                EditInputField(
                    value = vehicleYearInput.toString()
                    , label = R.string.vehicleYear
                    , onValueChange = { vehicleYearInput = it }
                    , keyboardOptions = keyboardNumeric(moveToNext = true)
                    , modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(40.dp))

                //  Only provide odometer when FIRST adding the vehicle
                if (editThisVehicle == null) {
                    EditInputField(
                        value = odometerReadingInput
                        , label = R.string.odometerReading
                        , onValueChange = { odometerReadingInput = it }
                        , keyboardOptions = keyboardNumeric(moveToNext = false)
                        , modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }

                Button(
                    onClick = { saveVehicle(vehicleMakeInput , vehicleModelInput , vehicleYearInput.toString() , vehicleDisplayNameInput
                        , onSaveVehicleClicked) }
//                    , enabled = allInputProvided(vehicleMakeInput , vehicleModelInput , vehicleYearInput , vehicleYear , vehicleDisplayNameInput)
                    , enabled = allInputProvided(vehicleMakeInput , vehicleModelInput , vehicleYearInput.toString() , vehicleDisplayNameInput)
                ) {
                    Text(text = stringResource(id = R.string.saveButton))
                }
            }
        }
    }
}



private fun saveVehicle(
    vehicleMakeInput: String , vehicleModelInput: String , vehicleYearInput: String , vehicleDisplayNameInput: String
    , navToNextScreen: () -> Unit
) {
    //  Add Vehicle to DataSource
    DataSource.AddVehicle(
        Vehicle(
            vehicleMake = vehicleMakeInput
            , displayName = vehicleDisplayNameInput
            , vehicleModel = vehicleModelInput
            , vehicleYear = vehicleYearInput.toInt()
            )
    )
    navToNextScreen()
}


/**
 *
 */
fun keyboardNumeric(moveToNext: Boolean) :KeyboardOptions  {
    return  KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number
        , imeAction = (if (moveToNext) ImeAction.Next   else  ImeAction.Done) as ImeAction
    )
}
fun keyboardText(moveToNext: Boolean) :KeyboardOptions  {
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



//private fun allInputProvided( make: String, model: String, year: String , yearInt: Int , displayName: String) : Boolean {
//    return(make.isNotEmpty() && model.isNotEmpty() && displayName.isNotEmpty() && year.isNotEmpty() && yearInt > 1900 )
//}
private fun allInputProvided( make: String, model: String, year: String , displayName: String) : Boolean {
    if (year.isNotEmpty() ) {
        if (year.toInt() > 1900)
            return (make.isNotEmpty() && model.isNotEmpty() && displayName.isNotEmpty())
    }
    return false
}



@Preview(
    showBackground = true
    , showSystemUi = true
)
@Composable
fun AddPreview() {
    FuelMileageTheme {
        AddEditVehicleScreen(editThisVehicle = null , {} , {} )
    }
}
@Preview(
    showBackground = true
    , showSystemUi = true
)
@Composable
fun EditPreview() {
    FuelMileageTheme {
        AddEditVehicleScreen(
            Vehicle(
                vehicleID = 0 ,
                vehicleYear = 2023 ,
                vehicleTrim = "LT" ,
                vehicleMake = "Chevy" ,
                vehicleModel = "Silverado 1500" ,
                displayName = "Elvira"
            )
            , {} , {} )
    }
}