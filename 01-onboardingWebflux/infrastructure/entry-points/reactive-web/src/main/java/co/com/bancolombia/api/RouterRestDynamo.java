package co.com.bancolombia.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestDynamo {
    private static final String PATH = "/api/information";

    @Bean
    public RouterFunction<ServerResponse> routerFunctionDynamo(HandlerDynamo handlerDynamo) {
        return route(POST(PATH), handlerDynamo::saveInformationUser);
    }
}