package com.ragnax.compartetriprepositorio.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

/**
 * Created by pechofrio on 13-08-19.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String PACKAGE_CONTROLLER = "com.ragnax.compartetriprepositorio.controller";

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.POST, new ArrayList<>())
                .globalResponseMessage(RequestMethod.GET, new ArrayList<>())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_CONTROLLER))
                .paths(PathSelectors.any())
                .build();
    }

	private ApiInfo apiInfo() {
        Contact contacto = new Contact("RagnaxSistemas", "", "soporte@ragnaxsistemas.cl");
        return new ApiInfo("Microservices Rest feecomision", "MicroServicio que implementa " +
                "Repositorio de datos de Arrastrame Trip",
                "1.0.0", "Ragnax Sistemas", contacto, "Apache",
                "http://www.ragnaxsistemas.cl.", new ArrayList<VendorExtension>());
    }

}