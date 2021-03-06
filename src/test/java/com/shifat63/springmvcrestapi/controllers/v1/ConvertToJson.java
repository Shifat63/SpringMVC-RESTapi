package com.shifat63.springmvcrestapi.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

// Author: Shifat63

public abstract class ConvertToJson {
    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new JsonMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
