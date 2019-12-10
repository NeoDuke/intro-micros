package com.viewnext.suma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viewnext.suma.domain.SumaResultAttempt;
import com.viewnext.suma.service.SumaService;

/**
 * This class provides a REST API to POST the attempts from users.
 */
@RestController
@RequestMapping("/resultados-suma")
final class SumaResultAttemptController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SumaResultAttemptController.class);

    private final SumaService sumaService;

    private final int serverPort;

    @Autowired
    SumaResultAttemptController(
            final SumaService sumaService,
            @Value("${server.port}") int serverPort) {
        this.sumaService = sumaService;
        this.serverPort = serverPort;
    }

    @PostMapping
    ResponseEntity<SumaResultAttempt> postResult(
            final @RequestBody SumaResultAttempt
                    sumaResultAttempt) {
        return ResponseEntity.ok(
                sumaService.checkAttempt(sumaResultAttempt)
        );
    }

    @GetMapping
    ResponseEntity<List<SumaResultAttempt>> getStatistics(
            final @RequestParam("alias") String alias) {
        return ResponseEntity.ok(
                sumaService.getStatsForUser(alias)
        );
    }

    @GetMapping("/{resultId}")
    ResponseEntity<SumaResultAttempt> getResultById(final @PathVariable("resultId") Long resultId) {
        log.info("Retrieving result {} from server @ {}", resultId, serverPort);
        return ResponseEntity.ok(
                sumaService.getResultById(resultId)
        );
    }

}
