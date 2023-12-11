package br.com.trabalhotds.user;

import org.springframework.web.bind.annotation.RestController;

import br.com.trabalhotds.utils.Utils;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/users")
public class UserController{

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        
        UserModel user = this.userRepository.findByUsername(userModel.getUsername());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já registrado");            
        }
        UserModel userCreated = this.userRepository.save(userModel);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário: "+ userCreated.getUsername() + " criado com sucesso!");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@RequestBody UserModel userModel){
        
        UserModel user = this.userRepository.findByUsername(userModel.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não existe.");            
        }else{
            this.userRepository.delete(userModel);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody UserModel userModel, HttpServletRequest request, @PathVariable Id id){
        UserModel user = this.userRepository.findById(id).orElse(null);

        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não existe.");
        }else{
            Utils.copyNonNullProperties(userModel, user);
            return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.save(user));
        }
    }

    @GetMapping("/")
    public List<UserModel> listAllUsers() {
        List<UserModel> users = this.userRepository.findAll();
        return users; 
    }

    @GetMapping("/{id}")
    public Optional<UserModel> listUser(Id id) {
        Optional<UserModel> user = this.userRepository.findById(id);
        return user;
    }
    
       



}