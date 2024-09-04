package br.com.gestao_vagas.gestao_vagas.modules.company.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo (username) não deve conter espaço.")
    @Schema(example = "Arthur", requiredMode = Schema.RequiredMode.REQUIRED, description = "Username da company")
    private String username;

    @Email(message = "O campo (email) deve conter um e-mail válido")
    @Schema(example = "Arthur@email.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "Email da company")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
    @Schema(example = "1234567890", requiredMode = Schema.RequiredMode.REQUIRED, description = "Senha da company")
    private String password;

    @Schema(example = "company.com.br", requiredMode = Schema.RequiredMode.REQUIRED, description = "Site da company")
    private String website;

    @Schema(example = "Vaga júnior", requiredMode = Schema.RequiredMode.REQUIRED, description = "Breve descrição sobre a vaga")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
