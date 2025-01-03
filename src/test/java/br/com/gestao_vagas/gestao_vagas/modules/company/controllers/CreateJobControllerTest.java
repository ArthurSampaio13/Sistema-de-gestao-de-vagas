package br.com.gestao_vagas.gestao_vagas.modules.company.controllers;


import br.com.gestao_vagas.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.gestao_vagas.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.gestao_vagas.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.gestao_vagas.gestao_vagas.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static br.com.gestao_vagas.gestao_vagas.utils.TestUtils.objectToJson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    @Autowired
    private CompanyRepository companyRepository;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {

        var company = CompanyEntity.builder()
                .description("TESTE_DESCRIPTION")
                .email("TESTE_EMAIL@EMAIL.COM")
                .username("TESTE_USERNAME")
                .password("1234567890")
                .build();

        companyRepository.saveAndFlush(company);

        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        var result = mockMvc.perform(
                MockMvcRequestBuilders.post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(createJobDTO))
                        .header("Authorization", TestUtils.generateToken(company.getId(),
                                "secret"))

        ).andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }

    @Test
    public void should_not_be_able_to_create_a_new_if_company_not_found() throws Exception {

        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/company/job/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectToJson(createJobDTO))
                                .header("Authorization", TestUtils.generateToken(UUID.randomUUID(),
                                        "secret")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
