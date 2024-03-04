package petcare.adopt.authentication.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import petcare.adopt.authentication.domain.User;
import petcare.adopt.authentication.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/petcare/user")
public class UserController extends GenericController<User> {

    @Autowired
    private UserService userService;

    @GetMapping("/authenticate")
    public ResponseEntity<User> authenticate(@RequestParam String username, @RequestParam String password) {
        Optional<User> optionalUser = userService.authenticate(username, password);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(optionalUser.get());
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> findByUsername(@RequestParam Long userId) {
        boolean exists = userService.exists(userId);

        return ResponseEntity.ok(exists);
    }

}
