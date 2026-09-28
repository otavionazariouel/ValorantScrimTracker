package uel.br.valorantscrimtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uel.br.valorantscrimtracker.model.Partida;
import uel.br.valorantscrimtracker.model.Round;
import uel.br.valorantscrimtracker.service.PartidaService;
import uel.br.valorantscrimtracker.service.RoundService;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rounds")
public class RoundController {

    private final RoundService roundService;
    private final PartidaService partidaService;

    public RoundController(RoundService roundService, PartidaService partidaService) {
        this.roundService = roundService;
        this.partidaService = partidaService;
    }

    @GetMapping("/novo/{partidaId}")
    public String novoRound(@PathVariable Long partidaId,
                            @RequestParam(required = false, defaultValue = "1") Integer numero,
                            @RequestParam(required = false) String lado,
                            Model model) {
        Round round = new Round();
        round.setNumeroRound(numero);
        if (lado != null) {
            round.setLado(lado);
        }

        model.addAttribute("round", round);
        return "rounds/formulario";
    }

    @PostMapping("/salvar")
    public String salvarRound(@ModelAttribute Round round,
                              @RequestParam Long partidaId,
                              RedirectAttributes redirectAttributes) {

        // Se o round já existir, cria a mensagem de erro e redireciona
        if (roundService.existeRoundNaPartida(partidaId, round.getNumeroRound())) {
            redirectAttributes.addFlashAttribute("erroRound",
                    "O Round " + round.getNumeroRound() + " já foi adicionado a esta partida!");
            return "redirect:/partidas/detalhes/" + partidaId;
        }

        Partida partida = new Partida();
        partida.setId(partidaId);
        round.setPartida(partida);

        roundService.salvar(round);

        return "redirect:/partidas/detalhes/" + partidaId;
    }

    @GetMapping("/deletar/{id}")
    public String deletarRound(@PathVariable Long id, @RequestParam Long partidaId, RedirectAttributes redirectAttributes) {
        roundService.deletarPorId(id);

        redirectAttributes.addFlashAttribute("sucesso", "Round removido com sucesso!");
        return "redirect:/partidas/detalhes/" + partidaId;
    }
}

