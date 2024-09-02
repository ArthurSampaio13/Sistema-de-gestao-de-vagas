package br.com.gestao_vagas.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    @Schema(example = "Desenvolvedor Java")
    private String description;
    @Schema(example = "Maria")
    private String username;
    @Schema(example = "maria@gmail.com")
    private String email;
    @Schema(example = "Maria")
    private String name;
    private UUID id;
}
