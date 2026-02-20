package br.com.Games.Games.Controller;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Games.Games.DTO.GameDTO;
import br.com.Games.Games.Model.GameModel;
import br.com.Games.Games.Repository.GameRepository;
import br.com.Games.Games.Util.Utils;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/games")
public class GameController {
    
    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public ResponseEntity<?> listarJogos(){
        return ResponseEntity.ok().body(this.gameRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarJogo(@PathVariable UUID id){
        var game = gameRepository.findById(id).orElse(null);
        if(game != null){
            return ResponseEntity.ok().body(game);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Jogo não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> criarJogo(@RequestBody @Valid GameDTO gameDTO){
        
        GameModel gameModel = new GameModel();
        BeanUtils.copyProperties(gameDTO, gameModel);

        var verifyGameName = this.gameRepository.findByName(gameModel.getName());

        if(verifyGameName == null ){
            return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(gameModel));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Um jogo com esse nome já existe!");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizarJogo(@RequestBody GameModel gameModel, @PathVariable UUID id){
        var game = this.gameRepository.findById(id).orElse(null);
        if(game != null){
            Utils.copyNonNullProperties(gameModel, game);
            return ResponseEntity.ok().body(gameRepository.save(gameModel));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Jogo não encontrado, tente novamente com outro ID!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarJogo(@PathVariable UUID id){
        var game = this.gameRepository.findById(id).orElse(null);
        if(game != null){
            gameRepository.deleteById(id);
            return ResponseEntity.ok().body("Jogo deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Jogo não encontrado");
        }
    }
}
