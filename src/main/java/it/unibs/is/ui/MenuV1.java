package it.unibs.is.ui;

import it.unibs.is.model.CampoDefinizione;
import it.unibs.is.model.Categoria;
import it.unibs.is.model.SchemaIniziative;

import java.util.List;
import java.util.Scanner;

import it.unibs.is.persistence.SchemaFileRepository;

public class MenuV1 {

    private boolean modificheNonSalvate = false;
    private final SchemaIniziative schema;
    private final Scanner scanner;

    private final SchemaFileRepository repo;

    public MenuV1(SchemaIniziative schema, SchemaFileRepository repo) {
        this.schema = schema;
        this.repo = repo;
        this.scanner = new Scanner(System.in);
    }


    public void esegui() {
        boolean continua = true;
        while (continua) {
            stampaMenu();
            int scelta = leggiIntero("Seleziona un'opzione: ");

            switch (scelta) {
                case 1 -> visualizzaCategorieECampi();
                case 2 -> aggiungiCategoria();
                case 3 -> rimuoviCategoria();
                case 4 -> aggiungiCampoComune();
                case 5 -> aggiungiCampoSpecificoACategoria();
                case 6 -> cambiaObbligatorietaCampoComune();
                case 0 -> {
                    if (modificheNonSalvate) {
                        System.out.print("Vuoi salvare le modifiche su file? (S/N): ");
                        String line = scanner.nextLine();
                        if (line != null && line.trim().equalsIgnoreCase("S")) {
                            try {
                                repo.salvaSchema(schema);
                                System.out.println("Salvataggio completato su file.");
                                modificheNonSalvate = false;
                            } catch (Exception e) {
                                System.out.println("Errore durante il salvataggio:");
                                e.printStackTrace();
                        }
                    } else {
                        System.out.println("Modifiche NON salvate.");
                    }
                }
                continua = false;
                System.out.println("Uscita.");
            }
                default -> System.out.println("Opzione non valida.");
            }
        }
    }

    private void stampaMenu() {
        System.out.println("\n--- MENU V1 ---");
        System.out.println("1) Visualizza categorie e campi");
        System.out.println("2) Aggiungi categoria");
        System.out.println("3) Rimuovi categoria");
        System.out.println("4) Aggiungi campo comune");
        System.out.println("5) Aggiungi campo specifico a categoria");
        System.out.println("6) Cambia obbligatorietà di un campo comune");
        System.out.println("0) Esci");
    }

    private int leggiIntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Input non valido, inserire un numero.");
            }
        }
    }

    private String leggiStringaNonVuota(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line != null && !line.trim().isEmpty()) {
                return line.trim();
            }
            System.out.println("Input non valido: non può essere vuoto.");
        }
    }

    private boolean leggiObbligatorieta() {
        while (true) {
            System.out.print("Obbligatorio? (O/N): ");
            String line = scanner.nextLine();
            if (line == null) continue;
            line = line.trim().toUpperCase();
            if (line.equals("O")) return true;
            if (line.equals("N")) return false;
            System.out.println("Input non valido: inserisci O oppure N.");
        }
    }

    private void visualizzaCategorieECampi() {
        System.out.println("\nCampi base:");
        for (CampoDefinizione c : schema.getCampiBase()) {
            System.out.println("- " + c.getNome() + " (" + (c.isObbligatorio() ? "OBBLIGATORIO" : "FACOLTATIVO") + ")");
        }

        System.out.println("\nCampi comuni:");
        for (CampoDefinizione c : schema.getCampiComuni()) {
            System.out.println("- " + c.getNome() + " (" + (c.isObbligatorio() ? "OBBLIGATORIO" : "FACOLTATIVO") + ")");
        }

        System.out.println("\nCategorie e campi specifici:");
        List<Categoria> categorie = schema.getCategorie();
        if (categorie.isEmpty()) {
            System.out.println("(Nessuna categoria presente)");
            return;
        }
        for (Categoria cat : categorie) {
            System.out.println("- Categoria: " + cat.getNome());
            List<CampoDefinizione> spec = cat.getCampiSpecifici();
            if (spec.isEmpty()) {
                System.out.println("  (Nessun campo specifico)");
            } else {
                for (CampoDefinizione c : spec) {
                    System.out.println("  - " + c.getNome() + " (" + (c.isObbligatorio() ? "OBBLIGATORIO" : "FACOLTATIVO") + ")");
                }
            }
        }
    }

    private void aggiungiCategoria() {
        String nome = leggiStringaNonVuota("Nome nuova categoria: ");
        if (schema.esisteCategoria(nome)) {
            System.out.println("Categoria già esistente.");
            return;
        }
        schema.aggiungiCategoria(new Categoria(nome));
        modificheNonSalvate = true;
        System.out.println("Categoria aggiunta.");
    }

    private void rimuoviCategoria() {
        String nome = leggiStringaNonVuota("Nome categoria da rimuovere: ");
        boolean rimossa = schema.rimuoviCategoria(nome);
        System.out.println(rimossa ? "Categoria rimossa." : "Categoria non trovata.");
        if (rimossa) modificheNonSalvate = true;
    }

    private void aggiungiCampoComune() {
        String nome = leggiStringaNonVuota("Nome nuovo campo comune: ");
        if (schema.esisteCampoComune(nome)) {
            System.out.println("Campo comune già esistente.");
            return;
        }
        boolean obbl = leggiObbligatorieta();
        schema.aggiungiCampoComune(new CampoDefinizione(nome, obbl, "COMUNE"));
        System.out.println("Campo comune aggiunto.");
        modificheNonSalvate = true;
    }

    private void aggiungiCampoSpecificoACategoria() {
        String nomeCat = leggiStringaNonVuota("Categoria: ");
        Categoria cat = schema.getCategoria(nomeCat);
        if (cat == null) {
            System.out.println("Categoria non trovata.");
            return;
        }
        String nomeCampo = leggiStringaNonVuota("Nome nuovo campo specifico: ");
        if (cat.esisteCampoSpecifico(nomeCampo)) {
            System.out.println("Campo specifico già presente in questa categoria.");
            return;
        }
        boolean obbl = leggiObbligatorieta();
        cat.aggiungiCampoSpecifico(new CampoDefinizione(nomeCampo, obbl, "SPECIFICO"));
        System.out.println("Campo specifico aggiunto.");
        modificheNonSalvate = true;
    }

    private void cambiaObbligatorietaCampoComune() {
        String nome = leggiStringaNonVuota("Nome campo comune: ");
        CampoDefinizione c = schema.getCampoComune(nome);
        if (c == null) {
            System.out.println("Campo comune non trovato.");
            return;
        }
        c.setObbligatorio(!c.isObbligatorio());
        System.out.println("Ora '" + c.getNome() + "' è " + (c.isObbligatorio() ? "OBBLIGATORIO" : "FACOLTATIVO"));
        modificheNonSalvate = true;
    }

}
