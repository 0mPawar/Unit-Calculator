package com.example.unitcalculator.typeConverter

import androidx.compose.runtime.Composable

// ✅ Length Converter
@Composable
fun LengthCalculator() {
    val units =
        listOf("Meter", "Kilometer", "Centimeter", "Millimeter", "Inch", "Foot", "Yard", "Mile")

    DefaultConverter("Length Converter", units) { value, from, to ->
        val inMeters = when (from) {
            "Meter" -> value
            "Kilometer" -> value * 1000
            "Centimeter" -> value / 100
            "Millimeter" -> value / 1000
            "Inch" -> value * 0.0254
            "Foot" -> value * 0.3048
            "Yard" -> value * 0.9144
            "Mile" -> value * 1609.34
            else -> value
        }
        when (to) {
            "Meter" -> inMeters
            "Kilometer" -> inMeters / 1000
            "Centimeter" -> inMeters * 100
            "Millimeter" -> inMeters * 1000
            "Inch" -> inMeters / 0.0254
            "Foot" -> inMeters / 0.3048
            "Yard" -> inMeters / 0.9144
            "Mile" -> inMeters / 1609.34
            else -> inMeters
        }
    }
}

// ✅ Area Converter
@Composable
fun AreaCalculator() {
    val units = listOf(
        "Square Meter",
        "Square Kilometer",
        "Square Centimeter",
        "Square Millimeter",
        "Hectare",
        "Acre"
    )

    DefaultConverter("Area Converter", units) { value, from, to ->
        val inSqMeters = when (from) {
            "Square Meter" -> value
            "Square Kilometer" -> value * 1_000_000
            "Square Centimeter" -> value / 10_000
            "Square Millimeter" -> value / 1_000_000
            "Hectare" -> value * 10_000
            "Acre" -> value * 4046.86
            else -> value
        }
        when (to) {
            "Square Meter" -> inSqMeters
            "Square Kilometer" -> inSqMeters / 1_000_000
            "Square Centimeter" -> inSqMeters * 10_000
            "Square Millimeter" -> inSqMeters * 1_000_000
            "Hectare" -> inSqMeters / 10_000
            "Acre" -> inSqMeters / 4046.86
            else -> inSqMeters
        }
    }
}

// ✅ Volume Converter
@Composable
fun VolumeCalculator() {
    val units = listOf(
        "Cubic Meter",
        "Liter",
        "Milliliter",
        "Cubic Centimeter",
        "Cubic Inch",
        "Cubic Foot",
        "Gallon"
    )

    DefaultConverter("Volume Converter", units) { value, from, to ->
        val inCubicMeters = when (from) {
            "Cubic Meter" -> value
            "Liter" -> value / 1000
            "Milliliter" -> value / 1_000_000
            "Cubic Centimeter" -> value / 1_000_000
            "Cubic Inch" -> value * 1.6387e-5
            "Cubic Foot" -> value * 0.0283168
            "Gallon" -> value * 0.00378541
            else -> value
        }
        when (to) {
            "Cubic Meter" -> inCubicMeters
            "Liter" -> inCubicMeters * 1000
            "Milliliter" -> inCubicMeters * 1_000_000
            "Cubic Centimeter" -> inCubicMeters * 1_000_000
            "Cubic Inch" -> inCubicMeters / 1.6387e-5
            "Cubic Foot" -> inCubicMeters / 0.0283168
            "Gallon" -> inCubicMeters / 0.00378541
            else -> inCubicMeters
        }
    }
}

// ✅ Weight Converter
@Composable
fun WeightCalculator() {
    val units = listOf("Kilogram", "Gram", "Milligram", "Pound", "Ounce", "Ton")

    DefaultConverter("Weight Converter", units) { value, from, to ->
        val inKg = when (from) {
            "Kilogram" -> value
            "Gram" -> value / 1000
            "Milligram" -> value / 1_000_000
            "Pound" -> value * 0.453592
            "Ounce" -> value * 0.0283495
            "Ton" -> value * 1000
            else -> value
        }
        when (to) {
            "Kilogram" -> inKg
            "Gram" -> inKg * 1000
            "Milligram" -> inKg * 1_000_000
            "Pound" -> inKg / 0.453592
            "Ounce" -> inKg / 0.0283495
            "Ton" -> inKg / 1000
            else -> inKg
        }
    }
}

// ✅ Speed Converter
@Composable
fun SpeedCalculator() {
    val units = listOf("m/s", "km/h", "mph", "knot")

    DefaultConverter("Speed Converter", units) { value, from, to ->
        val inMS = when (from) {
            "m/s" -> value
            "km/h" -> value / 3.6
            "mph" -> value * 0.44704
            "knot" -> value * 0.514444
            else -> value
        }
        when (to) {
            "m/s" -> inMS
            "km/h" -> inMS * 3.6
            "mph" -> inMS / 0.44704
            "knot" -> inMS / 0.514444
            else -> inMS
        }
    }
}

// ✅ Temperature Converter
@Composable
fun TemperatureCalculator() {
    val units = listOf("Celsius", "Fahrenheit", "Kelvin")

    DefaultConverter("Temperature Converter", units) { value, from, to ->
        val inCelsius = when (from) {
            "Celsius" -> value
            "Fahrenheit" -> (value - 32) * 5 / 9
            "Kelvin" -> value - 273.15
            else -> value
        }
        when (to) {
            "Celsius" -> inCelsius
            "Fahrenheit" -> (inCelsius * 9 / 5) + 32
            "Kelvin" -> inCelsius + 273.15
            else -> inCelsius
        }
    }
}

// ✅ Power Converter
@Composable
fun PowerCalculator() {
    val units = listOf("Watt", "Kilowatt", "Horsepower")

    DefaultConverter("Power Converter", units) { value, from, to ->
        val inWatt = when (from) {
            "Watt" -> value
            "Kilowatt" -> value * 1000
            "Horsepower" -> value * 745.7
            else -> value
        }
        when (to) {
            "Watt" -> inWatt
            "Kilowatt" -> inWatt / 1000
            "Horsepower" -> inWatt / 745.7
            else -> inWatt
        }
    }
}

// ✅ Energy Converter
@Composable
fun EnergyCalculator() {
    val units = listOf("Joule", "Kilojoule", "Calorie", "Kilocalorie", "Watt-hour", "Kilowatt-hour")

    DefaultConverter("Energy Converter", units) { value, from, to ->
        val inJoule = when (from) {
            "Joule" -> value
            "Kilojoule" -> value * 1000
            "Calorie" -> value * 4.184
            "Kilocalorie" -> value * 4184
            "Watt-hour" -> value * 3600
            "Kilowatt-hour" -> value * 3.6e6
            else -> value
        }
        when (to) {
            "Joule" -> inJoule
            "Kilojoule" -> inJoule / 1000
            "Calorie" -> inJoule / 4.184
            "Kilocalorie" -> inJoule / 4184
            "Watt-hour" -> inJoule / 3600
            "Kilowatt-hour" -> inJoule / 3.6e6
            else -> inJoule
        }
    }
}

// ✅ Frequency Converter
@Composable
fun FrequencyCalculator() {
    val units = listOf("Hertz", "Kilohertz", "Megahertz", "Gigahertz")

    DefaultConverter("Frequency Converter", units) { value, from, to ->
        val inHz = when (from) {
            "Hertz" -> value
            "Kilohertz" -> value * 1000
            "Megahertz" -> value * 1_000_000
            "Gigahertz" -> value * 1_000_000_000
            else -> value
        }
        when (to) {
            "Hertz" -> inHz
            "Kilohertz" -> inHz / 1000
            "Megahertz" -> inHz / 1_000_000
            "Gigahertz" -> inHz / 1_000_000_000
            else -> inHz
        }
    }
}

// ✅ Digital Storage Converter
@Composable
fun DigitalStorageCalculator() {
    val units = listOf("Bit", "Byte", "Kilobyte", "Megabyte", "Gigabyte", "Terabyte")

    DefaultConverter("Digital Storage Converter", units) { value, from, to ->
        val inBits = when (from) {
            "Bit" -> value
            "Byte" -> value * 8
            "Kilobyte" -> value * 8_000
            "Megabyte" -> value * 8_000_000
            "Gigabyte" -> value * 8_000_000_000
            "Terabyte" -> value * 8_000_000_000_000
            else -> value
        }
        when (to) {
            "Bit" -> inBits
            "Byte" -> inBits / 8
            "Kilobyte" -> inBits / 8_000
            "Megabyte" -> inBits / 8_000_000
            "Gigabyte" -> inBits / 8_000_000_000
            "Terabyte" -> inBits / 8_000_000_000_000
            else -> inBits
        }
    }
}

// ✅ Time Converter
@Composable
fun TimeCalculator() {
    val units = listOf("Second", "Minute", "Hour", "Day")

    DefaultConverter("Time Converter", units) { value, from, to ->
        val inSeconds = when (from) {
            "Second" -> value
            "Minute" -> value * 60
            "Hour" -> value * 3600
            "Day" -> value * 86400
            else -> value
        }
        when (to) {
            "Second" -> inSeconds
            "Minute" -> inSeconds / 60
            "Hour" -> inSeconds / 3600
            "Day" -> inSeconds / 86400
            else -> inSeconds
        }
    }
}