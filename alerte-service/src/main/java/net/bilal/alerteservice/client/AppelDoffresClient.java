package net.bilal.alerteservice.client;

import net.bilal.alerteservice.dto.AppelDoffresDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Component
public class AppelDoffresClient {

    private final RestClient restClient;

    public AppelDoffresClient(
            @Value("${backend.appeldoffres.url}") String backendUrl
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(backendUrl)
                .build();
    }

    public List<AppelDoffresDTO> getAllAppelsOffres(
            String authorization
    ) {

        AppelDoffresDTO[] resultat = restClient
                .get()
                .uri("/api/appels-offres")
                .header(
                        HttpHeaders.AUTHORIZATION,
                        authorization
                )
                .retrieve()
                .body(AppelDoffresDTO[].class);

        if (resultat == null) {
            return List.of();
        }

        return Arrays.asList(resultat);
    }
}