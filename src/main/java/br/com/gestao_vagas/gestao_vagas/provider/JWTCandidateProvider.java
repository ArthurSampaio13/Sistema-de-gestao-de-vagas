package br.com.gestao_vagas.gestao_vagas.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTCandidateProvider {
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public DecodedJWT validateToken(String token) {
        var tokenBearer = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var tokenFinal = JWT.require(algorithm)
                    .build()
                    .verify(tokenBearer);
            return tokenFinal;
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
