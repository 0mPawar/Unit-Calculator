package com.example.unitcalculator.otheraccessories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Water
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitcalculator.ui.theme.OrangeAccent
import com.example.unitcalculator.ui.theme.UnitCalculatorTheme

@Composable
fun AppDrawer(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.surface,
        drawerTonalElevation = 3.dp,
        drawerShape = RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp)
        ) {

            // --- HEADER ---
            Surface(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 28.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(OrangeAccent, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Calculate,
                                contentDescription = "App Logo",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                "Unit Calculator",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                "All-in-one converter & math",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // --- SECTIONS ---
            DrawerSectionTitle("Basic Converters")

            DrawerItem(
                Icons.Default.Calculate,
                "Calculator",
                "home",
                currentRoute == "home",
                onNavigate
            )
            DrawerItem(
                Icons.Default.Straighten,
                "Length",
                "LengthCalculator",
                currentRoute == "LengthCalculator",
                onNavigate
            )
            DrawerItem(
                Icons.Default.SquareFoot,
                "Area",
                "AreaCalculator",
                currentRoute == "AreaCalculator",
                onNavigate
            )
            DrawerItem(
                Icons.Default.Water,
                "Volume",
                "VolumeCalculator",
                currentRoute == "VolumeCalculator",
                onNavigate
            )
            DrawerItem(
                Icons.Default.FitnessCenter,
                "Weight",
                "WeightCalculator",
                currentRoute == "WeightCalculator",
                onNavigate
            )
            DrawerItem(
                Icons.Default.Speed,
                "Speed",
                "SpeedCalculator",
                currentRoute == "SpeedCalculator",
                onNavigate
            )
            DrawerItem(
                Icons.Default.Thermostat,
                "Temperature",
                "TemperatureCalculator",
                currentRoute == "TemperatureCalculator",
                onNavigate
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            DrawerSectionTitle("Advanced Converters")

            DrawerItem(
                Icons.Default.ElectricBolt,
                "Power",
                "PowerCalculator",
                currentRoute == "PowerCalculator",
                onNavigate
            )
            DrawerItem(
                Icons.Default.Bolt,
                "Energy",
                "EnergyCalculator",
                currentRoute == "EnergyCalculator",
                onNavigate
            )
            DrawerItem(
                Icons.Default.Waves,
                "Frequency",
                "FrequencyCalculator",
                currentRoute == "FrequencyCalculator",
                onNavigate
            )
            DrawerItem(
                Icons.Default.Storage,
                "Digital Storage",
                "DigitalStorageCalculator",
                currentRoute == "DigitalStorageCalculator",
                onNavigate
            )
            DrawerItem(
                Icons.Default.AccessTime,
                "Time",
                "TimeCalculator",
                currentRoute == "TimeCalculator",
                onNavigate
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            DrawerSectionTitle("Application")

            DrawerItem(
                Icons.Default.Settings,
                "Settings & About",
                "SettingsAboutScreen",
                currentRoute == "SettingsAboutScreen",
                onNavigate
            )
        }
    }
}

@Composable
fun DrawerItem(
    icon: ImageVector,
    label: String,
    route: String,
    selected: Boolean,
    onClick: (String) -> Unit
) {
    NavigationDrawerItem(
        icon = {
            Icon(
                icon,
                contentDescription = label,
                modifier = Modifier.size(22.dp)
            )
        },
        label = {
            Text(
                label,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
            )
        },
        selected = selected,
        onClick = { onClick(route) },
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = OrangeAccent.copy(alpha = 0.15f),
            selectedIconColor = OrangeAccent,
            selectedTextColor = OrangeAccent,
            unselectedContainerColor = Color.Transparent,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unselectedTextColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
    )
}

@Composable
fun DrawerSectionTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 6.dp),
        style = MaterialTheme.typography.labelMedium,
        color = OrangeAccent,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.8.sp
    )
}

@Preview(showBackground = true)
@Composable
fun AppDrawerPreview() {
    UnitCalculatorTheme(darkTheme = false) {
        AppDrawer(currentRoute = "home", onNavigate = {})
    }
}
