package com.example.unitcalculator.otheraccessories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.unitcalculator.CalculatorScreen
import com.example.unitcalculator.otherscreen.SettingsAboutScreen
import com.example.unitcalculator.typeConverter.AreaCalculator
import com.example.unitcalculator.typeConverter.DigitalStorageCalculator
import com.example.unitcalculator.typeConverter.EnergyCalculator
import com.example.unitcalculator.typeConverter.FrequencyCalculator
import com.example.unitcalculator.typeConverter.LengthCalculator
import com.example.unitcalculator.typeConverter.PowerCalculator
import com.example.unitcalculator.typeConverter.SpeedCalculator
import com.example.unitcalculator.typeConverter.TemperatureCalculator
import com.example.unitcalculator.typeConverter.TimeCalculator
import com.example.unitcalculator.typeConverter.VolumeCalculator
import com.example.unitcalculator.typeConverter.WeightCalculator
import com.example.unitcalculator.ui.theme.OrangeAccent
import com.example.unitcalculator.ui.theme.UnitCalculatorTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val title = when (currentRoute) {
        "home" -> "Unit Calculator"
        "LengthCalculator" -> "Length Converter"
        "AreaCalculator" -> "Area Converter"
        "VolumeCalculator" -> "Volume Converter"
        "WeightCalculator" -> "Weight Converter"
        "SpeedCalculator" -> "Speed Converter"
        "TemperatureCalculator" -> "Temperature Converter"
        "PowerCalculator" -> "Power Converter"
        "EnergyCalculator" -> "Energy Converter"
        "FrequencyCalculator" -> "Frequency Converter"
        "DigitalStorageCalculator" -> "Digital Storage Converter"
        "TimeCalculator" -> "Time Converter"
        "SettingsAboutScreen" -> "Settings & About"
        else -> "Unit Calculator"
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    scope.launch { drawerState.close() }
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open()
                                    else drawerState.close()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = OrangeAccent
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        ) { padding ->

            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                // Main Calculator
                composable("home") { CalculatorScreen() }

                // Basic Converters
                composable("LengthCalculator") { LengthCalculator() }
                composable("AreaCalculator") { AreaCalculator() }
                composable("VolumeCalculator") { VolumeCalculator() }
                composable("WeightCalculator") { WeightCalculator() }
                composable("SpeedCalculator") { SpeedCalculator() }
                composable("TemperatureCalculator") { TemperatureCalculator() }

                // Advanced Converters
                composable("PowerCalculator") { PowerCalculator() }
                composable("EnergyCalculator") { EnergyCalculator() }
                composable("FrequencyCalculator") { FrequencyCalculator() }
                composable("DigitalStorageCalculator") { DigitalStorageCalculator() }
                composable("TimeCalculator") { TimeCalculator() }

                // Settings
                composable("SettingsAboutScreen") {
                    SettingsAboutScreen(isDarkTheme, onThemeToggle)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ScreenPreview() {
    UnitCalculatorTheme(darkTheme = false) {
        Screen(
            isDarkTheme = false,
            onThemeToggle = {}
        )
    }
}
