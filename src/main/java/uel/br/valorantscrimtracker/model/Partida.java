package uel.br.valorantscrimtracker.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_partidas")
@Data
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataPartida = LocalDate.now();
    private String mapa;
    private String oponente;
    private Integer placarSeuTime;
    private Integer placarOponente;

    // Relacionamento 1:N
    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
    private List<Round> rounds;

    // Relacionamento 1:N
    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
    private List<EstatisticaJogador> estatisticas;
}