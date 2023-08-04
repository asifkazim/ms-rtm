package az.asifkazim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket commissionApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("az.asifkazim"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "Region TM API",
                "",
                "1.0",
                null,
                new Contact("", "", ""),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }


//    @Bean
//    public WebMvcConfigurer webMvcConfigurer()
//    {
//        return new WebMvcConfigurer()
//        {
//            @Override
//            public void addResourceHandlers( ResourceHandlerRegistry registry )
//            {
//                registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations( "classpath:/META-INF/resources/" );
//                registry.addResourceHandler( "/webjars/**" ).addResourceLocations( "classpath:/META-INF/resources/webjars/" );
//            }
//
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController( "/swagger-ui/")
//                        .setViewName("forward:"  +  "/swagger-ui/index.html");
//            }
//        };
//    }

}
