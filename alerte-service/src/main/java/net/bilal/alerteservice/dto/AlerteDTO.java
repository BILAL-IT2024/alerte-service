package net.bilal.alerteservice.dto;

import java.time.LocalDate;

public class AlerteDTO {

    private Long id;
    private String reference;
    private String objet;
    private LocalDate dateLimite;
    private long joursRestants;
    private String statut;
    private String das;
    private String etatAlerte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }

    public long getJoursRestants() {
        return joursRestants;
    }

    public void setJoursRestants(long joursRestants) {
        this.joursRestants = joursRestants;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDas() {
        return das;
    }

    public void setDas(String das) {
        this.das = das;
    }

    public String getEtatAlerte() {
        return etatAlerte;
    }

    public void setEtatAlerte(String etatAlerte) {
        this.etatAlerte = etatAlerte;
    }
}