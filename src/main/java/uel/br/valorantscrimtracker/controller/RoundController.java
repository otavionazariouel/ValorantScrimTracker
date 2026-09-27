package uel.br.valorantscrimtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uel.br.valorantscrimtracker.model.Partida;
import uel.br.valorantscrimtracker.model.Round;
import uel.br.valorantscrimtracker.service.PartidaService;
import uel.br.valorantscrimtracker.service.RoundService;

@Controller
@RequestMapping("/rounds")
public class RoundController {

    private final RoundService roundService;
    private final PartidaService partidaService;

    public RoundController(RoundService roundService, PartidaService partidaService) {
        this.roundService = roundService;
        this.partidaService = partidaService;
    }

    @PostMapping("/salvar")
    public String salvarRound(@ModelAttribute Round round, @RequestParam Long partidaId) {
        Partida partida = partidaService.buscarPorId(partidaId);
        round.setPartida(partida);
        roundService.salvar(round);

        // Redireciona de volta para os detalhes da partida
        return "redirect:/partidas/detalhes/" + partidaId;
    }
}