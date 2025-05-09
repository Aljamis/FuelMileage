package com.example.fuelmileage.data

import androidx.compose.runtime.mutableStateListOf
import com.example.fuelmileage.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date


/**
 * Mock data source object to mimic what might be provided by
 * a database .. if it were written yet
 */
object DataSource {
//    val vehicles = mutableListOf(
    val vehicles = mutableStateListOf(
        Vehicle(vehicleID = 1 , vehicleMake = "Chevrolet", vehicleModel = "Silverado"
            , displayName = "The Truck" , vehicleYear = 2021)
        , Vehicle(vehicleID = 2 , vehicleMake = "Toyota", vehicleModel = "Corolla"
            , displayName = "Violas")
        , Vehicle(vehicleID = 3 , vehicleMake = "Toyota", vehicleModel = "Sienna"
            , displayName = "Bus")
    )

    /**
     * Add a new Vehicle to the DataSource
     */
    fun AddVehicle(newVehicle: Vehicle) {
        newVehicle.vehicleID = vehicles.size +1
        this.vehicles.add( newVehicle )
    }


    /**
     * Update and existing Vehicle in the DataSource
     */
    fun EditVehicle(theVehicle: Vehicle) {
        for (vehicle in vehicles) {
            if (vehicle.vehicleID == theVehicle.vehicleID) {
                vehicle.vehicleYear  = theVehicle.vehicleYear
                vehicle.vehicleMake  = theVehicle.vehicleMake
                vehicle.vehicleModel = theVehicle.vehicleModel
                vehicle.displayName  = theVehicle.displayName
                vehicle.vehicleTrim  = theVehicle.vehicleTrim
            }
        }
    }


    val mileageEntryHx = mutableListOf(
        MileageEntry( odometerReading = 12547.0
            , dateFueledUp = LocalDateTime.parse("2024-07-04T09:45:00")
            , id=1)
        , MileageEntry(odometerReading = 12680.0 , dateFueledUp = LocalDateTime.parse("2024-07-06T14:30:00") , id=2)
        , MileageEntry(odometerReading = 12800.0 , dateFueledUp = LocalDateTime.parse("2024-07-09T11:45:00") , id = 3)
        , MileageEntry(odometerReading = 12978.0 , dateFueledUp = LocalDateTime.parse("2024-07-09T16:15:00") , id = 4)
        , MileageEntry(odometerReading = 13075.0 , dateFueledUp = LocalDateTime.parse("2024-07-09T21:23:00") , id = 5 , costOfFuel = 35.7 , gallonsLoaded = 10.0)
    )
    fun AddMileageEntry(newMileageHx: MileageEntry) {
        newMileageHx.id = mileageEntryHx.size
        mileageEntryHx.add( newMileageHx )
    }


// Vehicle with history of mileage fuel ups
    val vehicleHx = VehicleHistory(
        myVehicle = Vehicle(
            vehicleID = 5
            , vehicleMake = "Chevy"
            , vehicleModel = "Silverado 2500"
            , vehicleTrim = "LT"
            , displayName = "La Camionetta"
        )
        , listOf(
            MileageEntry( odometerReading = 12547.0 , dateFueledUp = LocalDateTime.now())
            , MileageEntry(odometerReading = 12680.0 , dateFueledUp = LocalDateTime.now())
            , MileageEntry(odometerReading = 12800.0 , dateFueledUp = LocalDateTime.now())
        )
    )



    fun GetVehHistory(theVehicle:  Vehicle?) : List<MileageEntry> {
        return listOf(
            MileageEntry( odometerReading = 12547.0
                , dateFueledUp = LocalDateTime.parse("2024-07-04T09:45:00")
                , id=1)
            , MileageEntry(odometerReading = 12680.0 , dateFueledUp = LocalDateTime.parse("2024-07-06T14:30:00") , id=2)
            , MileageEntry(odometerReading = 12800.0 , dateFueledUp = LocalDateTime.parse("2024-07-09T11:45:00") , id = 3)
            , MileageEntry(odometerReading = 12978.0 , dateFueledUp = LocalDateTime.parse("2024-07-09T16:15:00") , id = 4)
            , MileageEntry(odometerReading = 13075.0 , dateFueledUp = LocalDateTime.parse("2024-07-09T21:23:00") , id = 5 , costOfFuel = 35.7 , gallonsLoaded = 10.0)
        )
    }
}