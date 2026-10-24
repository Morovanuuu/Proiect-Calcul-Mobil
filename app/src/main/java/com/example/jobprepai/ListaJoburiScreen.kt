package com.example.jobprepai

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListaJoburiScreen(
    joburi: List<Job>,
    onJobSelectat: (Job) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(joburi) { job ->
            Text(
                text = job.titlu,
                modifier = Modifier.clickable { onJobSelectat(job) }
            )
        }
    }
}