package it.unibs.is.persistence;

import it.unibs.is.model.*;

import java.io.*;
import java.util.*;
import java.util.List;

public class SchemaFileRepository {

    private final File file;

    public SchemaFileRepository(String path) {
        this.file = new File(path);
    }

    public SchemaIniziative caricaSchema() throws IOException {
        SchemaIniziative schema = new SchemaIniziative();
        Categoria categoriaCorrente = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parti = line.split(";");
                String tipo = parti[0];

                switch (tipo) {
                    case "BASE":
                        schema.definisciCampiBaseOnce(
                                new CampoDefinizione(parti[1], true, "BASE")
                        );
                        break;

                    case "COMUNE":
                        schema.aggiungiCampoComune(
                                new CampoDefinizione(
                                        parti[1],
                                        parti[2].equals("OBBLIGATORIO"),
                                        "COMUNE"
                                )
                        );
                        break;

                    case "CATEGORIA":
                        categoriaCorrente = new Categoria(parti[1]);
                        schema.aggiungiCategoria(categoriaCorrente);
                        break;

                    case "SPECIFICO":
                        if (categoriaCorrente != null) {
                            categoriaCorrente.aggiungiCampoSpecifico(
                                    new CampoDefinizione(
                                            parti[1],
                                            parti[2].equals("OBBLIGATORIO"),
                                            "SPECIFICO"
                                    )
                            );
                        }
                        break;
                }
            }
        }
            schema.chiudiDefinizioneCampiBase();
        return schema;
    }
        public void salvaSchema(SchemaIniziative schema) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {

            writer.write("# === CAMPI BASE ===\n");
            for (CampoDefinizione c : schema.getCampiBase()) {
                writer.write("BASE;" + c.getNome() + ";" + (c.isObbligatorio() ? "OBBLIGATORIO" : "FACOLTATIVO") + "\n");
            }
            writer.write("\n");

            writer.write("# === CAMPI COMUNI ===\n");
            for (CampoDefinizione c : schema.getCampiComuni()) {
                writer.write("COMUNE;" + c.getNome() + ";" + (c.isObbligatorio() ? "OBBLIGATORIO" : "FACOLTATIVO") + "\n");
            }
            writer.write("\n");

            writer.write("# === CATEGORIE ===\n");
            List<Categoria> categorie = schema.getCategorie();
            for (Categoria cat : categorie) {
                writer.write("CATEGORIA;" + cat.getNome() + "\n");
                for (CampoDefinizione spec : cat.getCampiSpecifici()) {
                    writer.write("SPECIFICO;" + spec.getNome() + ";" + (spec.isObbligatorio() ? "OBBLIGATORIO" : "FACOLTATIVO") + "\n");
                }
                writer.write("\n");
            }
        }
    }
}