package by.grsu.entity;

import by.grsu.entity.enumiration.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private Role role;

    @OneToOne(fetch = FetchType.EAGER)
    private Card card;

    @OneToMany
    private List<BoughtBonus> boughtBonuses;
}
