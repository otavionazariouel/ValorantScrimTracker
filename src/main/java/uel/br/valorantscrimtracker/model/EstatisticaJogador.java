package uel.br.valorantscrimtracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_estatisticas", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"partida_id", "jogador_id"})
})
@Data
public class EstatisticaJogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento N:1
    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;

    // Relacionamento N:1
    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    private String agente;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
}