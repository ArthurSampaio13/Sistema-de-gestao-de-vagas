package br.com.gestao_vagas.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {
    @Schema(example = "Vaga para pessoa desenvolvedora Junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "GYMPass, Plano de saude", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
