package br.com.gestao_vagas.gestao_vagas.modules.candidate.controllers;

import br.com.gestao_vagas.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Autenticação candidate", description = "Endpoint para autenticação do candidado")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação de candidato",
            description = "Essa função é responsável por autenticar o candidado e retornar o token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AuthCandidateResponseDTO.class)),
            }),
            @ApiResponse(responseCode = "401", description = "Username/password incorrect.")
    })
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var token = this.authCandidateUseCase.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
}
