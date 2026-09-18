package uel.br.valorantscrimtracker.service;

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

    public List<Partida> listarTodas() {
        return partidaRepository.findAll();
    }

    public Partida buscarPorId(Long id) {
        return partidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada com ID: " + id));
    }

    public List<Partida> buscarPorOponente(String oponente) {
        return partidaRepository.findByOponenteContainingIgnoreCase(oponente);
    }

    public List<Partida> buscarPorMapa(String mapa) {
        return partidaRepository.findByMapaContainingIgnoreCase(mapa);
    }

    public void deletarPorId(Long id) {
        partidaRepository.deleteById(id);
    }
}