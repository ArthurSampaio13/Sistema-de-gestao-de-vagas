package br.com.gestao_vagas.gestao_vagas.modules.company.controllers;

import br.com.gestao_vagas.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.gestao_vagas.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.gestao_vagas.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
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

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/company")
@Tag(name = "Autenticação da company", description = "Endpoint responsável por autenticar a company e retornar o token")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação da company",
            description = "Essa função é responsável por autenticar a company e retornar o token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AuthCompanyDTO.class)),
            }),
            @ApiResponse(responseCode = "401", description = "Username/password incorrect.")
    })
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        try {
            return ResponseEntity.ok().body(authCompanyUseCase.execute(authCompanyDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
