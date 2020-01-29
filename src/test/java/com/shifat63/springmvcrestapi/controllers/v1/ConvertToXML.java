package com.shifat63.springmvcrestapi.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// Author: Shifat63

public abstract class ConvertToXML {
    public static String asXmlString(final Object obj) {
        try {
            ObjectMapper objectMapper = new XmlMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
