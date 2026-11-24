package com.example.jobprepai

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

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

    // Joburile vin de la backend (GET /jobs); stare = mesaj de incarcare/eroare, null cand avem lista
    var joburi by remember { mutableStateOf<List<Job>>(emptyList()) }
    var stare by remember { mutableStateOf<String?>("Se încarcă joburile...") }
    var eroare by remember { mutableStateOf(false) }
    var incercare by remember { mutableStateOf(0) } // crescut de butonul "Reîncearcă" ca sa refaca apelul
    var poateConecta by remember { mutableStateOf(false) }

    // Android 17+ cere permisiunea ACCESS_LOCAL_NETWORK pentru 10.0.2.2 / IP-ul PC-ului din retea
    val context = LocalContext.current
    val cerePermisiune = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { poateConecta = true } // daca e refuzata, apelul esueaza si afisam eroarea
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= 37 &&
            context.checkSelfPermission(Manifest.permission.ACCESS_LOCAL_NETWORK) != PackageManager.PERMISSION_GRANTED
        ) {
            cerePermisiune.launch(Manifest.permission.ACCESS_LOCAL_NETWORK)
        } else {
            poateConecta = true
        }
    }

    LaunchedEffect(poateConecta, incercare) {
        if (!poateConecta) return@LaunchedEffect
        stare = "Se încarcă joburile..."
        eroare = false
        try {
            joburi = api.jobs()
            stare = null
        } catch (e: Exception) {
            stare = "Backend indisponibil: ${e.message}"
            eroare = true
        }
    }

    // Favoritele din Room: raman salvate pe dispozitiv dupa repornirea aplicatiei
    val dao = remember { FavoriteDb.get(context).dao() }
    val favorite by remember { dao.toate() }.collectAsState(initial = emptyList())
    var doarFavorite by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Butonul de back
    BackHandler(enabled = jobSelectat != null) {
        jobSelectat = null
    }

    if (jobSelectat == null) {
        Column(modifier = Modifier.statusBarsPadding()) { // ca butoanele sa nu intre sub ceas/bara de stare
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(selected = !doarFavorite, onClick = { doarFavorite = false }, label = { Text("Toate") })
                FilterChip(selected = doarFavorite, onClick = { doarFavorite = true }, label = { Text("Favorite (${favorite.size})") })
            }
            if (!doarFavorite) {
                stare?.let { Text(it, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) }
                if (eroare) {
                    Button(onClick = { incercare++ }, modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text("Reîncearcă")
                    }
                }
            } else if (favorite.isEmpty()) {
                Text("Nu ai salvat niciun job.", modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            }
            ListaJoburiScreen(
                joburi = if (doarFavorite) favorite else joburi,
                onJobSelectat = { job -> jobSelectat = job }
            )
        }
    } else {
        val job = jobSelectat!!
        val esteFavorit = favorite.any { it.id == job.id }
        DetaliiJobScreen(
            job = job,
            isFavorite = esteFavorit,
            onFavoriteClick = {
                scope.launch { if (esteFavorit) dao.sterge(job) else dao.salveaza(job) }
            }
        )
    }
}