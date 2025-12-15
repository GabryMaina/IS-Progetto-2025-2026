package it.unibs.is.model;

import java.util.ArrayList;
import java.util.List;

public class SchemaIniziative {

    private boolean campiBaseFissati = false;

    private final List<CampoDefinizione> campiBase = new ArrayList<>();
    private final List<CampoDefinizione> campiComuni = new ArrayList<>();
    private final List<Categoria> categorie = new ArrayList<>();

    public boolean isCampiBaseFissati() {
        return campiBaseFissati;
    }

    public List<CampoDefinizione> getCampiBase() {
        return new ArrayList<>(campiBase);
    }

    public List<CampoDefinizione> getCampiComuni() {
        return new ArrayList<>(campiComuni);
    }

    public List<Categoria> getCategorie() {
        return new ArrayList<>(categorie);
    }

    // V1: definizione campi base una sola volta (immutabili)
    public void definisciCampiBase(List<CampoDefinizione> nuoviCampiBase) {
        if (campiBaseFissati) {
            throw new IllegalStateException("I campi base sono già stati definiti e non sono modificabili");
        }
        if (nuoviCampiBase == null || nuoviCampiBase.isEmpty()) {
            throw new IllegalArgumentException("Lista campi base vuota");
        }
        campiBase.clear();
        campiBase.addAll(nuoviCampiBase);
        campiBaseFissati = true;
    }

    // Supporto al caricamento da file riga-per-riga
    // Scelta progettuale: metodo di supporto per import testuale; il PDF non impone formato.
    public void definisciCampiBaseOnce(CampoDefinizione campoBase) {
        if (campiBaseFissati) return;
        campiBase.add(campoBase);
    }

    public void chiudiDefinizioneCampiBase() {
        if (!campiBase.isEmpty()) {
            campiBaseFissati = true;
        }
    }

    public void aggiungiCampoComune(CampoDefinizione campoComune) {
        if (campoComune == null) throw new IllegalArgumentException("Campo comune nullo");
        campiComuni.add(campoComune);
    }

    public void aggiungiCategoria(Categoria categoria) {
        if (categoria == null) throw new IllegalArgumentException("Categoria nulla");
        categorie.add(categoria);
    }
}