package co.com.bancolombia.redis.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRedisModel {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
