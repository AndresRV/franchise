package co.com.bancolombia.model.information;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Information {
    private String id;
    private String email;
    private String firstName;
    private String lastName;

    public Information toUpperCase() {
        return this.toBuilder()
                .email(this.getEmail().toUpperCase())
                .firstName(this.getFirstName().toUpperCase())
                .lastName(this.getLastName().toUpperCase())
                .build();
    }
}
