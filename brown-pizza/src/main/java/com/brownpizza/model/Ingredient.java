package com.brownpizza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Ingredient {

    @Id
    private String id;

    private String name;
    private Type type;

    public enum Type {
        MEAT, VEGETABLE, CHEESE, SAUCE, PEPPERS
    }
}
