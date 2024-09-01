package br.com.gestao_vagas.gestao_vagas.modules.candidate.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    private String description;
    private String username;
    private String email;
    private String name;
    private UUID id;
}
