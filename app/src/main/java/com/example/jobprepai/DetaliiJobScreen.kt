package com.example.jobprepai

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetaliiJobScreen(job: Job) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Detalii pentru: ${job.titlu}") // Text temporar
        Text(text = job.companie)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = job.descriere)
    }
}
