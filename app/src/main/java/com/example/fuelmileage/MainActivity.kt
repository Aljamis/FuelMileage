package com.example.fuelmileage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fuelmileage.ui.theme.FuelMileageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FuelMileageTheme {
                FuelMileageApp()
            }
        }
    }
}