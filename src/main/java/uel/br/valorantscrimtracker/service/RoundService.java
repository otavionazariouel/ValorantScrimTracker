package uel.br.valorantscrimtracker.service;

import org.springframework.stereotype.Service;
import uel.br.valorantscrimtracker.model.Round;
import uel.br.valorantscrimtracker.repository.RoundRepository;

import java.util.List;

@Service
public class RoundService {

    private final RoundRepository roundRepository;

    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    public Round salvar(Round round) {
        return roundRepository.save(round);
    }

    public List<Round> listarTodos() {
        return roundRepository.findAll();
    }

    public Round buscarPorId(Long id) {
        return roundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Round não encontrado com ID: " + id));
    }

    public void deletarPorId(Long id) {
        roundRepository.deleteById(id);
    }
}