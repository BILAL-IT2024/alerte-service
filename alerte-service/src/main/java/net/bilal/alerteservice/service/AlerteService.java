package net.bilal.alerteservice.service;

import net.bilal.alerteservice.client.AppelDoffresClient;
import net.bilal.alerteservice.dto.AlerteDTO;
import net.bilal.alerteservice.dto.AppelDoffresDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AlerteService {

    private final AppelDoffresClient appelDoffresClient;

    public AlerteService(AppelDoffresClient appelDoffresClient) {
        this.appelDoffresClient = appelDoffresClient;
    }

    public List<AlerteDTO> getAlertes(String authorization) {

        return appelDoffresClient
                .getAllAppelsOffres(authorization)
                .stream()

                .filter(ao ->
                        ao.getStatut() != null
                                && ao.getStatut().equalsIgnoreCase("EN_COURS")
                )

                .map(this::calculerAlerte)

                .filter(alerte ->
                        alerte.getEtatAlerte().equals("EN_RETARD")
                                || alerte.getEtatAlerte().equals("URGENT")
                )

                .toList();
    }

    private AlerteDTO calculerAlerte(AppelDoffresDTO ao) {

        AlerteDTO alerte = new AlerteDTO();

        alerte.setId(ao.getId());
        alerte.setReference(ao.getReference());
        alerte.setObjet(ao.getObjet());
        alerte.setDateLimite(ao.getDateLimite());
        alerte.setStatut(ao.getStatut());
        alerte.setDas(ao.getDas());

        if (ao.getDateLimite() == null) {
            alerte.setJoursRestants(0);
            alerte.setEtatAlerte("DATE_INDISPONIBLE");
            return alerte;
        }

        LocalDate aujourdHui = LocalDate.now();

        long joursRestants = ChronoUnit.DAYS.between(
                aujourdHui,
                ao.getDateLimite()
        );

        alerte.setJoursRestants(joursRestants);

        if (joursRestants < 0) {
            alerte.setEtatAlerte("EN_RETARD");

        } else if (joursRestants <= 7) {
            alerte.setEtatAlerte("URGENT");

        } else {
            alerte.setEtatAlerte("NORMAL");
        }

        return alerte;
    }
}