package br.com.Games.Games.Controller;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.Games.Games.DTO.UserDTO;
import br.com.Games.Games.Model.UserModel;
import br.com.Games.Games.Repository.UserRepository;
import br.com.Games.Games.Util.Utils;
import jakarta.validation.Valid;

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
    public ResponseEntity<?> buscarUsuario(@PathVariable UUID id){
        var user = this.userRepository.findById(id).orElse(null);
        if(user != null){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado!\n Tente novamente com outro Id.");
        }
    }

    //Utiliza o Valid com UserDTO para validar se não foram inserido dados nulos
    @PostMapping("")
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid UserDTO userDTO){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        var passwordEncrypted = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordEncrypted);
        UserModel user = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@RequestBody UserModel userModel, @PathVariable UUID id){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado, tente com outro id");
        } else {
            Utils.copyNonNullProperties(userModel, user);
            var userUpdated = this.userRepository.save(userModel);
            return ResponseEntity.ok().body(userUpdated);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable UUID id){
        var user = this.userRepository.findById(id);
        if(user != null){
            this.userRepository.deleteById(id);
            return ResponseEntity.ok().body("Usuário deletado com sucesso!!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
        }
    }
}
