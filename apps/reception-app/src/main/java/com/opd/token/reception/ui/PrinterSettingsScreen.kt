package com.opd.token.reception.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Print
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opd.token.shared.designsystem.components.PrimaryButton
import com.opd.token.shared.designsystem.components.SecondaryButton
import com.opd.token.shared.designsystem.theme.PrimaryBlue
import com.opd.token.shared.models.PrinterDevice
import com.opd.token.shared.models.PrinterPaperWidth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrinterSettingsScreen(onBack: () -> Unit) {
    var selectedPrinter by remember { mutableStateOf("POS-58 Bluetooth Printer (DC:0D:30:11:02:5A)") }
    var paperWidth by remember { mutableStateOf(PrinterPaperWidth.WIDTH_58MM) }
    var autoPrint by remember { mutableStateOf(true) }
    var testStatus by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ESC/POS Thermal Printer Settings", color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Connected Thermal Printer", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                    Text(selectedPrinter, fontSize = 18.sp, color = PrimaryBlue)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text("Paper Size Configuration")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = { paperWidth = PrinterPaperWidth.WIDTH_58MM },
                    modifier = Modifier.weight(1f),
                    colors = if (paperWidth == PrinterPaperWidth.WIDTH_58MM) ButtonDefaults.outlinedButtonColors(containerColor = PrimaryBlue.copy(alpha = 0.1f)) else ButtonDefaults.outlinedButtonColors()
                ) {
                    Text("58mm (32 Chars)")
                }
                OutlinedButton(
                    onClick = { paperWidth = PrinterPaperWidth.WIDTH_80MM },
                    modifier = Modifier.weight(1f),
                    colors = if (paperWidth == PrinterPaperWidth.WIDTH_80MM) ButtonDefaults.outlinedButtonColors(containerColor = PrimaryBlue.copy(alpha = 0.1f)) else ButtonDefaults.outlinedButtonColors()
                ) {
                    Text("80mm (48 Chars)")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Auto-Print on Token Generation")
                Switch(checked = autoPrint, onCheckedChange = { autoPrint = it })
            }
            Spacer(modifier = Modifier.height(24.dp))

            testStatus?.let {
                Text(text = it, color = PrimaryBlue)
                Spacer(modifier = Modifier.height(16.dp))
            }

            PrimaryButton(
                text = "Send Test Receipt Print",
                onClick = {
                    testStatus = "Sent ESC/POS test receipt to $selectedPrinter (${paperWidth.name})"
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
