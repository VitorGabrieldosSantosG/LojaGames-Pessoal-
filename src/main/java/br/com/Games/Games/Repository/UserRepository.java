package br.com.Games.Games.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Games.Games.Model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID>{
    UserModel findByEmail(String email);
}
