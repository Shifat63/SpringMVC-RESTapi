package com.shifat63.springmvcrestapi.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

// Author: Shifat63

public abstract class ConvertToJson {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
