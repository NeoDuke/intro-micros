package com.viewnext.suma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viewnext.suma.domain.Suma;
import com.viewnext.suma.service.SumaService;

/**
 * This class implements a REST API for our Suma application.
 */
@RestController
@RequestMapping("/sumas")
final class SumaController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SumaController.class);

    private final SumaService sumaService;

    private final int serverPort;

    @Autowired
    public SumaController(final SumaService sumaService, @Value("${server.port}") int serverPort) {
        this.sumaService = sumaService;
        this.serverPort = serverPort;
    }

    @GetMapping("/random")
    Suma getRandomMultiplication() {
        log.info("Generating a random plus operation from server @ {}", serverPort);
        return sumaService.createRandomPlusOperation();
    }

}
