from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI(title="JobPrep AI")


# Aceleasi campuri ca data class-ul Job din Android, ca Gson sa le mapeze direct
class Job(BaseModel):
    id: int
    titlu: str
    companie: str
    locatie: str
    descriere: str


# shortcut: date mock, se inlocuiesc cu Job Board API / PostgreSQL
JOBS = [
    Job(id=1, titlu="AI Software Programmer", companie="TechCorp AI", locatie="Cluj-Napoca / Remote",
        descriere="Dezvoltare și optimizare algoritmi de inteligență artificială. Experiență cu Python și PyTorch necesară."),
    Job(id=2, titlu="Machine Learning Engineer", companie="DataVision", locatie="București",
        descriere="Vei lucra la antrenarea și fine-tuning-ul modelelor de limbaj (LLM) pentru soluții enterprise."),
    Job(id=3, titlu="Prompt Engineer", companie="Creative AI Studio", locatie="Remote",
        descriere="Căutăm un specialist pentru optimizarea prompturilor și testarea riguroasă a modelelor generative."),
]


@app.get("/health")
def health():
    return {"status": "ok"}


@app.get("/jobs")
def jobs() -> list[Job]:
    return JOBS
