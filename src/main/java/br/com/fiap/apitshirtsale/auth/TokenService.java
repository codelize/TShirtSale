package br.com.fiap.apitshirtsale.auth;

import br.com.fiap.apitshirtsale.domain.user.User;
import br.com.fiap.apitshirtsale.domain.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public Algorithm ALGORITHM;
    private final UserRepository userRepository;

    public TokenService(@Value("${jwt.secret}") String secret, UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public User getUserFromToken(String token) {
        var id =JWT.require(ALGORITHM)
                .withIssuer("api_tshirtsale")
                .build()
                .verify(token)
                .getSubject();

        return userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

}
