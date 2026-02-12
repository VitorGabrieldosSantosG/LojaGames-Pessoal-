package br.com.Games.Games.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Games.Games.Model.UserModel;
import br.com.Games.Games.Repository.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> listarUsuarios() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> verificarUsuario(@PathVariable UUID id){

        var user = userRepository.findById(id);

        if(user != null){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado! \n Tente novamente com outro Id.");
        }

    }

    @PostMapping("")
    public ResponseEntity<?> criarUsuario(@RequestBody UserModel userModel){
        var user = userRepository.findByEmail(userModel.getEmail());
        if(user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário com esse email já existe, tente recuperar sua senha!");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
        }
    }

    // TODO: criar funções de atualizar informações do usuário
    // @PutMapping("/{id}")
    // public ResponseEntity<?> atualizarUsuario(@RequestBody UserModel userModel, @PathVariable UUID id){

    // }

    // TODO: criar funções de deletar usuário
    // @DeleteMapping("/{id}")
    // public ResponseEntity deletarUsuario(@PathVariable UUID id){

    // }
}
