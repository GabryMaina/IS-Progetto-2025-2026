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
**Scopo:** Consentire al configuratore di fissare i campi comuni e modificarli in qualsiasi momento, salvando in modo persistente.  
**Precondizioni:** Il configuratore ha effettuato l’accesso al back-end.  
**Postcondizioni:** L’elenco dei campi comuni risulta aggiornato e salvato in forma persistente.  
**Flusso principale:**
1. Il configuratore seleziona la funzione “Gestisci campi comuni”.
2. Il sistema mostra l’elenco corrente dei campi comuni (eventualmente vuoto).
3. Il configuratore aggiunge uno o più campi comuni indicando, per ciascuno, nome e obbligatorietà (obbligatorio/facoltativo).
4. Il configuratore conferma le modifiche.
5. Il sistema salva in modo persistente i campi comuni.

**Alternative / eccezioni:**

A1. Il configuratore modifica campi comuni quando non esiste alcuna categoria → il sistema consente l’operazione.

A2. Il configuratore tenta di creare un campo comune con nome già esistente tra i campi comuni → il sistema richiede un nome univoco.

## UC4 — Gestione categorie (aggiunta)
**Scopo:** Consentire al configuratore di creare/aggiungere categorie e definire per ciascuna eventuali campi specifici, con salvataggio persistente.  
**Precondizioni:** Il configuratore ha effettuato l’accesso al back-end.  
**Postcondizioni:** La nuova categoria (con eventuali campi specifici) è salvata in modo persistente.  
**Flusso principale:**
1. Il configuratore seleziona la funzione “Aggiungi categoria”.
2. Il configuratore inserisce il nome della categoria.
3. Il sistema verifica che il nome della categoria sia univoco.
4. Il configuratore (se necessario) definisce i campi specifici della categoria, indicando per ciascuno nome e obbligatorietà.
5. Il configuratore conferma.
6. Il sistema salva in modo persistente la categoria e i relativi campi specifici.

**Alternative / eccezioni:**

A1. Nome categoria già presente → il sistema richiede un nome diverso.

A2. Un campo specifico della categoria ha nome duplicato rispetto agli altri campi specifici della stessa categoria → il sistema richiede un nome univoco.

## UC5 — Modifica campi comuni e/o specifici (aggiunta/rimozione/obbligatorietà)
**Scopo:** Consentire la modifica ripetuta dell’elenco dei campi comuni e/o dei campi specifici di una categoria mediante aggiunta, rimozione e variazione dell’obbligatorietà.  
**Precondizioni:** Il configuratore ha effettuato l’accesso al back-end.  
**Postcondizioni:** L’elenco dei campi comuni e/o dei campi specifici della categoria selezionata è aggiornato e salvato in forma persistente.  
**Flusso principale:**
1. Il configuratore seleziona “Modifica campi”.
2. Il sistema chiede se modificare campi comuni o campi specifici.
3. Se campi comuni: il sistema mostra l’elenco dei campi comuni.
4. Se campi specifici: il sistema chiede la categoria e mostra i campi specifici di quella categoria.
5. Il configuratore esegue una o più operazioni tra: aggiunta campo, rimozione campo, modifica obbligatorietà.
6. Il configuratore conferma le modifiche.
7. Il sistema salva in modo persistente le modifiche.

**Alternative / eccezioni:**

A1. Tentativo di rimuovere un campo inesistente → il sistema segnala l’errore e non applica la rimozione.

A2. Tentativo di impostare nomi duplicati nell’elenco interessato (comuni o specifici della categoria) → il sistema richiede correzione.

## UC6 — Rimozione categoria
**Scopo:** Consentire al configuratore di rimuovere una categoria esistente (con i relativi campi specifici), rendendola non più disponibile per nuove iniziative.  
**Precondizioni:** Il configuratore ha effettuato l’accesso al back-end; esiste almeno una categoria.  
**Postcondizioni:** La categoria selezionata e i suoi campi specifici risultano rimossi e la modifica è salvata in modo persistente.  
**Flusso principale:**
1. Il configuratore seleziona “Rimuovi categoria”.
2. Il sistema mostra l’elenco delle categorie esistenti.
3. Il configuratore seleziona una categoria.
4. Il configuratore conferma la rimozione.
5. Il sistema rimuove la categoria e i relativi campi specifici.
6. Il sistema salva in modo persistente l’aggiornamento.

**Alternative / eccezioni:**

A1. Non esistono categorie → il sistema segnala che non ci sono categorie da rimuovere.

## UC7 — Visualizzazione categorie e campi
**Scopo:** Consentire al configuratore di visualizzare le categorie presenti e i campi associati (base, comuni, specifici).  
**Precondizioni:** Il configuratore ha effettuato l’accesso al back-end.  
**Postcondizioni:** Nessuna (operazione di sola lettura).  
**Flusso principale:**
1. Il configuratore seleziona “Visualizza categorie e campi”.
2. Il sistema mostra l’elenco dei campi base.
3. Il sistema mostra l’elenco dei campi comuni.
4. Il sistema mostra l’elenco delle categorie e, per ciascuna, i campi specifici.

**Alternative / eccezioni:**

A1. Una o più sezioni sono vuote (nessun campo comune o nessuna categoria) → il sistema visualizza comunque le altre sezioni disponibili. 