package uel.br.valorantscrimtracker.service;

import org.springframework.stereotype.Service;
import uel.br.valorantscrimtracker.model.Jogador;
import uel.br.valorantscrimtracker.repository.JogadorRepository;

import java.util.List;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public Jogador salvar(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    public List<Jogador> listarTodos() {
        return jogadorRepository.findAll();
    }

    public Jogador buscarPorId(Long id) {
        return jogadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com ID: " + id));
    }

    public void deletarPorId(Long id) {
        jogadorRepository.deleteById(id);
    }
}