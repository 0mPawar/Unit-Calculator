package com.example.unitcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.example.unitcalculator.otheraccessories.Screen
import com.example.unitcalculator.otheraccessories.SplashScreen
import com.example.unitcalculator.ui.theme.UnitCalculatorTheme
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var prefs: ThemePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_UnitCalculator)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        prefs = ThemePreferences(applicationContext)

        setContent {
            var showSplash by remember { mutableStateOf(true) }
            val darkFlow = remember { prefs.darkModeFlow.map { it ?: false } }
            val isDarkTheme by darkFlow.collectAsState(initial = false)

            UnitCalculatorTheme(darkTheme = isDarkTheme) {
                if (showSplash) {
                    SplashScreen(
                        onSplashFinished = {
                            showSplash = false
                        }
                    )
                } else {
                    Screen(
                        isDarkTheme = isDarkTheme,
                        onThemeToggle = { enabled ->
                            lifecycleScope.launch { prefs.setDarkMode(enabled) }
                        }
                    )
                }
            }
        }
    }
}
