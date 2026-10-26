package com.example.jobprepai

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ListaJoburiScreen(
    joburi: List<Job>,
    onJobSelectat: (Job) -> Unit
) {
    // Lista derulabila eficienta pentru randarea joburilor (similar cu RecyclerView)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp) // Spatiu intre carduri
    ) {
        items(joburi) { job ->
            // Fiecare job este afisat intr-un Card modern Material 3
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onJobSelectat(job) }, // Evenimentul de click cand utilizatorul apasa pe un job
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Detaliile jobului (Titlu si Companie)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        // Iconita decorativa pentru job
                        Icon(
                            imageVector = Icons.Default.Work,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = job.titlu,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = job.companie,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    // Sageata din dreapta care indica faptul ca elementul este asezat pentru selectie
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Vezi detalii",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}