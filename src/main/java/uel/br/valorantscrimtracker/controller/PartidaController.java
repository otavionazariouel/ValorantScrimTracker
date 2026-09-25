package uel.br.valorantscrimtracker.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uel.br.valorantscrimtracker.model.Partida;
import uel.br.valorantscrimtracker.service.PartidaService;

import java.util.List;

@Controller
@RequestMapping("/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @GetMapping
    public String listarPartidas(@RequestParam(required = false) String oponente,
                                 @RequestParam(required = false) String mapa,
                                 Model model) {
        List<Partida> partidas;

        if (oponente != null && !oponente.isBlank()) {
            partidas = partidaService.buscarPorOponente(oponente);
        } else if (mapa != null && !mapa.isBlank()) {
            partidas = partidaService.buscarPorMapa(mapa);
        } else {
            partidas = partidaService.listarTodas();
        }

        model.addAttribute("partidas", partidas);
        model.addAttribute("oponenteFiltro", oponente);
        model.addAttribute("mapaFiltro", mapa);
        return "partidas/lista";
    }

    @GetMapping("/nova")
    public String exibirFormularioNovaPartida(Model model) {
        model.addAttribute("partida", new Partida());
        return "partidas/formulario";
    }

    @PostMapping("/salvar")
    public String salvarPartida(@Valid @ModelAttribute("partida") Partida partida,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "partidas/formulario";
        }
        partidaService.salvar(partida);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Partida salva com sucesso!");
        return "redirect:/partidas";
    }

    @GetMapping("/detalhes/{id}")
    public String verDetalhes(@PathVariable Long id, Model model) {
        Partida partida = partidaService.buscarPorId(id);
        model.addAttribute("partida", partida);
        return "partidas/detalhes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPartida(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        partidaService.deletarPorId(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Partida removida com sucesso!");
        return "redirect:/partidas";
    }
    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Partida partida = partidaService.buscarPorId(id);
        model.addAttribute("partida", partida);
        return "partidas/formulario";
    }
}