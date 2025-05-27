package com.example.cricinfo.ui.register

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DatePickerScreen(
    dateOfBirth: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Date format for displaying the date string
    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    // Show DatePickerDialog when this flag is true
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                // Update calendar with selected date
                calendar.set(year, month, dayOfMonth)
                // Format the date and send back via callback
                onDateSelected(dateFormat.format(calendar.time))
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    OutlinedTextField(
        value = dateOfBirth,
        onValueChange = {},  // No manual input allowed, controlled via DatePicker
        label = { Text("Date of Birth") },
        modifier = modifier
            .fillMaxWidth()
            .clickable { showDatePicker = true },
        enabled = false,  // Disable keyboard input
        readOnly = true
    )
}

