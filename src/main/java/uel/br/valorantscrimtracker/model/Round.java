package uel.br.valorantscrimtracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_rounds")
@Data
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento N:1 - Vários rounds pertencem a uma partida
    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;

    private Integer numeroRound;
    private String lado;       // Ataque ou Defesa
    private String vencedor;   // Nosso Time ou Oponente
    private String tipoCompra; // Eco, Forçado, Armado
}