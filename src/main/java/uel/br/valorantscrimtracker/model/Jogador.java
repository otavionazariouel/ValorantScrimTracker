package uel.br.valorantscrimtracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_jogadores")
@Data
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String tag;
    private String funcao;
}