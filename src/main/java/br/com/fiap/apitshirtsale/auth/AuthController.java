package br.com.fiap.apitshirtsale.auth;

import br.com.fiap.apitshirtsale.domain.user.User;
import br.com.fiap.apitshirtsale.domain.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
public class AuthController {

    public Algorithm ALGORITHM;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public AuthController(@Value("${jwt.secret}") String secret, AuthService authService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        ALGORITHM = Algorithm.HMAC256(secret);
    }

    public Token create(User user){
        var expires = LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.ofHours(-3));

        var token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("username", user.getUsername())
                .withExpiresAt(expires)
                .sign(ALGORITHM);

        return new Token(token, user.getId().toString());
    }

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials){
        var user = userRepository.findByUsername(credentials.username())
                .orElseThrow(() -> new RuntimeException("Access Denied"));

        if ( !passwordEncoder.matches(credentials.password(), user.getPassword()) )
            throw new RuntimeException("Access Denied");

        return authService.create(user);
    }

}
