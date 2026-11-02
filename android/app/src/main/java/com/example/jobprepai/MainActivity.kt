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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    // Verificam conexiunea cu backend-ul o singura data, la pornire
    var stareBackend by remember { mutableStateOf("Backend: se conectează...") }
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

    LaunchedEffect(poateConecta) {
        if (!poateConecta) return@LaunchedEffect
        stareBackend = try {
            "Backend: ${api.health().status}"
        } catch (e: Exception) {
            "Backend indisponibil: ${e.message}"
        }
    }

    // Butonul de back
    BackHandler(enabled = jobSelectat != null) {
        jobSelectat = null
    }

    if (jobSelectat == null) {
        Column {
            Text(stareBackend, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            ListaJoburiScreen(
                joburi = listaJoburiTest,
                onJobSelectat = { job -> jobSelectat = job }
            )
        }
    } else {
        DetaliiJobScreen(job = jobSelectat!!)
    }
}