package it.unibs.is.persistence;

import it.unibs.is.model.*;

import java.io.*;
import java.util.*;

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
}