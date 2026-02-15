package br.com.Games.Games.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    
    @NotBlank(message = "O nome do usuário não pode ser nulo!")
    private String name;

    @NotBlank(message = "O email do usuário não pode ser nulo!")
    private String email;

    @NotBlank(message = "A senha do usuário não pode ser nulo!")
    private String password;
}
