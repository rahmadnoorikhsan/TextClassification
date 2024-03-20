package com.rahmadev.mediapipe.textclassification.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.mediapipe.tasks.components.containers.Classifications
import com.rahmadev.mediapipe.textclassification.ui.theme.TextClassificationTheme
import com.rahmadev.mediapipe.textclassification.utils.TextClassificationHelper
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextClassificationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    var inputString by rememberSaveable {
                        mutableStateOf("")
                    }
                    var result by rememberSaveable {
                        mutableStateOf("")
                    }

                    val textClassifier = TextClassificationHelper(
                        context = context,
                        classifierListener = object : TextClassificationHelper.ClassifierListener {
                            override fun onError(error: String) {
                                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                            }

                            override fun onResult(
                                results: List<Classifications>?,
                                inferenceTime: Long,
                            ) {
                                runOnUiThread {
                                    results?.let { it ->
                                        if (it.isNotEmpty() && it[0].categories().isNotEmpty()) {
                                            println(it)
                                            val sortedCategories = it[0].categories()
                                                .sortedByDescending { it?.score() }
                                            val displayResult =
                                                sortedCategories.joinToString("\n") {
                                                    "${it.categoryName()} " + NumberFormat.getPercentInstance()
                                                        .format(it.score()).trim()
                                                }
                                            result = displayResult
                                        }
                                    }
                                }
                            }
                        }
                    )

                    TextClassificationApp(
                        input = inputString,
                        result = result,
                        onInputChange = {
                            inputString = it
                        },
                        onClassify = {
                            textClassifier.classify(inputString)
                        }
                    )
                }
            }
        }
    }
}