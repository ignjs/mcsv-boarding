package com.eleuthera.mcsv_boarding.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * La anotación @Configuration indica que esta clase contiene definiciones de beans.
 * Los beans son objetos que son instanciados, ensamblados y gestionados por un contenedor de Spring IoC.
 */
@Configuration
public class OpenAPIConfig {

    /**
     * @Bean indica que el método produce un bean que será gestionado por el contenedor de Spring.
     * Este bean de tipo OpenAPI es la configuración principal para la documentación.
     * @return El objeto OpenAPI configurado.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        // Define la información de la API, incluyendo el título, la versión y una descripción.
        Info apiInfo = new Info()
                .title("Eleuthera - Microservice Boarding") // Título de la documentación de la API.
                .version("1.0.0")          // Versión actual de la API.
                .description("Una descripción concisa de lo que hace tu API."); // Breve descripción.

        // Define las URLs de los servidores de la API.
        // Esto es útil para documentar diferentes entornos (desarrollo, producción, etc.).
        Server server = new Server();
        server.setUrl("http://localhost:8080"); // URL del servidor de la API (ejemplo para desarrollo local).
        server.setDescription("Servidor de entorno de desarrollo.");

        // Configura el objeto OpenAPI con la información y las URLs de los servidores.
        return new OpenAPI()
                .info(apiInfo)
                .servers(List.of(server)); // Añade la lista de servidores a la configuración.
    }
}