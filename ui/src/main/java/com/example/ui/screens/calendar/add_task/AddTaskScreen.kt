package com.example.ui.screens.calendar.add_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ui.R
import com.example.ui.components.ButtonPrimary
import com.example.ui.components.TextInputField

@Composable
fun AddTaskDialog(
    name: String,
    description: String,
    isButtonEnabled: Boolean,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveTask: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        ) {
            AddTask(
                name = name,
                description = description,
                isButtonEnabled = isButtonEnabled,
                onNameChange = onNameChange,
                onDescriptionChange = onDescriptionChange,
                onSaveTask = onSaveTask
            )
        }
    }
}

@Composable
fun AddTask(
    name: String,
    description: String,
    isButtonEnabled: Boolean,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveTask: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            text = name,
            placeholderTextResId = R.string.task_name,
            onTextChanged = onNameChange,
        )

        TextInputField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(102.dp),
            text = description,
            placeholderTextResId = R.string.task_description,
            onTextChanged = onDescriptionChange,
        )

        ButtonPrimary(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSaveTask,
            enabled = isButtonEnabled,
            textResId = R.string.save,
        )
    }
}
