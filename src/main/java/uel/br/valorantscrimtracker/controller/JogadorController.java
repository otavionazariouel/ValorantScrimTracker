package uel.br.valorantscrimtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uel.br.valorantscrimtracker.model.Jogador;
import uel.br.valorantscrimtracker.service.JogadorService;

@Controller
@RequestMapping("/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String listarJogadores(Model model) {
        model.addAttribute("jogadores", jogadorService.listarTodos());
        return "jogadores/lista";
    }

    @GetMapping("/novo")
    public String exibirFormularioNovoJogador(Model model) {
        model.addAttribute("jogador", new Jogador());
        return "jogadores/formulario";
    }

    @PostMapping("/salvar")
    public String salvarJogador(@ModelAttribute Jogador jogador) {
        jogadorService.salvar(jogador);
        return "redirect:/jogadores";
    }

    @GetMapping("/deletar/{id}")
    public String deletarJogador(@PathVariable Long id) {
        jogadorService.deletarPorId(id);
        return "redirect:/jogadores";
    }
}