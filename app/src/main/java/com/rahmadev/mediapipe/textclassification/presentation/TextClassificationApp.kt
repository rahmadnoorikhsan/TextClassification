package com.rahmadev.mediapipe.textclassification.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rahmadev.mediapipe.textclassification.R

@Composable
fun TextClassificationApp(
    input: String,
    result: String,
    onInputChange: (String) -> Unit,
    onClassify: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = input,
            onValueChange = onInputChange,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(160.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onClassify, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.classify))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = result, style = MaterialTheme.typography.titleLarge)
    }
}

@Preview
@Composable
private fun TextClassificationPreview() {
    TextClassificationApp(
        input = "Hello World",
        result = "Good",
        onInputChange = {},
        onClassify = {}
    )
}