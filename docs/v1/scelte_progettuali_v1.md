# Scelte progettuali — Versione 1

## Persistenza
Scelta progettuale: memorizzare i dati persistenti su file di testo (formato definito dal gruppo).
Motivazione: il PDF non impone DBMS né una tecnologia di persistenza specifica; la persistenza su file di testo è leggibile e verificabile durante la dimostrazione.

## Formato file schema (V1)
Il file di persistenza utilizza un formato testuale strutturato per righe, con campi separati da ';'.
Ogni riga identifica il tipo di elemento (BASE, COMUNE, CATEGORIA, SPECIFICO) seguito dai dati necessari.
Il formato è pensato per essere leggibile e facilmente modificabile per la dimostrazione in sede d’esame.