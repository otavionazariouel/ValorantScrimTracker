package uel.br.valorantscrimtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uel.br.valorantscrimtracker.model.EstatisticaJogador;
import uel.br.valorantscrimtracker.model.Partida;
import uel.br.valorantscrimtracker.service.EstatisticaJogadorService;
import uel.br.valorantscrimtracker.service.PartidaService;

@Controller
@RequestMapping("/estatisticas")
public class EstatisticaJogadorController {

    private final EstatisticaJogadorService estatisticaService;
    private final PartidaService partidaService;

    public EstatisticaJogadorController(EstatisticaJogadorService estatisticaService, PartidaService partidaService) {
        this.estatisticaService = estatisticaService;
        this.partidaService = partidaService;
    }

    @PostMapping("/salvar")
    public String salvarEstatistica(@ModelAttribute EstatisticaJogador estatistica, @RequestParam Long partidaId) {
        Partida partida = partidaService.buscarPorId(partidaId);
        estatistica.setPartida(partida);
        estatisticaService.salvar(estatistica);

        // Redireciona de volta para os detalhes da partida
        return "redirect:/partidas/detalhes/" + partidaId;
    }
}