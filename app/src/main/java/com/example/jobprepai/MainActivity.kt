package com.example.jobprepai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JobPrepApp()
                }
            }
        }
    }
}

@Composable
fun JobPrepApp() {
    //  Starea pentru a retine job-ul selectat
    var jobSelectat by remember { mutableStateOf<Job?>(null) }

    // Butonul de back
    BackHandler(enabled = jobSelectat != null) {
        jobSelectat = null
    }

    if (jobSelectat == null) {
        ListaJoburiScreen(
            joburi = listaJoburiTest,
            onJobSelectat = { job -> jobSelectat = job }
        )
    } else {
        DetaliiJobScreen(job = jobSelectat!!)
    }
}