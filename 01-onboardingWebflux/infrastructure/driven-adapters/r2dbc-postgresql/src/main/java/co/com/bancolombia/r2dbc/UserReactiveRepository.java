package co.com.bancolombia.r2dbc;

import co.com.bancolombia.r2dbc.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserReactiveRepository extends ReactiveCrudRepository<UserEntity, Long> {
    @Query("SELECT * FROM users WHERE first_name = :firstName")
    Mono<UserEntity> findByIdFirstName(String firstName);
}
