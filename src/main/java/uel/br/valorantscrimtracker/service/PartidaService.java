package uel.br.valorantscrimtracker.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uel.br.valorantscrimtracker.model.Partida;
import uel.br.valorantscrimtracker.repository.PartidaRepository;

import java.util.List;

@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;

    public PartidaService(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    public Partida salvar(Partida partida) {
        return partidaRepository.save(partida);
    }

    public List<Partida> listarTodas(Sort sort) {
        return partidaRepository.findAll(sort);
    }

    public Partida buscarPorId(Long id) {
        return partidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada com ID: " + id));
    }

    public List<Partida> buscarPorOponenteOuMapa(String termo, Sort sort) {
        return partidaRepository.findByOponenteContainingIgnoreCaseOrMapaContainingIgnoreCase(termo, termo, sort);
    }

    public void deletarPorId(Long id) {
        Partida partida = buscarPorId(id);
        partidaRepository.delete(partida);
    }
}