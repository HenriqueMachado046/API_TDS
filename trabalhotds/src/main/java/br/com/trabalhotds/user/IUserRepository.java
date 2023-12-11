package br.com.trabalhotds.user;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Id;


public interface IUserRepository extends JpaRepository<UserModel, Id>{
 
   UserModel findByUsername(String username);

}