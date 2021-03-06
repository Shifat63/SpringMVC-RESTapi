package com.shifat63.springmvcrestapi.api.v1.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//Author: Shifat63

@Data
public class CategoryDTO {
    @ApiModelProperty(value = "Id of a certain category")
    private Long categoryId;

    @ApiModelProperty(value = "Name of category", notes = "Name must not be empty and must be between 1 to 100 characters", required = true)
    private String name;
}
