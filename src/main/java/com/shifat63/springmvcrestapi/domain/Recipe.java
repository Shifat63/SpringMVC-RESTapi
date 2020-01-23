package com.shifat63.springmvcrestapi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//Author: Shifat63

@Data
@Entity
@EqualsAndHashCode(exclude = {"ingredients", "categories"})
@ToString(exclude = {"ingredients", "categories"})
@Table(name = "recipe")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;

    @NotBlank(message = "Name must not be empty")
    @Size(min = 1,max = 200, message = "Name must be between 1 to 200 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Recipe must have ingredients")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id") )
    private Set<Ingredient> ingredients = new HashSet<>();

    @NotEmpty(message = "Recipe must belong to one or more than one category")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

}
