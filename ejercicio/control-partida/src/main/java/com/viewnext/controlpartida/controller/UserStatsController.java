
package com.viewnext.controlpartida.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viewnext.controlpartida.domain.GameStats;
import com.viewnext.controlpartida.service.GameService;

/**
 * This class implements a REST API for the GameControl User Statistics service.
 */
@RestController
@RequestMapping("/estadisticas")
class UserStatsController {

    private final GameService gameService;

    public UserStatsController(final GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public GameStats getStatsForUser(@RequestParam("userId") final Long userId) {
        return gameService.retrieveStatsForUser(userId);
    }
}
