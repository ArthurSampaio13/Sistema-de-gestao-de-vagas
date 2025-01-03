package br.com.gestao_vagas.gestao_vagas.modules.candidate.controllers;

import br.com.gestao_vagas.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import br.com.gestao_vagas.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.gestao_vagas.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidado")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @PostMapping("/")
    @Operation(summary = "Cadastro de candidato",
            description = "Essa função é responsável por cadastrar um candidato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CandidateEntity.class)),
            }),
            @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('candidate')")
    @Operation(summary = "Perfil do candidato",
            description = "Essa função é responsável por buscar as informações do perfil do candidato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class)),
            }),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    })
    @SecurityRequirement(name = "JWTAuth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
            System.out.println(ResponseEntity.ok().body(profile));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('candidate')")
    @Operation(summary = "Listagem de vagas disponível para o candidado",
            description = "Essa função é responsável por listar todas as vagas disponível baseado no filtro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class))),
            })
    })
    @SecurityRequirement(name = "JWTAuth")
    public List<JobEntity> findJobByFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterUseCase.execute(filter);
    }

    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('candidate')")
    @Operation(summary = "Inscrição do candidado para uma vaga",
            description = "Essa função é responsável por realizar a inscrição do candidado em uma vaga")
    @SecurityRequirement(name = "JWTAuth")
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob) {

        var idCandidate = request.getAttribute("candidate_id");

        try {
            var result = this.applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()), idJob);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
}
