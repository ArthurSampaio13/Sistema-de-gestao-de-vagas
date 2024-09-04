package br.com.gestao_vagas.gestao_vagas.modules.company.controllers;

import br.com.gestao_vagas.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.gestao_vagas.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.gestao_vagas.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@Tag(name = "Company", description = "Endpoint para criar uma company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    @Operation(summary = "Criar uma company",
            description = "Essa função é responsável por criar uma company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CompanyEntity.class)),
            }),
            @ApiResponse(responseCode = "400", description = "Company já existe")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        try {
            return ResponseEntity.ok(this.createCompanyUseCase.execute(companyEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
