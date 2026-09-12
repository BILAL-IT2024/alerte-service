package net.bilal.alerteservice.controller;

import net.bilal.alerteservice.dto.AlerteDTO;
import net.bilal.alerteservice.service.AlerteService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/alertes")
public class AlerteController {

    private final AlerteService alerteService;

    public AlerteController(AlerteService alerteService) {
        this.alerteService = alerteService;
    }

    @GetMapping
    public List<AlerteDTO> getAlertes(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        return alerteService.getAlertes(authorization);
    }
}