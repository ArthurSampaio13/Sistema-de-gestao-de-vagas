package br.com.gestao_vagas.gestao_vagas.modules.candidate.repositories;

import br.com.gestao_vagas.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
