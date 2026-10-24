# JobPrep AI - Asistent Inteligent pentru gasirea de Job-uri

## 1. Scenariul Aplicației
Pregătirea pentru un interviu de angajare poate fi stresantă. **JobPrep AI** își propune să simplifice acest proces, acționând ca un asistent personal de carieră direct pe telefon. 

Fluxul este simplu și intuitiv: utilizatorul începe prin a-și completa un scurt profil cu abilitățile, tehnologiile cunoscute și nivelul de experiență. Apoi, poate explora un feed cu locuri de muncă reale, actualizate la zi. Când găsește un anunț interesant, pe lângă citirea detaliilor clasice (locație, cerințe, descriere), are la dispoziție funcția de "Analiză AI Interviu". 

În acel moment, aplicația face o comparație între profilul utilizatorului și cerințele specifice acelui job. Folosind un model de limbaj (LLM), generează instant un ghid personalizat de pregătire: ce întrebări tehnice ar putea primi, cum să își pună în valoare punctele forte și ce aspecte ar trebui să mai repete înainte de discuția cu angajatorul.

## 2. Utilizatori și Roluri
* **Candidatul (Utilizatorul Principal):** Interacționează direct cu interfața grafică. El poate: să își editeze profilul, să caute joburi, să salveze joburile preferate offline și să ceară feedback de la AI.
* **Sistemul / Asistentul AI (Backend logic):** Rulează în fundal pentru a procesa datele. Rolul său este să facă legătura între aplicația client, API-ul de joburi (pentru a furniza date la zi) și API-ul modelului de limbaj (pentru a oferi consultanță simulată de interviu).

## 3. Arhitectura Aplicației
Aplicația va fi dezvoltată nativ în **Android Studio** folosind limbajul **Kotlin**, respectând bunele practici de dezvoltare software recomandate de Google:
* **Arhitectură:** **MVVM (Model - View - ViewModel)** pentru o separare clară între interfața grafică și logica aplicatiei.
* **Asincronism:** Folosirea **Kotlin Coroutines** pentru apelurile de rețea și accesarea bazei de date fără a bloca firul principal (Main Thread).
* **Interfață (UI):** Navigare folosind componenta de *Navigation Graph* și *RecyclerView* pentru randarea eficientă a listelor.
* **Persistența Datelor:** Baza de date locală **Room** pentru salvarea joburilor favorite și *SharedPreferences/DataStore* pentru setările profilului.
* **Networking:** Biblioteca **Retrofit2** împreună cu Gson/Moshi pentru parsarea răspunsurilor JSON de la API-urile externe.

## 4. Funcționalități Implementate
* **Profil Utilizator:** Formular pentru introducerea și salvarea locală a competențelor.
* **Listă de elemente:** Feed cu locuri de muncă (afișând titlu, companie, locație scurtă).
* **Ecran de Detaliu:** Afișarea completă a descrierii unui loc de muncă selectat.
* **Stocare pe dispozitiv:** Sistem de tip "Bookmark" / Favorite pentru accesarea joburilor salvate chiar și fără conexiune la internet.
* **Preluare API extern:** Integrarea unui sistem de căutare live a joburilor.
* **Analiză LLM:** Generarea unui ghid de interviu personalizat prin compararea CV-ului/Profilului cu cerințele jobului.

## 5. API-uri Externe Folosite
1. **Job Board API (Date):** [Adzuna API / Jooble API / Reed.co.uk API] - utilizat pentru a aduce feed-ul de joburi reale în aplicație.
2. **LLM API:** [OpenAI API / Google Gemini API] - utilizat pentru modulul avansat de generare a sfaturilor de interviu.

*Notă de securitate: Cheile API nu sunt incluse în acest repository. Pentru a rula proiectul local, trebuie creat un fișier `local.properties` în rădăcina proiectului cu valorile `JOB_API_KEY="..."` și `LLM_API_KEY="..."`.*

## 6. Plan de Lucru și Termene (Livrabile)
Dezvoltarea va urma o metodologie incrementală, planificată prin GitHub Projects, aliniată cu cerințele de evaluare:
* **[Săptămâna 3]** Crearea repository-ului, alegerea temei, definirea arhitecturii și setarea sarcinilor inițiale.
* **[Săptămâna 6]** Finalizarea interfeței grafice (UI): Ecranul de Profil, Ecranul Listă (RecyclerView) funcțional cu date *mock/dummy* și Ecranul de Detalii.
* **[Săptămâna 9]** Implementarea stocării locale: Integrarea Room Database pentru a salva/șterge/citi joburile favorite de pe dispozitiv.
* **[Săptămâna 11]** Accesare API Extern: Implementarea Retrofit pentru a prelua joburile reale de pe internet în loc de datele statice.
* **[Săptămâna 14]** Prezentarea finală: Finisarea aplicației, adăugarea integrării avansate cu LLM pentru simularea interviului, depanarea (bug-fixing) și susținerea cu suport PPTX.

## 7. Echipa și Responsabilități
**Morovan (Lead Developer / Backend & Arhitectură):**

**Laura (UI/UX Developer & Data Management):**
