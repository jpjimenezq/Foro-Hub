package api.forohub.forohub.infra.security;

import api.forohub.forohub.domain.user.User;
import api.forohub.forohub.domain.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private User user;
    @Autowired
    private UserRepository userRepository;

    /**
     * Generates a JWT token for the given user.
     * @param user The user object for whom the token is generated.
     * @return JWT token as a String.
     */
    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
            return JWT.create()
                    .withIssuer("forohub")
                    .withSubject(user.getUsername())
                    .withClaim("id",user.getId())
                    .withExpiresAt(expirationdate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }
    /**
     * Retrieves the subject (username) from the JWT token.
     * @param token JWT token string.
     * @return Username (subject) extracted from the token.
     */
    public String getSubject(String token){
        if (token == null) {
            throw new IllegalArgumentException("Token is null");
        }

        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getSubject();
            if (username == null) {
                throw new IllegalArgumentException("Invalid token: Subject not found");
            }
            User user = (User) userRepository.findByUsername(username);
            if (user == null) {
                throw new IllegalArgumentException("User not found for username: " + username);
            }

            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("forohub")
                    .build()
                    .verify(token);

            return verifier.getSubject();
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid token: " + e.getMessage(), e);
        }

        }
    /**
     * Calculates the expiration date for JWT token (2 hours from now).
     * @return Instant representing the expiration date.
     */
    private Instant expirationdate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
