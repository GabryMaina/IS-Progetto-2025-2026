package it.unibs.is;

import it.unibs.is.model.SchemaIniziative;
import it.unibs.is.persistence.SchemaFileRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("IS-Progetto 2025-2026 - Versione 1");

        try {
            SchemaFileRepository repo = new SchemaFileRepository("data/schema_v1.txt");
            SchemaIniziative schema = repo.caricaSchema();

            System.out.println("Schema caricato correttamente.");
            System.out.println("Categorie presenti: " + schema.getCategorie().size());
            System.out.println("Campi base: " + schema.getCampiBase().size());
            System.out.println("Campi comuni: " + schema.getCampiComuni().size());

        } catch (Exception e) {
            System.out.println("Errore nel caricamento dello schema:");
            e.printStackTrace();
        }
    }
}