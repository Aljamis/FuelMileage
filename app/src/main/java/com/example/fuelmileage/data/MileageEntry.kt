package com.example.fuelmileage.data

import java.time.LocalDateTime

/**
 * Data class to store data when fueling up.
 *
 * The first record in the array will be the Original Odometer reading
 * when the [Vehicle] was added to the database.  The gallonsLoaded and
 * costOfFuel will both be zeros
 */
data class MileageEntry(
    val id: Int = 0,
    val odometerReading: Double
    , val dateFueledUp:  LocalDateTime
    , val gallonsLoaded:  Double = 0.0
    , val costOfFuel: Double = 0.0
)
