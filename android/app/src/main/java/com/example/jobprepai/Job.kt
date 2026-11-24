package com.example.jobprepai

import androidx.room.Entity
import androidx.room.PrimaryKey

// Aceeasi clasa e folosita si pentru JSON-ul de la backend, si ca tabel Room pentru favorite
@Entity(tableName = "favorite")
data class Job(
    @PrimaryKey val id: Int,
    val titlu: String,
    val companie: String,
    val locatie: String,
    val descriere: String
)
