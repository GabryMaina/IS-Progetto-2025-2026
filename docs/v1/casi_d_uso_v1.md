# Casi d'uso — Versione 1

## Attore
- Configuratore

## UC1 — Primo accesso e impostazione credenziali personali
**Scopo:** Consentire a un configuratore di effettuare il primo accesso usando credenziali predefinite e impostare credenziali personali per gli accessi successivi.  
**Precondizioni:** Il configuratore possiede credenziali predefinite comunicate per la registrazione.  
**Postcondizioni:** Le credenziali personali del configuratore risultano memorizzate in modo persistente; il configuratore può operare sul back-end.  
**Flusso principale:**
1. Il configuratore avvia l’applicazione.
2. Il configuratore inserisce le credenziali predefinite.
3. Il sistema autentica il configuratore come appartenente al gruppo dei configuratori.
4. Il sistema richiede l’impostazione di credenziali personali.
5. Il configuratore inserisce username e password personali.
6. Il sistema verifica che lo username sia univoco tra i configuratori.
7. Il sistema salva in modo persistente le credenziali personali.
8. Il sistema consente l’accesso alle funzionalità del back-end.

**Alternative / eccezioni:**

A1. Credenziali predefinite errate → il sistema nega l’accesso.  
A2. Username personale già in uso da un altro configuratore → il sistema richiede uno username diverso.

## UC2 — Definizione campi base (una sola volta)
**Scopo:** Fissare e salvare in forma persistente l’elenco dei campi base, che deve poi restare immutabile.  
**Precondizioni:** Il configuratore ha effettuato l’accesso al back-end; l’applicazione è al primo avvio (non esiste ancora l’elenco dei campi base).  
**Postcondizioni:** L’elenco dei campi base è salvato in modo persistente e non può essere modificato successivamente.  
**Flusso principale:**
1. Il configuratore seleziona la funzione “Definisci campi base”.
2. Il sistema presenta l’elenco dei campi base richiesto dal progetto.
3. Il configuratore conferma la definizione.
4. Il sistema salva in modo persistente l’elenco dei campi base.
   
**Alternative / eccezioni:**

A1. L’elenco dei campi base è già stato creato in precedenza → il sistema non consente modifiche.

## UC3 — Gestione campi comuni (crea/modifica)
**Scopo:**  
**Precondizioni:**  
**Postcondizioni:**  
**Flusso principale:**  
**Alternative / eccezioni:**  

## UC4 — Gestione categorie (aggiunta)
**Scopo:**  
**Precondizioni:**  
**Postcondizioni:**  
**Flusso principale:**  
**Alternative / eccezioni:**  

## UC5 — Modifica campi comuni e/o specifici (aggiunta/rimozione/obbligatorietà)
**Scopo:**  
**Precondizioni:**  
**Postcondizioni:**  
**Flusso principale:**  
**Alternative / eccezioni:**  

## UC6 — Rimozione categoria
**Scopo:**  
**Precondizioni:**  
**Postcondizioni:**  
**Flusso principale:**  
**Alternative / eccezioni:**  

## UC7 — Visualizzazione categorie e campi
**Scopo:**  
**Precondizioni:**  
**Postcondizioni:**  
**Flusso principale:**  
**Alternative / eccezioni:**  