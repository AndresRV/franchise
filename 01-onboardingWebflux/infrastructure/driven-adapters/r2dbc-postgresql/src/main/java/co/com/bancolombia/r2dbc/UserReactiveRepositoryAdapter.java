package co.com.bancolombia.r2dbc;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserRepository;
import co.com.bancolombia.r2dbc.entity.UserEntity;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import co.com.bancolombia.r2dbc.helper.UserEntityMapper;
import lombok.AllArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Component
@Repository
@AllArgsConstructor
public class UserReactiveRepositoryAdapter implements UserRepository
{
    private final UserReactiveRepository userReactiveRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Mono<User> saveUser(User user) {
        return userReactiveRepository.save(userEntityMapper.toEntity(user))
                .map(userEntity -> userEntityMapper.toUser(userEntity));
    }

    @Override
    public Mono<User> getUserById(Long id) {
        return userReactiveRepository.findById(id)
                .map(userEntity -> userEntityMapper.toUser(userEntity));
    }

    @Override
    public Flux<User> getAllUsers() {
        return userReactiveRepository.findAll()
                .map(userEntity -> userEntityMapper.toUser(userEntity));
    }

    @Override
    public Mono<User> getUserByFirstName(String firstName) {
        return userReactiveRepository.findByIdFirstName(firstName)
                .map(userEntity -> userEntityMapper.toUser(userEntity));
    }
}
