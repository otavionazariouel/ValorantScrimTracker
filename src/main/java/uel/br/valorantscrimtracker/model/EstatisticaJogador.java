package uel.br.valorantscrimtracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_estatisticas")
@Data
public class EstatisticaJogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento N:1 - Várias estatísticas pertencem a uma partida
    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;

    // Relacionamento N:1 - Várias estatísticas pertencem a um jogador
    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    private String agente;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
}