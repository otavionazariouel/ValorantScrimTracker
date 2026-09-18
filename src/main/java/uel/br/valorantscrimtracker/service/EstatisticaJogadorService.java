package uel.br.valorantscrimtracker.service;

import org.springframework.stereotype.Service;
import uel.br.valorantscrimtracker.model.EstatisticaJogador;
import uel.br.valorantscrimtracker.repository.EstatisticaJogadorRepository;

import java.util.List;

@Service
public class EstatisticaJogadorService {

    private final EstatisticaJogadorRepository estatisticaRepository;

    public EstatisticaJogadorService(EstatisticaJogadorRepository estatisticaRepository) {
        this.estatisticaRepository = estatisticaRepository;
    }

    public EstatisticaJogador salvar(EstatisticaJogador estatistica) {
        return estatisticaRepository.save(estatistica);
    }

    public List<EstatisticaJogador> listarTodas() {
        return estatisticaRepository.findAll();
    }

    public EstatisticaJogador buscarPorId(Long id) {
        return estatisticaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estatística não encontrada com ID: " + id));
    }

    public void deletarPorId(Long id) {
        estatisticaRepository.deleteById(id);
    }
}