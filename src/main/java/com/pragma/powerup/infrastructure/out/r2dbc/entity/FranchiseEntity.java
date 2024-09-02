package com.pragma.powerup.infrastructure.out.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "franchise")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FranchiseEntity {
    @Id
    private Long id;
    private String name;
}
