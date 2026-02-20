package br.com.Games.Games.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Games.Games.Model.GameModel;


public interface GameRepository extends JpaRepository<GameModel, UUID> {
    GameModel findByName(String name);
}
