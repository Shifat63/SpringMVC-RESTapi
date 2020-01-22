package com.shifat63.springmvcrestapi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

//Author: Shifat63

@Data
@Entity
@EqualsAndHashCode(exclude = {"recipes"})
@ToString(exclude = {"recipes"})
@Table(name = "ingredient")
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long ingredientId;

    @NotBlank(message = "Name must not be empty")
    @Size(min = 1,max = 100, message = "Name must be between 1 to 100 characters")
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    private Set<Recipe> recipes;
}
