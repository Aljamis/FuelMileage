package com.example.fuelmileage.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fuelmileage.R
import com.example.fuelmileage.common.DateTimeToString
import com.example.fuelmileage.data.DataSource
import com.example.fuelmileage.data.MileageEntry
import com.example.fuelmileage.data.Vehicle
import com.example.fuelmileage.data.VehicleHistory
import com.example.fuelmileage.ui.theme.FuelMileageTheme


@Composable
fun MileagesHistoryScreen(
    forThisVehicle:  Vehicle?
    , modifier:  Modifier = Modifier
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
            Text(
                text = "Mileage History "+ (forThisVehicle?.displayName),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 10.dp, top = 5.dp)
            )
        }

        MileageHistoryList(historyList = DataSource.GetVehHistory( forThisVehicle ))
    }
}


@Composable
fun MileageHistoryList(
    historyList: List<MileageEntry>
    , modifier: Modifier = Modifier
) {
    LazyColumn {
        var previousMileage = historyList[0].odometerReading
        var isFirst = true
        items( items = historyList , key = {it.id}) {mileageHx ->
            if (!isFirst) {
                MileageHxItem(hx = mileageHx , previousMileage , modifier )
                previousMileage = mileageHx.odometerReading
            }
            isFirst = false
        }
    }
}

@Composable
private fun MileageHxItem( hx: MileageEntry , prevMileage : Double , modifier: Modifier ) {
    Card(
        modifier = Modifier.padding(start = 10.dp , top=16.dp , bottom = 0.dp , end = 10.dp)
        , elevation = CardDefaults.cardElevation( defaultElevation = 2.dp)
    ) {
        Column( modifier = Modifier.padding(16.dp) ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = DateTimeToString( hx.dateFueledUp )
                    , style = MaterialTheme.typography.titleLarge)
                Text(text = (hx.odometerReading - prevMileage).toString() )
            }
        }
    }
}


@Preview(
    showBackground = true
    , showSystemUi = true
)
@Composable
fun HistoryScreenPreview() {
    FuelMileageTheme {
        MileagesHistoryScreen( forThisVehicle = DataSource.vehicles[0])
    }
}