package api.forohub.forohub.controller;

import api.forohub.forohub.domain.user.User;
import api.forohub.forohub.domain.user.UserData;
import api.forohub.forohub.infra.security.DataJWTToken;
import api.forohub.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager; // Spring's AuthenticationManager for user authentication

    @Autowired
    private TokenService tokenService;

    /**
     * Endpoint for user authentication. Generates JWT token upon successful authentication.
     * @param userData User data containing username and password.
     * @return ResponseEntity containing JWT token.
     */
    @PostMapping // Use POST method for authentication
    public ResponseEntity userAuthentication(@RequestBody @Valid UserData userData) {
           Authentication aUtoken = new UsernamePasswordAuthenticationToken(userData.username(),userData.password());
            var authenticadedUser = authenticationManager.authenticate(aUtoken);
            var token= tokenService.generateToken((User) authenticadedUser.getPrincipal());
            return ResponseEntity.ok(new DataJWTToken(token));
        }
    }
