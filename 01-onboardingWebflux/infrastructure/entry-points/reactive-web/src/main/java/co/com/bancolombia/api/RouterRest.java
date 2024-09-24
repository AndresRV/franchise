package co.com.bancolombia.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    private static final String PATH = "/api/user";
    private static final String PATH_BY_ID = "/api/user/{id}";
    private static final String PATH_BY_FIRST_NAME = "/api/user/name/{name}";

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST(PATH_BY_ID), handler::saveUser)
                .andRoute(GET(PATH_BY_ID), handler::getUserById)
                .andRoute(GET(PATH), handler::getAllUsers)
                .and(route(GET(PATH_BY_FIRST_NAME), handler::getUserByFirstName));
    }
}