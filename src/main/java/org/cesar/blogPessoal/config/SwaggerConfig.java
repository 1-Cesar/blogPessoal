package org.cesar.blogPessoal.config;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springBlogPessoalOpenAPI() {
        return new OpenAPI()
                		.info(new Info()
		                        .title("Meu Blog Pessoal")
		                        .description("API Rest Blog Pessoal")
		                        .version("v1")
                        .license(new License()
                                .name("Todos os Direitos Reservados - Cesar Augusto")
                                .url("https://portfolio-cesar.netlify.app/"))
                        .contact(new Contact()
                                .name("Cesar Augusto")
                                .url("https://portfolio-cesar.netlify.app/")
                                .email("cesar.desenvolvedor@gmail.com")))
                		.externalDocs(new ExternalDocumentation()
		                        .description("Github")
		                        .url("https://github.com/1-Cesar"));

    }

    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }

    @Bean
    public OpenApiCustomiser customerGlobalResponseStatus() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Salvo!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluido!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro de Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro Interno no Servidor!"));
            }));
        };
    }
}
