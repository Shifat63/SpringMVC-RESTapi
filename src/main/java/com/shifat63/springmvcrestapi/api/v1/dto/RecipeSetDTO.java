package com.shifat63.springmvcrestapi.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

// Author: Shifat63

@Data
@AllArgsConstructor
public class RecipeSetDTO {
    Set<RecipeDTO> recipes = new HashSet<>();
}
