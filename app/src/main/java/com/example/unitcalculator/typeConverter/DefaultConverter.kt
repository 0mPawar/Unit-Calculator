package com.example.unitcalculator.typeConverter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitcalculator.BannerAd
import com.example.unitcalculator.ui.theme.OrangeAccent
import com.example.unitcalculator.ui.theme.UnitCalculatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultConverter(
    title: String,
    units: List<String>,
    convert: (Double, String, String) -> Double,
) {
    var fromUnit by remember { mutableStateOf(units.first()) }
    var toUnit by remember { mutableStateOf(units.getOrElse(1) { units.first() }) }

    var fromExpanded by remember { mutableStateOf(value = false) }
    var toExpanded by remember { mutableStateOf(value = false) }

    var inputValue by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    fun performCalculation() {
        val value = inputValue.toDoubleOrNull() ?: 0.0
        val converted = convert(value, fromUnit, toUnit)
        val formatted = if ((converted % 1.0) == 0.0) {
            converted.toLong().toString()
        } else {
            "%.6f".format(converted).trimEnd('0').trimEnd('.')
        }

        result = "$value $fromUnit = $formatted $toUnit"
        keyboardController?.hide()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .padding(bottom = 72.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Header Section
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(OrangeAccent, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Calculate,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Input & Unit Selection Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    // Input Value
                    OutlinedTextField(
                        value = inputValue,
                        onValueChange = { input ->
                            if (input.count { char -> char == '.' } <= 1) {
                                inputValue = input
                            }
                        },
                        label = { Text("Enter Value") },
                        leadingIcon = {
                            Icon(Icons.Default.Edit, contentDescription = null, tint = OrangeAccent)
                        },
                        trailingIcon = {
                            if (inputValue.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        inputValue = ""
                                        result = ""
                                    }
                                ) {
                                    Icon(Icons.Default.Clear, contentDescription = "Clear input")
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { performCalculation() }
                        ),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = OrangeAccent,
                            focusedLabelColor = OrangeAccent
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // From Unit Dropdown
                    ExposedDropdownMenuBox(
                        expanded = fromExpanded,
                        onExpandedChange = { fromExpanded = !fromExpanded }
                    ) {
                        OutlinedTextField(
                            value = fromUnit,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("From Unit") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(fromExpanded)
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = OrangeAccent,
                                focusedLabelColor = OrangeAccent
                            ),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(
                                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                                    enabled = true
                                )
                        )
                        ExposedDropdownMenu(
                            expanded = fromExpanded,
                            onDismissRequest = { fromExpanded = false }
                        ) {
                            units.forEach { unit ->
                                DropdownMenuItem(
                                    text = { Text(unit) },
                                    onClick = {
                                        fromUnit = unit
                                        fromExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Swap Button
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        FilledTonalIconButton(
                            onClick = {
                                val newFrom = toUnit
                                val newTo = fromUnit
                                fromUnit = newFrom
                                toUnit = newTo

                                val value = inputValue.toDoubleOrNull()
                                if (value != null) {
                                    val converted = convert(value, newFrom, newTo)
                                    val formatted = if ((converted % 1.0) == 0.0) {
                                        converted.toLong().toString()
                                    } else {
                                        "%.6f".format(converted).trimEnd('0').trimEnd('.')
                                    }

                                    result = "$value $newFrom = $formatted $newTo"
                                }
                            },
                            colors = IconButtonDefaults.filledTonalIconButtonColors(
                                containerColor = OrangeAccent.copy(alpha = 0.15f),
                                contentColor = OrangeAccent
                            ),
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                Icons.Default.SwapVert,
                                contentDescription = "Swap Units",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // To Unit Dropdown
                    ExposedDropdownMenuBox(
                        expanded = toExpanded,
                        onExpandedChange = { toExpanded = !toExpanded }
                    ) {
                        OutlinedTextField(
                            value = toUnit,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("To Unit") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(toExpanded)
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = OrangeAccent,
                                focusedLabelColor = OrangeAccent
                            ),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(
                                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                                    enabled = true
                                )
                        )
                        ExposedDropdownMenu(
                            expanded = toExpanded,
                            onDismissRequest = { toExpanded = false }
                        ) {
                            units.forEach { unit ->
                                DropdownMenuItem(
                                    text = { Text(unit) },
                                    onClick = {
                                        toUnit = unit
                                        toExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // Calculate Button
            Button(
                onClick = { performCalculation() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = OrangeAccent,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = "Calculate",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Result Display Card
            AnimatedVisibility(
                visible = result.isNotEmpty(),
                enter = fadeIn() + slideInVertically { 40 },
                exit = fadeOut() + slideOutVertically { 40 }
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Result",
                            style = MaterialTheme.typography.labelLarge,
                            color = OrangeAccent,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = result,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        // AdMob Banner Placement
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            BannerAd()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DefaultCalculatorPrev() {
    UnitCalculatorTheme(darkTheme = false) {
        val units = listOf("Meter", "Kilometer", "Centimeter", "Millimeter", "Inch", "Foot")
        DefaultConverter("Length Converter", units) { value, _, _ -> value * 1000 }
    }
}
