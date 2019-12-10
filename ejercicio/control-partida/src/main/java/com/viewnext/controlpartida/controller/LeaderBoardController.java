package com.viewnext.controlpartida.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viewnext.controlpartida.domain.LeaderBoardRow;
import com.viewnext.controlpartida.service.LeaderBoardService;

import java.util.List;

/**
 * This class implements a REST API for the GameControl LeaderBoard service.
 */
@RestController
@RequestMapping("/lideres")
class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;

    public LeaderBoardController(final LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping
    public List<LeaderBoardRow> getLeaderBoard() {
        return leaderBoardService.getCurrentLeaderBoard();
    }
}
