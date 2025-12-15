package it.unibs.is.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private final String nome;
    private final List<CampoDefinizione> campiSpecifici = new ArrayList<>();

    public Categoria(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome categoria non valido");
        }
        this.nome = nome;
    }

    public String getNome() { return nome; }

    public List<CampoDefinizione> getCampiSpecifici() {
        return new ArrayList<>(campiSpecifici);
    }

    public void aggiungiCampoSpecifico(CampoDefinizione campo) {
        if (campo == null) throw new IllegalArgumentException("Campo nullo");
        campiSpecifici.add(campo);
    }
}