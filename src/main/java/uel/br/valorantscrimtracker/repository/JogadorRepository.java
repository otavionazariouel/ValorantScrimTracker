package uel.br.valorantscrimtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uel.br.valorantscrimtracker.model.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}