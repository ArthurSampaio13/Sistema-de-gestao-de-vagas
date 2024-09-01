package br.com.gestao_vagas.gestao_vagas.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProfileCandidateResponseDTO {
    private String description;
    private String username;
    private String email;
    private String name;
    private UUID id;
}
