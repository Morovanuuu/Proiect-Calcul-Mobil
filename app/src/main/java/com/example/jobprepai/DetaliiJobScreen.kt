package com.example.jobprepai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetaliiJobScreen(
    job: Job,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {},
    onAiAnalysisClick: () -> Unit = {}
) {
    // Memoram starea pentru scroll in cazul in care descrierea este lunga
    val scrollState = rememberScrollState()

    Scaffold(
        // Bara de sus cu titlu si butonul de salvare la favorite (bookmark)
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalii Job") },
                actions = {
                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Salveaza jobul"
                        )
                    }
                }
            )
        },
        // Butonul fix de jos pentru Analiza AI (functionalitatea principala a aplicatiei)
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = onAiAnalysisClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "✨ Analiza AI Interviu",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        // Continutul derulabil al ecranului
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sectiunea 1: Titlul jobului si compania
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = job.titlu,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Work,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = job.companie,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Sectiunea 2: Card pentru locatie sau detalii rapide
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Locatie / Remote",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Divider(color = MaterialTheme.colorScheme.outlineVariant)

            // Sectiunea 3: Descrierea detaliata a postului
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Descrierea pozitiei",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = job.descriere,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp
                )
            }

            // Spatiu suplimentar jos ca textul sa nu fie acoperit de butonul fix
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}