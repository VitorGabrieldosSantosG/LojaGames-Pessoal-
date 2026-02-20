package br.com.Games.Games.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GameDTO {

    @NotBlank(message = "O nome do jogo não pode ser vazio!")
    private String name;

    @NotBlank(message = "A descrição do jogo não pode ser vazio!")
    private String description;

    @NotBlank(message = "O nome do desenvolvedor do jogo não pode ser vazio!")
    private String developer;

    @NotBlank(message = "O nome das plataformas do jogo não pode ser vazio!")
    private String plataforma;

    @NotNull(message = "O preço do produto não pode ser nulo!")
    private double price;
}
