package kg.example.spring.ecomarket.controller;

import kg.example.spring.ecomarket.entities.User;
import kg.example.spring.ecomarket.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/allUser")
    public List<User> getALlUser(){
        return userService.getAllUser();
    }
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User successfully created");
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User savedUser = userService.uploadUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.removeUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("The user has been successfully deleted");
    }
}
