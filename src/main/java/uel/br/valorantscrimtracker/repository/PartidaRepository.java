package uel.br.valorantscrimtracker.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uel.br.valorantscrimtracker.model.Partida;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

    List<Partida> findByOponenteContainingIgnoreCaseOrMapaContainingIgnoreCase(String oponente, String mapa, Sort sort);
}