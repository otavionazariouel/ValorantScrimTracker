package uel.br.valorantscrimtracker.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uel.br.valorantscrimtracker.model.Partida;
import uel.br.valorantscrimtracker.service.JogadorService;
import uel.br.valorantscrimtracker.service.PartidaService;

import java.util.List;

@Controller
@RequestMapping("/partidas")
public class PartidaController {

    private final PartidaService partidaService;
    private final JogadorService jogadorService;

    public PartidaController(PartidaService partidaService, JogadorService jogadorService) {
        this.partidaService = partidaService;
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String listarPartidas(@RequestParam(required = false) String busca,
                                 @RequestParam(required = false, defaultValue = "dataPartida") String campoOrdenacao,
                                 @RequestParam(required = false, defaultValue = "desc") String direcao,
                                 Model model) {

        Sort sort = direcao.equalsIgnoreCase("asc") ? Sort.by(campoOrdenacao).ascending() : Sort.by(campoOrdenacao).descending();

        List<Partida> partidas;

        if (busca != null && !busca.trim().isEmpty()) {
            partidas = partidaService.buscarPorOponenteOuMapa(busca, sort);
        } else {
            partidas = partidaService.listarTodas(sort);
        }

        model.addAttribute("partidas", partidas);
        model.addAttribute("busca", busca);
        model.addAttribute("ordemInversa", direcao.equalsIgnoreCase("asc") ? "desc" : "asc");

        return "partidas/lista";
    }

    @GetMapping("/nova")
    public String exibirFormularioNovaPartida(Model model) {
        model.addAttribute("partida", new Partida());
        return "partidas/formulario";
    }

    @PostMapping("/salvar")
    public String salvarPartida(@ModelAttribute Partida partida, RedirectAttributes redirectAttributes) {
        boolean isEdicao = partida.getId() != null;

        partidaService.salvar(partida);

        String mensagem = isEdicao ? "Partida atualizada com sucesso!" : "Partida cadastrada com sucesso!";
        redirectAttributes.addFlashAttribute("sucesso", mensagem);

        return "redirect:/partidas";
    }

    @GetMapping("/detalhes/{id}")
    public String verDetalhes(@PathVariable Long id, Model model) {
        Partida partida = partidaService.buscarPorId(id);
        model.addAttribute("partida", partida);
        model.addAttribute("jogadores", jogadorService.listarTodos());
        return "partidas/detalhes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPartida(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        partidaService.deletarPorId(id);

        redirectAttributes.addFlashAttribute("sucesso", "Partida removida com sucesso!");

        return "redirect:/partidas";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Partida partida = partidaService.buscarPorId(id);
        model.addAttribute("partida", partida);
        return "partidas/formulario";
    }
}