package com.example.jobprepai

data class Job(
    val id: Int,
    val titlu: String,
    val companie: String,
    val locatie: String,
    val descriere: String
)

// Datele de test (Mock Data) axate pe AI
val listaJoburiTest = listOf(
    Job(1, "AI Software Programmer", "TechCorp AI", "Cluj-Napoca / Remote", "Dezvoltare și optimizare algoritmi de inteligență artificială. Experiență cu Python și PyTorch necesară."),
    Job(2, "Machine Learning Engineer", "DataVision", "București", "Vei lucra la antrenarea și fine-tuning-ul modelelor de limbaj (LLM) pentru soluții enterprise."),
    Job(3, "Prompt Engineer", "Creative AI Studio", "Remote", "Căutăm un specialist pentru optimizarea prompturilor și testarea riguroasă a modelelor generative.")
)