package co.com.bancolombia.redis.template;

import co.com.bancolombia.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class ReactiveRedisTemplateAdapterOperationsTest {

    @Mock
    private ReactiveRedisConnectionFactory connectionFactory;

    @Mock
    private ObjectMapper objectMapper;

    private ReactiveRedisTemplateAdapter adapter;
    private User userMock;
    private UserRedisModel userRedisModelMock;

    @BeforeEach
    void setUp() {
        userMock = User.builder()
                .id(1L)
                .email("a@s.co")
                .firstName("qwer")
                .lastName("asdf")
                .build();

        userRedisModelMock = UserRedisModel.builder()
                .id(1L)
                .email("a@s.co")
                .firstName("qwer")
                .lastName("asdf")
                .build();

        MockitoAnnotations.openMocks(this);

        when(objectMapper.map(userMock, UserRedisModel.class)).thenReturn(userRedisModelMock);

        adapter = new ReactiveRedisTemplateAdapter(connectionFactory, objectMapper);
    }

    @Test
    void testSave() {
        StepVerifier.create(adapter.save("key", userMock))
                .expectNext(userMock)
                .verifyComplete();
    }

    @Test
    void testSaveWithExpiration() {

        StepVerifier.create(adapter.save("key", userMock, 2))
                .expectNext(userMock)
                .verifyComplete();
    }

    @Test
    void testFindById() {

        StepVerifier.create(adapter.findById("key"))
                .verifyComplete();
    }

}