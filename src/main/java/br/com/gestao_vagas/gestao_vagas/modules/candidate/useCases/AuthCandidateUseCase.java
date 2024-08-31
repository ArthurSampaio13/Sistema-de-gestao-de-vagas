package br.com.gestao_vagas.gestao_vagas.modules.candidate.useCases;

import br.com.gestao_vagas.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) {
        var candidate = candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect.");
                });
        var passwordMatches = this.passwordEncoder
                .matches(authCandidateRequestDTO.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new BadCredentialsException("Senha incorreta.");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("candidate"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
                .acessToken(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCandidateResponse;
    }
}
