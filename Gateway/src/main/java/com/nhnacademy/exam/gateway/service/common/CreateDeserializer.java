package com.nhnacademy.exam.gateway.service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.gateway.exception.CreateFailException;
import java.util.Map;

public class CreateDeserializer {
    public static Map<String, Long> getJsonDeserializedMapAndFailCheck(String json)
        throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        failCheck(json, om);

        Map<String, Long> map = om.readValue(json,
            new TypeReference<Map<String, Long>>() {});
        return map;
    }

    private static void failCheck(String json, ObjectMapper om) throws JsonProcessingException {
        String[] split = json.split(":");
        if (split[0].contains("fail")) {
            Map<String, String> map = om.readValue(json,
                new TypeReference<Map<String, String>>() {});
            throw new CreateFailException("create fail : becauese " + map.get("fail"));
        }
    }
}
