package com.example.unitcalculator

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitcalculator.ui.theme.CalculatorActionBgDark
import com.example.unitcalculator.ui.theme.CalculatorActionBgLight
import com.example.unitcalculator.ui.theme.CalculatorNumberBgDark
import com.example.unitcalculator.ui.theme.CalculatorNumberBgLight
import com.example.unitcalculator.ui.theme.CalculatorOperatorBgDark
import com.example.unitcalculator.ui.theme.CalculatorOperatorBgLight
import com.example.unitcalculator.ui.theme.OrangeAccent
import com.example.unitcalculator.ui.theme.UnitCalculatorTheme
import net.objecthunter.exp4j.ExpressionBuilder

@Composable
fun CalculatorScreen() {
    var displayValue by remember { mutableStateOf("0") }
    var lastPressedEquals by remember { mutableStateOf(false) }
    var lastAnswer by remember { mutableStateOf<String?>(null) }
    val operators = listOf("÷", "×", "−", "+")
    val scrollState = rememberScrollState()

    LaunchedEffect(displayValue) {
        scrollState.scrollTo(scrollState.maxValue)
    }

    val buttonGrid = listOf(
        listOf("AC", "\u232b", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "−"),
        listOf("1", "2", "3", "+"),
        listOf("0", "00", ".", "=")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // --- DISPLAY SECTION ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                // Top badge / indicator
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = OrangeAccent.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "CALCULATOR",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = OrangeAccent,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            letterSpacing = 1.sp
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    lastAnswer?.let {
                        Text(
                            text = "Ans = $it",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Text(
                        text = displayValue,
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(scrollState),
                        textAlign = TextAlign.End,
                        maxLines = 1
                    )
                }
            }
        }

        // --- KEYPAD SECTION ---
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            buttonGrid.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    row.forEach { label ->
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            if (label == "=") {
                                val interactionSource = remember { MutableInteractionSource() }
                                val isPressed by interactionSource.collectIsPressedAsState()
                                val scale by animateFloatAsState(
                                    targetValue = if (isPressed) 0.92f else 1.0f,
                                    animationSpec = tween(durationMillis = 100),
                                    label = "equalsScale"
                                )

                                FloatingActionButton(
                                    onClick = {
                                        lastAnswer = displayValue
                                        displayValue = evaluateExpression(displayValue)
                                        lastPressedEquals = true
                                    },
                                    containerColor = OrangeAccent,
                                    contentColor = Color.White,
                                    shape = CircleShape,
                                    interactionSource = interactionSource,
                                    elevation = FloatingActionButtonDefaults.elevation(
                                        defaultElevation = 4.dp,
                                        pressedElevation = 8.dp
                                    ),
                                    modifier = Modifier
                                        .size(68.dp)
                                        .scale(scale)
                                ) {
                                    Text(
                                        text = "=",
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            } else {
                                CalculatorButton(text = label) { clicked ->
                                    displayValue = when (clicked) {
                                        "AC" -> {
                                            lastPressedEquals = false
                                            "0"
                                        }

                                        "\u232b" -> {
                                            lastPressedEquals = false
                                            displayValue.dropLast(1).ifEmpty { "0" }
                                        }

                                        else -> {
                                            if (lastPressedEquals) {
                                                lastPressedEquals = false
                                                if (clicked in operators) {
                                                    lastAnswer = null
                                                    displayValue + clicked
                                                } else {
                                                    if (clicked in listOf(
                                                            "0",
                                                            "00"
                                                        )
                                                    ) "0" else clicked
                                                }
                                            } else {
                                                if (displayValue == "0" && clicked in operators) {
                                                    displayValue
                                                } else if (displayValue.lastOrNull()
                                                        ?.toString() in operators &&
                                                    clicked in operators
                                                ) {
                                                    displayValue.dropLast(1) + clicked
                                                } else {
                                                    if (displayValue == "0") clicked else displayValue + clicked
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    onClick: (String) -> Unit
) {
    val isOperator = text in listOf("÷", "×", "−", "+")
    val isAction = text in listOf("AC", "\u232b", "%")
    val isDark = MaterialTheme.colorScheme.background.red < 0.5f

    val backgroundColor = when {
        isOperator -> if (isDark) CalculatorOperatorBgDark else CalculatorOperatorBgLight
        isAction -> if (isDark) CalculatorActionBgDark else CalculatorActionBgLight
        else -> if (isDark) CalculatorNumberBgDark else CalculatorNumberBgLight
    }

    val textColor = when {
        text == "AC" -> MaterialTheme.colorScheme.error
        isOperator -> OrangeAccent
        isAction -> MaterialTheme.colorScheme.onSurfaceVariant
        else -> MaterialTheme.colorScheme.onSurface
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1.0f,
        animationSpec = tween(durationMillis = 100),
        label = "btnScale"
    )

    Box(
        modifier = Modifier
            .size(68.dp)
            .aspectRatio(1f)
            .scale(scale)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick(text) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = if (isAction) 20.sp else 24.sp,
            fontWeight = if (isOperator || text == "AC") FontWeight.Bold else FontWeight.SemiBold,
            color = textColor
        )
    }
}

fun evaluateExpression(expression: String): String {
    return try {
        var expr = expression

        val percentRegex = Regex("(\\d+(\\.\\d+)?)%")
        expr = percentRegex.replace(expr) { match ->
            val percentValue = match.groupValues[1].toDouble()

            val before = expr.substring(0, match.range.first)
            val numberRegex = Regex("(\\d+(\\.\\d+)?)")
            val numbers = numberRegex.findAll(before).toList()

            if (numbers.isNotEmpty()) {
                val base = numbers.last().value.toDouble()
                (base * percentValue / 100).toString()
            } else {
                (percentValue / 100).toString()
            }
        }

        val cleaned = expr
            .replace("×", "*")
            .replace("÷", "/")
            .replace("−", "-")

        val result = simpleEval(cleaned)

        if (result % 1.0 == 0.0) {
            result.toLong().toString()
        } else {
            "%.8f".format(result).trimEnd('0').trimEnd('.')
        }

    } catch (e: Exception) {
        "Error"
    }
}

fun simpleEval(expr: String): Double =
    ExpressionBuilder(expr).build().evaluate()

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CalculatorScreenPrev() {
    UnitCalculatorTheme(darkTheme = false) {
        CalculatorScreen()
    }
}
