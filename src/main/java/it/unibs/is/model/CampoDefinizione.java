package it.unibs.is.model;

public class CampoDefinizione {
    private final String nome;
    private boolean obbligatorio;
    private final String tipo; // "BASE", "COMUNE", "SPECIFICO"

    public CampoDefinizione(String nome, boolean obbligatorio, String tipo) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome campo non valido");
        }
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo campo non valido");
        }
        this.nome = nome;
        this.obbligatorio = obbligatorio;
        this.tipo = tipo;
    }

    public String getNome() { return nome; }
    public boolean isObbligatorio() { return obbligatorio; }
    public String getTipo() { return tipo; }

    public void setObbligatorio(boolean obbligatorio) {
        this.obbligatorio = obbligatorio;
    }
}