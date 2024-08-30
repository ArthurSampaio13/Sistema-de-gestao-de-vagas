package br.com.gestao_vagas.gestao_vagas.modules.company.useCases;

import br.com.gestao_vagas.gestao_vagas.modules.company.entities.JobEntity;
import br.com.gestao_vagas.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        if (jobEntity.getCompanyId() != null) {
            return this.jobRepository.save(jobEntity);
        }
        throw new RuntimeException("Company id nulo");
    }
}
