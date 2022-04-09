package com.fiap.challengefiap.configSwagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.fiap.challengefiap.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ChallengeFiap")
                .description("desafio da equipe será criar uma modelagem e consultas (Java/Hibernate) de informações comportamentais do consumidor no Onboarding (momento de chegada ao estabelecimento para a retirada do cartão de consumo), exemplos de informações comportamentais que devem ser exibidas na tela de onboarding logo após o preenchimento do telefone.")
                .version("1.0")
                .contact(contact())
                .build();

    }

    private Contact contact() {
        return new Contact(
                "Pablo A. Ruiz",
                "https://github.com/PabloaRuiz/ChallengeFiap",
                "Pabloa.ruiz@outlook.com");
    }
}
