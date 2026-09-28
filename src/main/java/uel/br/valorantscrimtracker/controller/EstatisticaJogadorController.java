package uel.br.valorantscrimtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uel.br.valorantscrimtracker.model.EstatisticaJogador;
import uel.br.valorantscrimtracker.model.Partida;
import uel.br.valorantscrimtracker.service.EstatisticaJogadorService;
import uel.br.valorantscrimtracker.service.PartidaService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String salvarEstatistica(@ModelAttribute EstatisticaJogador estatistica,
                                    @RequestParam Long partidaId,
                                    RedirectAttributes redirectAttributes) {

        if (estatisticaService.existeJogadorNaPartida(partidaId, estatistica.getJogador().getId())) {
            redirectAttributes.addFlashAttribute("erroEstatistica",
                    "Este jogador já possui estatísticas cadastradas nesta partida!");
            return "redirect:/partidas/detalhes/" + partidaId;
        }

        Partida partida = new Partida();
        partida.setId(partidaId);
        estatistica.setPartida(partida);

        estatisticaService.salvar(estatistica);

        return "redirect:/partidas/detalhes/" + partidaId;
    }

    @GetMapping("/deletar/{id}")
    public String deletarEstatistica(@PathVariable Long id, @RequestParam Long partidaId, RedirectAttributes redirectAttributes) {
        estatisticaService.deletarPorId(id);

        redirectAttributes.addFlashAttribute("sucesso", "Estatística removida com sucesso!");
        return "redirect:/partidas/detalhes/" + partidaId;
    }
}