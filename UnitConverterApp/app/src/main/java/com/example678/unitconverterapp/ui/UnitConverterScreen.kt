package com.example678.unitconverterapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example678.unitconverterapp.ui.theme.components.InputField
import com.example678.unitconverterapp.ui.theme.components.UnitDropdown
import com.example678.unitconverterapp.viewmodel.UnitConverterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverterScreen(viewModel: UnitConverterViewModel = viewModel()) {
    val input by viewModel.inputValue.collectAsStateWithLifecycle()
    val fromUnit by viewModel.fromUnit.collectAsStateWithLifecycle()
    val toUnit by viewModel.toUnit.collectAsStateWithLifecycle()
    val result by viewModel.result.collectAsStateWithLifecycle()

    FrozenGreenTheme {
        val scheme = MaterialTheme.colorScheme

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Unit Converter") },
                    colors = centerAlignedTopAppBarColors(
                        containerColor = scheme.primaryContainer,
                        titleContentColor = scheme.onPrimaryContainer,
                        navigationIconContentColor = scheme.onPrimaryContainer,
                        actionIconContentColor = scheme.onPrimaryContainer
                    )
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                scheme.primaryContainer.copy(alpha = 0.85f),
                                scheme.surface,
                                scheme.surfaceVariant.copy(alpha = 0.9f)
                            )
                        )
                    )
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // Frosted card with subtle gradient border
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .border(
                            width = 1.dp,
                            brush = Brush.linearGradient(
                                listOf(
                                    scheme.primary.copy(alpha = 0.35f),
                                    scheme.tertiary.copy(alpha = 0.25f),
                                    scheme.secondary.copy(alpha = 0.35f)
                                )
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = scheme.surface.copy(alpha = 0.92f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Input
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = "Value",
                                style = MaterialTheme.typography.labelLarge,
                                color = scheme.primary
                            )
                            InputField(
                                value = input,
                                onValueChange = { viewModel.onValueChange(it) }
                            )
                        }

                        // Unit selectors
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "From",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = scheme.secondary
                                )
                                UnitDropdown(
                                    selectedUnit = fromUnit,
                                    onUnitSelected = { viewModel.onFromUnitChange(it) }
                                )
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "To",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = scheme.secondary
                                )
                                UnitDropdown(
                                    selectedUnit = toUnit,
                                    onUnitSelected = { viewModel.onToUnitChange(it) }
                                )
                            }
                        }

                        // Convert button with mint accent
                        Button(
                            onClick = { viewModel.convert() },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = scheme.primary,
                                contentColor = scheme.onPrimary
                            )
                        ) {
                            Text("Convert")
                        }

                        // Result card with frosty rim
                        OutlinedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.outlinedCardColors(
                                containerColor = scheme.surface.copy(alpha = 0.96f)
                            ),
                            border = BorderStroke(
                                1.dp,
                                Brush.linearGradient(
                                    listOf(
                                        scheme.tertiary.copy(alpha = 0.28f),
                                        scheme.primary.copy(alpha = 0.28f)
                                    )
                                )
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Result",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = scheme.primary
                                )
                                Text(
                                    text = result.ifBlank { "-" },
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = scheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FrozenGreenTheme(content: @Composable () -> Unit) {
    val light = lightColorScheme(
        primary = Color(0xFF006A60),
        onPrimary = Color(0xFFFFFFFF),
        primaryContainer = Color(0xFFB8F5EC),
        onPrimaryContainer = Color(0xFF00201C),

        secondary = Color(0xFF416A64),
        onSecondary = Color(0xFFFFFFFF),
        secondaryContainer = Color(0xFFCDEBE6),
        onSecondaryContainer = Color(0xFF07201C),

        tertiary = Color(0xFF2B6F69),
        onTertiary = Color(0xFFFFFFFF),
        tertiaryContainer = Color(0xFFBCEFEB),
        onTertiaryContainer = Color(0xFF00201E),

        background = Color(0xFFF8FCFB),
        onBackground = Color(0xFF191C1B),
        surface = Color(0xFFF3FBF9),
        onSurface = Color(0xFF121413),

        surfaceVariant = Color(0xFFD7E6E2),
        onSurfaceVariant = Color(0xFF3F4946),
        outline = Color(0xFF6F7976),
        outlineVariant = Color(0xFFBAC6C2),
        inversePrimary = Color(0xFF54DBCA)
    )

    val dark = darkColorScheme(
        primary = Color(0xFF54DBCA),
        onPrimary = Color(0xFF003732),
        primaryContainer = Color(0xFF11524A),
        onPrimaryContainer = Color(0xFFD0FFF7),

        secondary = Color(0xFFB1CCC6),
        onSecondary = Color(0xFF1C3531),
        secondaryContainer = Color(0xFF2F4B46),
        onSecondaryContainer = Color(0xFFCFE8E3),

        tertiary = Color(0xFF96D3CE),
        onTertiary = Color(0xFF003734),
        tertiaryContainer = Color(0xFF134E49),
        onTertiaryContainer = Color(0xFFBCEFEB),

        background = Color(0xFF0E1413),
        onBackground = Color(0xFFDDE5E2),
        surface = Color(0xFF0F1715),
        onSurface = Color(0xFFBFC9C6),

        surfaceVariant = Color(0xFF3F4946),
        onSurfaceVariant = Color(0xFFBEC9C5),
        outline = Color(0xFF88938F),
        outlineVariant = Color(0xFF3F4946),
        inversePrimary = Color(0xFF006A60)
    )

    val isDark = androidx.compose.foundation.isSystemInDarkTheme()
    MaterialTheme(
        colorScheme = if (isDark) dark else light,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}