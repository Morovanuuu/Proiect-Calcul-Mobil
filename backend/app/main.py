from fastapi import FastAPI

app = FastAPI(title="JobPrep AI")


@app.get("/health")
def health():
    return {"status": "ok"}